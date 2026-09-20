# TaskForge

TaskForge helps small software teams plan work, follow it into a deployed application, and investigate what happens after delivery.

**Epic → story/task → pull request → build → deployed version → health and logs → incident and corrective work.**

The product is being developed in three releases: a complete work-management board, deployment tracking and operational investigation, then MCP access. The delivery plan also turns a spare PC into the private home server that runs TaskForge.

## Why TaskForge exists

A work board can tell a team that a story is done without showing whether it shipped, which services and environments contain it, or what happened after deployment. TaskForge will connect those answers so developers can move from planned work to delivery evidence and operational investigation in one product.

It is designed for small software teams that want to answer four questions:

- What are we building, and who is working on it?
- Which pull requests, builds and deployed versions contain that work?
- Is the deployed application healthy, and what do its logs and traces show?
- Which incidents and corrective tasks should we follow up on?

## An example journey

A team plans a checkout story in a 14-day sprint and splits it into implementation tasks. They link its GitHub pull request; the resulting build produces an immutable artifact with a record of included work. Operator-run scripts deploy that artifact, and TaskForge records the deployment receipt for each service and environment.

If checkout errors appear, the team can inspect the deployed version's health, bounded logs and trace links, record an incident, and link corrective work. An authorised MCP client will also be able to read that evidence and create an issue. A deployment containing the story establishes an association, not proof that the story caused the errors.

This is the planned end-to-end experience, not a description of features already available. TaskForge observes deployments; it does not execute deployments or rollbacks. It connects to observability backends rather than replacing their raw telemetry storage.

## Current implementation

Repository inspection on 2026-09-19 found:

| Area | Present now | Planned |
|---|---|---|
| Backend | Java 17 target, Spring Boot 3.4.5, Maven, Spring MVC, JPA/Hibernate, validation and Actuator | Supported-version review, complete domain and security |
| Persistence | PostgreSQL driver, Liquibase changelog, users, organisations, memberships and project entity | Issues, hierarchy, sprints, delivery metadata and incidents |
| API | User create/read/update and organisation creation code | Complete authenticated product APIs |
| Tests | Unit and MVC tests; PostgreSQL Testcontainers repository configuration | Release acceptance, concurrency, tenant, browser and recovery coverage |
| Frontend | No Angular application yet | Angular/TypeScript board and investigation screens |
| Infrastructure | Local application configuration, HTTPS enabled on port 8443 | PC server, Compose, CI/CD, VPN, backups and observability |
| Integrations | No deployment, telemetry or MCP implementation | GitHub/Actions, Grafana stack and MCP tools |

These observations describe files, not passing release evidence. Application tests and deployments were **not run** for this documentation revision. Testcontainers currently references PostgreSQL 13; runtime and image support must be reviewed during foundation work. No Maven wrapper, Docker Compose stack or CI workflow is present yet.

## Planned product

- Organisations, invitations, roles and projects.
- Epics, stories, child tasks, standalone bugs and 14-day project sprints.
- A fixed Kanban workflow with assignments, priorities, labels, comments, filters and audit history.
- Work-item links to pull requests, builds, artifacts and deployments across services/environments.
- Deployment health, bounded application logs, trace links, alerts and incidents.
- Authenticated MCP access to work and operational evidence.

Work completion, deployment success and runtime health are separate facts. TaskForge records and investigates deployments; operator-controlled deployment scripts execute them.

## Architecture direction

Angular → Spring Boot modular monolith → PostgreSQL.

The backend integrates with GitHub/Actions and queries Prometheus, Loki and Tempo through authorised application boundaries. Grafana provides deeper investigation. Raw telemetry stays in those systems; TaskForge stores relationships, delivery records, incidents and audit metadata.

The first host is an Ubuntu Server LTS PC accessed through LAN/private VPN, using Docker Compose and separate UAT/release data. Cognito supplies release identity. See the [system overview](docs/architecture/system-overview.md) and [server build plan](docs/delivery/server-build-plan.md).

## Develop the existing backend

Run commands from this directory, which contains pom.xml.

Prerequisites:

- A JDK compatible with the current Java 17 target and an installed Maven.
- Docker for the PostgreSQL Testcontainers tests, including permission to download the configured image.
- A reachable PostgreSQL database for application startup.
- Locally configured database credentials and, when HTTPS is enabled, a usable PKCS12 keystore.

Inspect [application.properties](src/main/resources/application.properties) and [application-local.properties](src/main/resources/application-local.properties). Override configuration privately; do not copy committed credentials or certificate material into a server deployment.

| Environment variable | Purpose |
|---|---|
| SPRING_DATASOURCE_URL | Your PostgreSQL JDBC URL |
| SPRING_DATASOURCE_USERNAME | Database user |
| SPRING_DATASOURCE_PASSWORD | Database password |
| SERVER_SSL_KEY_STORE | Location of your keystore |
| SERVER_SSL_KEY_STORE_PASSWORD | Keystore password |
| SERVER_SSL_KEY_STORE_TYPE | PKCS12 for the current configuration |

Set these through your shell, IDE or private configuration mechanism before starting. Both committed profiles enable HTTPS on port 8443. Liquibase runs migrations and Hibernate validates the schema; startup requires the configured database and TLS material.

~~~sh
mvn test
mvn package
mvn spring-boot:run -Dspring-boot.run.profiles=local
~~~

The first two commands require Docker because the suite includes PostgreSQL tests. The startup command uses your separately configured application database, not a Testcontainers database. These are commands supported by the current Maven project, not a claim that they passed on this machine.

After successful startup, inspect [local health](https://localhost:8443/actuator/health) using a client that trusts your local certificate. Existing APIs use the /taskforge prefix. There is currently no web board to open.

## Delivery forecast

Every sprint lasts **14 calendar days**, with 4–5 focused hours per week. The initial forecast is **64 sprints / 128 weeks**; dates remain unset and estimates are revisited using actual progress.

| Milestone | Forecast | Acceptance |
|---|---|---|
| Server foundation | S08 | Private server, existing-backend deployment and recovery evidence |
| Release 1 | S32 | Complete authenticated board, hierarchy and sprint planning |
| Release 2 | S56 | Deployment tracking, logs, health, alerts and incidents |
| MCP v1 | S64 | Supported client completes the authorised work-to-operations journey |

## Documentation

- [Product charter and measurable goals](docs/product/product-charter.md)
- [Release 1 scope](docs/product/release-1-scope.md), [Release 2 scope](docs/product/release-2-scope.md), [MCP v1 scope](docs/product/mcp-v1-scope.md)
- [64-sprint roadmap and story cards](docs/delivery/sprint-roadmap.md)
- [Master delivery plan](docs/delivery/master-plan.md) and [epic backlog](docs/delivery/backlog.md)
- [Server build plan](docs/delivery/server-build-plan.md) and [home-lab acceptance checklist](docs/delivery/homelab-delivery-checklist.md)
- [System overview](docs/architecture/system-overview.md), [decisions](docs/architecture/decision-log.md), [risks](docs/delivery/risk-register.md)
- [Delivery guide and story template](docs/product/product-scope-and-delivery-guide.md)

Existing ER, class and normalisation documents describe earlier design work. They must be reconciled with the release contracts before implementing new domain models.

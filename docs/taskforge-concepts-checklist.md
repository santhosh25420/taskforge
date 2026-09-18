# TaskForge: concepts to build and ship

This is a learning checklist for implementing TaskForge locally and shipping its secure Release 1. Learn each topic far enough to implement it, explain the choice, and demonstrate the result in TaskForge. You do not need to finish every topic before starting to code.

## Tags and scope

| Tag | Meaning |
| --- | --- |
| `build` | Needed to implement, run, debug, or test TaskForge locally. A build-only entry adds no separate deployment learning requirement; its resulting code still ships. |
| `deploy` | Needed to configure, release, secure, or operate TaskForge outside the developer workstation. Deployment concepts can be rehearsed locally. |
| `build` `deploy` | Needed during implementation and again when preparing or operating a release. |

Every topic and checklist subtopic has a tag. Sections 1–18 cover Release 1. Section 19 is conditional later learning, not a prerequisite for Release 1.

The governing scope is an **Angular/TypeScript frontend, Spring Boot modular monolith, PostgreSQL, Cognito authentication, and Ubuntu/Debian home-lab deployment**. Cognito is required for secure Release 1 even though AWS application hosting is deferred.

The existing Maven project declares Java 17 and Spring Boot 3.4.5 and uses Liquibase. The broader learning README mentions Java 21; the decision log leaves final supported toolchain selection to foundation work. Treat version selection as an explicit decision, not an assumption that every roadmap version is already implemented or supported.

## 1. Product scope and delivery basics — `build` `deploy`

Start with the [plain-language guide and TaskForge examples](product/product-scope-and-delivery-guide.md), then use the [home-lab targets and release gates](delivery/homelab-delivery-checklist.md). These implement the planning part of this topic; learning and execution checks remain open until demonstrated.

- [ ] `build` **Requirements and acceptance criteria:** personas, user stories, happy paths, failure paths, and observable outcomes; turn “create an organisation” into a testable journey.
- [ ] `build` **Release 1 domain:** organisations, memberships, invitations, projects, issues, labels, comments, and audit history; understand how these relate.
- [ ] `build` **Scope boundaries:** organisation-wide project visibility, fixed workflow, archival, and no outbound invitation email; avoid implementing features from the broader roadmap by accident.
- [ ] `build` **Incremental delivery:** vertical slices, small tasks, dependencies, definition of ready, and definition of done; finish UI-to-database journeys.
- [ ] `build` `deploy` **Quality targets:** define expected data volume, concurrent users, latency, accessibility, and recovery needs before choosing infrastructure or claiming performance.
- [ ] `deploy` **Release gates:** distinguish local development, private functional MVP, UAT, and secure release; collect evidence for each transition.

## 2. Development tools and reproducible setup — `build` `deploy`

- [ ] `build` **Git:** working tree, staging, commits, branches, diffs, merge conflicts, `.gitignore`, and reviewable changes.
- [ ] `build` **Terminal and IDE:** paths, environment variables, process management, ports, breakpoints, stack traces, and HTTP requests using an API client or `curl`.
- [ ] `build` **Maven:** POM, dependencies and scopes, plugins, test/package lifecycle, dependency tree, and executable Spring Boot JARs.
- [ ] `build` **Frontend tooling:** Node, package manager, `package.json`, lockfiles, scripts, development server, and production build output.
- [ ] `build` `deploy` **Toolchain consistency:** select compatible JDK, Spring Boot, Node, Angular, PostgreSQL, and container versions; pin them consistently across local development and CI.
- [ ] `build` **Repeatable onboarding:** documented prerequisites, example environment configuration, disposable seed data, and a predictable startup command.

## 3. Java foundations — `build`

- [ ] `build` **Language basics:** types, control flow, methods, access modifiers, packages, and null handling.
- [ ] `build` **Object-oriented design:** classes, interfaces, constructors, encapsulation, composition, and immutability; represent business rules without excessive inheritance.
- [ ] `build` **Collections and generics:** lists, sets, maps, type parameters, equality, and `hashCode`; choose structures for membership checks, mappings, and ordered results.
- [ ] `build` **Exceptions:** checked versus unchecked exceptions, propagation, and domain-specific failures; distinguish invalid input, missing resources, conflicts, and infrastructure failures.
- [ ] `build` **Modern Java essentials:** records for suitable DTOs, enums for roles/statuses/priorities, lambdas, streams, and appropriate `Optional` use within the selected JDK.
- [ ] `build` **Identifiers and time:** UUIDs or the selected ID strategy, `Instant`, UTC persistence, and client-side display time zones.
- [ ] `build` **Concurrency basics:** shared mutable state, thread safety, race conditions, and atomicity; understand why an in-memory counter cannot safely allocate persistent issue numbers across instances.
- [ ] `build` **Lombok and generated code:** generated constructors, getters, builders, and equality; inspect generated behavior and avoid unsuitable entity equality or recursive `toString` methods.

## 4. Domain modeling and modular design — `build`

- [ ] `build` **Modular monolith:** capability-based packages, ownership of data, explicit dependencies, and one deployable backend.
- [ ] `build` **Layer responsibilities:** controllers handle HTTP; application services coordinate use cases; domain objects/rules enforce invariants; repositories handle persistence.
- [ ] `build` **DTOs and mapping:** request/response contracts separate from JPA entities; control exposed fields and avoid accepting server-owned values from clients.
- [ ] `build` **Entities and value objects:** identity versus value equality, validation boundaries, and concepts such as issue keys and normalized email addresses.
- [ ] `build` **Invariants:** unique membership, valid ownership, assignee membership, project-scoped labels, and restrictions on archived records.
- [ ] `build` **Workflow state machines:** encode the approved transitions among `BACKLOG`, `TODO`, `IN_PROGRESS`, `REVIEW`, and `DONE`; reject invalid changes without side effects.
- [ ] `build` **Design documentation:** ER diagrams, class diagrams, request sequence diagrams, and architecture decision records; explain actual implementation choices.

## 5. HTTP and API design — `build` `deploy`

- [ ] `build` **HTTP fundamentals:** methods, paths, query parameters, headers, JSON bodies, status codes, and request/response flow.
- [ ] `build` **Resource contracts:** organisation/project/issue URLs, server-generated fields, naming conventions, and an explicit API versioning policy.
- [ ] `build` **Validation and errors:** field constraints, nested validation, consistent error bodies, safe messages, and actionable conflict responses.
- [ ] `build` **Filtering and bounded reads:** combine status/priority/assignee/label filters, cap page sizes, and use deterministic ordering with a unique tie-breaker.
- [ ] `build` **Concurrent edit contracts:** version fields or a chosen conditional-request scheme; let the client recognize stale updates and recover intentionally.
- [ ] `build` **Retry and idempotency semantics:** decide which operations can be safely repeated and how invitation acceptance or a retried comment avoids duplicate effects.
- [ ] `build` **API documentation:** OpenAPI schemas, examples, authentication requirements, error cases, and frontend/backend contract agreement.
- [ ] `build` `deploy` **Browser/API boundaries:** origins, CORS, preflight requests, cookies versus authorization headers, and environment-specific API URLs.

## 6. Spring Boot and application behavior — `build` `deploy`

- [ ] `build` **Dependency injection:** beans, component scanning, constructor injection, configuration classes, and bean lifecycle.
- [ ] `build` **Spring MVC:** controllers, request mapping, JSON serialization, parameter binding, Bean Validation, and centralized exception handling.
- [ ] `build` **Transaction management:** `@Transactional`, proxy boundaries, rollback behavior, and why self-invocation can bypass transactional interception.
- [ ] `build` **Application boundaries:** provider-neutral current-actor interface, application services, and adapters for development identity and Cognito.
- [ ] `build` `deploy` **External configuration:** property precedence, environment variables, profiles, required settings, and fail-fast validation; keep development identity and development credentials out of release configuration.
- [ ] `build` `deploy` **Application lifecycle:** startup failures, dependency availability, graceful shutdown, and completing or safely failing in-flight work.
- [ ] `build` `deploy` **Actuator:** health endpoints, liveness versus readiness, restricted exposure, and avoiding sensitive health details.

## 7. Relational modeling and PostgreSQL — `build` `deploy`

- [ ] `build` **Relational foundations:** tables, rows, primary/foreign keys, cardinality, join tables, normalization, and referential integrity.
- [ ] `build` **TaskForge schema:** user profiles, organisations, memberships, invitations, projects, issues, labels, issue-label links, comments, and audit entries.
- [ ] `build` **SQL:** inserts, updates, deletes, joins, aggregation, sorting, pagination, and parameterized queries.
- [ ] `build` **Constraints:** `NOT NULL`, unique, check, and foreign-key constraints; enforce membership uniqueness and project/issue key uniqueness in the database.
- [ ] `build` **Tenant-safe relationships:** include organisation context in lookups and validate related-resource ownership; understand where composite constraints can reinforce application rules.
- [ ] `build` **Transactions and isolation:** ACID, MVCC, read committed behavior, row locks, deadlocks, and transaction boundaries.
- [ ] `build` **Issue-number allocation:** atomic project counter updates or a justified locking design; guarantee unique increasing allocations under concurrent creation and define whether gaps are acceptable.
- [ ] `build` **Index fundamentals:** B-tree indexes, composite column order, selectivity, partial indexes where useful, and write/storage costs.
- [ ] `build` `deploy` **Query performance:** inspect `EXPLAIN`/`EXPLAIN ANALYZE` on representative safe test data; measure board/filter queries before and after index changes.
- [ ] `deploy` **Database operations:** connection limits, application pool sizing, disk growth, maintenance awareness, least-privilege access, and restricted network reachability.

## 8. JPA, Hibernate, and schema migrations — `build` `deploy`

- [ ] `build` **Entity mapping:** identifiers, enums, relationships, owning sides, join columns, timestamps, and appropriate cascade/orphan-removal behavior.
- [ ] `build` **Persistence context:** managed/detached entities, dirty checking, flush versus commit, and repository operations.
- [ ] `build` **Fetch behavior:** lazy/eager loading, N+1 queries, fetch joins/projections, and loading DTO data within a deliberate transaction boundary.
- [ ] `build` **Pagination and relationships:** avoid unbounded collection loading and understand pagination pitfalls when fetching to-many relationships.
- [ ] `build` **Optimistic locking:** `@Version`, stale-write failures, transaction rollback, and mapping conflicts into useful API/UI behavior.
- [ ] `build` **Transactional audit:** persist an issue mutation and its audit entry atomically; rejected or rolled-back changes must not appear as successful audit events.
- [ ] `build` `deploy` **Liquibase:** changelogs, ordered changesets, checksums, migration history, and schema validation; use the repository's chosen migration tool.
- [ ] `build` `deploy` **Migration discipline:** test clean installation and upgrades; preserve applied migration history once environments depend on it.
- [ ] `deploy` **Safe schema evolution:** compatible rollout order, expand/contract changes when necessary, migration ownership, and application rollback compatibility; rolling back an image does not automatically reverse database changes.

## 9. Angular, TypeScript, and browser fundamentals — `build`

- [ ] `build` **HTML and CSS:** semantic markup, forms, Flexbox/Grid, responsive layout, visible focus, and readable validation states.
- [ ] `build` **JavaScript and TypeScript:** objects/arrays, modules, promises, async behavior, interfaces, unions, narrowing, and nullability.
- [ ] `build` **Angular fundamentals:** components, templates, bindings, inputs/outputs, services, dependency injection, and the selected version's recommended component structure.
- [ ] `build` **Routing:** organisation/project routes, issue detail links, route parameters, navigation, and protected-route user experience.
- [ ] `build` **Reactive forms:** field validation, submission state, server error mapping, and preventing accidental duplicate submissions.
- [ ] `build` **HTTP integration:** typed request/response models, interceptors, error handling, request cancellation, and authentication headers.
- [ ] `build` **State and reactivity:** signals/observables as appropriate, RxJS fundamentals, derived state, subscription lifetime, and avoiding stale responses.
- [ ] `build` **Organisation switching:** clear tenant-specific state, cancel obsolete requests, and prevent the previous organisation's data from appearing after switching.
- [ ] `build` **Kanban interaction:** status columns, issue detail editing, bounded loading, legal transitions, and restoring or refreshing state after a failed move.
- [ ] `build` **Usability and accessibility:** loading/empty/error/conflict states, keyboard operation, labels, focus management, and a non-drag status-change action.

## 10. Identity, authorization, and secure application design — `build` `deploy`

- [ ] `build` **Authentication versus authorization:** identify the caller separately from deciding which organisation resources and operations they may access.
- [ ] `build` **Development identity adapter:** stable actor contract and explicit local/private activation; design domain code so replacing the adapter does not rewrite business rules.
- [ ] `build` `deploy` **OAuth 2.0 and OpenID Connect:** authorization code flow with PKCE, identity provider, scopes, access versus ID tokens, expiry, refresh, and logout semantics.
- [ ] `build` `deploy` **Cognito integration:** user pool/app client, allowed callback/logout URLs, identity claims, and mapping a stable external identity to a TaskForge profile.
- [ ] `build` `deploy` **Spring Security resource server:** validate token signature, issuer, expiry, and intended client/audience according to the selected Cognito token type; reject wrong token use.
- [ ] `build` **Tenant authorization:** membership-scoped queries and service checks for every read/write; test guessed IDs, cross-tenant assignees, and cross-project labels.
- [ ] `build` **Role permissions:** implement the approved owner/admin/member matrix, owner-sensitive actions, and consistent denial behavior; UI hiding is only a usability feature.
- [ ] `build` **Invitation security:** unpredictable tokens, hashed secret storage, expiry, revocation, single use, authenticated email matching, and atomic acceptance.
- [ ] `build` `deploy` **Browser session security:** explicit token storage choice, XSS exposure, CSRF relevance for cookie-based flows, and server/client behavior after logout and expiry.
- [ ] `build` `deploy` **Input and output safety:** parameterized SQL, safe text rendering, request-size limits, and redaction of invitation tokens, credentials, and personal data from logs.
- [ ] `deploy` **Release identity configuration:** disable development identity, configure real allowed origins/redirects, protect credentials, and verify unauthenticated and expired-token access is denied.

## 11. Testing and debugging — `build` `deploy`

- [ ] `build` **Unit tests:** JUnit, AssertJ, focused Mockito use, fixtures, and behavior-based assertions for domain and service rules.
- [ ] `build` **Database integration tests:** Testcontainers with PostgreSQL, actual Liquibase migrations, repository behavior, transaction rollback, and constraint enforcement.
- [ ] `build` **API tests:** validation, status/error contracts, serialization, authentication, role checks, and tenant boundaries.
- [ ] `build` **Concurrency tests:** simultaneous issue creation, stale issue edits, and repeated invitation acceptance; verify durable outcomes rather than only mocked calls.
- [ ] `build` **Frontend tests:** component interactions, forms, HTTP failures, organisation switching, and conflict recovery using the selected frontend test tools.
- [ ] `build` **Browser journey tests:** organisation creation → invitation → project → issue → workflow → comments/labels/history, including negative permission paths.
- [ ] `build` **Debugging techniques:** reproduce failures, read root-cause exceptions, inspect SQL and HTTP traffic, and minimize failing examples.
- [ ] `build` `deploy` **Security and dependency checks:** application dependencies, frontend dependencies, container images, secret exposure, and remediation of release-blocking findings.
- [ ] `deploy` **UAT and operational tests:** persona-based acceptance, responsive/browser checks, smoke tests, restart, rollback, and backup/restore rehearsals against release candidates.

## 12. Containers and local environment packaging — `build` `deploy`

- [ ] `build` `deploy` **Docker basics:** images versus containers, Dockerfiles, layers, build context, `.dockerignore`, ports, networks, environment variables, and volumes.
- [ ] `build` **Docker Compose for development:** backend/frontend/database dependencies, startup readiness, disposable data, and documented local commands.
- [ ] `build` `deploy` **Networking inside containers:** service DNS names, host ports versus container ports, and why `localhost` inside one container does not mean another container.
- [ ] `deploy` **Release images:** reproducible multi-stage builds, suitable runtime images, non-root execution, bounded resources, and production frontend assets served by a web server.
- [ ] `deploy` **Persistent data:** database volumes independent of container lifetime; understand the difference between recreating a container and deleting its data.
- [ ] `deploy` **Artifact identity:** registry access, immutable tags/digests, commit/version metadata, and promoting the exact images tested in UAT.

## 13. Linux and home-lab networking — `deploy`

- [ ] `deploy` **Ubuntu/Debian administration:** users/groups, permissions, SSH keys, packages, service management, security updates, and basic shell scripts.
- [ ] `deploy` **Host diagnosis:** CPU, memory, disk, process and container status, open ports, and system/application logs.
- [ ] `deploy` **Network fundamentals:** IP addresses, TCP, DNS, ports, routing, firewalls, private versus externally reachable services, and trusted access boundaries.
- [ ] `deploy` **Reverse proxy:** frontend serving, `/api` routing or the selected API route, forwarded headers, timeouts, and Angular deep-link fallback without masking API errors.
- [ ] `deploy` **TLS:** certificates, trust chains, termination, hostname validation, renewal, and secure browser-to-service traffic.
- [ ] `deploy` **Environment separation:** distinct UAT/release configuration, credentials, networks, and database storage; both still share the home lab's physical failure domain.
- [ ] `deploy` **Boot and restart behavior:** container restart policies or service supervision, startup ordering, reboot recovery, and graceful shutdown verification.

## 14. CI/CD and release configuration — `deploy`

- [ ] `deploy` **Continuous integration:** clean checkout, pinned toolchains, deterministic dependency installation, backend/frontend checks, and integration-test support.
- [ ] `deploy` **Pipeline fundamentals:** jobs, dependencies, artifacts, caching, status checks, and credentials; learn the chosen CI system's syntax, such as GitHub Actions if selected.
- [ ] `deploy` **Secrets management:** environment-scoped credentials, protected CI secrets, restricted secret files or the selected secret store, rotation, and no secrets baked into frontend bundles or images.
- [ ] `deploy` **Build once, promote:** build/version images once, deploy to UAT, and promote the same approved image digests to release without rebuilding.
- [ ] `deploy` **Environment configuration:** database endpoints, API routing, Cognito settings, CORS, log levels, and health configuration; choose frontend configuration handling compatible with artifact promotion.
- [ ] `deploy` **Deployment sequence:** preflight checks, backup/migration compatibility, service update, readiness checks, smoke tests, and defined failure handling.
- [ ] `deploy` **Rollback:** retain known-good artifacts/configuration, specify rollback triggers, and handle schema compatibility separately from application rollback.

## 15. Observability and performance — `build` `deploy`

- [ ] `build` `deploy` **Structured logging:** timestamps, levels, request/correlation IDs, useful business context, exception causes, and redaction.
- [ ] `build` `deploy` **Health semantics:** distinguish process liveness from readiness to serve traffic; define expected behavior when PostgreSQL is unavailable.
- [ ] `build` `deploy` **Metrics:** request latency/error rate/throughput, JVM memory and GC, thread usage, connection pool pressure, and database query behavior.
- [ ] `build` **JVM performance basics:** heap versus stack, garbage collection, thread dumps, heap diagnostics, and profiling a measured bottleneck.
- [ ] `build` `deploy` **Capacity and load tests:** realistic board sizes and user actions, percentiles, saturation, timeouts, and documented baseline results.
- [ ] `deploy` **Dashboards and alerts:** select an appropriate monitoring stack; make failures, sustained errors, disk pressure, and backup failures visible with actionable thresholds.
- [ ] `deploy` **Log/metric operations:** retention, rotation, disk limits, access controls, and finding the running application version during investigation.

## 16. Backup, recovery, and routine operation — `deploy`

- [ ] `deploy` **Recovery objectives:** recovery point objective (acceptable data loss) and recovery time objective (acceptable recovery duration); set realistic home-lab targets.
- [ ] `deploy` **PostgreSQL backup:** consistent logical backups or the selected strategy, scheduling, retention, encryption/access control, and storage outside the live database volume.
- [ ] `deploy` **Failure-domain separation:** choose backup storage that survives the host failures covered by your recovery promise; another directory on the same disk does not cover disk loss.
- [ ] `deploy` **Restore verification:** restore to a clean database, validate records and application journeys, and measure recovery time; a successful backup command alone is insufficient evidence.
- [ ] `deploy` **Failure scenarios:** database outage, application crash, host reboot, full disk, failed migration, expired certificate, and broken identity configuration.
- [ ] `deploy` **Runbooks:** deployment, rollback, restore, restart, troubleshooting, certificate/credential renewal, and incident steps another person could follow.
- [ ] `deploy` **Maintenance:** dependency/host updates, backup checks, storage cleanup, access reviews, and documented known limitations.

## 17. UAT, release evidence, and handover — `deploy`

- [ ] `deploy` **Acceptance coverage:** map every Release 1 journey to automated tests or recorded UAT, including invited users, unauthorized users, and cross-tenant attempts.
- [ ] `deploy` **Release candidate:** identify exact frontend/backend versions, migration state, configuration assumptions, and remaining defects.
- [ ] `deploy` **Go/no-go decision:** assess acceptance results, unresolved security findings, rollback readiness, recovery evidence, and approved limitations.
- [ ] `deploy` **Handover documentation:** user guide, API documentation, architecture/deployment diagrams, release notes, and operator runbooks.
- [ ] `deploy` **Stabilization:** watch errors, latency, health, storage, authentication, and backups after promotion; prioritize release defects before enhancements.

## 18. Practical learning order and completion checks — `build` `deploy`

Use the sections above as a reference while completing these milestones. Check a learning item only when you can explain it and show evidence in code, tests, configuration, or a rehearsal.

| Order | Tags | Learn first | Evidence that you can move on |
| --- | --- | --- | --- |
| 1 | `build` | Scope, tooling, Java, domain design | Explain the Release 1 model and build the existing backend reproducibly. |
| 2 | `build` | HTTP, Spring, SQL, JPA, Liquibase, backend tests | Implement a validated organisation/project slice with real PostgreSQL tests. |
| 3 | `build` | TypeScript, Angular, forms, routing, API integration | Complete the organisation-to-project journey through the browser. |
| 4 | `build` | Invitations, issues, workflow, transactions, concurrency, audit | Demonstrate the complete private functional MVP, including failure recovery and negative tests. |
| 5 | `build` `deploy` | Containers, Linux, networking, configuration, basic recovery | Run the private MVP on home-lab UAT and demonstrate restart, rollback, and restore. |
| 6 | `build` `deploy` | Cognito, Spring Security, roles, tenant isolation | Replace development identity and pass authentication, permission, and cross-tenant tests. |
| 7 | `deploy` | CI/CD, TLS, secrets, observability, operational hardening | Produce versioned release artifacts and complete deployment/recovery rehearsals. |
| 8 | `deploy` | UAT, release approval, handover, stabilization | Promote the UAT-approved artifacts into the secure home-lab release and verify operation. |

Do not postpone tenant-aware modeling, safe invitation design, or testability until milestone 6. The real identity provider can follow the private MVP; the application boundaries must support it from the start.

## 19. Conditional later topics — `build` `deploy`

These topics belong to the broader TaskForge learning roadmap. **They are not required to build or ship the current Release 1.** The tags below describe their role only if that later scope is selected.

- [ ] `deploy` **AWS hosting:** IAM and least privilege, VPC/subnets/security groups, compute choice, managed PostgreSQL, DNS/TLS, secrets, monitoring, backups, infrastructure as code, budgets, and teardown. Trigger: the separately approved cloud milestone after home-lab operation is proven.
- [ ] `build` `deploy` **Redis:** cache-aside, TTLs, invalidation, tenant-scoped keys, rate-limit algorithms, failure behavior, and deployment/monitoring. Trigger: a measured caching or rate-limiting need.
- [ ] `build` `deploy` **Kafka or another broker:** event contracts, partitions/ordering, consumer groups, delivery semantics, outbox, retries, deduplication, dead-letter handling, and broker operations. Trigger: an approved asynchronous feature.
- [ ] `build` `deploy` **Notifications and jobs:** delivery state, scheduling, retry/backoff, idempotency, provider failures, credentials, and operational visibility. Trigger: outbound notifications or scheduled work enters scope.
- [ ] `build` `deploy` **Advanced search:** PostgreSQL full-text search or another selected engine, relevance, indexing, tenant filters, and index maintenance. Trigger: current structured filters cannot satisfy an approved search journey.
- [ ] `build` `deploy` **Attachments:** upload validation, object storage, access controls, size limits, malware-handling decisions, retention, and backup implications. Trigger: file attachments enter scope.
- [ ] `build` `deploy` **Microservices and distributed systems:** service boundaries, network failures, timeouts, consistency, distributed tracing, and independent releases. Trigger: evidence that a module needs independent deployment or scaling.
- [ ] `deploy` **Kubernetes and high availability:** orchestration, scheduling, service discovery, probes, resource limits, secrets, redundancy, and recovery across failure domains. Trigger: operational requirements justify the added platform complexity.
- [ ] `build` `deploy` **AI/MCP integrations:** tool contracts, model limits, retrieval, tenant-safe access, prompt-injection defenses, evaluations, provider credentials, and cost monitoring. Trigger: a concrete approved product use case.

## Repository sources

The accepted delivery documents control this checklist's Release 1 scope. Broader learning roadmaps remain useful for later study.

- [Release 1 scope](product/release-1-scope.md)
- [Delivery master plan](delivery/master-plan.md)
- [Architecture and product decision log](architecture/decision-log.md)
- [Delivery backlog](delivery/backlog.md)
- [Current Maven configuration](../pom.xml)

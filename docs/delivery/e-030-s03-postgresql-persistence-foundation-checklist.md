# E-030-S03 — PostgreSQL Persistence Foundation Checklist

## Purpose

Make PostgreSQL, Liquibase, and the JPA model the trusted persistence path for TaskForge before implementing invitations, projects, or issues.

This document is a delivery checklist only. Creating it does not modify application code, Maven configuration, migrations, tests, or infrastructure.

## Current evidence

- `mvn test -q` currently passes.
- `src/test/java/com/sk/taskforge/repos/IUsersRepositoryTest.java` uses H2, disables Liquibase, and creates the schema with Hibernate `create-drop`.
- The current green repository test therefore does not prove that the committed Liquibase migrations work on PostgreSQL.
- Testcontainers is not yet configured.
- The Docker daemon was unavailable during preparation; start Docker before running the PostgreSQL container tests.

## Scope

### Included

- PostgreSQL Testcontainers dependency and test configuration.
- Liquibase migration execution during persistence integration tests.
- Hibernate schema validation against the migrated PostgreSQL database.
- User, organisation, and organisation-member persistence coverage.
- Constraint and transaction-boundary verification.
- Test instructions and evidence recording.

### Excluded

- New product capabilities.
- Frontend work.
- Authentication or authorization implementation.
- Invitations, project workflows, issues, labels, comments, or audit history.
- Redis, Kafka, AWS, or home-lab deployment changes.
- Rewriting existing migrations unless a failing PostgreSQL test proves it is necessary.

## Implementation checklist

### 1. Confirm the execution environment

- [ ] Start Docker Desktop or the configured Docker daemon.
- [ ] Confirm the daemon is reachable with `docker version`.
- [ ] Confirm Maven can resolve Testcontainers dependencies.
- [ ] Confirm the working directory is the Git repository at `taskforge/taskforge`.

### 2. Establish the PostgreSQL test path

- [ ] Add the Testcontainers PostgreSQL and JUnit integration dependencies with versions managed by the Spring Boot dependency BOM where possible.
- [ ] Configure a reusable PostgreSQL container for persistence integration tests.
- [ ] Register container JDBC URL, username, and password through test-time dynamic properties.
- [ ] Prevent Spring Boot from replacing the container datasource with an embedded database.
- [ ] Keep the test database disposable and isolated per test run.
- [ ] Remove the H2 dependency once no test relies on it for persistence.

### 3. Exercise the real schema

- [ ] Stop disabling Liquibase in repository integration tests.
- [ ] Run the complete `db.changelog-master.yaml` against a fresh PostgreSQL container.
- [ ] Keep `spring.jpa.hibernate.ddl-auto=validate` for the integration path.
- [ ] Keep the PostgreSQL dialect for the integration path.
- [ ] Ensure the application context starts only when the migrated schema matches the JPA mappings.
- [ ] Capture and resolve any migration/entity naming, type, nullability, index, or constraint mismatch.

### 4. Add persistence acceptance coverage

- [ ] Persist and retrieve a user by ID.
- [ ] Persist and retrieve a user by email.
- [ ] Delete a user where the schema permits deletion.
- [ ] Persist an organisation for an existing owner.
- [ ] Verify that organisation creation creates exactly one owner membership through the service transaction.
- [ ] Verify duplicate user email is rejected by the database constraint.
- [ ] Verify duplicate organisation name for the same owner is rejected by the database constraint.
- [ ] Verify duplicate organisation membership is rejected by the database constraint.
- [ ] Verify an organisation with a missing owner is rejected by the foreign key.
- [ ] Verify a membership with a missing user or organisation is rejected by the foreign keys.
- [ ] Verify a failed organisation operation does not leave a partial organisation or membership record.
- [ ] Keep assertions focused on persisted state, constraint behaviour, and transaction outcomes rather than implementation details.

### 5. Verify the delivery path

- [ ] Run the targeted PostgreSQL persistence tests with Docker available.
- [ ] Run `mvn test -q` from the repository root.
- [ ] Confirm no test silently falls back to H2 or Hibernate-generated schema.
- [ ] Confirm the test logs show PostgreSQL and Liquibase activity.
- [ ] Record the container image/version and Java/Maven versions used for evidence.
- [ ] Record any environment prerequisite, especially Docker availability.

## Recommended verification commands

Run from `/Users/sk14/Documents/foundation-2026/taskforge/taskforge`:

```bash
docker version
mvn -Dtest=IUsersRepositoryTest test
mvn test -q
```

If the persistence coverage is split into additional test classes, run the complete persistence test package before the full suite.

## Definition of done

- [ ] A fresh PostgreSQL container applies every committed Liquibase change successfully.
- [ ] Spring Boot starts with `ddl-auto=validate` against that migrated database.
- [ ] The user → organisation → owner-membership path passes against PostgreSQL.
- [ ] Duplicate, foreign-key, and rollback scenarios have automated coverage.
- [ ] No persistence test requires H2 or Hibernate `create-drop`.
- [ ] The full Maven test suite passes.
- [ ] Test evidence and any remaining risks are recorded in the delivery documentation.

## Risks and failure handling

| Risk | Response |
|---|---|
| Docker is unavailable | Start the Docker daemon before testing; do not replace PostgreSQL with H2 as a workaround. |
| Liquibase fails on a fresh database | Treat the migration failure as the primary defect; inspect migration order, SQL, rollback definitions, and PostgreSQL compatibility. |
| Hibernate validation fails | Reconcile the entity mapping and migration deliberately; do not switch validation off. |
| Existing tests depend on H2 behaviour | Convert the test to PostgreSQL semantics or classify it as a unit test that does not claim persistence coverage. |
| Constraint failure is translated too broadly | Preserve the database constraint and narrow application-level exception translation only after identifying the violated constraint. |
| Tests are slow or flaky | Reuse one container per test class/suite, isolate data transactionally, and avoid dependence on developer-local databases. |

## Handoff after completion

Once this checklist is complete, continue with the remaining E-030 delivery-foundation work. The next product slice should be the organisation, invitation, and project journey under E-040; do not begin issue workflow work until that vertical slice is integrated.

## Traceability

- Master plan: `docs/delivery/master-plan.md`, Phase 3.
- Backlog: `docs/delivery/backlog.md`, E-030-S03.
- Risk register: `docs/delivery/risk-register.md`, especially R-007.

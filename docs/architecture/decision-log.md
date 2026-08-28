# TaskForge Architecture and Product Decision Log

## Purpose

This log records confirmed decisions, their consequences, and decisions that still require analysis. It prevents implementation from turning assumptions into undocumented architecture.

### Status meanings

| Status | Meaning |
|---|---|
| Accepted | Approved baseline; changing it requires an explicit superseding decision |
| Proposed | Recommended but not approved |
| Deferred | Deliberately postponed until a named trigger or phase |
| Superseded | Replaced by a later decision |

An accepted entry is not a substitute for a full ADR when alternatives, migration, security, or long-term consequences require deeper analysis.

## Accepted decisions

### D-001 — Product audience and delivery intent

- Status: Accepted
- Decision: TaskForge is a portfolio product delivered with professional product and engineering practices for small software teams.
- Consequences:
  - Product usefulness and delivery evidence matter more than technology count.
  - Claims about users, interviews, reliability, or scale require real evidence.
  - Release 1 stays focused rather than cloning a mature enterprise product.
- Related: G-01, G-04, R-004.

### D-002 — Angular Kanban-first web application

- Status: Accepted
- Decision: Release 1 includes an Angular and TypeScript frontend whose primary project workspace is a Kanban board.
- Consequences:
  - Frontend UX, accessibility, state recovery, API integration, and browser testing are first-class delivery work.
  - Status changes require a keyboard/non-drag alternative.
  - Exact Angular version and frontend libraries are selected and pinned during E-030.
- Related: R1-F07, E-010, E-030, E-050, R-009.

### D-003 — Modular monolith with PostgreSQL

- Status: Accepted
- Decision: The backend remains a Spring Boot modular monolith with PostgreSQL as the source of truth.
- Consequences:
  - Business capabilities require clear internal module boundaries without network distribution.
  - Redis, Kafka, microservices, and Kubernetes require later measured justification.
  - Exact supported Java/Spring/PostgreSQL versions are selected during foundation work.
- Related: C-02, E-020, E-030, R-014.

### D-004 — Invite-based multi-tenancy and roles

- Status: Accepted
- Decision: Release 1 supports multiple organisations with `OWNER`, `ADMIN`, and `MEMBER` roles.
- Consequences:
  - Organisation membership is an authorization boundary for all organisation-owned resources.
  - Backend service/application boundaries enforce permissions; frontend visibility is not sufficient.
  - The exact role permission matrix is an E-020 deliverable.
- Related: R1-F02, BR-01, BR-02, R-002.

### D-005 — Organisation-wide project visibility

- Status: Accepted
- Decision: Every organisation member can access every project belonging to that organisation in Release 1.
- Consequences:
  - Release 1 does not contain private projects or project-specific membership.
  - Authorization is organisation-scoped while still validating project ownership by organisation.
  - A future change requires data, API, UI, authorization, migration, and test design.
- Related: BR-03, OOS-06.

### D-006 — Two invitation experiences, one invitation lifecycle

- Status: Accepted
- Decision: An invitation is discoverable in-app by matching email and can also be accepted through an expiring shareable link.
- Consequences:
  - Both experiences must operate on one invitation state rather than creating separate memberships or tokens.
  - Acceptance requires the invited authenticated identity for secure Release 1.
  - Release 1 does not send outbound invitation email.
  - Token storage, expiry, revocation, and logging details require a security ADR.
- Related: R1-F03, E-040, R-003.

### D-007 — Fixed Release 1 workflow

- Status: Accepted
- Decision: Issue statuses are `BACKLOG`, `TODO`, `IN_PROGRESS`, `REVIEW`, and `DONE`, with controlled transitions defined in the Release 1 scope.
- Consequences:
  - Custom statuses and workflow configuration are out of scope.
  - API, domain, audit, UI, and tests use one shared transition policy.
  - Changing the transition graph requires a product decision and compatibility review.
- Related: R1-F06, OOS-05.

### D-008 — Focused issue-management feature set

- Status: Accepted
- Decision: Release 1 includes priorities, assignees, project labels, comments, filters, optimistic concurrency, and audit history.
- Consequences:
  - Attachments, watchers, due dates, outbound notifications, and advanced search are excluded.
  - Issue and project archival is preferred over destructive deletion in Release 1.
  - Scope additions follow the documented scope-change rule.
- Related: R1-F05 through R1-F10, R-004.

### D-009 — Authentication follows the private functional MVP

- Status: Accepted
- Decision: Core functionality may be developed using a development identity boundary, but Cognito authentication and authorization are mandatory before secure Release 1.
- Consequences:
  - The development identity mechanism is restricted to local/private profiles.
  - Domain/application services must depend on a provider-neutral current-actor contract.
  - The functional MVP cannot be publicly exposed or represented as production-ready.
- Related: R1-F01, E-070, E-080, R-001, R-016.

### D-010 — Home lab before AWS

- Status: Accepted
- Decision: Ubuntu/Debian home-lab UAT and release environments precede AWS work.
- Consequences:
  - Local, UAT, and release can be logically separate but share a physical failure domain.
  - The home lab is not highly available.
  - AWS is not allowed to distract from Release 1 functionality and operation.
- Related: C-04, E-070, E-100, R-010.

### D-011 — AWS requires separate approval

- Status: Accepted
- Decision: AWS hosting is a later delivery epic requiring a current architecture, cost estimate, explicit provisioning approval, and teardown plan.
- Consequences:
  - Release 1 does not depend on AWS hosting.
  - No ongoing cloud spending is assumed.
  - Cloud work must deploy already-tested application artifacts rather than rebuild per environment.
- Related: E-120, R-017.

### D-012 — Two-week gated iterations

- Status: Accepted
- Decision: Delivery uses two-week iterations with planning, integration, demonstration, retrospective, and backlog refinement.
- Consequences:
  - Calendar forecasts are created only after ready work is estimated against capacity.
  - Phase exit gates remain outcome-based and can span more than one iteration.
  - Incomplete work is not relabelled complete at iteration end.
- Related: master plan governance, C-07.

### D-013 — Documentation-only inception boundary

- Status: Accepted
- Decision: The first implementation tranche changes only Markdown delivery/product/architecture documentation.
- Consequences:
  - Existing `src`, tests, migrations, Maven configuration, application configuration, infrastructure, CI, Git layout, and learning documents remain unchanged.
  - Any subsequent implementation tranche declares its file-change boundary before execution.
  - Documentation created in this tranche becomes the baseline for later plans.
- Related: C-01, E-000.

### D-014 — Existing data does not require preservation

- Status: Accepted
- Decision: Current development data has no preservation requirement.
- Consequences:
  - E-020 may design a clean schema baseline rather than carrying exploratory migration history solely for local data.
  - Actual migration replacement still requires an explicitly authorized implementation tranche.
  - Demo fixtures must be reproducible rather than preserved manually.
- Related: A-04, E-020, E-030, R-006.

## Deferred decisions

### D-100 — Exact supported toolchain versions

- Status: Deferred to E-030
- Decision needed: Java, Spring Boot, Angular, Node, Maven, PostgreSQL, container base images, and test-tool versions.
- Required evidence: support windows, compatibility, migration impact, security posture, local/home-lab compatibility.
- Related risk: R-014.

### D-101 — Public API contract conventions

- Status: Deferred to E-020
- Decision needed: versioning, resource naming, error format, pagination, filtering, optimistic-concurrency representation, idempotency, OpenAPI ownership, and frontend-client generation/validation.
- Required evidence: Release 1 journeys, compatibility needs, implementation/test cost.
- Related risk: R-019.

### D-102 — Module boundaries and dependency enforcement

- Status: Deferred to E-020
- Decision needed: internal capabilities, ownership of entities/data, cross-module calls/events, and automated boundary enforcement.
- Required evidence: domain workflows and transaction boundaries.

### D-103 — Clean schema baseline and migration policy

- Status: Deferred to E-020/E-030
- Decision needed: baseline contents, identifiers, timestamps, email normalization, ownership representation, issue numbering, audit schema, indexes, and append-only migration rules after baseline.
- Required evidence: ER review, PostgreSQL integration plan, confirmation that no environment data must be preserved.
- Related risks: R-006, R-007, R-008.

### D-104 — Invitation security design

- Status: Deferred to E-020
- Decision needed: token entropy and encoding, hashing, lifetime, revocation, storage, URL/fragment handling, authenticated acceptance, audit, and redaction.
- Required evidence: invitation UX and threat model.
- Related risk: R-003.

### D-105 — Development identity and Cognito boundary

- Status: Deferred to E-020
- Decision needed: provider-neutral actor contract, local adapter activation, profile provisioning/linking, email normalization, Cognito claims, logout, token storage, and release safety.
- Required evidence: threat model and Cognito flow selection.
- Related risks: R-001, R-013, R-016.

### D-106 — Role and permission matrix

- Status: Deferred to E-020
- Decision needed: exact owner/admin/member actions, ownership transfer, member removal, self-removal, archive permissions, invitation management, and denial semantics.
- Required evidence: user flows and tenant threat model.
- Related risk: R-002.

### D-107 — Home-lab topology and promotion model

- Status: Deferred to E-020/E-030
- Decision needed: reverse proxy, TLS/private access, container topology, UAT/release logical separation, registry, artifact promotion, secrets, logging, backups, and deployment automation.
- Required evidence: server capacity, network boundary, recovery needs, and operational ownership.
- Related risks: R-010, R-011, R-012, R-015, R-016.

### D-108 — Quantified non-functional targets

- Status: Deferred to E-020
- Decision needed: accessibility standard, supported browsers, capacity model, latency/error targets, log retention, backup schedule, RPO, RTO, and acceptable recovery procedure duration.
- Required evidence: product criticality, home-lab capability, expected demo/use profile.
- Related risks: R-011, R-015.

### D-109 — Comment mutation policy

- Status: Deferred to E-010/E-020
- Decision needed: whether Release 1 comments can be edited or deleted, and how any mutation appears in audit/history.
- Required evidence: user flow, product need, moderation/security impact.

## ADR candidates

Create numbered ADRs during the architecture tranche for at least:

1. Modular-monolith boundaries and dependency rules.
2. API contract and compatibility policy.
3. PostgreSQL baseline, identifiers, timestamps, tenancy, and issue numbering.
4. Development identity and Cognito integration boundary.
5. Role/permission enforcement and tenant-scoped data access.
6. Invitation secret lifecycle.
7. Optimistic concurrency and conflict contract.
8. Audit consistency and immutability.
9. Local/home-lab environment and immutable artifact promotion.
10. Backup, restore, RPO, and RTO.

## Decision-change process

To change an accepted decision:

1. Identify the decision being superseded.
2. State the product or engineering evidence requiring change.
3. Compare viable alternatives and consequences.
4. Update affected scope, backlog, risks, contracts, and tests.
5. Record the new accepted decision and mark the previous decision superseded.


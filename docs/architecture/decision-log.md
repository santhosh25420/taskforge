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

## Decision history and current baseline

Revised 2026-09-20. Accepted entries remain active unless explicitly superseded below. The current product contracts and 64-sprint roadmap implement D-016–D-024; historical text in superseded entries is not current scope.

## Decisions

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

- Status: Superseded by D-020 on 2026-09-20 (historical sequencing)
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
  - Calendar commitments require ready work and measured capacity; the provisional numbered forecast is now defined by D-017.
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
- Decision: Earlier exploratory application data had no preservation requirement. This applies only to that historical development dataset, never to PC disks, existing files or server/release data; recheck actual data before any future schema replacement.
- Consequences:
  - E-020 may design a clean schema baseline rather than carrying exploratory migration history solely for local data.
  - Actual migration replacement still requires an explicitly authorized implementation tranche.
  - Demo fixtures must be reproducible rather than preserved manually.
- Related: A-04, E-020, E-030, R-006.

### D-015 — MCP v1 follows secure Release 1

- Status: Superseded by D-023 on 2026-09-20 (historical five-tool boundary)
- Decision: TaskForge delivers MCP only after secure Release 1, as a separately gated v1 integration. The v1 surface contains four tenant-scoped read tools (`taskforge_search_issues`, `taskforge_get_issue`, `taskforge_get_project_summary`, and `taskforge_get_issue_audit_history`) and one idempotent write tool (`taskforge_create_issue`).
- Consequences:
  - MCP v1 does not change Release 1 scope, security gates, or release forecast.
  - Each tool must delegate to the same application services and authorization policies as the web API; direct repository or SQL access is prohibited.
  - The implementation must select and document a stable MCP transport and compatible authenticated resource-server design before any business-data tool ships.
  - Resources, prompts, model sampling, AI assistance, bulk mutations, role/membership/invitation changes, archive operations, and arbitrary queries are excluded from v1.
- Related: E-130, `docs/delivery/sprint-roadmap.md`, D-004, D-009.

### D-016 — Work, delivery and operational visibility

- Status: Accepted, 2026-09-20
- Decision: TaskForge's product journey extends from epics/stories/tasks through PR/build/artifact/deployment to health, logs, incidents and corrective work.
- Consequences: Deliver the complete board first (Release 1), then delivery/investigation (Release 2), then MCP. Product/customer observability is distinct from monitoring the TaskForge host itself.
- Related: G-01–G-08; release contracts.

### D-017 — Capacity-based 64-sprint forecast

- Status: Accepted, 2026-09-20
- Decision: Use 64 sprints of exactly 14 calendar days, initially 4–5 hours/week, 8–10 available hours/sprint and approximately 6–8 planned delivery hours.
- Consequences: S08 server, S32 Release 1, S56 Release 2, S64 MCP are provisional forecasts. Re-estimate, split and move stable story IDs using actual progress; calendar dates remain unset.
- Related: D-012; sprint roadmap.

### D-018 — Work hierarchy and project sprints

- Status: Accepted, 2026-09-20
- Decision: Epics group same-project stories; stories own tasks; bugs stand alone. Stories/bugs are assigned to 14-day project sprints and tasks inherit; epics span sprints.
- Consequences: Prevent cycles/foreign parents, preserve closure/carryover history, separate parent progress from explicit work acceptance, and keep work/build/deployment/health states distinct.
- Related: R1-F12/R1-F13; E-150.

### D-019 — PC server and private access

- Status: Accepted, 2026-09-20
- Decision: The roadmap includes assessing/preserving the user's PC, Ubuntu Server LTS, storage, SSH, LAN/private VPN, private HTTPS, Compose, off-host backup and host monitoring.
- Consequences: Actual disks/addresses/resources and backup destination require evidence before execution. UAT/release have separate data/secrets but one physical failure domain. Personal services/public ingress/AWS hosting are outside this horizon.
- Related: E-140; H-01–H-08; D-010/D-011.

### D-020 — Identity before integrated board acceptance

- Status: Accepted, 2026-09-20
- Decision: Private functional slices may use the development actor boundary, but S26–S27 establish Cognito/tenant security before S28 integrated board acceptance and S32 Release 1.
- Consequences: Supersedes D-009's requirement that the complete private-MVP gate precede authentication. E-070 retains functional acceptance ownership with updated dependencies; release profiles reject development identity.
- Related: E-070/E-080; R1-F01.

### D-021 — Observe externally executed deployments

- Status: Accepted, 2026-09-20
- Decision: TaskForge tracks and investigates deployments; private operator scripts execute deploy/rollback independently and spool authenticated idempotent receipts.
- Consequences: GitHub/Actions is the first provider via outbound conditional polling/reconciliation. Freeze artifact inclusion evidence, show uncertain/partial mappings, and preserve rollback as a new deployment attempt.
- Related: E-160; R2-F01–R2-F10.

### D-022 — Authorised telemetry over specialist backends

- Status: Accepted, 2026-09-20
- Decision: Keep raw logs/metrics/traces in Loki/Prometheus/Tempo and query them through constrained authorised backend adapters. Grafana provides deeper investigation.
- Consequences: Shared host/control-plane and unpartitioned streams remain operator-only. Enforce source ownership, redaction, query limits, destination access and explicit stale/unknown/unavailable scope. Incident association is not automatic causation.
- Related: E-170/E-180; Release 2 telemetry policy.

### D-023 — Eleven-tool MCP after Release 2

- Status: Accepted, 2026-09-20
- Decision: MCP v1 follows Release 2 and exposes the eleven named tools in the MCP contract: ten read tools and only taskforge_create_issue as a mutation.
- Consequences: Supersedes D-015's five-tool-only boundary and earlier timing; retain those five names and add six operational reads. Reuse product services, private HTTPS/client reachability, per-call authorization and bounded/redacted results. No deploy, rollback, incident mutation or arbitrary query.
- Related: E-130; M-F01–M-F11; M-J01–M-J04.

### D-024 — Documentation revision and evidence honesty

- Status: Accepted, 2026-09-20
- Decision: This authorised tranche rewrites product/delivery/architecture planning, adds the repository README and updates workspace learning entry points.
- Consequences: Existing code/configuration/migrations/tests/infrastructure remain unchanged. Historical inception evidence is retained; new story/gate acceptance is Not run. README versions and commands reflect inspected files, not claimed execution.
- Related: E-000 history; C-01; README.

## Deferred implementation decisions

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

### D-110 — Actual server inventory and recovery destination

- Status: Deferred to S01–S05
- Decision needed: actual hardware/disks and preserved data, selected supported OS/runtime versions, private names/addresses/VPN configuration, mounts, off-host destination and resource budgets.
- Required evidence: measured inventory, verified preservation/sample restore and private reachability; no guessed destructive targets.
- Related: E-140, R-021, R-031.

### D-111 — Integration and telemetry operating budgets

- Status: Deferred to the relevant S33–S55 slices
- Decision needed: selected credential registration, provider polling/page budgets, receipt ordering contract, query/queue/time limits, per-source retention and source authorization mapping.
- Required evidence: versioned adapter contracts, actual server measurements and failure/recovery tests. Unbounded defaults cannot pass readiness or acceptance.
- Related: E-160/E-170/E-190.

### D-112 — MCP protocol and supported client

- Status: Deferred to S57, before implementation in S58
- Decision needed: then-supported stable specification/SDK/client versions, private HTTPS reachability and compatible OAuth discovery/resource-server flow.
- Required evidence: compatibility spike and threat model against the accepted eleven-tool contract; no draft or cloud-client reachability assumed.
- Related: E-130, D-023.

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
11. Work hierarchy, sprint closure and carryover history.
12. Immutable artifact attribution, receipt ordering and reconciliation.
13. Telemetry tenancy, redaction, query bounds and retention.
14. Incident evidence/causality semantics.
15. Private MCP transport, identity, client support and tool compatibility.

## Decision-change process

To change an accepted decision:

1. Identify the decision being superseded.
2. State the product or engineering evidence requiring change.
3. Compare viable alternatives and consequences.
4. Update affected scope, backlog, risks, contracts, and tests.
5. Record the new accepted decision and mark the previous decision superseded.

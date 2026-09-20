# TaskForge Delivery Backlog

Status: accepted planning baseline, revised 2026-09-20. Product work remains Proposed until independently evidenced.

## Purpose

This is the epic-level product backlog for the complete private server, board, deployment/investigation and MCP journey. The [sprint roadmap](sprint-roadmap.md) contains 64 fourteen-day sprints, 128 stable TF story cards and their tasks. The [delivery guide](../product/product-scope-and-delivery-guide.md) explains how to use them.

Legacy E-xxx-Sxx identifiers remain epic slice references; TF-xxx IDs identify the current executable story cards. Epic numbers identify ownership, not chronological order. An epic can span or contribute to several sprints.

## Backlog states

| State | Meaning |
|---|---|
| Proposed | Scoped outcome and forecast; readiness not yet established |
| Ready | Decisions, dependencies, environment, test approach and capacity are sufficient to begin |
| In progress | Active within the work-in-progress limit |
| Review | Integrated work awaiting acceptance evidence |
| Done | Applicable acceptance/gate passed and accepted |
| Blocked | Specific missing prerequisite recorded with next action |
| Deferred | Outside the committed horizon or awaiting a scope decision |

## Definition of ready

- Persona, outcome, release scope and observable acceptance are stated.
- Relevant UI/API/data/authorization/migration/failure rules are specified for this slice.
- Dependencies and environmental facts are verified; PC disk/access and recovery prerequisites are known before host changes.
- Test approach, demonstration and evidence location are clear.
- Risks/decisions are linked and the work fits the sprint budget or is split vertically.
- Forecast placement or a created document does not by itself make a story Ready.

## Definition of done

- Acceptance passes in the relevant integrated environment.
- Applicable UI, API, persistence, identity, retry/failure, observability and operator behaviour is complete.
- Required unit/PostgreSQL/contract/browser/operational/MCP tests and CI pass as applicable.
- Contracts, diagrams, runbooks and release notes reflect the accepted behaviour.
- Demo/evidence identify revision, environment, date, commands/results and remaining defects.
- The reviewer/product owner accepts the outcome; no completion is inferred from elapsed time.

## Epic sequence

| Epic | Forecast | Outcome | Dependencies |
|---|---|---|---|
| E-000 | Historical; revised now | Version-controlled product/delivery baseline | Product direction |
| E-140 | S01–S08 | PC-to-server foundation | Actual PC assessment and preservation |
| E-010, E-020 | S09; refinement per slice | Product flows and architecture decisions | E-000, inspected implementation/server evidence |
| E-030 | S10–S12 | Repeatable browser/API/database/UAT path | E-010/E-020 decisions, E-140 |
| E-040 | S13–S17 | Organisation, invitations and projects | E-030 |
| E-050 | S18, S21–S22; assignment completed S23 | Issues and board workflow | E-040; relevant E-150/E-060 slices |
| E-150 | S19–S20 | Hierarchy and 14-day sprint planning | Issue creation |
| E-060 | S23–S25 | Collaboration, filtering and audit | Core work/board |
| E-080 | S26–S27 | Cognito, roles and tenant security | Functional work contracts/current-actor boundary |
| E-070 | S28 | Integrated authenticated private board acceptance | E-040/E-050/E-150/E-060/E-080 |
| E-090 | S29–S31 | Quality and recovery evidence | Integrated board |
| E-100 | S32 | Release 1 | E-090 and Release 1 gate |
| E-110 | S33 and S57; ongoing | Stabilisation and operation | Preceding release |
| E-160 | S33–S42 | Delivery evidence per work item | Release 1; stabilisation checkpoint |
| E-170 | S43–S48 | Authorised logs, health and traces | Delivery context and registered sources |
| E-180 | S49–S52 | Alerts, incidents and corrective work | Delivery/telemetry evidence |
| E-190 | S53–S56 | Release 2 resilience and promotion | E-160/E-170/E-180 and R2 gate |
| E-130 | S57–S64 | MCP v1 | Release 2; stabilisation checkpoint |
| E-120 | Deferred | Separately approved AWS deployment | Post-MCP product/cost decision |

## E-000 — Delivery inception

### Outcome

The project has one version-controlled delivery baseline covering purpose, Release 1 scope, delivery phases, backlog, risks, and decisions.

### Initial slices

- E-000-S01: Save the master industry delivery plan.
- E-000-S02: Establish the product charter.
- E-000-S03: Establish Release 1 capabilities, journeys, rules, exclusions, and release gate.
- E-000-S04: Establish the ordered epic backlog and delivery definitions.
- E-000-S05: Establish the risk register and review cadence.
- E-000-S06: Establish the decision log and ADR candidates.

### Acceptance criteria

- All six inception artifacts are present inside the actual Git repository.
- Release 1 goals, capabilities, journeys, and exclusions agree across the artifacts.
- Every epic has an outcome and acceptance criteria.
- Risks identify controls, owners, triggers, and related delivery work.
- Confirmed decisions are separated from pending architecture decisions.
- No application code, configuration, migration, test, build, infrastructure, or existing learning document changes as part of this epic.

### Status

Historical documentation tranche completed. This is not evidence that the new server/product implementation stories are accepted.

## E-140 — PC-to-server foundation

### Outcome

The user's PC becomes a privately accessible Ubuntu Server LTS host with preserved data, repeatable administration, isolated containers, PostgreSQL, host monitoring and proven off-host recovery.

### Slices and acceptance

| Slice | Sprint | Acceptance |
|---|---|---|
| E-140-S01 | S01 | H-01 inventory, capacity, preservation and sample restoration |
| E-140-S02 | S02 | H-02 supported installation, patching, non-root SSH and recovery access |
| E-140-S03 | S03 | H-03 private LAN/VPN, firewall and naming/HTTPS prerequisites |
| E-140-S04 | S04 | H-04 persistent storage/time and safe reboot |
| E-140-S05 | S05 | H-05 Compose, environment isolation and network boundaries |
| E-140-S06 | S06 | H-06 database persistence, off-host backup and clean restore |
| E-140-S07 | S07 | H-07 private host/backup monitoring with finite retention |
| E-140-S08 | S08 | H-08 current-backend deployment/recovery rehearsal |

The [server plan](server-build-plan.md) owns the gates. Hardware and destinations are unknown until assessed; no PC data erasure, purchasing or public exposure is implied. State: Proposed; no host checks executed.


## E-010 — Product flows and UX

### Outcome

Users and implementers can follow each critical journey, including alternate and failure paths, without inventing interaction behaviour during development.

### Initial slices

- E-010-S01: Navigation, organisation selection, and application shell.
- E-010-S02: Organisation creation and initial owner journey.
- E-010-S03: In-app invitation discovery, acceptance, rejection, expiry, revocation, and mismatch flows.
- E-010-S04: Shareable invitation-link flow with authentication hand-off.
- E-010-S05: Project creation, selection, archive, and empty states.
- E-010-S06: Kanban board loading, empty, filtering, movement, keyboard alternative, and failure recovery.
- E-010-S07: Issue create/edit/detail, comments, labels, history, validation, and conflict flows.
- E-010-S08: Responsive and accessibility specifications for critical journeys.

### Acceptance criteria

- J-01 through J-13 have happy, alternate, denial, validation, and system-failure paths where applicable.
- Wireframes identify required data and actions without defining unapproved backend behaviour.
- Kanban status change does not require drag-and-drop as its only interaction.
- Loading, empty, permission, validation, stale-write, expired-invitation, and retry states are explicit.
- UX choices follow the release-specific scope. Hierarchy and sprint planning are included in Release 1; investigation flows are refined before their Release 2 slices.

## E-020 — Architecture and engineering specification

### Outcome

The implementation team has agreed module, API, data, identity, security, environment, and quality contracts.

### Initial slices

- E-020-S01: Modular-monolith boundaries and dependency rules.
- E-020-S02: Public API conventions, error contract, pagination, versioning, and concurrency contract.
- E-020-S03: Domain model, invariants, aggregate boundaries, and lifecycle rules.
- E-020-S04: PostgreSQL ER model, constraints, indexing assumptions, migration baseline, and transaction boundaries.
- E-020-S05: Development-identity interface and Cognito replacement design.
- E-020-S06: Role/permission matrix and tenant authorization rules.
- E-020-S07: Invitation token, storage, expiry, revocation, and logging threat model.
- E-020-S08: Local, UAT, release, configuration, secrets, backup, restore, and rollback designs.
- E-020-S09: Test strategy, browser matrix, accessibility target, performance target, capacity assumptions, RPO, and RTO.

### Acceptance criteria

- Every Release 1 capability maps to an owning module and public/application boundary.
- API and data designs cover validation, failure, authorization, tenancy, and concurrency.
- Identity can change from local development to Cognito without rewriting domain services.
- Threat controls exist for tenant access, invitation secrets, authentication, unsafe logs, and deployment exposure.
- Quality targets are measurable and have named evidence.
- Decisions affecting the next slice are recorded before it starts; later integration/telemetry/MCP decisions are refined in their allocated sprints, not assumed accepted by this document.

## E-030 — Delivery foundation and walking skeleton

### Outcome

A clean environment can build, test, package, deploy, inspect, and roll back one complete Angular-to-PostgreSQL vertical request.

### Initial slices

- E-030-S01: Repository structure and active documentation boundary.
- E-030-S02: Supported runtime/toolchain versions and reproducible wrappers/lockfiles.
- E-030-S03: Clean PostgreSQL migration baseline and PostgreSQL integration-test foundation.
- E-030-S04: Spring Boot module skeleton and local development identity adapter.
- E-030-S05: Angular/TypeScript application shell and design-system baseline.
- E-030-S06: Versioned API contract and frontend API-client strategy.
- E-030-S07: Local container topology, health checks, configuration, and secrets handling.
- E-030-S08: Continuous integration, quality checks, immutable images, and artifact metadata.
- E-030-S09: Home-lab UAT deployment, smoke test, and rollback of the walking skeleton.

### Acceptance criteria

- A clean clone follows committed instructions without informal setup knowledge.
- PostgreSQL, not H2, validates migrations and persistence integration.
- A failing compilation, test, contract, lint, or agreed security check blocks integration.
- Angular, API, and database participate in the walking skeleton.
- The deployed version is identifiable, health-checked, and rollback-capable.
- No secret is committed to Git or embedded in a container image.

## E-040 — Organisations, invitations, and projects

### Outcome

A development actor can create an organisation, add members through either invitation experience, and create organisation-visible projects.

### Initial slices

- E-040-S01: User profile and current-actor application boundary.
- E-040-S02: Organisation creation and owner membership.
- E-040-S03: Organisation selection and member listing.
- E-040-S04: Invitation creation, secure token representation, and lifecycle.
- E-040-S05: In-app pending invitation discovery and acceptance.
- E-040-S06: Shareable link acceptance using the same invitation.
- E-040-S07: Invitation rejection, revocation, expiry, repeated use, and mismatch handling.
- E-040-S08: Project creation, project key uniqueness, listing, and archive behaviour.
- E-040-S09: Integrated Angular organisation, invitation, and project journey.

### Acceptance criteria

- J-01 through J-04 pass using the development identity boundary.
- Exactly one owner outcome exists after organisation creation.
- Duplicate memberships and project keys are prevented by application rules and database constraints.
- Raw invitation secrets are neither stored nor logged.
- Invalid invitation states cannot create membership.
- Cross-organisation project access is rejected in application and integration tests.

## E-050 — Issues and Kanban workflow

### Outcome

Organisation members can create, inspect, assign, prioritise, and move issues through the fixed workflow on a Kanban board.

### Initial slices

- E-050-S01: Project issue sequence and stable human-readable issue key.
- E-050-S02: Issue creation with title, description, reporter, priority, and initial state.
- E-050-S03: Issue detail and validated edits.
- E-050-S04: Assignee selection restricted to organisation members.
- E-050-S05: Fixed workflow transition service and denial outcomes.
- E-050-S06: Optimistic concurrency and stale-write conflict contract.
- E-050-S07: Kanban data loading and status columns.
- E-050-S08: Status movement, keyboard alternative, optimistic UI, and failure recovery.
- E-050-S09: Issue and project archive behaviour.

### Acceptance criteria

- J-05, J-06, and J-09 pass through the Angular application.
- Concurrent issue creation does not duplicate issue keys.
- Disallowed transitions and cross-tenant assignees do not change persisted state.
- Stale writes cannot silently overwrite newer data.
- Failed board moves return the interface to persisted truth and provide a useful outcome.

## E-150 — Work hierarchy and sprint planning

### Outcome

Members organise epics, stories, child tasks and standalone bugs, then plan and close project-level 14-day sprints without losing work/history.

### Slices

- E-150-S01: Same-project epic/story/task relationships, standalone bugs, type-aware board/detail views and progress.
- E-150-S02: Planned/active/closed 14-day sprints; stories/bugs assigned directly, tasks inherit; explicit carryover.

### Acceptance criteria

R1-F12/R1-F13 and J-12/J-13 pass. Invalid parent/type/project/cycle and concurrent sprint-close cases preserve state. Work completion, deployment outcome and health remain separate. Forecast S19–S20, TF-037–TF-040; State: Proposed.


## E-060 — Collaboration, filters, and audit

### Outcome

The focused board supports the collaboration and traceability needed for Release 1.

### Initial slices

- E-060-S01: Project label creation and lifecycle.
- E-060-S02: Apply and remove labels on issues.
- E-060-S03: Issue comments and retry-safe frontend behaviour.
- E-060-S04: Status, priority, assignee, and label filters.
- E-060-S05: Deterministic sorting, bounded loading, pagination, and empty results.
- E-060-S06: Append-only audit model and reliable audit creation.
- E-060-S07: Issue history display.
- E-060-S08: Integrated focused-board regression journey.

### Acceptance criteria

- J-07 and J-08 pass through the Angular application.
- Filters return the intersection of requested criteria without bypassing tenant rules.
- Comment retries do not create accidental duplicates under the specified retry model.
- Successful audited actions produce accurate entries; rejected actions do not.
- Board and audit loading is bounded and deterministic.

## E-080 — Authentication and tenant security

### Outcome

Release environments use Cognito identity and enforce role and tenant permissions at backend boundaries.

### Initial slices

- E-080-S01: Cognito environment and OAuth/OIDC flow configuration.
- E-080-S02: Angular sign-in, sign-out, route protection, expiry, and denial behaviour.
- E-080-S03: Spring Security token validation and consistent authentication failures.
- E-080-S04: External identity-to-TaskForge profile mapping.
- E-080-S05: Owner/admin/member service-boundary authorization.
- E-080-S06: Cross-tenant and IDOR test matrix for every protected resource.
- E-080-S07: Invitation acceptance bound to authenticated identity.
- E-080-S08: CORS, headers, token/log review, and release-profile safety.

### Acceptance criteria

- R1-F01 and J-10 pass, including hierarchy, sprint and nested-resource authorization.
- No release operation accepts development identity.
- Every organisation-owned resource has positive role tests and negative tenant tests.
- Frontend control visibility and backend authorization agree, while backend checks remain authoritative.
- Security review has no unresolved critical/high defect.

## E-070 — Integrated private board acceptance

### Outcome

The complete board, hierarchy and sprint planning are demonstrated on private UAT after identity and tenant checks, before Release 1 hardening and promotion.

### Initial slices

- E-070-S01: Deterministic non-sensitive UAT/demo fixtures.
- E-070-S02: Full functional regression on home-lab UAT.
- E-070-S03: Deployment smoke test and running-version evidence.
- E-070-S04: Reference S08 restart/rollback evidence at S28; repeat against the full candidate in S31 with E-090.
- E-070-S05: Reference S06/S08 clean restore evidence at S28; restore full product fixtures in S31 with E-090.
- E-070-S06: Known limitations and integrated private board acceptance record.

### Acceptance criteria

- J-01–J-13 pass on authenticated UAT after E-080; development identity is not accepted as release evidence.
- The environment is reachable only through the approved private boundary.
- Server-level backup/rollback evidence is linked; full candidate recovery remains an explicit S31 prerequisite for Release 1.
- The milestone is labelled private UAT and not yet an accepted release; hierarchy/carryover and role/tenant evidence are recorded.

## E-090 — Quality and operational hardening

### Outcome

The release candidate is measurable, diagnosable, recoverable, and supported by repeatable quality evidence.

### Initial slices

- E-090-S01: Complete backend unit, PostgreSQL integration, and API suites.
- E-090-S02: Complete Angular unit/component and browser suites.
- E-090-S03: API contract compatibility and migration verification.
- E-090-S04: Accessibility and supported-browser verification.
- E-090-S05: Dependency, container, configuration, and application security checks.
- E-090-S06: Structured logs, correlation, health, metrics, and dashboards.
- E-090-S07: Query-plan review and measured home-lab load baseline.
- E-090-S08: Automated backup, restore verification, graceful shutdown, and recovery drills.
- E-090-S09: Deployment, rollback, restore, incident, and troubleshooting runbooks.

### Acceptance criteria

- Agreed functional and non-functional targets pass with stored evidence.
- Build artifacts have traceable version and security metadata.
- An operator can detect, diagnose, roll back, and restore using committed instructions.
- No critical/high security defect remains.

## E-100 — UAT and secure home-lab release

### Outcome

The exact UAT-approved artifacts are promoted as TaskForge Release 1 after a recorded go/no-go decision.

### Initial slices

- E-100-S01: Scope freeze, release-candidate build, and release checklist.
- E-100-S02: Owner, admin, member, invited-user, and unauthorised-user UAT.
- E-100-S03: Regression, exploratory, responsive, compatibility, accessibility, and security testing.
- E-100-S04: Release-candidate rollback and recovery rehearsal.
- E-100-S05: Known limitations, release notes, user guide, API documentation, and diagrams.
- E-100-S06: Go/no-go record and immutable artifact promotion.
- E-100-S07: Post-promotion smoke test and release verification.

### Acceptance criteria

- The release gate in the scope document passes.
- The promoted artifact identifiers match UAT evidence.
- Rollback and restore evidence applies to the release candidate.
- A go decision and known limitations are recorded.

## E-110 — Stabilisation and operation

### Outcome

Release 1 is observed, supported, and improved through evidence rather than speculative technology adoption.

### Initial slices

- E-110-S01: Stabilisation window and defect priority rules.
- E-110-S02: Operational monitoring and recurring backup verification.
- E-110-S03: Severity-based incident/defect triage.
- E-110-S04: Release retrospective and risk review.
- E-110-S05: Product evidence review and next-milestone recommendation.

### Acceptance criteria

- Operational ownership and maintenance tasks are explicit.
- Release defects are within the product owner's accepted threshold.
- Release defects and backup/recovery health are reviewed at S33 and S57 before the next planned feature slice proceeds. The accepted roadmap selects Release 2 then MCP; stabilisation may reforecast it.

## E-160 — Deployment evidence and work traceability

### Outcome

A member follows work through explicit PR links, builds, immutable artifact contents and observed deployments for each service/environment.

### Slices

- E-160-S01: Organisation-owned GitHub repository connection and private credential references (S33).
- E-160-S02: Service/environment catalogue (S34).
- E-160-S03: Explicit many-to-many PR/work links and provenance (S35).
- E-160-S04: Outbound GitHub/Actions synchronisation, build attempts and freshness (S36).
- E-160-S05: Immutable artifact identity and included-work manifest (S37).
- E-160-S06: Restricted producer credentials and durable idempotent deployment receipts (S38).
- E-160-S07: History and confirmed current version (S39).
- E-160-S08: Per-target work delivery timeline and incomplete attribution (S40).
- E-160-S09: Failed/partial deployment and external rollback representation (S41).
- E-160-S10: Duplicate/delayed/missing/out-of-order reconciliation (S42).

### Acceptance criteria

R2-F01–R2-F10 pass. Builds do not establish deployments; partial targets do not establish complete delivery; receipts survive TaskForge outages and converge under replay. No TaskForge UI/MCP deployment execution is introduced. State: Proposed.


## E-170 — Authorised operational investigation

### Outcome

A member inspects bounded application logs, measured health and trace links for an authorised deployment with explicit uncertainty/freshness.

### Slices

- E-170-S01: Trusted source/service/environment/version/time correlation (S43).
- E-170-S02: Private Loki collection, access boundaries, redaction and retention (S44).
- E-170-S03: Bounded backend log-query contract (S45).
- E-170-S04: In-app log viewer and authorised Grafana links (S46).
- E-170-S05: Prometheus health summaries and missing/no-traffic/stale states (S47).
- E-170-S06: Tempo correlation and trace links (S48).

### Acceptance criteria

R2-F11–R2-F14 pass. Query/source/tenant overrides are denied; shared/unpartitioned host/control-plane logs remain operator-only. No raw telemetry is stored in PostgreSQL, and no-traffic/stale data is not labelled healthy. State: Proposed.


## E-180 — Alerts, incidents and corrective work

### Outcome

Responders use verified alerts and evidence to record an incident, resolve it and link corrective work.

### Slices

- E-180-S01: Authenticated incoming alert mapping, lifecycle and deduplication (S49).
- E-180-S02: Audited incident create/acknowledge/resolve flow (S50).
- E-180-S03: Evidence/deployment links and corrective stories/tasks/bugs (S51).
- E-180-S04: Controlled complete failure-investigation/recovery journey (S52).

### Acceptance criteria

R2-F15/R2-F16 and R2-J05 pass; S52 integrates R2-J01–R2-J06. Association is not automatically cause; foreign or untrusted references are rejected/quarantined. Incoming alerts do not add outbound paging/email to scope. State: Proposed.


## E-190 — Release 2 resilience and promotion

### Outcome

The delivery/investigation product is bounded, secure, recoverable and accepted on exact artifacts.

### Slices

- E-190-S01: Connector outage, queue backpressure, expiry and reconciliation (S53).
- E-190-S02: Complete operational-data access and secret-redaction matrix (S54).
- E-190-S03: Capacity, retention and full restore/replay rehearsal (S55).
- E-190-S04: Release 2 UAT, go/no-go, artifact promotion and smoke (S56).

### Acceptance criteria

R2-F17 and the Release 2 gate pass, retaining Release 1 regression. Historical raw telemetry lost to retention remains explicitly unavailable after restore. State: Proposed.


## E-130 — Controlled MCP v1 integration

### Outcome

A supported authenticated private-network MCP client uses the released work/delivery/investigation application services through eleven tools: ten reads and one idempotent create.

### Slices

- E-130-S01: Contract, threat model, stable protocol/SDK and client decision (S57).
- E-130-S02: Authenticated Streamable HTTP, discovery, feature isolation and diagnostics (S58).
- E-130-S03: Four work-management read tools (S59).
- E-130-S07: Work-delivery and deployment reads (S60).
- E-130-S08: Bounded health and log reads (S61).
- E-130-S09: Incident list/detail reads (S62).
- E-130-S04: Idempotent issue-creation tool (S63).
- E-130-S05: Supported-client journey, safe logs and misuse tests (S58–S64).
- E-130-S06: MCP candidate, external-client acceptance, disable/rollback/recovery and go/no-go (S64).

### Acceptance criteria

All M-F01–M-F11 and M-J01–M-J04 in the [MCP contract](../product/mcp-v1-scope.md) pass. Tools reuse product services and per-call identity/tenancy/source authorization. Only issue creation mutates; no deploy/rollback/incident mutation or arbitrary query exists. The five historical work tools retain names; six operational read tools extend them.

State: Proposed after Release 2 and S57 stabilisation. This replaces the historical five-tool-only, S14–S18 allocation.


## E-120 — Separately approved AWS deployment

### Outcome

TaskForge demonstrates cloud portability without creating unapproved cost or rebuilding application artifacts.

### Initial slices

- E-120-S01: Workload, availability, security, region, and budget requirements.
- E-120-S02: AWS architecture, threat model, cost estimate, and approval.
- E-120-S03: Infrastructure as code, secrets, networking, and teardown.
- E-120-S04: Database migration/restore and artifact deployment.
- E-120-S05: Cloud observability, backup, rollback, and recovery.
- E-120-S06: Cloud UAT, security, performance, and go/no-go.
- E-120-S07: Teardown rehearsal and ongoing-cost decision.

### Acceptance criteria

- Provisioning and spending have explicit approval.
- Existing tested application images are deployed without rebuilding.
- Cloud acceptance, security, rollback, restore, and teardown evidence passes.
- No resource remains chargeable without an explicit continuing-operations decision.

### Status

Deferred beyond the through-MCP forecast, subject to explicit cloud architecture/cost/provisioning approval.

## Deferred product backlog

Attachments, watchers, due dates, outbound notifications/paging, custom workflows, private-project roles, advanced/full-text/semantic search, additional Git providers, Redis/Kafka/Kubernetes/microservices, AI generation, TaskForge-executed deploy/rollback, arbitrary query tools, full embedded dashboard/trace exploration, AWS hosting, public ingress and personal home-lab services are not commitments through MCP v1.

A new item requires a product outcome, acceptance, dependencies, risks and explicit forecast/scope trade-off. Keep all release contracts and the decision log consistent.

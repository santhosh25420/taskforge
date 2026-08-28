# TaskForge Delivery Backlog

## Purpose

This backlog converts the master plan into ordered delivery outcomes. It is intentionally epic-level during inception. Stories become implementation-ready only after the preceding product-flow and architecture decisions are complete.

No dates are committed here. Estimates and iteration forecasts are added after stories satisfy the definition of ready and delivery capacity is known.

## Backlog states

| State | Meaning |
|---|---|
| Proposed | Outcome is known but refinement is incomplete |
| Ready | Acceptance, dependencies, design, and test approach are sufficient to begin |
| In progress | Actively being delivered within the work-in-progress limit |
| Review | Implementation is integrated and awaiting review/evidence |
| Done | Acceptance criteria and the applicable phase gate pass |
| Deferred | Explicitly outside the current release or awaiting a decision |

## Definition of ready

A story may enter an iteration only when:

- The user or operational outcome is stated.
- Acceptance criteria are observable.
- Dependencies and affected product rules are known.
- Applicable UX, API, data, authorization, failure, and migration behaviour is specified.
- The test approach and required environments are identified.
- Material risks and unresolved decisions are linked.
- The story is small enough to complete within one iteration or has been split vertically.

## Definition of done

A story is done only when:

- Acceptance criteria pass in an integrated environment.
- Applicable frontend, backend, persistence, authorization, failure, and observability behaviour is complete.
- Unit, integration, contract, and browser tests required by the change pass.
- Relevant documentation, decisions, and release notes are updated.
- Continuous integration passes.
- The behaviour is demonstrated and accepted.

## Epic sequence

| Order | Epic | Outcome | Dependency | Release |
|---:|---|---|---|---|
| 0 | E-000 Delivery inception | Approved product and delivery baseline | None | Release 1 |
| 1 | E-010 Product flows and UX | Decision-complete user experience | E-000 | Release 1 |
| 2 | E-020 Architecture and engineering specification | Decision-complete technical design | E-010 | Release 1 |
| 3 | E-030 Delivery foundation and walking skeleton | Reproducible vertical delivery path | E-020 | Release 1 |
| 4 | E-040 Organisations, invitations, and projects | Usable multi-tenant project setup | E-030 | Release 1 |
| 5 | E-050 Issues and Kanban workflow | Core work-management experience | E-040 | Release 1 |
| 6 | E-060 Collaboration, filters, and audit | Complete focused-board functionality | E-050 | Release 1 |
| 7 | E-070 Private home-lab functional MVP | Accepted functionality and operational rehearsal | E-060 | Pre-release milestone |
| 8 | E-080 Authentication and tenant security | Secure identity and authorization | E-070 | Release 1 |
| 9 | E-090 Quality and operational hardening | Diagnosable and recoverable release candidate | E-080 | Release 1 |
| 10 | E-100 UAT and home-lab release | Approved secure Release 1 | E-090 | Release 1 |
| 11 | E-110 Stabilisation and operation | Stable operated product and evidence-based next backlog | E-100 | Release 1 support |
| 12 | E-120 AWS deployment | Separately approved cloud deployment | E-110 and explicit approval | Later |

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

Done. The six inception artifacts were created and cross-checked during the documentation-only tranche.

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

- J-01 through J-10 have happy, alternate, denial, validation, and system-failure paths where applicable.
- Wireframes identify required data and actions without defining unapproved backend behaviour.
- Kanban status change does not require drag-and-drop as its only interaction.
- Loading, empty, permission, validation, stale-write, expired-invitation, and retry states are explicit.
- UX choices do not add a capability listed as out of scope.

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
- Material decisions are recorded as accepted ADRs before foundation implementation begins.

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

## E-070 — Private home-lab functional MVP

### Outcome

All focused functionality and basic operational procedures are demonstrated in a private integrated environment.

### Initial slices

- E-070-S01: Deterministic non-sensitive UAT/demo fixtures.
- E-070-S02: Full functional regression on home-lab UAT.
- E-070-S03: Deployment smoke test and running-version evidence.
- E-070-S04: Container restart and application rollback rehearsal.
- E-070-S05: Database backup and clean restore rehearsal.
- E-070-S06: Known-limitations and functional-MVP acceptance record.

### Acceptance criteria

- Release 1 functional journeys pass with the development identity adapter.
- The environment is reachable only through the approved private boundary.
- Backup restoration and application rollback are demonstrated with recorded results.
- The milestone is labelled private, unauthenticated, and non-production.

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

- R1-F01 and J-10 pass.
- No release operation accepts development identity.
- Every organisation-owned resource has positive role tests and negative tenant tests.
- Frontend control visibility and backend authorization agree, while backend checks remain authoritative.
- Security review has no unresolved critical/high defect.

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
- The next milestone is justified by product or operational evidence.

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

Deferred until after Release 1 stabilisation and explicit authorization.

## Deferred product backlog

The following remain ideas, not commitments:

- Attachments.
- Watchers.
- Due dates.
- Outbound notifications.
- Configurable workflows.
- Private projects or project-level roles.
- Advanced/full-text/semantic search.
- Redis.
- Kafka or another broker.
- Kubernetes.
- Microservices.
- AI or MCP features.

An item moves out of this list only through the scope-change rule in the Release 1 scope document.

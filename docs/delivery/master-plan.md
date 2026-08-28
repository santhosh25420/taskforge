# TaskForge Industry Delivery Master Plan

## Document control

| Field | Value |
|---|---|
| Status | Approved delivery baseline |
| Product | TaskForge |
| Delivery model | Portfolio product run as a professional software engagement |
| Primary audience | Small software teams |
| Iteration cadence | Two weeks |
| First deployment target | Private Ubuntu/Debian home lab |
| Public-cloud target | Deferred and separately approved AWS phase |

## Purpose

This document is the governing delivery plan for TaskForge. It defines how the product moves from discovery through design, implementation, release, operation, and later cloud deployment.

The project is managed by outcomes and exit gates. A phase is not complete because time elapsed or code was written; it is complete only when its evidence and acceptance criteria pass.

The learning roadmaps already present in the wider workspace remain reference material. They do not control product scope or delivery order.

## Product outcome

TaskForge Release 1 will be a Kanban-first work-management product for small software teams. It will let a team:

1. Create an organisation.
2. Invite members through an in-app invitation or shareable invitation link.
3. Create projects visible to the organisation.
4. Create, assign, prioritise, label, discuss, filter, and track issues.
5. Move issues through a fixed, controlled workflow.
6. Review an audit history of important issue changes.

The detailed product contract is maintained in [`../product/release-1-scope.md`](../product/release-1-scope.md).

## Delivery principles

- Start with a modular monolith and PostgreSQL.
- Deliver vertical slices that cross user interface, API, application logic, persistence, tests, and deployment.
- Keep the first release intentionally narrow and useful.
- Treat testing, security, observability, rollback, recovery, and documentation as feature work.
- Build an artifact once and promote the same artifact between environments.
- Keep the unauthenticated functional MVP private; authentication is a hard release gate.
- Add infrastructure such as Redis, Kafka, Kubernetes, microservices, AI, or advanced search only after a measured requirement exists.
- Record material choices in the decision log and, when implementation consequences are substantial, in an Architecture Decision Record.

## Roles and responsibilities

One contributor may perform several roles, but each responsibility remains explicit.

| Role | Accountabilities |
|---|---|
| Product owner | Product outcome, priorities, scope, acceptance, go/no-go decision |
| Delivery lead | Iteration planning, dependencies, risks, progress reporting, retrospectives |
| UX/UI | User journeys, interaction design, accessibility, responsive behaviour |
| Technical lead | Architecture, public contracts, data ownership, technical decisions |
| Backend engineering | Domain rules, API, persistence, integration tests |
| Frontend engineering | Angular application, usability, API integration, browser tests |
| QA | Test strategy, exploratory testing, regression, UAT evidence |
| Security | Threat model, identity, authorization, dependency and configuration review |
| Operations | Build artifacts, home-lab deployment, observability, backup, restore, rollback |

When no independent reviewer is available, work still passes a separate self-review checkpoint using the acceptance criteria, automated evidence, and recorded risks.

## Governance and delivery rhythm

### Two-week iteration

Each iteration contains:

1. Planning: select ready stories, confirm capacity, dependencies, risks, and the iteration goal.
2. Delivery: limit work in progress and complete vertical slices before starting more work.
3. Mid-iteration review: integrate early and surface scope or dependency problems.
4. Demonstration: show working behaviour against acceptance criteria.
5. Retrospective: record what to continue, stop, change, and follow up.
6. Backlog refinement: make the next set of stories ready without silently changing Release 1 scope.

### Change control

- Product scope changes update the product scope document and backlog.
- Material technical choices update the decision log and, when necessary, add an ADR.
- New Release 1 scope requires a trade-off: remove comparable scope, change the release forecast, or explicitly accept more risk.
- A phase exit gate cannot be waived silently. Any exception records its owner, reason, impact, expiry, and remediation.

### Feature definition of done

A feature is done only when:

- Its user outcome and acceptance criteria pass.
- API, domain, persistence, validation, authorization, and failure behaviour are addressed where applicable.
- Required unit, integration, and browser tests pass.
- Logs and operational behaviour are adequate to diagnose failure.
- Relevant documentation and the public API contract are updated.
- Continuous integration passes.
- The feature is demonstrated in an integrated environment.

## Environment progression

| Environment | Purpose | Boundary |
|---|---|---|
| Local development | Fast implementation and automated tests | Developer workstation; disposable data |
| Home-lab UAT | Integrated acceptance and operational rehearsal | Ubuntu/Debian server; logically separate configuration and data |
| Home-lab release | Secure Release 1 operation | Same physical failure domain as UAT; never described as highly available |
| AWS | Later cloud portability and deployment milestone | Requires separate architecture, cost, security, and provisioning approval |

The private functional MVP may run without production authentication only on a trusted LAN or equivalent private network. It must not be exposed publicly.

## Phase 0 — Inception and delivery baseline

### Objective

Make product intent, scope, ownership, delivery rules, risks, and decisions explicit before expanding implementation.

### Outputs

- Product charter.
- Release 1 scope and acceptance baseline.
- Prioritised epic backlog.
- Risk register.
- Architecture decision log.
- This master delivery plan.

### Exit gate

- Release 1 goals and non-goals are unambiguous.
- Each epic has an outcome and acceptance criteria.
- Current risks and decisions are traceable.
- The documentation is version-controlled.

## Phase 1 — Product flows and UX specification

### Objective

Define the user experience before committing to frontend structure or API details.

### Outputs

- User flows for onboarding, organisations, invitations, projects, the Kanban board, issue details, comments, filters, and failures.
- Low-fidelity wireframes and a reviewed interaction model.
- Navigation and information architecture.
- Responsive, loading, empty, error, conflict, and permission states.
- Accessibility acceptance criteria for critical flows.

### Exit gate

- Every Release 1 journey has a reviewed flow.
- The Kanban interaction, invitation experience, and issue-detail experience are testable from the specification.
- No unresolved UX choice blocks API or data design.

## Phase 2 — Architecture and engineering specification

### Objective

Make implementation boundaries and quality expectations decision-complete.

### Outputs

- High-level design and module boundaries.
- REST API conventions and versioning policy.
- Domain model and PostgreSQL ER model.
- Identity abstraction and later Cognito integration boundary.
- Role and permission matrix.
- Threat model and tenant-isolation rules.
- Local, UAT, release, backup, restore, and rollback designs.
- Non-functional requirements and test strategy.
- ADRs for decisions with substantial or difficult-to-reverse consequences.

### Exit gate

- API, domain, data, identity, tenancy, environment, and failure boundaries are agreed.
- Security and operational risks have planned controls.
- Foundation stories meet the definition of ready.

## Phase 3 — Repository and delivery foundation

### Objective

Create a repeatable development, test, packaging, and home-lab delivery path before building the complete product.

### Planned outcomes

- One coherent version-controlled repository for backend, frontend, infrastructure, and active documentation.
- Aligned supported Java, Spring Boot, Angular, Node, Maven, and PostgreSQL versions.
- Reproducible local environment.
- PostgreSQL-backed migration and integration testing.
- Angular application skeleton and Spring Boot modular-monolith skeleton.
- Container images, health checks, structured configuration, and secrets boundaries.
- Mandatory continuous integration and immutable artifact versioning.
- One walking-skeleton request through Angular, API, database, and home-lab deployment.

### Exit gate

- A clean machine can build, test, and run the system from committed instructions.
- Continuous integration blocks a failing change.
- The same immutable artifacts can be deployed and rolled back.
- The walking skeleton passes local and home-lab smoke tests.

## Phase 4 — Organisation, invitation, and project slice

### Objective

Deliver the first meaningful multi-tenant business flow.

### Planned outcomes

- User profiles and current-actor boundary.
- Organisation creation and membership roles.
- In-app pending invitations matched by email.
- Expiring, single-use shareable invitation links.
- Invitation acceptance and rejection paths.
- Organisation-wide project creation and access.
- Angular onboarding, organisation selection, invitations, and project screens.

### Exit gate

- The complete organisation-to-project journey passes through the browser.
- Duplicate, expired, reused, revoked, and mismatched-email invitations behave as specified.
- Membership and project rules are enforced and tested.

## Phase 5 — Issue workflow and Kanban slice

### Objective

Deliver the core day-to-day work-management experience.

### Planned outcomes

- Human-readable project issue keys.
- Issue creation and detail editing.
- Fixed workflow and validated transitions.
- Priority, reporter, assignee, and optimistic concurrency.
- Kanban columns, issue cards, controlled status movement, and failure recovery.
- Archival rather than destructive deletion for Release 1 records.

### Exit gate

- Users can manage an issue from creation through completion using the browser.
- Invalid transitions and concurrent changes produce consistent, tested outcomes.
- The interface recovers correctly when a board action fails.

## Phase 6 — Collaboration, filtering, and traceability

### Objective

Complete the focused Release 1 issue-management capability.

### Planned outcomes

- Project labels.
- Issue comments.
- Status, priority, assignee, and label filters.
- Deterministic sorting and pagination/loading behaviour.
- Append-only audit history for important issue changes.
- Issue history in the Angular interface.

### Exit gate

- All focused-board journeys in the Release 1 scope pass.
- Audit records identify the actor, time, action, and meaningful change.
- Filters and board loading remain deterministic and bounded.

## Phase 7 — Private home-lab functional MVP

### Objective

Demonstrate complete product functionality and the operational path before adding production identity.

### Planned outcomes

- Integrated functional MVP on the home-lab UAT stack.
- Seeded, non-sensitive demonstration data.
- Smoke, rollback, container-restart, backup, and restore rehearsals.
- Known limitations and defects recorded.

### Exit gate

- Release 1 functional journeys pass in the home lab.
- The deployment remains private and is explicitly labelled non-production.
- Backup restoration and application rollback are demonstrated.

## Phase 8 — Authentication, authorization, and tenant isolation

### Objective

Replace development identity with real identity and make tenant boundaries suitable for release.

### Planned outcomes

- Amazon Cognito sign-in and token lifecycle.
- Angular route protection and secure token use.
- Spring Security resource-server validation.
- Cognito identity-to-TaskForge profile mapping.
- Service-boundary enforcement of `OWNER`, `ADMIN`, and `MEMBER` permissions.
- Cross-tenant, insecure-direct-object-reference, and privilege-escalation tests.
- CORS, security headers, logout, expiry, and authentication-failure behaviour.

### Exit gate

- No non-local product operation succeeds without valid identity.
- Cross-tenant and role-boundary tests pass for every organisation-owned resource.
- The development identity mechanism is impossible to enable accidentally in release configuration.

## Phase 9 — Quality and operational hardening

### Objective

Make the system testable, diagnosable, recoverable, and safe to operate.

### Planned outcomes

- Completed backend, frontend, integration, contract, and browser test suites.
- Accessibility, security, dependency, and container reviews.
- Structured logs, correlation identifiers, health probes, metrics, and operational dashboards.
- Measured query plans and home-lab performance baseline.
- Automated backups outside the live database volume.
- Tested restore, rollback, restart, and graceful-shutdown behaviour.
- Deployment, rollback, recovery, incident, and troubleshooting runbooks.

### Exit gate

- Agreed functional and non-functional acceptance tests pass.
- A failure can be detected, investigated, rolled back, and recovered using committed procedures.
- No unresolved critical or high-severity security defect remains.

## Phase 10 — UAT and secure home-lab Release 1

### Objective

Approve and promote the exact tested product artifacts.

### Planned outcomes

- Scope freeze and release-candidate build.
- Persona-based UAT for owner, admin, member, invited user, and unauthorised user.
- Regression, exploratory, compatibility, responsive, accessibility, security, backup, and rollback evidence.
- Known limitations and formal go/no-go record.
- Semantic release tag, release notes, user guide, API documentation, diagrams, and runbooks.
- Promotion of the tested artifact versions into the home-lab release stack.

### Exit gate

- All Release 1 acceptance criteria pass.
- Role and tenant boundaries hold in API and UI tests.
- Smoke, rollback, and recovery checks pass against the release artifacts.
- The product owner records a go decision.

## Phase 11 — Stabilisation and operation

### Objective

Protect product quality immediately after release and use evidence to choose subsequent work.

### Planned outcomes

- Error, latency, resource, disk, health, backup, and authentication monitoring.
- Severity-based defect and incident triage.
- Release retrospective and updated risk register.
- Architecture, operational documentation, and backlog updates based on observed behaviour.
- A defined stabilisation window in which release defects take priority over enhancements.

### Exit gate

- Release defects are within the accepted threshold.
- Operational ownership and recurring maintenance are clear.
- The next product milestone is selected from evidence rather than technology preference.

## Phase 12 — Separately approved AWS deployment

### Objective

Prove cloud portability only after product functionality and home-lab operation are established.

### Planned outcomes

- Updated workload, availability, security, and budget assumptions.
- AWS architecture and cost estimate.
- Infrastructure as code and teardown procedure.
- Network, identity, secrets, TLS, monitoring, database, backup, restore, and rollback design.
- Deployment of the already-tested application artifacts.
- Repeated UAT, security, performance, recovery, and go/no-go gates.

### Exit gate

- AWS provisioning has explicit approval and cost controls.
- Cloud acceptance and recovery evidence passes.
- Teardown is tested, and no ongoing spending is assumed without approval.

## Release 1 exclusions

The following are outside Release 1 unless scope is formally changed:

- Attachments.
- Watchers.
- Due dates.
- Outbound notifications.
- Advanced or semantic search.
- Redis.
- Kafka or another message broker.
- Kubernetes.
- Microservices.
- AI or MCP features.
- AWS hosting.

## Traceability

| Concern | Source of truth |
|---|---|
| Product purpose and success | [`../product/product-charter.md`](../product/product-charter.md) |
| Release capabilities and acceptance | [`../product/release-1-scope.md`](../product/release-1-scope.md) |
| Delivery order and story outcomes | [`backlog.md`](backlog.md) |
| Delivery and product risks | [`risk-register.md`](risk-register.md) |
| Confirmed and pending decisions | [`../architecture/decision-log.md`](../architecture/decision-log.md) |


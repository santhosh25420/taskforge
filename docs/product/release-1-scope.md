# TaskForge Release 1 Scope

## Purpose

This document is the product contract for TaskForge Release 1. It defines included capabilities, business rules, user journeys, acceptance criteria, and explicit exclusions.

Detailed API schemas, database structures, and screen designs are intentionally deferred to the product-flow and architecture tranches. Those specifications must implement this contract without silently expanding it.

## Release definition

TaskForge has two distinct milestones:

| Milestone | Meaning |
|---|---|
| Private functional MVP | Complete focused-board functionality on a trusted local/home-lab network using a development identity mechanism |
| Release 1 | The functional MVP plus Cognito authentication, tenant authorization, quality hardening, UAT, recovery evidence, and secure home-lab promotion |

The private functional MVP is not a production release and must not be exposed publicly.

## Personas and permissions

| Persona | Release 1 responsibilities |
|---|---|
| Owner | Manage the organisation, invitations, memberships, projects, issues, and ownership-sensitive operations |
| Admin | Manage invitations, non-owner memberships, projects, and issues |
| Member | View organisation projects and create, view, assign, label, comment on, and transition issues |
| Invited user | Discover or open an invitation and accept it using the invited identity |
| Unauthorised user | Receive a safe, consistent denial without learning protected resource details |

The detailed permission matrix will be finalized during architecture design. It must not grant a lower role administrative powers merely because a UI control is hidden.

## Included capabilities

### R1-F01 — Identity and user profile

- Cognito provides Release 1 authentication.
- TaskForge maintains the application profile associated with the external identity.
- The private functional MVP uses a development identity adapter that cannot be enabled accidentally in release configuration.
- Release 1 supports sign-in, sign-out, token expiry, and authentication-failure handling required by the selected Cognito flow.

Acceptance criteria:

- A valid signed-in user is mapped to the correct TaskForge profile.
- Missing, invalid, or expired identity cannot access protected product operations.
- Signing out removes access to protected frontend routes and API operations.
- Development identity is unavailable in the release environment.

### R1-F02 — Organisation lifecycle

- A user can create an organisation and becomes its `OWNER`.
- Organisation membership roles are `OWNER`, `ADMIN`, and `MEMBER`.
- A user can belong to more than one organisation.
- Organisation context is visible and switchable in the Angular application.
- All organisation members can access all projects belonging to that organisation in Release 1.

Acceptance criteria:

- Organisation creation produces exactly one owner membership.
- Duplicate membership cannot be created for the same user and organisation.
- A user outside an organisation cannot access its membership, project, or issue data.
- Switching organisation changes the visible projects and prevents stale data from the previous organisation being shown.

### R1-F03 — Invitations

- An owner or admin can invite a user by email.
- A pending invitation is discoverable in-app when the signed-in user's normalized email matches.
- The same invitation provides an expiring, single-use shareable link.
- An invitation can be accepted, rejected, revoked, expire, or become invalid after successful use.
- Release 1 does not send outbound invitation email.

Acceptance criteria:

- The invited email can accept through the in-app list or the shareable link.
- A different identity cannot accept the invitation.
- Expired, revoked, rejected, and previously used invitations cannot create membership.
- Repeated acceptance is idempotent or returns a stable domain error without duplicate membership.
- Raw invitation secrets are not stored or written to logs.

Token lifetime and revocation details will be fixed in the security specification.

### R1-F04 — Projects

- An owner or admin can create a project within an organisation.
- A project has a human-readable name and stable key suitable for issue identifiers.
- All organisation members can see and use all organisation projects.
- Projects can be archived; Release 1 does not require destructive deletion.

Acceptance criteria:

- A project key is unique within its organisation.
- A user from another organisation cannot discover or access the project.
- An archived project is excluded from normal active views and cannot receive new issues.
- Existing project issue history remains available according to the final archive policy.

### R1-F05 — Issues and human-readable keys

- A member can create an issue in an active project.
- An issue has a stable human-readable key derived from the project key and project issue sequence.
- An issue includes title, description, reporter, assignee, priority, status, and timestamps.
- Priorities are `LOW`, `MEDIUM`, `HIGH`, and `URGENT`.
- Issues can be archived; destructive deletion is not required.
- Concurrent updates are detected rather than silently overwriting newer data.

Acceptance criteria:

- Issue keys are unique and monotonically allocated within a project under concurrent creation.
- Required fields and domain limits are validated consistently by API and UI.
- An assignee must be a member of the issue's organisation.
- A stale update cannot overwrite a newer update and returns a clear conflict outcome.
- Archived issues are excluded from active board queries.

### R1-F06 — Fixed workflow

Statuses are:

- `BACKLOG`
- `TODO`
- `IN_PROGRESS`
- `REVIEW`
- `DONE`

The default allowed transitions are:

| From | To |
|---|---|
| `BACKLOG` | `TODO` |
| `TODO` | `BACKLOG`, `IN_PROGRESS` |
| `IN_PROGRESS` | `TODO`, `REVIEW` |
| `REVIEW` | `IN_PROGRESS`, `DONE` |
| `DONE` | `REVIEW` |

Acceptance criteria:

- Issue creation starts in the status selected by the final product-flow specification, limited to an allowed initial state.
- Allowed transitions succeed through API and Kanban UI.
- Disallowed transitions fail without changing issue or audit state.
- A transition conflict caused by a stale version is visible and recoverable in the UI.

### R1-F07 — Kanban board

- The primary project workspace is a Kanban board with one column per fixed status.
- Users can create and open issues from the board.
- Users can move an issue only through a valid workflow transition.
- The board supports loading, empty, validation, permission, conflict, and retry states.
- Board data loading is bounded and does not rely on an unlimited result set.

Acceptance criteria:

- Each active issue appears in exactly one status column.
- A successful move updates the issue and board consistently.
- A failed move is reverted or refreshed and explains the outcome to the user.
- Keyboard and non-drag interaction is available for status changes.
- Reloading the board reproduces the persisted state.

### R1-F08 — Assignees, priorities, labels, and filters

- Users can set or clear an eligible assignee.
- Users can set issue priority.
- Projects support labels that can be applied to issues.
- Users can filter by status, priority, assignee, and label.
- Results use deterministic ordering.

Acceptance criteria:

- Filters produce the intersection of selected criteria.
- Invalid or cross-tenant identifiers do not bypass tenancy rules.
- Removing an assignee or label is reflected in board and issue detail views.
- Empty filtered results are distinguished from loading or failure.

### R1-F09 — Comments

- Organisation members can add comments to active issues.
- Comments display their author and creation time.
- Comment editing and deletion are not required unless approved during product-flow design.

Acceptance criteria:

- A comment is visible after successful creation and remains attached to the correct issue.
- Empty or invalid comments are rejected consistently.
- A user outside the organisation cannot read or create comments.
- Failure does not create a duplicate visible comment after retry.

### R1-F10 — Audit history

- Important issue changes produce append-only audit records.
- At minimum, audit covers creation and changes to status, title, priority, assignee, labels, and archive state.
- Audit entries identify actor, time, action, and the meaningful before/after change.

Acceptance criteria:

- Successful audited operations create the expected audit entry in the same reliable business operation.
- Failed or rejected operations do not produce misleading audit entries.
- Audit history is ordered deterministically and visible from issue details.
- Product users cannot edit audit records.

### R1-F11 — Diagnostics and operations

- Local, home-lab UAT, and home-lab release setup is documented.
- The application exposes safe health information for deployment checks.
- Logs support correlation and avoid secrets.
- Application artifacts are versioned and can be rolled back.
- PostgreSQL is backed up and restoration is tested.

Acceptance criteria:

- A documented smoke test confirms the deployed release.
- An operator can identify the running version.
- The previous approved application artifact can be restored.
- A backup can be restored into a clean database and verified.
- Application and database failure procedures are executable from committed runbooks.

## Critical user journeys

| ID | Journey | Successful outcome |
|---|---|---|
| J-01 | Create organisation | Signed-in user becomes owner of a new organisation |
| J-02 | Invite through in-app discovery | Matching user sees and accepts a pending invitation |
| J-03 | Invite through link | Matching user opens and accepts a valid shared invitation |
| J-04 | Create project | Owner/admin creates a project visible to organisation members |
| J-05 | Create issue | Member creates an issue with a stable project issue key |
| J-06 | Manage board | Member views and validly transitions issues on the Kanban board |
| J-07 | Collaborate on issue | Member assigns, prioritises, labels, comments, and reviews history |
| J-08 | Filter work | Member narrows board data using supported filters |
| J-09 | Handle concurrent edit | Stale edit is rejected and the user can refresh/reapply intentionally |
| J-10 | Prevent tenant access | User cannot access another organisation's resources by guessed identifier |
| J-11 | Operate release | Operator deploys, smoke-tests, rolls back, backs up, and restores |

Detailed happy paths, alternate paths, and wireframes will be produced in the product-flow tranche.

## Cross-cutting business rules

| ID | Rule |
|---|---|
| BR-01 | Organisation membership is required for every organisation-owned read or mutation. |
| BR-02 | UI visibility is not an authorization control; enforcement occurs in backend application boundaries. |
| BR-03 | All organisation members can access all organisation projects in Release 1. |
| BR-04 | Owner-sensitive operations cannot leave an organisation without exactly one valid ownership outcome. |
| BR-05 | Invitation acceptance requires the invited identity and cannot create duplicate membership. |
| BR-06 | Issue assignees and project labels must belong to the issue's tenant boundary. |
| BR-07 | Issue workflow changes must use an allowed transition. |
| BR-08 | Concurrent mutations must not silently lose a committed update. |
| BR-09 | Archive operations preserve history and remove records from normal active workflows. |
| BR-10 | Times are stored and exchanged consistently; display localisation is a client concern. |

## Release 1 quality attributes

The architecture tranche will assign measurable targets and evidence to each attribute.

- Security: authenticated release, least privilege, tenant isolation, safe invitation handling, and no critical/high unresolved security defect.
- Reliability: transactional business rules, concurrency protection, deterministic retries, health checks, graceful failure, and rollback.
- Recoverability: backup outside the live database volume and tested restoration.
- Usability: coherent Kanban workflow with clear loading, empty, validation, conflict, permission, and failure states.
- Accessibility: core flows usable without drag-only or pointer-only interactions.
- Performance: bounded board loading and measured response targets appropriate to the home-lab capacity.
- Maintainability: modular boundaries, versioned contracts and migrations, automated tests, and decision records.
- Operability: version identification, structured diagnostics, smoke tests, runbooks, and monitored resource limits.

## Explicitly out of scope

| ID | Capability |
|---|---|
| OOS-01 | Attachments |
| OOS-02 | Watchers |
| OOS-03 | Due dates |
| OOS-04 | Outbound email, push, SMS, or webhook notifications |
| OOS-05 | Configurable project workflows |
| OOS-06 | Private or project-specific membership |
| OOS-07 | Advanced, full-text, or semantic search |
| OOS-08 | Redis caching or rate limiting |
| OOS-09 | Kafka or another message broker |
| OOS-10 | Kubernetes |
| OOS-11 | Microservices |
| OOS-12 | AI or MCP features |
| OOS-13 | AWS hosting |
| OOS-14 | High-availability claims for the home lab |

## Release acceptance gate

Release 1 is approved only when:

- J-01 through J-11 pass in their applicable automated or UAT suites.
- Owner, admin, member, invited-user, unauthorised-user, and cross-tenant scenarios pass.
- The development identity mechanism is unavailable in release configuration.
- Critical browser, API, migration, security, rollback, and recovery evidence passes.
- No unresolved critical or high-severity security defect remains.
- Known limitations are documented.
- The exact UAT-approved artifacts are promoted without rebuilding.
- The product owner records a go decision.

## Scope change rule

Any addition to Release 1 must identify:

1. The user outcome it enables.
2. Acceptance criteria.
3. Dependencies and risks.
4. What existing scope is removed, how the forecast changes, or what additional risk is explicitly accepted.
5. Required updates to the backlog, risk register, and decision log.


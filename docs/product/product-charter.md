# TaskForge Product Charter

## Document status

This charter is the approved product baseline for discovery and Release 1 planning. It describes why TaskForge exists and what outcome it must produce. Detailed feature behaviour belongs in [`release-1-scope.md`](release-1-scope.md).

## Product statement

TaskForge is a portfolio-grade work-management product for small software teams that need a clear, shared view of project issues without the administration and breadth of a large enterprise platform.

It provides a Kanban-first workflow in which a team can organise projects, invite members, assign work, collaborate on issues, and understand how important issue data changed over time.

## Problem

Small software teams need a dependable way to answer:

- What work exists?
- What state is each item in?
- Who is responsible for it?
- What is most important?
- What discussion and decisions are attached to it?
- What changed, when, and by whom?

The first release is not intended to compete feature-for-feature with mature work-management suites. It is intended to solve this focused workflow coherently, securely, and operably.

## Primary audience

### Primary users

- Small software-team owners and leads.
- Developers and other team members managing project issues.

### Release 1 personas

| Persona | Need |
|---|---|
| Organisation owner | Establish the workspace, control membership, and retain final administrative authority |
| Organisation admin | Manage invitations, members, projects, and issue activity without transferring ownership |
| Organisation member | See organisation projects and create, update, assign, label, comment on, and move issues |
| Invited user | Discover or open an invitation, join the intended organisation, and understand invitation failures |
| Operator | Deploy, inspect, back up, restore, roll back, and troubleshoot the application |

## Product goals

| ID | Goal |
|---|---|
| G-01 | Deliver a useful Kanban workflow for small software teams. |
| G-02 | Enforce clear organisation tenancy and role boundaries before secure Release 1. |
| G-03 | Make the full system reproducible locally and operable on an Ubuntu/Debian home lab. |
| G-04 | Demonstrate professional delivery evidence: requirements, decisions, tests, security, observability, rollback, and recovery. |
| G-05 | Keep the architecture simple enough to evolve from measured needs. |

## Non-goals

| ID | Non-goal |
|---|---|
| NG-01 | Reproduce Jira or another mature product feature-for-feature. |
| NG-02 | Support microservices, Kubernetes, Redis, or Kafka in Release 1. |
| NG-03 | Deliver advanced search, semantic search, AI, or MCP capabilities in Release 1. |
| NG-04 | Deliver attachments, watchers, due dates, or outbound notifications in Release 1. |
| NG-05 | Claim high availability from one home-lab server. |
| NG-06 | Make AWS hosting part of the initial release commitment. |
| NG-07 | Expose an unauthenticated functional MVP to the public internet. |

## Stakeholders

| Stakeholder | Interest | Decision authority |
|---|---|---|
| Product owner | Product usefulness, scope, priorities, acceptance | Scope, priority, release go/no-go |
| Delivery/technical lead | Feasible sequencing, architecture, risks, quality | Technical recommendations and phase readiness |
| End-user representatives | Usability and workflow fit | Feedback and UAT evidence |
| Operator | Deployment, support, recovery, maintenance | Operational readiness recommendation |
| Portfolio reviewer | Clarity of engineering decisions and evidence | No delivery authority; represents an evaluation audience |

Stakeholder feedback must be recorded honestly. Portfolio work must not fabricate interviews, adoption, or satisfaction data.

## Constraints

| ID | Constraint |
|---|---|
| C-01 | Existing application code remains unchanged during the documentation-only inception tranche. |
| C-02 | The initial implementation remains a Spring Boot modular monolith backed by PostgreSQL. |
| C-03 | The web client uses Angular and TypeScript with a Kanban-first experience. |
| C-04 | The first operational target is an Ubuntu/Debian home-lab server. |
| C-05 | Authentication is deferred for the private functional MVP but is mandatory before secure Release 1. |
| C-06 | AWS work requires separate approval and is not allowed to create unapproved spending. |
| C-07 | Work is planned and reviewed in two-week iterations. |

## Assumptions

| ID | Assumption | Validation point |
|---|---|---|
| A-01 | All organisation members may access all organisation projects in Release 1. | Product-flow review |
| A-02 | A fixed workflow is sufficient for the first target users. | UX review and UAT |
| A-03 | Both in-app and manually shared invitations are valuable without outbound email. | Invitation flow review |
| A-04 | Existing development data does not require preservation. | Architecture/data-design tranche |
| A-05 | The home lab can run the required application, database, proxy, and operational tooling. | Foundation capacity check |
| A-06 | One person may hold multiple delivery roles, with explicit self-review gates where independent review is unavailable. | Each iteration review |

If an assumption is disproved, update this charter, the risk register, affected decisions, and backlog before implementation continues.

## Success measures

### Product acceptance measures

| ID | Measure |
|---|---|
| SM-01 | An owner can create an organisation, invite a member by either supported mechanism, and create a project. |
| SM-02 | A member can create and manage an issue through the complete fixed workflow from the Angular interface. |
| SM-03 | Users can assign, prioritise, label, comment on, filter, and review the history of issues. |
| SM-04 | Invalid transitions, stale updates, invalid invitations, and unauthorised access fail predictably. |
| SM-05 | Owner, admin, member, and cross-organisation access rules pass automated and UAT scenarios. |

### Engineering and operational measures

| ID | Measure |
|---|---|
| SM-06 | A clean environment can build, test, and run TaskForge from committed instructions. |
| SM-07 | Continuous integration blocks compilation, test, contract, lint, or agreed security-gate failures. |
| SM-08 | The same immutable release artifacts pass UAT and are promoted to the home-lab release environment. |
| SM-09 | An operator can detect a failure, inspect relevant evidence, roll back the application, and restore the database from documented procedures. |
| SM-10 | Release 1 has no unresolved critical or high-severity security defect. |

Numerical performance, recovery, accessibility, browser-support, and capacity targets will be made decision-complete during the architecture and engineering specification phase rather than guessed during inception.

## Product boundaries

### Release 1 includes

- Invite-based organisations and roles.
- Organisation-wide projects.
- Fixed issue workflow and Kanban board.
- Priorities, assignees, labels, comments, filtering, optimistic concurrency, and audit history.
- Private functional MVP followed by authenticated, authorised home-lab Release 1.
- Operational evidence for build, deployment, monitoring, backup, restore, and rollback.

### Later opportunities

- Outbound notifications and asynchronous processing.
- Search and measured performance improvements.
- Attachments, due dates, watchers, and richer project controls.
- AWS deployment.
- Other advanced capabilities justified by actual product or operational evidence.

## Release strategy

1. Build and accept the focused functionality in private environments.
2. Prove deployment, rollback, backup, and restore on the home lab.
3. Add Cognito authentication and enforce tenant authorization.
4. Complete security, quality, accessibility, performance, and operational hardening.
5. Run UAT and promote the exact approved artifacts to secure home-lab Release 1.
6. Stabilise before selecting enhancements or AWS deployment.

## Charter review triggers

Review this charter when:

- The primary audience changes.
- Release 1 gains or loses a major capability.
- Project visibility or tenancy changes.
- Authentication timing or deployment target changes.
- A critical assumption is disproved.
- A phase exit gate reveals that the desired outcome is no longer feasible within accepted constraints.


# Product scope and delivery basics, applied to TaskForge

Tags: `build` `deploy`

## Start here

**Product scope means deciding what the first version must do. Delivery means turning that decision into working software, checking it, and putting it somewhere people can use it.**

For TaskForge, the promise is: a small team can organise projects and track work on a board, with controlled access and a history of changes. For your home lab, the promise is: you can run that application on your server, inspect failures, and recover its data.

Implementing this topic produces requirements, small work items, acceptance checks, and release gates. Those are implemented in this guide and the linked [home-lab delivery checklist](../delivery/homelab-delivery-checklist.md). Application features and server deployment are separate execution work; writing these documents does not establish that either has passed.

The existing [Release 1 scope](release-1-scope.md) remains the product contract. This guide explains and applies it without replacing the [master plan](../delivery/master-plan.md) or inventing completed milestones.

## 1. The words, with examples — `build` `deploy`

| Word | Plain meaning | TaskForge example |
| --- | --- | --- |
| Product | The thing someone uses to solve a problem | TaskForge helps a team see its work and progress. |
| Scope | The boundary of what you will deliver | A fixed Kanban board is included; custom workflows are deferred. |
| Persona | A kind of user with a particular responsibility | An owner manages the organisation; a member works on issues. |
| Requirement | Something the product must do | A member can comment on an issue in their organisation. |
| User story | A short description of who needs something and why | As a member, I want to filter issues assigned to me so I can find my work. |
| Acceptance criterion | An observable condition that proves a requirement works | After filtering by my identity, every returned issue is assigned to me. |
| Happy path | The normal successful journey | The intended invitee accepts a valid invitation. |
| Failure path | What happens when an action cannot succeed | An expired invitation shows an explanation and creates no membership. |
| Backlog | An ordered list of work remaining | Organisation setup precedes project and issue workflows. |
| Vertical slice | One usable outcome across the necessary layers | Create organisation: form → API → database → visible result. |
| Quality target | How well a feature or system must work | A board request returns within the agreed time on measured test data. |
| UAT | User acceptance testing: use the app as its intended users | Act as owner and member and complete their actual journeys. |
| Release gate | Evidence that must pass before moving forward | Restore a backup successfully before accepting the home-lab release. |

`build` means you need the concept while implementing locally. `deploy` means you need it while preparing or operating the home-lab release. Some concepts matter in both places.

## 2. Who TaskForge is for — `build`

Use these personas when designing screens and tests. They describe behavior; they do not claim that user interviews or adoption have occurred.

| Persona | What they need | Example check |
| --- | --- | --- |
| Owner | Create an organisation and manage its membership and projects | Creating an organisation makes the current actor its owner. |
| Admin | Help manage invitations, non-owner memberships, and projects | Administrative actions respect the approved role matrix. |
| Member | Create, assign, move, label, discuss, and filter issues | A member can move an issue through an allowed transition. |
| Invited user | Join the correct organisation | A matching identity can accept a valid invitation once. |
| Outsider / unauthenticated user | Receive a safe denial | Guessing another organisation's issue ID reveals no protected data. |
| Operator — you | Run, diagnose, update, and recover TaskForge | Restore into a clean database and verify the application works. |

The exact permissions for ownership transfer and member removal remain decisions in the [decision log](../architecture/decision-log.md), especially D-106. Do not infer them from this summary.

## 3. The first-release boundary — `build` `deploy`

| Include in Release 1 | What that means for your work |
| --- | --- |
| Identity and organisation membership | Cognito sign-in for secure release; owner/admin/member roles and tenant isolation. |
| Invitations | In-app discovery and expiring shareable links; acceptance, rejection, revocation, and identity checks. |
| Projects | Organisation-wide visibility, stable project keys, and archival. |
| Issues and Kanban | Stable issue keys, fixed statuses, controlled transitions, and protection from stale edits. |
| Collaboration | Assignees, priorities, project labels, comments, filters, and audit history. |
| Usable Angular interface | Loading, empty, validation, denial, error, and conflict states; keyboard status changes. |
| Home-lab operation | Versioned deployments, authentication, diagnostics, backups, restore, rollback, and release evidence. |

Defer attachments, watchers, due dates, outbound notifications/email, custom workflows, private projects, advanced search, Redis, Kafka, microservices, Kubernetes, AI/MCP, and AWS application hosting. Cognito authentication still belongs to Release 1.

If an idea appears while building, record it in the backlog. Before adding it to Release 1, state the benefit, acceptance checks, added work, and what scope or forecast changes. This prevents a small first release from growing indefinitely.

## 4. Requirements turned into tests — `build`

“Given / When / Then” simply means **starting situation / action / expected result**. Use it to remove ambiguity before coding.

### Example A: create an organisation

**Story:** As a user, I want to create an organisation so my team has a shared workspace.

Traceability: R1-F02, J-01, E-010-S02, and the organisation work in E-040.

| Case | Given | When | Then |
| --- | --- | --- | --- |
| Success | A valid current actor and a valid organisation name | The user submits creation | One organisation and exactly one owner membership are saved; the new organisation appears in the interface. |
| Persistence | Creation succeeded | The user reloads and opens the organisation | The organisation and its ownership are still present. |
| Invalid input | The name violates the agreed validation rules | The user submits | A useful validation message appears and no organisation or membership is created. |
| Partial failure | Membership creation fails in the transaction | Creation is attempted | The organisation is not left behind without its owner membership. |
| Impersonation | The caller supplies somebody else's identity as owner | The request reaches the application | The caller cannot make ownership decisions by overriding the authenticated actor. |
| Access boundary | A different user is outside the organisation | They request its protected contents | Access is denied without exposing those contents. |

The private MVP may obtain the actor from a development adapter. Secure Release 1 must obtain it from validated identity. Organisation-name limits, duplicate-name policy, API errors, and UX details must be recorded before the corresponding implementation story is marked ready.

Repository observation: an organisation service and tests already exist. The service currently looks up an owner ID supplied in the request. That is existing implementation evidence, not proof that the current-actor or release authorization acceptance criteria above pass. Review and adapt it during identity-boundary work.

### Example B: move an issue

**Story:** As a member, I want to move an issue from `TODO` to `IN_PROGRESS` so the team sees that work has started.

Traceability: R1-F05, R1-F06, R1-F07, R1-F10; J-06 and J-09; E-050/E-060.

- [ ] `build` A valid `TODO` → `IN_PROGRESS` move persists and the card appears once in the new column after reload.
- [ ] `build` A prohibited transition leaves both issue state and successful-change audit history unchanged.
- [ ] `build` If another user changed the issue first, the stale move is rejected and the interface offers refresh/recovery.
- [ ] `build` If saving fails, the interface restores or reloads the persisted position and explains the failure.
- [ ] `build` A keyboard user can make the same status change without dragging.
- [ ] `build` A successful transition has an audit entry with actor, time, and old/new state when the audit slice is integrated.

## 5. Work in small complete pieces — `build` `deploy`

Building only all tables or only all screens does not yet give you a journey to try. Choose a small outcome and carry it through its necessary layers.

These outcomes summarize the existing backlog; they are not new competing epic IDs or claims that the work is ready. Review existing code and tests before estimating or rebuilding anything.

| Sequence | Existing work | Small demonstration | Dependencies / readiness |
| --- | --- | --- | --- |
| 1 | E-010/E-020 | Explain the create-organisation screen, validation, actor, API, and transaction | Resolve its UX and technical decisions. |
| 2 | E-030 | One Angular action reaches Spring Boot/PostgreSQL through the delivery path | Verify toolchain, migration/test foundation, actor adapter, API contract, and packaging. |
| 3 | E-040 | Create and reopen an organisation with its owner | Use the full criteria in example A; reuse existing implementation where correct. |
| 4 | E-040 | Intended invitee joins, then an owner/admin creates a visible project | Refine invitation lifecycle and project policies; split into separate stories. |
| 5 | E-050 | Create/open an issue, then move it correctly | Deliver creation and movement separately; include keys, validation, and concurrency. |
| 6 | E-060 | Collaborate and narrow the board | Separate labels, comments, filters, and audit into small stories, then integrate. |
| 7 | E-070 | Complete the private MVP on home-lab UAT | Rehearse deployment, restart, rollback, backup, and restore. |
| 8 | E-080/E-090 | Secure and diagnose the same journeys | Cognito, tenant/role tests, quality checks, and operational evidence. |
| 9 | E-100/E-110 | Promote and operate the tested release | UAT, go/no-go, exact artifact promotion, and stabilization. |

### Use this story card

```markdown
### <existing epic ID> — <one outcome>
Tags: build / deploy / both
State: Proposed
As a <persona>, I want <action>, so that <benefit>.
Scope/journey reference: <R1-Fxx / J-xx>
Dependencies and decisions: <specific prerequisites>
Included: <behavior in this story>
Acceptance: <Given / When / Then, including applicable failures>
Tasks: <small implementation steps across the required layers>
Tests: <unit / database / API / browser / operational as applicable>
Evidence: Not run — <add command or procedure, commit, environment, date, result>
Remaining defects or decisions: <list, or none after checking>
```

### Ready means you can start; done means you have proof

**Ready:** you know the outcome, inputs, rules, failure behavior, dependencies, test approach, and the implementation decisions that affect this story. If a decision is missing, create a small design task to resolve it.

**Done:** the applicable UI/API/database path works, acceptance and negative checks pass, relevant automated checks pass, documentation is current, and you can demonstrate the outcome. “Code exists” or “test file exists” is not a passing result.

The authoritative definitions are in the [backlog](../delivery/backlog.md).

### A manageable working rhythm

Use the existing two-week iteration cadence. Choose work only after checking your available time; this guide makes no delivery-date commitment.

1. At the start, choose one ready outcome and list its evidence of completion.
2. Work on one implementation story at a time. Split it if it cannot fit the iteration.
3. At the end of each session, record what changed, what passed, and the next small action.
4. Integrate and try the whole journey during the iteration.
5. At the end, demonstrate it, record remaining defects, and choose the next outcome from evidence.

An unfinished story stays unfinished. Reduce the next iteration's scope rather than changing the meaning of done.

## 6. Quality targets and release gates — `build` `deploy`

A feature requirement says **what** happens: “an issue appears on the board.” A quality target says **how well**: “the board remains usable with the agreed number of issues and users.” A gate asks **whether there is evidence** that those promises hold.

The [home-lab delivery checklist](../delivery/homelab-delivery-checklist.md) supplies candidate measurable targets, an environment record, and checks for local → private home-lab MVP → secure release. Candidate numbers are planning inputs until hardware and workload are validated; they are not measured performance or accepted architecture decisions.

## 7. What is established now — `build` `deploy`

- [x] The existing Release 1 boundary is explained in plain language.
- [x] Personas and concrete acceptance examples connect the concepts to TaskForge.
- [x] Work is mapped to the existing backlog, with a reusable story card and working rhythm.
- [x] Home-lab planning has a practical target/gate/evidence checklist.
- [ ] Application acceptance checks have passing evidence attached.
- [ ] Home-lab hardware, access, and recovery assumptions have been verified.
- [ ] Deployment and release gates have been executed and passed.

The original learning checklist remains unchecked until you can explain these ideas and use them on a real story. A useful first exercise is to take the organisation example, compare it with the existing implementation, and record which acceptance checks already have evidence and which still need work.

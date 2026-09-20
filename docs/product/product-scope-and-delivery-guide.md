# Product Scope and Delivery Guide

Revised 2026-09-20. Use the [charter](product-charter.md), [release contracts](product-charter.md#release-strategy), [backlog](../delivery/backlog.md) and [64-sprint roadmap](../delivery/sprint-roadmap.md) as the governing plan.

## What TaskForge promises

A team plans work, links it to code changes and a deployed version, investigates its permitted health/logs, and records incidents and corrective work. The PC-to-server roadmap provides the private environment in which this product can run and recover.

The complete board is Release 1. Deployment tracking and investigation are Release 2. MCP access follows Release 2. Host monitoring used to operate TaskForge is distinct from the customer-facing investigation capability.

## Terms in use

| Term | Meaning in this project |
|---|---|
| Product epic | A large outcome in the delivery backlog, identified by E-xxx |
| Product story | A deliverable capability or verification outcome with a stable TF-xxx ID |
| Implementation task | A checkbox under a story, identified by TF-xxx-Txx |
| TaskForge epic/story/task | Work entities that the finished application will let its users create |
| Sprint | Exactly 14 calendar days; a forecast slot is not proof of readiness |
| Artifact | Immutable output identified by digest and source/build evidence |
| Deployment | An externally executed attempt to run an artifact in a service/environment |
| Runtime health | Timestamped observed signals; separate from work/build/deployment status |
| Incident | An investigated problem with evidence and corrective work; linked deployment is not automatically proven cause |
| Acceptance evidence | Observed result tied to revision, environment, date and reproducible procedure |

The roadmap's TF IDs plan development of the product. They are not seeded or imported into an application that does not yet implement hierarchy/sprints.

## Scope by milestone

| Stage | Included | Gate |
|---|---|---|
| PC server | Preserve data, Linux, storage/access, Compose, database, backup, host monitoring | H-01–H-08 at S08 |
| Release 1 | Organisations/projects, typed work/hierarchy, 14-day sprints, board, collaboration, identity and recovery | J-01–J-13 at S32 |
| Release 2 | PR/build/artifact/deployment evidence, logs/health/trace links, alerts/incidents | R2-J01–R2-J06 at S56 |
| MCP v1 | Eleven tools through existing application services; only issue creation mutates | M-J01–M-J04 at S64 |

Deployment execution remains in operator scripts. Raw telemetry remains outside PostgreSQL. Private LAN/VPN access is the accepted network boundary.

## Refine and deliver a story

1. Read its scope contract and dependency evidence. Inspect existing code; do not rebuild a working capability merely because a plan lists it.
2. Resolve the affected UX/API/data/authorization/failure decisions and required environment facts.
3. Estimate against the sprint's 6–8 delivery-hour budget; split vertically if necessary.
4. Implement one useful path across its relevant layers with tests and diagnostics.
5. Reproduce the success and relevant failure cases in the integrated environment.
6. Record evidence and review acceptance separately from implementation. Reforecast unfinished work without changing the 14-day cadence.

All 128 initial roadmap cards are Proposed, not automatically implementation-ready. Early effort values are planning budgets. The [backlog](../delivery/backlog.md#definition-of-ready) owns Ready/Done definitions.

## Worked acceptance examples

### Create an organisation — TF-025/TF-026, R1-F02, J-01

Given a current actor and valid name, when creation commits, then one organisation and exactly one owner membership exist. If membership persistence fails, the organisation transaction rolls back. A caller cannot replace the actor by supplying another user's ID.

Repository observation: organisation creation code already exists and needs comparison with this actor boundary. File presence is not acceptance evidence.

### Close a sprint — TF-039/TF-040, R1-F13, J-13

Given a 14-day project sprint containing a story and unfinished tasks, when it closes, then its history records the result and the operator explicitly returns or carries the story to a planned sprint. Child tasks inherit the resulting story assignment. Concurrent closure cannot drop work, and the move does not mark it done or deployed.

### Trace a deployed story — TF-079/TF-080, R2-F08

Given explicit PR links and artifact inclusion evidence, when that artifact is confirmed running in UAT, then the story timeline shows UAT delivery with its provenance. A successful build without a receipt remains unconfirmed. A second service lacking evidence prevents a claim of complete multi-service delivery.

### Investigate safely — TF-089/TF-090, R2-F12

Given a member entitled to a deployment's telemetry source, when a bounded query runs, then only that permitted service/time/tenant scope is returned. A supplied source URL, raw query or tenant-header override is rejected. Missing or stale logs are labelled accordingly; shared host logs remain operator-only.

### Retry MCP creation — TF-125/TF-126, M-F05

Given an authenticated caller and a valid create command/key, when an uncertain response is retried, then exactly one item and one successful-create audit entry exist. A different command using the same key returns a conflict. No MCP call executes deployment or rollback.

## Reusable card

~~~markdown
### TF-<stable ID> — <outcome>
State: Proposed
Persona and benefit:
Epic / release capability / milestone:
Forecast sprint: Sxx (14 calendar days)
Estimated effort and confidence:
Dependencies and readiness decisions:
Tasks:
- [ ] TF-<ID>-T01: <concrete implementation>
- [ ] TF-<ID>-T02: <failure/verification work>
Acceptance:
- Given <state>, when <action>, then <observable success>.
- Given <relevant failure>, when <action>, then <safe observable outcome>.
Tests and demonstration:
Evidence: Not run
Revision/configuration, environment, date, procedure, expected/observed:
Remaining defects, decision and next action:
~~~

## Evidence discipline

A created runbook is not a performed restoration; a configured test is not a fresh passing run; a CI build is not a successful deployment; a deployment is not measured healthy; a related incident is not proven caused by every included story.

Use [home-lab gates](../delivery/homelab-delivery-checklist.md) for release evidence. Keep secrets and raw sensitive logs out of documents. Preserve historical decisions and record superseding ones in the [decision log](../architecture/decision-log.md).

## Learning and change control

Read only what supports the current work and record the resulting decision/test/runbook. Older Java/AWS/Redis/Kafka learning maps remain optional references. They cannot override the accepted release sequence or add scope.

New ideas require a capability, acceptance, dependencies/risks and a forecast trade-off, then consistent updates to contracts, backlog and decisions. AWS hosting, public ingress and personal home-lab services are unscheduled.

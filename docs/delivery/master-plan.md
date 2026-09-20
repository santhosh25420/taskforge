# TaskForge Delivery Master Plan

Status: accepted documentation baseline, revised 2026-09-19. This plan supersedes the former 18-sprint forecast.

## Outcome and authority

Deliver the journey **work → PR/build → deployed version → health/logs → incident/corrective work**, on a private PC server, with controlled MCP access.

The [charter](../product/product-charter.md) owns purpose and goals. Release contracts own included behaviour. The [backlog](backlog.md) owns epic outcomes; the [sprint roadmap](sprint-roadmap.md) owns the 64-sprint allocation and story cards. The [system overview](../architecture/system-overview.md) owns integration boundaries. Learning maps are references, not competing delivery schedules.

## Delivery sequence

| Stage | Sprints | Required result |
|---|---|---|
| PC-to-server foundation | S01–S08 | Assessed hardware/data, private access, storage, Compose, PostgreSQL, backups, host monitoring and existing-backend rehearsal |
| Application walking slice | S09–S12 | Reviewed product contracts and a repeatable browser/API/database/UAT path |
| Complete work product | S13–S28 | Organisations through hierarchy, sprints, board, collaboration and authenticated tenant-safe UAT |
| Release 1 | S29–S32 | Quality/recovery evidence and exact-artifact promotion of the board |
| Delivery evidence | S33–S42 | Repository/service/environment model, PR/build/artifact relationships, receipts and reconciliation |
| Operational investigation | S43–S52 | Scoped telemetry, logs, health, traces, alerts, incidents and corrective work |
| Release 2 | S53–S56 | Recovery, capacity/security evidence and accepted delivery/investigation release |
| MCP v1 | S57–S64 | Identity/discovery, all approved tools, client journey and release/recovery evidence |
| Later | Unscheduled | E-120 AWS hosting or other separately prioritised capabilities |

This sequence replaces historical phase numbers; epic IDs remain stable. E-010/E-020 design work is planned explicitly in S09 and refined within later slices. E-110 stabilisation applies after each release. MCP E-130 follows Release 2 rather than immediately following the board.

## Capacity and cadence

64 sprints × 14 days = 128 weeks, initially 512–640 available hours. Plan approximately 6–8 delivery hours per sprint; use the remainder for review, learning that unblocks work, coordination and unforeseen issues. This is a forecast, not a fixed completion guarantee.

- At sprint start, confirm capacity, dependencies, ready stories and one demonstrable goal.
- Work on one story at a time; integrate its applicable UI/API/data/operations path.
- At the midpoint, review blockers and reduce planned scope before starting more work.
- At sprint end, demonstrate acceptance, record evidence/defects, review risks and choose a retrospective action.
- Refine the next sprint and reforecast after any spillover. Never extend a sprint to hide unfinished work.
- Keep story IDs when work moves. Do not mark a story ready merely because it has a forecast sprint.

## Definition of ready and done

Use the [backlog definitions](backlog.md#definition-of-ready). Ready requires the affected behaviour, dependencies, actor/tenant boundary, failure cases and test approach to be clear. Server changes additionally require actual host/disk identifiers and recovery prerequisites.

Done requires acceptance evidence from the relevant integrated environment, passing applicable tests, current contracts/runbooks, and an accepted demo. The evidence record includes revision, date, environment, command/procedure, outcome and unresolved defects. Documentation state and product completion are separate.

## Environment and delivery rules

| Environment | Boundary |
|---|---|
| Developer workstation | Disposable tests and explicit local identity; no assumed access to PC disks |
| Home-lab UAT | Private network, separate credentials/volumes, non-sensitive fixtures |
| Home-lab release | Cognito, tenant enforcement, private HTTPS, release data and recovery evidence |
| Observability services | Private; backend/operator access with explicit organisation/source mapping |

UAT and release share a physical failure domain. Host operation must remain possible if TaskForge, GitHub synchronisation or the log viewer is down.

Build immutable images in CI, promote the same digests, and operate deployments through external scripts. Durable receipts allow TaskForge to catch up after an outage. No UI/MCP action executes deployment or rollback in this horizon.

## Milestone gates

- **S08 server gate:** H-01–H-08 in the [server plan](server-build-plan.md) pass, including recovery and private reachability; existing-backend startup is not a product release.
- **S32 Release 1 gate:** R1 capabilities and J-01–J-13 pass, with identity, tenant tests, quality, backup/restore and rollback evidence.
- **S56 Release 2 gate:** R2 capabilities and journeys prove delivery attribution and authorised investigation, including stale data, retry/order and connector outage cases.
- **S64 MCP gate:** all eleven tools meet their contract and the supported client completes the end-to-end journey; MCP disablement and rollback preserve product operation.

Every release has scope freeze, exact candidate identifiers, known limitations, security/quality evidence and a recorded go/no-go. Critical/high unresolved security defects block promotion. Missing evidence remains missing.

## Roles and change control

Product owner: scope and acceptance. Delivery lead: forecast and risks. UX/frontend/backend: complete vertical slices. Security/QA: boundaries and negative cases. Operator: host, deployment and recovery.

Change the affected release contract, epic, sprint forecast, risk and decision together. Preserve the existing meaning of IDs; add new capabilities rather than reusing an old identifier for different work. Stable story IDs and the legacy schedule crosswalk live in the sprint roadmap.

No release completion, host installation, passing application tests or working external connection is claimed by this documentation revision.

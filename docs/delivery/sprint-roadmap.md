# TaskForge Product Sprint Roadmap

Status: accepted planning baseline; all implementation stories below are **Proposed**, with evidence **Not run**. Revised 2026-09-20. This replaces the former 18-sprint/36-week forecast.

## Outcome and cadence

**Epic → story/task → PR → build → deployed version → health/logs → incident/corrective work**, delivered on the user's private PC server and accessible through MCP.

- 64 sprints × 14 calendar days = 128 weeks, initially 512–640 available hours at 4–5 hours/week.
- Budget 6–8 delivery hours within each sprint's 8–10 available hours. The cards initially allocate 5 hours to a capability and 2 hours to verification, a 7-hour forecast rather than a validated estimate.
- Re-estimate and split vertically when a card cannot fit. Preserve its stable TF ID when it moves; add suffixed child IDs when splitting, and update dependencies. Never extend the 14-day sprint or skip its gate to preserve a date.
- Calendar dates remain unset. At start, sprint n covers start + 14(n−1) days through start + 14n − 1 days inclusive.
- One active story at a time. Review progress mid-sprint; demonstrate, retrospect and refine at the end. Record actual hours and reforecast after spillover.
- Testing and operations tasks are part of each delivery slice; the paired evidence story makes acceptance explicit. They do not postpone all testing to a later release.
- A scheduled story is not automatically Ready. Apply the [backlog definitions](backlog.md#definition-of-ready), resolve its affected decisions and confirm real environment prerequisites first.

## Milestones and contracts

| Milestone | Forecast | Gate |
|---|---|---|
| Private PC server | S08 | [H-01–H-08](server-build-plan.md#build-sequence-and-gates): private access, data preservation, database recovery and existing-backend rehearsal |
| Complete work-management release | S32 | [Release 1](../product/release-1-scope.md): board, hierarchy, 14-day project sprints, identity, tenant/security and recovery |
| Delivery and investigation release | S56 | [Release 2](../product/release-2-scope.md): artifact/deployment evidence, authorised logs/health/traces, alerts, incidents and reconciliation |
| MCP v1 | S64 | [MCP contract](../product/mcp-v1-scope.md): eleven tools, supported client, scoped reads, idempotent create and recovery |

Host/app monitoring required to operate TaskForge starts early. Customer-facing deployment tracking starts after Release 1. TaskForge never executes deployments in this horizon; operator scripts remain independent.

## Sprint index

| Sprint | Outcome | Epic(s) | Stories |
|---|---|---|---|
| S01 | [Assess the PC and preserve data](#s01) | E-140 | TF-001, TF-002 |
| S02 | [Install and administer Ubuntu](#s02) | E-140 | TF-003, TF-004 |
| S03 | [Establish private network access](#s03) | E-140 | TF-005, TF-006 |
| S04 | [Make storage and host recovery reliable](#s04) | E-140 | TF-007, TF-008 |
| S05 | [Prepare isolated container environments](#s05) | E-140 | TF-009, TF-010 |
| S06 | [Back up and restore PostgreSQL](#s06) | E-140 | TF-011, TF-012 |
| S07 | [Observe host and backup health](#s07) | E-140 | TF-013, TF-014 |
| S08 | [Rehearse the existing backend on the PC](#s08) | E-140 | TF-015, TF-016 |
| S09 | [Refine product flows and architecture](#s09) | E-010, E-020 | TF-017, TF-018 |
| S10 | [Make backend builds and persistence reproducible](#s10) | E-030 | TF-019, TF-020 |
| S11 | [Open the Angular shell with an explicit actor](#s11) | E-030 | TF-021, TF-022 |
| S12 | [Deploy the browser-to-database walking slice](#s12) | E-030 | TF-023, TF-024 |
| S13 | [Create an organisation and its owner](#s13) | E-040 | TF-025, TF-026 |
| S14 | [Switch organisations and inspect members](#s14) | E-040 | TF-027, TF-028 |
| S15 | [Invite and join through in-app discovery](#s15) | E-040 | TF-029, TF-030 |
| S16 | [Complete invitation-link failure paths](#s16) | E-040 | TF-031, TF-032 |
| S17 | [Create and archive projects](#s17) | E-040 | TF-033, TF-034 |
| S18 | [Capture issues with stable keys](#s18) | E-050 | TF-035, TF-036 |
| S19 | [Organise epics, stories, tasks and bugs](#s19) | E-150 | TF-037, TF-038 |
| S20 | [Plan and close fourteen-day sprints](#s20) | E-150 | TF-039, TF-040 |
| S21 | [Manage the fixed Kanban workflow](#s21) | E-050 | TF-041, TF-042 |
| S22 | [Recover from concurrent edits and failures](#s22) | E-050 | TF-043, TF-044 |
| S23 | [Assign, prioritise and label work](#s23) | E-060 | TF-045, TF-046 |
| S24 | [Discuss work and audit accepted changes](#s24) | E-060 | TF-047, TF-048 |
| S25 | [Filter work and page through history](#s25) | E-060 | TF-049, TF-050 |
| S26 | [Sign in with Cognito](#s26) | E-080 | TF-051, TF-052 |
| S27 | [Verify roles and tenant isolation](#s27) | E-080 | TF-053, TF-054 |
| S28 | [Accept the complete board in private UAT](#s28) | E-070 | TF-055, TF-056 |
| S29 | [Verify accessibility and regression](#s29) | E-090 | TF-057, TF-058 |
| S30 | [Diagnose and measure the application](#s30) | E-090 | TF-059, TF-060 |
| S31 | [Rehearse Release 1 recovery](#s31) | E-090 | TF-061, TF-062 |
| S32 | [Release the complete work-management product](#s32) | E-100 | TF-063, TF-064 |
| S33 | [Stabilise Release 1 and connect a repository](#s33) | E-110, E-160 | TF-065, TF-066 |
| S34 | [Catalogue services and environments](#s34) | E-160 | TF-067, TF-068 |
| S35 | [Link work explicitly to pull requests](#s35) | E-160 | TF-069, TF-070 |
| S36 | [Synchronise GitHub builds and freshness](#s36) | E-160 | TF-071, TF-072 |
| S37 | [Record immutable artifact contents](#s37) | E-160 | TF-073, TF-074 |
| S38 | [Receive durable deployment receipts](#s38) | E-160 | TF-075, TF-076 |
| S39 | [Show environment deployment history](#s39) | E-160 | TF-077, TF-078 |
| S40 | [Trace work across services and environments](#s40) | E-160 | TF-079, TF-080 |
| S41 | [Represent failure and rollback honestly](#s41) | E-160 | TF-081, TF-082 |
| S42 | [Reconcile delayed and missing delivery events](#s42) | E-160 | TF-083, TF-084 |
| S43 | [Correlate telemetry to deployment context](#s43) | E-170 | TF-085, TF-086 |
| S44 | [Collect and protect application logs](#s44) | E-170 | TF-087, TF-088 |
| S45 | [Query bounded deployment logs](#s45) | E-170 | TF-089, TF-090 |
| S46 | [Investigate logs inside TaskForge](#s46) | E-170 | TF-091, TF-092 |
| S47 | [Show measured deployment health](#s47) | E-170 | TF-093, TF-094 |
| S48 | [Link logs to traces](#s48) | E-170 | TF-095, TF-096 |
| S49 | [Receive and deduplicate alerts](#s49) | E-180 | TF-097, TF-098 |
| S50 | [Manage an incident lifecycle](#s50) | E-180 | TF-099, TF-100 |
| S51 | [Connect incidents to evidence and corrective work](#s51) | E-180 | TF-101, TF-102 |
| S52 | [Run a complete deployment investigation](#s52) | E-180 | TF-103, TF-104 |
| S53 | [Recover connectors and queues after outages](#s53) | E-190 | TF-105, TF-106 |
| S54 | [Review operational data access and secrets](#s54) | E-190 | TF-107, TF-108 |
| S55 | [Verify capacity, retention and full recovery](#s55) | E-190 | TF-109, TF-110 |
| S56 | [Release deployment tracking and investigation](#s56) | E-190 | TF-111, TF-112 |
| S57 | [Stabilise Release 2 and specify MCP](#s57) | E-110, E-130 | TF-113, TF-114 |
| S58 | [Authenticate MCP discovery and transport](#s58) | E-130 | TF-115, TF-116 |
| S59 | [Expose work-management read tools](#s59) | E-130 | TF-117, TF-118 |
| S60 | [Expose work delivery and deployment tools](#s60) | E-130 | TF-119, TF-120 |
| S61 | [Expose bounded health and log tools](#s61) | E-130 | TF-121, TF-122 |
| S62 | [Expose incident read tools](#s62) | E-130 | TF-123, TF-124 |
| S63 | [Create issues safely from a supported MCP client](#s63) | E-130 | TF-125, TF-126 |
| S64 | [Release TaskForge MCP v1](#s64) | E-130 | TF-127, TF-128 |

## Story card conventions

Each TF ID is independent of its forecast sprint. Each task ID is the owning story ID plus T01/T02/T03. Estimate means an initial effort budget, not earned progress. All cards start Proposed and every evidence record starts Not run.

Server evidence records host/config revision, commands/procedures and recovery outcomes; application evidence records commit/image digest, migration version, environment and test/demo result. Store no credentials or raw sensitive logs in evidence. The operator/product reviewer accepts the demo separately from implementing it.

All sprints depend on the prior sprint's required evidence plus their named scope prerequisites. A defect may be resolved and reforecast explicitly; dependency completion must not be assumed. E-010/E-020 foundation refinements occur at S09 and continue in each affected slice.

## Sprint cards

<a id="s01"></a>

### S01 — Assess the PC and preserve data

**Goal:** Establish a safe, feasible server installation.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-01.  
**Prerequisites:** Accepted charter and access to the actual PC; hardware/data/backup details must be discovered before installation.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-001 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to establish a safe, feasible server installation.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-001-T01: Record actual CPU/RAM, disk identities, OS, existing files/workloads and console access.
- [ ] TF-001-T02: Size the initial stack, backup destination and recovery headroom; identify unresolved prerequisites.
- [ ] TF-001-T03: Back up preservation-required data outside the host and restore a sample without modifying source data.

Acceptance:

- Given the actual PC, when assessment completes, then the installation target, preserved data and feasible resource/backup budget are recorded.
- Given an unknown disk or unverified backup, when installation readiness is reviewed, then readiness remains blocked without erasing data.

Tests: Inventory inspection and isolated sample-restoration rehearsal. Run the focused checks while implementing; TF-002 records the integrated acceptance.  
Demo: Show the disk/data inventory and recovered sample.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-002 — Verify assess the pc and preserve data

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-001. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-002-T01: Execute and record: Inventory inspection and isolated sample-restoration rehearsal.
- [ ] TF-002-T02: Demonstrate: Show the disk/data inventory and recovered sample. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Show the disk/data inventory and recovered sample. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s02"></a>

### S02 — Install and administer Ubuntu

**Goal:** Administer a supported Ubuntu Server installation through reliable access.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-02.  
**Prerequisites:** S01 required evidence (TF-002); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-003 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to administer a supported Ubuntu Server installation through reliable access.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-003-T01: Select a supported LTS and confirmed installation target from S01; record recovery access.
- [ ] TF-003-T02: Install/patch Ubuntu and create a non-root administrative user with SSH keys.
- [ ] TF-003-T03: Verify console/key access and reboot before restricting password-based administration.

Acceptance:

- Given confirmed preservation and targets, when the host restarts, then the operator can log in and administer the patched installation.
- Given a failed key-login check, when hardening is attempted, then working recovery access is retained.

Tests: Console, SSH, privilege and reboot checks. Run the focused checks while implementing; TF-004 records the integrated acceptance.  
Demo: Reboot and log in using the documented operator account.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-004 — Verify install and administer ubuntu

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-003. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-004-T01: Execute and record: Console, SSH, privilege and reboot checks.
- [ ] TF-004-T02: Demonstrate: Reboot and log in using the documented operator account. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Reboot and log in using the documented operator account. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s03"></a>

### S03 — Establish private network access

**Goal:** Reach the server privately from the MacBook and authorised remote devices.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-03.  
**Prerequisites:** S02 required evidence (TF-004); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-005 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to reach the server privately from the MacBook and authorised remote devices.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-005-T01: Record LAN address reservation, private naming and allowed inbound/outbound paths.
- [ ] TF-005-T02: Configure firewall and private VPN; retain console recovery while changing access.
- [ ] TF-005-T03: Set private HTTPS/naming prerequisites and document certificate trust/renewal responsibilities.

Acceptance:

- Given an authorised LAN/VPN device, when it connects, then intended administration/application endpoints are reachable.
- Given an unapproved network path, when database or administration access is attempted, then it is denied.

Tests: Reachability/firewall matrix from LAN, VPN and unapproved paths. Run the focused checks while implementing; TF-006 records the integrated acceptance.  
Demo: Connect through VPN and show blocked non-approved ports.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-006 — Verify establish private network access

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-005. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-006-T01: Execute and record: Reachability/firewall matrix from LAN, VPN and unapproved paths.
- [ ] TF-006-T02: Demonstrate: Connect through VPN and show blocked non-approved ports. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Connect through VPN and show blocked non-approved ports. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s04"></a>

### S04 — Make storage and host recovery reliable

**Goal:** Retain correct storage, time and access after a server restart.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-04.  
**Prerequisites:** S03 required evidence (TF-006); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-007 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to retain correct storage, time and access after a server restart.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-007-T01: Configure identified persistent mounts, ownership and disk-health inspection.
- [ ] TF-007-T02: Configure clock synchronisation and record resource limits and power/reboot behaviour.
- [ ] TF-007-T03: Write recovery steps for missing mounts, disk pressure and interrupted boot.

Acceptance:

- Given configured storage, when the PC reboots, then mounts, permissions, clock and access return as recorded.
- Given a missing application mount, when services start, then failure is visible rather than writing application data into the wrong location.

Tests: Reboot and safe missing-mount/disk-pressure simulations. Run the focused checks while implementing; TF-008 records the integrated acceptance.  
Demo: Show persistent mounts and correct data after reboot.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-008 — Verify make storage and host recovery reliable

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-007. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-008-T01: Execute and record: Reboot and safe missing-mount/disk-pressure simulations.
- [ ] TF-008-T02: Demonstrate: Show persistent mounts and correct data after reboot. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Show persistent mounts and correct data after reboot. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s05"></a>

### S05 — Prepare isolated container environments

**Goal:** Run separate private UAT and release container environments.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-05.  
**Prerequisites:** S04 required evidence (TF-008); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-009 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to run separate private UAT and release container environments.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-009-T01: Install supported Docker Engine/Compose and document operator privileges.
- [ ] TF-009-T02: Define isolated networks, credentials, storage and resource budgets for UAT/release.
- [ ] TF-009-T03: Run a disposable smoke stack and test restart plus network/storage boundaries.

Acceptance:

- Given both environments, when UAT data changes, then release data remains unchanged and both stacks restart predictably.
- Given a non-approved client, when it attempts database or Docker management access, then the private boundary rejects it.

Tests: Compose configuration inspection, isolation and restart checks. Run the focused checks while implementing; TF-010 records the integrated acceptance.  
Demo: Change a UAT fixture and prove release storage is separate.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-010 — Verify prepare isolated container environments

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-009. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-010-T01: Execute and record: Compose configuration inspection, isolation and restart checks.
- [ ] TF-010-T02: Demonstrate: Change a UAT fixture and prove release storage is separate. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Change a UAT fixture and prove release storage is separate. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s06"></a>

### S06 — Back up and restore PostgreSQL

**Goal:** Recover database data from a verified backup outside the PC.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-06.  
**Prerequisites:** S05 required evidence (TF-010); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-011 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to recover database data from a verified backup outside the PC.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-011-T01: Configure persistent PostgreSQL with private credentials and the selected resource budget.
- [ ] TF-011-T02: Schedule backup transfer outside the host with retention and failure reporting.
- [ ] TF-011-T03: Restore a known fixture into isolated storage and document recovery commands.

Acceptance:

- Given a completed off-host backup, when it is restored to a clean database, then expected records and relationships are recoverable.
- Given a failed or stale backup, when its status is inspected, then it cannot be reported as a valid recent recovery point.

Tests: Backup failure, freshness and isolated restore checks. Run the focused checks while implementing; TF-012 records the integrated acceptance.  
Demo: Restore the fixture and record elapsed time and recoverable timestamp.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-012 — Verify back up and restore postgresql

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-011. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-012-T01: Execute and record: Backup failure, freshness and isolated restore checks.
- [ ] TF-012-T02: Demonstrate: Restore the fixture and record elapsed time and recoverable timestamp. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Restore the fixture and record elapsed time and recoverable timestamp. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s07"></a>

### S07 — Observe host and backup health

**Goal:** Detect resource, disk and backup failures on the private server.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-07.  
**Prerequisites:** S06 required evidence (TF-012); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-013 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to detect resource, disk and backup failures on the private server.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-013-T01: Run Prometheus/Grafana and host metrics with private access and bounded retention.
- [ ] TF-013-T02: Add CPU/memory/disk and backup-age panels with explicit warning thresholds.
- [ ] TF-013-T03: Document who inspects alerts and how to recover from a missing collector.

Acceptance:

- Given a controlled disk or backup-age breach, when monitoring evaluates it, then the operator sees a timestamped warning.
- Given missing metrics, when the dashboard loads, then missing/stale data is not shown as healthy.

Tests: Safe threshold breach, collector outage and dashboard access checks. Run the focused checks while implementing; TF-014 records the integrated acceptance.  
Demo: Show normal health, trigger a warning and acknowledge it.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-014 — Verify observe host and backup health

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-013. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-014-T01: Execute and record: Safe threshold breach, collector outage and dashboard access checks.
- [ ] TF-014-T02: Demonstrate: Show normal health, trigger a warning and acknowledge it. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Show normal health, trigger a warning and acknowledge it. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s08"></a>

### S08 — Rehearse the existing backend on the PC

**Goal:** Run and recover the current backend from a known revision.  
**Milestone:** Server foundation (S08). **Epic(s):** E-140. **Scope:** H-08; R1-F11.  
**Prerequisites:** S07 required evidence (TF-014); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-015 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to run and recover the current backend from a known revision.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-015-T01: Package the existing backend and privately supply its database/TLS configuration.
- [ ] TF-015-T02: Deploy it on the private server; record revision, health and current functional limitations.
- [ ] TF-015-T03: Rehearse restart, compatible artifact rollback and database restoration using S06 procedures.

Acceptance:

- Given the recorded artifact, when deployment and recovery complete, then health and persisted fixtures match that revision.
- Given a migration/startup failure, when deployment is assessed, then it remains failed and recovery uses the documented compatible state.

Tests: Backend smoke, host reboot, migration failure and recovery rehearsal. Run the focused checks while implementing; TF-016 records the integrated acceptance.  
Demo: Show the running backend revision and recovery evidence; label it pre-release.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-016 — Verify rehearse the existing backend on the pc

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-015. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-016-T01: Execute and record: Backend smoke, host reboot, migration failure and recovery rehearsal.
- [ ] TF-016-T02: Demonstrate: Show the running backend revision and recovery evidence; label it pre-release. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Show the running backend revision and recovery evidence; label it pre-release. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s09"></a>

### S09 — Refine product flows and architecture

**Goal:** Turn the accepted product journey into implementable slices.  
**Milestone:** Release 1 (S32). **Epic(s):** E-010, E-020. **Scope:** J-01–J-13; R2-J01–R2-J06; M-J01–M-J04.  
**Prerequisites:** S08 required evidence (TF-016); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-017 — Deliver the capability

State: Proposed. Persona: product owner. Estimate: 5 hours, provisional.  
Story: As a product owner, I want to turn the accepted product journey into implementable slices.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-017-T01: Review actual backend/schema/test assets against the release contracts and identify reusable work.
- [ ] TF-017-T02: Specify board/hierarchy/sprint journeys, failure UX, role matrix and module ownership.
- [ ] TF-017-T03: Record foundational API/data/identity decisions and make S10–S12 stories ready.

Acceptance:

- Given each committed capability, when refinement ends, then it maps to a persona, module, story and observable acceptance case.
- Given an unresolved decision affecting the next slice, when readiness is evaluated, then the slice is not marked ready.

Tests: Contract/journey traceability and architecture review. Run the focused checks while implementing; TF-018 records the integrated acceptance.  
Demo: Walk through a story from planning to future operational investigation.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-018 — Verify refine product flows and architecture

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-017. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-018-T01: Execute and record: Contract/journey traceability and architecture review.
- [ ] TF-018-T02: Demonstrate: Walk through a story from planning to future operational investigation. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Walk through a story from planning to future operational investigation. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s10"></a>

### S10 — Make backend builds and persistence reproducible

**Goal:** Build and test the backend against its real PostgreSQL migrations.  
**Milestone:** Release 1 (S32). **Epic(s):** E-030. **Scope:** E-030-S02/S03/S06/S08; R1-F11.  
**Prerequisites:** S09 required evidence (TF-018); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-019 — Deliver the capability

State: Proposed. Persona: contributor. Estimate: 5 hours, provisional.  
Story: As a contributor, I want to build and test the backend against its real PostgreSQL migrations.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-019-T01: Review supported runtime/dependency versions and document the chosen reproducible toolchain.
- [ ] TF-019-T02: Verify Liquibase clean-install and relevant upgrade behaviour with PostgreSQL tests; repair identified gaps.
- [ ] TF-019-T03: Add CI build/test and versioned API/error-contract checks with artifact metadata.

Acceptance:

- Given a clean supported environment, when CI runs, then the backend builds and real migrations/constraints are exercised.
- Given a broken migration or failing contract test, when CI runs, then the change cannot produce an approved artifact.

Tests: Clean build, PostgreSQL integration, migration and deliberate failing-check cases. Run the focused checks while implementing; TF-020 records the integrated acceptance.  
Demo: Run the documented commands and inspect CI evidence.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-020 — Verify make backend builds and persistence reproducible

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-019. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-020-T01: Execute and record: Clean build, PostgreSQL integration, migration and deliberate failing-check cases.
- [ ] TF-020-T02: Demonstrate: Run the documented commands and inspect CI evidence. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Run the documented commands and inspect CI evidence. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s11"></a>

### S11 — Open the Angular shell with an explicit actor

**Goal:** Open a coherent workspace with a safe development identity boundary.  
**Milestone:** Release 1 (S32). **Epic(s):** E-030. **Scope:** R1-F01; E-030-S04/S05.  
**Prerequisites:** S10 required evidence (TF-020); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-021 — Deliver the capability

State: Proposed. Persona: local product user. Estimate: 5 hours, provisional.  
Story: As a local product user, I want to open a coherent workspace with a safe development identity boundary.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-021-T01: Create the Angular/TypeScript shell, navigation and accessible loading/empty/error states.
- [ ] TF-021-T02: Implement provider-neutral current-actor handling with an explicitly private development adapter.
- [ ] TF-021-T03: Connect the shell to a typed/contract-checked API client and document release-profile restrictions.

Acceptance:

- Given the private development profile, when the user opens the shell, then the actor and empty workspace are clear.
- Given a caller-supplied actor override or release profile, when development identity is attempted, then it cannot grant authority.

Tests: Component/browser states and actor/profile negative tests. Run the focused checks while implementing; TF-022 records the integrated acceptance.  
Demo: Open the shell, identify the actor and simulate an unavailable API.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-022 — Verify open the angular shell with an explicit actor

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-021. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-022-T01: Execute and record: Component/browser states and actor/profile negative tests.
- [ ] TF-022-T02: Demonstrate: Open the shell, identify the actor and simulate an unavailable API. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Open the shell, identify the actor and simulate an unavailable API. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s12"></a>

### S12 — Deploy the browser-to-database walking slice

**Goal:** Deliver the same integrated artifact through local and UAT environments.  
**Milestone:** Release 1 (S32). **Epic(s):** E-030. **Scope:** E-030-S07/S08/S09; R1-F11.  
**Prerequisites:** S11 required evidence (TF-022); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-023 — Deliver the capability

State: Proposed. Persona: contributor. Estimate: 5 hours, provisional.  
Story: As a contributor, I want to deliver the same integrated artifact through local and UAT environments.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-023-T01: Connect one read/write browser action to the backend/PostgreSQL path with bounded error handling.
- [ ] TF-023-T02: Build versioned images in CI and publish immutable digests to the selected registry.
- [ ] TF-023-T03: Deploy approved digests through private operator scripts and verify version, persistence and rollback.

Acceptance:

- Given one approved build, when it is promoted to UAT, then the browser action persists and the running digests match the build.
- Given a failed smoke check, when promotion is attempted, then failure is recorded and the prior compatible artifact can be restored.

Tests: Browser/API/database test plus digest, health and rollback checks. Run the focused checks while implementing; TF-024 records the integrated acceptance.  
Demo: Run the walking slice in UAT and identify the exact artifact.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-024 — Verify deploy the browser-to-database walking slice

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-023. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-024-T01: Execute and record: Browser/API/database test plus digest, health and rollback checks.
- [ ] TF-024-T02: Demonstrate: Run the walking slice in UAT and identify the exact artifact. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Run the walking slice in UAT and identify the exact artifact. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s13"></a>

### S13 — Create an organisation and its owner

**Goal:** Create a workspace with one valid owner membership.  
**Milestone:** Release 1 (S32). **Epic(s):** E-040. **Scope:** R1-F02; J-01.  
**Prerequisites:** S12 required evidence (TF-024); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-025 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to create a workspace with one valid owner membership.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-025-T01: Refine existing organisation code to derive the owner from the current actor.
- [ ] TF-025-T02: Implement transactional creation, constraints and a useful response/error contract.
- [ ] TF-025-T03: Add the browser creation flow and its validation/loading/retry states.

Acceptance:

- Given a valid actor and name, when creation succeeds, then one organisation and exactly one owner membership exist.
- Given membership persistence failure or attempted owner impersonation, when creation is attempted, then no unauthorised or partial organisation is created.

Tests: PostgreSQL transaction/constraint, actor and browser acceptance tests. Run the focused checks while implementing; TF-026 records the integrated acceptance.  
Demo: Create and reopen an organisation, then show an invalid attempt.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-026 — Verify create an organisation and its owner

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-025. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-026-T01: Execute and record: PostgreSQL transaction/constraint, actor and browser acceptance tests.
- [ ] TF-026-T02: Demonstrate: Create and reopen an organisation, then show an invalid attempt. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Create and reopen an organisation, then show an invalid attempt. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s14"></a>

### S14 — Switch organisations and inspect members

**Goal:** See only the selected organisation and its permitted members.  
**Milestone:** Release 1 (S32). **Epic(s):** E-040. **Scope:** R1-F02; J-10.  
**Prerequisites:** S13 required evidence (TF-026); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-027 — Deliver the capability

State: Proposed. Persona: organisation member. Estimate: 5 hours, provisional.  
Story: As a organisation member, I want to see only the selected organisation and its permitted members.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-027-T01: Add actor-scoped organisation/member queries with deterministic bounded results.
- [ ] TF-027-T02: Build organisation selection and member views that clear prior-context state.
- [ ] TF-027-T03: Exercise direct-ID access and failed-load transitions between two tenants.

Acceptance:

- Given membership in two organisations, when context changes, then only the selected organisation's current data is shown.
- Given another tenant's identifier or a failed switch request, when the view loads, then protected or stale prior-tenant data is not displayed.

Tests: API isolation and browser context-switch/failure cases. Run the focused checks while implementing; TF-028 records the integrated acceptance.  
Demo: Switch between two organisations and demonstrate cross-tenant denial.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-028 — Verify switch organisations and inspect members

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-027. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-028-T01: Execute and record: API isolation and browser context-switch/failure cases.
- [ ] TF-028-T02: Demonstrate: Switch between two organisations and demonstrate cross-tenant denial. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Switch between two organisations and demonstrate cross-tenant denial. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s15"></a>

### S15 — Invite and join through in-app discovery

**Goal:** Invite the intended user and let them join once.  
**Milestone:** Release 1 (S32). **Epic(s):** E-040. **Scope:** R1-F03; J-02.  
**Prerequisites:** S14 required evidence (TF-028); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-029 — Deliver the capability

State: Proposed. Persona: owner or admin. Estimate: 5 hours, provisional.  
Story: As a owner or admin, I want to invite the intended user and let them join once.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-029-T01: Specify token/email/role policy and persist invitation secrets only as safe representations.
- [ ] TF-029-T02: Implement authorised invitation creation and matching-email discovery/acceptance transactions.
- [ ] TF-029-T03: Add invitation creation and accept/reject UI using the same lifecycle.

Acceptance:

- Given a matching pending invitation, when the intended identity accepts, then exactly one eligible membership is created.
- Given a mismatched identity or repeated acceptance, when acceptance is attempted, then no unauthorised or duplicate membership results.

Tests: Invitation constraint, transaction, identity and browser cases. Run the focused checks while implementing; TF-030 records the integrated acceptance.  
Demo: Invite a second identity and accept from its pending list.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-030 — Verify invite and join through in-app discovery

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-029. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-030-T01: Execute and record: Invitation constraint, transaction, identity and browser cases.
- [ ] TF-030-T02: Demonstrate: Invite a second identity and accept from its pending list. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Invite a second identity and accept from its pending list. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s16"></a>

### S16 — Complete invitation-link failure paths

**Goal:** Use a shareable invitation link with clear lifecycle outcomes.  
**Milestone:** Release 1 (S32). **Epic(s):** E-040. **Scope:** R1-F03; J-03.  
**Prerequisites:** S15 required evidence (TF-030); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-031 — Deliver the capability

State: Proposed. Persona: invited user. Estimate: 5 hours, provisional.  
Story: As a invited user, I want to use a shareable invitation link with clear lifecycle outcomes.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-031-T01: Connect link opening and identity hand-off to the existing invitation record.
- [ ] TF-031-T02: Implement expiry, rejection, revocation and stable repeated-use outcomes.
- [ ] TF-031-T03: Ensure URL handling, UI failures and logs do not disclose raw secrets.

Acceptance:

- Given a valid link and matching identity, when accepted, then the existing invitation grants one membership.
- Given an expired/revoked/used/mismatched link, when opened or accepted, then membership is unchanged and the outcome is safe.

Tests: Browser hand-off, race/reuse and secret-redaction checks. Run the focused checks while implementing; TF-032 records the integrated acceptance.  
Demo: Accept a valid link and show expired and mismatched cases.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-032 — Verify complete invitation-link failure paths

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-031. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-032-T01: Execute and record: Browser hand-off, race/reuse and secret-redaction checks.
- [ ] TF-032-T02: Demonstrate: Accept a valid link and show expired and mismatched cases. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Accept a valid link and show expired and mismatched cases. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s17"></a>

### S17 — Create and archive projects

**Goal:** Manage projects that all permitted organisation members can use.  
**Milestone:** Release 1 (S32). **Epic(s):** E-040. **Scope:** R1-F04; J-04.  
**Prerequisites:** S16 required evidence (TF-032); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-033 — Deliver the capability

State: Proposed. Persona: owner or admin. Estimate: 5 hours, provisional.  
Story: As a owner or admin, I want to manage projects that all permitted organisation members can use.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-033-T01: Implement project creation with stable organisation-unique keys and PostgreSQL constraints.
- [ ] TF-033-T02: Build active project selection/listing with deterministic empty/loading/error states.
- [ ] TF-033-T03: Implement archive policy that preserves history and blocks new work in archived projects.

Acceptance:

- Given an active organisation, when a project is created, then members can select it by its stable key.
- Given a duplicate key, archived target or cross-tenant request, when a prohibited operation is attempted, then no invalid state is persisted.

Tests: Concurrent key uniqueness, archive and browser tenant tests. Run the focused checks while implementing; TF-034 records the integrated acceptance.  
Demo: Create a project, inspect it as a member, then archive it.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-034 — Verify create and archive projects

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-033. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-034-T01: Execute and record: Concurrent key uniqueness, archive and browser tenant tests.
- [ ] TF-034-T02: Demonstrate: Create a project, inspect it as a member, then archive it. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Create a project, inspect it as a member, then archive it. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s18"></a>

### S18 — Capture issues with stable keys

**Goal:** Create and inspect valid work with stable project issue keys.  
**Milestone:** Release 1 (S32). **Epic(s):** E-050. **Scope:** R1-F05; J-05.  
**Prerequisites:** S17 required evidence (TF-034); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-035 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to create and inspect valid work with stable project issue keys.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-035-T01: Implement database-backed issue numbering and title/description/reporter/priority/initial-state rules.
- [ ] TF-035-T02: Add create/detail API contracts and accessible browser forms.
- [ ] TF-035-T03: Define idempotent create-command behaviour for later client retries and audit integration.

Acceptance:

- Given concurrent valid creates in an active project, when they commit, then each receives a unique monotonically allocated key and can be reopened.
- Given invalid input, an archived project or conflicting idempotency reuse, when creation is attempted, then no invalid/duplicate issue is accepted.

Tests: PostgreSQL concurrency, validation/idempotency and browser reload tests. Run the focused checks while implementing; TF-036 records the integrated acceptance.  
Demo: Create work concurrently and reopen one item by its key.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-036 — Verify capture issues with stable keys

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-035. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-036-T01: Execute and record: PostgreSQL concurrency, validation/idempotency and browser reload tests.
- [ ] TF-036-T02: Demonstrate: Create work concurrently and reopen one item by its key. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Create work concurrently and reopen one item by its key. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s19"></a>

### S19 — Organise epics, stories, tasks and bugs

**Goal:** Navigate a valid work hierarchy without confusing parent and child progress.  
**Milestone:** Release 1 (S32). **Epic(s):** E-150. **Scope:** R1-F12; J-12.  
**Prerequisites:** S18 required evidence (TF-036); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-037 — Deliver the capability

State: Proposed. Persona: team member. Estimate: 5 hours, provisional.  
Story: As a team member, I want to navigate a valid work hierarchy without confusing parent and child progress.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-037-T01: Add work types and permitted same-project parent relationships with cycle protection.
- [ ] TF-037-T02: Build epic/story/task navigation and a default story/bug board with explicit child-task views.
- [ ] TF-037-T03: Implement type-aware progress counts and explicit story completion rather than automatic child roll-up acceptance.

Acceptance:

- Given an epic with a story and tasks plus a standalone bug, when reopened, then relationships and type-labelled progress are preserved.
- Given a cycle, wrong parent type or foreign-project parent, when saved, then the hierarchy remains unchanged.

Tests: Hierarchy constraint, archive/orphan, progress-count and browser navigation tests. Run the focused checks while implementing; TF-038 records the integrated acceptance.  
Demo: Navigate epic to task and show a rejected invalid parent.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-038 — Verify organise epics, stories, tasks and bugs

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-037. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-038-T01: Execute and record: Hierarchy constraint, archive/orphan, progress-count and browser navigation tests.
- [ ] TF-038-T02: Demonstrate: Navigate epic to task and show a rejected invalid parent. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Navigate epic to task and show a rejected invalid parent. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s20"></a>

### S20 — Plan and close fourteen-day sprints

**Goal:** Plan work in project sprints with explicit carryover.  
**Milestone:** Release 1 (S32). **Epic(s):** E-150. **Scope:** R1-F13; J-13.  
**Prerequisites:** S19 required evidence (TF-038); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-039 — Deliver the capability

State: Proposed. Persona: team lead. Estimate: 5 hours, provisional.  
Story: As a team lead, I want to plan work in project sprints with explicit carryover.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-039-T01: Implement planned/active/closed sprints, 14-calendar-day dates and one-active-sprint constraints.
- [ ] TF-039-T02: Allow stories/bugs to be assigned; tasks inherit their story's sprint and epics span sprints.
- [ ] TF-039-T03: Build start/close workflows that snapshot results and explicitly return or carry unfinished work.

Acceptance:

- Given planned work, when the sprint starts and closes, then dates, inherited task assignment and completion/carryover history are correct.
- Given concurrent closure, a second active sprint or a conflicting task assignment, when submitted, then no work or history is silently lost.

Tests: Date/state/concurrency API tests and browser planning/close journeys. Run the focused checks while implementing; TF-040 records the integrated acceptance.  
Demo: Close a sprint with unfinished work and show its preserved history.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-040 — Verify plan and close fourteen-day sprints

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-039. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-040-T01: Execute and record: Date/state/concurrency API tests and browser planning/close journeys.
- [ ] TF-040-T02: Demonstrate: Close a sprint with unfinished work and show its preserved history. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Close a sprint with unfinished work and show its preserved history. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s21"></a>

### S21 — Manage the fixed Kanban workflow

**Goal:** See and move active work through its permitted workflow.  
**Milestone:** Release 1 (S32). **Epic(s):** E-050. **Scope:** R1-F06/R1-F07; J-06.  
**Prerequisites:** S20 required evidence (TF-040); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-041 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to see and move active work through its permitted workflow.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-041-T01: Implement bounded column/card queries with deterministic ordering and work-type filters.
- [ ] TF-041-T02: Enforce the fixed transition graph in the application service.
- [ ] TF-041-T03: Build board movement plus an accessible non-drag status control.

Acceptance:

- Given active story/bug work, when loaded and validly moved, then each card appears once in the persisted target column.
- Given an invalid transition or inaccessible issue, when movement is attempted, then issue state is unchanged.

Tests: Transition-policy, bounded-query and keyboard/browser movement tests. Run the focused checks while implementing; TF-042 records the integrated acceptance.  
Demo: Move an issue through a valid path and reject an invalid move.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-042 — Verify manage the fixed kanban workflow

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-041. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-042-T01: Execute and record: Transition-policy, bounded-query and keyboard/browser movement tests.
- [ ] TF-042-T02: Demonstrate: Move an issue through a valid path and reject an invalid move. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Move an issue through a valid path and reject an invalid move. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s22"></a>

### S22 — Recover from concurrent edits and failures

**Goal:** Recover safely when another edit or a failed request changes the expected state.  
**Milestone:** Release 1 (S32). **Epic(s):** E-050. **Scope:** R1-F05/R1-F06/R1-F07; J-09.  
**Prerequisites:** S21 required evidence (TF-042); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-043 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to recover safely when another edit or a failed request changes the expected state.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-043-T01: Add optimistic-version checks to work edits, transitions and archive operations.
- [ ] TF-043-T02: Return consistent conflicts and implement UI refresh/reapply/revert behaviour.
- [ ] TF-043-T03: Complete archive views and retain prior issue/project history.

Acceptance:

- Given a stale editor, when it saves, then the newer committed state survives and the user can refresh and intentionally reapply.
- Given a failed optimistic move or unauthorised archive, when the response returns, then the board restores persisted truth without erasing history.

Tests: Two-editor concurrency, transport-failure and archive browser/API cases. Run the focused checks while implementing; TF-044 records the integrated acceptance.  
Demo: Reproduce a conflict between two views and recover.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-044 — Verify recover from concurrent edits and failures

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-043. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-044-T01: Execute and record: Two-editor concurrency, transport-failure and archive browser/API cases.
- [ ] TF-044-T02: Demonstrate: Reproduce a conflict between two views and recover. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Reproduce a conflict between two views and recover. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s23"></a>

### S23 — Assign, prioritise and label work

**Goal:** Add correct ownership and classification to permitted work.  
**Milestone:** Release 1 (S32). **Epic(s):** E-060. **Scope:** R1-F08; J-07.  
**Prerequisites:** S22 required evidence (TF-044); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-045 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to add correct ownership and classification to permitted work.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-045-T01: Implement eligible same-organisation assignee selection and priority changes.
- [ ] TF-045-T02: Implement project-label creation/lifecycle and issue apply/remove operations.
- [ ] TF-045-T03: Expose the controls in board/detail with consistent validation and pending/failure states.

Acceptance:

- Given an eligible member and project label, when applied, then assignment, priority and labels persist and display correctly.
- Given a foreign-tenant assignee/label or removed reference, when applied, then invalid metadata is rejected without partial updates.

Tests: Constraint/tenant/service tests and browser add/remove cases. Run the focused checks while implementing; TF-046 records the integrated acceptance.  
Demo: Assign work, change priority and apply/remove a label.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-046 — Verify assign, prioritise and label work

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-045. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-046-T01: Execute and record: Constraint/tenant/service tests and browser add/remove cases.
- [ ] TF-046-T02: Demonstrate: Assign work, change priority and apply/remove a label. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Assign work, change priority and apply/remove a label. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s24"></a>

### S24 — Discuss work and audit accepted changes

**Goal:** Retain discussion and a reliable history of important changes.  
**Milestone:** Release 1 (S32). **Epic(s):** E-060. **Scope:** R1-F09/R1-F10; J-07.  
**Prerequisites:** S23 required evidence (TF-046); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-047 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to retain discussion and a reliable history of important changes.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-047-T01: Implement validated comments with an explicit retry/deduplication contract and author/time display.
- [ ] TF-047-T02: Write append-only audit entries atomically with creation and required issue mutations, including earlier slices.
- [ ] TF-047-T03: Build pending/failed/retry comment states and the ordered history view.

Acceptance:

- Given a successful mutation or retried comment, when the issue is reloaded, then the intended result appears once with correct actor/time history.
- Given a rejected mutation or audit-write failure, when the operation ends, then there is no misleading success entry or unaudited accepted change.

Tests: Transaction rollback, comment retry and append-only audit API/browser tests. Run the focused checks while implementing; TF-048 records the integrated acceptance.  
Demo: Retry a comment and compare successful versus rejected change history.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-048 — Verify discuss work and audit accepted changes

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-047. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-048-T01: Execute and record: Transaction rollback, comment retry and append-only audit API/browser tests.
- [ ] TF-048-T02: Demonstrate: Retry a comment and compare successful versus rejected change history. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Retry a comment and compare successful versus rejected change history. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s25"></a>

### S25 — Filter work and page through history

**Goal:** Narrow work and inspect history with predictable bounded loading.  
**Milestone:** Release 1 (S32). **Epic(s):** E-060. **Scope:** R1-F08/R1-F10; J-08.  
**Prerequisites:** S24 required evidence (TF-048); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-049 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to narrow work and inspect history with predictable bounded loading.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-049-T01: Implement intersecting status/priority/assignee/label filters and type-aware queries.
- [ ] TF-049-T02: Review indexes, stable ordering and bounded board/history pagination.
- [ ] TF-049-T03: Build empty-versus-failure states and preserve selected filters across safe refreshes.

Acceptance:

- Given combined filters and multiple pages, when queried, then only the matching permitted work/history is returned in deterministic order.
- Given a crafted foreign identifier, empty result or failed next page, when loaded, then tenancy holds and the UI distinguishes each outcome.

Tests: Repository/query-plan, pagination and browser filter/history cases. Run the focused checks while implementing; TF-050 records the integrated acceptance.  
Demo: Filter by several criteria and page through one issue's history.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-050 — Verify filter work and page through history

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-049. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-050-T01: Execute and record: Repository/query-plan, pagination and browser filter/history cases.
- [ ] TF-050-T02: Demonstrate: Filter by several criteria and page through one issue's history. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Filter by several criteria and page through one issue's history. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s26"></a>

### S26 — Sign in with Cognito

**Goal:** Access the product with real identity and predictable session handling.  
**Milestone:** Release 1 (S32). **Epic(s):** E-080. **Scope:** R1-F01; J-01–J-13.  
**Prerequisites:** S25 required evidence (TF-050); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-051 — Deliver the capability

State: Proposed. Persona: user. Estimate: 5 hours, provisional.  
Story: As a user, I want to access the product with real identity and predictable session handling.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-051-T01: Configure the selected Cognito/OIDC flow and private callback/TLS requirements.
- [ ] TF-051-T02: Implement backend token validation and idempotent external-identity/profile mapping.
- [ ] TF-051-T03: Implement Angular sign-in/out, expiry, route protection and release-profile rejection of development identity.

Acceptance:

- Given valid identity, when signed in, then the correct profile and permitted product routes are available.
- Given missing/expired/invalid identity or release-profile development credentials, when protected operations are attempted, then access is denied safely.

Tests: Resource-server claim/profile tests and browser login/logout/expiry cases. Run the focused checks while implementing; TF-052 records the integrated acceptance.  
Demo: Sign in, use the board, sign out and repeat a protected request.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-052 — Verify sign in with cognito

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-051. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-052-T01: Execute and record: Resource-server claim/profile tests and browser login/logout/expiry cases.
- [ ] TF-052-T02: Demonstrate: Sign in, use the board, sign out and repeat a protected request. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Sign in, use the board, sign out and repeat a protected request. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s27"></a>

### S27 — Verify roles and tenant isolation

**Goal:** Prove every work-management operation enforces its actor and tenant rules.  
**Milestone:** Release 1 (S32). **Epic(s):** E-080. **Scope:** R1-F01–R1-F13; J-10.  
**Prerequisites:** S26 required evidence (TF-052); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-053 — Deliver the capability

State: Proposed. Persona: security reviewer. Estimate: 5 hours, provisional.  
Story: As a security reviewer, I want to prove every work-management operation enforces its actor and tenant rules.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-053-T01: Complete owner/admin/member service-boundary permissions, including sprint administration.
- [ ] TF-053-T02: Parameterise negative access cases for every resource, relationship and invitation route.
- [ ] TF-053-T03: Review CORS, headers, identity storage, secret/log handling and release configuration.

Acceptance:

- Given the role matrix, when each persona exercises its allowed action, then server and UI agree on the outcome.
- Given guessed IDs, role escalation or mismatched invitation identity, when called directly, then no protected data or mutation is exposed.

Tests: Complete resource/role/IDOR matrix and security configuration review. Run the focused checks while implementing; TF-054 records the integrated acceptance.  
Demo: Run the matrix and demonstrate a crafted direct-call denial.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-054 — Verify verify roles and tenant isolation

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-053. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-054-T01: Execute and record: Complete resource/role/IDOR matrix and security configuration review.
- [ ] TF-054-T02: Demonstrate: Run the matrix and demonstrate a crafted direct-call denial. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Run the matrix and demonstrate a crafted direct-call denial. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s28"></a>

### S28 — Accept the complete board in private UAT

**Goal:** Demonstrate the full authenticated work-management journey on the PC.  
**Milestone:** Release 1 (S32). **Epic(s):** E-070. **Scope:** R1-F01–R1-F13; J-01–J-13.  
**Prerequisites:** S27 required evidence (TF-054); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-055 — Deliver the capability

State: Proposed. Persona: product reviewer. Estimate: 5 hours, provisional.  
Story: As a product reviewer, I want to demonstrate the full authenticated work-management journey on the PC.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-055-T01: Create deterministic non-sensitive persona/work/hierarchy/sprint fixtures.
- [ ] TF-055-T02: Run all board journeys, including both invitation routes, carryover and concurrent-edit recovery.
- [ ] TF-055-T03: Record defects, running artifacts and acceptance evidence without declaring a release prematurely.

Acceptance:

- Given the integrated UAT artifact, when J-01–J-13 are exercised, then every required journey has reproducible evidence.
- Given a failed journey or missing evidence, when acceptance is reviewed, then its defect remains open and release readiness is not claimed.

Tests: End-to-end persona UAT and critical regression checks. Run the focused checks while implementing; TF-056 records the integrated acceptance.  
Demo: Run organisation-to-sprint-completion with a second tenant.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-056 — Verify accept the complete board in private uat

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-055. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-056-T01: Execute and record: End-to-end persona UAT and critical regression checks.
- [ ] TF-056-T02: Demonstrate: Run organisation-to-sprint-completion with a second tenant. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Run organisation-to-sprint-completion with a second tenant. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s29"></a>

### S29 — Verify accessibility and regression

**Goal:** Complete critical journeys using supported browsers and keyboard controls.  
**Milestone:** Release 1 (S32). **Epic(s):** E-090. **Scope:** Release 1 quality attributes.  
**Prerequisites:** S28 required evidence (TF-056); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-057 — Deliver the capability

State: Proposed. Persona: user. Estimate: 5 hours, provisional.  
Story: As a user, I want to complete critical journeys using supported browsers and keyboard controls.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-057-T01: Complete browser/API/contract regression coverage for agreed critical paths.
- [ ] TF-057-T02: Check focus, semantics, keyboard status changes and target responsive layouts.
- [ ] TF-057-T03: Record and resolve accessibility/compatibility defects against the acceptance target.

Acceptance:

- Given supported browsers/viewports and keyboard input, when critical journeys run, then they remain usable and understandable.
- Given validation, permission or loading failure, when shown, then focus/status feedback remains accessible and state is recoverable.

Tests: Browser matrix, manual keyboard review and contract regression. Run the focused checks while implementing; TF-058 records the integrated acceptance.  
Demo: Complete board and sprint actions without drag/pointer dependence.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-058 — Verify verify accessibility and regression

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-057. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-058-T01: Execute and record: Browser matrix, manual keyboard review and contract regression.
- [ ] TF-058-T02: Demonstrate: Complete board and sprint actions without drag/pointer dependence. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Complete board and sprint actions without drag/pointer dependence. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s30"></a>

### S30 — Diagnose and measure the application

**Goal:** Identify failures and measured capacity of the current application.  
**Milestone:** Release 1 (S32). **Epic(s):** E-090. **Scope:** R1-F11; Q-01/Q-02/Q-09.  
**Prerequisites:** S29 required evidence (TF-058); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-059 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to identify failures and measured capacity of the current application.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-059-T01: Add safe structured correlation, version/health and application/database metrics.
- [ ] TF-059-T02: Measure query plans and a reproducible home-lab load profile against candidate targets.
- [ ] TF-059-T03: Document bottlenecks and resource/latency limits using observed results.

Acceptance:

- Given a known request or controlled failure, when inspected, then the operator can correlate it to logs/metrics and the running version.
- Given a missed target or missing signal, when results are recorded, then it is reported as a defect/limit rather than a passing performance claim.

Tests: Seeded load/query-plan tests, correlation and controlled failure inspection. Run the focused checks while implementing; TF-060 records the integrated acceptance.  
Demo: Find a failed request and show the measured performance report.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-060 — Verify diagnose and measure the application

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-059. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-060-T01: Execute and record: Seeded load/query-plan tests, correlation and controlled failure inspection.
- [ ] TF-060-T02: Demonstrate: Find a failed request and show the measured performance report. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Find a failed request and show the measured performance report. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s31"></a>

### S31 — Rehearse Release 1 recovery

**Goal:** Recover the release candidate without untested migration assumptions.  
**Milestone:** Release 1 (S32). **Epic(s):** E-090. **Scope:** R1-F11; J-11; Q-06–Q-10.  
**Prerequisites:** S30 required evidence (TF-060); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-061 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to recover the release candidate without untested migration assumptions.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-061-T01: Verify automated off-host backups and restore full work/hierarchy/sprint fixtures into clean storage.
- [ ] TF-061-T02: Rehearse candidate restart, graceful shutdown and compatible artifact rollback.
- [ ] TF-061-T03: Complete deployment/restore/incident runbooks and record actual RPO/RTO evidence.

Acceptance:

- Given a candidate backup and compatible artifacts, when recovery runs, then identity/work/history relationships and version are verified.
- Given incompatible schema or corrupt/missing backup, when recovery is attempted, then the procedure reports the failure and follows its contingency.

Tests: Timed restore, compatibility/rollback, backup-failure and shutdown checks. Run the focused checks while implementing; TF-062 records the integrated acceptance.  
Demo: Restore into an isolated database and verify the same board.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-062 — Verify rehearse release 1 recovery

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-061. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-062-T01: Execute and record: Timed restore, compatibility/rollback, backup-failure and shutdown checks.
- [ ] TF-062-T02: Demonstrate: Restore into an isolated database and verify the same board. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Restore into an isolated database and verify the same board. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s32"></a>

### S32 — Release the complete work-management product

**Goal:** Promote the exact accepted Release 1 artifact.  
**Milestone:** Release 1 (S32). **Epic(s):** E-100. **Scope:** Release 1 acceptance gate.  
**Prerequisites:** S31 required evidence (TF-062); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-063 — Deliver the capability

State: Proposed. Persona: product owner. Estimate: 5 hours, provisional.  
Story: As a product owner, I want to promote the exact accepted Release 1 artifact.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-063-T01: Freeze R1 scope/candidate identifiers and collect J-01–J-13 plus security/quality/recovery evidence.
- [ ] TF-063-T02: Publish release notes, user/API guidance, known limitations and operator handoff.
- [ ] TF-063-T03: Record go/no-go, promote approved digests without rebuild and smoke-test running versions.

Acceptance:

- Given passing candidate evidence and a go decision, when promoted, then the release artifact matches UAT and critical journeys still work.
- Given a failed gate or different digest, when promotion is reviewed, then release acceptance is withheld.

Tests: Release evidence review, exact-digest comparison and post-promotion smoke. Run the focused checks while implementing; TF-064 records the integrated acceptance.  
Demo: Present Release 1 evidence and the authenticated running board.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-064 — Verify release the complete work-management product

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-063. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-064-T01: Execute and record: Release evidence review, exact-digest comparison and post-promotion smoke.
- [ ] TF-064-T02: Demonstrate: Present Release 1 evidence and the authenticated running board. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Present Release 1 evidence and the authenticated running board. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s33"></a>

### S33 — Stabilise Release 1 and connect a repository

**Goal:** Connect an authorised GitHub repository while supporting the released board.  
**Milestone:** Release 2 (S56). **Epic(s):** E-110, E-160. **Scope:** R2-F01.  
**Prerequisites:** S32 required evidence (TF-064); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-065 — Deliver the capability

State: Proposed. Persona: owner or admin. Estimate: 5 hours, provisional.  
Story: As a owner or admin, I want to connect an authorised GitHub repository while supporting the released board.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-065-T01: Review Release 1 defects/backup health and give release defects priority over new integration work.
- [ ] TF-065-T02: Register an organisation-bound repository with private credential references and minimum read permissions.
- [ ] TF-065-T03: Build connection status, revocation and last-successful-sync UI with audit records.

Acceptance:

- Given stable Release 1 and a permitted repository, when connected, then its organisation binding and connection status are visible.
- Given revoked credentials or another tenant's repository binding, when used, then access fails safely without disturbing the board.

Tests: Connection authorization, credential-redaction and Release 1 smoke tests. Run the focused checks while implementing; TF-066 records the integrated acceptance.  
Demo: Connect a repository, revoke access and inspect the visible failure.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-066 — Verify stabilise release 1 and connect a repository

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-065. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-066-T01: Execute and record: Connection authorization, credential-redaction and Release 1 smoke tests.
- [ ] TF-066-T02: Demonstrate: Connect a repository, revoke access and inspect the visible failure. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Connect a repository, revoke access and inspect the visible failure. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s34"></a>

### S34 — Catalogue services and environments

**Goal:** Identify which application services run in each permitted environment.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F02.  
**Prerequisites:** S33 required evidence (TF-066); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-067 — Deliver the capability

State: Proposed. Persona: team member. Estimate: 5 hours, provisional.  
Story: As a team member, I want to identify which application services run in each permitted environment.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-067-T01: Define organisation-owned service/environment identities and project/repository relationships.
- [ ] TF-067-T02: Build catalogue create/read/update/archive contracts with owner/admin configuration controls.
- [ ] TF-067-T03: Show members the permitted targets without exposing host execution credentials.

Acceptance:

- Given two services and UAT/release environments, when opened, then their stable identities and ownership are clear.
- Given cross-tenant references or archived targets, when configured or queried, then invalid associations are rejected.

Tests: Catalogue relationship/tenant constraints and UI configuration/read tests. Run the focused checks while implementing; TF-068 records the integrated acceptance.  
Demo: Inspect two services across UAT and release.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-068 — Verify catalogue services and environments

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-067. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-068-T01: Execute and record: Catalogue relationship/tenant constraints and UI configuration/read tests.
- [ ] TF-068-T02: Demonstrate: Inspect two services across UAT and release. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Inspect two services across UAT and release. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s35"></a>

### S35 — Link work explicitly to pull requests

**Goal:** Connect a story, task or bug to the changes intended to deliver it.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F03.  
**Prerequisites:** S34 required evidence (TF-068); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-069 — Deliver the capability

State: Proposed. Persona: developer. Estimate: 5 hours, provisional.  
Story: As a developer, I want to connect a story, task or bug to the changes intended to deliver it.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-069-T01: Add many-to-many work/PR links validated against registered repositories.
- [ ] TF-069-T02: Build attach/remove flows and audit link changes; treat parsed keys only as suggestions.
- [ ] TF-069-T03: Show link provenance and unresolved references on work detail.

Acceptance:

- Given a permitted work item and PR, when linked, then both views show the explicit association and its actor.
- Given an unrelated repository or a guessed branch/title key, when observed, then no unverified delivery association is asserted.

Tests: Relationship, permission, suggestion-versus-confirmation and browser tests. Run the focused checks while implementing; TF-070 records the integrated acceptance.  
Demo: Link multiple PRs to a story and one PR to multiple work items.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-070 — Verify link work explicitly to pull requests

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-069. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-070-T01: Execute and record: Relationship, permission, suggestion-versus-confirmation and browser tests.
- [ ] TF-070-T02: Demonstrate: Link multiple PRs to a story and one PR to multiple work items. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Link multiple PRs to a story and one PR to multiple work items. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s36"></a>

### S36 — Synchronise GitHub builds and freshness

**Goal:** See actual PR/build attempts with clear sync freshness.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F04.  
**Prerequisites:** S35 required evidence (TF-070); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-071 — Deliver the capability

State: Proposed. Persona: developer. Estimate: 5 hours, provisional.  
Story: As a developer, I want to see actual PR/build attempts with clear sync freshness.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-071-T01: Implement outbound authenticated polling with conditional requests, pagination and stored cursors.
- [ ] TF-071-T02: Map PR/build attempts/results and external build-log links without deriving deployment success.
- [ ] TF-071-T03: Build fresh/stale/disconnected states and provider rate-limit/backoff handling.

Acceptance:

- Given permitted GitHub data, when synchronised, then build attempts and results reflect the provider with last-observed timestamps.
- Given rate limiting, unchanged responses or expired credentials, when polling, then it backs off/deduplicates and never invents fresh success.

Tests: Provider contract fixtures for pagination/304/rate limits and UI freshness tests. Run the focused checks while implementing; TF-072 records the integrated acceptance.  
Demo: Observe a passing and failing build while deployment remains unconfirmed.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-072 — Verify synchronise github builds and freshness

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-071. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-072-T01: Execute and record: Provider contract fixtures for pagination/304/rate limits and UI freshness tests.
- [ ] TF-072-T02: Demonstrate: Observe a passing and failing build while deployment remains unconfirmed. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Observe a passing and failing build while deployment remains unconfirmed. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s37"></a>

### S37 — Record immutable artifact contents

**Goal:** Know exactly which work is evidenced inside an artifact.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F05.  
**Prerequisites:** S36 required evidence (TF-072); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-073 — Deliver the capability

State: Proposed. Persona: release operator. Estimate: 5 hours, provisional.  
Story: As a release operator, I want to know exactly which work is evidenced inside an artifact.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-073-T01: Define a versioned manifest linking artifact digest, source commit, build attempt and included PR/work references.
- [ ] TF-073-T02: Have the external build/release path supply the manifest with completeness/provenance.
- [ ] TF-073-T03: Persist immutable manifests and audited corrections without rewriting history when links later change.

Acceptance:

- Given an artifact and complete manifest, when inspected, then its exact digest/build/commit and included work are reproducible.
- Given missing/conflicting manifest data or later PR-link edits, when viewed, then uncertainty/corrections are explicit and original evidence remains.

Tests: Manifest schema, immutability, conflicting-digest and link-change tests. Run the focused checks while implementing; TF-074 records the integrated acceptance.  
Demo: Inspect one artifact containing two stories and an incomplete artifact.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-074 — Verify record immutable artifact contents

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-073. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-074-T01: Execute and record: Manifest schema, immutability, conflicting-digest and link-change tests.
- [ ] TF-074-T02: Demonstrate: Inspect one artifact containing two stories and an incomplete artifact. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Inspect one artifact containing two stories and an incomplete artifact. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s38"></a>

### S38 — Receive durable deployment receipts

**Goal:** Report real deployment attempts even when TaskForge is temporarily down.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F06.  
**Prerequisites:** S37 required evidence (TF-074); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-075 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to report real deployment attempts even when TaskForge is temporarily down.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-075-T01: Register restricted machine-producer credentials and validate receipt identity/schema.
- [ ] TF-075-T02: Add durable external-script spooling with event/attempt IDs, artifact digest, target, times and observed outcome.
- [ ] TF-075-T03: Implement authenticated idempotent ingestion and acknowledgment after durable acceptance.

Acceptance:

- Given a script-verified running artifact, when its receipt is accepted, then the exact attempt/service/environment/outcome is recorded once.
- Given duplicate/conflicting IDs, unauthorised targets or TaskForge downtime, when reporting, then duplicates are safe, invalid input rejected and undelivered receipts retained.

Tests: Machine-auth, concurrent duplicate, conflicting payload and outage/replay integration tests. Run the focused checks while implementing; TF-076 records the integrated acceptance.  
Demo: Stop TaskForge, deploy privately, then replay the stored receipt.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-076 — Verify receive durable deployment receipts

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-075. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-076-T01: Execute and record: Machine-auth, concurrent duplicate, conflicting payload and outage/replay integration tests.
- [ ] TF-076-T02: Demonstrate: Stop TaskForge, deploy privately, then replay the stored receipt. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Stop TaskForge, deploy privately, then replay the stored receipt. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s39"></a>

### S39 — Show environment deployment history

**Goal:** See deployment attempts and the last confirmed running version.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F07.  
**Prerequisites:** S38 required evidence (TF-076); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-077 — Deliver the capability

State: Proposed. Persona: team member. Estimate: 5 hours, provisional.  
Story: As a team member, I want to see deployment attempts and the last confirmed running version.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-077-T01: Project accepted receipt events into ordered per-service/environment history.
- [ ] TF-077-T02: Resolve last confirmed active artifact using attempt/event semantics rather than arrival order.
- [ ] TF-077-T03: Build timeline/current-version views with pending, failed, partial and unknown outcomes.

Acceptance:

- Given a successful deployment followed by a pending/failed attempt, when the environment opens, then its prior confirmed version remains identifiable.
- Given incomplete or contradictory observations, when projecting state, then ambiguity is visible instead of asserting a new active version.

Tests: Projection ordering, failed-attempt and browser state tests. Run the focused checks while implementing; TF-078 records the integrated acceptance.  
Demo: Show history with success, failure and an unresolved attempt.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-078 — Verify show environment deployment history

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-077. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-078-T01: Execute and record: Projection ordering, failed-attempt and browser state tests.
- [ ] TF-078-T02: Demonstrate: Show history with success, failure and an unresolved attempt. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Show history with success, failure and an unresolved attempt. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s40"></a>

### S40 — Trace work across services and environments

**Goal:** Follow a work item to every evidenced delivery target.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F08; R2-J01/R2-J02.  
**Prerequisites:** S39 required evidence (TF-078); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-079 — Deliver the capability

State: Proposed. Persona: developer. Estimate: 5 hours, provisional.  
Story: As a developer, I want to follow a work item to every evidenced delivery target.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-079-T01: Join explicit work/PR links, immutable manifests, build references and deployment history.
- [ ] TF-079-T02: Expose a bounded work-delivery read contract with provenance and completeness per target.
- [ ] TF-079-T03: Build story/task delivery timeline showing multiple services/environments and partial delivery.

Acceptance:

- Given a story in two artifacts deployed to different targets, when inspected, then each target has its own evidence and outcome.
- Given one successful target and missing evidence for another, when summarised, then the story is not globally claimed fully deployed.

Tests: Many-to-many attribution, incomplete mapping, tenant and browser journey tests. Run the focused checks while implementing; TF-080 records the integrated acceptance.  
Demo: Trace a story from PRs through builds to UAT and release targets.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-080 — Verify trace work across services and environments

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-079. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-080-T01: Execute and record: Many-to-many attribution, incomplete mapping, tenant and browser journey tests.
- [ ] TF-080-T02: Demonstrate: Trace a story from PRs through builds to UAT and release targets. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Trace a story from PRs through builds to UAT and release targets. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s41"></a>

### S41 — Represent failure and rollback honestly

**Goal:** Understand failed rollouts and operator-executed rollbacks.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F09; R2-J04.  
**Prerequisites:** S40 required evidence (TF-080); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-081 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to understand failed rollouts and operator-executed rollbacks.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-081-T01: Define failed/partial rollout states and reference the attempt/artifact being replaced.
- [ ] TF-081-T02: Record rollback as a new external deployment of a previous digest with complete history.
- [ ] TF-081-T03: Build UI showing actual observed version and related work without changing work completion.

Acceptance:

- Given an operator rollback, when its verified receipt arrives, then the prior digest becomes the confirmed version through a new attempt.
- Given a failed or partial rollback, when inspected, then historical attempts remain and no recovered-health claim is inferred from the command alone.

Tests: Rollback projection, partial result and status-separation tests. Run the focused checks while implementing; TF-082 records the integrated acceptance.  
Demo: Fail a deployment and show a separately recorded rollback.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-082 — Verify represent failure and rollback honestly

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-081. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-082-T01: Execute and record: Rollback projection, partial result and status-separation tests.
- [ ] TF-082-T02: Demonstrate: Fail a deployment and show a separately recorded rollback. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Fail a deployment and show a separately recorded rollback. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s42"></a>

### S42 — Reconcile delayed and missing delivery events

**Goal:** Restore trustworthy delivery state after duplicate, missing or late data.  
**Milestone:** Release 2 (S56). **Epic(s):** E-160. **Scope:** R2-F10; R2-J06.  
**Prerequisites:** S41 required evidence (TF-082); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-083 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to restore trustworthy delivery state after duplicate, missing or late data.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-083-T01: Add idempotent processing and explicit per-attempt ordering/conflict handling.
- [ ] TF-083-T02: Implement replay/reconciliation checkpoints and bounded operator-visible failure records.
- [ ] TF-083-T03: Recover from missing receipt/source intervals without allowing older arrivals to overwrite newer evidence.

Acceptance:

- Given shuffled/repeated events, when replay completes, then history/current projections converge to the same correct state.
- Given conflicting terminal events or unfillable gaps, when reconciled, then the conflict/gap remains visible and cannot silently regress the active version.

Tests: Permutation/replay, missing-interval, conflict and restored-database integration tests. Run the focused checks while implementing; TF-084 records the integrated acceptance.  
Demo: Replay shuffled receipts and compare the reconstructed environment history.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-084 — Verify reconcile delayed and missing delivery events

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-083. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-084-T01: Execute and record: Permutation/replay, missing-interval, conflict and restored-database integration tests.
- [ ] TF-084-T02: Demonstrate: Replay shuffled receipts and compare the reconstructed environment history. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Replay shuffled receipts and compare the reconstructed environment history. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s43"></a>

### S43 — Correlate telemetry to deployment context

**Goal:** Select authorised runtime evidence for the relevant service and version.  
**Milestone:** Release 2 (S56). **Epic(s):** E-170. **Scope:** R2-F11.  
**Prerequisites:** S42 required evidence (TF-084); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-085 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to select authorised runtime evidence for the relevant service and version.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-085-T01: Define trusted source/service/environment/version/time/trace mappings with explicit source ownership.
- [ ] TF-085-T02: Instrument the demonstration service and collector with supported OpenTelemetry attributes.
- [ ] TF-085-T03: Resolve read context server-side and label version-unavailable data as broader service/environment evidence.

Acceptance:

- Given trusted correlation metadata, when a deployment is selected, then the intended service/version/time context is resolved.
- Given missing version or tenant attribution, when evidence is requested, then scope limitations or operator-only classification are explicit.

Tests: Collector fixture, context resolution, scope/freshness and cross-tenant tests. Run the focused checks while implementing; TF-086 records the integrated acceptance.  
Demo: Follow a known deployment into its available telemetry context.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-086 — Verify correlate telemetry to deployment context

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-085. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-086-T01: Execute and record: Collector fixture, context resolution, scope/freshness and cross-tenant tests.
- [ ] TF-086-T02: Demonstrate: Follow a known deployment into its available telemetry context. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Follow a known deployment into its available telemetry context. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s44"></a>

### S44 — Collect and protect application logs

**Goal:** Retain useful application logs with bounded storage and correct access.  
**Milestone:** Release 2 (S56). **Epic(s):** E-170. **Scope:** R2-F12; R2-J03.  
**Prerequisites:** S43 required evidence (TF-086); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-087 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to retain useful application logs with bounded storage and correct access.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-087-T01: Deploy Loki/collector privately and establish authenticated source/tenant boundaries.
- [ ] TF-087-T02: Configure structured collection, secret redaction, finite retention and source health.
- [ ] TF-087-T03: Separate shared host/control-plane streams from member-visible organisation application streams.

Acceptance:

- Given authorised application traffic, when collected, then useful redacted logs are available only in the intended source/tenant.
- Given an unpartitioned shared stream or embedded secret, when collected/exposed, then it cannot leak through an organisation member's view.

Tests: Collection/redaction fixtures, source-auth, retention and tenant-isolation tests. Run the focused checks while implementing; TF-088 records the integrated acceptance.  
Demo: Inspect authorised application logs and deny access to a shared host stream.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-088 — Verify collect and protect application logs

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-087. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-088-T01: Execute and record: Collection/redaction fixtures, source-auth, retention and tenant-isolation tests.
- [ ] TF-088-T02: Demonstrate: Inspect authorised application logs and deny access to a shared host stream. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Inspect authorised application logs and deny access to a shared host stream. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s45"></a>

### S45 — Query bounded deployment logs

**Goal:** Request only the permitted log window for an investigated deployment.  
**Milestone:** Release 2 (S56). **Epic(s):** E-170. **Scope:** R2-F12.  
**Prerequisites:** S44 required evidence (TF-088); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-089 — Deliver the capability

State: Proposed. Persona: member. Estimate: 5 hours, provisional.  
Story: As a member, I want to request only the permitted log window for an investigated deployment.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-089-T01: Define constrained severity/text/time/page filters and measured maximum budgets.
- [ ] TF-089-T02: Implement backend source authorization, query construction, timeout and output-redaction policy.
- [ ] TF-089-T03: Return rows plus effective scope, timestamps, continuation/truncation and source failure states.

Acceptance:

- Given an authorised deployment and bounded filters, when queried, then results stay inside the permitted tenant/service/time scope.
- Given raw query/URL injection, excessive window, expired retention or source timeout, when requested, then it is rejected or reported explicitly without widening access.

Tests: API boundary/injection, limit, timeout, retention and redaction tests. Run the focused checks while implementing; TF-090 records the integrated acceptance.  
Demo: Query a bounded deployment window and demonstrate a denied scope override.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-090 — Verify query bounded deployment logs

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-089. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-090-T01: Execute and record: API boundary/injection, limit, timeout, retention and redaction tests.
- [ ] TF-090-T02: Demonstrate: Query a bounded deployment window and demonstrate a denied scope override. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Query a bounded deployment window and demonstrate a denied scope override. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s46"></a>

### S46 — Investigate logs inside TaskForge

**Goal:** Read deployment logs and open deeper authorised investigation.  
**Milestone:** Release 2 (S56). **Epic(s):** E-170. **Scope:** R2-F12; R2-J03.  
**Prerequisites:** S45 required evidence (TF-090); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-091 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to read deployment logs and open deeper authorised investigation.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-091-T01: Build the read-only log viewer with filters, pagination and correlation fields.
- [ ] TF-091-T02: Show loading, empty, stale, expired, truncated and unavailable states distinctly.
- [ ] TF-091-T03: Add authorised Grafana links that preserve context without embedding secrets or bypassing destination access.

Acceptance:

- Given a permitted log query, when filters/pages change, then the viewer shows bounded results and context consistently.
- Given lost source access or failed next-page load, when displayed, then previous data is not falsely presented as fresh or expanded.

Tests: Component/browser query-state, keyboard, link and access-loss tests. Run the focused checks while implementing; TF-092 records the integrated acceptance.  
Demo: Open a story's deployment logs and follow its Grafana link.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-092 — Verify investigate logs inside taskforge

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-091. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-092-T01: Execute and record: Component/browser query-state, keyboard, link and access-loss tests.
- [ ] TF-092-T02: Demonstrate: Open a story's deployment logs and follow its Grafana link. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Open a story's deployment logs and follow its Grafana link. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s47"></a>

### S47 — Show measured deployment health

**Goal:** Understand measured application health and the limits of the evidence.  
**Milestone:** Release 2 (S56). **Epic(s):** E-170. **Scope:** R2-F13.  
**Prerequisites:** S46 required evidence (TF-092); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-093 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to understand measured application health and the limits of the evidence.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-093-T01: Query permitted Prometheus signals for availability, latency and errors using bounded server-built requests.
- [ ] TF-093-T02: Return observation time, coverage, scope and fresh/stale/unknown/unavailable states.
- [ ] TF-093-T03: Build health summaries that label service-level data and avoid treating no traffic as a successful measurement.

Acceptance:

- Given fresh scoped measurements, when health opens, then values, observation time and actual scope are visible.
- Given no traffic, missing metrics or stale source, when summarised, then the application is not automatically shown healthy.

Tests: Metric fixture, no-traffic, staleness, cross-tenant and browser tests. Run the focused checks while implementing; TF-094 records the integrated acceptance.  
Demo: Compare healthy measurements with an unavailable/no-traffic window.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-094 — Verify show measured deployment health

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-093. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-094-T01: Execute and record: Metric fixture, no-traffic, staleness, cross-tenant and browser tests.
- [ ] TF-094-T02: Demonstrate: Compare healthy measurements with an unavailable/no-traffic window. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Compare healthy measurements with an unavailable/no-traffic window. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s48"></a>

### S48 — Link logs to traces

**Goal:** Follow a permitted log's trace correlation into Tempo.  
**Milestone:** Release 2 (S56). **Epic(s):** E-170. **Scope:** R2-F14.  
**Prerequisites:** S47 required evidence (TF-094); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-095 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to follow a permitted log's trace correlation into Tempo.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-095-T01: Deploy/configure private Tempo and collector tracing with finite retention.
- [ ] TF-095-T02: Preserve trace IDs in logs and validate source/context for trace links.
- [ ] TF-095-T03: Provide Grafana/Tempo links and explicit absent/expired/unavailable trace outcomes.

Acceptance:

- Given a collected trace and permitted log, when followed, then the destination opens the same authorised trace context.
- Given expired or inaccessible trace data, when followed, then absence/denial is clear and no credential or foreign-tenant link is exposed.

Tests: Log/trace correlation, retention, destination access and browser-link tests. Run the focused checks while implementing; TF-096 records the integrated acceptance.  
Demo: Follow a failed request from its log to its trace.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-096 — Verify link logs to traces

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-095. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-096-T01: Execute and record: Log/trace correlation, retention, destination access and browser-link tests.
- [ ] TF-096-T02: Demonstrate: Follow a failed request from its log to its trace. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Follow a failed request from its log to its trace. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s49"></a>

### S49 — Receive and deduplicate alerts

**Goal:** See meaningful incoming operational alerts for permitted services.  
**Milestone:** Release 2 (S56). **Epic(s):** E-180. **Scope:** R2-F15.  
**Prerequisites:** S48 required evidence (TF-096); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-097 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to see meaningful incoming operational alerts for permitted services.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-097-T01: Define authenticated alert-source registration and trusted target mapping.
- [ ] TF-097-T02: Persist/deduplicate alert lifecycle events while retaining firing/resolved history.
- [ ] TF-097-T03: Build incoming alert views and quarantine unmapped/untrusted alerts for operator inspection.

Acceptance:

- Given repeated firing/resolved events for one alert, when ingested, then one consistent alert history appears.
- Given forged source identity or unknown tenant mapping, when delivered, then no member-visible incident/evidence is created automatically.

Tests: Alert authentication, deduplication, ordering and target-mapping tests. Run the focused checks while implementing; TF-098 records the integrated acceptance.  
Demo: Send repeated alert events and show one correctly resolved history.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-098 — Verify receive and deduplicate alerts

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-097. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-098-T01: Execute and record: Alert authentication, deduplication, ordering and target-mapping tests.
- [ ] TF-098-T02: Demonstrate: Send repeated alert events and show one correctly resolved history. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Send repeated alert events and show one correctly resolved history. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s50"></a>

### S50 — Manage an incident lifecycle

**Goal:** Record, acknowledge and resolve an organisation incident.  
**Milestone:** Release 2 (S56). **Epic(s):** E-180. **Scope:** R2-F16.  
**Prerequisites:** S49 required evidence (TF-098); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-099 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to record, acknowledge and resolve an organisation incident.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-099-T01: Define severity, state transitions, owner/evidence fields and audited actor/time history.
- [ ] TF-099-T02: Implement permitted incident create/read/acknowledge/resolve services and API.
- [ ] TF-099-T03: Build incident list/detail with validation, concurrency and failure feedback.

Acceptance:

- Given a permitted incident, when acknowledged and resolved, then its lifecycle and actor/time history remain available.
- Given a stale update, forbidden transition or foreign-tenant incident, when mutated, then existing state is preserved.

Tests: Lifecycle/concurrency/tenant API tests and browser incident journey. Run the focused checks while implementing; TF-100 records the integrated acceptance.  
Demo: Create an incident, acknowledge it and record its resolution.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-100 — Verify manage an incident lifecycle

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-099. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-100-T01: Execute and record: Lifecycle/concurrency/tenant API tests and browser incident journey.
- [ ] TF-100-T02: Demonstrate: Create an incident, acknowledge it and record its resolution. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Create an incident, acknowledge it and record its resolution. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s51"></a>

### S51 — Connect incidents to evidence and corrective work

**Goal:** Link an investigation to deployments and follow-up work without guessing cause.  
**Milestone:** Release 2 (S56). **Epic(s):** E-180. **Scope:** R2-F16; R2-J05.  
**Prerequisites:** S50 required evidence (TF-100); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-101 — Deliver the capability

State: Proposed. Persona: responder. Estimate: 5 hours, provisional.  
Story: As a responder, I want to link an investigation to deployments and follow-up work without guessing cause.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-101-T01: Add validated incident links to deployments, alerts, evidence references and corrective stories/tasks/bugs.
- [ ] TF-101-T02: Distinguish associated/suspected evidence from explicitly recorded investigation findings.
- [ ] TF-101-T03: Build navigation between incident, deployment and corrective work with audit of link changes.

Acceptance:

- Given authorised evidence and corrective work, when linked, then both directions preserve provenance and human findings.
- Given temporal coincidence or a foreign-tenant reference, when linking/summarising, then causation is not inferred and invalid references are denied.

Tests: Relationship provenance, permission and corrective-work browser tests. Run the focused checks while implementing; TF-102 records the integrated acceptance.  
Demo: Link an incident to a deployment and create a corrective task.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-102 — Verify connect incidents to evidence and corrective work

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-101. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-102-T01: Execute and record: Relationship provenance, permission and corrective-work browser tests.
- [ ] TF-102-T02: Demonstrate: Link an incident to a deployment and create a corrective task. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Link an incident to a deployment and create a corrective task. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s52"></a>

### S52 — Run a complete deployment investigation

**Goal:** Demonstrate delivery and investigation using a controlled failure.  
**Milestone:** Release 2 (S56). **Epic(s):** E-180. **Scope:** R2-J01–R2-J06.  
**Prerequisites:** S51 required evidence (TF-102); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-103 — Deliver the capability

State: Proposed. Persona: product reviewer. Estimate: 5 hours, provisional.  
Story: As a product reviewer, I want to demonstrate delivery and investigation using a controlled failure.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-103-T01: Prepare a non-sensitive demonstration release containing known work/PR/build/artifact links.
- [ ] TF-103-T02: Inject a reversible service failure and follow deployment, logs, health, traces and alert context.
- [ ] TF-103-T03: Record incident findings, external rollback/recovery and corrective work with honest scope labels.

Acceptance:

- Given the seeded release/failure, when investigated, then the reviewer can follow the complete evidence chain and its recovery.
- Given a missing correlation link or stale signal, when encountered, then the limitation is visible rather than fabricated.

Tests: Cross-surface browser/API UAT with controlled failure and recovery. Run the focused checks while implementing; TF-104 records the integrated acceptance.  
Demo: Demonstrate one story from deployment to incident and corrective work.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-104 — Verify run a complete deployment investigation

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-103. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-104-T01: Execute and record: Cross-surface browser/API UAT with controlled failure and recovery.
- [ ] TF-104-T02: Demonstrate: Demonstrate one story from deployment to incident and corrective work. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Demonstrate one story from deployment to incident and corrective work. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s53"></a>

### S53 — Recover connectors and queues after outages

**Goal:** Recover integrations without losing receipts or exhausting the PC.  
**Milestone:** Release 2 (S56). **Epic(s):** E-190. **Scope:** R2-F10/R2-F17; R2-J06.  
**Prerequisites:** S52 required evidence (TF-104); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-105 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to recover integrations without losing receipts or exhausting the PC.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-105-T01: Apply bounded queues, retry/backoff and dead-letter/inspection paths to integration workers.
- [ ] TF-105-T02: Exercise GitHub, telemetry and TaskForge outages plus credential expiry.
- [ ] TF-105-T03: Reconcile restored cursors and spooled receipts, prioritising board availability.

Acceptance:

- Given a temporary outage, when services recover, then durable events are processed once and freshness recovers visibly.
- Given prolonged failure or full queue/storage budget, when limits are reached, then backpressure/failure is visible without unbounded growth.

Tests: Outage, quota, expiry, queue exhaustion and replay fault-injection tests. Run the focused checks while implementing; TF-106 records the integrated acceptance.  
Demo: Disconnect sources, inspect backlog/limits and recover them.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-106 — Verify recover connectors and queues after outages

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-105. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-106-T01: Execute and record: Outage, quota, expiry, queue exhaustion and replay fault-injection tests.
- [ ] TF-106-T02: Demonstrate: Disconnect sources, inspect backlog/limits and recover them. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Disconnect sources, inspect backlog/limits and recover them. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s54"></a>

### S54 — Review operational data access and secrets

**Goal:** Prove operational evidence is safe for each role and tenant.  
**Milestone:** Release 2 (S56). **Epic(s):** E-190. **Scope:** R2-F17; R2 telemetry policy.  
**Prerequisites:** S53 required evidence (TF-106); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-107 — Deliver the capability

State: Proposed. Persona: security reviewer. Estimate: 5 hours, provisional.  
Story: As a security reviewer, I want to prove operational evidence is safe for each role and tenant.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-107-T01: Extend the resource/role matrix to connections, producers, artifacts, logs, metrics, traces, alerts and incidents.
- [ ] TF-107-T02: Review source allowlists, redirects, redaction and credential storage/rotation.
- [ ] TF-107-T03: Remediate findings and inspect audit/log outputs for leaked sensitive content.

Acceptance:

- Given each allowed persona/source, when queried, then only authorised redacted evidence is returned.
- Given crafted tenant/source/query parameters, revoked membership or secret-bearing logs, when requested, then bypass/leakage is prevented.

Tests: Cross-tenant, SSRF/query-injection, secret-redaction and permission-revocation checks. Run the focused checks while implementing; TF-108 records the integrated acceptance.  
Demo: Run the operational-access matrix and show resolved findings.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-108 — Verify review operational data access and secrets

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-107. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-108-T01: Execute and record: Cross-tenant, SSRF/query-injection, secret-redaction and permission-revocation checks.
- [ ] TF-108-T02: Demonstrate: Run the operational-access matrix and show resolved findings. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Run the operational-access matrix and show resolved findings. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s55"></a>

### S55 — Verify capacity, retention and full recovery

**Goal:** Restore the integrated product within recorded server limits.  
**Milestone:** Release 2 (S56). **Epic(s):** E-190. **Scope:** R2-F17; Q-01–Q-11.  
**Prerequisites:** S54 required evidence (TF-108); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-109 — Deliver the capability

State: Proposed. Persona: operator. Estimate: 5 hours, provisional.  
Story: As a operator, I want to restore the integrated product within recorded server limits.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-109-T01: Measure application/telemetry storage and workload against actual PC capacity; fix accepted finite budgets.
- [ ] TF-109-T02: Restore work/delivery/incident metadata and source configuration into an isolated environment.
- [ ] TF-109-T03: Replay receipts/reconcile providers and document effects of telemetry that has expired from retention.

Acceptance:

- Given retained backups and receipt/source evidence, when restored, then product relationships and current-state projections are correct within recorded recovery limits.
- Given expired raw telemetry or unavailable provider, when historical evidence opens, then it remains explicitly unavailable rather than reconstructed fiction.

Tests: Load/retention bounds, timed restoration, reconciliation and missing-evidence tests. Run the focused checks while implementing; TF-110 records the integrated acceptance.  
Demo: Restore a complete story/deployment/incident chain and show any retention gaps.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-110 — Verify verify capacity, retention and full recovery

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-109. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-110-T01: Execute and record: Load/retention bounds, timed restoration, reconciliation and missing-evidence tests.
- [ ] TF-110-T02: Demonstrate: Restore a complete story/deployment/incident chain and show any retention gaps. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Restore a complete story/deployment/incident chain and show any retention gaps. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s56"></a>

### S56 — Release deployment tracking and investigation

**Goal:** Promote the accepted operational-investigation product.  
**Milestone:** Release 2 (S56). **Epic(s):** E-190. **Scope:** Release 2 acceptance gate.  
**Prerequisites:** S55 required evidence (TF-110); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-111 — Deliver the capability

State: Proposed. Persona: product owner. Estimate: 5 hours, provisional.  
Story: As a product owner, I want to promote the accepted operational-investigation product.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-111-T01: Freeze candidate and collect R2 journeys plus R1 regression, security and recovery evidence.
- [ ] TF-111-T02: Publish user/integration/operator guides, source limits, compatibility and known limitations.
- [ ] TF-111-T03: Record go/no-go, promote the exact digest and perform cross-surface smoke tests.

Acceptance:

- Given accepted evidence, when Release 2 is promoted, then work, delivery, logs/health and incident journeys work on the approved artifacts.
- Given any failed gate, mismatched artifact or unresolved critical/high security issue, when reviewed, then release acceptance is withheld.

Tests: R1/R2 UAT, artifact comparison and post-promotion operational smoke. Run the focused checks while implementing; TF-112 records the integrated acceptance.  
Demo: Present Release 2 and complete the work-to-investigation journey.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-112 — Verify release deployment tracking and investigation

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-111. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-112-T01: Execute and record: R1/R2 UAT, artifact comparison and post-promotion operational smoke.
- [ ] TF-112-T02: Demonstrate: Present Release 2 and complete the work-to-investigation journey. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Present Release 2 and complete the work-to-investigation journey. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s57"></a>

### S57 — Stabilise Release 2 and specify MCP

**Goal:** Define a compatible bounded MCP surface over the stable released product.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-110, E-130. **Scope:** M-F01–M-F11; M-J01–M-J04.  
**Prerequisites:** S56 required evidence (TF-112); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-113 — Deliver the capability

State: Proposed. Persona: product owner. Estimate: 5 hours, provisional.  
Story: As a product owner, I want to define a compatible bounded MCP surface over the stable released product.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-113-T01: Review Release 2 defects/recovery signals and prioritise stabilisation before admitting integration tools.
- [ ] TF-113-T02: Specify all eleven tool schemas, permissions, output bounds, errors and audit origins.
- [ ] TF-113-T03: Select and record a stable MCP specification/SDK plus a supported private-network client and authentication compatibility.

Acceptance:

- Given stable Release 2, when the MCP contract is reviewed, then every tool maps to an existing authorised application operation.
- Given an unsupported client/auth flow or unimplemented product dependency, when readiness is assessed, then the affected tool does not proceed as ready.

Tests: Tool-to-service/permission traceability and protocol/client compatibility spike. Run the focused checks while implementing; TF-114 records the integrated acceptance.  
Demo: Walk through the tool inventory and supported-client connection plan.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-114 — Verify stabilise release 2 and specify mcp

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-113. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-114-T01: Execute and record: Tool-to-service/permission traceability and protocol/client compatibility spike.
- [ ] TF-114-T02: Demonstrate: Walk through the tool inventory and supported-client connection plan. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Walk through the tool inventory and supported-client connection plan. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s58"></a>

### S58 — Authenticate MCP discovery and transport

**Goal:** Connect a supported MCP client over private HTTPS with valid identity.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** M-J01/M-J04.  
**Prerequisites:** S57 required evidence (TF-114); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-115 — Deliver the capability

State: Proposed. Persona: integration user. Estimate: 5 hours, provisional.  
Story: As a integration user, I want to connect a supported MCP client over private HTTPS with valid identity.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-115-T01: Implement the selected Streamable HTTP lifecycle/capabilities and protocol-compatible errors.
- [ ] TF-115-T02: Implement resource-server/OAuth discovery and per-request identity/scope validation.
- [ ] TF-115-T03: Add feature disablement, safe request correlation and client login/expiry/disconnect instructions.

Acceptance:

- Given a reachable authorised client, when it negotiates and discovers tools, then the approved protocol/capabilities are reported.
- Given missing/expired identity or disabled MCP, when accessed, then transport fails safely and the web product remains usable.

Tests: Protocol, transport-auth, private reachability, expiry and MCP-off smoke tests. Run the focused checks while implementing; TF-116 records the integrated acceptance.  
Demo: Connect and discover through the supported client, then expire identity.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-116 — Verify authenticate mcp discovery and transport

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-115. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-116-T01: Execute and record: Protocol, transport-auth, private reachability, expiry and MCP-off smoke tests.
- [ ] TF-116-T02: Demonstrate: Connect and discover through the supported client, then expire identity. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Connect and discover through the supported client, then expire identity. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s59"></a>

### S59 — Expose work-management read tools

**Goal:** Read permitted work and history through the existing application services.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** M-F01–M-F04.  
**Prerequisites:** S58 required evidence (TF-116); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-117 — Deliver the capability

State: Proposed. Persona: integration user. Estimate: 5 hours, provisional.  
Story: As a integration user, I want to read permitted work and history through the existing application services.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-117-T01: Implement search, get-issue, project-summary and audit-history tool adapters.
- [ ] TF-117-T02: Reuse product filters, type-aware counts, tenant checks and bounded pagination.
- [ ] TF-117-T03: Map validation/domain/source failures without disclosing protected resource details.

Acceptance:

- Given authorised project/work data, when the four tools are called, then results agree with the web application.
- Given guessed tenant IDs, oversized filters or parent/child count ambiguity, when queried, then access/bounds hold and counts state their work type.

Tests: Four-tool schema/contract and positive/negative integration matrix. Run the focused checks while implementing; TF-118 records the integrated acceptance.  
Demo: Search a story, inspect it, read project counts and its audit history.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-118 — Verify expose work-management read tools

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-117. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-118-T01: Execute and record: Four-tool schema/contract and positive/negative integration matrix.
- [ ] TF-118-T02: Demonstrate: Search a story, inspect it, read project counts and its audit history. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Search a story, inspect it, read project counts and its audit history. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s60"></a>

### S60 — Expose work delivery and deployment tools

**Goal:** Follow work into its evidenced deployment targets.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** M-F06/M-F07.  
**Prerequisites:** S59 required evidence (TF-118); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-119 — Deliver the capability

State: Proposed. Persona: integration user. Estimate: 5 hours, provisional.  
Story: As a integration user, I want to follow work into its evidenced deployment targets.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-119-T01: Implement work-item-delivery and get-deployment adapters through Delivery services.
- [ ] TF-119-T02: Preserve artifact/attempt/rollback relationships, provenance and completeness in results.
- [ ] TF-119-T03: Add bounded output and permission checks for all linked target resources.

Acceptance:

- Given permitted work with multiple deployments, when queried, then the same per-target evidence as the UI is returned.
- Given incomplete mappings or a foreign target, when queried, then limitations/denials remain explicit without a false global delivery claim.

Tests: Two-tool contract, many-to-many, partial-evidence and cross-tenant tests. Run the focused checks while implementing; TF-120 records the integrated acceptance.  
Demo: Follow a story into UAT/release deployments and inspect a rollback.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-120 — Verify expose work delivery and deployment tools

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-119. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-120-T01: Execute and record: Two-tool contract, many-to-many, partial-evidence and cross-tenant tests.
- [ ] TF-120-T02: Demonstrate: Follow a story into UAT/release deployments and inspect a rollback. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Follow a story into UAT/release deployments and inspect a rollback. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s61"></a>

### S61 — Expose bounded health and log tools

**Goal:** Inspect the same permitted deployment signals available in the application.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** M-F08/M-F09.  
**Prerequisites:** S60 required evidence (TF-120); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-121 — Deliver the capability

State: Proposed. Persona: integration user. Estimate: 5 hours, provisional.  
Story: As a integration user, I want to inspect the same permitted deployment signals available in the application.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-121-T01: Implement health and deployment-log adapters reusing Observability policy and bounds.
- [ ] TF-121-T02: Preserve freshness, effective scope, truncation, redaction and source failure metadata.
- [ ] TF-121-T03: Treat returned content as untrusted data and prevent backend query/source overrides.

Acceptance:

- Given a valid authorised deployment query, when called, then health/log results match the product's scoped evidence.
- Given raw-query injection, oversized requests, untrusted log instructions or stale sources, when handled, then access is not widened and uncertainty is retained.

Tests: Tool/schema, boundary-injection, staleness, redaction and untrusted-content tests. Run the focused checks while implementing; TF-122 records the integrated acceptance.  
Demo: Read health/logs for a deployment, then demonstrate stale and denied cases.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-122 — Verify expose bounded health and log tools

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-121. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-122-T01: Execute and record: Tool/schema, boundary-injection, staleness, redaction and untrusted-content tests.
- [ ] TF-122-T02: Demonstrate: Read health/logs for a deployment, then demonstrate stale and denied cases. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Read health/logs for a deployment, then demonstrate stale and denied cases. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s62"></a>

### S62 — Expose incident read tools

**Goal:** Inspect permitted incidents and their evidence links.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** M-F10/M-F11.  
**Prerequisites:** S61 required evidence (TF-122); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-123 — Deliver the capability

State: Proposed. Persona: integration user. Estimate: 5 hours, provisional.  
Story: As a integration user, I want to inspect permitted incidents and their evidence links.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-123-T01: Implement incident list/detail adapters with bounded filters and current membership checks.
- [ ] TF-123-T02: Return incident lifecycle, evidence associations and corrective-work references.
- [ ] TF-123-T03: Preserve distinction between recorded findings and unconfirmed causal associations.

Acceptance:

- Given a permitted incident, when listed/opened, then its lifecycle and evidence match the application.
- Given a foreign incident or merely coincident deployment, when queried, then protected data is denied and causation is not invented.

Tests: Two-tool filtering/pagination, tenant and evidence-semantics contract tests. Run the focused checks while implementing; TF-124 records the integrated acceptance.  
Demo: Open a related incident through MCP and inspect its corrective work.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-124 — Verify expose incident read tools

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-123. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-124-T01: Execute and record: Two-tool filtering/pagination, tenant and evidence-semantics contract tests.
- [ ] TF-124-T02: Demonstrate: Open a related incident through MCP and inspect its corrective work. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Open a related incident through MCP and inspect its corrective work. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s63"></a>

### S63 — Create issues safely from a supported MCP client

**Goal:** Create follow-up work once and verify it across MCP and the web board.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** M-F05; M-J01–M-J03.  
**Prerequisites:** S62 required evidence (TF-124); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-125 — Deliver the capability

State: Proposed. Persona: integration user. Estimate: 5 hours, provisional.  
Story: As a integration user, I want to create follow-up work once and verify it across MCP and the web board.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-125-T01: Implement the sole write tool through the existing create service, with allowed types, parent/sprint rules and separate create scope.
- [ ] TF-125-T02: Reuse actor/project/command-scoped idempotency and origin-aware audit without logging tokens or raw telemetry.
- [ ] TF-125-T03: Complete clean-client setup and the work→deployment→incident→corrective-issue journey.

Acceptance:

- Given an uncertain create response, when the same authorised command/key is retried, then exactly one item and successful-create audit event exist.
- Given conflicting key reuse, read-only scope or invalid parent/project, when called, then no extra or unauthorised work is created.

Tests: Concurrent retry, scope, hierarchy and supported-client/web end-to-end tests. Run the focused checks while implementing; TF-126 records the integrated acceptance.  
Demo: Create corrective work via MCP, retry it and find the single item on the board.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-126 — Verify create issues safely from a supported mcp client

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-125. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-126-T01: Execute and record: Concurrent retry, scope, hierarchy and supported-client/web end-to-end tests.
- [ ] TF-126-T02: Demonstrate: Create corrective work via MCP, retry it and find the single item on the board. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Create corrective work via MCP, retry it and find the single item on the board. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

<a id="s64"></a>

### S64 — Release TaskForge MCP v1

**Goal:** Release the complete supported MCP journey with recovery evidence.  
**Milestone:** MCP v1 (S64). **Epic(s):** E-130. **Scope:** MCP v1 release gate.  
**Prerequisites:** S63 required evidence (TF-126); resolve this slice's contracts and environment prerequisites before marking it Ready.  
**Sprint budget:** 7 delivery hours; 14 calendar days. Re-estimate at planning.

#### TF-127 — Deliver the capability

State: Proposed. Persona: product owner. Estimate: 5 hours, provisional.  
Story: As a product owner, I want to release the complete supported MCP journey with recovery evidence.  
Dependencies: sprint prerequisites above. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-127-T01: Freeze tool/client/protocol versions and collect all eleven-tool contracts plus M-J01–M-J04 evidence.
- [ ] TF-127-T02: Rehearse MCP enable/disable, rollback and restore while preserving web/operator paths.
- [ ] TF-127-T03: Publish release notes, compatibility/deprecation policy, runbooks and go/no-go; promote exact artifacts.

Acceptance:

- Given passing product/security/client/recovery evidence, when promoted, then the supported client completes the authorised work-to-operations journey.
- Given an unsupported tool mutation, failed gate or mismatched artifact, when assessed, then release acceptance is withheld.

Tests: Eleven-tool inventory, external-client UAT, security matrix and MCP-on/off recovery regression. Run the focused checks while implementing; TF-128 records the integrated acceptance.  
Demo: Run the full final journey and demonstrate MCP disablement without breaking the product.  
Evidence: Not run — record revision/configuration, environment, date, commands/results and defects.

#### TF-128 — Verify release taskforge mcp v1

State: Proposed. Persona: reviewer/operator. Estimate: 2 hours, provisional.  
Story: As a reviewer/operator, I want reproducible evidence of this sprint's outcome and failure behaviour before accepting it.  
Dependencies: TF-127. Epic/milestone/scope: as above.

Tasks:

- [ ] TF-128-T01: Execute and record: Eleven-tool inventory, external-client UAT, security matrix and MCP-on/off recovery regression.
- [ ] TF-128-T02: Demonstrate: Run the full final journey and demonstrate MCP disablement without breaking the product. Record unresolved defects, actual effort and one retrospective action; refine the next dependency.

Acceptance:

- The capability's success and failure criteria have observed results in the stated environment; failed checks remain open.
- The recorded evidence identifies the exact revision/configuration and can be replayed by the next contributor; a missing prerequisite is marked Blocked, never Pass.

Demo and sprint exit: Run the full final journey and demonstrate MCP disablement without breaking the product. Both story acceptance records are reviewed before their dependent work becomes Ready.  
Evidence: Not run — attach command/procedure, environment, revision, date, observed outcome and acceptance decision.

## Legacy schedule crosswalk

The former S01-01…S18-04 cards were planning allocations, not accepted implementation. Their meaning is retained through the existing epic slice IDs and the mapping below; new TF IDs are stable regardless of future sprint movement.

| Former sprint card prefix | Replacement allocation |
|---|---|
| S01-* foundation | S09–S12; TF-017–TF-024 |
| S02-* organisations | S13–S14; TF-025–TF-028 |
| S03-* invitations | S15–S16; TF-029–TF-032 |
| S04-* projects | S17; TF-033–TF-034 |
| S05-* issues/edits/assignees | S18, S22–S23; TF-035–TF-036, TF-043–TF-046 |
| S06-* workflow/archive | S21–S22; TF-041–TF-044 |
| S07-* collaboration | S23–S24; TF-045–TF-048 |
| S08-* filters/audit | S24–S25; TF-047–TF-050 |
| S09-* private MVP/recovery | S08, S28, S31; TF-015–TF-016, TF-055–TF-056, TF-061–TF-062 |
| S10-* authentication | S26; TF-051–TF-052 |
| S11-* tenant security | S27; TF-053–TF-054 |
| S12-* hardening | S29–S31; TF-057–TF-062 |
| S13-* Release 1 | S32; TF-063–TF-064 |
| S14-* MCP foundation | S57–S58; TF-113–TF-116 |
| S15-* MCP reads | S59; TF-117–TF-118 |
| S16-* MCP create | S63; TF-125–TF-126 |
| S17-* client/operations | S58, S63–S64; TF-115–TF-116, TF-125–TF-128 |
| S18-* MCP release | S64; TF-127–TF-128 |

E-000 inception evidence remains historical documentation evidence. It does not mark these new implementation cards Done. New epics E-140–E-190 cover the PC server, hierarchy/sprints, delivery, telemetry, incidents and Release 2; E-130 retains MCP and E-120 retains deferred AWS hosting.

## Completion and later work

MCP completion requires the full story → PR → artifact → deployment → health/logs → incident → corrective work journey through the supported client and web UI. All four milestone gates retain observed evidence and known limitations.

After S64, review product and operational evidence before scheduling extensions. Public ingress, AWS application hosting, personal services, deployment execution inside TaskForge, other Git providers, arbitrary-query tools, advanced AI, Kafka/Redis/Kubernetes and full dashboard/trace exploration are not commitments of this forecast.

# TaskForge Home-Lab Delivery Checklist

Status: planning only; host checks, application acceptance, deployments and recovery rehearsals have not been run for this revision. Use with the [server build plan](server-build-plan.md), [release contracts](../product/product-charter.md#release-strategy) and [roadmap](sprint-roadmap.md).

## Environment record

| Item | Record |
|---|---|
| PC hardware, disks, existing OS/data | Unknown; assess/preserve in S01 |
| Target | Supported Ubuntu Server LTS selected at installation |
| Access | LAN/private VPN; private HTTPS; no public ingress |
| CPU/RAM/storage budgets | Pending inventory and measured workload |
| Hostnames/IPs/VPN/backup destination | Pending actual environment discovery |
| UAT/release storage and credentials | Separate; concrete identifiers assigned during server setup |
| Deployment authority | Operator-controlled external scripts |
| Operational owner | Project owner |
| Physical failure domain | One PC; no high-availability claim |

## Gate H — server readiness, S08

- [ ] H-01–H-08 in the server plan pass with host-specific evidence.
- [ ] Existing files are preserved and required backup copies were restored successfully.
- [ ] Private administration, storage mounts, clock, containers and database survive reboot.
- [ ] PostgreSQL and Docker management are not exposed through public or unapproved interfaces.
- [ ] Host/disk/backup monitoring is private and visibly detects an agreed failure.
- [ ] Current backend starts from a known revision with local credentials/TLS replaced appropriately.
- [ ] Backend restart, compatible artifact rollback and clean database restoration are demonstrated.

## Gate L — every application slice

- [ ] Story has a defined outcome, scope reference, actor/tenant rules and failure scenarios.
- [ ] Applicable UI/API/database/integration path runs in its specified environment.
- [ ] Migrations and constraints pass against PostgreSQL; no persistence confidence is inferred from mocks.
- [ ] Required automated tests and a reproducible demo pass.
- [ ] Failure, stale/conflict, permission and retry states are visible where applicable.
- [ ] Evidence records revision, environment, commands/results and remaining defects.

## Gate R1 — complete board, S32

- [ ] J-01–J-13 pass, including work hierarchy and 14-day sprint close/carryover.
- [ ] Cognito/role/tenant checks pass; development identity is unavailable in release.
- [ ] Browser, API, concurrency, migration, accessibility and supported-browser checks pass.
- [ ] Application logs are safe; health/version and operational signals are usable.
- [ ] Off-host backups, isolated restore and compatible rollback are rehearsed for the candidate.
- [ ] No unresolved critical/high security defect; known limitations and go/no-go recorded.
- [ ] Exact UAT-approved artifacts promoted and running-version smoke test recorded.

## Gate R2 — delivery and investigation, S56

- [ ] R2-J01–R2-J06 pass: PR/build/artifact attribution, per-target deployment state and recovery.
- [ ] Failed builds/rollouts, partial mappings and rollback history cannot falsely mark work deployed.
- [ ] Receipt replay, duplicates, conflicting IDs, out-of-order events and reconciliation pass.
- [ ] Logs/health/traces are bounded and authorised; shared host/control-plane data remains operator-only.
- [ ] Empty, no traffic, stale, expired-retention and unavailable telemetry are distinguishable.
- [ ] Incoming alerts/incident lifecycle, evidence links and corrective work pass.
- [ ] Secret expiry, GitHub limits, source outage, storage limits and queue backpressure are tested.
- [ ] Restored metadata reconciles with retained external telemetry and queued receipts.
- [ ] R1 regression, exact-artifact promotion and recovery evidence pass.

## Gate M — MCP v1, S64

- [ ] M-J01–M-J04 and all eleven tool contracts pass with a supported private-network client.
- [ ] Read-only tools cannot mutate; create retries are idempotent and auditable.
- [ ] Cross-tenant/scope and raw-query bypass attempts fail without leaking operational data.
- [ ] Returned log content is treated as untrusted data; no tokens/raw logs enter tool audit records.
- [ ] MCP-on/off smoke checks and rollback/restore preserve web and operator workflows.
- [ ] Versioned tool/client compatibility, limitations and go/no-go are recorded.

## Candidate measurement targets

These remain proposals until hardware/workload validation records accepted values. They are not measured results or uptime promises.

| ID | Candidate | Evidence |
|---|---|---|
| Q-01 | 2 organisations, 5 projects each, 1,000 active work items/project, 5 concurrent users | Seed/load scenario with work-type and tenant distribution |
| Q-02 | Board/filter API p95 ≤ 1 second, unexpected errors < 1% for a 10-minute warmed run | Hardware, artifact, query mix, latency distribution and error record |
| Q-03 | No duplicate keys, lost accepted edits, duplicate membership or receipt effects | PostgreSQL concurrency/retry tests |
| Q-04 | Protected operations reject missing/invalid identity and cross-tenant access | Positive/negative API/browser/MCP matrices |
| Q-05 | Keyboard critical flows at 390px and 1366px viewports | Browser/manual accessibility evidence |
| Q-06 | Application-data RPO ≤ 24 hours | Latest verified off-host recoverable backup time |
| Q-07 | Restore ≤ 2 hours on available compatible hardware | Timed restore, identity/work/delivery/incident checks; hardware replacement excluded |
| Q-08 | Compatible application rollback ≤ 15 minutes | Timed rollback with migration-compatibility record |
| Q-09 | Sustained service/backup failure visible within 5 minutes; disk warning at 80% | Controlled failure detection and acknowledgement |
| Q-10 | 7 daily and 4 weekly off-host database restore points | Storage sizing, retained-copy verification and restore |
| Q-11 | Finite log/metric/trace retention and bounded queries/queues | Recorded per-source limits, exhaustion/truncation and source-outage tests |

Quality values are refined in the allocated story before it becomes ready. A failed capacity check changes limits/topology or the forecast explicitly; it does not manufacture a passing gate.

## Evidence record

~~~text
Gate / story / test:
State: Not run | Pass | Fail | Blocked
Date and operator:
Commit / image digest / migration version:
Environment and hardware:
Command or procedure:
Expected / observed:
Evidence location:
Remaining defects / accepted limitations:
Next action:
~~~

All gates currently remain **Not run**. Checklist creation does not approve public exposure, PC disk changes, purchases or cloud provisioning.

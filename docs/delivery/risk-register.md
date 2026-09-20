# TaskForge Risk Register

## Purpose

This register tracks threats to product outcome, security, quality, delivery, and operation. A risk is not closed because it has a planned control; it is closed only when the exposure no longer exists or evidence shows the control is effective.

Baseline revised 2026-09-20 for the PC server, hierarchy, Release 2 and MCP scope. Controls are planned unless independently evidenced.

## Scoring

| Value | Likelihood | Impact |
|---|---|---|
| 1 | Unlikely | Minor rework or inconvenience |
| 2 | Possible | Material iteration or quality impact |
| 3 | Likely | Release outcome or safety threatened |

Score is likelihood multiplied by impact:

- 1–2: Low
- 3–4: Medium
- 6–9: High

## Active risks

| ID | Risk | L | I | Score | Owner | Planned controls | Trigger / evidence | Related work | Status |
|---|---|---:|---:|---:|---|---|---|---|---|
| R-001 | Authentication is deferred while functionality is built, creating a risk of accidental exposure or authorization rework. | 2 | 3 | 6 | Security / technical lead | Keep functional MVP private; define a current-actor interface before domain work; make Cognito a hard release gate; prevent development identity in release profiles. | Service reachable outside approved private boundary; domain services depend directly on a development header or Cognito type. | E-020, E-030, E-070, E-080 | Open |
| R-002 | A tenant-isolation defect exposes another organisation's projects, issues, members, comments, or history. | 2 | 3 | 6 | Security / backend | Service-boundary membership checks; tenant-scoped queries; negative cross-tenant tests for every resource; IDOR review. | A resource can be loaded or mutated by identifier without organisation membership enforcement. | E-020, E-040, E-050, E-060, E-080 | Open |
| R-003 | Invitation secrets are leaked, replayed, logged, or accepted by the wrong identity. | 2 | 3 | 6 | Security / backend | Store token hashes; single-use lifecycle; expiry/revocation; authenticated email match; redact logs; negative/replay tests. | Raw token in database/log/telemetry; repeated or mismatched acceptance creates membership. | E-020, E-040, E-080 | Open |
| R-004 | Release 1 scope expands into a Jira clone and delays delivery. | 3 | 2 | 6 | Product owner | Explicit non-goals; scope-change rule; deferred backlog; require trade-off, forecast change, or accepted risk for additions. | Attachments, notifications, custom workflow, advanced search, or infrastructure added without approved scope change. | E-000 through E-100 | Open |
| R-005 | One contributor fills multiple roles, weakening independent review and allowing blind spots. | 3 | 2 | 6 | Delivery lead | Separate role checklists; automated gates; explicit self-review pass; request external review for security/release decisions when available. | Same work is marked accepted without evidence or a review checkpoint. | All epics | Open |
| R-006 | Existing exploratory schema or code choices are treated as fixed and force avoidable complexity. | 2 | 2 | 4 | Technical lead | Re-evaluate through product/domain/data design; preserve useful behaviour, not accidental structure; use clean baseline because no valuable data is assumed. | Architecture is justified only by existing class/table shape rather than product rules. | E-020, E-030 | Open |
| R-007 | Configured PostgreSQL tests are mistaken for fresh migration/constraint evidence, or use an unreviewed image version. | 2 | 2 | 4 | Backend / QA | Testcontainers is present; run and record clean-install/upgrade/constraint checks against the selected supported PostgreSQL image. | Tests exist but have no current recorded execution; image remains unreviewed. | E-030, E-090 | Open |
| R-008 | Concurrent issue creation or updates cause duplicate issue keys or lost changes. | 2 | 3 | 6 | Backend | Database-backed project sequence/allocation design; optimistic locking; transaction tests; concurrent integration scenarios. | Duplicate project issue sequence or silent overwrite observed. | E-020, E-050 | Open |
| R-009 | Kanban UI becomes inconsistent with persisted state after a failed or conflicting move. | 2 | 2 | 4 | Frontend / QA | Define optimistic-update contract; revert or refetch on failure; keyboard alternative; Playwright conflict/failure cases. | Issue appears in a different column after reload, or failure is silent. | E-010, E-050 | Open |
| R-010 | Home-lab UAT and release share one physical host and are mistaken for independent or highly available environments. | 3 | 2 | 6 | Operations | Document shared failure domain; logical isolation only; never claim HA; keep recovery evidence and external backups. | Documentation or portfolio material claims production HA or independent infrastructure. | E-020, E-070, E-090, E-100 | Open |
| R-011 | Backup jobs succeed but backups are unusable or lost with the home server. | 2 | 3 | 6 | Operations | Store copies outside live volume/host; automated verification; scheduled clean restore drills; record RPO/RTO evidence. | Backup never restored; only copy exists on live database disk. | E-020, E-070, E-090 | Open |
| R-012 | Environment drift causes local/UAT/release behaviour or artifacts to differ. | 2 | 2 | 4 | Operations | Pin versions; externalize environment configuration; build once; promote immutable digests; smoke-test running version. | Environment rebuilds artifact or uses an unrecorded version/configuration. | E-030, E-070, E-100 | Open |
| R-013 | Secrets, tokens, personal data, or credentials are committed or exposed in logs and images. | 2 | 3 | 6 | Security / operations | Secret scanning; environment-specific secret storage; image inspection; structured-log redaction; review test fixtures. | Secret-scanner alert, token in log, credential in Git history or image layer. | E-020, E-030, E-080, E-090 | Open |
| R-014 | Dependency or framework versions become unsupported or vulnerable during the long delivery. | 2 | 2 | 4 | Technical lead | Select supported versions at foundation; automate dependency/security reporting; scheduled patch review; record upgrade decisions. | End-of-support date or critical advisory affects a dependency. | E-030, E-090, E-110 | Open |
| R-015 | Home-lab capacity is insufficient for the frontend, backend, database, test data, logs, and backup workload. | 2 | 2 | 4 | Operations | Measure hardware/capacity during architecture; set resource limits; load test; disk alerts; adjust topology or targets based on evidence. | Memory pressure, disk growth, latency, or restarts breach agreed targets. | E-020, E-070, E-090 | Open |
| R-016 | Public or remote access is enabled before authentication, TLS, authorization, recovery, and operational gates pass. | 2 | 3 | 6 | Product owner / security | Private-network rule for MVP; documented release exposure gate; firewall/reverse-proxy review; go/no-go checklist. | Router port-forward, public tunnel, or public DNS exists before E-080/E-090 acceptance. | E-070, E-080, E-100 | Open |
| R-017 | AWS resources create unexpected charges or distract from product functionality. | 2 | 2 | 4 | Product owner / operations | Defer AWS beyond the MCP forecast to E-120; require architecture, cost estimate, explicit provisioning approval, budgets, and teardown evidence. | AWS resource is provisioned before E-120 approval or remains after its approved window. | E-120 | Open |
| R-018 | Product decisions remain in conversation rather than version control and are later contradicted. | 2 | 2 | 4 | Delivery lead | Maintain charter, scope, master plan, backlog, risk register, decision log, and ADRs in Git; update related artifacts together. | Implementation choice cannot be traced to an approved document. | E-000 and all decision-bearing work | Controlled; monitor |
| R-019 | The API and Angular client drift, producing late integration failures. | 2 | 2 | 4 | Backend / frontend | Versioned API contract; generated or contract-validated client; CI compatibility check; integrate vertical slices early. | Frontend needs manual untracked types or breaks after backend merge. | E-020, E-030, E-040 through E-090 | Open |
| R-020 | Audit history is incomplete, misleading, or can be changed by normal product operations. | 2 | 2 | 4 | Backend / QA | Define audited operations; append-only application boundary; transaction consistency; denial/failure tests; no product edit API. | Successful change lacks entry, rejected change creates entry, or user edits history. | E-020, E-060 | Open |

| R-021 | PC installation or mount changes destroy existing files. | 2 | 3 | 6 | Operator | Inventory exact disks; preserve by default; verify off-host copy and recovery before installation. | Unknown disk target or unverified preserved-data backup. | E-140 | Open |
| R-022 | Shared telemetry or source manipulation leaks another tenant's logs or host data. | 2 | 3 | 6 | Security / backend | Trusted source/tenant mapping; backend-built bounded queries; operator-only shared streams; destination authorization. | Tenant/source/URL override changes returned scope; unpartitioned streams reach members. | E-170/E-190/E-130 | Open |
| R-023 | The long forecast exceeds available capacity or stories are falsely marked ready/done. | 3 | 2 | 6 | Delivery lead | Use 6–8 delivery-hour budgets; track actual effort; split/reforecast; keep 14-day cadence and evidence gates. | Repeated spillover, unestimated stories or completion without a demo. | All epics | Open |
| R-024 | Duplicate, missing or out-of-order receipts assert the wrong deployed version. | 2 | 3 | 6 | Backend / operator | Durable script spool; restricted producer IDs; idempotency; explicit ordering/conflicts; replay/reconciliation tests. | Late event regresses active version, duplicate history or unresolved missing interval. | E-160/E-190 | Open |
| R-025 | A work link or coincident incident is presented as complete delivery or proven cause. | 2 | 2 | 4 | Product / QA | Immutable manifests, per-target provenance/completeness and explicit investigation findings. | Build success becomes deployed; one target implies all; association becomes automatic blame. | E-160/E-180/E-130 | Open |
| R-026 | GitHub limits, credential expiry or source outages silently stale the product. | 3 | 2 | 6 | Integration owner | Conditional bounded polling/backoff; visible freshness; credential review and reconciliation. | Throttling/revocation or missing observations while UI reports fresh success. | E-160/E-190 | Open |
| R-027 | TaskForge outage prevents deployment or recovery of TaskForge itself. | 2 | 3 | 6 | Operator | Independent scripts, credentials and runbooks; durable receipts; monitoring outside the application. | Operator workflow requires a working TaskForge API/UI. | E-140/E-160/E-190 | Open |
| R-028 | Logs, traces or integration queues exhaust the PC. | 3 | 2 | 6 | Operator | Measured finite retention/query/queue budgets, disk alerts and backpressure. | Unbounded growth, full spool or shared-host resource pressure. | E-140/E-170/E-190 | Open |
| R-029 | Integration secrets or unrestricted query tools grant unintended access. | 2 | 3 | 6 | Security | Server-side credentials, registered producer scope, source allowlist, redaction and no arbitrary backend query inputs. | Secret in response/log or arbitrary network destination accepted. | E-160/E-170/E-130 | Open |
| R-030 | Hierarchy or sprint carryover loses work or inflates progress. | 2 | 2 | 4 | Backend / QA | Same-project/cycle constraints, inherited task sprint, explicit closure snapshot and type-aware counts. | Orphan task, conflicting sprint, concurrent closure loss or parent/child double-counting. | E-150 | Open |
| R-031 | No off-host backup destination is available when recovery is needed. | 2 | 3 | 6 | Operator / product owner | Resolve destination/capacity in S01; demonstrate sample/full restoration; block recovery gate if absent. | Only backup exists on the PC or destination remains unknown. | E-140/E-090/E-190 | Open |
| R-032 | MCP exposes more authority than the product or claims unsupported client reachability. | 2 | 3 | 6 | Security / integration owner | Eleven-tool allowlist; one write; per-call product/source authorization; verified private client/auth compatibility. | Unexpected mutation, scope bypass, raw-query input or client cannot reach VPN endpoint. | E-130 | Open |

## Risk review process

- Review high risks during every iteration planning and demonstration.
- Review all risks at each phase gate and release go/no-go.
- Add a risk when new uncertainty threatens a product, security, quality, delivery, cost, or operational outcome.
- When a trigger occurs, create or update backlog work rather than only changing the score.
- A score change records the evidence in the risk history below.

## Risk acceptance rule

A high risk cannot be silently carried through a release gate. It must be:

1. Mitigated with evidence,
2. Avoided by changing scope or design,
3. Transferred through a real external responsibility, or
4. Explicitly accepted by the product owner with impact, expiry/review date, and contingency recorded.

## Risk history

| Date | Risk | Change | Evidence / reason |
|---|---|---|---|
| 2026-08-27 | R-001 through R-020 | Initial register created | Step 1 delivery inception |
| 2026-08-27 | R-018 | Mitigation established | Six governing inception artifacts created inside the Git repository |
| 2026-09-20 | R-007 | Updated stale H2 description | PostgreSQL Testcontainers configuration inspected; execution not claimed |
| 2026-09-20 | R-021–R-032 | Added expanded product/server risks | Accepted work → deployment → investigation → MCP scope and 4–5 hours/week capacity |

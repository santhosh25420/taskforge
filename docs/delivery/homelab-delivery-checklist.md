# TaskForge home-lab delivery checklist

Tags: `build` `deploy`

## Purpose and status

This applies [product scope and delivery basics](../product/product-scope-and-delivery-guide.md) to the home lab. It defines the operational outcome, candidate quality targets, release checks, and a place to record evidence.

**Status: planning artifact created; no home-lab connection, deployment, acceptance run, or recovery rehearsal was performed as part of creating this document.** Hardware and access details are unverified. All execution gates below start as not run.

The [master plan](master-plan.md), [Release 1 scope](../product/release-1-scope.md), and [decision log](../architecture/decision-log.md) govern the release. The numerical targets below are proposed inputs to E-020-S09/D-108; topology details belong to E-020-S08/D-107. They do not silently mark deferred decisions accepted.

## 1. The operational promise — `deploy`

As the operator, I want to run TaskForge on my home server so intended users can use the tested application and I can investigate failures and recover their work.

Home-lab delivery includes the frontend, backend, PostgreSQL, private access/TLS design, separate environment configuration/data, version identification, diagnostics, backups, and recovery procedures. A server that merely starts the application has demonstrated startup, not release readiness.

AWS application hosting and high availability are outside this milestone. Cognito remains the secure release identity provider. Keeping a secure release private is valid; passing its release gate does not itself authorize public exposure.

## 2. Environment record — `deploy`

Record operational metadata only. Keep passwords, private keys, and tokens in the chosen secret store, not this document.

| Item | Current record | Why it matters |
| --- | --- | --- |
| Physical host / VM | Unknown | Determines resource limits and failure domain. |
| Operating system | Ubuntu/Debian is the accepted target; actual installation unverified | Deployment commands and service management depend on it. |
| CPU / RAM / free storage | Unknown | Required before validating capacity targets. |
| Existing workloads | Unknown | TaskForge must share the server without exhausting it. |
| Intended access | Unknown; planning assumes trusted private access initially | Determines routing, TLS, and firewall design. |
| UAT/release hostnames and addresses | Not selected | Needed for proxy configuration and Cognito redirects. |
| Runtime / image registry | Not verified or selected in this checklist | Needed for reproducible artifact delivery. |
| UAT/release database and storage names | Not selected; must be separate | Prevents acceptance tests from altering release data. |
| Backup destination outside the host | Not selected | Required to recover from loss of the server. |
| Operator | Project owner, unless another operator is assigned | Someone must respond to failures and maintain backups. |

### Environments in plain language

| Environment | Purpose | Identity and data boundary |
| --- | --- | --- |
| Local | Implement and test quickly on your workstation | Disposable test data; explicit development identity allowed. |
| Home-lab UAT | Rehearse real user journeys and operation | Separate configuration/data; private development identity only during private MVP, then real identity for release-candidate UAT. |
| Home-lab release | Run the accepted version for intended users | Cognito, tenant authorization, TLS/access controls, release data, and tested recovery. |

UAT and release can share one machine but must not share writable application data or credentials. If that machine fails, both may fail. A diagram or separate container names do not create independent hardware.

## 3. Candidate quality targets — `build` `deploy`

These are concrete starting proposals for a small portfolio workload, not claims about your server. Measure them in UAT and record accepted values before treating them as release commitments. Revise unrealistic targets explicitly rather than claiming they passed.

| ID | Tags | Candidate target or existing requirement | How to prove it |
| --- | --- | --- | --- |
| Q-01 | `build` `deploy` | Proposed test dataset: 2 organisations, 5 projects per organisation, 1,000 active issues per project, and 5 concurrently active users | Save a reproducible seed/load scenario with tenant-separated data. |
| Q-02 | `build` `deploy` | Proposed performance: board/filter API p95 ≤ 1 second, unexpected request failures < 1%, for a 10-minute run after warm-up at Q-01 workload | Record request mix, page size, host specs, test version, latency distribution, and failures; exclude intentional denial tests from this error metric. |
| Q-03 | `build` | Required integrity: no duplicate issue keys, lost accepted edits, or duplicate membership in defined concurrency/retry cases | Real PostgreSQL concurrency and transaction tests with recorded outcomes. |
| Q-04 | `build` `deploy` | Required security: all protected release operations reject missing/invalid identity and cross-tenant access; no unresolved critical/high security defects | API/browser permission tests, configuration review, and recorded security findings. |
| Q-05 | `build` | Required usability: core journeys work by keyboard, status change has a non-drag action, and failures are explained | Manual keyboard checks and browser tests; candidate viewport widths 390 px and 1366 px. |
| Q-06 | `deploy` | Proposed recovery point objective: lose at most 24 hours of data | Automated daily backups with an off-host completed copy; record the latest recoverable point and alert if its age exceeds the target. |
| Q-07 | `deploy` | Proposed recovery time objective: ≤ 2 hours from recovery start to verified service on available compatible hardware | Timed clean restore plus identity, organisation, issue, and audit checks; record that obtaining replacement hardware is outside this measured interval. |
| Q-08 | `deploy` | Proposed application rollback: ≤ 15 minutes to a known-good compatible artifact | Timed UAT rollback and smoke test; document incompatible schema cases separately. |
| Q-09 | `deploy` | Proposed failure visibility: sustained application failure is visible within 5 minutes; disk alert at 80% usage | Trigger controlled UAT failures and capture health/monitoring evidence. |
| Q-10 | `deploy` | Proposed backup retention: 7 daily and 4 weekly restore points outside the host | Verify completed copies, restore a retained copy, and measure storage required before accepting this policy. |

Here, p95 means at least 95% of measured requests meet the stated time. Recovery point means how much recent work you might lose; recovery time means how long restoration takes. These targets do not create an uptime or high-availability promise.

Target decision record: **Pending host/workload validation.** When resolved, record accepted values, operator/product-owner decision, date, and evidence in the existing architecture decision process.

## 4. Gate L: a local slice works — `build`

Apply this gate to each selected vertical slice, then repeat the integrated functional journeys when the MVP is complete.

- [ ] Prerequisites, selected tool versions, and startup/test instructions are recorded.
- [ ] The selected user journey works through Angular, Spring Boot, and PostgreSQL.
- [ ] Valid input, invalid input, relevant permission rules, and persistence after reload pass.
- [ ] Applicable rollback/concurrency/failure behavior has test evidence.
- [ ] Migrations run against a clean test database and mappings validate.
- [ ] The slice's automated checks pass; remaining work is recorded honestly.
- [ ] Development identity is explicitly restricted to the local/private environment design.

Exit evidence: story reference + tested commit + exact commands/results + a reproducible user demonstration. This gate alone does not establish that all Release 1 functionality is complete.

## 5. Gate H: private functional MVP on the home lab — `deploy`

Traceability: E-070, R1-F11/J-11, with all functional journeys from E-040 through E-060.

- [ ] Environment record is complete enough to deploy and recover; host capacity and available storage are measured.
- [ ] Selected container/runtime, network, TLS/private access, and secret-handling design is recorded.
- [ ] Access is verified within the trusted private boundary; an MVP using development identity has no public ingress.
- [ ] UAT has separate configuration, credentials, and persistent storage from release.
- [ ] Versioned frontend/backend artifacts are deployed and their running versions can be identified.
- [ ] Organisation, both invitation paths, project, issue, board, collaboration, filtering, and conflict journeys pass using non-sensitive demonstration data.
- [ ] Browser refresh on a deep link works; database data survives application/container restart.
- [ ] Health status and useful logs can be inspected without exposing secrets.
- [ ] A controlled restart/reboot rehearsal demonstrates service recovery.
- [ ] A backup outside the live volume/host is restored into a clean, isolated database and its records are checked.
- [ ] A previous compatible application version is restored and smoke-tested.
- [ ] Known limitations are recorded and the environment is identified as a private functional MVP.

### Worked operational acceptance example

**Given** UAT contains a known organisation, issue, comment, and audit entry, **when** the operator backs it up and restores the backup into a separate clean database, **then** the restored application can show those records with their relationships intact, and the elapsed recovery time and recovered data timestamp are recorded.

Perform the rehearsal against isolated restore storage. A successful backup command alone does not satisfy this acceptance check.

## 6. Gate R: secure home-lab Release 1 — `deploy`

Traceability: E-080, E-090, E-100 and the existing Release 1 acceptance gate.

- [ ] All private functional MVP gate evidence is available and still applicable.
- [ ] Cognito sign-in, expiry, logout, invalid-token, and authentication-failure cases pass.
- [ ] Development identity cannot be enabled accidentally in release configuration.
- [ ] Owner/admin/member and cross-organisation tests pass for every protected resource.
- [ ] TLS, allowed origins/redirects, restricted service reachability, and secrets configuration pass review.
- [ ] Candidate quality targets have been resolved into recorded accepted targets, and required measurements pass.
- [ ] Required browser, API, database, migration, accessibility, and security checks pass.
- [ ] No unresolved critical or high-severity security defect remains.
- [ ] Backup schedule, off-host copies, failure visibility, retention, and clean restore are demonstrated.
- [ ] Migration sequence and application rollback compatibility are documented and rehearsed.
- [ ] User guide, API documentation, release notes, deployment, troubleshooting, rollback, and restore procedures are usable.
- [ ] Persona-based UAT passes against the exact release-candidate artifacts.
- [ ] The product owner records go/no-go with evidence and known limitations.
- [ ] The same approved artifact versions are promoted without rebuilding, and release smoke checks pass.

Public access, if later desired, requires its own concrete access design and review under the existing exposure rule. Home-lab Release 1 need not be internet-facing.

## 7. Evidence record — `build` `deploy`

Start each gate as **Not run**. Use **Pass** only when the required evidence exists; use **Fail** for an executed check that did not meet its expectation. A planned command or a document is not execution evidence.

| Gate | Current result | Tested version/environment | Evidence | Remaining action |
| --- | --- | --- | --- | --- |
| L — local slice | Not run in this task | Not recorded | None recorded here | Select/refine a story and run its checks. |
| H — private home-lab MVP | Not run | Host unverified | None | Confirm environment and execute after the functional MVP is ready. |
| R — secure home-lab release | Not run | Not deployed in this task | None | Complete identity, quality, recovery, UAT, and promotion gates. |

Copy this entry for an actual check:

```markdown
Check ID / story:
Date and operator:
Environment and host specification:
Commit / frontend image digest / backend image digest:
Migration version and non-secret configuration reference:
Test data and starting conditions:
Exact command or manual procedure:
Expected result:
Actual result and evidence location:
Result: Not run / Pass / Fail
Defect or follow-up:
```

Release decision record: **Not requested or made in this task.** At release time, attach the evidence, remaining limitations, recovery plan, and product-owner decision before promotion.

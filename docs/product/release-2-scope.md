# TaskForge Release 2 Scope

Status: accepted planning contract, revised 2026-09-19; implementation and acceptance not run. Forecast: S33–S56, following accepted Release 1.

## Outcome and boundary

An organisation member can trace work to an actual deployment, inspect authorised runtime evidence, and record an incident with corrective work. TaskForge observes deployments executed by external operator scripts.

GitHub/Actions is the first provider. The PC server remains reachable through LAN/private VPN. Prometheus, Loki and Tempo own raw telemetry; Grafana supports deeper investigation. TaskForge stores delivery metadata, relationships, audit and incident records.

## Capabilities

| ID | Capability | Acceptance criteria | Sprints |
|---|---|---|---|
| R2-F01 | Repository connection | Owner/admin binds an authorised repository to its organisation; credentials remain server-side; revoked access shows a connection error and last successful sync. | S33 |
| R2-F02 | Service/environment catalogue | A project can own multiple services; service deployments reference an organisation-owned environment; foreign-tenant identifiers are rejected. | S34 |
| R2-F03 | Work/PR links | Members explicitly link stories, tasks or bugs to repository PRs; multiple links are supported and link changes are audited. A title/branch key may suggest, but cannot establish, deployment inclusion. | S35 |
| R2-F04 | Builds and synchronisation | PR state, build attempts/results and external log links are read from GitHub; pagination, conditional requests, backoff and freshness are visible. Passing builds alone do not establish delivery. | S36 |
| R2-F05 | Artifact inclusion evidence | Immutable artifact digest, commit, build attempt and included PR/work references form a versioned manifest. Missing or incomplete inclusion evidence is displayed. | S37 |
| R2-F06 | Deployment attempts and receipts | Authenticated machine producers record service/environment, artifact digest, attempt identity, event identity, outcome and timestamps. Duplicate receipts are harmless; conflicting reuse is rejected. | S38 |
| R2-F07 | Deployment history/current state | Timeline preserves attempts and observed successful deployments. A pending or failed attempt cannot overwrite the last confirmed active version. Ambiguous/partial results show uncertainty. | S39 |
| R2-F08 | Work delivery timeline | A work item can map to many deployments/services/environments, and an artifact can include many work items. Display evidence provenance, completeness and per-target results; no global “deployed” claim from one partial target. | S40 |
| R2-F09 | Failure and rollback | Failed/partial attempts remain visible. Rollback is a new deployment of a previous digest linked to the replaced attempt; it does not erase history or change work status. | S41 |
| R2-F10 | Reconciliation | Delayed, duplicate, missing and out-of-order events converge without false state regression; terminal conflicts are flagged. Scripts spool receipts durably while TaskForge is unavailable. | S42, S53 |
| R2-F11 | Telemetry correlation | Server maps authorised service/environment/version and time window to a configured telemetry source. Trusted tenant attribution is required for member-visible data. | S43 |
| R2-F12 | In-app logs | User can page through bounded, read-only application logs by deployment/time/severity/text, with freshness, redaction, truncation and backend-failure states. No arbitrary LogQL or source URL input. | S44–S46 |
| R2-F13 | Health summary | Show measured availability, latency and error signals for the selected service/deployment context with timestamps and coverage. Missing/no-traffic/stale data is not automatically healthy. | S47 |
| R2-F14 | Trace investigation | Permitted log trace IDs link to the matching Tempo/Grafana context. Expired/missing traces and unavailable version filtering are explicit; no full trace explorer is built in TaskForge. | S48 |
| R2-F15 | Alerts | Authenticated incoming operational alerts are deduplicated and mapped to authorised service/environment context; resolved events retain history. Unmapped alerts are quarantined for operator review. | S49 |
| R2-F16 | Incidents | Members create, acknowledge and resolve organisation incidents, with severity, actor/time history, evidence links, related deployments and corrective stories/tasks/bugs. Evidence association is distinct from a confirmed cause. | S50–S52 |
| R2-F17 | Operational release quality | Connector outages, queue bounds, credential expiry, access/redaction, retention, capacity and restore are tested before exact-artifact release. | S53–S56 |

## Journeys

| ID | Journey and required result |
|---|---|
| R2-J01 | Link a story to a PR, observe its build, select its immutable artifact and find a confirmed deployment. |
| R2-J02 | Deliver one story to multiple services/environments and show partial/unknown coverage without claiming complete delivery. |
| R2-J03 | Follow a deployment into permitted logs, health and trace links; explicitly distinguish empty, stale and unavailable evidence. |
| R2-J04 | Inspect a failed deployment and an operator-executed rollback, preserving all history and the actual active version. |
| R2-J05 | Receive an alert, investigate an incident, link evidence and create corrective work without claiming automatic causation. |
| R2-J06 | Keep deploying while TaskForge is unavailable; recover, replay receipts, reconcile and verify tenant-safe state. |

## Interface contract

These are planned interfaces, not implemented routes. Detailed schemas/OpenAPI are tasks in the allocated sprint; use the common versioned API/error/pagination conventions.

| Boundary | Inputs and outputs that must be defined |
|---|---|
| Repository connections | Organisation/project, approved provider repository identity, credential reference; connection/freshness state without secret values |
| Service/environment catalogue | Organisation-scoped identifiers and display metadata; no arbitrary network execution target |
| Delivery reads | Work item or service/environment identity and bounded pagination; PR/build/artifact/attempt relationships with provenance and completeness |
| Receipt ingestion | Producer identity plus stable deployment/event IDs, artifact identity and ordered attempt events; accepted, duplicate, conflict or rejected outcome |
| Health/log queries | Authorised deployment or service/environment and bounded time/filter parameters; data plus observation time, scope, freshness and truncation |
| Alerts/incidents | Verified alert producer or authenticated member; incident state transitions and audited evidence/corrective-work relationships |

Receipt producer credentials are separate from human identity and restricted to registered services/environments. Organisation is resolved through that registration, never accepted as authority from the request body. Data reads recheck current membership and source authorization.

A successful rollout receipt must identify the observed running artifact after the deployment script's smoke check. Pipeline job success alone is insufficient. A historical artifact manifest is not rewritten when a user later edits a PR link; record corrections with provenance instead.

## Telemetry policy

- Validate source ownership, permitted service/environment and time bounds on the server. Arbitrary backend URLs, queries and tenant-header overrides are forbidden.
- Org source/credential separation must enforce the same boundary as application membership. Shared host/control-plane telemetry is operator-only.
- A shared application log line without reliable tenant attribution must not be shown to a member merely because the member can see that service.
- Redact at collection and response boundaries. Do not persist raw query results in audit records.
- Metadata holds observation time, query scope and evidence links; raw logs/metrics/traces stay in their backends with finite retention.
- Version-unavailable metrics are labelled service/environment-level. They are not presented as specific to a story or deployment version.
- Grafana/Tempo links require equivalent destination access control; a link is not a bypass or a guarantee that the user has external access.

## Release gate

At S56, R2-J01–R2-J06 and all capability criteria pass with revision/environment evidence. Include duplicate/out-of-order/missing receipts, revoked GitHub access, no telemetry, stale data, log-source injection, cross-tenant access, partial deployment and recovery. Preserve the Release 1 regression suite. Promote the exact candidate, with no unresolved critical/high security defect and tested rollback/restore.

## Exclusions

TaskForge-triggered deploy/rollback, arbitrary queries, raw telemetry storage in PostgreSQL, full trace/dashboard building, automatic causal diagnosis, multi-provider Git support, outbound paging/email/SMS, Kubernetes, required Kafka/Redis, public server ingress, AWS hosting and personal home-lab services are outside this release.

References: [GitHub deployment semantics](https://docs.github.com/en/rest/deployments/deployments), [efficient private-network polling](https://docs.github.com/en/rest/using-the-rest-api/best-practices-for-using-the-rest-api), [OpenTelemetry service/version attributes](https://opentelemetry.io/docs/specs/semconv/resource/service/), [Loki access boundary](https://grafana.com/docs/loki/latest/operations/authentication/).

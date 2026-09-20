# TaskForge System Overview

Status: planned architecture, revised 2026-09-19. Only the early Spring Boot backend is implemented today; this diagram is a target, not a deployment inventory.

## Boundaries

~~~mermaid
flowchart LR
  User["Browser / private MCP client"] --> App["TaskForge application services"]
  App --> DB["PostgreSQL: work, delivery, incidents, audit"]
  GH["GitHub / Actions / registry"] -->|"outbound synchronisation initiated by TaskForge"| App
  Operator["Operator deployment scripts"] --> Runtime["Application service in an environment"]
  Operator -->|"durable authenticated receipts"| App
  Runtime --> Collector["Telemetry collection"]
  Collector --> Signals["Prometheus / Loki / Tempo"]
  App -->|"authorised bounded reads"| Signals
  User -->|"separate authorised investigation"| Grafana["Grafana"]
  Grafana --> Signals
~~~

The GitHub arrow represents returned data; no inbound GitHub-to-home-server connection is required. The scripts and telemetry collector operate independently of TaskForge.

## Modules and ownership

| Module | Owns | Allowed collaboration |
|---|---|---|
| Identity/access | External identity mapping, current actor and organisation roles | Shared authorization contract; Cognito types do not leak into domain services |
| Work management | Projects, typed work items, hierarchy, sprints, workflow and comments/labels | Publishes/queryable work identity and validates write commands |
| Audit | Append-only successful-change evidence | Participates in reliable application transactions |
| Delivery | Repositories, PR/build references, artifacts/manifests, services, environments and deployment events | Links validated work identities; receives trusted producer events |
| Observability | Source mappings, query policy, freshness and bounded read adapters | Resolves only authorised delivery context; does not own raw telemetry |
| Incidents | Alert references, incident lifecycle, evidence and corrective-work relationships | Links work/delivery evidence without inferring causation |
| Integration workers | GitHub cursors, receipt/alert processing, retries and reconciliation | Call owning application services; persist durable processing state |
| MCP adapter | Protocol/schema/error mapping | Calls the same services as REST, never repositories directly |

The Spring Boot backend remains a modular monolith. Integration queues/state can use PostgreSQL initially; no broker or cache is required to meet the accepted scope.

## Work and delivery model

Epics group stories; stories own tasks; bugs stand alone. Work item IDs/keys are stable. A project sprint lasts 14 calendar days; stories/bugs are assigned directly, child tasks inherit, and epics span sprints. Parent and child statuses remain explicit; completing tasks does not silently accept a story.

A work/PR relation is many-to-many. A build attempt identifies source revision and produces an immutable artifact. A manifest freezes its included PR/work references and completeness. Deployments are per service/environment; each attempt references an artifact digest and has durable producer events. One story can require several target deployments.

Keep four dimensions separate: work status, build outcome, deployment outcome, runtime health. An incident association is a fifth relationship, not a derived guilt verdict.

## Delivery data flow and recovery

1. Owner/admin registers a repository and service/environment mapping; secret values are stored outside API responses and version control.
2. The outbound GitHub worker synchronises permitted PR/build/deployment metadata with authenticated conditional requests, bounded pagination, backoff and cursors.
3. An operator script deploys a selected immutable digest over the private network and verifies the running artifact.
4. The script spools receipt events locally before attempting delivery. Registered producer credentials restrict the services/environments it can report.
5. TaskForge deduplicates events by producer/event ID, validates attempt ordering and projects history/current state.
6. Reconciliation handles missed/late events and stale cursors; conflicts become visible instead of overwriting trusted state.

A failed attempt leaves the previous confirmed active version in place unless observations establish a partial/unknown state. Rollback creates a new attempt deploying the prior digest. Out-of-order receipt arrival does not decide deployment chronology. After database restore, receipt replay and source reconciliation must repair projections without duplicate history.

## Observability and authorization

Correlate source, service, environment, version/digest, instance where available, timestamp and trace ID. Collection must preserve trustworthy tenancy or classify the signal operator-only.

The backend checks current organisation membership and source ownership before querying. It supplies tenant context and constrained filters itself. Client-provided source URLs, raw backend queries and tenant headers are prohibited. Provider URLs are configured through an operator-controlled allowlist to prevent arbitrary network access.

Use bounded query windows, page/row limits, timeouts and explicit truncation. Numerical budgets are measured/recorded in S44–S47 against the host; acceptance cannot pass with unbounded defaults. Distinguish no results, no traffic, expired retention, stale source and source unavailable.

System/shared administration logs stay operator-only. A service shared by multiple product organisations does not make its unpartitioned logs safe for every member. Grafana and Tempo require equivalent access at the destination.

Metadata and audit retention differ from raw telemetry retention. Backups contain application metadata and approved configuration/secrets recovery material, not an unbounded copy of every log.

## Deployment topology

- Developer workstation: source, builds and disposable tests.
- PC: Ubuntu Server LTS, private HTTPS/VPN, Docker Compose, separate UAT/release credentials and storage.
- PostgreSQL: application data, separate UAT/release databases and tested off-host backups.
- Observability: resource-limited Prometheus, Grafana, Loki, collector and Tempo as their sprints arrive.
- GitHub Actions: build/test and publish immutable images; operator scripts pull approved digests.
- Cognito: release user identity; no AWS application-hosting requirement.

Choose actual disks, addresses, private names, backup destination and supported versions from the S01–S05 inventory. These require real environment evidence before execution, not invented values in a diagram. The single host is not highly available.

## Public interfaces and compatibility

Plan REST/application boundaries for typed work/hierarchy/sprints; source/catalogue administration; work delivery reads; machine receipt ingestion; constrained telemetry reads; alert/incident lifecycle. The release contracts define minimum input/output behaviour; each slice supplies its versioned schema, errors and tests before becoming ready.

MCP shares these services and exposes only the eleven tools in its contract. No integration credential grants product users host/operator capabilities. Migration and artifact compatibility are rehearsed for each release; applied server migrations are not rewritten as exploratory local history.

Authoritative references: [Release 1](../product/release-1-scope.md), [Release 2](../product/release-2-scope.md), [MCP](../product/mcp-v1-scope.md), [decision log](decision-log.md).

# TaskForge MCP v1 Scope

Status: accepted planning contract, revised 2026-09-19. Forecast S57–S64, after Release 2. The server/client and release evidence do not exist yet.

## Product outcome

An authenticated supported MCP client can inspect work, its delivery and operational evidence, then create an issue for follow-up. Every tool delegates to the same application services and permission checks as the web application.

The tool set contains **eleven tools: ten reads and one write**. Resources, prompts, model sampling and autonomous execution are excluded.

## Tool inventory

| ID | Tool | Result / constraint | Sprint |
|---|---|---|---|
| M-F01 | taskforge_search_issues | Bounded, deterministic work search using the product filters; no semantic search | S59 |
| M-F02 | taskforge_get_issue | Permitted issue/work-item detail and hierarchy context | S59 |
| M-F03 | taskforge_get_project_summary | Deterministic counts with work types stated and no parent/child double-counting disguised as story progress | S59 |
| M-F04 | taskforge_get_issue_audit_history | Paginated immutable history, with the product redaction and authorization policy | S59 |
| M-F05 | taskforge_create_issue | The only mutation: create an allowed story/task/bug using existing validation, parent/sprint rules, actor attribution and idempotency | S63 |
| M-F06 | taskforge_get_work_item_delivery | Per-service/environment delivery relationships, provenance and incomplete mappings | S60 |
| M-F07 | taskforge_get_deployment | Artifact, attempt/result, observed version and rollback relationships | S60 |
| M-F08 | taskforge_get_deployment_health | Measured health plus scope, observation time and unknown/stale/unavailable state | S61 |
| M-F09 | taskforge_query_deployment_logs | Bounded authorised log results and truncation/redaction metadata; no arbitrary queries | S61 |
| M-F10 | taskforge_list_incidents | Bounded organisation-scoped incident list with supported filters | S62 |
| M-F11 | taskforge_get_incident | Incident detail, evidence associations and corrective work; association is not proven cause | S62 |

The original five work tools are preserved by name. Epic/sprint administration, membership, invitations, connection configuration, incident mutation, archive/delete, deployment, rollback, infrastructure and arbitrary-query operations are not exposed.

## Identity, contracts and failures

- S57 records a stable supported MCP specification/SDK and the client compatibility target. S58 verifies authenticated Streamable HTTP over private HTTPS and the corresponding OAuth resource-server/discovery requirements.
- The client must have LAN/VPN reachability. Cloud-hosted clients without that route are not claimed to be supported.
- Identity determines actor, membership and granted scope. Tenant arguments only select among already authorised resources.
- Read and create permissions are distinct. No session or discovery result substitutes for per-call authorization.
- Schemas, bounded outputs, validation and domain failures are documented per tool. Transport authentication failures remain transport failures; tool/domain failures use the selected protocol's error contract.
- Create retries use an idempotency key scoped to actor/project/operation and canonical command. Same key/same command returns the prior result; conflicting reuse is rejected; concurrent retries create only one item and one successful-create audit event.
- Tool text and returned logs are untrusted data. No returned instruction can grant authority or change what tools exist.
- Audit records contain actor, tool, target, origin, result and correlation metadata, not tokens, arbitrary prompts or raw log results.
- Disabling MCP leaves the web product and operator deployment path functional.

## Acceptance journeys

| ID | Required journey |
|---|---|
| M-J01 | Supported client signs in, discovers the eleven tools and reads only permitted project/work data; missing/expired identity is denied. |
| M-J02 | Client follows one story through PR/build/artifact/deployment into health/log evidence and a related incident, retaining unknown/stale/association labels. |
| M-J03 | Client creates corrective work, retries after an uncertain response, and sees exactly one new item with accurate audit history in the web board. |
| M-J04 | Revoke permissions, disconnect a telemetry source, disable MCP and roll back the candidate; each fails or recovers without leaking data or weakening the web product. |

## Release gate

S64 requires contract/integration tests for all eleven tools, external-client evidence, cross-tenant/scope/oversized-input tests, log redaction, concurrency/idempotency tests, web regression and MCP-on/off rollback/restore evidence. The candidate has no unresolved critical/high security defect, and the operator/product owner records its go/no-go.

Client and protocol versions are reverified when the MCP implementation sprints begin; do not freeze today's draft protocol or claim future client compatibility in this planning revision.

See the [system overview](../architecture/system-overview.md), [Release 2 telemetry policy](release-2-scope.md#telemetry-policy), and [official MCP specification](https://modelcontextprotocol.io/specification/).

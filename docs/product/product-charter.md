# TaskForge Product Charter

Status: accepted planning baseline, revised 2026-09-19. Product implementation and release acceptance remain unverified.

## Product statement

TaskForge helps small software teams plan work, trace its delivery into running applications, and investigate operational behaviour. Its central journey is:

**Epic → story/task → pull request → build → deployed version → health and logs → incident and corrective work.**

A team can answer what was planned, which changes shipped where, whether the application is healthy, and which evidence supports an investigation. Association with a failing deployment is not an automatic claim that a particular story caused the failure.

## Audience and responsibilities

| Persona | Need |
|---|---|
| Organisation owner/admin | Manage membership, projects and authorised integration connections |
| Developer/member | Plan work, collaborate, follow delivery and inspect permitted operational evidence |
| Team lead | Organise epics and 14-day sprints, review progress and follow corrective work |
| Invited user | Join the correct organisation with predictable identity checks |
| Application responder | A member investigating an authorised service and maintaining its incident record |
| Host operator | Build, patch, deploy, monitor, back up and recover the private server |

Product roles remain OWNER, ADMIN and MEMBER. Team lead and responder are personas, not additional permission roles. Host operation is a separate responsibility and does not grant ordinary members access to shared host or TaskForge administration logs.

## Goals and success measures

| ID | Goal | Observable acceptance |
|---|---|---|
| G-01 | Deliver useful collaborative work planning | Owner creates a project; members plan a story and tasks, complete its workflow, and review comments/history. |
| G-02 | Enforce organisation and role boundaries | Positive and cross-tenant negative cases pass for work, delivery, telemetry and MCP. |
| G-03 | Build and operate the PC server | Private access, reboot recovery, off-host backup and clean restoration are demonstrated. |
| G-04 | Produce repeatable engineering evidence | CI, UAT, immutable artifacts, release decisions and recovery results identify the tested revision/environment. |
| G-05 | Keep the architecture maintainable | Domain logic stays in the modular monolith; integrations use explicit boundaries and measured capacity. |
| G-06 | Make delivery traceable per work item | A story links to a PR, build, immutable artifact and confirmed deployment per service/environment, including failures and rollbacks. |
| G-07 | Support evidence-based investigation | An authorised user follows a deployment into bounded logs, health, traces and an incident without inventing missing or causal evidence. |
| G-08 | Expose the product through MCP | A supported client reads work/delivery/operational evidence and creates one issue safely through existing application services. |

Retain success-measure IDs SM-01–SM-05 for board journeys, invalid/concurrent operation handling and tenant checks; SM-06–SM-10 for reproducible build, CI, artifact promotion, recovery and security. Their executable criteria are in the [Release 1 contract](release-1-scope.md). New measures SM-11 (delivery attribution), SM-12 (authorised investigation), SM-13 (PC recovery) and SM-14 (MCP journey) are satisfied by R2-J01–R2-J06, H-01–H-08 and M-J01–M-J04 in their linked contracts. No user adoption or performance result is claimed without evidence.

## Release strategy

| Milestone | Product commitment | Forecast |
|---|---|---|
| Server foundation | PC ready for private application operation | S08 |
| Release 1 | Complete board, hierarchy, sprints, collaboration, Cognito and secure home-lab operation | S32 |
| Release 2 | Deployment tracking, in-app logs, health, trace links, alerts and incidents | S56 |
| MCP v1 | Controlled client access to the released work and investigation capabilities | S64 |

The complete board is delivered before customer-facing deployment tracking. Basic host/app monitoring exists earlier to operate TaskForge itself. These are separate uses of observability.

## Constraints

| ID | Constraint |
|---|---|
| C-01 | This planning revision changes documentation only. Later execution tasks declare their implementation boundary. |
| C-02 | Spring Boot modular monolith, PostgreSQL and Liquibase remain the application foundation. |
| C-03 | Angular/TypeScript provides the board and investigation UI. |
| C-04 | Ubuntu Server LTS on the user's PC is the initial server target; access is LAN/private VPN. |
| C-05 | Development identity is restricted to private development/MVP use; Cognito and tenant authorization are mandatory at Release 1. |
| C-06 | AWS application hosting is deferred; Cognito remains included. No hardware purchase or cloud spending is implied by planning. |
| C-07 | All sprints last 14 calendar days; initial capacity is 8–10 hours/sprint, with 6–8 hours planned delivery. |
| C-08 | MCP follows Release 2; every tool uses the product's application services and authorization. |
| C-09 | Deployments and rollbacks execute outside TaskForge, even when TaskForge is unavailable. |
| C-10 | Raw logs, metrics and traces remain in observability backends; application metadata remains in PostgreSQL. |

## Assumptions

| ID | Assumption | Validation |
|---|---|---|
| A-01 | All organisation members can use all organisation projects | Role/tenant acceptance; integration access can still be more restrictive |
| A-02 | Fixed statuses and a simple epic/story/task hierarchy satisfy initial planning | S09 journeys and S28 acceptance |
| A-03 | In-app and manually shared invitations are sufficient without outbound email | S15–S16 |
| A-04 | Earlier exploratory application data was disposable | Recheck before any future schema replacement; does not apply to PC disks or server data |
| A-05 | PC resources can support the selected stack with bounded retention | S01 assessment, S08 readiness, S55 measurements |
| A-06 | One contributor fills product, engineering and operator responsibilities | Separate evidence/self-review checkpoints each sprint |
| A-07 | GitHub/Actions is the first integration provider | S33 connector setup; outbound internet required |
| A-08 | Existing PC data must be preserved until assessed and backed up | S01; no blanket disk-erasure approval |
| A-09 | A backup destination outside the host can be made available | S01 feasibility; unresolved destination blocks server recovery acceptance |

## Non-goals through MCP v1

Preserve NG-01 (full Jira parity), NG-02 (mandatory Redis/Kafka/microservices/Kubernetes), NG-03 (semantic search/AI generation), NG-04 (attachments/watchers/due dates/outbound notifications), NG-05 (single-host HA claims), NG-06 (AWS application hosting) and NG-07 (public unauthenticated access) as exclusions through this horizon.

Also excluded: TaskForge-controlled deployment execution, arbitrary SQL/LogQL/PromQL tools, embedded full trace exploration, automated root-cause claims, additional Git providers and personal media/photo/file-sync services. Incoming operational alerts are included in Release 2 and are distinct from outbound notifications.

## Governance

The product owner accepts scope and release results; the delivery lead maintains capacity and dependencies; engineering owns contracts; the operator owns recovery; security/QA review evidence. One person may fill these roles, but writing a checklist does not satisfy it.

Review scope when audience, integration provider, access model, capacity or required capabilities change. Record trade-offs in the [decision log](../architecture/decision-log.md), [risk register](../delivery/risk-register.md) and [sprint roadmap](../delivery/sprint-roadmap.md) together. Calendar dates remain unset until Sprint 1 starts.

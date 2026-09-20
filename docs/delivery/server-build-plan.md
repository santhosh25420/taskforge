# PC-to-TaskForge Server Build Plan

Status: planned, not executed. Hardware, existing data, installation and backup destination are unverified. E-140, S01–S08 establish the server; later sprints expand the application and telemetry stack.

## Target

An Ubuntu Server LTS PC hosts private UAT/release environments and can be maintained from the MacBook over LAN/private VPN. Docker Compose runs TaskForge and its supporting services. Shared hardware remains one failure domain.

Personal media/photo/file-sync services, public ingress, Kubernetes and AWS application hosting are outside this plan.

## Inventory to establish in S01

| Required record | Present status | Acceptance |
|---|---|---|
| CPU, RAM, architecture and virtualization support | Unknown | Actual inventory and resource budget recorded |
| Disk identities, capacities, health, partitions and free space | Unknown | Exact device/mount mapping; no use of guessed disk names |
| Existing OS, files, workloads and preservation needs | Unknown; preserve by default | Owner-reviewed preservation inventory and verified backup before installation |
| Wired/wireless network, router access and address reservation | Unknown | Reachability and private address plan tested |
| Backup destination outside the host | Unknown | Destination available, sized and a sample restored |
| Install media, console/recovery access and power behaviour | Unknown | Recovery access and reboot procedure recorded |
| Internet access for packages, GitHub and Cognito | Unverified | Required outbound access works without public server ingress |

Capacity budgets must include UAT, release, database, telemetry, backup staging and operating-system headroom. If insufficient, reduce simultaneous UAT workload or retention explicitly; do not invent available capacity or silently drop acceptance gates.

## Build sequence and gates

| Gate | Sprint | Tasks | Evidence to accept |
|---|---|---|---|
| H-01 Assess and preserve | S01 | Inventory host/disks/data; test off-host backup; record resource and recovery prerequisites | Inventory plus sample restoration; installation targets identified and data preservation confirmed |
| H-02 Install and administer | S02 | Select supported Ubuntu Server LTS; install only on confirmed target; patch; create non-root operator; configure SSH keys | Console and SSH access after reboot; key access works before disabling password login |
| H-03 Private access | S03 | Reserve LAN address; configure host firewall and VPN; establish private naming/HTTPS approach | Mac reaches intended private endpoints; unapproved inbound paths fail; recovery access retained |
| H-04 Persistent host | S04 | Configure identified storage/mounts/permissions; time sync; disk checks; document reboot/power recovery | Mounts, clock and administration survive reboot; disk failure/space alerts have an owner |
| H-05 Container platform | S05 | Install supported Docker Engine/Compose; create networks, UAT/release volume and secret boundaries; resource limits | Disposable smoke stack restarts; UAT cannot write release data; DB/management ports not publicly reachable |
| H-06 Database and backups | S06 | Run PostgreSQL with persistent storage; schedule encrypted/authenticated off-host backup as appropriate; restore into isolated storage | Restored fixture checks pass and backup age/failure are inspectable |
| H-07 Host visibility | S07 | Install Prometheus/Grafana and host metrics; set disk/resource/backup-age signals and retention | Controlled unhealthy/expired-backup signal is visible; dashboards private; disk growth bounded |
| H-08 Backend rehearsal | S08 | Package/configure existing backend with fresh secret/TLS material; deploy privately; smoke/restart/restore and rollback rehearsal | Exact backend revision and observed version recorded; host reboot and private-only boundary verified |

H-08 is an early backend rehearsal using current capabilities, not a complete board release. The repeatable Angular/CI/image-registry walking slice follows in S09–S12. PostgreSQL/Liquibase issues discovered here become tracked defects rather than bypassing migrations.

An OS install or disk repartition task must identify actual devices and verify preservation at execution time. This planning document is not authorisation to erase unknown PC data.

## Private deployment workflow

GitHub Actions eventually builds/tests images and publishes them by immutable digest. The operator uses a private-network script to select and deploy an approved digest, run migrations under the compatibility policy, smoke-test the actual running version and record the outcome.

Scripts can run without TaskForge and do not expose host credentials or the Docker socket to the application. At S38 they gain a durable local receipt spool and authenticated delivery; after an outage they replay until acknowledged. Promotion and rollback are external operator operations, not product buttons.

Keep UAT/release secrets and volumes separate. Record incompatible migration cases before attempting an application rollback; a previous image alone cannot undo a destructive schema change.

## Later server work

| Sprints | Extension |
|---|---|
| S10–S12 | Supported runtime alignment, CI, image registry, immutable walking-slice delivery |
| S26–S32 | Cognito/private HTTPS configuration, release security, backup/restore and rollback gates |
| S33–S42 | Outbound GitHub synchronisation, producer credentials, durable receipts and reconciliation |
| S43–S48 | Collector/OpenTelemetry, Loki/Tempo, tenant-aware access, bounded retention and investigation links |
| S49–S56 | Alert ingestion, source outage recovery, capacity and complete Release 2 restoration |
| S57–S64 | Private MCP endpoint/client reachability, protocol authentication and disable/rollback checks |

## Runbook deliverables

Installation and access; storage and reboot; configuration/secrets; deployment/promotion/rollback; backup/restore; telemetry/retention; incident response; credential/certificate renewal. Each has prerequisites, exact tested commands, expected output, failure recovery and evidence. Commands are added only after actual environment discovery.

Refer to the [home-lab checklist](homelab-delivery-checklist.md) for ongoing gates and the [sprint roadmap](sprint-roadmap.md) for story/task allocation. The wider home-lab learning notes are background; this document is the TaskForge server scope.

Official implementation references: [Ubuntu OpenSSH](https://ubuntu.com/server/docs/how-to/security/openssh-server/), [Docker on Ubuntu](https://docs.docker.com/engine/install/ubuntu/), [GitHub API polling constraints](https://docs.github.com/en/rest/using-the-rest-api/best-practices-for-using-the-rest-api), [Loki authentication](https://grafana.com/docs/loki/latest/operations/authentication/).

# TaskForge Schema Diagram

This schema diagram is translated from [E-R-diagram.pdf](./E-R-diagram.pdf). It keeps the original entities and relationship intent, but expresses them as implementation-ready relational tables.

```mermaid
erDiagram
    USERS ||--o{ ORGANISATIONS : owns
    ORGANISATIONS ||--o{ ORGANISATION_MEMBERS : has
    USERS ||--o{ ORGANISATION_MEMBERS : joins

    ORGANISATIONS ||--o{ PROJECTS : contains
    PROJECTS ||--o{ PROJECT_MEMBERS : has
    USERS ||--o{ PROJECT_MEMBERS : joins

    PROJECTS ||--o{ TEAMS : contains
    TEAMS ||--o{ TEAM_MEMBERS : has
    USERS ||--o{ TEAM_MEMBERS : joins

    PROJECTS ||--o{ TASKS : contains
    TEAMS |o--o{ TASKS : groups
    USERS ||--o{ TASKS : owns
    USERS |o--o{ TASKS : assigned_to

    ORGANISATIONS ||--o{ LABELS : defines
    TASKS ||--o{ TASK_LABELS : tagged_with
    LABELS ||--o{ TASK_LABELS : applied_to

    USERS {
        uuid id PK
        varchar name
        varchar email
        audit_columns audits
    }

    ORGANISATIONS {
        uuid id PK
        varchar name
        varchar slug
        uuid owner_id FK
        audit_columns audits
    }

    ORGANISATION_MEMBERS {
        uuid id PK
        uuid organisation_id FK
        uuid user_id FK
        varchar role
        audit_columns audits
    }

    PROJECTS {
        uuid id PK
        varchar name
        uuid organisation_id FK
        varchar slug
        audit_columns audits
    }

    PROJECT_MEMBERS {
        uuid id PK
        uuid project_id FK
        uuid user_id FK
        varchar role
        audit_columns audits
    }

    TEAMS {
        uuid id PK
        varchar name
        uuid project_id FK
        varchar slug
        audit_columns audits
    }

    TEAM_MEMBERS {
        uuid id PK
        uuid team_id FK
        uuid user_id FK
        varchar role
        audit_columns audits
    }

    TASKS {
        uuid id PK
        varchar title
        text description
        uuid project_id FK
        uuid team_id FK
        varchar status
        uuid owner_id FK
        uuid assignee_id FK
        audit_columns audits
    }

    LABELS {
        uuid id PK
        varchar name
        varchar color
        uuid organisation_id FK
        audit_columns audits
    }

    TASK_LABELS {
        uuid id PK
        uuid task_id FK
        uuid label_id FK
        audit_columns audits
    }
```

## Relationship Notes

- `organisations.owner_id` points to `users.id`.
- `organisation_members`, `project_members`, and `team_members` model membership and role assignment.
- `projects.organisation_id` scopes projects to an organisation.
- `teams.project_id` scopes teams to a project.
- `tasks.project_id` scopes tasks to a project.
- `tasks.team_id` is modeled as optional because a task may exist at project level before being assigned to a team.
- `tasks.owner_id` points to the user who created or owns the task.
- `tasks.assignee_id` is modeled as optional because tasks can be unassigned.
- `labels.organisation_id` scopes reusable labels to an organisation.
- `task_labels` is the many-to-many join table between `tasks` and `labels`.

## Suggested Constraints

- `users.email` should be unique.
- `organisations.slug` should be unique.
- `projects` should have a unique `(organisation_id, slug)`.
- `teams` should have a unique `(project_id, slug)`.
- `labels` should have a unique `(organisation_id, name)`.
- `organisation_members` should have a unique `(organisation_id, user_id)`.
- `project_members` should have a unique `(project_id, user_id)`.
- `team_members` should have a unique `(team_id, user_id)`.
- `task_labels` should have a unique `(task_id, label_id)`.

# Normalisation Report

This report is based on the tables currently defined in the database migrations:

- `users`
- `organisations`
- `organisation_members`
- `projects`

The ER diagram also contains `project_members`, `teams`, `team_members`,
`tasks`, `labels`, and `task_labels`, but those tables are not yet defined in
the current migrations and cannot be assessed here.

## Normalisation criteria

- **1NF:** every attribute contains one atomic value, with no repeating groups
  or lists stored in a column.
- **2NF:** the relation is in 1NF and every non-key attribute depends on the
  whole candidate key. This matters only when a composite candidate key exists.
- **3NF:** the relation is in 2NF and no non-key attribute depends on another
  non-key attribute. In other words, non-key attributes depend on the key, the
  whole key, and nothing but the key.

## `users`

### Relation and keys

```text
USERS(id, name, email, created_at, updated_at)
```

- Primary/candidate key: `id`
- Alternate candidate key: `email`, because it is `NOT NULL UNIQUE`
- `id` and `email` are both single-column keys.

### Functional dependencies

```text
id    -> name, email, created_at, updated_at
email -> id, name, created_at, updated_at
```

### 1NF

The relation is in 1NF. Every column contains a single scalar value, and there
are no repeating groups or list-valued columns.

### 2NF

The relation is in 2NF because all candidate keys consist of a single
attribute. Therefore, partial dependency on part of a composite key is not
possible.

### 3NF

The relation is in 3NF. `name`, `email`, `created_at`, and `updated_at` depend
directly on either candidate key. There is no identified dependency between
non-key attributes.

## `organisations`

### Relation and keys

```text
ORGANISATIONS(id, name, owner_id, created_at, updated_at)
```

- Primary/candidate key: `id`
- `owner_id` is a foreign key referencing `users(id)`; it is not a candidate
  key because one user may own multiple organisations.
- `name` is not a candidate key because the database does not require it to be
  unique.

### Functional dependencies

```text
id -> name, owner_id, created_at, updated_at
```

### 1NF

The relation is in 1NF. Every column contains one scalar value, and there are
no repeating groups or list-valued columns.

### 2NF

The relation is in 2NF because its only defined candidate key is the
single-column key `id`.

### 3NF

The relation is in 3NF based on the current attributes and constraints. The
organisation's `owner_id` identifies a user, but user attributes are not stored
in this relation. Therefore, there is no transitive dependency through
`owner_id`.

## `organisation_members`

### Relation and keys

```text
ORGANISATION_MEMBERS(
    id,
    organisation_id,
    user_id,
    name,
    role,
    created_at,
    updated_at
)
```

- Primary/candidate key in the current database: `id`
- Intended business key: `(organisation_id, user_id)`
- The intended business key is not currently enforced because there is no
  `UNIQUE (organisation_id, user_id)` constraint.

### 1NF

The relation is in 1NF if `name` and `role` each represent one scalar value.
There are no repeating groups or list-valued columns.

### 2NF

With the current schema, the only enforced candidate key is the single-column
key `id`, so partial dependency does not apply.

If `(organisation_id, user_id)` is made a candidate key, `role` must depend on
the complete pair, not on only `organisation_id` or only `user_id`. This is the
expected dependency for an organisation membership role.

### 3NF and duplication risk

The relation is in 3NF only if `name` is a membership-specific value, such as a
display name chosen for that organisation.

If `name` duplicates `users.name`, the following transitive dependency exists:

```text
organisation_members.id -> user_id -> users.name
```

That duplicates user data and can create update anomalies. In that case,
`name` should be removed from `organisation_members` and read from `users`, or
renamed/documented as an intentionally membership-specific display name.

The recommended constraint is:

```sql
ALTER TABLE organisation_members
    ADD CONSTRAINT uq_organisation_member
    UNIQUE (organisation_id, user_id);
```

## `projects`

### Relation and keys

```text
PROJECTS(id, title, organisation_id, slug, created_at, updated_at)
```

- Primary/candidate key: `id`
- `organisation_id` is a foreign key referencing `organisations(id)`.
- `slug` has a global `UNIQUE` constraint but is nullable. It is therefore not
  a complete candidate key for all rows unless it is also made `NOT NULL`.

### Functional dependencies

```text
id -> title, organisation_id, slug, created_at, updated_at
```

If `slug` is made `NOT NULL`, the unique constraint also gives:

```text
slug -> id, title, organisation_id, created_at, updated_at
```

### 1NF

The relation is in 1NF. Each column contains one scalar value, with no
repeating groups or list-valued columns.

### 2NF

The relation is in 2NF because the defined candidate key `id` is a single
attribute. No partial dependency is possible.

### 3NF

The relation is in 3NF based on the current attributes. Project title,
organisation, slug, and audit timestamps depend directly on the project key;
no project or organisation attributes are duplicated here.

If slugs are intended to be unique only within an organisation, the constraint
should instead be a composite constraint on `(organisation_id, slug)`.

## Summary

| Relation | Current normalisation result | Action |
|---|---|---|
| `users` | 3NF | Keep the primary and alternate keys documented. |
| `organisations` | 3NF | Treat `owner_id` as a foreign key, not a candidate key. |
| `organisation_members` | Conditional 3NF | Remove duplicated user name, or define it as membership-specific; add the composite uniqueness constraint. |
| `projects` | 3NF | Make `slug` non-null if it is intended to be a candidate key. |

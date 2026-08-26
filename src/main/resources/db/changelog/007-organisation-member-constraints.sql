UPDATE organisation_members
SET name = users.name
FROM users
WHERE organisation_members.user_id = users.id
  AND organisation_members.name IS NULL;

UPDATE organisation_members
SET role = 'MEMBER'
WHERE role IS NULL;

ALTER TABLE organisation_members
    ALTER COLUMN name SET NOT NULL,
    ALTER COLUMN role SET NOT NULL;

ALTER TABLE organisation_members
    ADD CONSTRAINT uq_organisation_member
    UNIQUE (organisation_id, user_id);

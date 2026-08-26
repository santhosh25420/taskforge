ALTER TABLE organisations
    ADD COLUMN slug VARCHAR(250),
    ADD CONSTRAINT organisation_slug_unique UNIQUE (slug);

ALTER TABLE organisations
ADD CONSTRAINT UK_organisation_name_owner_id UNIQUE(owner_id,name);

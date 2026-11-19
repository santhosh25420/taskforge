package com.sk.taskforge.organisation.persistence;

import com.sk.taskforge.organisation.domain.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrganisationRepository extends JpaRepository<Organisation, UUID> {

}

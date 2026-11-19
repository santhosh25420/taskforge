package com.sk.taskforge.organisation.persistence;

import com.sk.taskforge.organisation.domain.OrganisationMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrganisationMemberRepository extends JpaRepository<OrganisationMembers, UUID> {
}

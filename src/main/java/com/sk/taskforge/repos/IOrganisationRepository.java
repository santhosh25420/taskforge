package com.sk.taskforge.repos;

import com.sk.taskforge.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrganisationRepository extends JpaRepository<Organisation, UUID> {

}

package com.sk.taskforge.configs;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

public interface MyContainers {

    @Container
    @ServiceConnection
    PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:13");
}

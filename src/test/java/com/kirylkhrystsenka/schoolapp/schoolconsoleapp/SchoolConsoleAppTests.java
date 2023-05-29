package com.kirylkhrystsenka.schoolapp.schoolconsoleapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class SchoolConsoleAppTests {
//    @Container
//    public static PostgreSQLContainer container = new PostgreSQLContainer()
//            .withUsername("postgres")
//            .withPassword("T3n$hi7892")
//            .withDatabaseName("postgres");
//    @DynamicPropertySource
//    static void properties(DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//    }

    @Test
    void contextLoads() {
    }

}

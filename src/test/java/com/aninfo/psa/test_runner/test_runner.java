package com.aninfo.psa.test_runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.ANY, connection= EmbeddedDatabaseConnection.H2)
@CucumberOptions(
        features = "src/test/resources"
        ,glue={"com/aninfo/psa"}
        ,publish = true
)
public class test_runner {
}

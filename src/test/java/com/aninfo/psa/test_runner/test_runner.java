package com.aninfo.psa.test_runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources"
        ,glue={"com/aninfo/psa"}
        ,publish = true
)
public class test_runner {
}

package com.aninfo.psa.test_runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources"
        ,glue={"src/test/java/com/aninfo/psa/"}
)
public class test_runner {
}

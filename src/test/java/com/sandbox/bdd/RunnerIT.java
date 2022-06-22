package com.sandbox.bdd;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@ContextConfiguration
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com/sandbox/bdd/stepDefs","src/test/resources"},
        dryRun = false,
        tags = {"@add_customer","@add_paymentMethod","@charge_transaction","@capture_transaction"})
public class RunnerIT {

}

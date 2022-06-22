package com.sandbox.bdd.response;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sandbox.bdd.stepDefs.BaseStepDef;

public class ScenarioHook {
    private static final Logger LOG = LoggerFactory.getLogger(BaseStepDef.class);
    @After(order = 0)
    public void status(Scenario scenario) {
        LOG.info("STATUS: {}", scenario.getStatus());
    }
}


package com.epam.training.munara_narkozieva.base;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    //protected String browserType;


}
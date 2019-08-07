package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author carl
 */
@Slf4j
public class H2MethodResetExtension implements Extension, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        log.info("===================clean before each test : {} =========================", context.getTestMethod().get());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        log.info("===================clean after each test : {} =========================", context.getTestMethod().get());
    }
}

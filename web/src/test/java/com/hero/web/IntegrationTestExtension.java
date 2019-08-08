package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author carl
 */
@Slf4j
public class IntegrationTestExtension implements Extension, BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        log.info("===================before all tests=========================");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        log.info("===================after all tests=========================");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        log.info("===================before each test : {} =========================", context.getTestMethod().get());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        log.info("===================after each test : {} =========================", context.getTestMethod().get());
    }
}

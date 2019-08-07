package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author carl
 */
@Slf4j
public class H2ClassResetExtension implements Extension, BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        log.info("===================clean before all tests=========================");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        log.info("===================clean after all tests=========================");
    }
}

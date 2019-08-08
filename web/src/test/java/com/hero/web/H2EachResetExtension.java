package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author carl
 */
@Slf4j
public class H2EachResetExtension implements Extension, BeforeEachCallback {

    static Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./target/test", "sa", "password").load();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        log.info("=================== clean db before method : {} =========================", context.getTestMethod().get());
        flyway.clean();
        flyway.migrate();
    }
}

package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author carl
 */
@Slf4j
public class H2OnceResetExtension implements Extension, BeforeAllCallback {
    static Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./target/test", "sa", "password").load();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        log.info("=================== clean db before all tests =========================");
        flyway.clean();
        flyway.migrate();
    }
}

package com.hero.web;

import lombok.extern.slf4j.Slf4j;
import org.h2.command.ddl.CreateSchema;
import org.h2.command.ddl.DropSchema;
import org.h2.engine.ConnectionInfo;
import org.h2.engine.Engine;
import org.h2.engine.Session;
import org.h2.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author carl
 */
@Slf4j
public class H2Utils {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./target/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "password";
    private static final String DEFAULT_SCHEMA = "PUBLIC";

    public void destroyDb() {

    }

    public static void initDb() {
        try (Connection conn = getConnection()) {
            log.info("schema : {}", conn.getSchema());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ss() {
        ConnectionInfo info = new ConnectionInfo(URL);
        info.setUserName(USERNAME);
        info.setUserPasswordHash(PASSWORD.getBytes());

        Session session = Engine.getInstance().createSession(info);

        session.getDatabase().getAllSchemas().forEach(x -> {
            log.info("schema-------------");
            log.info(x.getName());
            log.info("tables-------------");
            x.getAllTablesAndViews().forEach(t -> {
                log.info(t.getName());
            });
        });

        session.close();
    }

    public void dropSchema(String schema, Session session) {
        DropSchema dropSchema = new DropSchema(session);
        dropSchema.setSchemaName(schema);
        log.info(dropSchema.getSQL());
        dropSchema.update();
    }

    public void createSchema(String schema, Session session) {
        CreateSchema createSchema = new CreateSchema(session);
        createSchema.setSchemaName(schema);
        log.info(createSchema.getSQL());
        createSchema.update();
    }

    private static Connection getConnection() {
        try {
            return JdbcUtils.getConnection(DRIVER, URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}

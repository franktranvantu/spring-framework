package com.franktranvantu.transactionmanagement;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

public class TestUtils {
    public static void freshDatabase(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/db/schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/db/data.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}

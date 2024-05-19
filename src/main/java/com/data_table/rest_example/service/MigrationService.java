package com.data_table.rest_example.service;

import com.data_table.rest_example.ApplicationConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.flywaydb.core.Flyway;

@ApplicationScoped
public class MigrationService {
    final ApplicationConfigProperty applicationConfigProperty;

    @Inject
    public MigrationService(ApplicationConfigProperty applicationConfigProperty) {
        this.applicationConfigProperty = applicationConfigProperty;
    }

    public void migrate() {
        Flyway flyway = Flyway.configure().dataSource(applicationConfigProperty.getDatasourceJdbcUrl(),
                applicationConfigProperty.getDatasourceUsername(),
                applicationConfigProperty.getDatasourcePassword()).load();
        flyway.migrate();
    }

}

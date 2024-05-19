package com.data_table.rest_example;

import com.data_table.rest_example.service.MigrationService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MicroServiceApplication implements QuarkusApplication {

    final MigrationService migrationService;

    @Inject
    public MicroServiceApplication(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @Override
    public int run(String... args) {
//        migrationService.migrate();
        Quarkus.waitForExit();
        return 0;
    }
}

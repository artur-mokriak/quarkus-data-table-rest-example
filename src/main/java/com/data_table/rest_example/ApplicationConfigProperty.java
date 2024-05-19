package com.data_table.rest_example;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ApplicationConfigProperty {
    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String datasourceJdbcUrl;

    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;

    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;

    public ApplicationConfigProperty() {
    }

    public String getDatasourceJdbcUrl() {
        return datasourceJdbcUrl;
    }

    public void setDatasourceJdbcUrl(String datasourceJdbcUrl) {
        this.datasourceJdbcUrl = datasourceJdbcUrl;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public void setDatasourceUsername(String datasourceUsername) {
        this.datasourceUsername = datasourceUsername;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public void setDatasourcePassword(String datasourcePassword) {
        this.datasourcePassword = datasourcePassword;
    }
}

package com.data_table.rest_example;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main {

    final MicroServiceApplication microServiceApplication;

    @Inject
    public Main(MicroServiceApplication microServiceApplication) {
        this.microServiceApplication = microServiceApplication;
    }

    public static void main(String ... args) {
        System.out.println("Running main method");
        Quarkus.run(MicroServiceApplication.class, args);
    }
}

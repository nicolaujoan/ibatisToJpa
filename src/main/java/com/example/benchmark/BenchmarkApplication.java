package com.example.benchmark;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BenchmarkApplication {
    public static void main(String[] args) {
        // Not used directly; Spring is started by the benchmarks.
        new SpringApplicationBuilder(BenchmarkApplication.class)
            .run(args);
    }
}

package com.example.benchmark.util;

import com.example.benchmark.BenchmarkApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringHolder {
    private static ConfigurableApplicationContext context;

    public static synchronized ConfigurableApplicationContext getContext() {
        if (context == null) {
            context = new SpringApplicationBuilder(BenchmarkApplication.class)
                    .properties("spring.main.web-application-type=none")
                    .run();
        }
        return context;
    }
}

package com.example.benchmark.bench;

import com.example.benchmark.util.DataLoader;
import com.example.benchmark.util.SpringHolder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.springframework.context.ConfigurableApplicationContext;

@State(Scope.Benchmark)
public class BaseBenchmark {
    protected ConfigurableApplicationContext ctx;

    @Setup(Level.Trial)
    public void setup() {
        ctx = SpringHolder.getContext();
        // Load data once
        ctx.getBean(DataLoader.class).ensureLoaded();
    }
}

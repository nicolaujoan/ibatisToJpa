package com.example.benchmark.bench;

import com.example.benchmark.dto.UserProjection;
import com.example.benchmark.repo.UserRepository;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;

public class SpringProjectionBenchmark extends BaseBenchmark {

    @Benchmark
    public List<UserProjection> run() {
        UserRepository repo = ctx.getBean(UserRepository.class);
        return repo.findAllUsersProjection();
    }
}

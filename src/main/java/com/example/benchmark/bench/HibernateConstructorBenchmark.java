package com.example.benchmark.bench;

import com.example.benchmark.dto.UserDTO;
import jakarta.persistence.EntityManager;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;

public class HibernateConstructorBenchmark extends BaseBenchmark {

    @Benchmark
    @SuppressWarnings("unchecked")
    public List<UserDTO> run() {
        EntityManager em = ctx.getBean(EntityManager.class);
        String sql = "SELECT id, first_name, last_name FROM users";
        return (List<UserDTO>) em.createNativeQuery(sql, "UserDTOMap")
                .getResultList();
    }
}

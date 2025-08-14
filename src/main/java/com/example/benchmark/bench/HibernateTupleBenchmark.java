package com.example.benchmark.bench;

import com.example.benchmark.dto.UserDTO;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;

public class HibernateTupleBenchmark extends BaseBenchmark {

    @Benchmark
    public List<UserDTO> run() {
        EntityManager em = ctx.getBean(EntityManager.class);
        String sql = "SELECT id, first_name, last_name FROM users";
        return em.unwrap(Session.class)
                .createNativeQuery(sql)
                .setTupleTransformer((tuple, aliases) ->
                        new UserDTO((Long) tuple[0], (String) tuple[1], (String) tuple[2]))
                .list();
    }
}

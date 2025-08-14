package com.example.benchmark.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader {

    private final JdbcTemplate jdbc;
    private final int total;
    private final int batchSize;

    public DataLoader(JdbcTemplate jdbc,
                      @Value("${app.users-count}") int total,
                      @Value("${app.batch-size}") int batchSize) {
        this.jdbc = jdbc;
        this.total = total;
        this.batchSize = batchSize;
    }

    public void ensureLoaded() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'USERS'", Integer.class);
        // Table created by JPA on startup. Check row count.
        Integer rows = jdbc.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        if (rows != null && rows >= total) return;

        Random r = new Random(42);
        String sql = "INSERT INTO users(first_name, last_name) VALUES(?, ?)";
        List<Object[]> batch = new ArrayList<>(batchSize);

        for (int i = 0; i < total; i++) {
            String fn = "FN" + r.nextInt(1_000_000);
            String ln = "LN" + r.nextInt(1_000_000);
            batch.add(new Object[]{fn, ln});
            if (batch.size() == batchSize) {
                jdbc.batchUpdate(sql, batch);
                batch.clear();
            }
        }
        if (!batch.isEmpty()) jdbc.batchUpdate(sql, batch);
    }
}

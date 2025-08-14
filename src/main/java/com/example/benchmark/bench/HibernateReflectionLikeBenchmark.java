package com.example.benchmark.bench;

import com.example.benchmark.dto.UserBeanDTO;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.openjdk.jmh.annotations.Benchmark;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateReflectionLikeBenchmark extends BaseBenchmark {

    @Benchmark
    public List<UserBeanDTO> run() {
        EntityManager em = ctx.getBean(EntityManager.class);
        String sql = "SELECT id AS id, first_name AS firstName, last_name AS lastName FROM users";

        List<Object[]> rows = em.unwrap(Session.class)
                .createNativeQuery(sql)
                .list();

        // Simulate reflection-based alias-to-bean mapping:
        Map<String, Method> setters = new HashMap<>();
        try {
            setters.put("id", UserBeanDTO.class.getMethod("setId", Long.class));
            setters.put("firstname", UserBeanDTO.class.getMethod("setFirstName", String.class));
            setters.put("lastname", UserBeanDTO.class.getMethod("setLastName", String.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Aliases order is known from SELECT
        String[] aliases = new String[]{"id", "firstname", "lastname"};

        List<UserBeanDTO> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            UserBeanDTO bean = new UserBeanDTO();
            for (int i = 0; i < row.length; i++) {
                Method m = setters.get(aliases[i]);
                try {
                    m.invoke(bean, row[i]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            result.add(bean);
        }
        return result;
    }
}

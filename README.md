# Hibernate Mapping Benchmark (Spring Boot + Hibernate 6 + H2 + JMH)

Benchmarks four native-query mapping styles:

1) Hibernate native + manual tuple mapping (no reflection)
2) Hibernate native + @ConstructorResult (DTO constructor)
3) Hibernate native + reflection-like mapping (simulates AliasToBean)
4) Spring Data JPA native + interface projection

## Build & Run

```bash
# Optionally tune data size (defaults to 1,000,000 rows) and batch size
export USERS_COUNT=1000000
export BATCH_SIZE=10000

mvn -q -e -DskipTests clean package
java -jar target/benchmarks.jar
```

JMH output will list average times (ms).

> Note: The 'reflection-like' benchmark emulates the behavior of Hibernate's legacy AliasToBeanResultTransformer,
> which is not available in Hibernate 6. This still captures the reflective mapping overhead for comparison.

##### Resources:

ibatis: https://archive.apache.org/dist/ibatis/binaries/ibatis.java/ibatis-3/ibatis-3-core-3.0.0-190/iBATIS-3-User-Guide.pdf

vlad: https://vladmihalcea.com/spring-jpa-dto-projection/

gpt chat: https://chatgpt.com/share/689e1527-7f1c-800e-be5d-aaf68f8e1bed

call python script from maven build: https://gist.github.com/busbey/bc6022b69ae7d2f1502bdbed7c1737c6

interesting post: https://stackoverflow.com/questions/1984548/hibernate-vs-ibatis

check high performance java persistence book
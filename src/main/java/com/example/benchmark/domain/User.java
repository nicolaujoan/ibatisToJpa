package com.example.benchmark.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@SqlResultSetMapping(
    name = "UserDTOMap",
    classes = @ConstructorResult(
        targetClass = com.example.benchmark.dto.UserDTO.class,
        columns = {
            @ColumnResult(name = "id", type = Long.class),
            @ColumnResult(name = "first_name", type = String.class),
            @ColumnResult(name = "last_name", type = String.class)
        }
    )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}

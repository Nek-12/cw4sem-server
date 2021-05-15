package com.fantastictrio.cw4sem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "[\\w-.]{3,31}")
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "[\\x21-\\x7E]{8,64}")
    private String password;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Nationalized
    @Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Nationalized
    @Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Enumerated
    private Role role;

    @Column(name = "created_date")
    private Instant createdDate;

    public interface UserProjection {
        Integer getId();
        String getUsername();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

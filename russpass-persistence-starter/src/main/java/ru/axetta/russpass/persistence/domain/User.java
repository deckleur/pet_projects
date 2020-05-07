package ru.axetta.russpass.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "russpass_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @Column(name = "email", nullable = false, length = 150)
    @Getter @Setter
    private String email;

    @Column(name = "password", nullable = false, length = 150)
    @Getter @Setter
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @Getter @Setter
    private Role role;

    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(name = "middle_name")
    @Getter @Setter
    private String middleName;

    @Column(name = "image_url", length = 1000)
    @Getter @Setter
    private String imageUrl;

    @Column(name = "google_id")
    @Getter @Setter
    private String googleId;

    @Column(name = "facebook_id")
    @Getter @Setter
    private String facebookId;

    @Column(name = "email_confirmed", nullable = false)
    @Getter @Setter
    private boolean emailConfirmed;

    @Column(name = "deleted", nullable = false)
    @Getter @Setter
    private boolean deleted;

    @Column(name = "blocked", nullable = false)
    @Getter @Setter
    private boolean blocked;

    @Column(name = "created", nullable = false)
    @Getter @Setter
    private LocalDateTime created;

    @Column(name = "parameters", columnDefinition = "text")
    @Getter @Setter
    private String parameters;

    public User(String email, String password, String lastName, String firstName, String middleName,
                String imageUrl, String parameters) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.imageUrl = imageUrl;
        this.parameters = parameters;
        this.created = LocalDateTime.now();
    }

}

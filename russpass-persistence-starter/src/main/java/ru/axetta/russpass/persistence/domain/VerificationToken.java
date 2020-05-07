package ru.axetta.russpass.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "russpass_verification_token")
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false, length = 50)
    @Getter @Setter
    private Type tokenType;

    @Column(name = "token", nullable = false, length = 30)
    @Getter @Setter
    private String token;

    @Column(name = "expire", nullable = false)
    @Getter @Setter
    private LocalDateTime expire;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private User user;

    public VerificationToken(Type tokenType, String token, LocalDateTime expire, User user) {
        this.tokenType = tokenType;
        this.token = token;
        this.expire = expire;
        this.user = user;
    }

    public enum Type {
        CONFIRM_EMAIL,
        DELETE_ACCOUNT,
        CHANGE_PASSWORD;
    }
}

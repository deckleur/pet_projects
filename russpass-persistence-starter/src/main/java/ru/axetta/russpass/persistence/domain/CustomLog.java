package ru.axetta.russpass.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "russpass_custom_log")
@NoArgsConstructor
public class CustomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @Column(name = "type", nullable = false, length = 50)
    @Getter @Setter
    private CustomLogType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private User user;

    @Column(name = "created", nullable = false)
    @Getter @Setter
    private LocalDateTime created;

    @Column(name = "message", length = 1000)
    @Getter @Setter
    private String message;

    public CustomLog(CustomLogType type, User user, String message) {
        this.type = type;
        this.user = user;
        this.created = LocalDateTime.now();
        this.message = message;
    }
}

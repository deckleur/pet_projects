package ru.axetta.russpass.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "russpass_property")
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @Column(name = "key", nullable = false, unique = true)
    @Getter @Setter
    private String key;

    @Column(name = "value", nullable = false, length = 100000)
    @Getter @Setter
    private String value;

}

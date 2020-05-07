package ru.axetta.russpass.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "russpass_menu_item")
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "uri", nullable = false, unique = true, length = 150)
    @Getter @Setter
    private String uri;

    @Column(name = "methods", nullable = false, unique = true, length = 150)
    @Getter @Setter
    private String methods;

    @Column(name = "description", nullable = false, length = 1000)
    @Getter @Setter
    private String description;

    @ManyToMany
    @Getter @Setter
    private Set<Role> roles;

    public MenuItem(String name, String uri, String methods, String description) {
        this.name = name;
        this.uri = uri;
        this.methods = methods;
        this.description = description;
    }
}

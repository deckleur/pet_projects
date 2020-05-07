package ru.axetta.russpass.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "russpass_role")
@NoArgsConstructor
public class Role {

    public static final String ADMIN_ROLE = "admin";
    public static final String USER_ROLE = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    @Getter @Setter
    private String name;

    @Column(name = "description", length = 2000)
    @Getter @Setter
    private String description;

    @ManyToMany
    @JoinTable(
            name = "russpass_menu_item_to_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    @Getter @Setter
    private Set<MenuItem> menuItems;

    @Column(name = "editable", nullable = false)
    @Getter @Setter
    private boolean editable;

    @Column(name = "parameters", columnDefinition = "text")
    @Getter @Setter
    private String parameters;

    public Role(String name, String description, String parameters) {
        this.name = name;
        this.description = description;
        this.editable = true;
        this.parameters = parameters;
    }
}

package ru.axetta.russpass.persistence.dao;

import org.springframework.data.jpa.domain.Specification;
import ru.axetta.russpass.persistence.domain.Role;
import ru.axetta.russpass.persistence.domain.User;

public class UserSpecifications {

    public static Specification<User> hasRole(Role role) {
        return role == null ? Specification.where(null) :
                (user, cq, cb) -> cb.equal(user.get("role"), role);
    }

    public static Specification<User> emailContains(String email) {
        return email == null ? Specification.where(null) :
                (user, cq, cb) -> cb.like(user.get("email"), "%" + email + "%");
    }

    public static Specification<User> lastNameContains(String lastName) {
        return lastName == null ? Specification.where(null) :
                (user, cq, cb) -> cb.like(user.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<User> isBlocked(Boolean blocked) {
        return blocked == null ? Specification.where(null) :
                (user, cq, cb) -> cb.equal(user.get("blocked"), blocked);
    }

    public static Specification<User> isDeleted(Boolean deleted) {
        return deleted == null ? Specification.where(null) :
                (user, cq, cb) -> cb.equal(user.get("deleted"), deleted);
    }
}

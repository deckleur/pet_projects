package ru.axetta.russpass.persistence.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.axetta.russpass.persistence.domain.Role;
import ru.axetta.russpass.persistence.domain.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query
    User findByIdAndDeleted(long id, boolean deleted);

    default User findExistingUser(long id) {
        return findByIdAndDeleted(id, false);
    }

    @Query
    User findByEmailAndDeleted(String email, boolean deleted);

    default User findExistingUser(String email) {
        return findByEmailAndDeleted(email, false);
    }

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE russpass_user set role_id = 2 WHERE role_id = :roleId")
    void resetUsersRole(@Param("roleId") long roleId);
}

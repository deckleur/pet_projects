package ru.axetta.russpass.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.axetta.russpass.persistence.domain.MenuItem;
import ru.axetta.russpass.persistence.domain.Role;

@Repository
public interface MenuItemDao extends JpaRepository<MenuItem, Long> {

}

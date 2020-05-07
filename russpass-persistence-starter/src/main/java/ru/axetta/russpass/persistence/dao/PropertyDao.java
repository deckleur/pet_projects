package ru.axetta.russpass.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.axetta.russpass.persistence.domain.Property;

@Repository
public interface PropertyDao extends JpaRepository<Property, Long> {
}

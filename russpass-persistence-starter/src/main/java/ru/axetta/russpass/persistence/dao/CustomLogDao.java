package ru.axetta.russpass.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.axetta.russpass.persistence.domain.CustomLog;

@Repository
public interface CustomLogDao extends JpaRepository<CustomLog, Long> {

}

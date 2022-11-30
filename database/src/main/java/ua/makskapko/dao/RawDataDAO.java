package ua.makskapko.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.makskapko.entity.RawData;

public interface RawDataDAO extends JpaRepository<RawData, Long> {
}

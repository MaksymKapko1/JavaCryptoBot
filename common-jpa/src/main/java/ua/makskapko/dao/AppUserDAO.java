package ua.makskapko.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.makskapko.entity.AppUser;

public interface AppUserDAO extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByTelegramUserId(Long id);
}

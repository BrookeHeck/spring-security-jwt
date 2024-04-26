package study.games.flashcard.wars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.games.flashcard.wars.models.entities.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByUsername(String username);

    AppUser findAppUserByEmail(String email);
}

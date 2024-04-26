package study.games.flashcard.wars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.games.flashcard.wars.models.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);

    AppUser findAppUserByEmail(String email);
}

package game_store.persistance.repositories;

import game_store.model.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    boolean existsByTitleEquals(final String title);

    Game getGameByTitleEquals(final String title);
}

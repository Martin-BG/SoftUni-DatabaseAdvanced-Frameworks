package game_store.persistance.services.api;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.entities.Game;

public interface GameService {

    Game persist(AddGameDto addGameDto);

    boolean gameExists(String title);
}

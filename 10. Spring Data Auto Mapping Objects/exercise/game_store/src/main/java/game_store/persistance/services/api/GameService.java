package game_store.persistance.services.api;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.entities.Game;

public interface GameService {

    Game persist(AddGameDto addGameDto);

    EditGameDto getEditGameDtoById(Long id);

    boolean gameExists(String title);

    void update(long id, EditGameDto editGameDto);
}

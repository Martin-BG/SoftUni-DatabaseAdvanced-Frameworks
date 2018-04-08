package game_store.persistance.services.api;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.model.entities.Game;

public interface GameService {

    Game persist(AddGameDto addGameDto);

    EditGameDto getEditGameDtoById(Long id);

    GameTitleViewDto getGameTitleViewDtoById(Long id);

    boolean gameExists(String title);

    boolean gameExists(Long id);

    void update(long id, EditGameDto editGameDto);

    void delete(Long id);
}

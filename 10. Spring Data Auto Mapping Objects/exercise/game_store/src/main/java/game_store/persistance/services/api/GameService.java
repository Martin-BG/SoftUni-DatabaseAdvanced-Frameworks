package game_store.persistance.services.api;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.dto.view.GameFullViewDto;
import game_store.model.dto.view.GameTitleAndPriceViewDto;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.model.entities.Game;

import java.util.List;

public interface GameService {

    Game persist(AddGameDto addGameDto);

    EditGameDto getEditGameDtoById(Long id);

    GameTitleViewDto getGameTitleViewDtoById(Long id);

    GameFullViewDto getFullGameViewDtoByTitle(String title);

    List<GameTitleAndPriceViewDto> getAllGamesTitleAndPrice();

    boolean gameExists(String title);

    boolean gameExists(Long id);

    void update(long id, EditGameDto editGameDto);

    void delete(Long id);
}

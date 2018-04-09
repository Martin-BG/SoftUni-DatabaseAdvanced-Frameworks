package game_store.persistance.services.api;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.dto.view.GameFullViewDto;
import game_store.model.dto.view.GameTitleAndPriceViewDto;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.model.entities.Game;

import java.util.Set;

public interface GameService {

    Game persist(AddGameDto addGameDto);

    EditGameDto getEditGameDtoById(Long id);

    GameTitleViewDto getGameTitleViewDtoById(Long id);

    GameFullViewDto getFullGameViewDtoByTitle(String title);

    Set<GameTitleAndPriceViewDto> getAllGamesTitleAndPrice();

    GameTitleViewDto getGameTitleViewDtoByTitle(String title);

    boolean gameExists(String title);

    void update(long id, EditGameDto editGameDto);

    void delete(Long id);

    Game getGameFromTitle(String title);
}

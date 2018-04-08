package game_store.persistance.services.impl;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.entities.Game;
import game_store.model.utils.ObjectMapper;
import game_store.persistance.repositories.GameRepository;
import game_store.persistance.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public boolean gameExists(final String title) {
        return this.gameRepository.existsByTitleEquals(title);
    }

    @Override
    public void update(final long id, final EditGameDto editGameDto) {
        // TODO - Investigate best practices for merging DTOs back into their entities
        Game game = ObjectMapper.getInstance().map(editGameDto, Game.class);
        game.setId(id);
        this.gameRepository.save(game);
    }

    @Override
    public Game persist(final AddGameDto addGameDto) {
        Game game = ObjectMapper.getInstance().map(addGameDto, Game.class);
        return this.gameRepository.save(game);
    }

    @Override
    public EditGameDto getEditGameDtoById(final Long id) {
        Optional<Game> game = this.gameRepository.findById(id);
        return game.map(game1 -> ObjectMapper.getInstance().map(game1, EditGameDto.class)).orElse(null);
    }
}

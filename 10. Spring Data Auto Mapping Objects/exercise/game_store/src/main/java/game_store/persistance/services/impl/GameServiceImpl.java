package game_store.persistance.services.impl;

import game_store.model.dto.binding.AddGameDto;
import game_store.model.entities.Game;
import game_store.model.utils.ObjectMapper;
import game_store.persistance.repositories.GameRepository;
import game_store.persistance.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Game persist(final AddGameDto addGameDto) {
        Game game = ObjectMapper.getInstance().map(addGameDto, Game.class);

/*        TypeMap<AddGameDto, Game> typeMap = ObjectMapper.getInstance().createTypeMap(AddGameDto.class, Game.class);
        typeMap.addMappings(m -> {
            m.map(AddGameDto::getTitle, Game::setTitle);
            m.map(AddGameDto::getPrice, Game::setPrice);
            m.map(AddGameDto::getSize, Game::setSize);
            m.map(AddGameDto::getDescription, Game::setDescription);
            m.map(AddGameDto::getReleaseDate, Game::setReleaseDate);
            m.map(AddGameDto::getThumbnailUrl, Game::setThumbnailUrl);
            m.map(AddGameDto::getTrailer, Game::setTrailer);
        });
        Game game = typeMap.map(addGameDto);*/
        return this.gameRepository.save(game);
    }
}

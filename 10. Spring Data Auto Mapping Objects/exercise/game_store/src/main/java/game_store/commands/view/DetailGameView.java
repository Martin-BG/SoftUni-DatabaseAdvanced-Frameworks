package game_store.commands.view;

import game_store.commands.Command;
import game_store.constants.CommandConstants;
import game_store.constants.CommandMessages;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.view.GameFullViewDto;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import org.springframework.dao.DataIntegrityViolationException;

public class DetailGameView extends Command {

    public DetailGameView(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        try {
            GameFullViewDto gameFullViewDto = super.getGameService().getFullGameViewDtoByTitle(args[0]);

            if (gameFullViewDto == null) {
                return CommandMessages.GAME_NOT_FOUND;
            }

            final StringBuilder sb = new StringBuilder()
                    .append(String.format("Title: %s%n", gameFullViewDto.getTitle()))
                    .append(String.format("Price: %.2f%n", gameFullViewDto.getPrice()))
                    .append(String.format("Description: %s%n", gameFullViewDto.getDescription()))
                    .append(String.format("Release date: %s%n", gameFullViewDto.getReleaseDate().format(CommandConstants.DATE_FORMAT)))
                    .append(String.format("Size: %.2f%n", gameFullViewDto.getSize()))
                    .append(String.format("Thumbnail URL: %s%n", gameFullViewDto.getThumbnailUrl()))
                    .append(String.format("Youtube trailer ID: %s", gameFullViewDto.getTrailer()));
            return sb.toString().trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

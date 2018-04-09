package game_store.commands.game;

import game_store.commands.Command;
import game_store.constants.CommandMessages;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import game_store.store.LoggedUserRegister;
import org.springframework.dao.DataIntegrityViolationException;

public class DeleteGame extends Command {

    public DeleteGame(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        if (!LoggedUserRegister.isAdmin()) {
            return CommandMessages.ADMIN_REQUIRED;
        }

        try {
            GameTitleViewDto gameTitleViewDto = super.getGameService().getGameTitleViewDtoById(Long.parseLong(args[0]));
            if (gameTitleViewDto == null) {
                return CommandMessages.INVALID_GAME_ID;
            }

            super.getGameService().delete(Long.parseLong(args[0]));

            return String.format(CommandMessages.GAME_DELETED, gameTitleViewDto.getTitle());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

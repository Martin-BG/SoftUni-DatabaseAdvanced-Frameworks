package game_store.commands.shop;

import game_store.commands.Command;
import game_store.constants.CommandMessages;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import game_store.store.LoggedUserRegister;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Set;

public class AddItem extends Command {

    public AddItem(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        Long loggedUserId = LoggedUserRegister.getLoggedUserId();

        if (loggedUserId == null) {
            return CommandMessages.NO_CURRENT_USER;
        }

        try {
            GameTitleViewDto game = super.getGameService().getGameTitleViewDtoByTitle(args[0]);

            if (game == null) {
                return CommandMessages.GAME_NOT_FOUND;
            }

            Set<GameTitleViewDto> ownedGamesTitle = super.getUserService().getOwnedGamesTitle(loggedUserId);
            if (ownedGamesTitle.contains(game)) {
                return String.format(CommandMessages.GAME_ALREADY_OWNED, game.getTitle());
            }

            Set<GameTitleViewDto> cartGamesTitle = super.getUserService().getCartGamesTitle(loggedUserId);
            if (cartGamesTitle.contains(game)) {
                return String.format(CommandMessages.GAME_ALREADY_IN_CART, game.getTitle());
            }

            super.getUserService().addGameToUserCart(loggedUserId, game);

            cartGamesTitle = super.getUserService().getCartGamesTitle(loggedUserId);
            if (cartGamesTitle.contains(game)) {
                return String.format(CommandMessages.GAME_ADDED_TO_CART, game.getTitle());
            } else {
                return String.format(CommandMessages.FAILED_TO_ADD_TO_CART, game.getTitle());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

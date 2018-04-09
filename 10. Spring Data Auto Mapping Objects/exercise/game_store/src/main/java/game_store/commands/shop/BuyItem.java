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

public class BuyItem extends Command {

    public BuyItem(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        Long loggedUserId = LoggedUserRegister.getLoggedUserId();

        if (loggedUserId == null) {
            return CommandMessages.NO_CURRENT_USER;
        }

        try {
            Set<GameTitleViewDto> cart = super.getUserService().getCartGamesTitle(loggedUserId);
            if (cart.isEmpty()) {
                return CommandMessages.SHOPPING_CART_IS_EMPTY;
            }

            Set<GameTitleViewDto> previouslyOwnedGamesTitle = super.getUserService().getOwnedGamesTitle(loggedUserId);

            super.getUserService().buyAllGamesFromUserCart(loggedUserId);

            Set<GameTitleViewDto> boughGames = super.getUserService().getOwnedGamesTitle(loggedUserId);
            boughGames.removeAll(previouslyOwnedGamesTitle);
            final StringBuilder sb = new StringBuilder(CommandMessages.SUCCESSFULLY_BOUGHT_GAMES);
            boughGames.forEach(game -> sb.append(String.format(CommandMessages.BOUGHT_GAMES_FORMAT, game.getTitle())));
            return sb.toString().trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

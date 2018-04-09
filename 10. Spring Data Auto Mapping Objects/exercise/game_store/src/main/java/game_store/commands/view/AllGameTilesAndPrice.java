package game_store.commands.view;

import game_store.commands.Command;
import game_store.constants.CommandMessages;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;

public class AllGameTilesAndPrice extends Command {

    public AllGameTilesAndPrice(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        final StringBuilder sb = new StringBuilder();

        super.getGameService().getAllGamesTitleAndPrice()
                .forEach(game -> sb.append(String.format(
                        CommandMessages.VIEW_TITLE_PRICE, game.getTitle(), game.getPrice())));

        return sb.toString().trim();
    }
}

package game_store.commands.view;

import game_store.commands.Command;
import game_store.constants.CommandMessages;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import game_store.store.LoggedUserRegister;

public class OwnedGames extends Command {

    public OwnedGames(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        if (!LoggedUserRegister.hasLoggedUser()) {
            return CommandMessages.NO_CURRENT_USER;
        }

        final StringBuilder sb = new StringBuilder(CommandMessages.OWNED_GAMES);

        super.getUserService().getOwnedGamesTitle(LoggedUserRegister.getLoggedUserId())
                .forEach(game -> sb.append(game.getTitle()).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}

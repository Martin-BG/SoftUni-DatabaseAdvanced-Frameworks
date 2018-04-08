package game_store.commands;

import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;

public abstract class Command implements Executable {

    private final UserService userService;
    private final GameService gameService;

    protected Command(final UserService userService, final GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    protected UserService getUserService() {
        return this.userService;
    }

    protected GameService getGameService() {
        return this.gameService;
    }
}

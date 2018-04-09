package game_store.commands.game;

import game_store.commands.Command;
import game_store.constants.CommandConstants;
import game_store.constants.CommandMessages;
import game_store.constants.ValidationConstrains;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.binding.AddGameDto;
import game_store.model.utils.ObjectValidator;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import game_store.store.LoggedUserRegister;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGame extends Command {

    private static final int TITLE_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int SIZE_INDEX = 2;
    private static final int TRAILER_INDEX = 3;
    private static final int THUMBNAIL_INDEX = 4;
    private static final int DESCRIPTION_INDEX = 5;
    private static final int RELEASE_DATE_INDEX = 6;

    public AddGame(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        if (!LoggedUserRegister.isAdmin()) {
            return CommandMessages.ADMIN_REQUIRED;
        }

        try {
            if (super.getGameService().gameExists(args[TITLE_INDEX])) {
                return CommandMessages.GAME_BY_THAT_NAME_ALREADY_EXIST;
            }

            AddGameDto addGameDto = new AddGameDto(
                    args[TITLE_INDEX],
                    new BigDecimal(args[PRICE_INDEX]),
                    new BigDecimal(args[SIZE_INDEX]),
                    args[TRAILER_INDEX].substring(args[TRAILER_INDEX].length() - ValidationConstrains.GAME_TRAILER_MAX_LENGTH),
                    args[THUMBNAIL_INDEX],
                    args[DESCRIPTION_INDEX],
                    LocalDate.parse(args[RELEASE_DATE_INDEX], CommandConstants.DATE_FORMAT));

            ObjectValidator.validate(addGameDto);

            super.getGameService().persist(addGameDto);

            return String.format(CommandMessages.GAME_ADDED, args[TITLE_INDEX]);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

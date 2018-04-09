package game_store.commands.user;

import game_store.commands.Command;
import game_store.constants.CommandMessages;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.binding.RegisterUserDto;
import game_store.model.utils.ObjectValidator;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import org.springframework.dao.DataIntegrityViolationException;

public class Register extends Command {

    private static final int EMAIL_INDEX = 0;
    private static final int PASSWORD_INDEX = 1;
    private static final int PASSWORD_CONFIRM_INDEX = 2;
    private static final int USER_NAME_INDEX = 3;

    public Register(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        try {
            if (super.getUserService().userExists(args[EMAIL_INDEX])) {
                return CommandMessages.CANNOT_REGISTER_USER_EMAIL_ALREADY_USED;
            }

            RegisterUserDto registerUserDto = new RegisterUserDto(
                    args[EMAIL_INDEX],
                    args[PASSWORD_INDEX],
                    args[PASSWORD_CONFIRM_INDEX],
                    args[USER_NAME_INDEX]);

            ObjectValidator.validate(registerUserDto);

            super.getUserService().persist(registerUserDto);

            return String.format(CommandMessages.USER_WAS_REGISTERED, args[USER_NAME_INDEX]);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

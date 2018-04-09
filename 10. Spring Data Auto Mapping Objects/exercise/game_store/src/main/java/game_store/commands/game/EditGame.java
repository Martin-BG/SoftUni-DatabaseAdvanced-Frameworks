package game_store.commands.game;

import game_store.commands.Command;
import game_store.constants.CommandConstants;
import game_store.constants.CommandMessages;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.utils.ObjectValidator;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import game_store.store.LoggedUserRegister;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EditGame extends Command {

    private static final int COMMAND_INDEX = 0;
    private static final int ID_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public EditGame(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        if (!LoggedUserRegister.isAdmin()) {
            return CommandMessages.ADMIN_REQUIRED;
        }

        try {
            EditGameDto editGameDto = super.getGameService().getEditGameDtoById(Long.parseLong(args[ID_INDEX]));

            if (editGameDto == null) {
                return CommandMessages.INVALID_GAME_ID;
            }

            for (int i = 1; i < args.length; i++) {
                String[] tokens = args[i].split(CommandConstants.INPUT_PARAMS_SEPARATOR);

                switch (tokens[COMMAND_INDEX]) {
                case "title":
                    editGameDto.setTitle(tokens[VALUE_INDEX]);
                    break;
                case "price":
                    editGameDto.setPrice(new BigDecimal(tokens[VALUE_INDEX]));
                    break;
                case "size":
                    editGameDto.setSize(new BigDecimal(tokens[VALUE_INDEX]));
                    break;
                case "thumbnail":
                    editGameDto.setThumbnailUrl(tokens[VALUE_INDEX]);
                    break;
                case "trailer":
                    editGameDto.setTrailer(tokens[VALUE_INDEX]);
                    break;
                case "description":
                    editGameDto.setDescription(tokens[VALUE_INDEX]);
                    break;
                case "date":
                    editGameDto.setReleaseDate(LocalDate.parse(tokens[VALUE_INDEX], CommandConstants.DATE_FORMAT));
                    break;
                default:
                    throw new InvalidCommandException(String.format(CommandMessages.UNKNOWN_PARAMETER_TYPE, tokens[COMMAND_INDEX]));
                }
            }

            ObjectValidator.validate(editGameDto);

            super.getGameService().update(Long.parseLong(args[ID_INDEX]), editGameDto);

            return String.format(CommandMessages.GAME_EDITED, editGameDto.getTitle());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

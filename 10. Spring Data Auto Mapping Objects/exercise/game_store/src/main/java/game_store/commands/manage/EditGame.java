package game_store.commands.manage;

import game_store.commands.Command;
import game_store.constants.CommandConstants;
import game_store.constants.CommandMessages;
import game_store.exceptions.InvalidCommandException;
import game_store.model.dto.binding.EditGameDto;
import game_store.model.utils.ObjectValidator;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EditGame extends Command {

    public EditGame(final UserService userService, final GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(final String... args) {
        try {
            EditGameDto editGameDto = super.getGameService().getEditGameDtoById(Long.parseLong(args[0]));

            if (editGameDto == null) {
                throw new InvalidCommandException(CommandMessages.INVALID_GAME_ID);
            }

            for (int i = 1; i < args.length; i++) {
                String[] tokens = args[i].split(CommandConstants.INPUT_PARAMS_SEPARATOR);

                switch (tokens[0]) {
                case "title":
                    editGameDto.setTitle(tokens[1]);
                    break;
                case "price":
                    editGameDto.setPrice(new BigDecimal(tokens[1]));
                    break;
                case "size":
                    editGameDto.setSize(new BigDecimal(tokens[1]));
                    break;
                case "thumbnail":
                    editGameDto.setThumbnailUrl(tokens[1]);
                    break;
                case "trailer":
                    editGameDto.setTrailer(tokens[1]);
                    break;
                case "description":
                    editGameDto.setDescription(tokens[1]);
                    break;
                case "date":
                    editGameDto.setReleaseDate(LocalDate.parse(tokens[1], CommandConstants.DATE_FORMAT));
                    break;
                default:
                    throw new InvalidCommandException(String.format(CommandMessages.UNKNOWN_PARAMETER_TYPE, tokens[0]));
                }
            }

            ObjectValidator.validate(editGameDto);

            super.getGameService().update(Long.parseLong(args[0]), editGameDto);

            return String.format(CommandMessages.GAME_EDITED, editGameDto.getTitle());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(CommandMessages.INVALID_COMMAND_ARGUMENTS);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}

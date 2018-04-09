package game_store.store;

import game_store.constants.CommandMessages;
import game_store.model.dto.view.LoggedInUserDto;
import game_store.model.enums.Role;

public class LoggedUserRegister {

    private static LoggedInUserDto currentUser;

    public static String logInUser(LoggedInUserDto user) {
        final StringBuilder sb = new StringBuilder();

        if (hasLoggedUser()) {
            sb.append(logOutUser()).append(System.lineSeparator());
        }

        currentUser = user;

        sb.append(String.format(CommandMessages.USER_LOGGED_IN, currentUser.getFullName()));

        return sb.toString();
    }

    public static String logOutUser() {
        if (currentUser == null) {
            return CommandMessages.NO_USER_LOGGED_IN;
        }

        LoggedInUserDto loggedInUserDto = currentUser;
        currentUser = null;
        return String.format(CommandMessages.USER_LOGGED_OUT, loggedInUserDto.getFullName());
    }

    public static boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == Role.ADMIN;
    }

    public static boolean hasLoggedUser() {
        return currentUser != null;
    }

    public static Long getLoggedUserId() {
        if (currentUser != null) {
            return currentUser.getId();
        }

        return null;
    }
}

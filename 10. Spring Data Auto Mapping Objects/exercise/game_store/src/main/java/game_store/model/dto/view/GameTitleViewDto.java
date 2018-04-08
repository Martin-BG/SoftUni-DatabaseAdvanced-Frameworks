package game_store.model.dto.view;

import game_store.model.validators.game_title.GameTitle;

public class GameTitleViewDto {

    @GameTitle
    private String title;

    public GameTitleViewDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final GameTitleViewDto that = (GameTitleViewDto) o;

        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}

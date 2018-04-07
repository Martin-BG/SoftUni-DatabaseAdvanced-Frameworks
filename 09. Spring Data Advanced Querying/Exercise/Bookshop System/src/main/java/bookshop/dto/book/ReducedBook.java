package bookshop.dto.book;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;

import java.math.BigDecimal;

public class ReducedBook {

    private final String title;
    private final EditionType editionType;
    private final AgeRestriction ageRestriction;
    private final BigDecimal price;

    public ReducedBook(final String title, final EditionType editionType,
                       final AgeRestriction ageRestriction, final BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %.2f",
                this.title, this.editionType, this.ageRestriction, this.price);
    }
}

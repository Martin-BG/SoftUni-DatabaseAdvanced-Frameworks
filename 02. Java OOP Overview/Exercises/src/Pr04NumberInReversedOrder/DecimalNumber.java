package Pr04NumberInReversedOrder;

import java.text.DecimalFormat;

public class DecimalNumber {

    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("0.###############");

    private final double number;

    public DecimalNumber(double number) {
        this.number = number;
    }

    public String getReversed() {
        return new StringBuilder(DECIMAL_FORMATTER.format(this.number))
                .reverse().toString();
    }
}

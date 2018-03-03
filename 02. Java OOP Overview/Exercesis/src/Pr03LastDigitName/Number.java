package Pr03LastDigitName;

public class Number {

    private final int number;

    public Number(int number) {
        this.number = Math.abs(number);
    }

    public String getNameOfLastDigit() {
        return getNameOfDigit(this.number % 10);
    }

    private String getNameOfDigit(int digit) {

        switch (digit) {
        case 0:
            return "zero";
        case 1:
            return "one";
        case 2:
            return "two";
        case 3:
            return "three";
        case 4:
            return "four";
        case 5:
            return "five";
        case 6:
            return "six";
        case 7:
            return "seven";
        case 8:
            return "eight";
        case 9:
            return "nine";
        default:
            return "";
        }
    }
}

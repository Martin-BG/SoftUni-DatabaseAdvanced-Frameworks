package Pr03LastDigitName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(
                new Pr03LastDigitName.Number(Integer.parseInt(reader.readLine()))
                        .getNameOfLastDigit());
    }
}

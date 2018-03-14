package pr02_multiple_implementation;


import pr02_multiple_implementation.contracts.Birthable;
import pr02_multiple_implementation.contracts.Identifiable;
import pr02_multiple_implementation.model.Citizen;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Class[] citizenInterfaces = Citizen.class.getInterfaces();

        if (Arrays.asList(citizenInterfaces).contains(Birthable.class)
                && Arrays.asList(citizenInterfaces).contains(Identifiable.class)) {
            Method[] methods = Birthable.class.getDeclaredMethods();
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            methods = Identifiable.class.getDeclaredMethods();
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());

            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = Integer.parseInt(scanner.nextLine());
            String id = scanner.nextLine();
            String birthdate = scanner.nextLine();
            Identifiable identifiable = new Citizen(name, age, id, birthdate);
            Birthable birthable = new Citizen(name, age, id, birthdate);
        }
    }
}

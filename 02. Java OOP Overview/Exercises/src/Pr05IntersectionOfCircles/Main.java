package Pr05IntersectionOfCircles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double[] params = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToDouble(Double::parseDouble).toArray();
        Circle c1 = new Circle(new Point(params[0], params[1]), params[2]);

        params = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToDouble(Double::parseDouble).toArray();
        Circle c2 = new Circle(new Point(params[0], params[1]), params[2]);

        System.out.println(Utility.circlesIntersect(c1, c2) ? "Yes" : "No");
    }
}

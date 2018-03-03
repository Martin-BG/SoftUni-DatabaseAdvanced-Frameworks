package Pr05IntersectionOfCircles;

public class Utility {

    public static boolean circlesIntersect(Circle c1, Circle c2) {
        return getDistanceBetweenPoints(c1.getCenter(), c2.getCenter()) <=
                c1.getRadius() + c2.getRadius();
    }

    public static double getDistanceBetweenPoints(Point p1, Point p2) {
        return Math.sqrt(
                Math.pow((p2.getX() - p1.getX()), 2) +
                        Math.pow((p2.getY() - p1.getY()), 2));
    }
}

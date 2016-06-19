package geometry;

/**
 * The Point class has two coordinates, x and and y and has methods to calculate
 * the distance between to points and check whether two points are equal.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Point {
    private double x; // The x value of the point.
    private double y; // The y value of the point.

    /**
     * Point creates a point with given x and y values.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance method returns the distance of this point to the other point.
     *
     * @param other the second point to check it's distance from the first one.
     * @return double with the value of the distance between the points.
     */
    public double distance(Point other) {
        return (Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y)));
    }

    /**
     * equals method checks whether 2 points are the same or not.
     *
     * @param other the second point to check if it's the same as the first one.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * getX method returns the x value of the point.
     *
     * @return x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * setX method sets a new value for the point's x coordinate.
     *
     * @param newX is the new x value for the point.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * getY method returns the y value of the point.
     *
     * @return y value of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * setY method sets a new value for the point's y coordinate.
     *
     * @param newY is the new y value for the point.
     */
    public void setY(double newY) {
        this.y = newY;
    }
}

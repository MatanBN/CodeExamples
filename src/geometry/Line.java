package geometry;

import java.util.List;

/**
 * The Line class has starting a point and and end point and it connects the two
 * points.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Line {
    private Point start; // The first point of the line
    private Point end; // The last point of the line

    /**
     * Line is the constructor and creates the line with 2 given points.
     *
     * @param start is the first point of the line.
     * @param end   is the last point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Line is the constructor and creates the line with 2 x and values.
     *
     * @param x1 is the first point's x value in the line.
     * @param y1 is the first point's y value in the line.
     * @param x2 is the last point's x value in the line.
     * @param y2 is the last point's x value in the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length method calculates the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        double dx = this.start.getX() - this.end.getX();
        double dy = this.start.getY() - this.end.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * middle method calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * start method returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end method returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting method checks whether 2 lines are intersecting.
     *
     * @param other is the second line that is checked whether it intersect with the first line.
     * @return true if the lines are intersecting and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * intersectionWith method checks whether 2 lines are intersecting and
     * returns the intersection point.
     *
     * @param other is the second line that is checked whether it intersect with the first line.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double intersectionX;
        double intersectionY;
        // Checking whether our line is vertical.
        if (this.isVertical()) {
            if (other.isVertical()) {
                return null;
            }
            // Calculating the shared point's x and y values.
            intersectionX = this.start.getX();
            intersectionY = other.yCoordinate(intersectionX);
            // Checking whether the other line is vertical.
        } else if (other.isVertical()) {
            // Calculating the shared point's x and y values.
            intersectionX = other.start.getX();
            intersectionY = this.yCoordinate(intersectionX);
        } else {
            if (this.parallelTo(other)) {
                return null;
            }
            double myConstant = this.constant();
            intersectionX = xCoordinate(myConstant, this.slope(), other.constant(), other.slope());
            intersectionY = yCoordinate(intersectionX);
        }
        // Checking whether the shared x and y are inside the line segments
        if (this.inSegment(intersectionX, intersectionY) && other.inSegment(intersectionX, intersectionY)) {
            return new Point(intersectionX, intersectionY);
        }
        return null;
    }

    /**
     * parallelTo method checks if this line and another line are parallel.
     *
     * @param other line to check if it is parallel to mine.
     * @return true if the line are parallel to each other, false otherwise.
     */
    public boolean parallelTo(Line other) {
        return this.slope() == other.slope();
    }

    /**
     * inXSegment method checks if the x and y coordinates are in the line
     * segment.
     *
     * @param x the x coordinate to check.
     * @param y the y coordinate to check.
     * @return true if the x and y parameter is in the line segment, false otherwise.
     */
    public boolean inSegment(double x, double y) {
        return inXSegment(x) && inYSegment(y);
    }

    /**
     * inXSegment method checks if the x coordinates are in the x segment.
     *
     * @param x the x coordinate to check.
     * @return true if the x parameter is in the x segment, false otherwise.
     */
    public boolean inXSegment(double x) {
        return (x >= Math.min(this.start.getX(), this.end.getX()) && x <= Math.max(this.start.getX(), this.end.getX()));
    }

    /**
     * inXSegment method checks if the x coordinates are in the x segment.
     *
     * @param y the y coordinate to check.
     * @return true if the y parameter is in the y segment, false otherwise.
     */
    public boolean inYSegment(double y) {
        return (y >= Math.min(this.start.getY(), this.end.getY()) && y <= Math.max(this.start.getY(), this.end.getY()));
    }

    /**
     * isVertical method checks if line is vertical.
     *
     * @return true if line is vertical, false otherwise.
     */
    public boolean isVertical() {
        return (this.start.getX() == this.end.getX());
    }

    /**
     * equals method checks whether 2 lines are the same or not.
     *
     * @param other is the second line that is checked whether its the same line as the first one.
     * @return true if the lines are equal and false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }

    /**
     * slope method calculates the slope of the line.
     *
     * @return the slope of the line.
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * constant method calculates the constant of the line.
     *
     * @return the constant of the line.
     */
    public double constant() {
        return (this.start.getY() - (this.slope() * this.start.getX()));
    }

    /**
     * xCoordinate method calculates the x value in the intersection point of
     * two lines.
     *
     * @param const1 is the constant of the first line.
     * @param slope1 is the slope of the first line.
     * @param const2 is the constant of the second line.
     * @param slope2 is the slope of the second line.
     * @return the x value in the intersection point of the lines.
     */
    public double xCoordinate(double const1, double slope1, double const2, double slope2) {
        return ((const2 - const1) / (slope1 - slope2));
    }

    /**
     * yCoordinate method calculates the x value in the intersection point of
     * two lines.
     *
     * @param xCoordinate is the x value in the intersection point of the lines.
     * @return the y value in the intersection point of the lines.
     */
    public double yCoordinate(double xCoordinate) {
        return (this.slope() * xCoordinate) + this.constant();
    }

    /**
     * closestIntersectionToStartOfLine method finds the closest intersection
     * point to the start of the line.
     *
     * @param rect the rectangle to check the intersection point with.
     * @return the closest intersection to the start point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Object> intersectList = rect.intersectionPoints(this);
        // Check if there aren't any intersection points.
        if (intersectList.isEmpty()) {
            return null;
        }
        // Find the closest intersection point to the start point.
        Point minPoint = (Point) intersectList.get(0);
        for (Object o : intersectList) {
            if ((minPoint.distance(start) > ((Point) o).distance(start))) {
                minPoint = (Point) o;
            }
        }
        return minPoint;
    }

}

package game;

import geometry.Point;

/**
 * The Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor to create the ball's velocity.
     *
     * @param dx the speed in the x plane.
     * @param dy the speed in the y plane.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed calculates the dx and dy parameters by angle and speed
     * that are given.
     *
     * @param angle the angle of the ball's movement.
     * @param speed the speed of the ball.
     * @return the game.Velocity created.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radainAngle = Math.toRadians(angle);
        double dx = speed * Math.sin(radainAngle);
        double dy = -speed * Math.cos(radainAngle);
        return new Velocity(dx, dy);
    }

    /**
     * applyToPoint methods get a point and returns an updated point according to the velocity.
     *
     * @param p The current coordinates.
     * @return the updated coordinates.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * getDx returns the ball's dx attribute.
     *
     * @return the x speed.
     */
    public double getDx() {
        return dx;
    }

    /**
     * setDx sets the ball's dx attribute.
     *
     * @param newDx the new x speed.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * getDy returns the ball's dy attribute.
     *
     * @return the y speed.
     */
    public double getDy() {
        return dy;
    }

    /**
     * setDy sets the ball's dy attribute.
     *
     * @param newDy is the y speed.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
}

package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Rectangle;
import geometry.Point;

import java.util.ArrayList;

/**
 * The Paddle is a moving block in the lowest row on the surface. It's members
 * are rectangle, the surface borders and a keyboard sensor.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Paddle extends BaseBlock {
    private biuoop.KeyboardSensor keyboard; // The keyboard sensor
    private Rectangle borders; // A rectangle with the borders of the surface
    private ArrayList regions; // 5 different regions of the paddle.
    private int speed;

    /**
     * The constructor creates the paddle.
     *
     * @param key     is the keyboard sensor in order to move it with the keyboard.
     * @param rec     is the rectangle with the parameter for the paddle.
     * @param border  is surface border.
     * @param speed   the speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor key, geometry.Rectangle rec, Rectangle border, int speed) {
        super(rec);
        this.keyboard = key;
        this.speed = speed;
        this.borders = border;
        this.regions = new ArrayList();
        int fifthRec = rec.getWidth() / 5;
        int startFrom = rec.getX();
        int end = rec.getX() + fifthRec;
        for (int i = 0; i < 5; ++i) {
            Point startPoint = new Point(startFrom, rec.getY());
            Point endPoint = new Point(end, rec.getY());
            regions.add(new Line(startPoint, endPoint));
            startFrom += fifthRec;
            end += fifthRec;
        }


    }

    /**
     * moveLeft is in charge of moving the paddle left. The method checks
     * whether the next x coordinate is out left edge or not and moves it as it
     * should.
     * @param newSpeed the speed to move the paddle.
     */
    public void moveLeft(double newSpeed) {
        Rectangle rectangle = super.getRectangle();
        if (rectangle.getX() - newSpeed >= borders.getX() + 10) {
            rectangle.getUpperLeft().setX(rectangle.getX() - newSpeed);
            alignRegions();
        } else {
            rectangle.getUpperLeft().setX(borders.getX() + 10);
        }
    }

    /**
     * moveRight is in charge of moving the paddle right. The method checks
     * whether the next x coordinate is out right edge or not and moves it as it
     * should.
     * @param newSpeed the speed to move the paddle.

     */
    public void moveRight(double newSpeed) {
        Rectangle rectangle = super.getRectangle();
        if (rectangle.getX() + rectangle.getWidth() + newSpeed <= borders.getWidth() - 10) {
            rectangle.getUpperLeft().setX(rectangle.getX() + newSpeed);
            alignRegions();
        } else {
            rectangle.getUpperLeft().setX(borders.getWidth() - rectangle.getWidth() - 10);
        }
    }

    /**
     * relocatePaddle changes the paddle's location (only it's X value) on the screen.
     *
     * @param x is the new upperleft's x value.
     */
    public void relocatePaddle(int x) {
        Rectangle rectangle = super.getRectangle();

        rectangle.getUpperLeft().setX(x);
        alignRegions();

    }

    /**
     * alignRegions changes the location of the paddle to the center of the screen.
     */
    private void alignRegions() {
        Rectangle rectangle = super.getRectangle();

        int fifthRec = rectangle.getWidth() / 5;
        int startFrom = rectangle.getX();
        int end = rectangle.getX() + fifthRec;
        for (Object o : regions) {
            Line l = (Line) o;
            l.start().setX(startFrom);
            l.end().setX(end);
            startFrom += fifthRec;
            end += fifthRec;
        }
    }

    /**
     * timePassed checks whether the key that the user pressed is left key or
     * right key and calls the suitable method in order to move it to the right
     * side.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
        double newSpeed = this.speed * dt;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(newSpeed);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(newSpeed);
        }
    }

    /**
     * drawOn method draws the paddle on a given surface.
     *
     * @param d is the surface to draw the paddle on
     */
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = super.getRectangle();

        rectangle.drawOn(d);
    }
}

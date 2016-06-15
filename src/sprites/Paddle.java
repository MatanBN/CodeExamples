package sprites;

import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import environment.Collidable;
import game.Velocity;
import geometry.Line;
import geometry.Rectangle;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The Paddle is a moving block in the lowest row on the surface. It's members
 * are rectangle, the surface borders and a keyboard sensor.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard; // The keyboard sensor
    private Rectangle rectangle; // The shape of the paddle
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
     * @param color   is the frame color of the paddle.
     * @param filling is the filling of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor key, geometry.Rectangle rec, Rectangle border, int speed, Color color,
                  Sprite filling) {
        this.keyboard = key;
        this.speed = speed;
        this.rectangle = new Rectangle(rec.getUpperLeft(), rec.getWidth(), rec.getHeight(), color, filling);
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
        if (rectangle.getX() - newSpeed >= borders.getX() + 20) {
            rectangle.getUpperLeft().setX(rectangle.getX() - newSpeed);
            alignRegions();
        } else {
            rectangle.getUpperLeft().setX(borders.getX() + 20);
        }
    }

    /**
     * moveRight is in charge of moving the paddle right. The method checks
     * whether the next x coordinate is out right edge or not and moves it as it
     * should.
     * @param newSpeed the speed to move the paddle.

     */
    public void moveRight(double newSpeed) {
        if (rectangle.getX() + rectangle.getWidth() + newSpeed <= borders.getWidth() - 20) {
            rectangle.getUpperLeft().setX(rectangle.getX() + newSpeed);
            alignRegions();
        } else {
            rectangle.getUpperLeft().setX(borders.getWidth() - rectangle.getWidth() - 20);
        }
    }

    /**
     * relocatePaddle changes the paddle's location (only it's X value) on the screen.
     *
     * @param x is the new upperleft's x value.
     */
    public void relocatePaddle(int x) {
        rectangle.getUpperLeft().setX(x);
        alignRegions();

    }

    /**
     * alignRegions changes the location of the paddle to the center of the screen.
     */
    private void alignRegions() {
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
        rectangle.drawOn(d);
    }

    /**
     * getCollisionRectangle returns the paddle's shape.
     *
     * @return the given rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit return the new velocity after the hit based on force the object
     * inflicted on us.
     *
     * @param hitter          the ball that hit this paddle.
     * @param collisionPoint  is the collision point of an object with the paddle.
     * @param currentVelocity is the current velocity of the object that will collide with the paddle.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() > this.rectangle.getY()) {
            Block block = new Block(this.getCollisionRectangle());
            return block.hit(hitter, collisionPoint, currentVelocity);
        }
        double angle = determineHitPoint(collisionPoint);
        if (angle == 3) {
            Block block = new Block(this.getCollisionRectangle());
            return block.hit(hitter, collisionPoint, currentVelocity);
        } else {
            return Velocity.fromAngleAndSpeed(angle,
                    Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
        }
    }

    /**
     * determineHitPoint checks in what region was the collision and returns the
     * angle accordingly.
     *
     * @param collisionPoint is the collision point of an object with the paddle.
     * @return the angle to adjust the velocity.
     */
    private double determineHitPoint(Point collisionPoint) {
        int i;
        for (i = 0; i < 5; ++i) {
            if (((Line) (regions.get(i))).inXSegment(collisionPoint.getX())) {
                break;
            }
        }
        int angle;
        switch (i) {
            case 0:
                angle = 300;
                break;
            case 1:
                angle = 330;
                break;
            case 2:
                angle = 3;
                break;
            case 3:
                angle = 30;
                break;
            case 4:
                angle = 60;
                break;
            default:
                angle = 0;
                break;
        }
        return angle;
    }

    /**
     * addToGame is in charge of adding the paddle as a sprite and as a
     * environment.Collidable to the game's suitable lists.
     *
     * @param g is the game object we created.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}

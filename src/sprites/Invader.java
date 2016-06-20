package sprites;

import animations.GameLevel;
import biuoop.DrawSurface;
import game.GroupMovement;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Image;

/**
 * Invader is a BaseBlock that represents an alien in the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Invader extends BaseBlock {
    private Image invaderImage;
    private GroupMovement gm;
    private int firstY;

    /**
     * Constructor creates an invader block.
     *
     * @param r      the rectangle of the invader.
     * @param img    the image of the invader.
     * @param firstY the y coordinate of the upper-left corner.
     */
    public Invader(Rectangle r, Image img, int firstY) {
        super(r);
        this.invaderImage = img;
        this.firstY = firstY;
    }

    /**
     * Constructor creates an invader block.
     *
     * @param x      is the x coordinate of left corner.
     * @param y      is the y coordinate of left corner.
     * @param width  is the rectangle's width.
     * @param height is the rectangle's height.
     * @param img    the image of the invader.
     * @param firstY the y coordinate of the upper-left corner.
     */
    public Invader(int x, int y, int width, int height, Image img, int firstY) {
        super(new Rectangle(x, y, width, height));
        this.invaderImage = img;
        this.firstY = firstY;

    }

    /**
     * Currently doesn't do anything.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * goDown changes the y coordinate of the invader.
     */
    public void goDown() {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setY(p.getY() + 10);
    }

    /**
     * drawOn method draws the aliens and the on a given surface.
     *
     * @param d is the surface to draw the aliens on.
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(this.getRectangle().getX(), this.getRectangle().getY(), invaderImage);
    }

    /**
     * getX returns the aliens left-upper corner x coordinate.
     *
     * @return the aliens left-upper corner x coordinate.
     */
    public double getX() {
        return this.getRectangle().getX();
    }

    /**
     * getY returns the aliens left-upper corner y coordinate.
     *
     * @return the aliens left-upper corner y coordinate.
     */
    public double getY() {
        return this.getRectangle().getY();
    }

    /**
     * removeFromGame removes the invader from the game.
     *
     * @param gameLevel the GameLevel to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gm.remove(this);
    }

    /**
     * setGm sets the GroupMovement of the game.
     *
     * @param gameLevel is the GroupMovement of the game.
     */
    public void setGm(GroupMovement gameLevel) {
        this.gm = gameLevel;
    }

    /**
     * getFirstY returns the y coordinate of the upper-left corner
     * of the invader when it's created.
     *
     * @return the y coordinate of the upper-left corner of the invader when it's created.
     */
    public int getFirstY() {
        return firstY;
    }
}

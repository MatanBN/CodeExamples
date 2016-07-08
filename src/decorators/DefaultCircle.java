package decorators;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * Default implementation of the circle interface.
 */
public class DefaultCircle implements Circle {

    private int x;
    private int y;
    private int radius;

    /**
     * Constructor.
     *
     * @param x initial x value
     * @param y initial y value
     * @param radius radius
     */
    public DefaultCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setX(int value) {
        this.x = value;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setY(int value) {
        this.y = value;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadius() {
        return this.radius;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRadius(int value) {
        this.radius = value;
    }

    /**
     * Draws the circle as a black boundary.
     */
    @Override
    public void drawOn(DrawSurface ds) {
        ds.setColor(Color.BLACK);
        ds.drawCircle(this.x, this.y, this.radius);
    }
}

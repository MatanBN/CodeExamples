package decorators;

import biuoop.DrawSurface;

/**
 * A common interface for the entity circle.
 */
public interface Circle {

    /**
     * Getter for the x coordinate of circle.
     *
     * @return the x coordinate value
     */
    int getX();

    /**
     * Getter for the y coordinate of circle.
     *
     * @return the y coordinate value
     */
    int getY();

    /**
     * Setter for the x coordinate of circle.
     *
     * @param value the new value for the x coordinate
     */
    void setX(int value);

    /**
     * Setter for the y coordinate of circle.
     *
     * @param value the new value for the y coordinate
     */
    void setY(int value);

    /**
     * Getter for the circles radius
     *
     * @return the radius value
     */
    int getRadius();

    /**
     * Setter for the circles radius
     *
     * @param value the new radius value
     */
    void setRadius(int value);

    /**
     * Draw the circle on the surface
     *
     * @param ds the surface to draw the circle on
     */
    void drawOn(DrawSurface ds);

}

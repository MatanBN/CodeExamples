package sprites;

import biuoop.DrawSurface;

/**
 * The Sprite holds 2 statements of any Sprite object.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public interface Sprite {
    /**
     * drawOn method draws the sprites.Sprite to the screen.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void timePassed(double dt);
}

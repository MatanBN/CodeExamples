package sprites;

import biuoop.DrawSurface;

/**
 * LevelIndicator holds a string with the name of the current level and draws it on top
 * of the screen.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class LevelIndicator implements Sprite {
    private String name; //The name of the level

    /**
     * The constructor gets the name of the level.
     *
     * @param name of the current level.
     */
    public LevelIndicator(String name) {
        this.name = name;
    }

    /**
     * drawOn draws the current level's name on the screen.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(600, 10, "Level Name: " + name, 10);
    }

    /**
     * Currently doesn't do anything.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {

    }
}

package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The LiveIndicator class contains the current lives status and draws it on top of the screen.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class LiveIndicator implements Sprite {
    private int lives; // The lives status

    /**
     * The constructor gets the number of lives from the game.gameLevel.
     *
     * @param lives is the lives status variable.
     */
    public LiveIndicator(int lives) {
        this.lives = lives;
    }

    /**
     * decrease reduces the liveIndicator by one.
     */
    public void decrease() {
        --lives;
    }

    /**
     * getValue returns the number of lives that has left.
     *
     * @return number of lives that has left.
     */
    public int getValue() {
        return lives;
    }

    /**
     * drawOn draws the number of lives on top of the screen.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(100, 10, "Lives: " + lives, 10);
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

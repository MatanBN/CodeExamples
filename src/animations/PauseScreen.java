package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * PauseScreen is an Animation that displays a pause screen when it
 * is been called.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard; // The keyboard sensor of the game.
    private boolean stop; // A boolean member that tells if the game is on pause or not.

    /**
     * The constructor gets the P from the keyboard sensor and initializes the
     * stop member to false.
     *
     * @param k is keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * doOneFrame method draws the pause screen.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 4 - 150, d.getHeight() / 2 - 100, "Dear Player,", 32);
        d.drawText(d.getWidth() / 4 - 150, d.getHeight() / 2 - 50, "It seems that you have paused the game.", 32);
        d.drawText(d.getWidth() / 4 - 150, d.getHeight() / 2, "If you would like to continue pleas press space :)", 32);

    }

    /**
     * shouldStop method returns the value of stop.
     *
     * @return the value of the stop variable.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}

package sprites;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;


/**
 * The ScoreIndicator class contains the current score and has methods to draw the score.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class ScoreIndicator implements Sprite {
    private Counter score; // The current score.

    /**
     * ScoreIndicator constructor.
     */
    public ScoreIndicator() {
        score = new Counter();
    }

    /**
     * increase method increases the score according to the number given.
     *
     * @param num the amount to increase the score with.
     */
    public void increase(int num) {
        score.increase(num);
    }

    /**
     * getScore returns the score counter.
     *
     * @return the score counter.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * drawOn method draws the score to the surface.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 10, "Score: " + score.getValue(), 10);
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

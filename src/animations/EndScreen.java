package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.LiveIndicator;
import sprites.ScoreIndicator;

import java.awt.Color;


/**
 * EndScreen is an Animation that displays the end screen when the game is finished.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class EndScreen implements Animation {
    private KeyboardSensor ks; // The keyboard sensor of the game.
    private LiveIndicator liveIndicator; // The LiveIndicator of the player.
    private ScoreIndicator score; // The ScoreIndicator of the player.
    private boolean exitGame; // The member that says whether we should exit the game or not.

    /**
     * The constructor for the end screen animation.
     * stop member to false.
     *
     * @param ks            the keyboard sensor.
     * @param liveIndicator the LiveIndicator of the player
     * @param score         The ScoreIndicator of the player.
     */
    public EndScreen(KeyboardSensor ks, LiveIndicator liveIndicator, ScoreIndicator score) {
        this.ks = ks;
        this.liveIndicator = liveIndicator;
        this.score = score;
        this.exitGame = false;
    }

    /**
     * doOneFrame method draws the end screen.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
            d.setColor(Color.black);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.drawText(d.getWidth() / 4 + 110, d.getHeight() / 2 - 50, "game Over :(", 32);
            d.drawText(d.getWidth() / 4 + 100, d.getHeight() / 2 + 50, "Your score is "
                    + score.getScore().getValue(), 32);
    }

    /**
     * shouldStop method returns the value of stop.
     *
     * @return the value of the exitGame variable.
     */
    @Override
    public boolean shouldStop() {
        return this.exitGame;
    }
}

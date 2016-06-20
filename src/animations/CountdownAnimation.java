package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import sprites.SpriteCollection;

import java.awt.Color;


/**
 * CountDownAnimation will display the given gameScreen, for numOfSeconds seconds,
 * and on top of them it will show a countdown from countFrom back to 1.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds; // The number of seconds of the numbers to appear on screen
    private int counter; // The counter that counts backwards
    private int countFrom; // The number to from backwards
    private SpriteCollection screen; // The list of the sprites of the game
    private boolean running; // The member that says whether the game runs or not

    /**
     * The constructor initializes the members by the given parameters from the gameLevel.
     *
     * @param numOfSeconds is the number of seconds of the numbers to appear on screen.
     * @param countFrom    is the number to from backwards.
     * @param gameScreen   is the list of the sprites of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        counter = countFrom;
        this.countFrom = countFrom;
        screen = gameScreen;
        running = false;
    }

    /**
     * doOneFrame method draws the numbers from contFrom to 1 on the screen.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        String s;
        Sleeper sleeper = new Sleeper();
        screen.drawAllOn(d);
        if (counter == 0) {
            s = "GO";
        } else {
            s = Integer.toString(counter);
        }
        if (counter == -1) {
            running = true;
        } else {
            d.setColor(Color.white);
            d.drawText(d.getWidth() / 2, 450, s, 32);
            --counter;

        }
        sleeper.sleepFor((long) ((1000) * this.numOfSeconds) / (countFrom + 1));
    }

    /**
     * shouldStop method returns the value of running.
     *
     * @return the value of the running variable.
     */
    public boolean shouldStop() {
        return running;
    }
}
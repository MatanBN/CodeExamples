package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner takes an Animation object and runs it.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class AnimationRunner {
    private GUI gui; // The gui windows of the game.
    private int framesPerSecond; // The frames per second represented by integer

    /**
     * The constructor initializes the frame rate to 60 frames per second
     * and creates the gui window of the game.
     *
     * @param framesPerSecond the number of framers per second.
     * @param gui             the gui of the game.
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * run method runs the animation loop.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        double dt = 1 / (double) framesPerSecond;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d, dt);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * getGui returns the gui of the game.
     *
     * @return the gui of the game.
     */
    public GUI getGui() {
        return this.gui;
    }

}
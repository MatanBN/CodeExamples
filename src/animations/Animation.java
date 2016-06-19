package animations;

import biuoop.DrawSurface;

/**
 * Animation holds 2 statements of any Animation.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public interface Animation {

    /**
     * doOneFrame method draws the game to the screen.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop method returns the value of running.
     *
     * @return the value of the running variable.
     */
    boolean shouldStop();
}

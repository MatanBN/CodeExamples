package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation is a decorator-class that wraps an existing animation
 * and adds a "waiting-for-key" behavior to it.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public abstract class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor to create the KeyPressStoppableAnimation.
     *
     * @param sensor    is the keyboard sensor.
     * @param key       is the key to stop the animation.
     * @param animation is the animation that will be stopped.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;

    }

    /**
     * doOneFrame method draws the animation given to the constructor.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * shouldStop method returns the value of stop.
     *
     * @return the value of the stop variable.
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }
}

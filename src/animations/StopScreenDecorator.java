package animations;

import biuoop.KeyboardSensor;

/**
 * The StopScreenDecorator class is a decorator class for every pause/end animation.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class StopScreenDecorator extends KeyPressStoppableAnimation {
    /**
     * Constructor for the StopScreenDecorator.
     *
     * @param sensor    The keyboard sensor.
     * @param key       The key to return from this animation.
     * @param animation The animation to draw.
     */
    public StopScreenDecorator(KeyboardSensor sensor, String key, Animation animation) {
        super(sensor, key, animation);
    }

}

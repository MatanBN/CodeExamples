package animations;

/**
 * Task holds 1 statement of any Task Object.
 * @param <T> generic object that the task has.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public interface Task<T> {

    /**
     * run method runs the animation loop.
     *
     * @return the animation.
     */
    T run();
}

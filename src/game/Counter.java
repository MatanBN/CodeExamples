package game;

/**
 * The Counter is an object that has a counter of integers in order to be used in different classes
 * while keeping its value and changing it in different classes.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Counter {
    private int counter;

    /**
     * The constructor initialize the counter to 0.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * increase is in charge of adding a number to the counter.
     *
     * @param number is the number that is added to the counter.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * decrease is in charge of subtracting a number from the counter.
     *
     * @param number is the number that is subtracted from the counter.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * getValue returns the value of the current counter.
     *
     * @return the value of the current counter.
     */
    public int getValue() {
        return this.counter;
    }
}

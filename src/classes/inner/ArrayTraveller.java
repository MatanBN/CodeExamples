package classes.inner;

import java.util.LinkedList;
import java.util.List;

/**
 * Demonstration of how static inner classes can be
 * used. The Listener and Event are more natural here instead
 * of Having ArrayTravellerLister and ArrayTravelerEvent top level
 * classes.
 */
public class ArrayTraveller {

    private int[] array;
    private List<Listener> listeners;

    /**
     * Constructor
     *
     * @param array array to travel on
     */
    public ArrayTraveller(int[] array) {
        this.array = array;
        this.listeners = new LinkedList<Listener>();
    }

    /**
     * Add listener
     *
     * @param listener
     */
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove listener
     *
     * @param listener
     */
    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Notify listeners that a new value was encountered
     *
     * @param value
     */
    private void notifyEncounter(int value) {
        Event event = new Event(value);
        for (Listener listener : listeners) {
            listener.handleEncounter(event);
        }
    }

    /**
     * Travel over the array and notify registered listeners
     */
    public void travel() {
        for (int i = 0; i < array.length; i += 1) {
            notifyEncounter(array[i]);
        }
    }


    ///////////////////
    // Inner Classes //
    ///////////////////

    /**
     * The listener interface used to lister to the travel
     */
    public static interface Listener {

        void handleEncounter(Event event);
    }

    /**
     * Information about the encounter of an element,
     */
    public static class Event {

        private int value;

        public Event(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}

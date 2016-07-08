package classes.anonymous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Demonstration of anonymous class usage
 */
public class SimpleAnonymousExample {

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        SimpleAnonymousExample example = new SimpleAnonymousExample();

        System.out.println("Forward");
        example.printSorted(false);

        System.out.println("Backward");
        example.printSorted(true);

    }



    private ArrayList<Integer> list;

    /**
     * Constructor
     */
    public SimpleAnonymousExample() {
        this.list = new ArrayList<Integer>();
        this.list.add(9);
        this.list.add(3);
        this.list.add(8);
        this.list.add(7);
        this.list.add(10);
    }

    /**
     * Print the numbers in a sorted way
     *
     * @param isBackward - 'true' if printing should be in reverse order
     */
    public void printSorted(final boolean isBackward) {

        // create copy of array
        ArrayList<Integer> copy = new ArrayList<Integer>(this.list);

        // sort based on 'isBackward' parameter
        Collections.sort(copy, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // accessing the final variable of the containing method
                if (isBackward) {
                    return o2 - o1;
                } else {
                    return o1 - o2;
                }
            }
        });

        // print values in order
        for (int i = 0; i < copy.size(); i += 1) {
            System.out.println(copy.get(i));
        }

    }

}

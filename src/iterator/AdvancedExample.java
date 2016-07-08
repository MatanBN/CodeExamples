package iterator;

/**
 * Demonstrate iteration over a SearchTree with for each
 */
public class AdvancedExample {

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // create tree
        SearchTree<Integer> tree = new SearchTree<Integer>();

        // add elements
        tree.add(7);
        tree.add(3);
        tree.add(8);
        tree.add(19);
        tree.add(20);

        // iterate and print all
        for(Integer value : tree) {
            System.out.println(value);
        }

    }

}

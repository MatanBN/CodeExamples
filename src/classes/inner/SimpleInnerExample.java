package classes.inner;

/**
 * Created with IntelliJ IDEA.
 * User: mcshlain
 * Date: 5/24/14
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleInnerExample {

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        SimpleInnerExample example = new SimpleInnerExample();

        System.out.println("Forward:");
        example.printAll();

        System.out.println("Backward:");
        example.setBackwardMode(true);
        example.printAll();

    }

    // array of integers
    private int[] array;

    // responsible for printing the array
    private Printer printer;

    public SimpleInnerExample() {
        this.array = new int[]{3,7,8,9,10};
        // default printing is forward
        this.printer = new ForwardPrinter();
    }

    public void setBackwardMode(boolean isBackward) {
        // create appropriate printer based on parameter
        if(isBackward){
            this.printer = new BackwardPrinter();
        } else {
            this.printer = new ForwardPrinter();
        }
    }

    /**
     * Print array values
     */
    public void printAll(){
        this.printer.print();
    }

    ///////////////////
    // Inner classes //
    ///////////////////

    /**
     * Inner interface for an object that knows how to print
     */
    private interface Printer {
        void print();
    }

    /**
     * Implementation of Printer that prints the outer object
     * array from start to end
     */
    private class ForwardPrinter implements Printer {

        @Override
        public void print() {
            for(int i = 0; i < array.length; i+= 1) {
                // access array of outer class
                System.out.println(array[i]);
            }
        }

    }

    /**
     * Implementation of Printer that prints the outer object
     * array from end to start
     */
    private class BackwardPrinter implements Printer {

        @Override
        public void print() {
            for(int i = array.length - 1; i >= 0; i-= 1) {
                // access array of outer class
                System.out.println(array[i]);
            }
        }

    }

}

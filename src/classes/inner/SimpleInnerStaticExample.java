package classes.inner;

/**
 * Created with IntelliJ IDEA.
 * User: mcshlain
 * Date: 5/24/14
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleInnerStaticExample {

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ArrayTraveller traveller = new ArrayTraveller(new int[]{3, 7, 2, 1});

        traveller.addListener(new ArrayTraveller.Listener() {
            @Override
            public void handleEncounter(ArrayTraveller.Event event) {
                System.out.println(event.getValue());
            }
        });

        traveller.addListener(new ArrayTraveller.Listener() {
            @Override
            public void handleEncounter(ArrayTraveller.Event event) {
                if (event.getValue() == 7) {
                    System.out.println("BOOM!");
                }
            }
        });

        traveller.travel();
    }

}

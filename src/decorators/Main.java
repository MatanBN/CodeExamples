package decorators;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A graphical demonstration of using circles with different combinations of behaviours using the Decorator pattern.
 */
public class Main {

    /**
     * application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // create gui + related object
        int stageWidth = 800;
        int stageHeight = 600;

        GUI gui = new GUI("Decorator example", stageWidth, stageHeight);
        Sleeper sleeper = new Sleeper();

        List<MovingCircle> movingCircles = new ArrayList<MovingCircle>();

        // create a circle with blue filling
        // that can move and will wrap around the screen edges
        movingCircles.add(new MovingCircle(5,
                new SpaceBendingDecorator(stageWidth, stageHeight,
                        new FillDrawingDecorator(Color.BLUE,
                                new DefaultCircle(150, 150, 50)
                        ))));


        // create a circle with green filling
        // that has a red center point
        // a black coordinates indication and can move (but not wrap around the edges)
        movingCircles.add(new MovingCircle(5,
                new CoordsDrawingDecorator(Color.BLACK,
                        new CenterDrawingDecorator(Color.RED,
                                new FillDrawingDecorator(Color.GREEN,
                                        new DefaultCircle(150, 450, 80)
                                )))));


        // create a circle with all behaviours
        movingCircles.add(new MovingCircle(10,
                new SpaceBendingDecorator(stageWidth, stageHeight,
                        new CoordsDrawingDecorator(Color.WHITE,
                                new CenterDrawingDecorator(Color.RED,
                                        new FillDrawingDecorator(Color.BLACK,
                                                new DefaultCircle(350, 150, 110)
                                        ))))));


        // create a circle with all behaviours except for coordinates drawing
        movingCircles.add(new MovingCircle(5,
                new SpaceBendingDecorator(stageWidth, stageHeight,
                                new CenterDrawingDecorator(Color.BLACK,
                                        new FillDrawingDecorator(Color.MAGENTA,
                                                new DefaultCircle(250, 250, 30)
                                        )))));


        // create a circle that can only move
        movingCircles.add(new MovingCircle(5,new DefaultCircle(450, 350, 20)));


        // animation loop
        while (true) {

            DrawSurface ds = gui.getDrawSurface();

            // process all circles
            for (MovingCircle circle : movingCircles) {

                // decide if to move the circle up or down
                if (gui.getKeyboardSensor().isPressed(KeyboardSensor.DOWN_KEY)) {
                    circle.moveDown();
                } else if (gui.getKeyboardSensor().isPressed(KeyboardSensor.UP_KEY)) {
                    circle.moveUp();
                }

                // decide if to move the circle left or right
                if (gui.getKeyboardSensor().isPressed(KeyboardSensor.LEFT_KEY)) {
                    circle.moveLeft();
                } else if (gui.getKeyboardSensor().isPressed(KeyboardSensor.RIGHT_KEY)) {
                    circle.moveRight();
                }

                circle.drawOn(ds);
            }

            gui.show(ds);

            sleeper.sleepFor(50);
        }

    }


}

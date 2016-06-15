import animations.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.HighScoresTable;

import java.io.File;

/**
 * The Ass7Game initializes and start the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class Ass7Game {
    /**
     * The main creates the game object, initializes it and runs it.
     *
     * @param args the input from command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Space Invaders", 800, 600);
        AnimationRunner myAnimationRunner = new AnimationRunner(60, gui);
        HighScoresTable myScores = new HighScoresTable(10);
        try {
            myScores.load(new File("highscores"));
        } catch (Exception e) {
            System.out.print("Unable to load the file");
        }
        String fileName;
        if (args.length >= 1) {
            fileName = args[0];
        } else {
            fileName = "level_sets.txt";
        }
        GameFlow gameFlow = new GameFlow(myAnimationRunner, gui.getKeyboardSensor(), 7, myScores);
        gameFlow.chooseTask();
    }
}

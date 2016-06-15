package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.HighScoresTable;
import game.ScoreInfo;

/**
 * HighScoresAnimation will display the scores in the high-scores table,
 * until a specified key is pressed.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private String endKey;
    private KeyboardSensor ks;
    private boolean stop;

    /**
     * Constructor to create the HighScoresAnimation.
     *
     * @param scores is the HighScoreTable of the game.
     * @param endKey is the key to stop the animation.
     * @param ks     is the keyboard sensor.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor ks) {
        this.scoresTable = scores;
        this.endKey = endKey;
        this.ks = ks;
        this.stop = false;
    }

    /**
     * doOneFrame method draws the HighScores table.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        for (int i = 0; i < scoresTable.currentSize(); ++i) {
            ScoreInfo score = scoresTable.getScore(i);
            d.drawText(50, 50 * (i + 1), score.getName() + ": " + score.getScore(), 20);
        }
        if (ks.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * shouldStop method returns the value of stop.
     *
     * @return the value of the stop variable.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;

    }
}

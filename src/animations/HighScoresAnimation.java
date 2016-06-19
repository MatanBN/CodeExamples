package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.HighScoresTable;
import game.ScoreInfo;

import java.awt.*;

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
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, d.getWidth() - 20, d.getHeight());
        d.setColor(Color.black);
        d.drawText(73, 70, "High Scores", 50);
        d.drawLine(80,149,680,149);
        d.drawLine(80,156,680,156);
        d.drawText(81, 139, "Player Name", 25);
        d.drawText(481, 139, "Score", 25);
        d.setColor(Color.YELLOW);
        d.drawText(70, 70, "High Scores", 50);
        d.setColor(Color.green);
        d.drawText(80, 140, "Player Name", 25);
        d.drawText(480, 140, "Score", 25);
        d.fillRectangle(80,150,600,6);
        d.setColor(Color.cyan);
        d.drawText(230,500, "Press space to continue", 30);
        d.setColor(Color.black);
        d.drawText(231,502, "Press space to continue", 30);
        String playerScore;
        d.setColor(Color.orange);
        for (int i = 0; i < scoresTable.currentSize(); ++i) {
            ScoreInfo score = scoresTable.getScore(i);
            d.drawText(85, 180 * (i + 1), score.getName() , 20);
            playerScore = Integer.toString(score.getScore());
            d.drawText(485, 180 * (i + 1), playerScore, 20);
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

package game;

import java.io.Serializable;

/**
 * ScoreInfo holds the name of the player and his score.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Constructor create a ScoreInfo object.
     *
     * @param name  is the player's name.
     * @param score is the player's score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName returns the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore returns the player's score.
     *
     * @return the player's score.
     */
    public int getScore() {
        return this.score;
    }
}

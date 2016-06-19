package listeners;

import game.Counter;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Sprite;

/**
 * ScoreTrackingListener updates the counter when blocks are being hit and removed from the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * The constructor gets the score counter and updates it.
     *
     * @param scoreCounter is the counter that is a new counter given.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent notifies that there is a hit and increases the score counter by 5 when it hits a block
     * and 10 when removing one.
     *
     * @param beingHit is the block that is being hit.
     * @param hitter   is the Ball that's doing the hitting.
     */
    public void hitEvent(BaseBlock beingHit, Sprite hitter) {
        this.currentScore.increase(100);
    }
}

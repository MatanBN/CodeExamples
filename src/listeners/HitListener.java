package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * HitListener Objects are notified of hit events.
 *
 * @author Matan Ben Noach Nir Ben Shalom.
 * @version 1.0 9 April 2016.
 */

public interface HitListener {

    /**
     * hitEvent is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block tat is being hit.
     * @param hitter   is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

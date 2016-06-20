package listeners;

import animations.GameLevel;
import game.Counter;
import game.GroupMovement;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Invader;
import sprites.Sprite;

/**
 * AlienRemover is in charge of removing aliens from the gameLevel, as well as keeping count
 * of the number of aliens that remain.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class AlienRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * The constructor gets the gamelevel and a counter of the removed aliens.
     *
     * @param gl            is the current gamelevel of the game.
     * @param removedBlocks a counter that holds the removed blocks number.
     */
    public AlienRemover(GameLevel gl, Counter removedBlocks) {
        gameLevel = gl;
        remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent notifies that there is a hit with a "AlienRemover" block and removes it.
     *
     * @param beingHit is the block tat is being hit.
     * @param hitter   is the Ball that's doing the hitting.
     */
    public void hitEvent(BaseBlock beingHit, Ball hitter) {
        if (!hitter.isAlien()) {
            beingHit.removeFromGame(gameLevel);
            remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
        }
    }
}

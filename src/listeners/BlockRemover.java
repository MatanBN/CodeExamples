package listeners;

import animations.GameLevel;
import game.Counter;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Sprite;

/**
 * BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel; //The current gamelevel
    private Counter remainingBlocks; // A counter of the remaining balls

    /**
     * The constructor gets the gamelevel and a counter of the removed balls.
     *
     * @param gameLevel     is the current gamelevel of the game.
     * @param removedBlocks a counter that holds the removed blocks number.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.

    /**
     * hitEvent notifies that there is a hit with a "BlockRemover" block and removes this block.
     *
     * @param beingHit is the specific block that is being hit.
     * @param hitter   is the ball that hit the block.
     */
    public void hitEvent(BaseBlock beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }


}

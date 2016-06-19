package listeners;

import animations.GameLevel;
import game.Counter;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Sprite;

/**
 * BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */


public class BallRemover implements HitListener {
    private GameLevel gameLevel;  //The current gamelevel

    /**
     * The constructor gets the gamelevel and a counter of the removed balls.
     *
     * @param gameLevel    is the current gamelevel of the game.
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.

    /**
     * hitEvent notifies that there is a hit with a "BallRemover" block and removes the ball.
     *
     * @param deathRegion is the block on the bottom of the screen and in charge of removing the balls.
     * @param beingHit    is the ball that hit the block.
     */
    public void hitEvent(BaseBlock deathRegion, Sprite beingHit) {
        Ball ball = (Ball) beingHit;
        ball.removeFromGame(gameLevel);
    }
}

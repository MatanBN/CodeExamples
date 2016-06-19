package listeners;

import animations.GameLevel;
import game.Velocity;
import geometry.Point;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Sprite;


/**
 * BallAdder is a class that implements a HitListener  in order to create new balls and when
 * there is a hit with a block of this type.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class BallAdder implements HitListener {
    private GameLevel gameLevel; //The current gamelevel

    /**
     * The constructor gets the gamelevel and a counter of the removed balls.
     *
     * @param gameLevel is the current gamelevel of the game.
     */
    public BallAdder(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * hitEvent notifies that there is a hit with a "BallAdder" block and creates a ball and removes
     * the hitListener from the its list.
     *
     * @param beingHit is the specific block that is being hit.
     * @param hitter   is the ball that hit the block.
     */
    public void hitEvent(BaseBlock beingHit, Sprite hitter) {
        gameLevel.createBall(new Point(50, 90), 2, new Velocity(240, -180));
        beingHit.removeHitListener(this);
    }
}

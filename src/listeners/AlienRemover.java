package listeners;

import animations.GameLevel;
import game.Counter;
import game.GroupMovement;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Invader;
import sprites.Sprite;

/**
 * Created by user on 19/06/2016.
 */
public class AlienRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    public AlienRemover(GameLevel gl, Counter removedBlocks) {
        gameLevel = gl;
        remainingBlocks = removedBlocks;
    }

    public void hitEvent(BaseBlock beingHit, Ball hitter) {
        if (!hitter.isAlien()) {
            beingHit.removeFromGame(gameLevel);
            remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
        }
    }
}

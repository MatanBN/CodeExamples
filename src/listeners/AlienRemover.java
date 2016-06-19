package listeners;

import animations.GameLevel;
import game.Counter;
import game.GroupMovement;
import sprites.BaseBlock;
import sprites.Invader;
import sprites.Sprite;

/**
 * Created by user on 19/06/2016.
 */
public class AlienRemover implements HitListener {
    private GroupMovement groupMovement;
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private String harta;

    public AlienRemover(GameLevel gl, Counter removedBlocks, GroupMovement gm) {
        gameLevel = gl;
        groupMovement = gm;
        remainingBlocks = removedBlocks;
    }

    public void hitEvent(BaseBlock beingHit, Sprite hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        groupMovement.remove((Invader) beingHit);
    }
}

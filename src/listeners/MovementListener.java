package listeners;

import game.GroupMovement;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Sprite;

/**
 * Created by Matan on 6/19/2016.
 */
public class MovementListener implements HitListener {
    private GroupMovement gm;

    public MovementListener(GroupMovement gm) {
        this.gm = gm;
    }

    @Override
    public void hitEvent(BaseBlock beingHit, Sprite hitter) {
        gm.setSpeed(((gm.getSpeed() * -1) * 1.1));
        gm.goDown();

    }
}

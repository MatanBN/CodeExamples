package game;

import sprites.SpriteCollection;

/**
 * Created by Matan on 6/15/2016.
 */
public class GroupMovement {
    private SpriteCollection invaders;

    public GroupMovement(SpriteCollection invaders) {
        this.invaders = invaders;
    }

    public void notifyInvaders(double dt) {
        invaders.notifyAllTimePassed(dt);
    }
}

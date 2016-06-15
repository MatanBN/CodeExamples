package game;

import sprites.Invaders;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.util.ArrayList;

/**
 * Created by Matan on 6/15/2016.
 */
public class GroupMovement extends SpriteCollection {
    public GroupMovement() {
        super();
    }

    public void verticalMovement() {
        ArrayList<Sprite> invaders = super.getSprites();
        for (Sprite s : invaders) {
            Invaders inv = (Invaders) s;
            inv.goDown();
            inv.setSpeed(inv.getSpeed() + Math.signum(inv.getSpeed()) * 10);
            inv.setSpeed(inv.getSpeed() * -1);
        }
    }

}

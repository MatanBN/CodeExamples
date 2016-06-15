package game;

import sprites.Invaders;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.util.ArrayList;

/**
 * Created by Matan on 6/15/2016.
 */
public class GroupMovement {

    public void verticalMovement() {
        for (Invaders s : invaders) {
            Invaders inv = (Invaders) s;
            inv.goDown();
            inv.setSpeed(inv.getSpeed() + Math.signum(inv.getSpeed()) * 10);
            inv.setSpeed(inv.getSpeed() * -1);
        }
    }

}

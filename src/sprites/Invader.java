package sprites;

import animations.GameLevel;
import biuoop.DrawSurface;
import environment.CollisionInfo;
import environment.GameEnvironment;
import game.GroupMovement;
import game.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;

/**
 * Created by Matan on 6/15/2016.
 */
public class Invader extends BaseBlock {
    private Image invaderImage;
    private GroupMovement gm;
    private int firstY;

    public Invader(Rectangle r, Image img, int firstY) {
        super(r);
        this.invaderImage = img;
        this.firstY = firstY;
    }

    public Invader(int x, int y, int width, int height, Image img, int firstY) {
        super(new Rectangle(x, y, width, height));
        this.invaderImage = img;
        this.firstY = firstY;

    }

    @Override
    public void timePassed(double dt) {

    }

    public void goDown() {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setY(p.getY() + 10);
    }

    public void drawOn(DrawSurface d) {
        d.drawImage(this.getRectangle().getX(), this.getRectangle().getY(), invaderImage);
    }

    public double getX (){
        return this.getRectangle().getX();
    }

    public double getY() {
        return this.getRectangle().getY();
    }

    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gm.remove(this);
    }

    public void setGm(GroupMovement gm) {
        this.gm = gm;
    }

    public int getFirstY() {
        return firstY;
    }
}

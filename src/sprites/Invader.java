package sprites;

import biuoop.DrawSurface;
import game.GroupMovement;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;

/**
 * Created by Matan on 6/15/2016.
 */
public class Invader extends BaseBlock {
    private GroupMovement gm;
    private Image invaderImage;

    public Invader(Rectangle r, Image img) {
        super(r);
        this.invaderImage = img;
    }

    public Invader(int x, int y, int width, int height, Image img) {
        super(new Rectangle(x, y, width, height));
        this.invaderImage = img;
    }

    @Override
    public void timePassed(double dt) {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setX(p.getX() + gm.getSpeed() * dt);
    }

    public void goDown() {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setY(p.getY() + 10);
        gm.setSpeed((gm.getSpeed() + (int) Math.signum(100.0)) * -1);
    }

    public void drawOn(DrawSurface d) {
        d.drawImage(this.getRectangle().getX(), this.getRectangle().getY(), invaderImage);
    }

    public void setGm(GroupMovement gm) {
        this.gm = gm;
    }
}

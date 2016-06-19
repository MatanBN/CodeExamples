package sprites;

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
}

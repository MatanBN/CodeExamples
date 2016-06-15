package sprites;

import geometry.Point;
import geometry.Rectangle;

/**
 * Created by Matan on 6/15/2016.
 */
public class Invaders extends BaseBlock {
    private double speed;

    public Invaders(Rectangle r) {
        super(r);
        this.speed = 600;
    }

    public Invaders(Rectangle r, Sprite fill) {
        super(r, fill);
        this.speed = 600;

    }

    public Invaders(int x, int y, int width, int height, Sprite fill) {
        super(x, y, width, height, fill);
        this.speed = 600;
    }

    @Override
    public void timePassed(double dt) {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setX(p.getX() + speed * dt);
    }

    public void goDown() {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setY(p.getY() + 10);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

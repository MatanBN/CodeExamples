package patterns.factory.good;

import biuoop.DrawSurface;
import patterns.factory.bad.MovingSprite;
import patterns.factory.bad.Velocity;

import java.awt.*;

/**
 * Created by mcshlain on 6/19/16.
 */
public class Missile implements MovingSprite {


    private double x;
    private double y;
    private Velocity velocity;

    public Missile(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    @Override
    public void timePassed(double dt) {
        if (this.velocity != null) {
            this.x += this.velocity.getDx() * dt;
            this.y += this.velocity.getDy() * dt;
        }
    }

    @Override
    public void drawOn(DrawSurface ds) {
        ds.setColor(Color.BLUE);
        ds.fillRectangle((int) this.x - 30, (int) this.y - 10, 60, 20);
        ds.setColor(Color.BLACK);
        ds.drawRectangle((int) this.x - 30, (int) this.y - 10, 60, 20);

        Polygon missileHead = new Polygon();
        missileHead.addPoint((int)this.x + 30, (int)this.y - 30);
        missileHead.addPoint((int)this.x + 30, (int)this.y + 30);
        missileHead.addPoint((int)this.x + 60, (int)this.y);

        ds.setColor(Color.RED);
        ds.fillPolygon(missileHead);
        ds.setColor(Color.BLACK);
        ds.drawPolygon(missileHead);
    }
}

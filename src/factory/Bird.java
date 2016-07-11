package patterns.factory.good;

import biuoop.DrawSurface;
import patterns.factory.bad.MovingSprite;
import patterns.factory.bad.Velocity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by mcshlain on 6/19/16.
 */
public class Bird implements MovingSprite {

    private BufferedImage[] frames;
    private double x;
    private double y;
    private Velocity velocity;

    private int currentFrame;
    private double framesCountdown;
    private double framesSpeed;

    public Bird(double x, double y) {
        this.x = x;
        this.y = y;
        this.currentFrame = 0;

        this.framesSpeed = 0.05;
        this.framesCountdown = this.framesSpeed;

        this.frames = new BufferedImage[4];

        try {
            this.frames[0] = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("frame1.png"));
            this.frames[1] = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("frame2.png"));
            this.frames[2] = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("frame3.png"));
            this.frames[3] = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("frame4.png"));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load cannon image", e);
        }

    }

    @Override
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    @Override
    public void timePassed(double dt) {
        this.framesCountdown -= dt;

        // switch frame if needed
        if (this.framesCountdown <= 0) {
            this.currentFrame = (this.currentFrame + 1) % this.frames.length;
            this.framesCountdown = this.framesSpeed;
        }

        // change location based on velocity
        if (this.velocity != null) {
            this.x += this.velocity.getDx() * dt;
            this.y += this.velocity.getDy() * dt;
        }
    }

    @Override
    public void drawOn(DrawSurface ds) {

        int centerX = (int)(this.x - this.frames[0].getWidth()/2);
        int centerY = (int)(this.y - this.frames[0].getHeight()/2);

        ds.drawImage(centerX, centerY, this.frames[this.currentFrame]);
    }
}

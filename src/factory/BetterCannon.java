package patterns.factory.good;

public class BetterCannon implements Sprite {

    private KeyboardSensor keyboardSensor;
    private SpriteCollection spriteCollection;

    private double x;
    private double y;

    private BufferedImage image;
    private double coolDown;

    private BulletFactory bulletFactory;

    public BetterCannon(double x, double y, KeyboardSensor keyboardSensor,
                        SpriteCollection spriteCollection,
                        BulletFactory bulletFactory) {
        this.keyboardSensor = keyboardSensor;
        this.spriteCollection = spriteCollection;
        this.x = x;
        this.y = y;
        this.coolDown = 0;

        this.bulletFactory = bulletFactory;

        // Load image for the cannon
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("cannon.png");
        try {
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load cannon image", e);
        }
    }

    @Override
    public void timePassed(double dt) {

        this.coolDown -= dt;

        if(this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)
           && coolDown <= 0) {
            MovingSprite bullet = this.bulletFactory.create(
                    this.x + this.image.getWidth()/2,
                    // -10, cause image not exactly symmetrical
                    this.y + this.image.getHeight()/2 - 10);

            bullet.setVelocity(Velocity.fromAngleAndSpeed(90, 400));

            this.spriteCollection.addSprite(bullet);

            this.coolDown = 0.5;
        }
    }

    @Override
    public void drawOn(DrawSurface ds) {
        ds.drawImage((int)this.x,(int)this.y, this.image);
    }
}

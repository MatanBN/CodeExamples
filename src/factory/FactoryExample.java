package patterns.factory.good;

public class FactoryExample {

    public static void main(String[] args) {

        GUI gui = new GUI("Cannon", 1200, 600);
        Sleeper sleeper = new Sleeper();
        SpriteCollection screen = new SpriteCollection();

        SpriteCollection bulletsLayer = new SpriteCollection();
        SpriteCollection cannonLayer = new SpriteCollection();

        // make sure bullets are drawn behind the cannon
        screen.addSprite(bulletsLayer);
        screen.addSprite(cannonLayer);

        BetterCannon cannon1 = new BetterCannon(
                5, 100, gui.getKeyboardSensor(), bulletsLayer,
                new MissileFactory());

        BetterCannon cannon2 = new BetterCannon(
                5, 300, gui.getKeyboardSensor(), bulletsLayer,
                new BirdsFactory());

        cannonLayer.addSprite(cannon1);
        cannonLayer.addSprite(cannon2);

        int fps = 60;

        while (true){
            screen.timePassed(1.0/fps);
            DrawSurface ds = gui.getDrawSurface();
            screen.drawOn(ds);
            gui.show(ds);
            sleeper.sleepFor(1000/fps);
        }
    }

    private static class MissileFactory implements BulletFactory {

        @Override
        public MovingSprite create(double x, double y) {
            return new Missile(x, y);
        }
    }

    private static class BirdsFactory implements BulletFactory {

        @Override
        public MovingSprite create(double x, double y) {
            return new Bird(x, y);
        }
    }
}

package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;


/**
 * MenuAnimation display the menu of the game.
 * @param <T> generic object that the menu has.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class MenuAnimation<T> implements Menu {
    private AnimationRunner ar;
    private ArrayList<Selection> menuSelections;
    private KeyboardSensor ks;
    private boolean stopper;
    private String key;
    private String message;

    /**
     * Constructor to create the MenuAnimation.
     *
     * @param ks      is the keyboard sensor.
     * @param ar      is the animation runner from the gameflow.
     * @param message is the message at the head of the menu.
     */
    public MenuAnimation(KeyboardSensor ks, AnimationRunner ar, String message) {
        this.ar = ar;
        menuSelections = new ArrayList<Selection>();
        this.ks = ks;
        this.message = message;
        this.stopper = false;
    }

    /**
     * doOneFrame method draws the menu.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, d.getWidth() - 20, d.getHeight());
        d.setColor(Color.YELLOW);
        d.drawText(200, 100, message, 32);
        for (int i = 1; i <= menuSelections.size(); i++) {
            d.drawText(200, 100 + (i * 50), menuSelections.get(i - 1).getMessage() + "- "
                    + menuSelections.get(i - 1).getKey(), 20);
        }
        for (int i = 0; i < menuSelections.size(); i++) {
            if (ks.isPressed(menuSelections.get(i).getKey())) {
                key = menuSelections.get(i).getKey();
                this.stopper = true;
            }
        }
    }

    /**
     * shouldStop method returns the value of stopper.
     *
     * @return the value of the stopper variable.
     */
    @Override
    public boolean shouldStop() {
        return this.stopper;
    }

    /**
     * addSelection adds a new task to the list of the game's tasks.
     *
     * @param newKey     is the key to press in order to run the task.
     * @param newMessage is the task's name.
     * @param returnVal  is the object that runs the task.
     */
    @Override
    public void addSelection(String newKey, String newMessage, Object returnVal) {
        menuSelections.add(new Selection(newKey, newMessage, (T) returnVal, false));
    }

    /**
     * getStatus checks which task has been chosen and return it;
     * if it's a sub-menu the method runs this sub-menu an return its task.
     *
     * @return the returnVal of that task.
     */
    @Override
    public T getStatus() {

        int i = getIndex(key);
        Selection select = menuSelections.get(i);

        if (select.isSubMenu()) {
            Menu<T> subMenu = (Menu<T>) select.returnVal;
            ar.run(subMenu);
            T task = subMenu.getStatus();
            return task;
        }
        return menuSelections.get(i).returnVal;
    }

    /**
     * addSubMenu adds a new sub-menu to a menu's selections list.
     *
     * @param newKey     is the key to press in order to run the task.
     * @param newMessage is the task's name.
     * @param subMenu    is a menu object.
     */
    @Override
    public void addSubMenu(String newKey, String newMessage, Menu subMenu) {
        menuSelections.add(new Selection(newKey, newMessage, (T) subMenu, true));
    }

    /**
     * getIndex gets a string which represent a task and returns its index in the selections list.
     *
     * @param s is the string of the task to check it in the list.
     * @return the index in the selections list.
     */
    public int getIndex(String s) {
        for (int i = 0; i < menuSelections.size(); i++) {
            if (menuSelections.get(i).getKey().equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Selection is an inner class that holds the values of each task.
     */
    private class Selection {
        private String key;
        private String message;
        private T returnVal;
        private boolean subMenu;

        /**
         * Constructor create the a tak.
         *
         * @param key       is the key to press in order to run the task.
         * @param message   is the task's name.
         * @param returnVal is the object that runs the task.
         * @param subMenu   is a boolean that gets true in sub-menu types and false otherwise.
         */
        public Selection(String key, String message, T returnVal, boolean subMenu) {
            this.key = key;
            this.message = message;
            this.returnVal = returnVal;
            this.subMenu = subMenu;
        }

        /**
         * getKey returns the key of the task.
         *
         * @return the key of the task.
         */
        public String getKey() {
            return this.key;
        }

        /**
         * getMassage returns the task's name.
         *
         * @return the task's name.
         */
        public String getMessage() {
            return this.message;
        }

        /**
         * getReturnVal returns the object that runs the task.
         *
         * @return the object that runs the task.
         */
        public T getReturnVal() {
            return this.returnVal;
        }

        /**
         * isSubMenu returns true if its a sub-menu object and false otherwise.
         *
         * @return true if its a sub-menu object and false otherwise.
         */
        public boolean isSubMenu() {
            return subMenu;
        }
    }

}

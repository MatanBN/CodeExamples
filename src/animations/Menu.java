package animations;


/**
 * Menu holds 2 statements of any menu Object.
 * @param <T> generic object that the menu has.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public interface Menu<T> extends Animation {
    /**
     * addSelection adds a new task to the list of the game's tasks.
     *
     * @param key       is the key to press in order to run the task.
     * @param message   is the task's name.
     * @param returnVal is the object that runs the task.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * getStatus checks which task has been chosen.
     *
     * @return the returnVal of that task.
     */
    T getStatus();

    /**
     * addSubMenu adds a new sub-menu to an existed menu.
     *
     * @param key     is the key to press in order to run the task.
     * @param message is the task's name.
     * @param subMenu is a menu object.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}

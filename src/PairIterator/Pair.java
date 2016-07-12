/**
 * Created by Matan on 7/10/2016.
 */
public class Pair<E> {
    private E e1;
    private E e2;

    public Pair(E e1, E e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public E first() {
        return this.e1;
    }

    public E second() {
        return this.e2;
    }
}

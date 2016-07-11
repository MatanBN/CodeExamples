package patterns.iterator;

import java.util.Iterator;

/**
 * Created by mcshlain on 7/4/15.
 */
public class CurrentIteratorAdapter<E> {

    private Iterator<E> itor;
    private E element;

    public CurrentIteratorAdapter(Iterator<E> itor) {
        this.itor = itor;
        // Need to hold the first element
        next();
    }

    public boolean hasNext() {
        return this.element != null;
    }

    public E value() {
        return this.element;
    }

    public void next() {
        if(itor.hasNext()) {
            this.element = itor.next();
        } else {
            this.element = null;
        }
    }

}

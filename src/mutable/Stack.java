package stack.mutable;

import java.util.Iterator;

/**
 * Created by mcshlain on 7/4/15.
 */
public interface Stack<E> extends Iterable<E> {

    void push(E elem);

    void pop();

    E top();

    int size();

    boolean isEmpty();

}

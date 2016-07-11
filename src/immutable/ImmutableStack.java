package stack.immutable;

/**
 * Created by mcshlain on 7/4/15.
 */
public interface ImmutableStack<E> extends Iterable<E>{

    ImmutableStack<E> push(E elem);

    ImmutableStack<E> pop();

    E top();

    int size();

    boolean isEmpty();

}

package stack.mutable;

import java.util.Iterator;

/**
 * Created by mcshlain on 7/4/15.
 */
public class LinkedListStack<E> implements Stack<E> {

    private Node<E> head;
    private int size;

    public LinkedListStack() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void push(E elem) {
        Node<E> newHead = new Node<E>(elem);
        newHead.setNext(this.head);
        this.head = newHead;

        this.size += 1;
    }

    @Override
    public void pop() {
        if(this.size == 0) {
            throw new RuntimeException("pop empty!");
        }

        this.head = this.head.getNext();
        this.size -= 1;
    }

    @Override
    public E top() {
        if(this.size == 0) {
            throw new RuntimeException("top empty!");
        }

        return this.head.getData();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size != 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private static class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<E> {

        private Node<E> currentNode;

        public ListIterator() {
            this.currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E value = this.currentNode.data;
            this.currentNode = this.currentNode.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}

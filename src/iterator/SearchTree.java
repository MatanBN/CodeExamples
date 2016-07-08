package iterator;

import java.util.*;

/**
 * A non self balancing search tree implementation.
 * Demonstrates the use of inner classes for iterator and internal structure
 * of Node.
 * Also uses generics that allow only comparable classes to be elements in this
 * data structure.
 */
public class SearchTree<T extends Comparable<T>> implements Iterable<T> {

    // The root node of the tree
    private Node<T> root;

    // Revision number to log changes in the structure
    private int currentRevision;

    /**
     * Default Constructor.
     */
    public SearchTree() {
        this.root = null;
        this.currentRevision = 0;
    }

    /**
     * Add element
     *
     * @param value value
     */
    public void add(T value) {

        // if no root just add as root
        if (root == null) {
            this.root = new Node<T>(value);
            return;
        }

        // check for uniqueness
        if (contains(value)) {
            return;
        }


        // insert value as new node
        insertNode(new Node<T>(value), this.root);

        // tree has changed so revision is incremented
        this.currentRevision += 1;
    }

    /**
     * Recursively add a node at another node, if no place
     * propagate the call to the correct child  based on search
     * tree ordering
     *
     * @param newNode - the new node to add
     * @param at      - where to add it
     */
    private void insertNode(Node<T> newNode, Node<T> at) {

        // check if value is smaller that the node
        if (newNode.getValue().compareTo(at.getValue()) <= 0) {
            // go left
            if (!at.hasLeftChild()) {
                at.setLeftChild(newNode);
                newNode.setParent(at);
            } else {
                insertNode(newNode, at.getLeftChild());
            }
        } else {
            // go right
            if (!at.hasRightChild()) {
                at.setRightChild(newNode);
                newNode.setParent(at);
            } else {
                insertNode(newNode, at.getRightChild());
            }
        }
    }

    /**
     * Is the data structure contain a value?
     *
     * @param value the value to check
     * @return 'true' if it exists, 'false' otherwise
     */
    public boolean contains(T value) {
        if (this.root == null) {
            return false;
        }

        return find(value, this.root) != null;
    }

    /**
     * Find a node with specified value
     * (Recursive )
     *
     * @param value the value to look for
     * @param at    where to search for
     * @return - the node or null if nothing is found.
     */
    public Node<T> find(T value, Node<T> at) {
        if (at.getValue().equals(value)) {
            return at;
        } else {
            if (value.compareTo(at.getValue()) <= 0) {
                if (at.hasLeftChild()) {
                    return find(value, at.getLeftChild());
                } else {
                    return null;
                }
            } else {
                if (at.hasRightChild()) {
                    return find(value, at.getRightChild());
                } else {
                    return null;
                }

            }
        }
    }

    /**
     * Remove a value from the data structure
     *
     * @param value
     */
    public void remove(T value) {
        if (this.root == null) {
            return;
        }

        // find node to remove
        Node<T> node = find(value, this.root);
        if (node == null) {
            return;
        }


        // update according to search tree rules
        if (!node.hasLeftChild() && !node.hasRightChild()) {
            removeLeaf(node);
        } else if (!node.hasLeftChild() && node.hasRightChild()) {
            replaceWith(node, node.getRightChild());
        } else if (node.hasLeftChild() && !node.hasRightChild()) {
            replaceWith(node, node.getLeftChild());
        } else { //node.hasLeftChild() && node.hasRightChild()
            Node<T> replacement = findRightMost(node.getLeftChild());
            Node<T> replacementParent = replacement.getParent();
            Node<T> replacementLeftChild = replacement.getLeftChild();

            replacement.setRightChild(node.getRightChild());
            if (node.hasRightChild()) {
                node.getRightChild().setParent(replacement);
            }

            if (replacement != node.getLeftChild()) {
                replacement.setLeftChild(node.getLeftChild());
                if (node.hasLeftChild()) {
                    node.getLeftChild().setParent(replacement);
                }
                replaceWith(node, replacement);

                replacementParent.setRightChild(replacementLeftChild);
            } else {
                replaceWith(node, replacement);
            }
        }

        // update revision because something was removed
        this.currentRevision += 1;
    }

    /**
     * Find right most node
     *
     * @param at right most node of
     * @return the node
     */
    private Node<T> findRightMost(Node<T> at) {
        if (!at.hasRightChild()) {
            return at;
        } else {
            return findRightMost(at.getRightChild());
        }
    }

    /**
     * Replace a node with a different node
     * updating correctly the parent and children
     *
     * @param node        the node to replace
     * @param replacement the node that will replace it
     */
    private void replaceWith(Node<T> node, Node<T> replacement) {
        Node<T> parent = node.getParent();

        if (parent == null) {
            this.root = replacement;
            replacement.setParent(null);
        } else {
            if (parent.getLeftChild() == node) {
                parent.setLeftChild(replacement);
                replacement.setParent(parent);
            } else {
                parent.setRightChild(replacement);
                replacement.setParent(parent);
            }
        }

        node.setLeftChild(null);
        node.setRightChild(null);
    }

    /**
     * Remove leaf node
     *
     * @param node the leaf node
     */
    private void removeLeaf(Node<T> node) {
        Node<T> parent = node.getParent();

        if (parent.getLeftChild() == node) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    /**
     * Amount of unique values in the data structure
     *
     * @return number of values
     */
    public int size() {
        if (root != null) {
            return root.size();
        } else {
            return 0;
        }
    }

    /**
     * Create a new iterator
     *
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<T>(this.currentRevision, this.root);
    }

    ////////////////////
    // Inner Classess //
    ////////////////////

    /**
     * Pre Order iterator
     *
     * @param <E> the value provided by the iterator
     */
    private class TreeIterator<E> implements java.util.Iterator<E> {

        // data revision for when the iterator was created
        private int iterationRevision;

        // visiting stack to keep track of nodes left to visit
        private Deque<Node<E>> visitingStack;

        public TreeIterator(int revision, Node<E> root) {
            this.iterationRevision = revision;
            this.visitingStack = new LinkedList<Node<E>>();
            this.visitingStack.push(root);
        }

        /**
         * Validate that the revision of the data structure still
         * matches the revision for which the iterator was created
         * if no match throw ConcurrentModificationException
         */
        private void validateRevision() {
            if (currentRevision != this.iterationRevision) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Is next element available
         *
         * @return 'true' if available, 'false' otherwise
         */
        @Override
        public boolean hasNext() {
            validateRevision();

            return !this.visitingStack.isEmpty();
        }

        /**
         * Get the next element.
         *
         * @return the element.
         */
        @Override
        public E next() {
            validateRevision();

            // throw appropriate exception if visiting stack is empty
            if (this.visitingStack.isEmpty()) {
                throw new NoSuchElementException();
            }

            // get next node
            Node<E> nextNode = this.visitingStack.pop();

            // update the visiting stack with next node children
            if (nextNode.hasRightChild()) {
                this.visitingStack.push(nextNode.getRightChild());
            }
            if (nextNode.hasLeftChild()) {
                this.visitingStack.push(nextNode.getLeftChild());
            }

            // return value of next node
            return nextNode.getValue();
        }

        /**
         * Not supported will throw  UnsupportedOperationException
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Helper class that represents a tree node
     *
     * @param <E> the value stored in the node
     */
    private static class Node<E> {

        private E value;
        private Node<E> parent;
        private Node<E> leftChild;
        private Node<E> rightChild;

        /**
         * Constructor.
         *
         * @param value value stored in the node
         */
        public Node(E value) {
            this(value, null);
        }

        /**
         * Constructor.
         *
         * @param value  value stored in the node
         * @param parent the parent node
         */
        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        /**
         * Get parent node
         *
         * @return parent node
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Set parent node
         *
         * @param parent the new parent
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        /**
         * Get value stored in the node
         *
         * @return the node
         */
        public E getValue() {
            return this.value;
        }

        /**
         * Get the amount of nodes in the subtree
         *
         * @return amount of nodes
         */
        public int size() {
            int result = 1;
            if (hasLeftChild()) {
                result += this.leftChild.size();
            }
            if (hasRightChild()) {
                result += this.rightChild.size();
            }
            return result;
        }

        /**
         * Is there a left child?
         *
         * @return 'true' if exists, 'false' otherwise
         */
        public boolean hasLeftChild() {
            return this.leftChild != null;
        }

        /**
         * Is there a right child?
         *
         * @return 'true' if exists, 'false' otherwise
         */
        public boolean hasRightChild() {
            return this.rightChild != null;
        }

        /**
         * Get the left child
         *
         * @return left child node
         */
        public Node<E> getLeftChild() {
            return this.leftChild;
        }

        /**
         * Set the left child
         *
         * @param leftChild new left child node
         */
        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        /**
         * Get right child
         *
         * @return right child node
         */
        public Node<E> getRightChild() {
            return this.rightChild;
        }

        /**
         * Set right child
         *
         * @param rightChild
         */
        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }
    }

}

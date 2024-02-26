package iterator_tdd;

import java.util.Iterator;


/**
 * Represents a list of integers, with a built-in iterator that is bidirectional and circular.
 */
public interface CircularList {

    /**
     * Adds an element to the list, namely, after the last inserted one.
     * @param element the element to be added to the list
     */
    void add(final int element);

    /**
     * Provides the current size of the list
     * @return the size of the list
     */
    int size();

    /**
     * Checks if the list is empty
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Creates a new iterator which yield elements from the list circularly, from first to last.
     * @return a new {@code Iterator<Integer>}
     */
    Iterator<Integer> forwardIterator();

    /**
     * Creates a new iterator which yield elements from the list circularly, from last to first.
     * @return a new {@code Iterator<Integer>}
     */
    Iterator<Integer> backwardIterator();
}


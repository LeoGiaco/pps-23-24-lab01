import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iterator_tdd.CircularList;
import iterator_tdd.CircularListImpl;

public class CircularListWithIteratorTest {
    private static final int LIST_SIZE = 10;
    private CircularList list;


    List<Integer> generateAndAddValuesToList(int numOfValues) {
        return Stream.iterate(0, i -> i+1)
                        .limit(numOfValues)
                        .peek(value -> list.add(value))
                        .toList();
    }
    
    @BeforeEach
    void beforeEach() {
        list = new CircularListImpl();
    }

    @Test
    void testListIsEmpty() {
        assertEquals(true, list.isEmpty());
    }

    @Test
    void testAddElementToList() {
        list.add(0);
        assertEquals(false, list.isEmpty());
    }

    @Test
    void testSizeOfListIsAccurate() {
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(0);
        }
        assertEquals(LIST_SIZE, list.size());
    }

    @Test
    void testForwardIteratorReturnsCorrectValues() {
        List<Integer> values = generateAndAddValuesToList(LIST_SIZE);
        Iterator<Integer> forwardIterator = list.forwardIterator();
        List<Integer> readValues = Stream.generate(forwardIterator::next)
                                        .limit(LIST_SIZE)
                                        .toList();
        assertIterableEquals(values, readValues);
    }

    @Test
    void testForwardIteratorLoops() {
        final int value = 0;
        list.add(value);
        list.add(value + 1);
        Iterator<Integer> forwardIterator = list.forwardIterator();
        forwardIterator.next();
        forwardIterator.next();
        assertEquals(value, forwardIterator.next());
    }

    @Test
    void testForwardIteratorThrows() {
        Iterator<Integer> forwardIterator = list.forwardIterator();
        assertThrows(NoSuchElementException.class, forwardIterator::next);
    }

    @Test
    void testBackwardIteratorReturnsCorrectValues() {
        List<Integer> values = generateAndAddValuesToList(LIST_SIZE);
        Iterator<Integer> backwardIterator = list.backwardIterator();
        List<Integer> readValues = Stream.generate(backwardIterator::next)
                                        .limit(LIST_SIZE)
                                        .collect(Collectors.toList());
        Collections.reverse(readValues);
        assertIterableEquals(values, readValues);
    }

    @Test
    void testBackwardIteratorLoops() {
        final int value = 1;
        list.add(value - 1);
        list.add(value);
        Iterator<Integer> backwardIterator = list.backwardIterator();
        backwardIterator.next();
        backwardIterator.next();
        assertEquals(value, backwardIterator.next());
    }

    @Test
    void testBackwardIteratorThrows() {
        Iterator<Integer> backwardIterator = list.backwardIterator();
        assertThrows(NoSuchElementException.class, backwardIterator::next);
    }
}

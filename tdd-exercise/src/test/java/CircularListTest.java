import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tdd.CircularList;
import tdd.CircularListImpl;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final Optional<Integer> EMPTY = Optional.empty();
    private CircularList list;

    @BeforeEach
    void beforeEach() {
        list = new CircularListImpl();
    }

    @Test
    void testListIsEmpty() {
        assertEquals(true, list.isEmpty());
    }

    @Test
    void testListNoLongerEmptyAfterAdd() {
        list.add(0);
        assertEquals(false, list.isEmpty());
    }
    
    @Test
    void testListIsCorrectSize() {
        final int numOfAdds = 10;
        for (int i = 0; i < numOfAdds; i++) {
            list.add(0);
        }
        assertEquals(numOfAdds, list.size());
    }

    @Test
    void testNextIsEmptyOnEmptyEmptyList() {
        assertEquals(EMPTY, list.next());
    }
   
    @Test
    void testNextElementIsCorrect() {
        final int value = 0;
        list.add(value);
        assertEquals(value, list.next().get());
    }

    @Test
    void testNextReturnsAllElementInCorrectOrder() {
        final List<Integer> values = List.of(1,2,3);
        for (Integer value : values) {
            list.add(value);
        }
        final List<Integer> readValues = new ArrayList<>(values.size());
        for (int i = 0; i < list.size(); i++) {
            readValues.add(list.next().get());
        }
        assertIterableEquals(values, readValues);
    }

    @Test
    void testNextLoopsBack() {
        final int value = 0;
        list.add(value);
        list.add(value + 1);
        list.next();
        list.next();
        assertEquals(value, list.next().get());
    }

    @Test
    void testPreviousIsEmptyOnEmptyEmptyList() {
        assertEquals(EMPTY, list.previous());
    }

    @Test
    void testPreviousElementIsCorrect() {
        final int value = 0;
        list.add(value);
        assertEquals(value, list.previous().get());
    }

    @Test
    void testPreviousReturnsAllElementInOppositeOrder() {
        final List<Integer> values = List.of(1,2,3);
        for (Integer value : values) {
            list.add(value);
        }
        final List<Integer> readValues = new ArrayList<>(values.size());
        for (int i = 0; i < list.size(); i++) {
            readValues.add(list.previous().get());
        }
        Collections.reverse(readValues);
        assertIterableEquals(values, readValues);
    }

    @Test
    void testPreviousLoopsBack() {
        final int value = 1;
        list.add(value - 1);
        list.add(value);
        list.previous();
        list.previous();
        assertEquals(value, list.previous().get());
    }

    @Test
    void testReset() {
        final int firstVal = 0;
        final int otherVals = 1;
        final int additions = 10;
        list.add(firstVal);
        for (int i = 0; i < additions; i++) {
            list.add(otherVals);
        }
        list.next();
        list.next();
        list.reset();
        assertEquals(firstVal, list.next().get());
    }

    @Test
    void testSize() {
        list.add(0);
        assertEquals(1, list.size());
    }
}

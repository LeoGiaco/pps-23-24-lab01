import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtered_tdd.CircularList;
import filtered_tdd.CircularListImpl;

public class CircularListWithFilteredSearch {
    
    CircularList list;
    static final Optional<Integer> EMPTY = Optional.empty();
    static final int NUM_OF_VALUES = 10;

    void generateValues(final int numOfValues) {
        for (int value = 0; value < numOfValues; value++) {
            list.add(value);
        }
    }

    @BeforeEach
    void testBeforeEach() {
        list = new CircularListImpl();
    }

    @Test
    void testFailedSearchReturnsNothing() {
        assertEquals(EMPTY, list.filteredNext(val -> val == 0));
    }

    @Test
    void testSuccessfulSearchReturnsValue() {
        int searchedValue = 4;
        generateValues(NUM_OF_VALUES);
        assertEquals(searchedValue, list.filteredNext(value -> value == searchedValue).get());
    }

    @Test
    void testFilteredSearchLoops() {
        Predicate<Integer> onlyEven = value -> value % 2 == 0;
        generateValues(NUM_OF_VALUES);
        for (int i = 0; i < NUM_OF_VALUES / 2; i++) {
            list.filteredNext(onlyEven);
        }
        assertEquals(0, list.filteredNext(onlyEven).get());
    }
}

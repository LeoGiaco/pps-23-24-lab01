package filtered_tdd;

import java.util.Optional;
import java.util.function.Predicate;

public interface CircularList extends iterator_tdd.CircularList {
    Optional<Integer> filteredNext(Predicate<Integer> condition);
}

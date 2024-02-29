package filtered_tdd;

import java.util.Optional;
import java.util.function.Predicate;

public class CircularListImpl extends iterator_tdd.CircularListImpl implements CircularList {

    int currentPosition = 0;

    @Override
    public Optional<Integer> filteredNext(Predicate<Integer> condition) {
        if (this.size() == 0) {
            return Optional.empty();
        }
        int position = this.currentPosition;
        do {
            int element = this.list.get(position);
            if (condition.test(element)) {
                this.currentPosition = (position + 1) % this.size();
                return Optional.of(element);
            }
            position = (position + 1) % this.size();
        } while (position != currentPosition);
        return Optional.empty();
    }
}

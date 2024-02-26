package tdd;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

public class CircularListImpl implements CircularList {
    private Integer index;
    private List<Integer> list;

    public CircularListImpl() {
        this.index = 0;
        this.list = new LinkedList<>();
        list.iterator();
    }

    @Override
    public void add(int element) {
        this.list.add(element);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if (this.size() > 0) {
            var value = this.list.get(this.index);
            this.index = (this.index + 1) % this.size();
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> previous() {
        if (this.size() > 0) {
            this.index--;
            if (this.index < 0) {
                this.index = this.size() - 1;
            }
            return Optional.of(this.list.get(this.index));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void reset() {
        this.index = 0;
    }

    @Override
    public int size() {
        return this.list.size();
    }
    
}

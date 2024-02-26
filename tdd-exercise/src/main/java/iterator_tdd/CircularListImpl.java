package iterator_tdd;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularListImpl implements CircularList {

    private List<Integer> list;

    public CircularListImpl() {
        list = new LinkedList<>();
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<Integer> forwardIterator() {
        return new Iterator<Integer>() {
            
            private int index = 0;

            @Override
            public boolean hasNext() {
                return list.size() > 0;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("List is empty.");
                }
                Integer value = list.get(this.index);
                this.index = (this.index + 1) % list.size();
                return value;
            }
        };
    }

    @Override
    public Iterator<Integer> backwardIterator() {
        return new Iterator<Integer>() {
            
            private int index = list.size() - 1;

            @Override
            public boolean hasNext() {
                return list.size() > 0;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("List is empty.");
                }
                Integer value = list.get(this.index--);
                if (this.index < 0) {
                    this.index = list.size() - 1;
                }
                return value;
            }
        };    }
    
}

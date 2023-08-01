import java.util.Iterator;
import java.util.NoSuchElementException;

public class EVL<T> implements Iterable<T> {

    private ListElement first = null;
    private int size = 0;

    private class ListElement {

        T data;
        ListElement next = null;

        ListElement(T data) {
            this.data = data;
        }
    }

    public T getFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.data;

    }

    public void push(T e) {

        if (null == e) {
            throw new IllegalStateException();
        }
        ListElement element = new ListElement(e);
        if (isEmpty()) {
            first = element;
        } else {
            ListElement tmp = first;
            while (null != tmp.next) {
                tmp = tmp.next;
            }
            tmp.next = element;
        }
        size++;
    }

    private T popFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedElement;
        if (1 == size) {
            removedElement = first.data;
            first = null;
        } else {
            removedElement = getFirst();
            first = first.next;
        }
        size--;
        return removedElement;
    }

    public T pop() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedElement;
        if (1 == size) {
            removedElement = first.data;
            first = null;
        } else {
            ListElement tmp = first;
            while (null != tmp.next.next) {
                tmp = tmp.next;
            }
            removedElement = tmp.next.data;
            tmp.next = null;
        }
        size--;
        return removedElement;
    }

    public T search(T e) {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        ListElement tmp = first;
        while (null != tmp) {
            if (tmp.data.equals(e)) {
                return e;
            } else {
                tmp = tmp.next;
            }
        }
        return null;
    }

    public void flush() {
        for (int i = size; 0 < size; i--) {
            pop();
        }
    }

    public T searchAndDelete(T e) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (first.data.equals(e)) {
            return popFirst();
        }

        ListElement tmp = first;
        while (tmp.next != null) {
            if (tmp.next.data.equals(e)) {
                T removedElement = tmp.next.data;
                tmp.next = tmp.next.next;
                size--;
                return removedElement;
            } else {
                tmp = tmp.next;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new EVLIterator();
    }

    private class EVLIterator implements Iterator<T> {

        ListElement current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T value = current.data;
            current = current.next;
            return value;
        }
    }

    @Override
    public String toString() {
        return toString(first);
    }

    private String toString(ListElement current) {

        if (isEmpty()) {
            return "Empty!";
        } else if (null == current.next) {
            return current.data.toString();
        } else {
            return current.data + " -> " + toString(current.next);
        }
    }
}

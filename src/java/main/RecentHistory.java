package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class RecentHistory<T> {
    // Usage of Queue as suggested by ChatGPT 3.5 by OpenAI on 3/5/24
    private final Queue<T> data = new LinkedList<>();

    private final int maxLength;

    public RecentHistory(int maxLength) {
        this.maxLength = maxLength;
    }

    public void add(T item) {
        if (data.size() >= maxLength) {
            data.poll();
        }

        data.add(item);
    }

    public void clear() {
        data.clear();
    }

    /** Gets the last item in the history, removes it if remove is true.
     * Will not modify the history if remove is false.
     * @param remove Should we remove the last element?
     * @return The last element, null if the history is empty.
     */
    public T getLast(boolean remove) {
        if (remove) {
            return data.poll();
        }

        return data.peek();
    }


    /** Gets and removes the last element of the history. Equivalent to getLast(true)
     * @return Returns the last element of the history
     */
    public T getLast() {
        return getLast(true);
    }

    public Stream<T> stream() {
        return data.stream();
    }

    public T[] toArray(T[] a) {
        return data.toArray(a);
    }

    public Object[] toArray() {
        return data.toArray();
    }
}

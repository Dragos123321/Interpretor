package Model.Utils;

public class Pair<T, E> {
    private final T first;
    private final E second;

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    T getFirst() {
        return first;
    }

    E getSecond() {
        return second;
    }
}

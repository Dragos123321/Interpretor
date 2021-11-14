package Model.Utils;

public class Tuple<T, E> {
    private final T first;
    private final E second;

    public Tuple(T first, E second) {
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

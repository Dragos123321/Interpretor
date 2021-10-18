package Model.Adt;

public interface IStack<T> {
    T pop();

    void push(T v);

    boolean isEmpty();

    String toString();
}


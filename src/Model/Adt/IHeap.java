package Model.Adt;

public interface IHeap<T> {
    int add(T value);

    T update(Integer key, T newValue);

    T remove(Integer key);

    T lookup(Integer key);

    boolean isDefined(Integer key);

    String toString();
}

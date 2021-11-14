package Model.Adt;

import java.util.Map;

public interface IHeap<T> {
    int add(T value);

    T update(Integer key, T newValue);

    T remove(Integer key);

    T lookup(Integer key);

    boolean isDefined(Integer key);

    void setContent(Map<Integer, T> value);

    Map<Integer, T> getContent();

    String toString();
}

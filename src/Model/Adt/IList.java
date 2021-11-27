package Model.Adt;

import java.util.List;

public interface IList<T> {
    void add(T v);
    T pop();
    String toString();
    boolean empty();
    T getFirstElement();
    T getLastElement();
    List<T> getInner();
    void setList(List<T> newList);
    void clear();
}

package Model.Adt;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDict<T1, T2> {
    void add(T1 v1, T2 v2);

    void update(T1 v1, T2 v2);

    void remove(T1 v1);

    T2 lookup(T1 id);

    boolean isDefined(T1 id);

    Map<T1, T2> getContent();

    String toString();

    IDict<T1, T2> deepCopy();

    Set<Map.Entry<T1, T2>> getAll();
}

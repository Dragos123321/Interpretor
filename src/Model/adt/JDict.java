package Model.adt;

import java.util.HashMap;
import java.util.Map;

public class JDict<T1, T2> implements IDict<T1, T2> {
    Map<T1, T2> dictionary;

    public JDict() {
        dictionary = new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        if (dictionary.containsKey(v1)) {
            dictionary.put(v1, v2);
        }
    }

    @Override
    public T2 lookup(T1 id) {
        return dictionary.get(id);
    }

    @Override
    public boolean isDefined(T1 id) {
        return dictionary.containsKey(id);
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }
}

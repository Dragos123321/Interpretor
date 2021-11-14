package Model.Adt;

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
    public void remove(T1 v1) {
        dictionary.remove(v1);
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
    public Map<T1, T2> getContent() {
        return dictionary;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        for (T1 key : dictionary.keySet()) {
            strBuilder.append(key.toString()).append(" -> ").append(dictionary.get(key).toString());
            strBuilder.append("\n");
        }

        return strBuilder.toString();
    }
}

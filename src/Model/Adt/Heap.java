package Model.Adt;

import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Heap<T> implements IHeap<T> {
    Map<Integer, T> heapTable;
    private int freeAddress = 1;

    public Heap() {
        heapTable = new HashMap<Integer, T>();
    }

    @Override
    public int add(T value) {
        heapTable.put(freeAddress++, value);
        return freeAddress - 1;
    }

    @Override
    public T update(Integer key, T value) {
        if (heapTable.containsKey(key)) {
            return heapTable.put(key, value);
        }
        return null;
    }

    @Override
    public T remove(Integer key) {
        return heapTable.remove(key);
    }

    @Override
    public T lookup(Integer key) {
        return heapTable.get(key);
    }

    @Override
    public boolean isDefined(Integer key) {
        return heapTable.containsKey(key);
    }

    @Override
    public void setContent(Map<Integer, T> value) {
        this.heapTable = value;
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.heapTable;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        for (Integer key : heapTable.keySet()) {
            strBuilder.append(Integer.toString(key)).append(" -> ").append(heapTable.get(key).toString());
            strBuilder.append("\n");
        }

        return strBuilder.toString();
    }
}

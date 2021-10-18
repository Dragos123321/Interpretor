package Model.Adt;

import java.util.Vector;

public class JList<T> implements IList<T> {
    Vector<T> list;

    public JList() {
        list = new Vector<T>();
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public T pop() {
        return list.remove(list.size() - 1);
    }

    public T getFirstElement() {
        return this.list.get(0);
    }

    @Override
    public boolean empty() {
        return this.list.isEmpty();
    }

    @Override
    public void clear(){
        this.list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

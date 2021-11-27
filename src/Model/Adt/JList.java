package Model.Adt;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class JList<T> implements IList<T> {
    List<T> list;

    public JList() {
        list = new ArrayList<>();
    }

    @Override
    public List<T> getInner() {
        return list;
    }

    @Override
    public void setList(List<T> newList) {
        this.list = newList;
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public T pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public T getFirstElement() {
        return this.list.get(0);
    }

    @Override
    public T getLastElement() {
        return this.list.get(this.list.size() - 1);
    }

    @Override
    public boolean empty() {
        return this.list.isEmpty();
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        for (T el : list) {
            strBuilder.append(el.toString());
            strBuilder.append("\n");
        }

        return strBuilder.toString();
    }
}

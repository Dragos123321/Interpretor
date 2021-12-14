package Model.Adt;

import Model.Statements.IStmt;

import java.util.*;
import java.util.stream.Collectors;

public class JStack<T> implements IStack<T> {
    Stack<T> stack;

    public JStack() {
        stack = new Stack<T>();
    }

    public JStack(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        List<String> strList = new ArrayList<String>();

        for (T el : stack) {
            strList.add(el.toString());
        }

        ListIterator<String> iterator = strList.listIterator(strList.size());

        StringBuilder strBuilder = new StringBuilder();

        while (iterator.hasPrevious()) {
            strBuilder.append(iterator.previous());
            strBuilder.append("\n");
        }

        return strBuilder.toString();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(stack);
    }
}

package Model.adt;

import java.util.Stack;
import java.util.Vector;

public class JStack<T> implements IStack<T> {
    Stack<T> stack;

    public JStack() {
        stack = new Stack<T>();
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
        StringBuilder str = new StringBuilder();
        str.append("[ ");

        for (T el : stack) {
            str.append(el.toString());
            str.append(" ");
        }

        str.append("]");

        return str.toString();
    }
}

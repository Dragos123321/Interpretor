package Model.Adt;

import Model.Statements.IStmt;

import java.util.List;

public interface IStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    String toString();
    List<T> getAll();
}


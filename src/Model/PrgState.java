package Model;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Adt.IList;
import Model.Adt.IStack;
import Model.Exceptions.ControllerError;
import Model.Statements.IStmt;
import Model.Types.IType;
import Model.Value.IValue;

import java.io.BufferedReader;

public class PrgState {

    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IList<IValue> out;
    IDict<String, BufferedReader> fileTable;
    IHeap<IValue> heap;
    IStmt originalProgram; //optional field, but good to have
    private static int current_id = 1;
    private int thread_id;

    public PrgState(IStack<IStmt> stack, IDict<String, IValue> symTable, IList<IValue> out,
                    IDict<String, BufferedReader> fileTable, IHeap<IValue> heap,
                    IStmt originalProgram) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.exeStack.push(originalProgram);
        this.originalProgram = originalProgram;
        this.heap = heap;
        setID();
    }

    public int getID() {
        return thread_id;
    }

    synchronized public void setID() {
        this.thread_id = current_id;
        current_id++;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IList<IValue> getOutput() {
        return out;
    }

    public IDict<String, IValue> getSymTable() {
        return this.symTable;
    }

    public IDict<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IHeap<IValue> getHeap() {
        return this.heap;
    }

    public void setExeStack(IStack<IStmt> stack) {
        this.exeStack = stack;
    }

    public void setSymTable(IDict<String, IValue> table) {
        this.symTable = table;
    }

    public void setOutput(IList<IValue> output) {
        this.out = output;
    }

    public void setFileTable(IDict<String, BufferedReader> table) {
        this.fileTable = table;
    }

    public void setHeap(IHeap<IValue> heap) {
        this.heap = heap;
    }

    public String toString() {
        return  "ID: " + thread_id + "\n" +
                "Stack: " + exeStack.toString() + "\n" +
                "SymTable: " + symTable.toString() + "\n" +
                "Out: " + out.toString() + "\n" +
                "FileTable: " + fileTable.toString() + "\n" +
                "Heap: " + heap.toString();
    }

    public boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws ControllerError {
        if (exeStack.isEmpty())
            throw new ControllerError("Stack is empty.");
        IStmt crtStmt = exeStack.pop();

        try {
            return crtStmt.execute(this);
        } catch (Exception err) {
            throw new ControllerError(err.getMessage());
        }
    }
}
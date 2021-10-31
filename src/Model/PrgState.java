package Model;
import Model.Adt.IDict;
import Model.Adt.IList;
import Model.Adt.IStack;
import Model.Statements.IStmt;
import Model.Value.IValue;

import java.io.BufferedReader;

public class PrgState {

    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IList<IValue> out;
    IDict<String, BufferedReader> fileTable;
    IStmt originalProgram; //optional field, but good to have

    public PrgState(IStack<IStmt> stack, IDict<String, IValue> symTable, IList<IValue> out,
                    IDict<String, BufferedReader> fileTable, IStmt originalProgram) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram;
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

    public String toString() {
        return  "Stack: " + exeStack.toString() + "\n" +
                "SymTable: " + symTable.toString() + "\n" +
                "Out: " + out.toString() + "\n" +
                "FileTable: " + fileTable.toString();
    }
}
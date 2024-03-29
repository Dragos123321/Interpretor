package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.*;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    private final IExp expression;
    private final String filename;

    public closeRFile(String filename, IExp expr) {
        this.filename = filename;
        this.expression = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        try {
            IValue exprValue = expression.eval(symTable, heap);

            IDict<String, BufferedReader> fileTable = state.getFileTable();

            if (exprValue.getType().equals(new StringType())) {
                StringValue string_expr = (StringValue) exprValue;
                String filepath = string_expr.getValue();

                BufferedReader reader = fileTable.lookup(filepath);

                if (reader != null) {
                    try {
                        reader.close();
                        fileTable.remove(filepath);
                    } catch (IOException err) {
                        throw new StmtError(err.getMessage());
                    }
                } else {
                    throw new FileNotOpenedError("File " + filepath + " is not open.");
                }
            }
            else {
                throw new StmtError("File name is not a string.");
            }
        }
        catch(ExpError err) {
            throw new StmtError(err.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "closeRFile(" + filename + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new closeRFile(filename, expression);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        IType type_exp = expression.typeCheck(typeEnv);
        if (type_exp.equals(new StringType())) {
            return typeEnv;
        } else {
            throw new TypeMismatch("The expression of READ is not of type string");
        }
    }
}

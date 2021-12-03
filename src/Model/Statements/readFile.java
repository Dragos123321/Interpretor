package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.*;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class readFile implements IStmt {
    private final String filename;
    private final IExp expression;
    private final String var_name;

    public readFile(String filename, IExp expr, String var_name) {
        this.filename = filename;
        this.expression = expr;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IDict<String, IValue> symTable = state.getSymTable();
        IDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heap = state.getHeap();

        IValue var = symTable.lookup(var_name);
        if (var == null) {
            throw new UndefinedVariable(var_name + " is not defined.");
        }

        if (!var.getType().equals(new IntType())) {
            throw new TypeMismatch(var_name + " is not an integer.");
        }

        IntValue ivar = (IntValue) var;

        try {
            IValue expr_value = this.expression.eval(symTable, heap);

            if (!expr_value.getType().equals(new StringType())) {
                throw new TypeMismatch("Expression does not evaluate to string.");
            }

            StringValue str_value = (StringValue)expr_value;

            String filePath = str_value.getValue();

            BufferedReader reader = fileTable.lookup(filePath);
            if (reader == null) {
                throw new FileNotOpenedError(filePath + " is not open for reading");
            }

            try {
                String read_value = reader.readLine();
                int value;
                if (Objects.equals(read_value, "")) {
                    value = 0;
                }
                else {
                    value = Integer.parseInt(read_value);
                }

                symTable.update(var_name, new IntValue(value));
            }
            catch (IOException err) {
                throw new StmtError(err.getMessage());
            }
        }
        catch (ExpError err) {
            throw new StmtError(err.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "readFile(" + filename + ", " + var_name + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new readFile(filename, expression, var_name);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        IType type_exp = expression.typeCheck(typeEnv);
        if (type_exp.equals(new StringType())) {
            return typeEnv;
        } else {
            throw new StmtError("The expression of WRITE is not of type string");
        }
    }
}

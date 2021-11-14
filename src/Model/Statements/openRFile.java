package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ExpError;
import Model.Exceptions.StmtError;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Types.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {
    private final IExp expression;
    private final String filename;

    public openRFile(String filename, IExp exp) {
        this.filename = filename;
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError {
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        try {
            IValue exprValue = expression.eval(symTable, heap);

            IDict<String, BufferedReader> fileTable = state.getFileTable();

            if (exprValue.getType().equals(new StringType())) {
                StringValue string_expr = (StringValue) exprValue;
                String filepath = string_expr.getValue();

                if (fileTable.lookup(filepath) == null) {
                    try {
                        FileReader in = new FileReader(filepath);
                        BufferedReader reader = new BufferedReader(in);
                        fileTable.add(filepath, reader);
                    } catch (FileNotFoundException err) {
                        throw new StmtError("File " + filepath + " not found.");
                    }
                } else {
                    throw new StmtError("File already exists.");
                }
            } else {
                throw new StmtError("File name is not a string.");
            }
        } catch (ExpError err) {
            throw new StmtError(err.getMessage());
        }
        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + filename + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new openRFile(filename, expression);
    }

}

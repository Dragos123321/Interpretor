package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.JDict;
import Model.Adt.JStack;
import Model.Exceptions.StmtError;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.IType;

public class ForkStatement implements IStmt {
    private IStmt statement;

    public ForkStatement(IStmt stmt) {
        this.statement = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError {
        return new PrgState(new JStack<>(), state.getSymTable().deepCopy(), state.getOutput(), state.getFileTable(), state.getHeap(),
                state.getTypeChecker().deepCopy(), this.statement);
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStatement(statement);
    }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception {
        statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
}

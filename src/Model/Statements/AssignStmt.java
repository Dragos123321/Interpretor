package Model.Statements;

import Model.Exceptions.StmtError;
import Model.PrgState;
import Model.Adt.IDict;
import Model.Exp.IExp;
import Model.Types.IType;
import Model.Value.IValue;

public class AssignStmt implements IStmt{

    String id;
    IExp expression;

    public AssignStmt(String id, IExp exp){
        this.id = id;
        this.expression = exp;
    }

    @Override
    public String toString(){
        return this.id + "=" + this.expression.toString();
    }

    public PrgState execute(PrgState state) throws StmtError {
        IDict<String, IValue> symTable = state.getSymTable();

        if (symTable.isDefined(id)) {
            try {
                IValue val = expression.eval(symTable);
                IType type = symTable.lookup(id).getType();
                if (val.getType().equals(type)) {
                    symTable.update(id, val);
                } else {
                    throw new StmtError("Type of expression and type of variable do not match.");
                }
            } catch (Exception err) {
                throw new StmtError(err.getMessage());
            }
        } else {
            throw new StmtError("Variable " + id.toString() + " is not defined.");
        }

        state.setSymTable(symTable);
        return state;
    }
}

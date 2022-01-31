package Model.Utils;

import Model.Statements.CompStmt;
import Model.Statements.IStmt;

import java.util.List;

public class StmtGenerator {
    public static IStmt generate(List<IStmt> statements) {
        if (statements.size() == 0) {
            return null;
        }
        if (statements.size() == 1) {
            return statements.get(0);
        }

        return new CompStmt(statements.get(0), generate(statements.subList(1, statements.size())));
    }
}

package Repo;
import Model.Adt.IList;
import Model.PrgState;

import java.io.IOException;

public interface IRepo {
    void addPrg(PrgState newPrg);
    IList<PrgState> getPrgList();
    void setPrgList(IList<PrgState> newPrgStates);
    void logPrgStateExec(PrgState state) throws IOException;
}

package Repo;
import Model.Adt.IList;
import Model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    void addPrg(PrgState newPrg);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newPrgStates);
    void logPrgStateExec(PrgState state) throws IOException;
}

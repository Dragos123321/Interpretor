package Repo;
import Model.PrgState;

import java.io.IOException;

public interface IRepo {
    void addPrg(PrgState newPrg);
    PrgState getCrtPrg();
    void logPrgStateExec() throws IOException;
}

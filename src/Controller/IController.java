package Controller;

import Model.PrgState;
import Repo.Repo;

public interface IController {
    void addProgram(PrgState newPrg);
    PrgState oneStep(PrgState state);
    void allStep();
}

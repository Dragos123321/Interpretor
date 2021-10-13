package Repo;

import Model.PrgState;
import Model.adt.JList;

public class Repo implements IRepo {
    JList<PrgState> mPrgStates;

    public Repo() {
        mPrgStates = new JList<PrgState>();
    }

    @Override
    public PrgState getCrtPrg() {
        return mPrgStates.pop();
    }

    @Override
    public void addPrg(PrgState newPrg) {
        mPrgStates.add(newPrg);
    }
}

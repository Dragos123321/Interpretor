package Repo;

import Model.Adt.IList;
import Model.PrgState;
import Model.Adt.JList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    List<PrgState> mPrgStates;
    String logFilePath;
    PrintWriter logFile;
    boolean firstTime;

    public Repo(PrgState prgState, String logFilePath) {
        mPrgStates = new ArrayList<PrgState>();
        addPrg(prgState);
        this.logFilePath = logFilePath;
        this.firstTime = true;
    }

    @Override
    public List<PrgState> getPrgList() {
        return mPrgStates;
    }

    @Override
    public void setPrgList(List<PrgState> newPrgStates) {
        mPrgStates = newPrgStates;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        mPrgStates.add(newPrg);
    }

    @Override
    public void logPrgStateExec(PrgState state) throws IOException {
        if (firstTime) {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            this.firstTime = false;
        } else {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        }

        logFile.println("-------------------------------------------------------");
        logFile.print("Thread id: ");
        logFile.println(state.getID());
        logFile.println("ExeStack:");
        logFile.println(state.getExeStack().toString());
        logFile.println("SymTable:");
        logFile.println(state.getSymTable().toString());
        logFile.println("Output:");
        logFile.println(state.getOutput().toString());
        logFile.println("FileTable:");
        logFile.println(state.getFileTable().toString());
        logFile.println("Heap:");
        logFile.println(state.getHeap().toString());
        logFile.println("-------------------------------------------------------");

        logFile.close();
    }
}

package Repo;

import Model.PrgState;
import Model.Adt.JList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repo implements IRepo {
    JList<PrgState> mPrgStates;
    String logFilePath;
    PrintWriter logFile;
    boolean firstTime;

    public Repo(PrgState prgState, String logFilePath) {
        mPrgStates = new JList<PrgState>();
        addPrg(prgState);
        this.logFilePath = logFilePath;
        this.firstTime = true;
    }

    @Override
    public PrgState getCrtPrg() {
        return mPrgStates.getLastElement();
    }

    @Override
    public void addPrg(PrgState newPrg) {
        mPrgStates.add(newPrg);
    }

    @Override
    public void logPrgStateExec() throws IOException {
        if (firstTime) {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            this.firstTime = false;
        } else {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        }

        logFile.println("ExeStack:");
        logFile.println(mPrgStates.getLastElement().getExeStack().toString());
        logFile.println("SymTable:");
        logFile.println(mPrgStates.getLastElement().getSymTable().toString());
        logFile.println("Output:");
        logFile.println(mPrgStates.getLastElement().getOutput().toString());
        logFile.println("FileTable:");
        logFile.println(mPrgStates.getLastElement().getFileTable().toString());
        logFile.println("Heap:");
        logFile.println(mPrgStates.getLastElement().getHeap().toString());

        logFile.close();
    }
}

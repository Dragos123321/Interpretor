package Repo;

import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

public class Repo implements IRepo {
    List<PrgState> mPrgStates;
    String logFilePath;
    PrintWriter logFile;
    boolean firstTime;

    public Repo(PrgState prgState, String logFilePath) {
        mPrgStates = new Vector<PrgState>();
        addPrg(prgState);
        this.logFilePath = logFilePath;
        this.firstTime = true;
    }

    @Override
    public List<PrgState> getPrgList() {
        return mPrgStates;
    }

    public PrgState returnByProgramID(int id) {
        for (var prg : mPrgStates) {
            if (prg.getID() == id) {
                return prg;
            }
        }
        return null;
    }

    @Override
    public synchronized void setPrgList(List<PrgState> newPrgStates) {
        mPrgStates = newPrgStates;
    }

    @Override
    public synchronized void addPrg(PrgState newPrg) {
        mPrgStates.add(newPrg);
    }

    @Override
    public synchronized void logPrgStateExec(PrgState state) throws IOException {
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

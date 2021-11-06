package View;

import Controller.Controller;
import Model.Adt.*;
import Model.Exp.ArithmeticExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repo.IRepo;
import Repo.Repo;

import java.io.BufferedReader;


public class Interpreter {

    public static void main(String[] args) {
        IStack<IStmt> exeStack1 = new JStack<IStmt>();
        IDict<String, IValue> symTable1 = new JDict<String, IValue>();
        IList<IValue> out1 = new JList<IValue>();
        IDict<String, BufferedReader> fileTable1 = new JDict<String, BufferedReader>();

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(exeStack1, symTable1, out1, fileTable1, ex1);
        IRepo repo1 = new Repo(prg1, "log1.txt");
        Controller cont1 = new Controller(repo1);

        IStack<IStmt> exeStack2 = new JStack<IStmt>();
        IDict<String, IValue> symTable2 = new JDict<String, IValue>();
        IList<IValue> out2 = new JList<IValue>();
        IDict<String, BufferedReader> fileTable2 = new JDict<String, BufferedReader>();
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithmeticExp('+', new ValueExp(new IntValue(2)), new ArithmeticExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithmeticExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(exeStack2, symTable2, out2, fileTable2, ex2);
        IRepo repo2 = new Repo(prg2, "log2.txt");
        Controller cont2 = new Controller(repo2);

        IStack<IStmt> exeStack3 = new JStack<IStmt>();
        IDict<String, IValue> symTable3 = new JDict<String, IValue>();
        IList<IValue> out3 = new JList<IValue>();
        IDict<String, BufferedReader> fileTable3 = new JDict<String, BufferedReader>();
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));
        PrgState prg3 = new PrgState(exeStack3, symTable3, out3, fileTable3, ex3);
        IRepo repo3 = new Repo(prg3, "log3.txt");
        Controller cont3 = new Controller(repo3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), cont1));
        menu.addCommand(new RunExample("2", ex2.toString(), cont2));
        menu.addCommand(new RunExample("3", ex3.toString(), cont3));

        menu.show();
    }
}

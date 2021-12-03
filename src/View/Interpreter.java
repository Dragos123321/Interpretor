package View;

import Controller.Controller;
import Model.Adt.*;
import Model.Exp.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.*;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repo.IRepo;
import Repo.Repo;

import java.io.BufferedReader;


public class Interpreter {

    public static void main(String[] args) {
        IStack<IStmt> exeStack1 = new JStack<>();
        IDict<String, IValue> symTable1 = new JDict<>();
        IList<IValue> out1 = new JList<>();
        IDict<String, BufferedReader> fileTable1 = new JDict<>();
        IDict<String, IType> typeChecker = new JDict<>();
        IHeap<IValue> heap1 = new Heap<IValue>();

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        try {
            ex1.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg1 = new PrgState(exeStack1, symTable1, out1, fileTable1, heap1, ex1);
        IRepo repo1 = new Repo(prg1, "log1.txt");
        Controller cont1 = new Controller(repo1);

        IStack<IStmt> exeStack2 = new JStack<>();
        IDict<String, IValue> symTable2 = new JDict<>();
        IList<IValue> out2 = new JList<>();
        IDict<String, BufferedReader> fileTable2 = new JDict<>();
        IHeap<IValue> heap2 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithmeticExp('+', new ValueExp(new IntValue(2)), new ArithmeticExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithmeticExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));
        try {
            ex2.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg2 = new PrgState(exeStack2, symTable2, out2, fileTable2, heap2, ex2);
        IRepo repo2 = new Repo(prg2, "log2.txt");
        Controller cont2 = new Controller(repo2);

        IStack<IStmt> exeStack3 = new JStack<>();
        IDict<String, IValue> symTable3 = new JDict<>();
        IList<IValue> out3 = new JList<>();
        IDict<String, BufferedReader> fileTable3 = new JDict<>();
        IHeap<IValue> heap3 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));
        try {
            ex3.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg3 = new PrgState(exeStack3, symTable3, out3, fileTable3, heap3, ex3);
        IRepo repo3 = new Repo(prg3, "log3.txt");
        Controller cont3 = new Controller(repo3);

        IStack<IStmt> exeStack4 = new JStack<>();
        IDict<String, IValue> symTable4 = new JDict<>();
        IList<IValue> out4 = new JList<>();
        IDict<String, BufferedReader> fileTable4 = new JDict<>();
        IHeap<IValue> heap4 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf",
                new ValueExp(new StringValue("test.in"))), new CompStmt(new openRFile("varf", new ValueExp(new StringValue("test.in"))),
                new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new readFile("varf",
                        new ValueExp(new StringValue("test.in")), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new readFile("varf", new ValueExp(new StringValue("test.in")), "varc"),
                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                        new closeRFile("varf", new ValueExp(new StringValue("test.in")))))))))));
        try {
            ex4.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg4 = new PrgState(exeStack4, symTable4, out4, fileTable4, heap4, ex4);
        IRepo repo4 = new Repo(prg4, "log4.txt");
        Controller cont4 = new Controller(repo4);

        IStack<IStmt> exeStack5 = new JStack<>();
        IDict<String, IValue> symTable5 = new JDict<>();
        IList<IValue> out5 = new JList<>();
        IDict<String, BufferedReader> fileTable5 = new JDict<>();
        IHeap<IValue> heap5 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v",
                new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        try {
            ex5.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg5 = new PrgState(exeStack5, symTable5, out5, fileTable5, heap5, ex5);
        IRepo repo5 = new Repo(prg5, "log5.txt");
        Controller cont5 = new Controller(repo5);

        IStack<IStmt> exeStack6 = new JStack<>();
        IDict<String, IValue> symTable6 = new JDict<>();
        IList<IValue> out6 = new JList<>();
        IDict<String, BufferedReader> fileTable6 = new JDict<>();
        IHeap<IValue> heap6 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v",
                new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                new PrintStmt(new ArithmeticExp('+', new HeapReadingExp(new HeapReadingExp(new VarExp("a"))),
                                        new ValueExp(new IntValue(5)))))))));
        try {
            ex6.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg6 = new PrgState(exeStack6, symTable6, out6, fileTable6, heap6, ex6);
        IRepo repo6 = new Repo(prg6, "log6.txt");
        Controller cont6 = new Controller(repo6);

        IStack<IStmt> exeStack7 = new JStack<>();
        IDict<String, IValue> symTable7 = new JDict<>();
        IList<IValue> out7 = new JList<>();
        IDict<String, BufferedReader> fileTable7 = new JDict<>();
        IHeap<IValue> heap7 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v",
                new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithmeticExp('+',
                        new HeapReadingExp(new VarExp("v")),
                        new ValueExp(new IntValue(5))))))));
        try {
            ex7.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg7 = new PrgState(exeStack7, symTable7, out7, fileTable7, heap7, ex7);
        IRepo repo7 = new Repo(prg7, "log7.txt");
        Controller cont7 = new Controller(repo7);

        IStack<IStmt> exeStack8 = new JStack<>();
        IDict<String, IValue> symTable8 = new JDict<>();
        IList<IValue> out8 = new JList<>();
        IDict<String, BufferedReader> fileTable8 = new JDict<>();
        IHeap<IValue> heap8 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithmeticExp('-',
                                new VarExp("v"), new ValueExp(new IntValue(1)))))), new PrintStmt(new VarExp("v")))));
        try {
            ex8.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg8 = new PrgState(exeStack8, symTable8, out8, fileTable8, heap8, ex8);
        IRepo repo8 = new Repo(prg8, "log8.txt");
        Controller cont8 = new Controller(repo8);

        IStack<IStmt> exeStack9 = new JStack<>();
        IDict<String, IValue> symTable9 = new JDict<>();
        IList<IValue> out9 = new JList<>();
        IDict<String, BufferedReader> fileTable9 = new JDict<>();
        IHeap<IValue> heap9 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v",
                new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))), new CompStmt(
                                new HeapAllocStmt("v", new ValueExp(new IntValue(30))),
                                new CompStmt(new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))),
                                        new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                                new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))))))))))));
        try {
            ex9.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg9 = new PrgState(exeStack9, symTable9, out9, fileTable9, heap9, ex9);
        IRepo repo9 = new Repo(prg9, "log9.txt");
        Controller cont9 = new Controller(repo9);

        IStack<IStmt> exeStack10 = new JStack<>();
        IDict<String, IValue> symTable10 = new JDict<>();
        IList<IValue> out10 = new JList<>();
        IDict<String, BufferedReader> fileTable10 = new JDict<>();
        IHeap<IValue> heap10 = new Heap<IValue>();
        typeChecker = new JDict<>();
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
                        new CompStmt(new ForkStatement(new CompStmt(new HeapWriteStmt("a", new ValueExp(new IntValue(30))),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a"))))))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));
        try {
            ex10.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        PrgState prg10 = new PrgState(exeStack10, symTable10, out10, fileTable10, heap10, ex10);
        IRepo repo10 = new Repo(prg10, "log10.txt");
        Controller cont10 = new Controller(repo10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), cont1));
        menu.addCommand(new RunExample("2", ex2.toString(), cont2));
        menu.addCommand(new RunExample("3", ex3.toString(), cont3));
        menu.addCommand(new RunExample("4", ex4.toString(), cont4));
        menu.addCommand(new RunExample("5", ex5.toString(), cont5));
        menu.addCommand(new RunExample("6", ex6.toString(), cont6));
        menu.addCommand(new RunExample("7", ex7.toString(), cont7));
        menu.addCommand(new RunExample("8", ex8.toString(), cont8));
        menu.addCommand(new RunExample("9", ex9.toString(), cont9));
        menu.addCommand(new RunExample("10", ex10.toString(), cont10));

        menu.show();
    }
}

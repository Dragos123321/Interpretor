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

import java.util.Scanner;

public class Console {
    private final Controller controller;

    public Console(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        IStack<IStmt> exeStack = new JStack<IStmt>();
        IDict<String, IValue> symTable = new JDict<String, IValue>();
        IList<IValue> out = new JList<IValue>();
        PrgState myPrgState = new PrgState(exeStack, symTable, out, null);

        // ex 1:  int v; v = 2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        //ex 2: a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithmeticExp('+', new ValueExp(new IntValue(2)), new ArithmeticExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithmeticExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        // ex 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));

        boolean done = false;

        Console.showMenu();

        while (!done) {
            String command = get_command();

            switch (command) {
                case "1":
                    exeStack = new JStack<IStmt>();
                    symTable = new JDict<String, IValue>();
                    out = new JList<IValue>();
                    myPrgState = new PrgState(exeStack, symTable, out, null);
                    exeStack.push(ex1);
                    myPrgState.setExeStack(exeStack);
                    this.controller.addProgram(myPrgState);
                    this.controller.allStep();
                    break;

                case "2":
                    exeStack = new JStack<IStmt>();
                    symTable = new JDict<String, IValue>();
                    out = new JList<IValue>();
                    myPrgState = new PrgState(exeStack, symTable, out, null);
                    exeStack.push(ex2);
                    myPrgState.setExeStack(exeStack);
                    this.controller.addProgram(myPrgState);
                    this.controller.allStep();
                    break;

                case "3":
                    exeStack = new JStack<IStmt>();
                    symTable = new JDict<String, IValue>();
                    out = new JList<IValue>();
                    myPrgState = new PrgState(exeStack, symTable, out, null);
                    exeStack.push(ex3);
                    myPrgState.setExeStack(exeStack);
                    this.controller.addProgram(myPrgState);
                    this.controller.allStep();
                    break;

                case "x":
                    done = true;
                    break;

                case "4":
            }
        }
    }

    private static void showMenu() {
        System.out.println("1 - run first example");
        System.out.println("2 - run first example");
        System.out.println("3 - run first example");
        System.out.println("4 - run first example");
        System.out.println("x - exit");
    }

    private String get_command() {
        System.out.print("Insert command: ");

        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}

package View;
import Model.Exp.ArithmeticExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repo.Repo;
import Controller.Controller;
import Model.Adt.IDict;
import Model.Adt.IStack;
import Model.Adt.IList;
import Model.Adt.JList;
import Model.Adt.JDict;
import Model.Adt.JStack;
import Model.PrgState;


public class Main {

    static Repo myRepository = new Repo();
    static Controller myController = new Controller(myRepository);

    public static void main(String[] args) {

        IStmt originalProgram = new IfStmt(new ValueExp(new IntValue(10)),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new PrintStmt(new ArithmeticExp('/',
                                new VarExp("v"), new ValueExp(new IntValue(3))))),
                new PrintStmt(new ValueExp(new IntValue(100))));
        IStack<IStmt> exeStack = new JStack<IStmt>();
        IDict<String, IValue> symTable = new JDict<String, IValue>();
        IList<IValue> out = new JList<IValue>();
        PrgState myPrgState = new PrgState(exeStack, symTable, out, originalProgram);

 // ex 1:  int v; v = 2; Print(v)
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        exeStack.push(ex1);

        //ex 2: a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithmeticExp('+',new ValueExp(new IntValue(2)),new ArithmeticExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(
                                new AssignStmt("b",new ArithmeticExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        //exeStack.push(ex2);
        // ex 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                        new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                VarExp("v"))))));
        //exeStack.push(ex3);

        myPrgState.setExeStack(exeStack);
        myController.addProgram(myPrgState);
        myController.allStep();
    }
}

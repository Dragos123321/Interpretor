package View;
import Model.exp.ArithExp;
import Model.exp.ValueExp;
import Model.exp.VarExp;
import Model.stmt.*;
import Model.types.BoolType;
import Model.types.IntType;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;
import Repo.Repo;
import Controller.Controller;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.adt.IList;
import Model.adt.JList;
import Model.adt.JDict;
import Model.adt.JStack;
import Model.PrgState;


public class Main {

    static Repo myRepository = new Repo();
    static Controller myController = new Controller(myRepository);

    public static void main(String[] args) {

        IStmt originalProgram = new IfStmt(new ValueExp(new IntValue(10)),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new PrintStmt(new ArithExp('/',
                                new VarExp("v"), new ValueExp(new IntValue(3))))),
                new PrintStmt(new ValueExp(new IntValue(100))));
        IStack<IStmt> exeStack = new JStack<IStmt>();
        //exeStack.push(originalProgram);
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
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(
                                new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
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

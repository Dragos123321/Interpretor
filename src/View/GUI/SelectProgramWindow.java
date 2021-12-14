package View.GUI;

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
import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectProgramWindow implements Initializable {
    private List<IStmt> programs;
    private MainWindow mainWindow;

    @FXML
    private ListView<String> programsListView;

    @FXML
    private Button selectButton;

    public void setSelectProgramWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private void createProgramsList() {
        IDict<String, IType> typeChecker = new JDict<>();
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        try {
            ex1.typecheck(typeChecker);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }

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
        programs = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createProgramsList();
        programsListView.setItems(FXCollections.observableArrayList(programs.stream().map(IStmt::toString).collect(Collectors.toList())));

        selectButton.setOnAction(actionEvent -> {
            int index = programsListView.getSelectionModel().getSelectedIndex();

            if (index < 0 || index >= 10) {
                return;
            }

            IStack<IStmt> exeStack = new JStack<>();
            IDict<String, IValue> symTable = new JDict<>();
            IList<IValue> out = new JList<>();
            IDict<String, BufferedReader> fileTable = new JDict<>();
            IHeap<IValue> heap = new Heap<IValue>();
            PrgState initialProgram = new PrgState(exeStack, symTable, out, fileTable, heap, programs.get(index));
            IRepo repo = new Repo(initialProgram, "log" + index + ".txt");
            Controller controller = new Controller(repo);

            mainWindow.setController(controller);
        });
    }
}

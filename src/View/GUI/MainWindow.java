package View.GUI;

import Controller.Controller;
import Model.Adt.*;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Statements.IfStmt;
import Model.Value.IValue;
import Model.Value.IntValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainWindow implements Initializable {
    private Controller controller;

    @FXML
    private ListView<Integer> programStateListView;
    @FXML
    private ListView<String> exeStackListView;
    @FXML
    private ListView<IValue> outListView;

    @FXML
    private TableView<Map.Entry<String, String>> symTableView;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symTableKeyColumn;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symTableValueColumn;

    @FXML
    private TableView<Map.Entry<Integer, String>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> heapTableKeyColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> heapTableValueColumn;

    @FXML
    private TableView<Map.Entry<String, String>> fileTableView;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> fileTableKeyColumn;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> fileTableValueColumn;

    @FXML
    private TextField nrProgramStatesTextField;

    @FXML
    private Button oneStepButton;

    private void populateProgramStateListView() {
        List<PrgState> programs = controller.getRepo().getPrgList();
        programStateListView.setItems(FXCollections.observableList(programs.stream().map(PrgState::getID).collect(Collectors.toList())));
        programStateListView.refresh();

        nrProgramStatesTextField.setText("The number of programs: " + programs.size());
    }

    private void populateExeStackListView(PrgState state) {
        IStack<IStmt> exeStack = new JStack<>();
        if (state != null) {
            exeStack = state.getExeStack();
        }

        List<String> exeStackList = new ArrayList<>();
        for (IStmt statement: exeStack.getAll()) {
            exeStackList.add(statement.toString());
        }

        exeStackListView.setItems(FXCollections.observableList(exeStackList));
        exeStackListView.refresh();
    }

    private void populateSymTableView(PrgState state) {
        IDict<String, IValue> symTable = new JDict<>();
        if (state != null) {
            symTable = state.getSymTable();
        }

        List<Map.Entry<String, String>> symTableList = new ArrayList<>();
        for (var entry : symTable.getAll()) {
            symTableList.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().toString()));
        }

        symTableView.setItems(FXCollections.observableList(symTableList));
        symTableView.refresh();
    }

    private void populateOutput(PrgState state) {
        IList<IValue> output = new JList<>();
        if (state != null) {
            output = state.getOutput();
        }

        outListView.setItems(FXCollections.observableList(output.getRawList()));
        outListView.refresh();
    }

    private void populateHeapTableView(PrgState state) {
        IHeap<IValue> heap = new Heap<>();
        if (state != null) {
            heap = state.getHeap();
        }

        List<Map.Entry<Integer, String>> heapList = new ArrayList<>();
        for (var entry : heap.getAll()) {
            heapList.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().toString()));
        }

        heapTableView.setItems(FXCollections.observableList(heapList));
        heapTableView.refresh();
    }

    private void populateFileTableView(PrgState state) {
        IDict<String, BufferedReader> fileTable = new JDict<>();
        if (state != null) {
            fileTable = state.getFileTable();
        }

        List<Map.Entry<String, String>> fileList = new ArrayList<>();
        for (var entry : fileTable.getAll()) {
            fileList.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().toString()));
        }

        fileTableView.setItems(FXCollections.observableList(fileList));
        fileTableView.refresh();
    }

    private PrgState getCurrentPrgState() {
        if (programStateListView.getSelectionModel().getSelectedIndex() == -1)
            return null;
        int current_id = programStateListView.getSelectionModel().getSelectedItem();
        if (controller.getRepo().returnByProgramID(current_id) == null && !controller.getRepo().getPrgList().isEmpty()) {
            programStateListView.getSelectionModel().select(1);
            current_id = programStateListView.getSelectionModel().getSelectedItem();
        }
        return controller.getRepo().returnByProgramID(current_id);
    }

    private void changePrgState(PrgState newState) {
        populateProgramStateListView();
        populateExeStackListView(newState);
        populateFileTableView(newState);
        populateOutput(newState);
        populateHeapTableView(newState);
        populateSymTableView(newState);
    }

    private void oneStep() {
        if (getCurrentPrgState() == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "No program selected", ButtonType.OK);
            error.showAndWait();
            return;
        }

        controller.executeOneStep();
        changePrgState(getCurrentPrgState());
    }

    public void setController(Controller newController) {
        this.controller = newController;
        populateProgramStateListView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        symTableKeyColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        symTableValueColumn.setCellValueFactory(p -> new SimpleStringProperty((p.getValue().getValue().toString())));

        heapTableKeyColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapTableValueColumn.setCellValueFactory(p -> new SimpleStringProperty((p.getValue().getValue().toString())));

        fileTableKeyColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        fileTableValueColumn.setCellValueFactory(p -> new SimpleStringProperty((p.getValue().getValue().toString())));

        programStateListView.setOnMouseClicked(mouseEvent -> {
            changePrgState(getCurrentPrgState());
        });

        oneStepButton.setOnAction(actionEvent -> {
            oneStep();
        });
    }
}

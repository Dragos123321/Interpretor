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
import View.GUI.MainWindow;
import View.GUI.SelectProgramWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;


public class Interpreter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader();

        mainLoader.setLocation(getClass().getResource("mainWindow.fxml"));
        Parent mainWindow = mainLoader.load();

        MainWindow runWindow = mainLoader.getController();

        stage.setTitle("Interpretor");
        stage.setScene(new Scene(mainWindow, 1024, 600));
        stage.show();

        FXMLLoader secondaryLoader = new FXMLLoader();
        secondaryLoader.setLocation(getClass().getResource("selectWindow.fxml"));
        Parent secondaryWindow = secondaryLoader.load();

        SelectProgramWindow selectWindow = secondaryLoader.getController();
        selectWindow.setSelectProgramWindow(runWindow);

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Interpretor");
        secondaryStage.setScene(new Scene(secondaryWindow, 800, 600));
        secondaryStage.show();
    }
}

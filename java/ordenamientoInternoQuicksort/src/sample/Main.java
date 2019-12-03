package sample;

import basics.Validacion;
import db.ConnectDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ConnectDB connect = ConnectDB.getInstance("jdbc:mysql://localhost/empresa", "erickdb", "", "MySql");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 577, 434));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

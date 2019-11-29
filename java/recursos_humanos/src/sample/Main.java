package sample;

import conexionDB.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Conexion connect = Conexion.getInstance("jdbc:mysql://localhost/recursosHumanos", "erickdb", "", "MySql");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Recursos Humanos");
        primaryStage.setScene(new Scene(root, 800, 574));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

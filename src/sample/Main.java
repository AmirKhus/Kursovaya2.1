package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginFile.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 674, 486));
        primaryStage.show();
    }

    public static User user;
    public static ArrayObject arrayObject;


    public static void main(String[] args) {
        launch(args);
    }
}

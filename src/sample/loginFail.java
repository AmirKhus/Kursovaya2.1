package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class loginFail implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginText;

    @FXML
    private PasswordField PasswordText;

    @FXML
    private Button LoginButton;

    @FXML
    private Hyperlink RegisterButton;


    public static String NameCase;
    public static String SurnameCase;
    public static String idAftor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RegisterButton.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Регистрация");
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/Registor.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

//            Stage stage1 = (Stage) lodinButton.getScene().getWindow();
//            stage1.close();
        });

        LoginButton.setOnAction(event -> {
            boolean zanyat = true;
            boolean nePravilno = false;
            try (Scanner scan = new Scanner(new File("PersonDataBase.txt"))) {
                while (scan.hasNextLine() && zanyat) {
                    String[] logon = scan.nextLine().split(",");
                    if (logon[0].equals(LoginText.getText()) && logon[1].equals(PasswordText.getText())) {
                        zanyat = false;
                        nePravilno = false;
                        SurnameCase = logon[3];
                        NameCase = logon[2];
                        idAftor = logon[4];
                        Main.user = new User(logon[0], logon[2], logon[3], logon[1], logon[4], logon[5], logon[6]);
                    } else {
                        nePravilno = true;
                    }

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }


            if (nePravilno) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println("Пароль или логин неверно введены");

                alert.setTitle("Ошибка");

                alert.setHeaderText("Сообщение об ошибке");

                alert.setContentText("Проверьте введенные логин и пароль");

                alert.showAndWait();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                System.out.println("Вход в учетную запись\n прошла успешно !!!Мои поздравления *****");

                alert1.setTitle("Ты супер!");

                alert1.setHeaderText("Сообщение об успехе");

                alert1.setContentText("Вход в учетную запись прошла\n успешно !!!Мои поздравления *****");

                alert1.showAndWait();

                Stage stage = new Stage();
                stage.setTitle("Художественный салон");
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/Catalog.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) LoginButton.getScene().getWindow();
                stage1.close();

            }
        });

    }
}

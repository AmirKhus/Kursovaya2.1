package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RegisterController implements Initializable {


    @FXML
    private TextField Login;

    @FXML
    private TextField Password;

    @FXML
    private TextField Name;

    @FXML
    private TextField Surname;

    @FXML
    private Button RegistorButton;

    @FXML
    private Button Phon;

    @FXML
    private Button CancelButton;

//    public void RegistorPerson() {
//        System.out.println("++++++++++++++++++");
//        try (Scanner scan = new Scanner(new File("PersonDataBase.txt"))) {
//            while(scan.hasNextLine() && NeZanyat){
//                String[] logon = scan.nextLine().split(",");
//                if (logon[0].equals(Login.getText())) {
//                    NeZanyat = false;
//                }
//            }
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        if (NeZanyat) {
//            String data = Login.getText()+","+ Password.getText()+" "+Name.getText()+","+Surname.getText()+"\n";
//            OutputStream os = null;
//            try {
//                //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
//                os = new FileOutputStream(new File("PersonDataBase.txt"), true);
//                os.write(data.getBytes(), 0, data.length());
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//                System.out.println("Создание учетной записи прошла успешно !!!Мои поздравления *****");
//
//                alert.setTitle("Ты супер!");
//
//                alert.setHeaderText("Сообщение об успехе");
//
//                alert.setContentText("Создание учетной записи прошла успешно !!!Мои поздравления *****");
//
//                alert.showAndWait();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally{
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            RegistorButton.getScene().getWindow().getOnCloseRequest();//прячем наше первое окно Взамен следующей
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/loginFile.fxml")); // позволяет указать расположение  нужного нам файла
//
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            Parent root = loader.getRoot(); //путь к необходимой сцены передали переменной
//            Stage stage = new Stage();
//            stage.setTitle("Регистрация");
//            stage.setScene(new Scene(root ));//указываем необходимую нам сцену
//            RegistorButton.getScene().getWindow().hide();
//            //stageLogin.close();
//
//            stage.close();//показать и подаждать пока появится сцена
//
//        }else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            System.out.println("Логин занят");
//
//            alert.setTitle("Ошибка");
//
//            alert.setHeaderText("Сообщение об ошибке");
//
//            alert.setContentText("Логин занят");
//
//            alert.showAndWait();
//
//
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RegistorButton.setOnAction(event -> {
            boolean NeZanyat = true;
            try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\PersonDataBase.txt"))) {
                while (scan.hasNextLine() && NeZanyat) {
                    String[] logon = scan.nextLine().split(",");
                    System.out.println(Login.getText());
                    if (logon[0].equals(Login.getText())) {
                        NeZanyat = false;
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (NeZanyat) {
                int idPeson = (int) (Math.random() * 999999);
                System.out.println(idPeson);
                String data = Login.getText() + "," + Password.getText() + "," + Name.getText() + "," + Surname.getText() + "," + idPeson + "," + Phon.getText()+ "\n";
                OutputStream os = null;
                try {
                    //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
                    os = new FileOutputStream(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\PersonDataBase.txt"), true);
                    os.write(data.getBytes("UTF8"), 0, data.getBytes("UTF8").length);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    System.out.println("Создание учетной записи прошла успешно !!!Мои поздравления *****");

                    alert.setTitle("Ты супер!");

                    alert.setHeaderText("Сообщение об успехе");

                    alert.setContentText("Создание учетной записи прошла успешно !!!Мои поздравления *****");

                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//                При нажатии на кнопку регистрация
                RegistorButton.getScene().getWindow().getOnCloseRequest();//прячем наше первое окно Взамен следующей
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/loginFile.fxml")); // позволяет указать расположение  нужного нам файла

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Parent root = loader.getRoot(); //путь к необходимой сцены передали переменной
                Stage stage = new Stage();
                stage.setScene(new Scene(root));//указываем необходимую нам сцену
                RegistorButton.getScene().getWindow().hide();
                //stageLogin.close();

                stage.close();//показать и подаждать пока появится сцена

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println("Логин занят");

                alert.setTitle("Ошибка");

                alert.setHeaderText("Сообщение об ошибке");

                alert.setContentText("Логин занят");

                alert.showAndWait();


            }

        });
    }
}

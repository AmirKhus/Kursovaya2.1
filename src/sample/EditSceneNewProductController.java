package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class EditSceneNewProductController extends loginFail implements Initializable {

    @FXML
    private GridPane productTabel;

    @FXML
    private TextField productPutField;

    @FXML
    private TextField productAftorField;

    @FXML
    private TextField productSumField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productWidthField;

    @FXML
    private TextField productLengthField;

    @FXML
    private Button Ok;

    @FXML
    private Button Cancel;
    private Product product;
    private boolean okKlicked = false;

    public boolean isOkKlicked() {
        return okKlicked;
    }


    @FXML
    void handleOk() {
        if (isInputValid()) {
            int idProduct = (int) (Math.random() * 999999);

            System.out.println(idProduct);
            String data = productPutField.getText() + "," + productAftorField.getText() + "," + productSumField.getText() + "," + idProduct + ","
                    + productNameField.getText() + "," + idAftor + "," + productLengthField.getText() + "," + productWidthField.getText() + "\n";

            try (OutputStream os = new FileOutputStream(new File("ProductDataBase.txt"), true)) {
                os.write(data.getBytes("UTF8"), 0, data.getBytes("UTF8").length);
            } catch (FileNotFoundException e) {
                String errorMessage = "";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Неверный путь");
                alert.setHeaderText("Внесите корректную информацию");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            } catch (IOException e) {
                System.out.println("ыфвфывфыв");
                e.printStackTrace();
            }
            Stage stage1 = (Stage) Ok.getScene().getWindow();
            stage1.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (productPutField.getText() == null || productPutField.getText().length() == 0) {
            errorMessage += "Нет доступного пути\n";
        }
        if (productNameField.getText() == null || productNameField.getText().length() == 0) {
            errorMessage += "Нет доступного наименования товара\n";
        }
        if (productSumField.getText() == null || productSumField.getText().length() == 0) {
            errorMessage += "Нет доступной сувары\n";
        }
        if (productAftorField.getText() == null || productAftorField.getText().length() == 0) {
            errorMessage += "Нет автора\n";
        }
        if (productLengthField.getText() == null || productLengthField.getText().length() == 0) {
            errorMessage += "Нет Длины\n";
        }
        if (productWidthField.getText() == null || productWidthField.getText().length() == 0) {
            errorMessage += "Нет Ширины\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
// Показывает сообщение об ошибке
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

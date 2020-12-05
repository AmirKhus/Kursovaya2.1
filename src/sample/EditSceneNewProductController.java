package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
//            product.setProductName(productNameField.getText());
//            product.setProductSum(productSumField.getText());
//            product.setProductAftor(productAftorField.getText());
//            product.setProductPutImage(productPutField.getText());
////            product.setProductImage(productPutField.getText());
//            Product.addProduct(product);
            int idProduct = (int) (Math.random() * 999999);
            System.out.println(idProduct);
            String data = productPutField.getText() + "," + productAftorField.getText() + "," + productSumField.getText() + "," + idProduct + "," + productNameField.getText()+ "," + idAftor + "\n";
            OutputStream os;
            try {
                //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
                os = new FileOutputStream(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"), true);
                os.write(data.getBytes("UTF8"), 0, data.getBytes("UTF8").length);
            } catch (IOException e) {
                e.printStackTrace();
            }
//C:\Users\KP\Downloads\images.png

        }
        Stage stage1 = (Stage) Ok.getScene().getWindow();
        stage1.close();
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

package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EditSceneController implements Initializable {

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

    private Stage dialogStage;
    private Product product;
    private boolean okKlicked = false;

    public boolean isOkKlicked() {
        return okKlicked;
    }

    @FXML
    private void handleCancel() {
//        dialogStage.close();
    }


    @FXML
    void handleOk() {
        if (isInputValid()) {
            product.setProductId(productPutField.getText());
            product.setProductName(productNameField.getText());
            product.setProductSum(productSumField.getText());
            product.setProductAftor(productAftorField.getText());
            product.setProductPutImage(productPutField.getText());
//            product.setProductImage(productPutField.getText());

            Product.addProduct(product);
            okKlicked = true;
            dialogStage.close();
        }
    }
    private boolean isInputValid(){
        String errorMessage = "";
        if  (productPutField.getText() == null || productPutField.getText().length() == 0) {
            errorMessage += "Нет доступного артикула\n";
        }
        if  (productNameField.getText() == null || productNameField.getText().length() == 0) {
            errorMessage += "Нет доступного наименования товара\n";
        }
        if (productSumField.getText() == null || productSumField.getText().length() == 0) {
            errorMessage += "Нет доступной сувары\n";
        }
        if (productAftorField.getText() == null || productAftorField.getText().length() == 0) {
            errorMessage += "Нет количества\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }else {
// Показывает сообщение об ошибке
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;

        productPutField.setText(product.productPutImage().getValue() != null ? product.productPutImage().getValue():"");
        productNameField.setText(product.productNameProperty().getValue()!=null ? product.productNameProperty().getValue().toString():"");
        productSumField.setText(product.productSumProperty().getValue()!= null ? product.productSumProperty().getValue().toString():"");
        productAftorField.setText(product.productAftorProperty().getValue()!= null ?product.productAftorProperty().getValue().toString():"");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
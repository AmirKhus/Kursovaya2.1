package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    @FXML
    private TextField productLengthField;

    @FXML
    private TextField productWidthField;


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
    void handleOk() throws IOException {
        if (isInputValid()) {
            product.setProductName(productNameField.getText());
            product.setProductSum(productSumField.getText());
            product.setProductAftor(productAftorField.getText());
            product.setProductPutImage(productPutField.getText());
            product.setProductLength(productLengthField.getText());
            product.setProductWidth(productWidthField.getText());

//       String productId= "426";
//        String fileName = "fail.txt";
//            Scanner scan = new Scanner(new File(fileName));
//            while(scan.hasNextLine()) {
//                String[] logon = scan.nextLine().split(",");
//                if (logon[3].equals(productId) ) {
//                    Charset charset = StandardCharsets.UTF_8;
//                    Path path = Paths.get(fileName);
//                    Files.write(path,
//                            new String(Files.readAllBytes(path), charset).replace(logon[0], newProductAftorField)
//                                    .getBytes(charset));
//                    Files.write(path,
//                            new String(Files.readAllBytes(path), charset).replace(logon[1], newProductSum)
//                                    .getBytes(charset));
//                    Files.write(path,
//                            new String(Files.readAllBytes(path), charset).replace(logon[2], newproductName)
//                                    .getBytes(charset));
//                }

//            if (logon[3].equals(product.productIdProperty().getValue()) ) {
            String fileName = "C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt";
            Scanner scan = new Scanner(new File(fileName));
            while(scan.hasNextLine()) {
                String[] logon = scan.nextLine().split(",");
                if (logon[3].equals(product.productIdProperty().getValue()) ) {
                    Charset charset = StandardCharsets.UTF_8;
                    Path path = Paths.get(fileName);
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(logon[0], productPutField.getText())
                                    .getBytes(charset));
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(logon[1], productAftorField.getText())
                                    .getBytes(charset));
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(logon[2], productSumField.getText())
                                    .getBytes(charset));
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(logon[4], productNameField.getText())
                                    .getBytes(charset));
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(logon[6], productLengthField.getText())
                                    .getBytes(charset));
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(logon[7], productWidthField.getText())
                                    .getBytes(charset));
                }
            }
            Product.addProduct(product);
            okKlicked = true;
            dialogStage.close();
        }
    }
    private boolean isInputValid(){
        String errorMessage = "";
        if  (productPutField.getText() == null || productPutField.getText().length() == 0) {
            errorMessage += "Нет доступного пути\n";
        }
        if  (productNameField.getText() == null || productNameField.getText().length() == 0) {
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
        if (product != null) {
            productPutField.setText(product.productPutImage() != null ? product.productPutImage().getValue():"");
            productNameField.setText(product.productNameProperty() !=null ? product.productNameProperty().getValue():"");
            productSumField.setText(product.productSumProperty() != null ? product.productSumProperty().getValue():"");
            productAftorField.setText(product.productAftorProperty() != null ?product.productAftorProperty().getValue():"");
            productWidthField.setText(product.productWidthProperty() != null ?product.productWidthProperty().getValue():"");
            productLengthField.setText(product.productLengthProperty() != null ?product.productLengthProperty().getValue():"");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private static ArrayObject arrayObject;

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

            WriteArrayWithFile();
            for (int i = 0; i < arrayObject.getBody().length; i++) {
                if (arrayObject.body[i].equals(product.productIdProperty().getValue())) {
                    arrayObject.body[i - 3] = productPutField.getText();
                    arrayObject.body[i - 2] = productAftorField.getText();
                    arrayObject.body[i - 1] = productSumField.getText();
//                array[3] idproduct;//
                    arrayObject.body[i + 1] = productNameField.getText();
//                array[5]= idaftor
                    arrayObject.body[i + 3] = productLengthField.getText();
                    arrayObject.body[i + 4] = productWidthField.getText();
                }
            }
            FileWriter filewriter;
            int coutCell = 0;
            try {
                filewriter = new FileWriter(new File("ProductDataBase.txt "));
                for (int i = 0; i < arrayObject.getBody().length; ++i)
                    if (coutCell < 7) {
                        filewriter.write(arrayObject.getBody()[i] + ",");
                        coutCell++;
                        System.out.println(coutCell);
                    } else {
                        filewriter.write(arrayObject.getBody()[i] + "\n");
                        coutCell = 0;
                    }
                filewriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Product.addProduct(product);
            okKlicked = true;
            dialogStage.close();
        }
    }

    static void WriteArrayWithFile() {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner scan = new Scanner(new File("ProductDataBase.txt"))) {
            while (scan.hasNextLine()) {
                String[] logon = scan.nextLine().split(",");
                for (int i = 0; i < logon.length; i++) {
                    list.add(logon[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] array = list.toArray(new String[0]);
        arrayObject = new ArrayObject(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println("array[" + i + "] = " + array[i]);
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
            productPutField.setText(product.productPutImage() != null ? product.productPutImage().getValue() : "");
            productNameField.setText(product.productNameProperty() != null ? product.productNameProperty().getValue() : "");
            productSumField.setText(product.productSumProperty() != null ? product.productSumProperty().getValue() : "");
            productAftorField.setText(product.productAftorProperty() != null ? product.productAftorProperty().getValue() : "");
            productWidthField.setText(product.productWidthProperty() != null ? product.productWidthProperty().getValue() : "");
            productLengthField.setText(product.productLengthProperty() != null ? product.productLengthProperty().getValue() : "");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
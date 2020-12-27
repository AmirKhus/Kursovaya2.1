package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class productDescriptionController implements Initializable {

    @FXML
    private ImageView productImageField;

    @FXML
    private Label productNameField;

    @FXML
    private Label productAftorField;

    @FXML
    private Label productSumField;

    @FXML
    private Label productLengthField;

    @FXML
    private Label productAftorPhonField;

    @FXML
    private Label productWidthField;

    @FXML
    private ImageView ImageFonDiscriotion;

    @FXML
    private Button Cancel;

    private Stage dialogStage;
    private Product product;
    private boolean okKlicked = false;

    public boolean isOkKlicked() {
        return okKlicked;
    }

    @FXML
    private void handleCancel() {
        Stage stage1 = (Stage) Cancel.getScene().getWindow();
        stage1.close();
    }

    boolean haveProduct;

    @FXML
    private void BuyProduck() {
        Stage stage = new Stage();
        stage.setTitle("Художественный салон");
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/BuyProduct.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();


//        try (Scanner scan = new Scanner(new File("ProductFavoritesDataBase.txt"))) {
//            while (scan.hasNextLine()) {
//                System.out.println("Вывод информации");
//                String[] logon = scan.nextLine().split(",");
//                System.out.println(logon[3] + "---" + product.productIdProperty().getValue());
//                if (logon[3].equals(product.productIdProperty().getValue())) {
//                    haveProduct = true;
//                    break;
//                } else {
//                    haveProduct = false;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (haveProduct) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//            alert.setTitle("Предупреждение");
//
//            alert.setHeaderText("Товар ужe был добавлен");
//
//            alert.setContentText("Данный товара уже был добавленн в Избранное!");
//
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//
//            alert.setTitle("Оповещение");
//
//            alert.setHeaderText("Товар добавлен");
//
//            alert.setContentText("Данный товара успешно добавленн в Избранное!");
//
//            alert.showAndWait();
//            String data = product.productPutImage().getValue() + "," + product.productAftorProperty().getValue() + "," + product.productSumProperty().getValue() + ","
//                    + product.productIdProperty().getValue() + "," + product.productNameProperty().getValue() + "," + Main.user.getId() + "," + product.productLengthProperty().getValue()
//                    + "," + product.productWidthProperty().getValue() + "," + Main.user.getId() + "," + Main.user.getPhon() + "\n";
//            OutputStream os;
//            try {
//                //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
//                os = new FileOutputStream(new File("ProductFavoritesDataBase.txt"), true);
//                os.write(data.getBytes("UTF8"), 0, data.getBytes("UTF8").length);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @FXML
    private void addProduckFavorites() {

        try (Scanner scan = new Scanner(new File("ProductFavoritesDataBase.txt"))) {
            while (scan.hasNextLine()) {
                System.out.println("Вывод информации");
                String[] logon = scan.nextLine().split(",");
                System.out.println(logon[3] + "---" + product.productIdProperty().getValue());
                if (logon[3].equals(product.productIdProperty().getValue())) {
                    haveProduct = true;
                    break;
                } else {
                    haveProduct = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (haveProduct) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Предупреждение");

            alert.setHeaderText("Товар ужe был добавлен");

            alert.setContentText("Данный товара уже был добавленн в Избранное!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Оповещение");

            alert.setHeaderText("Товар добавлен");

            alert.setContentText("Данный товара успешно добавленн в Избранное!");

            alert.showAndWait();
            String data = product.productPutImage().getValue() + "," + product.productAftorProperty().getValue() + "," + product.productSumProperty().getValue() + ","
                    + product.productIdProperty().getValue() + "," + product.productNameProperty().getValue() + "," + Main.user.getId() + "," + product.productLengthProperty().getValue()
                    + "," + product.productWidthProperty().getValue() + "," + Main.user.getId() + "," + Main.user.getPhon() + "\n";
            OutputStream os;
            try {
                //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
                os = new FileOutputStream(new File("ProductFavoritesDataBase.txt"), true);
                os.write(data.getBytes("UTF8"), 0, data.getBytes("UTF8").length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void handleOk() throws IOException {
        if (isInputValid()) {
            product.setProductName(productNameField.getText());
            product.setProductSum(productSumField.getText());
            product.setProductAftor(productAftorField.getText());
            product.setProductLength(productLengthField.getText());
            product.setProductWidth(productWidthField.getText());

            Scanner scan = new Scanner(new File("ProductDataBase.txt"));
            while (scan.hasNextLine()) {
                String[] logon = scan.nextLine().split(",");
                if (logon[3].equals(product.productIdProperty().getValue())) {

                    logon[1] = productAftorField.getText();
                    logon[2] = productSumField.getText();
                    logon[4] = productNameField.getText();

                }
            }


            Product.addProduct(product);
            okKlicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
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

    public void setProduct(Product product) throws FileNotFoundException {
        this.product = product;
        Image img;
        if (product != null) {
            img = new Image(new FileInputStream(product.productPutImage().getValue()));
            productImageField.setImage(img);
            productNameField.setText(product.productNameProperty() != null ? product.productNameProperty().getValue() : "");
            productSumField.setText(product.productSumProperty() != null ? product.productSumProperty().getValue() : "");
            productAftorField.setText(product.productAftorProperty() != null ? product.productAftorProperty().getValue() : "");
            productWidthField.setText(product.productWidthProperty() != null ? product.productWidthProperty().getValue() : "");
            productLengthField.setText(product.productLengthProperty() != null ? product.productLengthProperty().getValue() : "");
            productAftorPhonField.setText(Main.user.getPhon() != null ? Main.user.getPhon() : "");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = null;
        try {
            img = new Image(new FileInputStream("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\DescriptionFon.jpg"));
            ImageFonDiscriotion.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}

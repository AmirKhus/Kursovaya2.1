package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MyProductFavoritesController implements Initializable {
    final ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    private Button showProductEditDialog;

    @FXML
    private Button Cancel;

    @FXML
    private TableView<Product> catalogTabel;

    @FXML
    private TableColumn<Product, ImageView> ImageProduct;

    @FXML
    private TableColumn<Product, String> AftorProduct;

    @FXML
    private TableColumn<Product, String> SumProduct;

    @FXML
    private TableColumn<Product, String> idProduct;

    @FXML
    private TableColumn<Product, String> NameProduct;

    static String put;
    private String idProductString;
    private static ArrayObject arrayObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try (Scanner scan = new Scanner(new File("ProductFavoritesDataBase.txt"))) {
            while (scan.hasNextLine()) {
                String[] logon = scan.nextLine().split(",");
                System.out.println(logon[0]);
                put = logon[0];
                Image img = new Image(new FileInputStream(logon[0]));
                ImageView photo = new ImageView(img);
                if (logon[5].equals(Main.user.getId())) {
                    productData.add(new Product(photo, logon[1], logon[2], logon[3], logon[4], Main.user, logon[0], logon[6], logon[7]));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ImageProduct.setCellValueFactory(data -> data.getValue().productImageProperty());
        AftorProduct.setCellValueFactory(data -> data.getValue().productAftorProperty());
        SumProduct.setCellValueFactory(data -> data.getValue().productSumProperty());
        idProduct.setCellValueFactory(data -> data.getValue().productIdProperty());
        NameProduct.setCellValueFactory(data -> data.getValue().productNameProperty());
        catalogTabel.setItems(productData);

        catalogTabel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));
    }

    private void showProductDetails(Product product) {
        if (product != null) {
            idProductString = product.getProductId();
            System.out.println(idProductString + "showProductDetails");
        } else {
        }
    }

    static Product product;

    @FXML
    private void handleDeleteProduc() {
        int selectedIndex = catalogTabel.getSelectionModel().getSelectedIndex();
        WriteArrayWithFile();
        for (int i = 0; i < arrayObject.getBody().length; i++) {
            if (arrayObject.body[i].equals(idProductString)) {

                arrayObject.body[i - 3] = "";
                arrayObject.body[i - 2] = "";
                arrayObject.body[i - 1] = "";
                arrayObject.body[i] = "";
                arrayObject.body[i + 1] = "";
                arrayObject.body[i + 2] = "";
                arrayObject.body[i + 3] = "";
                arrayObject.body[i + 4] = "";
            }
        }
        FileWriter filewriter;
        int coutCell = 0;
        try {
            filewriter = new FileWriter(new File("ProductFavoritesDataBase.txt "));
            for (int i = 0; i < arrayObject.getBody().length; ++i)
                if (coutCell < 7) {
                    System.out.println(idProductString);
                    if (arrayObject.body[i].equals("")) {
                    } else {
                        filewriter.write(arrayObject.getBody()[i] + ",");
                    }
                    coutCell++;
                    System.out.println(coutCell);
                } else {
                    if (arrayObject.body[i].equals("")) {
                    } else {
                        filewriter.write(arrayObject.getBody()[i] + "\n");
                    }
                    coutCell = 0;
                }
            filewriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (selectedIndex >= 0) {
            catalogTabel.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран товар");
            alert.setContentText("Выберите товар в таблице");

            alert.showAndWait();
        }
    }

    static void WriteArrayWithFile() {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner scan = new Scanner(new File("ProductFavoritesDataBase.txt"))) {
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

    @FXML
    private void handleCancel() {
//        dialogStage.close();
        Stage stage1 = (Stage) Cancel.getScene().getWindow();
        stage1.close();
    }

    @FXML
    private void BuyProduct() {
        Product selectedProduct = catalogTabel.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = showProdoctEditDialog(selectedProduct);
            if (okClicked) {
                int selectedIndex = catalogTabel.getSelectionModel().getSelectedIndex();
                productData.set(selectedIndex, selectedProduct);
            }

        } else {
//        Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не набрано");
            alert.setHeaderText("Нет продукта");
            alert.setContentText("Выберите продукт в таблице");
            alert.showAndWait();
        }
    }

    public boolean showProdoctEditDialog(Product product) {
        try {
//          Загружаем fxml-файл и создаем новую сцену для
//          для всплывающего диалогового окна
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditSceneController.class.getResource("productDescription.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

//    Создаем диалоговое окно Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Информация о товаре");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
//    Передаём адресата в контроллер.
            productDescriptionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
// диалоговое окно и ждет, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkKlicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MyProductController implements Initializable {

    final ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    private Button showProductEditDialog;

    @FXML
    private Button Cacel;

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
//    public void ShowInfoProduct() {
//        try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"))) {
//            while(scan.hasNextLine() ) {
//                String[] logon = scan.nextLine().split(",");
////                Image img = new Image(new FileInputStream("C:\\Users\\KP\\IdeaProjects\\untitled3\\src\\sample\\ruki.png"));
////                ImageView photo = new ImageView(img);
//                String put= "ruki2.png";
//                Image img = new Image(getClass().getResource(put).toExternalForm());
//                ImageView photo = new ImageView(img);
//                if (logon[5].equals(Main.user.getId())) {
//                    productData.add(new Product( logon[1], logon[2], logon[3], logon[4], Main.user));
//                    System.out.println(logon[1]);
//                }
//;
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        ImageProduct.setCellValueFactory(new PropertyValueFactory<>("ImageProduct"));
//        AftorProduct.setCellValueFactory(new PropertyValueFactory<>("AftorProduct"));
//        SumProduct.setCellValueFactory(new PropertyValueFactory<>("SumProduct"));
//        idProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
//        NameProduct.setCellValueFactory(new PropertyValueFactory<>("NameProduct"));
//
//        catalogTabel.setItems(productData);
//
//        catalogTabel.getSelectionModel().selectedItemProperty().addListener(
//                (observable,oldValue,newValue )-> showProductDetails(newValue));//Что тут происходит
//    }

    static String put;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"))) {
            while(scan.hasNextLine() ) {
                String[] logon = scan.nextLine().split(",");
                System.out.println(logon[0]);
                put = logon[0];
                Image img = new Image(new FileInputStream(logon[0]));
                ImageView photo = new ImageView(img);
                if (logon[5].equals(Main.user.getId())) {
                    productData.add(new Product(photo, logon[1], logon[2], logon[3], logon[4], Main.user,logon[0]));
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
//        catalogTabel.getSelectionModel().selectedItemProperty().addListener(
//                (observable,oldValue,newValue )-> showProductDetails(newValue));
    }
//    private void showProductDetails( Product product) {
//        if (product != null) {
//            idProduct.setText(product.productIdProperty().getValue().toString());
//            System.out.println(product.productIdProperty().getValue().toString());
//            AftorProduct.setText(product.productAftorProperty().toString());
//            NameProduct.setText(product.productNameProperty().toString());//Зачем toString()
//            SumProduct.setText(product.productSumProperty().toString());
//            ImageProduct.setText(product.productImageProperty().toString());
//        } else {
//            idProduct.setText("");
//            AftorProduct.setText("");
//            NameProduct.setText("");
//            AftorProduct.setText("");
//            ImageProduct.setText("");
//        }
//    }


    @FXML
    private void handleNewProduct(){
        Product tempProduct = new Product();
        boolean okClicked = this.showProdoctEditDialog(tempProduct);
        if (okClicked) {
            productData.add(tempProduct);
        }
    }

    @FXML
    private void handleDeleteProduc() {
        int selectedIndex = catalogTabel.getSelectionModel().getSelectedIndex();
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


    @FXML
    private void handleEditProduct() {
        Product selectedProduct = catalogTabel.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = showProdoctEditDialog(selectedProduct);
            if (okClicked) {
                int selectedIndex = catalogTabel.getSelectionModel().getSelectedIndex();
                productData.set(selectedIndex, selectedProduct);
            }

        }else{
//        Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не набрано");
            alert.setHeaderText("Нет продукта");
            alert.setContentText("Выберите продукт в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel() {
//        dialogStage.close();
        Stage stage1 = (Stage) Cacel.getScene().getWindow();
        stage1.close();
    }


    public boolean showProdoctEditDialog(Product product){
        try {
//          Загружаем fxml-файл и создаем новую сцену для
//          для всплывающего диалогового окна
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditSceneController.class.getResource("EditScene.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

//    Создаем диалоговое окно Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
//    Передаём адресата в контроллер.
            EditSceneController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
// диалоговое окно и ждет, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkKlicked();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

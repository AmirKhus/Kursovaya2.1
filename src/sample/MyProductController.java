package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private String ImagePutProductString;
    private String AftorProductString;
    private String SumProductString;
    private String idProductString;
    private String NameProductString;
    static String put;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initializ -> "+idProductString);
        try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"))) {
            while(scan.hasNextLine() ) {
                String[] logon = scan.nextLine().split(",");
                System.out.println(logon[0]);
                put = logon[0];
                Image img = new Image(new FileInputStream(logon[0]));
                ImageView photo = new ImageView(img);
                if (logon[5].equals(Main.user.getId())) {
                    productData.add(new Product(photo, logon[1], logon[2], logon[3], logon[4], Main.user,logon[0],logon[6],logon[7]));
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
                (observable,oldValue,newValue )-> showProductDetails(newValue));

    }
    private void showProductDetails( Product product) {
        if (product != null) {
            System.out.println("HI");
//            idProductString = product.productIdProperty().getValue().toString();
//            AftorProductString = product.productAftorProperty().getValue();
//            NameProductString =product.productNameProperty().getValue().toString();//Зачем toString()
//            SumProductString =product.productSumProperty().getValue().toString();
//            ImagePutProductString = product.productPutImage().getValue().toString();
//            System.out.println("showProductDetails -> "+idProductString);
//            String fileName = "C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt";
//            Scanner scan;
//            try {
//                System.out.println("product.productIdProperty().getValue() "+product.productIdProperty().getValue());
//                scan = new Scanner(new File(fileName));
//                while(scan.hasNextLine()) {
//                    String[] logon = scan.nextLine().split(",");
//                    if (logon[3].equals(product.productIdProperty().getValue()) ) {
//                        Charset charset = StandardCharsets.UTF_8;
//                        Path path = Paths.get(fileName);
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[0], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[1], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[2], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[3], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[4], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[5], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[6], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Files.writeString(path,
//                                    Files.readString(path, charset).replace(logon[7], ""), charset);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(" handleDeleteProduc POSLE -> "+idProductString);
//                        break;
//                    }
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }


        } else {
            idProduct.setText("");
            AftorProduct.setText("");
            NameProduct.setText("");
            AftorProduct.setText("");
            ImageProduct.setText("");
        }
    }


    @FXML
    private void handleNewProduct(){
        Stage stage = new Stage();
        stage.setTitle("New product");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/EditSceneNewProduct.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            System.out.println("jopa");
        }
    }
    static Product product;
    @FXML
    private void handleDeleteProduc() throws IOException {
        System.out.println(" handleDeleteProduc DO -> "+idProductString);
        int selectedIndex = catalogTabel.getSelectionModel().getSelectedIndex();
        System.out.println(" handleDeleteProduc DO 1.2-> "+idProductString);
        System.out.println("product.productIdProperty().getValue() "+product.productIdProperty().getValue());
        if (selectedIndex >= 0) {
            catalogTabel.getItems();
            System.out.println("product.productIdProperty().getValue() "+product.productIdProperty().getValue());
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
//    public boolean showProdoctEditDialogNewProduct(Product product){
//        try {
////          Загружаем fxml-файл и создаем новую сцену для
////          для всплывающего диалогового окна
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(EditSceneNewProductController.class.getResource("EditSceneNewProduct.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
////    Создаем диалоговое окно Stage
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("New Product");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(null);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
////    Передаём адресата в контроллер.
//            EditSceneNewProductController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setProduct(product);
//// диалоговое окно и ждет, пока пользователь его не закроет
//            dialogStage.showAndWait();
//            return controller.isOkKlicked();
//        }catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}

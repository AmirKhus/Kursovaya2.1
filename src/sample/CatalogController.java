package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class CatalogController extends loginFail implements Initializable {

    @FXML
    private Tab CatalogTab;

    @FXML
    private Button BuyProduct;

    @FXML
    private Tab LKTab;

    @FXML
    private Menu Файл;

    @FXML
    private MenuItem Закрыть;

    @FXML
    private Menu Заказы;

    @FXML
    private MenuItem Посмотреть;

    final ObservableList<Product> productData = FXCollections.observableArrayList();

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

    @FXML
    private ImageView FonImageLK;

    @FXML
    private void ShowHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(null);
        alert.setTitle("Помощь");
        alert.setContentText("При возникновении проблем пишите\n на почту amir19040268@gmail.com");

        alert.showAndWait();
    }

    @FXML
    private Menu Товары;

    @FXML
    private Label Name;

    @FXML
    private Label Phon;

    @FXML
    private Label Surname;


    @FXML
    private Button MyProduct;

    @FXML
    private Button MyOrders;

    @FXML
    private void soldProduct() {
        if (Main.user.getDostup().equals("1")) {
            Stage stage = new Stage();
            stage.setTitle("Проданные товары");
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("soldProduct.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Нет доступа");
            alert.setHeaderText("Кнопак не действительна");
            alert.setContentText("Это кнопка действительная только для администрации");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleShowMyProductFavorites() {
        Stage stage = new Stage();
        stage.setTitle("Избранные");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MyProductFavorites.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleFilterProduct() {
        Stage stage = new Stage();
        stage.setTitle("Избранные");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("filter.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    private void ShowProduct() {
        try (Scanner scan = new Scanner(new File("ProductDataBase.txt"))) {
            while (scan.hasNextLine()) {
                String[] logon = scan.nextLine().split(",");
                System.out.println(logon[0]);
                Image img = new Image(new FileInputStream(logon[0]));
                ImageView photo = new ImageView(img);
                productData.add(new Product(photo, logon[1], logon[2], logon[3], logon[4], Main.user, logon[0], logon[6], logon[7]));
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = null;
        try {
            img = new Image(new FileInputStream("C:\\Users\\KP\\Downloads\\9966913_img-0735.jpg"));
            FonImageLK.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Name.setText(Main.user.getFirstName());
        Surname.setText(Main.user.getLastName());
        Phon.setText(Main.user.getPhon());
        MyProduct.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Мои товары");
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("MyProduct.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
        ShowProduct();
    }

}
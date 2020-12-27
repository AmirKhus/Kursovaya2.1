package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class filterController implements Initializable {

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
    private TextField filterField;


    @FXML
    private Button Cancel;

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
            dialogStage.setTitle("Edit Product");
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

    @FXML
    private void handleCancel() {
        Stage stage1 = (Stage) Cancel.getScene().getWindow();
        stage1.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        FilteredList<Product> filteredData = new FilteredList<>(productData, b -> true);


        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.productNameProperty().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (employee.productAftorProperty().getValue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(employee.productIdProperty().getValue()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(catalogTabel.comparatorProperty());

        catalogTabel.setItems(sortedData);
    }
}
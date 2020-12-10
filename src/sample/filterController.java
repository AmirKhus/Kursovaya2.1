package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"))) {
            while(scan.hasNextLine() ) {
                String[] logon = scan.nextLine().split(",");
                System.out.println(logon[0]);
                Image img = new Image(new FileInputStream(logon[0]));
                ImageView photo = new ImageView(img);
                productData.add(new Product(photo, logon[1], logon[2], logon[3], logon[4], Main.user,logon[0],logon[6],logon[7]));
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


        filterField.textProperty().addListener((observable, oldValue, newValue) ->{
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
                }
                else if (String.valueOf(employee.productIdProperty().getValue()).indexOf(lowerCaseFilter)!=-1)
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
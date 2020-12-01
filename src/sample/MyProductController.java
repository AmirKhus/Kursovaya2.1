package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyProductController implements Initializable {

    final ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    private Button showProductEditDialog;

    @FXML
    private Button Cacel;

    @FXML
    private TableView<Product> catalogTabel;

//    @FXML
//    private TableColumn<Product, ImageView> ImageProduct;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"))) {
//            while(scan.hasNextLine() ) {
//                String[] logon = scan.nextLine().split(",");
////                Image img = new Image(new FileInputStream("C:\\Users\\KP\\IdeaProjects\\untitled3\\src\\sample\\ruki.png"));
////                ImageView photo = new ImageView(img);
////                String put= "ruki2.png";
////                Image img = new Image(getClass().getResource(put).toExternalForm());
////                ImageView photo = new ImageView(img);
////                if (logon[5].equals(Main.user.getId())) {
////                    productData.add(new Product( logon[1], logon[2], logon[3], logon[4], Main.user));
//                    System.out.println(logon[1]);
//                    System.out.println(logon[2]);
//                    System.out.println(logon[3]);
//                    System.out.println(logon[4]);
//                    Name = logon[4];
//                    Aftor = logon[1];
//                    Sum = logon[2];
//                    Artiku = logon[3];
////                }
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        productData.add(new Product(  "Aftor", "Sum", "Artiku","Name", Main.user));

//        ImageProduct.setCellValueFactory(new PropertyValueFactory<>("ImageProduct"));
        productData.add(new Product("Aftor", "Sum", "Artiku","Name",Main.user));
        productData.add(new Product("Aftor", "Sum", "Artiku","Name",Main.user));
        AftorProduct.setCellValueFactory(new PropertyValueFactory<Product,String>("AftorProduct"));
        SumProduct.setCellValueFactory(new PropertyValueFactory<Product,String>("SumProduct"));
        idProduct.setCellValueFactory(new PropertyValueFactory<Product,String>("idProduct"));
        NameProduct.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        catalogTabel.setItems(productData);

//        catalogTabel.getSelectionModel().selectedItemProperty().addListener(
//                (observable,oldValue,newValue )-> showProductDetails(newValue));//Что тут происходит
    }
//    private void showProductDetails( Product product) {
//        if (product != null) {
//            idProduct.setText(product.getProductId().toString());
//            AftorProduct.setText(product.getProductName());
//            NameProduct.setText(product.getProductSum().toString());//Зачем toString()
//            AftorProduct.setText(product.getProductAftor().toString());
////            ImageProduct.setText(product.getProductImage().toString());
//        } else {
//            idProduct.setText("");
//            AftorProduct.setText("");
//            NameProduct.setText("");
//            AftorProduct.setText("");
////            ImageProduct.setText("");
//        }
//    }
}

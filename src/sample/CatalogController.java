package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
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

    @FXML
    private Menu Товары;

    @FXML
    private Label Name;

    @FXML
    private Label Surname;


    @FXML
    private Button MyProduct;

    @FXML
    private Button MyOrders;

    @FXML
    private void handleShowMyProduct() {
//        try (Scanner scan = new Scanner(new File("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\src\\sample\\ProductDataBase.txt"))) {
//            while(scan.hasNextLine()) {
//                String[] logon = scan.nextLine().split(",");
//                Image img = new Image(getClass().getResource("C:\\Users\\KP\\IdeaProjects\\untitled3\\src\\sample\\ruki2.png").toExternalForm());
//                ImageView photo = new ImageView(img);
//                new Product(photo,logon[1],logon[2],logon[3],logon[4]);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        MyProductController myProductController = new MyProductController();
//        myProductController.ShowInfoProduct();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setText(Main.user.getFirstName());
        Surname.setText(Main.user.getLastName());
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

//            Stage stage1 = (Stage) lodinButton.getScene().getWindow();
//            stage1.close();
        });
    }

}
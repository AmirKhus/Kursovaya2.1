package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyProductController implements Initializable {

    @FXML
    private Label LabelName;

    @FXML
    private Label LabelPhon;

    @FXML
    private ImageView ImageFonDiscriotion;
    Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelName.setText(Main.user.getFirstName());
        LabelPhon.setText(Main.user.getPhon());
        Image img;
        try {
            img = new Image(new FileInputStream("C:\\Users\\KP\\IdeaProjects\\Kursovaya2\\FanksBuy.jpg"));
            ImageFonDiscriotion.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
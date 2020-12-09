package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;


public class Product {
    private StringProperty productName;
    private StringProperty productAftor;
    private StringProperty productId;
    private StringProperty productSum;
    private StringProperty productPutImage;
    private StringProperty productLength;
    private StringProperty productWidth;
    private User user;
    public ObjectProperty<ImageView> productImage = null;

    public String getProductWidth() {
        return this.productWidth.get();
    }

    public void setProductWidth(String productWidth) {
        this.productWidth.set(productWidth);
    }

    public StringProperty productWidthProperty() {
        return this.productWidth;
    }
    //    -------------------------------
    public String getProductLength() {
        return this.productLength.get();
    }

    public void setProductLength(String productLength) {
        this.productLength.set(productLength);
    }

    public StringProperty productLengthProperty() {
        return this.productLength;
    }
    //    -------------------------------
    public String getProductPutImage() {
        return this.productPutImage.get();
    }

    public void setProductPutImage(String productPutImage) {
        this.productPutImage.set(productPutImage);
    }

    public StringProperty productPutImage() {
        return this.productPutImage;
    }
//    -------------------------------

    public Product(ObjectProperty<ImageView> productImage) {

        this.productImage = productImage;
    }

    public ImageView getProductImage() {
        return this.productImage.get();
    }

    public void setProductImage(ImageView productImage) {
        this.productImage.set(productImage);
    }

    public ObjectProperty<ImageView> productImageProperty() {
        return this.productImage;
    }
//-------------------------------------
    public String getProductName() {
        return this.productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public StringProperty productNameProperty() {
        return this.productName;
    }
//    -------------------------------
    public String getProductAftor() {
        return this.productAftor.get();
    }

    public void setProductAftor(String productAftor) {
        this.productAftor.set(productAftor);
    }

    public StringProperty productAftorProperty() {
        return this.productAftor;
    }
    //    -------------------------------
    public String getProductId() {
        return this.productId.get();
    }

    public void setProductId(String productId) {
        this.productId.set(productId);
    }

    public StringProperty productIdProperty() {
        return this.productId;
    }
    //    -------------------------------
    public String getProductSum() {
        return this.productSum.get();
    }

    public void setProductSum(String productSum) {
        this.productSum.set(productSum);
    }

    public StringProperty productSumProperty() {
        return this.productSum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product(ImageView productImage, String ProductAftor, String productSum, String productId, String productName,User user,String productPutImage,String productLength,String productWidth) {
        this.productName = new SimpleStringProperty(productName);
        this.productSum = new SimpleStringProperty(productSum);
        this.productImage = new SimpleObjectProperty(productImage);
        this.productAftor = new SimpleStringProperty (ProductAftor);
        this.productId = new SimpleStringProperty(productId);
        this.productLength = new SimpleStringProperty(productLength);
        this.productWidth = new SimpleStringProperty(productWidth);
        this.user = user;
        this.productPutImage = new SimpleStringProperty(productPutImage);
    }

    public static void addProduct(Product product) {

    }
    public Product() {

    }
}


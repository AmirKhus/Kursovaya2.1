package sample;

import javafx.scene.image.ImageView;


public class Product {
    private String productName;
    private String productAftor;
    private String ProductId;
    private String productSum;
    private User user;
//    private ImageView productImage;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAftor() {
        return productAftor;
    }

    public void setProductAftor(String productAftor) {
        this.productAftor = productAftor;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductSum() {
        return productSum;
    }

    public void setProductSum(String productSum) {
        this.productSum = productSum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product(String ProductAftor, String productSum, String ProductId, String productName,User user) {
        this.productName = productName;
        this.productSum = productSum;
//        this.productImage = productImage;
        this.productAftor = ProductAftor;
        this.ProductId = ProductId;
        this.user = user;
    }


    public Product() {

    }
}


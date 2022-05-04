package gov.iti.jets.presentation.dtos;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserCart {

    private int cartProductId;

    private String productName;

    private int totalPrice;

    private int quantity;

    public UserCart() {
    }

    public UserCart(int cartProductId, String productName, int totalPrice, int quantity) {
        this.cartProductId = cartProductId;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

   

    public int getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(int cartProductId) {
        this.cartProductId = cartProductId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "UserCart [cartProductId=" + cartProductId + ", productName=" + productName + ", quantity=" + quantity
                + ", totalPrice=" + totalPrice + "]";
    }
}

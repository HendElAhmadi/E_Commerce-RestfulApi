package gov.iti.jets.dtos;



import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CartDto {
    
    private int cartUserId;

    private int cartProductId;

  
    private int totalPrice;

  

    private int quantity;

    public CartDto(int cartUserId, int cartProductId, int totalPrice, int quantity) {
        this.cartUserId = cartUserId;
        this.cartProductId = cartProductId;
        this.totalPrice = totalPrice;
      
        this.quantity = quantity;
    }

    public CartDto() {
    }

    public int getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(int cartUserId) {
        this.cartUserId = cartUserId;
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

    @Override
    public String toString() {
        return "CartDto [cartProductId=" + cartProductId + ", cartUserId=" + cartUserId + ", quantity=" + quantity
                + ", totalPrice=" + totalPrice + "]";
    }

  

   
}

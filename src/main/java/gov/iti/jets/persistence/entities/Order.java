package gov.iti.jets.persistence.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;




@Entity
@Table(name = "orders", catalog = "ecommerce_schema")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id",length = 10)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "total_price")
    private int totalPrice;

 

    public Order() {
    }

    public Order(User user, int totalPrice) {
    
        this.user = user;
        this.totalPrice = totalPrice;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonbTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", totalPrice=" + totalPrice + ", user=" + user.toString() + "]";
    }

   

    

   

   
}

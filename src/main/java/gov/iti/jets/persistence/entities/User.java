package gov.iti.jets.persistence.entities;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users", catalog = "ecommerce_schema")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",length = 10)
    
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name="user_name")
    private String userName;

    
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private double wallet;
   
    private String password;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    
    private List<CartProducts> cartProductsList = new ArrayList<>();

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
   
    private List<Order> orderList = new ArrayList<>();

    public User(){

    }
    

    public User(int id, UserType userType, String userName, String email, String phoneNumber, double wallet,
            String password) {
        this.id = id;
        this.userType = userType;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;
        this.password = password;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public List<CartProducts> getCartProductsList() {
        return cartProductsList;
    }

    public void setCartProductsList(List<CartProducts> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }

    
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", password=" + password + ", phoneNumber=" + phoneNumber
                + ", userName=" + userName + ", userType=" + userType + ", wallet=" + wallet + "]";
    }

    
}

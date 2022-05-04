package gov.iti.jets.dtos;

import gov.iti.jets.persistence.entities.UserType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDto {
   
    
    private int id;


    private UserType userType;

    
    private String userName;

    private String email;


    private String phoneNumber;

    private double wallet;
   
    private String password;


    public UserDto() {
    }

    public UserDto(int id, UserType userType, String userName, String email, String phoneNumber, double wallet,
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

    @Override
    public String toString() {
        return "UserDto [email=" + email + ", id=" + id + ", password=" + password + ", phoneNumber=" + phoneNumber
                + ", userName=" + userName + ", userType=" + userType + ", wallet=" + wallet + "]";
    } 
}

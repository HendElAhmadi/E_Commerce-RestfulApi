package gov.iti.jets.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class UserWalletDto {

    Double userWallet;

    public UserWalletDto(Double userWallet) {
        this.userWallet = userWallet;
    }

    public UserWalletDto() {
    }

    public Double getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(Double userWallet) {
        this.userWallet = userWallet;
    }

    @Override
    public String toString() {
        return "UserWalletDto [userWallet=" + userWallet + "]";
    }

    
    
}

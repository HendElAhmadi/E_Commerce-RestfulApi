package gov.iti.jets.dtos;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto {

    @XmlElement
    private int id;

    @XmlTransient
    @JsonbTransient
    private UserDto userDto;

    @XmlElement
    private int totalPrice;

    public OrderDto() {
    }

    public OrderDto(int id, UserDto userDto, int totalPrice) {
        this.id = id;
        this.userDto = userDto;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDto [id=" + id + ", totalPrice=" + totalPrice + ", userDto=" + userDto.hashCode() + "]";
    }
}

package gov.iti.jets.dtos;


import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class OrderDto {

    private int id;

   
    private UserDto userDto;

    
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

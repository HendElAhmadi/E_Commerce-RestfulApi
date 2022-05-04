package gov.iti.jets.presentation.dtos;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.Category;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto {
    
   @XmlElement
    private Integer id;

    @XmlElement
    private String name;

    @XmlElement
    private String description;

    @XmlElement
    private int quantity;

    @XmlElement
    private int price;

    @XmlTransient
    private List<Category> categories = new ArrayList<Category>();

    public ProductDto() {
    }

    public ProductDto(String name, String description, int quantity, List<Category> categories) {

        this.name = name;
        this.description = description;

        this.quantity = quantity;

        this.categories = categories;

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

   

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto [categories=" + categories.toString() + ", description=" + description + ", id=" + id + ", name=" + name
                + ", price=" + price + ", quantity=" + quantity + "]";
    }

    
}

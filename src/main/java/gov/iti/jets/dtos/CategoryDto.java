package gov.iti.jets.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoryDto {
    
    private Integer id;

   
    private String value;

   
    private String description;

    
    private List<ProductDto> products = new ArrayList<ProductDto>();


    public CategoryDto(Integer id, String value, String description, List<ProductDto> products) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.products = products;
    }


    public CategoryDto() {
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public List<ProductDto> getProducts() {
        return products;
    }


    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "CategoryDto [description=" + description + ", id=" + id + ", products=" + products.toString() + ", value=" + value
                + "]";
    }

   
}

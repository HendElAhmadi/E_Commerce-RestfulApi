package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.presentation.dtos.CategoryDto;
import gov.iti.jets.presentation.dtos.ProductDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("products")
public class ProductController {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @GET
    @Path("*")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllProducts() {

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p", Product.class);
        try {
            List<Product> productList = query.getResultList();
            ProductDto productDto;
            List<ProductDto> productDtoList = new ArrayList<ProductDto>();
            for (Product product : productList) {
                productDto = new ProductDto();

                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setCategories(product.getCategories());
                productDto.setDescription(product.getDescription());
                productDto.setPrice(product.getPrice());
                productDtoList.add(productDto);
            }

            GenericEntity<List<ProductDto>> entity = new GenericEntity<List<ProductDto>>(productDtoList) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no products!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @GET
    @Path("{pid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getProduct(@PathParam("pid") int id) {

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.id= :id ", Product.class)
                .setParameter("id", id);
        try {
            Product product = query.getSingleResult();

            ProductDto productDto = new ProductDto();

            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCategories(product.getCategories());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());

            GenericEntity<ProductDto> entity = new GenericEntity<ProductDto>(productDto) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no products!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getProductByName(@QueryParam("id") int id, @QueryParam("name") String name) {

        TypedQuery<Product> query = entityManager
                .createQuery("select p from Product p where p.id= :id and p.name= :name", Product.class)
                .setParameter("id", id)
                .setParameter("name", name);
        try {
            Product product = query.getSingleResult();
            ProductDto productDto = new ProductDto();

            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCategories(product.getCategories());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            GenericEntity<ProductDto> entity = new GenericEntity<ProductDto>(productDto) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no products!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @GET
    @Path("{pid}/categories")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCategories(@PathParam("pid") Integer id) {

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.id= :id ", Product.class)
                .setParameter("id", id);
        try {
            Product product = query.getSingleResult();

            List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
            for (Category category : product.getCategories()) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setDescription(category.getDescription());
                categoryDto.setValue(category.getValue());

                categoryDtoList.add(categoryDto);
            }

            GenericEntity<List<CategoryDto>> entity = new GenericEntity<List<CategoryDto>>(categoryDtoList) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no categoryList!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createProduct(ProductDto productDto) {

        TypedQuery<Product> query = entityManager
                .createQuery("select p from Product p where p.name= :name ", Product.class)
                .setParameter("name", productDto.getName());
        if (query.getResultList().size() != 0) {
            return "Product already exists";

        }
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setCategories(productDto.getCategories());
        product.setPrice(productDto.getPrice());
        entityManager.persist(product);
        entityTransaction.commit();
        System.out.println(product);
        System.out.println("productDto = " + productDto);
        entityManager.close();

        return "Product is created successfully";

    }

}

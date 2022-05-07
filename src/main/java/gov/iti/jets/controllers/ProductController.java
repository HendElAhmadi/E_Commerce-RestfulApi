package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entitiesservices.QueryService;
import gov.iti.jets.persistence.entitiesservices.QueryServiceImpl;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.dtos.CategoryDto;
import gov.iti.jets.dtos.ProductDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
    private QueryService queryService = new QueryServiceImpl();

    @GET
    @Path("*")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllProducts() {

        TypedQuery<Product> query = queryService.getAllProducts(entityManager);

        List<Product> productList = query.getResultList();
        ProductDto productDto;
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        for (Product product : productList) {
            productDto = new ProductDto();

            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCategories(product.getCategories());
            productDto.setDescription(product.getDescription());
            productDto.setQuantity(product.getQuantity());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
        }

        if (productDtoList.size() == 0) {
            GenericEntity<String> message = new GenericEntity<String>("There are no products!") {
            };
            return Response.ok().entity(message).build();
        }

        GenericEntity<List<ProductDto>> entity = new GenericEntity<List<ProductDto>>(productDtoList) {
        };
        return Response.ok().entity(entity).build();

    }

    @GET
    @Path("{pid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getProduct(@PathParam("pid") int id) {

        TypedQuery<Product> query = queryService.getProductById(entityManager, id);
        try {
            Product product = query.getSingleResult();

            ProductDto productDto = new ProductDto();

            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCategories(product.getCategories());
            productDto.setDescription(product.getDescription());
            productDto.setQuantity(product.getQuantity());
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
    public Response getProductByName(@QueryParam("name") String name) {

        TypedQuery<Product> query = queryService.getProductByName(entityManager, name);
        try {
            Product product = query.getSingleResult();
            ProductDto productDto = new ProductDto();

            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCategories(product.getCategories());
            productDto.setDescription(product.getDescription());
            productDto.setQuantity(product.getQuantity());
            productDto.setPrice(product.getPrice());
            GenericEntity<ProductDto> entity = new GenericEntity<ProductDto>(productDto) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no product with this name!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @GET
    @Path("{pid}/categories")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCategories(@PathParam("pid") Integer id) {

        try {

            TypedQuery<Product> query = queryService.getProductById(entityManager, id);


            Product product = query.getSingleResult();

            List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
            for (Category category : product.getCategories()) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setDescription(category.getDescription());
                categoryDto.setValue(category.getValue());

                categoryDtoList.add(categoryDto);
            }

            if (categoryDtoList.size() == 0) {
                GenericEntity<String> message = new GenericEntity<String>("There is no categoryList!") {
                };
                return Response.ok().entity(message).build();
            }

            GenericEntity<List<CategoryDto>> entity = new GenericEntity<List<CategoryDto>>(categoryDtoList) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {

            GenericEntity<String> message = new GenericEntity<String>("There is no product with this id") {
                };
                return Response.ok().entity(message).build();
        }

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createProduct(ProductDto productDto) {
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = queryService.getProductByName(entityManager2, productDto.getName());
        
        if (query.getResultList().size() != 0) {
            return "Product already exists";

        }
        EntityTransaction entityTransaction = entityManager2.getTransaction();
        entityTransaction.begin();
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setCategories(productDto.getCategories());
        product.setPrice(productDto.getPrice());
        entityManager2.persist(product);
        entityTransaction.commit();
        System.out.println(product);
        System.out.println("productDto = " + productDto);
        entityManager2.close();

        return "Product is created successfully";

    }

    @DELETE
    @Path("{pid}")
    public String deleteProduct(@PathParam("pid") int id) {

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = queryService.getProductById(entityManager2, id);

        try {
            EntityTransaction entityTransaction = entityManager2.getTransaction();
            entityTransaction.begin();
            Product product = query.getSingleResult();

            TypedQuery<CartProducts> query3 = queryService.getCartByProductId(entityManager2, id);
            
            if (query3.getResultList().size() != 0) {
                List<CartProducts>CartProductsList=query3.getResultList();
                List<Integer>cartIds=new ArrayList<>();
                for (CartProducts cartProducts:CartProductsList) {
                    cartIds.add(cartProducts.getCartId().getUserId());
                }
                return "Here are the users Ids who use this product: "+cartIds+"\n ,you should delete theirs carts first";
            }

            entityManager2.remove(product);
            entityTransaction.commit();
            entityManager2.close();
            return "product deleted successfully";
        } catch (Exception e) {

            return "There is no product with this id!";
        }

    }

    @PUT
    @Path("{pid}")
    public String updateProductQuantity(@PathParam("pid") int id, @QueryParam("quantity") int quantity) {

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = queryService.getProductById(entityManager2, id);

        try {
            EntityTransaction entityTransaction = entityManager2.getTransaction();
            entityTransaction.begin();
            Product product = query.getSingleResult();
            if (product.getQuantity() == quantity) {
                return "It is the same product quantity!!";
            }
            product.setQuantity(quantity);
            entityTransaction.commit();
            entityManager2.close();
            return "product updated successfully";
        } catch (Exception e) {

            return "There is no product with this id!";
        }

    }

}

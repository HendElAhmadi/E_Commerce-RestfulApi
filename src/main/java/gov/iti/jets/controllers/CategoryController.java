package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entitiesservices.QueryService;
import gov.iti.jets.persistence.entitiesservices.QueryServiceImpl;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.dtos.CategoryDto;
import gov.iti.jets.dtos.ProductDto;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("categories")
public class CategoryController {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private QueryService queryService =new QueryServiceImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

    public Response getAllCategories() {

        TypedQuery<Category> query = queryService.getAllCategories(entityManager);
        List<Category> categoryList = query.getResultList();
        CategoryDto categoryDto;
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (Category category : categoryList) {
            categoryDto = new CategoryDto();

            categoryDto.setId(category.getId());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setValue(category.getValue());
            List<ProductDto> productDtoList = new ArrayList<ProductDto>();
            for (Product product : category.getProducts()) {
                ProductDto productDto = new ProductDto();

                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setCategories(product.getCategories());
                productDto.setDescription(product.getDescription());
                productDto.setQuantity(product.getQuantity());
                productDto.setPrice(product.getPrice());
                productDtoList.add(productDto);
            }
            categoryDto.setProducts(productDtoList);

            categoryDtoList.add(categoryDto);
        }
        if (categoryDtoList.size() == 0) {
            GenericEntity<String> message = new GenericEntity<String>("There are no categories!") {
            };
            return Response.ok().entity(message).build();
        }

        GenericEntity<List<CategoryDto>> entity = new GenericEntity<List<CategoryDto>>(categoryDtoList) {
        };
        return Response.ok().entity(entity).build();

    }

    @GET
    @Path("{cid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCategory(@PathParam("cid") int id) {

        TypedQuery<Category> query = queryService.getCategoryById(entityManager, id);
        try {
            Category category = query.getSingleResult();
            CategoryDto categoryDto = new CategoryDto();

            categoryDto.setId(category.getId());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setValue(category.getValue());
            List<ProductDto> productDtoList = new ArrayList<ProductDto>();
            for (Product product : category.getProducts()) {
                ProductDto productDto = new ProductDto();

                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setCategories(product.getCategories());
                productDto.setDescription(product.getDescription());
                productDto.setQuantity(product.getQuantity());
                productDto.setPrice(product.getPrice());
                productDtoList.add(productDto);
            }
            categoryDto.setProducts(productDtoList);
            GenericEntity<CategoryDto> entity = new GenericEntity<CategoryDto>(categoryDto) {
            };
            return Response.ok().entity(entity).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no category with this id!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @GET
    @Path("{cid}/products")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getProducts(@PathParam("cid") Integer id) {

        try{
            TypedQuery<Category> query = queryService.getCategoryById(entityManager, id);

        Category category = query.getSingleResult();

        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        for (Product product : category.getProducts()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCategories(product.getCategories());
            productDto.setDescription(product.getDescription());
            productDto.setQuantity(product.getQuantity());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
        }

        
        GenericEntity<List<ProductDto>> entity = new GenericEntity<List<ProductDto>>(productDtoList) {
        };
        return Response.ok().entity(entity).build();
    }catch(Exception e){
        GenericEntity<String> message = new GenericEntity<String>("There are no products!") {
            };
            return Response.ok().entity(message).build();
    }

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createCategory(CategoryDto categoryDto) {

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        TypedQuery<Category> query = queryService.getCategoryValue(entityManager2, categoryDto.getValue());
        if (query.getResultList().size() != 0) {
            return "Category already exists";

        }

        EntityTransaction entityTransaction = entityManager2.getTransaction();
        entityTransaction.begin();
        Category category = new Category();
        category.setValue(categoryDto.getValue());
        category.setDescription(categoryDto.getDescription());

        entityManager2.persist(category);
        entityTransaction.commit();
        System.out.println(category);
        System.out.println("categoryDto = " + categoryDto);
        entityManager2.close();

        return "Category is created successfully";

    }

}

package gov.iti.jets.persistence.entitiesservices;

import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public interface QueryService {
    
    public TypedQuery<User> getAllUsers(EntityManager entityManager );
    public TypedQuery<User> getUserById(EntityManager entityManager , int userId);
    public TypedQuery<User> getUserEmail(EntityManager entityManager ,String email);

    public TypedQuery<Category> getAllCategories(EntityManager entityManager );
    public TypedQuery<Category> getCategoryById(EntityManager entityManager ,int id );
    public TypedQuery<Category> getCategoryValue(EntityManager entityManager ,String value);

    public TypedQuery<Product> getAllProducts(EntityManager entityManager );
    public TypedQuery<Product> getProductById(EntityManager entityManager ,int id );
    public TypedQuery<Product> getProductByName(EntityManager entityManager ,String name);

    public TypedQuery<Order> getAllOrders(EntityManager entityManager );
    public TypedQuery<Order> getOrderByUserId(EntityManager entityManager , int userId);

    public TypedQuery<CartProducts> getAllCarts(EntityManager entityManager );
    public TypedQuery<CartProducts> getCartByUserId(EntityManager entityManager , int userId);
    public TypedQuery<CartProducts> getCartByProductId(EntityManager entityManager , int pId);
    public TypedQuery<CartProducts> getCartByCartId(EntityManager entityManager , CartId cartId);
    public TypedQuery<CartProducts> getCartByUserAndProductIds(EntityManager entityManager ,int userId,int pId);
}

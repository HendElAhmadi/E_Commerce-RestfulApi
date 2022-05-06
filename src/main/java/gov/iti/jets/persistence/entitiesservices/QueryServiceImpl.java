package gov.iti.jets.persistence.entitiesservices;

import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Category;
import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class QueryServiceImpl implements QueryService{

    @Override
    public TypedQuery<User> getAllUsers(EntityManager entityManager) {
       
        return entityManager.createQuery("select u from User u", User.class);
    }

    @Override
    public TypedQuery<User> getUserById(EntityManager entityManager, int id) {
        
        return entityManager.createQuery("select u from User u where u.id= :id ", User.class)
        .setParameter("id", id);

    }

    

    @Override
    public TypedQuery<User> getUserEmail(EntityManager entityManager, String email) {
        
        return entityManager.createQuery("select u from User u where u.email= :email ", User.class)
        .setParameter("email", email);
    }

    @Override
    public TypedQuery<Category> getAllCategories(EntityManager entityManager) {
        
        return entityManager.createQuery("select c from Category c", Category.class);
        
    }

    @Override
    public TypedQuery<Category> getCategoryById(EntityManager entityManager, int id) {
        
        return entityManager
        .createQuery("select c from Category c where c.id= :id ", Category.class)
        .setParameter("id", id);
    }

    @Override
    public TypedQuery<Category> getCategoryValue(EntityManager entityManager, String value) {
        
        return entityManager
        .createQuery("select c from Category c where c.value= :value ", Category.class)
        .setParameter("value",value);
    }

    @Override
    public TypedQuery<Product> getAllProducts(EntityManager entityManager) {
       
        return entityManager.createQuery("select p from Product p", Product.class);

    }

    @Override
    public TypedQuery<Product> getProductById(EntityManager entityManager, int id) {
        
        return entityManager.createQuery("select p from Product p where p.id= :id ", Product.class)
        .setParameter("id", id);        
    }

    @Override
    public TypedQuery<Product> getProductByName(EntityManager entityManager, String name) {
        
        return entityManager
        .createQuery("select p from Product p where  p.name= :name", Product.class)
        .setParameter("name", name);
    }

    @Override
    public TypedQuery<Order> getAllOrders(EntityManager entityManager) {
        
        return entityManager.createQuery("select o from Order o", Order.class);

    }

    @Override
    public TypedQuery<Order> getOrderByUserId(EntityManager entityManager, int id) {
        
        return entityManager
        .createQuery("select o from Order o where o.user.id= :id ", Order.class)
        .setParameter("id", id);
    }

    @Override
    public TypedQuery<CartProducts> getAllCarts(EntityManager entityManager) {
        
        return entityManager.createQuery("select C from CartProducts C", CartProducts.class);
        
    }

    @Override
    public TypedQuery<CartProducts> getCartByUserId(EntityManager entityManager, int userId) {
        
        return entityManager
        .createQuery("select C from CartProducts C where C.user.id= :id ", CartProducts.class)
        .setParameter("id", userId);
    }

    @Override
    public TypedQuery<CartProducts> getCartByProductId(EntityManager entityManager , int pId){

        return entityManager
        .createQuery("select C from CartProducts C where C.product.id= :id ", CartProducts.class)
        .setParameter("id", pId);
    }

    @Override
    public TypedQuery<CartProducts> getCartByCartId(EntityManager entityManager , CartId cartId){

        return entityManager
        .createQuery("select c from CartProducts c where c.cartId= :cartId ", CartProducts.class)
        .setParameter("cartId", cartId);
    }

    @Override
    public TypedQuery<CartProducts> getCartByUserAndProductIds(EntityManager entityManager ,int userId,int pId){

        return entityManager
        .createQuery("select C from CartProducts C where C.user.id= :id and C.product.id= :pid",
                CartProducts.class)
        .setParameter("id", userId)
        .setParameter("pid", pId);
    }
    

    
    
}

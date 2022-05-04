package gov.iti.jets;

// import java.time.LocalDate;
// import java.util.ArrayList;

// import java.util.List;


// import gov.iti.jets.persistence.entities.CartId;
// import gov.iti.jets.persistence.entities.CartProducts;
// import gov.iti.jets.persistence.entities.Category;

// import gov.iti.jets.persistence.entities.Order;
// import gov.iti.jets.persistence.entities.Product;
// import gov.iti.jets.persistence.entities.User;
// import gov.iti.jets.persistence.entities.UserType;
// import gov.iti.jets.persistence.util.ManagerFactory;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.EntityManagerFactory;
// import jakarta.persistence.EntityTransaction;


public class App {

    public static void main(String[] args) {

        // EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();

        // EntityManager entityManager = entityManagerFactory.createEntityManager();

        // EntityTransaction entityTransaction = entityManager.getTransaction();
        // entityTransaction.begin();

        // Category category = new Category();
        // category.setValue("showerGel");
        // category.setDescription("Beautiful");

        // entityManager.persist(category);

        // Category category2 = new Category();
        // category2.setValue("eyeCream");
        // category2.setDescription("good");

        // entityManager.persist(category2);

        // Category category3 = new Category();
        // category3.setValue("candle");
        // category3.setDescription("Beaut");

        // entityManager.persist(category3);

        // Category category4 = new Category();
        // category4.setValue("cleanser");
        // category4.setDescription("goody");

        // entityManager.persist(category4);

        // List<Category> myCategoryList = new ArrayList<Category>();
        // myCategoryList.add(category);

        // myCategoryList.add(category2);

        // List<Category> myCategoryList2 = new ArrayList<Category>();
        // myCategoryList2.add(category3);

        // myCategoryList2.add(category4);

        // Product myProduct = new Product();
        // myProduct.setName("Dark");
        // myProduct.setDescription("Floorish");

        // myProduct.setQuantity(20);
        // myProduct.setPrice(200);
        // myProduct.setCategories(myCategoryList);

        // entityManager.persist(myProduct);

        // Product myProduct2 = new Product();
        // myProduct2.setName("RED");
        // myProduct2.setDescription("Strong");

        // myProduct2.setQuantity(10);
        // myProduct2.setPrice(300);
        // myProduct2.setCategories(myCategoryList2);

        // entityManager.persist(myProduct2);

        // User user1 = new User();
        // user1.setUserName("hend");
        // user1.setPassword("1234");
        // user1.setUserType(UserType.ADMIN);
        // user1.setEmail("eee@gmail.com");
        // user1.setPhoneNumber("123456");
        // user1.setWallet(500.0);

        // entityManager.persist(user1);

        // User user2 = new User();
        // user2.setUserName("marwa");
        // user2.setPassword("1233");
        // user2.setUserType(UserType.CLERK);
        // user2.setEmail("eeer@gmail.com");
        // user2.setPhoneNumber("123496");
        // user2.setWallet(300.0);
        // // user2.setCartProductsList(cartProductsList2);

        // entityManager.persist(user2);

        // CartId cartId = new CartId();
        // cartId.setProductId(myProduct.getId());
        // cartId.setUserId(user1.getId());

        // CartId cartId2 = new CartId();
        // cartId2.setProductId(myProduct2.getId());
        // cartId2.setUserId(user2.getId());

        // CartProducts cartProducts = new CartProducts();
        // cartProducts.setCartId(cartId);
        // cartProducts.setQuantity(2);
        // cartProducts.setTotalPrice(myProduct.getPrice()*2);

        // entityManager.persist(cartProducts);

        // CartProducts cartProducts2 = new CartProducts();
        // cartProducts2.setCartId(cartId2);
        // cartProducts2.setQuantity(4);
        // cartProducts2.setTotalPrice(myProduct2.getPrice()*4);

        // entityManager.persist(cartProducts2);

       

        // User user3 = new User();
        // user3.setUserName("hafsa");
        // user3.setPassword("1236");
        // user3.setUserType(UserType.CLIENT);
        // user3.setEmail("efe@gmail.com");
        // user3.setPhoneNumber("123556");
        // user3.setWallet(700.0);
       

        // entityManager.persist(user3);

        // Order order = new Order();
       
        // order.setTotalPrice(300);
        // order.setUser(entityManager.find(User.class, 1));
        // entityManager.persist(order);


        // entityTransaction.commit();
        // entityManager.close();
        // entityManagerFactory.close();

    }
}

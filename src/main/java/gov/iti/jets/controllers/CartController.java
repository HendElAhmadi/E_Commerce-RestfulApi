package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Product;

import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.dtos.CartDto;
import gov.iti.jets.dtos.UserCart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("cart")
public class CartController {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @GET
    @Path("*")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllcarts() {

        TypedQuery<CartProducts> query = entityManager.createQuery("select C from CartProducts C", CartProducts.class);
       try {

           List<CartProducts> cartProductsList = query.getResultList();
        CartDto cartDto;
        List<CartDto> cartDtoList = new ArrayList<CartDto>();
        for (CartProducts cartProducts : cartProductsList) {

            cartDto = new CartDto();

            cartDto.setCartProductId(cartProducts.getCartId().getProductId());
            cartDto.setCartUserId(cartProducts.getCartId().getUserId());
            cartDto.setTotalPrice(cartProducts.getTotalPrice());
            cartDto.setQuantity(cartProducts.getQuantity());

            cartDtoList.add(cartDto);
        }

        GenericEntity<List<CartDto>> entity = new GenericEntity<List<CartDto>>(cartDtoList) {
        };
        return Response.ok().entity(entity).build();

       } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no carts!") {
            };
            return Response.ok().entity(message).build();
       }
        

    }

    @GET
    @Path("{uid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUserCart(@PathParam("uid") int userId) {

        TypedQuery<CartProducts> query = entityManager.createQuery("select C from CartProducts C", CartProducts.class);
        
        List<CartProducts> cartProductsList = query.getResultList();
        UserCart userCart;
        List<UserCart> userCartList = new ArrayList<UserCart>();
        for (CartProducts cartProducts : cartProductsList) {
            if (cartProducts.getCartId().getUserId() == userId) {

                userCart = new UserCart();

                userCart.setCartProductId(cartProducts.getCartId().getProductId());

                userCart.setProductName(cartProducts.getProduct().getName());
                userCart.setQuantity(cartProducts.getQuantity());
                userCart.setTotalPrice(cartProducts.getTotalPrice());
                userCartList.add(userCart);
            }

        }
        if(userCartList.size() ==0){
            
            GenericEntity<String> message = new GenericEntity<String>("Cart is Empty!") {
            };
            return Response.ok().entity(message).build();
        }

        GenericEntity<List<UserCart>> entity = new GenericEntity<List<UserCart>>(userCartList) {
        };
        return Response.ok().entity(entity).build();

    }

    @POST
    @Path("{uid}")
    public String addToCart(@PathParam("uid") int userId, @QueryParam("name") String productName,
            @QueryParam("quantity") int quantity) {

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();       
        TypedQuery<Product> query = entityManager2
                .createQuery("select p from Product p where p.name= :name ", Product.class)
                .setParameter("name", productName);

        Product product = query.getSingleResult();

        int productQuantity = product.getQuantity();

        if (quantity > productQuantity) {

            return " Product quantity is less than the demanded!!";
        }

        EntityTransaction entityTransaction = entityManager2.getTransaction();
        entityTransaction.begin();

        CartId cartId = new CartId();
        cartId.setProductId(product.getId());
        cartId.setUserId(userId);

        TypedQuery<CartProducts> query2 = entityManager2
                .createQuery("select c from CartProducts c where c.cartId= :cartId ", CartProducts.class)
                .setParameter("cartId", cartId);
       
        if (query2.getResultList().size() != 0) {
            
            CartProducts cartProducts = query2.getResultList().get(0);
            if(cartProducts.getQuantity()==quantity){
                return "your product already exist with the same quantiy";
            }
            setUserCart(cartProducts, product, quantity);
            entityManager2.persist(cartProducts);
            entityTransaction.commit();
            entityManager2.close();

            return "Product is successfully added to your cart!!";

        }

        CartProducts cartProducts = new CartProducts();
        cartProducts.setCartId(cartId);
        setUserCart(cartProducts, product, quantity);
        entityManager2.persist(cartProducts);
        entityTransaction.commit();
        entityManager2.close();

        return "Product is successfully added to your cart!!";

    }


    private void setUserCart(CartProducts cartProducts,Product product, int quantity){
        cartProducts.setQuantity(quantity);
        cartProducts.setTotalPrice(product.getPrice() * quantity);

       

        
    }

}

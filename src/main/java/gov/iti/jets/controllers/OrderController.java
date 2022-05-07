package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.entitiesservices.QueryService;
import gov.iti.jets.persistence.entitiesservices.QueryServiceImpl;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.dtos.OrderDto;
import gov.iti.jets.dtos.UserDto;
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

import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("orders")
public class OrderController {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private QueryService queryService = new QueryServiceImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllOrders() {

        TypedQuery<Order> query = queryService.getAllOrders(entityManager);
        List<Order> orderList = query.getResultList();
        OrderDto orderDto;
        List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
        for (Order order : orderList) {
            orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setTotalPrice(order.getTotalPrice());
            User user = order.getUser();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserType(user.getUserType());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setWallet(user.getWallet());
            userDto.setPassword(user.getPassword());
            orderDto.setUserDto(userDto);
            orderDtoList.add(orderDto);
        }
        if (orderDtoList.size() == 0) {
            GenericEntity<String> message = new GenericEntity<String>("There are no orders!") {
            };

            return Response.ok().entity(message).build();

        }

        GenericEntity<List<OrderDto>> entity = new GenericEntity<List<OrderDto>>(orderDtoList) {
        };

        return Response.ok().entity(entity).build();

    }

    @GET
    @Path("{uid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getOrderById(@PathParam("uid") int userId) {

        try {
            TypedQuery<Order> query = queryService.getOrderByUserId(entityManager, userId);
            Order order = query.getSingleResult();
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setTotalPrice(order.getTotalPrice());
            User user = order.getUser();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserType(user.getUserType());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setWallet(user.getWallet());
            userDto.setPassword(user.getPassword());
            orderDto.setUserDto(userDto);
            GenericEntity<OrderDto> entity = new GenericEntity<OrderDto>(orderDto) {
            };

            return Response.ok().entity(entity).build();
        } catch (Exception e) {

            GenericEntity<String> message = new GenericEntity<String>("There is no order!") {
            };

            return Response.ok().entity(message).build();
        }

    }

    @POST
    @Path("{uid}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String makeOrder(@PathParam("uid") int userId) {

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        TypedQuery<User> query1 = queryService.getUserById(entityManager2, userId);
        if (query1.getResultList().size() == 0) {
            return "user doesn't exist!!";
        }
        TypedQuery<Order> query = queryService.getOrderByUserId(entityManager2, userId);
        if (query.getResultList().size() != 0) {

            return "order already exists check it out by get Method";
        }

        List<CartProducts> cartProductsList = getUserCart(entityManager2);
        int totalPrice = 0;
        for (CartProducts cartProducts : cartProductsList) {
            if (cartProducts.getCartId().getUserId() == userId) {

                int productQuantity = cartProducts.getProduct().getQuantity();
                if (productQuantity == 0) {

                    return "we are sorry but the product is out of stock !!";
                }
                if (productQuantity < cartProducts.getQuantity()) {

                    return "the" + cartProducts.getProduct().getName() + " product quantity\n" +
                            "in stock is only : " + productQuantity;
                }
                totalPrice += cartProducts.getTotalPrice();
            }

        }
        if (totalPrice == 0) {

            return "cart is empty";
        }
        EntityTransaction entityTransaction = entityManager2.getTransaction();
        entityTransaction.begin();

        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setUser(entityManager2.find(User.class, userId));
        entityManager2.persist(order);
        entityTransaction.commit();

        entityManager2.close();
        return "Order is created successfully";

    }

    @PUT
    @Path("{uid}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String updateOrder(@PathParam("uid") int userId) {

        TypedQuery<User> query1 = queryService.getUserById(entityManager, userId);
        if (query1.getResultList().size() == 0) {
            return "user doesn't exist!!";
        }
        TypedQuery<Order> query = queryService.getOrderByUserId(entityManager, userId);
        if (query.getResultList().size() == 0) {

            return "order doesn't exist";
        }

        List<CartProducts> cartProductsList = getUserCart(entityManager);
        int totalPrice = 0;
        for (CartProducts cartProducts : cartProductsList) {
            if (cartProducts.getCartId().getUserId() == userId) {

                int productQuantity = cartProducts.getProduct().getQuantity();
                if (productQuantity == 0) {

                    return "we are sorry but the product is out of stock !!";
                }
                if (productQuantity < cartProducts.getQuantity()) {

                    return "the" + cartProducts.getProduct().getName() + " product quantity\n" +
                            "in stock is only : " + productQuantity;
                }
                totalPrice += cartProducts.getTotalPrice();
            }

        }
        if (totalPrice == 0) {

            return "cart is empty";
        }
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Order order = query.getSingleResult();
        order.setTotalPrice(totalPrice);
        entityManager.persist(order);
        entityTransaction.commit();

        return "Order is updated successfully";

    }

    @POST
    @Path("{uid}/checkout")
    public String checkout(@PathParam("uid") int userId) {

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        TypedQuery<Order> query = queryService.getOrderByUserId(entityManager2, userId);
        if (query.getResultList().size() == 0) {

            return "Please place your order";
        }

        Order order = query.getSingleResult();
        User user = order.getUser();

        if (user.getWallet() < order.getTotalPrice()) {

            return "user doesn't have enough money";
        }

        List<CartProducts> cartProductsList = getUserCart(entityManager2);
        EntityTransaction entityTransaction = entityManager2.getTransaction();
        entityTransaction.begin();
        int i = 0;
        for (CartProducts cartProducts : cartProductsList) {

            if (cartProducts.getCartId().getUserId() == userId) {

                TypedQuery<Product> query2 = queryService.getProductById(entityManager2,
                        cartProducts.getCartId().getProductId());

                Product product = query2.getSingleResult();
                product.setQuantity(product.getQuantity() - cartProducts.getQuantity());
                entityManager2.persist(product);
                entityManager2.remove(cartProducts);
                i++;
            }
        }
        if (i == 0) {

            return "there is no cart !!";
        }
        user.setWallet(user.getWallet() - order.getTotalPrice());
        entityManager2.persist(user);
        entityManager2.remove(order);
        entityTransaction.commit();
        entityManager2.close();
        return "Transaction done successfully";

    }

    @DELETE
    @Path("{uid}")
    public String deleteOrder(@PathParam("uid") int userId) {

        try {
            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            TypedQuery<Order> query = queryService.getOrderByUserId(entityManager2, userId);

            EntityTransaction entityTransaction = entityManager2.getTransaction();
            entityTransaction.begin();
            Order order = query.getSingleResult();

            entityManager2.remove(order);
            entityTransaction.commit();
            entityManager2.close();
            return "user order deleted successfully";
        } catch (Exception e) {

            return "There is no order to delete!";
        }

    }

    private List<CartProducts> getUserCart(EntityManager entityManager) {
        TypedQuery<CartProducts> query2 = queryService.getAllCarts(entityManager);
        List<CartProducts> cartProductsList = query2.getResultList();

        return cartProductsList;
    }

}

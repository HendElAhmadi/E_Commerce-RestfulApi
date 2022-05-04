package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.presentation.dtos.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
public class UserController {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllUsers() {

        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        try {

            List<User> userList = query.getResultList();
            UserDto userDto;
            List<UserDto> userDtoList = new ArrayList<UserDto>();
            for (User user : userList) {
                userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUserType(user.getUserType());
                userDto.setUserName(user.getUserName());
                userDto.setEmail(user.getEmail());
                userDto.setPhoneNumber(user.getPhoneNumber());
                userDto.setWallet(user.getWallet());
                userDto.setPassword(user.getPassword());
                userDtoList.add(userDto);
            }

            GenericEntity<List<UserDto>> entity = new GenericEntity<List<UserDto>>(userDtoList) {
            };
            return Response.ok().entity(entity).build();

        } catch (Exception e) {

            GenericEntity<String> message = new GenericEntity<String>("There is no users!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @GET
    @Path("{uid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUserById(@PathParam("uid") int id) {

        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id= :id ", User.class)
                .setParameter("id", id);

        try {
            User user = query.getSingleResult();

            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserType(user.getUserType());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setWallet(user.getWallet());
            userDto.setPassword(user.getPassword());

            System.out.print(user);

            GenericEntity<UserDto> message = new GenericEntity<UserDto>(userDto) {
            };
            return Response.ok().entity(message).build();
        } catch (Exception e) {
            GenericEntity<String> message = new GenericEntity<String>("There is no user with this id!") {
            };
            return Response.ok().entity(message).build();
        }

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createUser(UserDto userDto) {

        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email= :email ", User.class)
                .setParameter("email", userDto.getEmail());
        if (query.getResultList().size() != 0) {
            return "email already exists";

        }

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user1 = new User();
        user1.setUserName(userDto.getUserName());
        user1.setPassword(userDto.getPassword());
        user1.setUserType(userDto.getUserType());
        user1.setEmail(userDto.getEmail());
        user1.setPhoneNumber(userDto.getPhoneNumber());
        user1.setWallet(userDto.getWallet());

        entityManager.persist(user1);
        entityTransaction.commit();
        System.out.println(user1);
        System.out.println("userDto = " + userDto);
        entityManager.close();

        return "user created successfully";
    }

}

package gov.iti.jets.persistence.util;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

public class ManagerFactory {
    
    private static final EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce_schema");
    
    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    } 
}

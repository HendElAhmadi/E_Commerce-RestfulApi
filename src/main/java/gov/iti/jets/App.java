package gov.iti.jets;

import gov.iti.jets.persistence.util.ManagerFactory;

import jakarta.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();

       
    }
    
}

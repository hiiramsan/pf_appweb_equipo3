/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sportifydao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author jl4ma
 */
public class SportifyDAO {

     public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_SportifyDAO_jar_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            System.out.println("Database generated successfully!");
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Error generating database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


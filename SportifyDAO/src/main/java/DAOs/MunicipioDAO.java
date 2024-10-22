/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import dominio.Municipio;
import interfaces.IMunicipioDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author jl4ma
 */
public class MunicipioDAO implements IMunicipioDAO{
    
    private EntityManager em;
    private Conexion conexion;

    public MunicipioDAO() {
        this.conexion = new Conexion();
        this.em = conexion.getEntityManager();
    }
    @Override
    public void agregarMunicipio(Municipio municipio) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(municipio);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    @Override
    public void actualizarMunicipio(Municipio municipio) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(municipio);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    @Override
    public void eliminarMunicipio(Municipio municipio) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Municipio municipioToRemove = em.find(Municipio.class, municipio.getIdMunicipio());
            if (municipioToRemove != null) {
                em.remove(municipioToRemove);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    @Override
    public Municipio consultarMunicipio(int id) {
        return em.find(Municipio.class, id);
    }
}

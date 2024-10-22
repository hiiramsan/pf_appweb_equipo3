/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import dominio.Estado;
import dominio.Municipio;
import interfaces.IEstadoDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author jl4ma
 */
public class EstadoDAO implements IEstadoDAO{
    
    private EntityManager em;
    private Conexion conexion;

    public EstadoDAO() {
        this.conexion = new Conexion();
        this.em = conexion.getEntityManager();
    }
    @Override
    public void agregarEstado(Estado estado) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin(); // Inicia la transacción
            em.persist(estado); // Persiste el nuevo estado
            tx.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // Hace rollback en caso de error
            }
            System.err.println("Error al agregar estado: " + e.getMessage());
        }
    }

    @Override
    public void agregarMunicipio(Estado estado, Municipio municipio) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Aquí podrías añadir lógica para agregar un municipio al estado
            // Por ejemplo, agregar el municipio a una lista de municipios en Estado
            estado.getMunicipios().add(municipio); // Suponiendo que Estado tiene una lista de Municipios
            municipio.setEstado(estado); // Establece la relación en el municipio
            em.persist(municipio); // Persiste el nuevo municipio
            em.merge(estado); // Actualiza el estado para incluir el nuevo municipio
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al agregar municipio: " + e.getMessage());
        }
    }

    @Override
    public void actualizarEstado(Estado estado) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(estado); // Actualiza el estado
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al actualizar estado: " + e.getMessage());
        }
    }

    @Override
    public void eliminarEstado(Estado estado) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            estado = em.find(Estado.class, estado.getIdEstado()); // Busca el estado
            em.remove(estado); // Elimina el estado encontrado
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar estado: " + e.getMessage());
        }
    }

    @Override
    public Estado consultarEstado(int id) {
        try {
            return em.find(Estado.class, id); // Busca y retorna el estado por ID
        } catch (Exception e) {
            System.err.println("Error al consultar estado: " + e.getMessage());
            return null;
        }
    }
}
    
    


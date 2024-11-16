/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import dominio.Estado;
import dominio.Usuario;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jl4ma
 */
public class UsuarioDAO implements IUsuarioDAO {

    private EntityManager em;
    private Conexion conexion;

    public UsuarioDAO() {
        this.conexion = new Conexion();
        this.em = conexion.getEntityManager();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(usuario);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(usuario);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Usuario usuarioToRemove = em.find(Usuario.class, usuario.getIdUsuario());
            if (usuarioToRemove != null) {
                em.remove(usuarioToRemove);
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
    public Usuario consultarUsuario(int id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public Usuario consultarUsuarioPorEmail(String email) {
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
            query.setParameter("correo", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void agregarEstado(Estado estado, Usuario usuario) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            usuario.setEstado(estado); // O cualquier otra lógica para asociar el estado al usuario
            em.merge(usuario);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } catch (RuntimeException e) {
            // Manejo de errores
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
        return usuarios;
    }
}

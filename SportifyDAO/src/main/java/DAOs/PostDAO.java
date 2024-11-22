/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import dominio.Post;
import interfaces.IPostDAO;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author jl4ma
 */
public class PostDAO implements IPostDAO {

    private EntityManager em;
    private Conexion conexion;

    public PostDAO() {
        this.conexion = new Conexion();
        this.em = conexion.getEntityManager();
    }

    @Override
    public void agregarPost(Post post) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(post);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    @Override
    public void actualizarPost(Post post) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(post);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Lanza la excepción para manejarla en otro lugar
        }
    }

    public void eliminarPost(Post post) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Post postToRemove = em.find(Post.class, post.getIdPost());
            if (postToRemove != null) {
                em.remove(postToRemove);
                System.out.println("Post con ID " + post.getIdPost() + " eliminado.");
            } else {
                System.out.println("Post no encontrado para eliminar.");
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Post consultarPost(int id) {
        try {
            return em.find(Post.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void anclarPost(Post post) {
        // Suponiendo que anclarPost solo modifica una propiedad del post
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Post postToUpdate = em.find(Post.class, post.getIdPost());
            if (postToUpdate != null) {
                postToUpdate.setIsAnclado(true);
                em.merge(postToUpdate);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Post> obtenerTodosLosPosts() {
        try {
            TypedQuery<Post> query = em.createQuery(
                    "SELECT p FROM Post p ", Post.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

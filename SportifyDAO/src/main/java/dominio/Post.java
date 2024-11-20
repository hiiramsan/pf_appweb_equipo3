/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;

    @Temporal(TemporalType.DATE)
    private Calendar fechaHoraCreacion;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String contenido;

    @Temporal(TemporalType.DATE)
    private Calendar fechaHoraEdicion;

    @Column(nullable = false)
    private boolean isAnclado;
    
    @Column(length = 255)
    private String foto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
    
    public enum Categoria {
        SOCCER, BASKETBALL, BASEBALL 
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario autor;
    
    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public Calendar getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Calendar fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Calendar getFechaHoraEdicion() {
        return fechaHoraEdicion;
    }

    public void setFechaHoraEdicion(Calendar fechaHoraEdicion) {
        this.fechaHoraEdicion = fechaHoraEdicion;
    }

    public boolean isIsAnclado() {
        return isAnclado;
    }

    public void setIsAnclado(boolean isAnclado) {
        this.isAnclado = isAnclado;
    }
    
    public Usuario getAutor() {
        return autor;
    }
    
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}

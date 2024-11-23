package com.challenge.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Author autor;
    private String idioma;
    private long numeroDeDesacargas;

    //Constructors

    public Book() {
    }

    public Book(DatoBook datoBook) {
        this.titulo = datoBook.titulo();
        this.autor = (datoBook.autor() != null && !datoBook.autor().isEmpty())?
                new Author(datoBook.autor().get(0)):null;
        this.idioma = datoBook.idioma().get(0);
        this.numeroDeDesacargas = datoBook.numeroDeDescargas();
    }

    //Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public long getNumeroDeDesacargas() {
        return numeroDeDesacargas;
    }

    public void setNumeroDeDesacargas(long numeroDeDesacargas) {
        this.numeroDeDesacargas = numeroDeDesacargas;
    }

    //To String

    @Override
    public String toString() {
        return """
                ----- Libro -----
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %s
                -----------------
                """.formatted(titulo, autor.getNombre(), idioma, numeroDeDesacargas);
    }

}

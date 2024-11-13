package com.challenge.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Author autor;
    private String idioma;
    private double numeroDeDesacargas;

    //Constructors

    public Book() {
    }

    public Book (DatoBook datoBook) {
        this.titulo = datoBook.titulo();
        this.autor = datoBook.autor();
        this.idioma = datoBook.idioma();
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

    public double getNumeroDeDesacargas() {
        return numeroDeDesacargas;
    }

    public void setNumeroDeDesacargas(double numeroDeDesacargas) {
        this.numeroDeDesacargas = numeroDeDesacargas;
    }

    //To String

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDesacargas=" + numeroDeDesacargas;
    }

}

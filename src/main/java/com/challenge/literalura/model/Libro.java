package com.challenge.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;
    private String nombreAutor;
    private String idiomas;
    private Integer descargas;
    @ManyToOne
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibros datosLibro) {
        this.titulo = datosLibro.titulo();
        this.descargas = datosLibro.descargas();
        Autor autor = new Autor(datosLibro.autor().get(0));
        this.nombreAutor = autor.getNombre();
        this.idiomas = datosLibro.idiomas().get(0);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "----------------------LIBRO----------------------\n" +
                "   Titulo: " + titulo + "\n" +
                "   Autor: " + (autor != null ? autor.getNombre() : "N/A") + "\n" +
                "   Idiomas: " + idiomas + "\n" +
                "   Numero de Descargas:" + descargas + "\n" +
                "-------------------------------------------------";
    }

}

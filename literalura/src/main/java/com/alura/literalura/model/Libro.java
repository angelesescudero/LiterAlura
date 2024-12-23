package com.alura.literalura.model;


import com.alura.literalura.dto.LibroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")

public class Libro {

    @Id
    private Integer id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne //Un libro un autor
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Lenguaje lenguaje;
    private Integer totalDeDescargas;

    public Libro() {
    }

    public Libro(LibroDTO libroDTO, Autor autor, Lenguaje lenguaje) {

        this.id = libroDTO.id();
        this.titulo = libroDTO.titulo();
        this.autor = autor;
        this.lenguaje = lenguaje;
        this.totalDeDescargas = libroDTO.totalDeDescargas();
    }
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "Sin autor") +
                ", lenguaje=" + lenguaje +
                ", totalDeDescargas=" + totalDeDescargas +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getTotalDeDescargas() {
        return totalDeDescargas;
    }

    public void setTotalDeDescargas(Integer totalDeDescargas) {
        this.totalDeDescargas = totalDeDescargas;
    }
}

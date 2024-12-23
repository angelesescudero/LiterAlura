package com.alura.literalura.repository;

import com.alura.literalura.model.Lenguaje;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);
    List<Libro> findByLenguaje(Lenguaje lenguaje);
    boolean existsByTitulo(String titulo);
}
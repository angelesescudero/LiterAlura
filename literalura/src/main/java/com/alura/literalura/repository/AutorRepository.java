package com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.fechaDeFallecimiento >= :fechaDeFallecimiento")
    List<Autor> fechaDeNacimientoYFallecimiento(@Param("fechaDeFallecimiento") Integer fechaDeFallecimiento);

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :autor, '%'))")
    Optional<Autor> buscarAutorPorNombre(@Param("autor") String autor);

    Autor findByNombreAndFechaDeNacimientoAndFechaDeFallecimiento(String nombre, Integer integer, Integer integer1);

    @Query("SELECT a FROM Autor a")
    List<Autor> mostrarAutoresRegistrados();
}








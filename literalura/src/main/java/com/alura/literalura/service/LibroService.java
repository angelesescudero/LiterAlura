package com.alura.literalura.service;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Lenguaje;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public List<AutorDTO> mostrarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .map(autor -> new AutorDTO(
                        autor.getNombre(),
                        autor.getFechaDeNacimiento(),
                        autor.getFechaDeFallecimiento(),
                        autor.getLibros().stream()
                                .map(libro -> libro.getTitulo())
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Libro> convierteLibro(List<LibroDTO> librosDTO) {
        // Inicializar la lista de libros
        List<Libro> libros = new ArrayList<>();
        System.out.println("LibroDTO recibido: " + librosDTO);


        for (LibroDTO libroDTO : librosDTO) {
            // Obtener el primer autor transformado
            Optional<AutorDTO> primerAutorDTO = libroDTO.autores().stream().findFirst();
            Autor autor = null;

            if (primerAutorDTO.isPresent()) {
                AutorDTO autorDTO = primerAutorDTO.get();

                // Verificar si el autor ya existe en la base de datos (por nombre o ID)
                Optional<Autor> autorExistente = Optional.ofNullable(autorRepository.findByNombreAndFechaDeNacimientoAndFechaDeFallecimiento(autorDTO.nombre(), autorDTO.fechaDeNacimiento(), autorDTO.fechaDeFallecimiento()));
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get(); // Usar el autor existente
                } else {
                    // Crear un nuevo autor y persistirlo
                    autor = new Autor(autorDTO);
                    autorRepository.save(autor);
                }
            }
            // Determinar el lenguaje desde la lista de lenguajes

            Lenguaje enumLenguaje = libroDTO.lenguajes().stream()
                    .map(Lenguaje::fromString)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(Lenguaje.OTRO);
            // Verificar si el libro ya existe
            if (libroRepository.existsByTitulo(libroDTO.titulo())) {
                System.out.println("Libro ya existente: " + libroDTO.titulo());
                continue;
            }
            // Saltar a la siguiente iteración si el libro ya existe
            //  Crear la entidad Libro directamente usando el constructor

            Libro libro = new Libro(libroDTO, autor, enumLenguaje);

            // Persistir la entidad Libro
            libroRepository.save(libro);
            libros.add(libro);
        }
        return libros;
    }


}



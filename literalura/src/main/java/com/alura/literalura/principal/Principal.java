package com.alura.literalura.principal;


import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.dto.ResultadosDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Lenguaje;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final LibroService libroService;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository, LibroService libroService) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.libroService = libroService;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \nElije la opcion del menu que deseas:
                    
                    ********************** MENU **********************
                    1 - Buscar libro por titulo
                    2 - Listar todos los libros registrados
                    3 - Listar los autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Buscar libro por autor
                    0 - Salir
                    **************************************************
                    
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosPorAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    buscarLibroPorAutor();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private List<LibroDTO> getResultadosDTO(String nombre) {
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombre.replace(" ", "+"));
        ResultadosDTO datos = conversor.obtenerDatos(json, ResultadosDTO.class);
        return datos.resultados();
    }


    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();
        List<LibroDTO> libroDTO = getResultadosDTO(nombreLibro);

        if (libroDTO.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }
        // Convertir LibroDTO a Libro
        List<Libro> libros = libroService.convierteLibro(libroDTO);

        // Llamar al merodo implimirLibros para mostrar la lista de libros encontrados
        LibroPrinter.imprimirLibros(libros);
    }
    //Metodo para mostrar  la lista de los libros
    public static class LibroPrinter {

        public static void imprimirLibros(List<Libro> listaLibros) {
            System.out.println("\n**********************\nLibros registrados:");
            listaLibros.forEach(libro -> {
                System.out.println(
                        "\n*********************\nTítulo: " + libro.getTitulo() +
                                "\nIdioma: " + libro.getLenguaje() +
                                "\nTotal de descargas: " + libro.getTotalDeDescargas() +
                                "\nAutor: " + libro.getAutor().getNombre());
            });
        }
    }
    private void mostrarLibrosRegistrados() {
        List<Libro> listaLibros = libroRepository.findAll();
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        // Llamar al metodo imprimirLibros para mostrar la lista de libros
        LibroPrinter.imprimirLibros(listaLibros);
    }
    private void mostrarAutoresRegistrados() {
        List<AutorDTO> listaAutores = libroService.mostrarAutoresRegistrados();
        if (listaAutores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        System.out.println("\n**********************\nAutores registrados:");
        listaAutores.forEach(autor -> {
            System.out.println("\n**********************\nNombre: " + autor.nombre());
            System.out.println("Fecha de Nacimiento: " + autor.fechaDeNacimiento());
            if (autor.fechaDeFallecimiento() != null) {
                System.out.println("Fecha de Fallecimiento: " + autor.fechaDeFallecimiento());
            }
            System.out.println("Libros: " + String.join(", ", autor.titulosLibros()));
        });
    }


    private void buscarLibroPorAutor() {
        System.out.println("Escribe el nombre del autor que deseas buscar: ");
        var nombreAutor = teclado.nextLine();
        List<LibroDTO> libroDTO = getResultadosDTO(nombreAutor);
        // Depuración
        System.out.println("Resultados obtenidos del API: " + libroDTO);

        if (libroDTO.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }
        // Convertir LibroDTO a Libro
        List<Libro> libros = libroService.convierteLibro(libroDTO);

        // Depuración
        System.out.println("Libros convertidos: " + libros);

        // Llamar al metodo imprimirLibros para mostrar la lista de libros encontrados
        LibroPrinter.imprimirLibros(libros);
    }
    private void mostrarAutoresVivosPorAno() {
        System.out.println("Escribe el año para encontrar autores vivos: ");
        int ano = teclado.nextInt();
        teclado.nextLine();

        // Obtener todos los autores de la base de datos
        List<Autor> listaAutores = autorRepository.findAll();

        // Filtrar los autores que estaban vivos en el año especificado
        List<Autor> autoresVivos = listaAutores.stream()
                .filter(autor -> autor.getFechaDeNacimiento() != null
                        && autor.getFechaDeNacimiento() <= ano
                        && (autor.getFechaDeFallecimiento() == null || autor.getFechaDeFallecimiento() >= ano))
                .toList();
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + ano);
            return;
        }
        System.out.println("\n**********************\nAutores vivos en el año " + ano + ":");
        autoresVivos.forEach(autor -> {
            System.out.println( "\nNombre: " + autor.getNombre()
                    + "\nFecha de Nacimiento: " + autor.getFechaDeNacimiento()
                    + (autor.getFechaDeFallecimiento() != null ? "\nFecha de Fallecimiento: "
                    + autor.getFechaDeFallecimiento() : ""));
        });
    }
    private void mostrarLibrosPorIdioma() {
        System.out.println("Escribe el idioma de los libros que deseas buscar:\n" +
                "  \"es - Español\",\n" +
                "  \"en - Inglés\",\n" +
                "  \"it - Italiano\",\n" +
                "  \"fr - Francés\",\n" +
                "  \"pt - Portugués\",\n");

        String idioma = teclado.nextLine();
        Lenguaje lenguaje = Lenguaje.fromString(idioma); // Cambiado a fromString para coincidir con los códigos de idioma

        if (lenguaje == Lenguaje.OTRO) {
            System.out.println("Idioma no válido.");
            return;
        }

        // Obtener libros por idioma del repositorio
        List<Libro> listaLibros = libroRepository.findByLenguaje(lenguaje);

        if (listaLibros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
            return;
        }

        // Llamar al metodo imprimirLibros para mostrar la lista de libros encontrados
        LibroPrinter.imprimirLibros(listaLibros);
    }

}

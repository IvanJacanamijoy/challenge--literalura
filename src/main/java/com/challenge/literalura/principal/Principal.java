package com.challenge.literalura.principal;

import com.challenge.literalura.model.Autor;
import com.challenge.literalura.model.Datos;
import com.challenge.literalura.model.DatosLibros;
import com.challenge.literalura.model.Libro;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import com.challenge.literalura.service.ConsumoAPI;
import com.challenge.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private int opcion = -1;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void iniciar() {
        while (opcion != 0) {
            mostrarMenu();

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine();
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibroRegistrados();
                    break;
                case 3:
                    listarAutoresRegitrados();
                    break;
                case 4:
                    buscarAutoresVivoEnUnYear();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("""
                            ---------------------------------------------------------
                            | Finalizó el programa. ¡Gracias por usar la aplicacion |
                            ---------------------------------------------------------
                            """);
                    break;
                default:
                    System.out.println("Eliga una opcion valida");
                    break;
            }

        }
    }

    private void mostrarMenu() {
        System.out.println("""
                -----------------------------------------
                |        Bienvenido a LiterALURA        |
                |---------------------------------------|
                |        Challenge realizado por        |
                |      Ivan Yesid Rojas Jacanamijoy     |
                |---------------------------------------|
                | Seleccione una opción:                |
                |---------------------------------------|
                | 1. Buscar libro por título            |
                | 2. Listar libros registrados          |
                | 3. Listar autores registrados         |
                | 4. Listar autores vivos en un año     |
                | 5. Listar libros por idioma           |
                | 0. Salir de la aplicación             |
                -----------------------------------------
                """);
    }

    private Datos getDatosLibros() {
        System.out.println("""
                -----------------------------------------
                |Por favor, escribe el nombre del libro |
                |que deseas buscar:                     |
                -----------------------------------------
                """);
        var nombreLibro = teclado.nextLine().toLowerCase().replace(" ", "%20");

        System.out.println("""
                ---------------------------------------------------
                | Consultando la API para obtener datos del libro |
                ---------------------------------------------------
                """);
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro);

        System.out.println("""
                --------------------------------
                | Respuesta de la API obtenida |
                --------------------------------
                """);
        System.out.println(json);
        return conversor.obtenerDatos(json, Datos.class);
    }

    private void buscarLibro() {
        try {
            Datos datos = getDatosLibros();

            Optional<DatosLibros> libros = datos.resultados().stream()
                    .findFirst();

            if (libros.isPresent()) {
                Libro libro = new Libro(libros.get());
                Autor autor = new Autor(libros.get().autor().get(0));

                if (libroRepository.existsByTitulo(libro.getTitulo())) {
                    System.out.println("""
                            ---------------------------------------------------
                            | El libro ya está registrado en la base de datos |
                            ---------------------------------------------------
                            """);
                } else {
                    System.out.println("""
                            ----------------------------------------------------------------
                            | El libro no está en la base de datos. Procesando su registro |
                            ----------------------------------------------------------------
                            """);
                    var autorExistente = autorRepository.findByNombreContainsIgnoreCase(autor.getNombre());
                    if (autorExistente.isPresent()) {
                        var autorDelLibro = autorExistente.get();
                        libro.setAutor(autorDelLibro);
                        libroRepository.save(libro);
                        System.out.println("""
                                -------------------------------------------
                                | El autor ya eciste en la base de datos  |
                                | Se ha vinculado el libro con este autor |
                                -------------------------------------------
                                """);
                    } else {
                        autor.setListaLibros(libro);
                        autorRepository.save(autor);
                        libroRepository.save(libro);
                        System.out.println("""
                                ----------------------------------------------------------------
                                | El autor y el libro han sido registrados en la base de datos |
                                ----------------------------------------------------------------
                                """);
                    }
                    System.out.println("""
                            --------------------------------------------
                            | El libro ha sido registrado exitosamente |
                            --------------------------------------------
                            """);
                    System.out.println(libro);
                }
            } else {
                System.out.println("""
                        ------------------------------------------------------------
                        | No se encontró ningún libro que coincida con la búsqueda |
                        ------------------------------------------------------------
                        """);
            }
        } catch (Exception e) {
            System.out.println("Se produjo un error al insertar el libro: " + e.getMessage());
        }
    }

    public void listarLibroRegistrados() {
        System.out.println("""
                ------------------------------------------
                | Libros registrados en la Base de datos |
                ------------------------------------------
                """);
        var libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    public void listarAutoresRegitrados() {
        System.out.println("""
                -------------------------------------------
                | Autores registrados en la Base de datos |
                -------------------------------------------
                """);
        var autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    public void buscarAutoresVivoEnUnYear() {
        System.out.println("""
                --------------------------------------
                | Ingrese el año a buscar autor vivo |
                --------------------------------------
                """);
        var year = teclado.nextInt();
        var autoresVivos = autorRepository.autoresVivoDuranteEseYear(year);
        autoresVivos.forEach(System.out::println);
    }

    public void buscarLibroPorIdioma() {
        System.out.println("""
                -----------------------------------
                |   Ingrese el idioma a buscar:   |
                |   en - Ingles                   |
                |   es - Español                  |
                -----------------------------------
                """);
        var idioma = teclado.nextLine();
        var listaDeLibros = libroRepository.librosPorIdioma(idioma);
        if (listaDeLibros.isEmpty()) {
            System.out.println("""
                    ---------------------------------------------
                    | No se encontró ningún libro en ese idioma |
                    ---------------------------------------------
                    """);
        } else {
            listaDeLibros.forEach(System.out::println);
        }
    }
}
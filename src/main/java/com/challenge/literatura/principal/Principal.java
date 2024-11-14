package com.challenge.literatura.principal;

import com.challenge.literatura.model.Author;
import com.challenge.literatura.model.Book;
import com.challenge.literatura.model.DatoBook;
import com.challenge.literatura.model.Result;
import com.challenge.literatura.repository.IAuthorRepository;
import com.challenge.literatura.repository.IBookRepository;
import com.challenge.literatura.service.ConvierteDatos;
import com.challenge.literatura.service.ServiceApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ServiceApi serviceApi = new ServiceApi();
    private final String URL = "https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private IBookRepository bookRep;
    private IAuthorRepository authorRep;
    private List<Book> libros = new ArrayList<>();
    List<Author> authors = new ArrayList<>();

    public Principal(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        bookRep = bookRepository;
        authorRep = authorRepository;
    }

    public void menu() {
        var option = -1;
        while (option != 0) {
            String menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - salir""";
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarautoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                case 0:
                    System.out.println("Cerrando Aplicacion");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    public Book getDatosBook(){
        System.out.println("Introduzca el titulo: ");
        var titulo = scanner.nextLine().toLowerCase();
        var json = serviceApi.obtenerDatos(URL + titulo.replaceAll(" ", "%20"));
        Result result = convierteDatos.obtenerDatos(json, Result.class);
        DatoBook datoBook = result.resultBooks().get(0);
        return new Book(datoBook);
    }

    public void buscarLibroPorTitulo() {
        Book book = getDatosBook();
        bookRep.save(book);
        System.out.println(book);
    }

    public void listarLibrosRegistrados(){
        libros = bookRep.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            libros.stream().forEach(System.out::println);
        }

    }

    public void listarautoresRegistrados(){
        authors = authorRep.findAll();
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            authors.stream().forEach(System.out::println);
        }
    }

    public void listarAutoresPorAnio(){
        System.out.println("Introduzca el año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        authors = authorRep.findYear(anio);
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            authors.stream().forEach(System.out::println);
        }
    }

    public void listarLibrosPorIdioma(){
        System.out.println("Introduzca el idioma: ");
        String idioma = scanner.nextLine().toLowerCase();
        libros = bookRep.findidiomas(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            libros.stream().forEach(System.out::println);
        }
    }
}

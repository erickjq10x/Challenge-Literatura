package com.challenge.literatura.principal;

import com.challenge.literatura.model.Book;
import com.challenge.literatura.model.DatoBook;
import com.challenge.literatura.repository.IAuthorRepository;
import com.challenge.literatura.repository.IBookRepository;
import com.challenge.literatura.service.ConvierteDatos;
import com.challenge.literatura.service.ServiceApi;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ServiceApi serviceApi = new ServiceApi();
    private final String URL = "https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private IBookRepository bookRep;
    private IAuthorRepository authorRep;

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
                case 0:
                    System.out.println("Cerrando Aplicacion");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    public DatoBook getDatosBook(){
        System.out.println("Introduzca el titulo: ");
        var titulo = scanner.nextLine();
        var json = serviceApi.obtenerDatos(URL + titulo.replaceAll(" ", "%20"));
        DatoBook datoBook = convierteDatos.obtenerDatos(json,DatoBook.class);
        return datoBook;
    }

    public void buscarLibroPorTitulo() {
        DatoBook datoBook = getDatosBook();
        Book book = new Book(datoBook);
        bookRep.save(book);
        System.out.println(book);
    }
}

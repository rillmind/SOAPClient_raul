package br.edu.ifpe.arqsoft.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.edu.ifpe.soapserver.ws.service.Book;
import br.edu.ifpe.soapserver.ws.service.BookService;

public class WSClient {
	public static void main(String[] args) throws MalformedURLException {

		// All data contained in this class was gathered from WSDL

		URL wsdlURL = new URL("http://localhost:8888/ws/Book?wsdl");

		QName qname = new QName("http://service.ws.soapserver.ifpe.edu.br/", "BookServiceImplService");

		Service service = Service.create(wsdlURL, qname);

		BookService bookService = service.getPort(BookService.class);

		Book book1 = new Book();
		book1.setTitle("As Crônicas de Gelo e Fogo");
		book1.setCode("123456");
		book1.setCost(69.99);
		
		Book book2 = new Book();
		book2.setTitle("Senhor dos Anéis");
		book2.setCode("654321");
		book2.setCost(49.50);
		
		Book book3 = new Book();
		book3.setTitle("As Crônicas de Nárnia");
		book3.setCode("456789");
		book3.setCost(29.99);
		
		Book book4 = new Book();
		book4.setTitle("Harry Potter e o Prisioneiro de Askaban");
		book4.setCode("789123");
		book4.setCost(56.49);
		
		System.out.println("Serviço: " + bookService.toString());
		System.out.println("Adicionando livro: " + book1.getTitle() + " (" + book1.getCost() + ")" + " | " + bookService.addBook(book1));
		System.out.println("Adicionando livro: " + book2.getTitle() + " (" + book2.getCost() + ")" + " | " + bookService.addBook(book2));
		System.out.println("Adicionando livro: " + book3.getTitle() + " (" + book3.getCost() + ")" + " | " + bookService.addBook(book3));
		System.out.println("Adicionando livro: " + book4.getTitle() + " (" + book4.getCost() + ")" + " | " + bookService.addBook(book4));
		System.out.println("Retornando livro: " + bookService.getBookById("123456").getTitle() + " (" + book1.getCost() + ")");
		System.out.println("Retornando livro: " + bookService.getBookById("654321").getTitle() + " (" + book2.getCost() + ")");
		System.out.println("Retornando livro: " + bookService.getBookById("456789").getTitle() + " (" + book3.getCost() + ")");
		System.out.println("Retornando livro: " + bookService.getBookById("789123").getTitle() + " (" + book4.getCost() + ")");
		System.out.println("O livro com id: " + book1.getCode() + " está disponível? " + bookService.isBookAvailable(book1.getCode()));
		System.out.println("Removendo livro de id: " + book1.getCode() + " | " + bookService.removeBook("123456"));
		System.out.println("O livro com id: " + book1.getCode() + " está disponível? " + bookService.isBookAvailable(book1.getCode()));
		System.out.println("O livro com id: " + book2.getCode() + " custa menos que $35? " + bookService.isBookCostAbove(book2.getCode(), 35));
	}
}

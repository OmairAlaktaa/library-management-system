package com.maidscc.library_management_system.service.book;

import com.maidscc.library_management_system.model.Book;
import com.maidscc.library_management_system.repository.book.BookRepository;
import com.maidscc.library_management_system.dto.book.BookRequestDTO;
import com.maidscc.library_management_system.dto.book.BookResponseDTO;
import com.maidscc.library_management_system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        return convertToResponseDTO(book);
    }

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = convertToEntity(bookRequestDTO);
        Book savedBook = bookRepository.save(book);
        return convertToResponseDTO(savedBook);
    }

    public BookResponseDTO updateBook(Integer bookId, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublicationYear(bookRequestDTO.getPublicationYear());
        book.setIsbn(bookRequestDTO.getIsbn());

        Book updatedBook = bookRepository.save(book);
        return convertToResponseDTO(updatedBook);
    }

    public void deleteBook(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        bookRepository.delete(book);
    }

    private BookResponseDTO convertToResponseDTO(Book book) {
        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setBookID(book.getBookID());
        responseDTO.setTitle(book.getTitle());
        responseDTO.setAuthor(book.getAuthor());
        responseDTO.setPublicationYear(book.getPublicationYear());
        responseDTO.setIsbn(book.getIsbn());
        return responseDTO;
    }

    private Book convertToEntity(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublicationYear(bookRequestDTO.getPublicationYear());
        book.setIsbn(bookRequestDTO.getIsbn());
        return book;
    }
    
}

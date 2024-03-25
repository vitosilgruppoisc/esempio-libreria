package silecchia.test.service;

import org.springframework.stereotype.Service;

import silecchia.test.model.LibroModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    private static List<LibroModel> books;

    static {
        books = new ArrayList<>();
        books.add(new LibroModel("The Great Gatsby", 1925));
        books.add(new LibroModel("To Kill a Mockingbird", 1960));
        books.add(new LibroModel("1984", 1949));
        books.add(new LibroModel("The Catcher in the Rye", 1951));
    }

    public List<LibroModel> getAllBooksByLogin(String login) {
        if (login != null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getAnno() > 1951)
                .toList();
    }

    public void save(LibroModel book) {
        books.add(book);
    }

    public LibroModel findByTitleAndDelete(String title) {
        LibroModel libroModel = books.stream()
                .filter(it -> it.getTitolo().equals(title))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        books.remove(libroModel);
        return libroModel;
    }

    public void edit(LibroModel book) {
        save(book);
    }

    public void delete(String title) {
        findByTitleAndDelete(title);
    }
}

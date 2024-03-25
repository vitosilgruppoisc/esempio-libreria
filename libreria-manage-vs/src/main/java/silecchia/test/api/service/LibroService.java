package silecchia.test.api.service;

import silecchia.test.api.model.Libro;
import silecchia.test.api.model.FilterLibro;
import silecchia.test.api.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroService {
    private LibroRepository libroRepository;

    public LibroService() {
    }

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    public Libro findByTitle(String title) {
        return libroRepository.findByTitoloAllIgnoreCase(title);
    }

 
    public List<Libro> findByAutoriId(Long id) {
        return libroRepository.findByAutoriId(id);
    }


    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    public void update(Libro libro) {
        libroRepository.save(libro);
    }

	public List<Libro> findParameter(FilterLibro jsonString) {
		return libroRepository.findByTitoloContaining(jsonString.getTitolo());
	}

	public List<Libro> findAllFilter(FilterLibro jsonString) {
		if ("OR".equalsIgnoreCase(jsonString.getCondition()))
		    return libroRepository.findByFilterLibroOR(jsonString);
		else 
			return libroRepository.findByFilterLibro(jsonString);
		//return libroRepository.findByAutori_Cognome(jsonString.getAutore());
		
	}

}

package silecchia.test.api.controller;



import silecchia.test.api.exceptions.BindingErrorsResponse;
import silecchia.test.api.model.Libro;
import silecchia.test.api.model.FilterLibro;
import silecchia.test.api.service.LibroService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/libri")
public class LibroController {
    private LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("")
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> allLibros = libroService.findAll();
        if (allLibros == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allLibros.isEmpty())
            return new ResponseEntity<>(allLibros, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allLibros, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    
    public List<Libro> search(@RequestBody FilterLibro jsonString) {
    	
    	
    	return libroService.findAllFilter(jsonString);
    	//return libroService.findParameter(jsonString);
       

    }

    @PostMapping(value = "/save")
    
    public ResponseEntity<Libro> saveLibro(@RequestBody Libro libro) {
    	
    	libroService.save(libro);
    	return new ResponseEntity<>(libro, HttpStatus.CREATED);
    	//return libroService.findParameter(jsonString);
       

    }
  @PutMapping(value = "/update")
    
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
    	
    	libroService.save(libro);
    	return new ResponseEntity<>(libro, HttpStatus.CREATED);
    	//return libroService.findParameter(jsonString);
       

    }
   @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibro(@PathVariable Long id) {
        return libroService.findById(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/by-author-id/{authorIds}")
    public ResponseEntity<List<Libro>> getAllLibrosByAuthorId(@PathVariable Long[] authorIds) {
        List<Libro> allLibrosByAuthorId = new ArrayList<>();
        for (Long authorId : authorIds) {
            List<Libro> libriByAuthorId = libroService.findByAutoriId(authorId);
            if (!libriByAuthorId.isEmpty()) allLibrosByAuthorId.addAll(libriByAuthorId);
        }
        if (allLibrosByAuthorId.isEmpty())
            return new ResponseEntity<>(allLibrosByAuthorId, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(allLibrosByAuthorId, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Libro> saveLibros(@RequestBody @Valid Libro libro, BindingResult bindingResult,
                                         UriComponentsBuilder uriComponentsBuilder) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (libro == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        libroService.save(libro);
        headers.setLocation(uriComponentsBuilder.path("/libri/{id}").buildAndExpand(libro.getId()).toUri());
        return new ResponseEntity<>(libro, headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable("id") Long id, @RequestBody @Valid Libro libro,
                                           BindingResult bindingResult) {
        Optional<Libro> currentLibro = libroService.findById(id);
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (libro == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        if (!currentLibro.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        libroService.update(libro);
        return new ResponseEntity<>(libro, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable("id") Long id) {
        Optional<Libro> libroToDelete = libroService.findById(id);
        if (!libroToDelete.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        libroService.delete(id);
        return new ResponseEntity<>(libroToDelete.get(), HttpStatus.NO_CONTENT);

    }
}

package silecchia.test.api.controller;

import silecchia.test.api.model.Autore;
import silecchia.test.api.service.AutoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by silecchia..
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/autori")
public class AutoreController {
	private AutoreService autoreService;

	public AutoreController(AutoreService autoreService) {
		this.autoreService = autoreService;
	}

	@GetMapping("")
	public ResponseEntity<List<Autore>> getAllAutori() {
		List<Autore> allAutori = autoreService.findAll();
		if (allAutori == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else if (allAutori.isEmpty())
			return new ResponseEntity<>(allAutori, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(allAutori, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Autore> getAutore(@PathVariable Long id) {
		return autoreService
				.findById(id)
				.map(autore -> new ResponseEntity<>(autore, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping(value = "/save")

	public ResponseEntity<Autore> saveLibro(@RequestBody Autore autore) {

		autoreService.save(autore);
		return new ResponseEntity<>(autore, HttpStatus.CREATED);
		//return libroService.findParameter(jsonString);


	}
	@PutMapping(value = "/update/{id}")

	public ResponseEntity<Autore> updateAutore(@RequestBody Autore autore) {

		autoreService.save(autore);
		return new ResponseEntity<>(autore, HttpStatus.CREATED);
		//return libroService.findParameter(jsonString);


	}
	@PutMapping("/{id}")
	public ResponseEntity<Autore> updateAutore(@PathVariable("id") Long id, @RequestBody  Autore autore
			) {
		Optional<Autore> currentAutore = autoreService.findById(id);

		HttpHeaders headers = new HttpHeaders();
		if ( (autore == null)) {
			
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		if (!currentAutore.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		autore.setId(id);
		autoreService.update(autore);
		return new ResponseEntity<>(autore, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Autore> deleteAutore(@PathVariable("id") Long id) {
		Optional<Autore> autoreToDelete = autoreService.findById(id);
		if (!autoreToDelete.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		autoreService.delete(id);
		return new ResponseEntity<>(autoreToDelete.get(), HttpStatus.NO_CONTENT);
	}

}

package silecchia.test.api.repository;
/*
 * - CRUD anagrafiche autori (nome, cognome, sesso, età)
- CRUD libri (titolo, autore, genere, anno di pubblicazione, giacenza).
- Ricerche nel catalogo in base a diversi criteri (titolo, autore, genere).
 * */

import silecchia.test.api.model.Libro;
import silecchia.test.api.model.FilterLibro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Libro findByTitoloAllIgnoreCase(String title);



    List<Libro> findByAutoriId(Long id);

    @Query("SELECT b " +
            "FROM Libro b ")
    List<Libro> findAll();

    List<Libro> findByTitoloContaining(String title);
    
    @Query("SELECT b " +
            "FROM Libro b join fetch b.autori a where (:#{#filterLibro.titolo} IS NULL " + 
    		"OR b.titolo=:#{#filterLibro.titolo}) AND (:#{#filterLibro.genere} IS NULL OR b.genere=:#{#filterLibro.genere}) " +
            "AND a.cognome=:#{#filterLibro.autore}" ) 
    List<Libro> findByFilterLibro(@Param("filterLibro") FilterLibro filterLibro );
    
    @Query("SELECT b " +
            "FROM Libro b join fetch b.autori a where (:#{#filterLibro.titolo} IS NULL " + 
    		"OR b.titolo=:#{#filterLibro.titolo}) OR (:#{#filterLibro.genere} IS NULL OR b.genere=:#{#filterLibro.genere}) " +
            "OR a.cognome=:#{#filterLibro.autore}" ) 
    List<Libro> findByFilterLibroOR(@Param("filterLibro") FilterLibro filterLibro );
    
    List<Libro> findByAutori_Cognome(String cognome);





	

}

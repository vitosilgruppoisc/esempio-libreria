package silecchia.test.api.repository;

import silecchia.test.api.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by silecchia..
 */
@Repository
public interface AutoreRepository extends JpaRepository<Autore, Long> {

    Autore findByNomeAndCognomeAllIgnoreCase(String name, String surname);

}

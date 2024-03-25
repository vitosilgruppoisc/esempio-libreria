package silecchia.test.api.service;

import silecchia.test.api.model.Autore;
import silecchia.test.api.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by silecchia..
 */
@Service
@Transactional
public class AutoreService {

    private AutoreRepository autoreRepository;

    public AutoreService() {
    }

    @Autowired
    public AutoreService(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }


    public List<Autore> findAll (){
        return autoreRepository.findAll();
    }

    public Optional<Autore> findById(Long id){
        return autoreRepository.findById(id);
    }

    public Autore findByNameAndSurname(String name, String surname){
        return autoreRepository.findByNomeAndCognomeAllIgnoreCase(name, surname);
    }

    public void save(Autore autore){
        autoreRepository.save(autore);
    }

    public void update(Autore autore){
        autoreRepository.save(autore);
    }

    public void delete(Long id){
        autoreRepository.deleteById(id);
    }


}

package silecchia.test.api.model;

/*
 * - CRUD anagrafiche autori (nome, cognome, sesso, età)
- CRUD libri (titolo, autore, genere, anno di pubblicazione, giacenza).
- Ricerche nel catalogo in base a diversi criteri (titolo, autore, genere).
 * */


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Libro implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String titolo;

    private String genere;

    private Integer anno;
    
 
    private Integer giacenza;

 

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "libro_autore",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autore_id"))
    private Set<Autore> autori = new HashSet<>();

 
    //Constructors
    @Autowired
    public Libro(Long id, String titolo, Set<Autore> autore, String yearOfPublication,
                String description) {
        this.id = id;
        this.titolo = titolo;
        this.autori = autore;
       
    }

    public Libro() {
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public String gettitolo() {
        return titolo;
    }

    public void settitolo(String titolo) {
        this.titolo = titolo;
    }

  
    public Set<Autore> getAutori() {
        return autori;
    }

    public void setAutori(Set<Autore> autori) {
        this.autori = autori;
    }



  
}

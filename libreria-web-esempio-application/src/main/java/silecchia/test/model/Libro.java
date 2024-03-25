package silecchia.test.model;

/*
 * - CRUD anagrafiche autori (nome, cognome, sesso, età)
- CRUD libri (titolo, autore, genere, anno di pubblicazione, giacenza).
- Ricerche nel catalogo in base a diversi criteri (titolo, autore, genere).
 * */

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;


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

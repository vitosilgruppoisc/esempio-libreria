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
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;




@SuppressWarnings("deprecation")
@Entity
public class Autore {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String cognome;

    private String sesso; 

    private Integer eta;

    @JsonBackReference
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "autori")
    private Set<Libro> libri = new HashSet<>();


    //Constructors
    public Autore(String nome, String cognome, Set<Libro> libri) {
   
        this.nome = nome;
        this.cognome = cognome;
        this.libri = libri;
    }

    public Autore(String nome, String cognome) {
   
        this.nome = nome;
        this.cognome = cognome;
    }

    public Autore() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public Set<Libro> getLibri() {
		return libri;
	}

	public void setLibri(Set<Libro> libri) {
		this.libri = libri;
	}

 
}

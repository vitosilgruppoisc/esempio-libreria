package silecchia.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroModel {

    public LibroModel(String titoloT, Integer anno) {
		this.titolo=titoloT;
		this.anno=anno;
	}

    public LibroModel(String titoloT, String genere,Integer anno,Integer giacenza) {
		this.titolo=titoloT;
		this.genere=genere;
		this.anno=anno;
		this.giacenza=giacenza;
	}
	public LibroModel() {
		
	}
	private String titolo;

    private String genere;

    private Integer anno;
    
 
    private Integer giacenza;


	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getGiacenza() {
		return giacenza;
	}

	public void setGiacenza(Integer giacenza) {
		this.giacenza = giacenza;
	}
}

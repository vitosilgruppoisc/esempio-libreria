package silecchia.test.api.model;

/*
 * - CRUD anagrafiche autori (nome, cognome, sesso, età)
- CRUD libri (titolo, autore, genere, anno di pubblicazione, giacenza).
- Ricerche nel catalogo in base a diversi criteri (titolo, autore, genere).
 * */

public class FilterLibro {

	


		private String titolo;

	    private String genere;
	    
	    private String autore;
	    private String condition;
	    private Integer anno;
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
		public String getAutore() {
			return autore;
		}
		public void setAutore(String autore) {
			this.autore = autore;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		public Integer getAnno() {
			return anno;
		}
		public void setAnno(Integer anno) {
			this.anno = anno;
		}
	    
}

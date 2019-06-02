package ostalo;

import accounts.Pacijent;
import ostalo.Pregled;

public class Racun extends OstaloFunctions {

	private Integer Cena;
	private Boolean Placeno;
	private Pacijent Pacijent;
	private String Pregled;
	public Racun( Pacijent pacijent, String IDPregleda,Integer cena, Boolean placeno) {
		this.Cena = cena;
		this.Placeno = placeno;
		this.Pacijent = pacijent;
		this.Pregled = IDPregleda;
	}
	public Integer getCena() {
		return Cena;
	}
	public void setCena(Integer cena) {
		Cena = cena;
	}
	public Boolean getPlaceno() {
		return Placeno;
	}
	public void setPlaceno(Boolean placeno) {
		Placeno = placeno;
	}
	public Pacijent getPacijent() {
		return Pacijent;
	}
	public void setPacijent(Pacijent pacijent) {
		Pacijent = pacijent;
	}
	public String getPregled() {
		return Pregled;
	}
	public void setPregled(String pregled) {
		Pregled = pregled;
	}
	
	

}

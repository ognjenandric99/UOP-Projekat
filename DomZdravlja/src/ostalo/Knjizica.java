package ostalo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import accounts.Pacijent;

public class Knjizica extends OstaloFunctions{
	private Pacijent pacijent;
	private String Kategorija;
	private Date DatumIsteka;
	private Boolean aktivna = false;
	public Knjizica(Pacijent pacijent) {
		this.pacijent = pacijent;
		String linije = getText("src/ostalo/knjizice.txt");
		String[] linija = linije.split("\\;");
		for(int i =0;i<linija.length;i++) {
			String[] tKnj = linija[i].split("\\|");
			if(pacijent.getJmbg().equalsIgnoreCase(tKnj[0])) {
				this.Kategorija = tKnj[1];
				this.aktivna=Boolean.valueOf(tKnj[3]);
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					DatumIsteka = sdf.parse(tKnj[2]);
					this.DatumIsteka = DatumIsteka;
				}
				catch(ParseException e) {
					e.printStackTrace();
					System.out.println("Datum parsing ne valja za knjizicu");
				}
			}
		}
		
	}
	public Pacijent getPacijent() {
		return pacijent;
	}
	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
	public String getKategorija() {
		return Kategorija;
	}
	public void setKategorija(String kategorija) {
		Kategorija = kategorija;
	}
	public Date getDatumIsteka() {
		return DatumIsteka;
	}
	public void setDatumIsteka(Date datumIsteka) {
		DatumIsteka = datumIsteka;
	}
	public Boolean getAktivna() {
		return aktivna;
	}
	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
	}
	

}

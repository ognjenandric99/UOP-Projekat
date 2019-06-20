package ostalo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import accounts.Pacijent;
import startPackage.TipKnjizice;

public class Knjizica extends OstaloFunctions{
	private String pacijent;
	private TipKnjizice Kategorija;
	private LocalDateTime DatumIsteka;
	private Boolean aktivna = false;
	public Knjizica(String jmbgPacijenta) {
		this.pacijent = jmbgPacijenta;
		String linije = getText("src/ostalo/knjizice.txt");
		String[] linija = linije.split("\\;");
		for(int i =0;i<linija.length;i++) {
			String[] tKnj = linija[i].split("\\|");
			if(pacijent.equalsIgnoreCase(tKnj[0])) {
				this.Kategorija = TipKnjizice.valueOf(tKnj[1]);
				this.aktivna=Boolean.valueOf(tKnj[3]);
				try {
					String[] datumi = tKnj[2].split("\\-");
					LocalDateTime cal = LocalDateTime.of(Integer.valueOf(datumi[0]),Integer.valueOf(datumi[1]),Integer.valueOf(datumi[2]),0,0);
					this.DatumIsteka = cal;
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Datum parsing ne valja za knjizicu");
				}
			}
		}
		
	}
	public String getPacijent() {
		return pacijent;
	}
	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}
	
	public TipKnjizice getKategorija() {
		return Kategorija;
	}
	public void setKategorija(TipKnjizice kategorija) {
		Kategorija = kategorija;
	}
	public Boolean getAktivna() {
		return aktivna;
	}
	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
	}
	
	public LocalDateTime getDatumIsteka() {
		return DatumIsteka;
	}
	public void setDatumIsteka(LocalDateTime datumIsteka) {
		DatumIsteka = datumIsteka;
	}
	public String vratiFormatiranDatum() {
		DateTimeFormatter fr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		String ispis = fr.format(this.DatumIsteka);
				
		return ispis;
	}
	

}

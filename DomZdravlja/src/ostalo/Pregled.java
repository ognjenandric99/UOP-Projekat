package ostalo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import accounts.Doktor;
import accounts.Pacijent;
import startPackage.Status;

public class Pregled extends OstaloFunctions {
	private String ID,Opis,Soba;
	private String Pacijent;
	private LocalDateTime Termin;
	private Status Status;
	private String Doktor;
	public Pregled(String ID, String opis, String soba,String pacijent, String doktor, LocalDateTime termin,
			startPackage.Status status) {

		this.ID = ID;
		this.Opis = opis;
		this.Soba = soba;
		this.Pacijent = pacijent;
		this.Doktor = doktor;
		this.Termin = termin;
		this.Status = status;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getOpis() {
		return Opis;
	}
	public void setOpis(String opis) {
		Opis = opis;
	}
	public String getSoba() {
		return Soba;
	}
	public void setSoba(String soba) {
		Soba = soba;
	}
	public Status getStatus() {
		return Status;
	}
	
	public LocalDateTime getTermin() {
		return Termin;
	}
	public void setTermin(LocalDateTime termin) {
		Termin = termin;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	public String getPacijent() {
		return Pacijent;
	}
	public void setPacijent(String pacijent) {
		Pacijent = pacijent;
	}
	public String getDoktor() {
		return Doktor;
	}
	public void setDoktor(String doktor) {
		Doktor = doktor;
	}
	public String vratiFormatiranDatum() {
		DateTimeFormatter fr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		String ispis = fr.format(this.Termin);
		return ispis;
	}
	
	
}

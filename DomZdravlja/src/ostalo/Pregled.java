package ostalo;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import accounts.Doktor;
import accounts.Pacijent;
import startPackage.Status;

public class Pregled extends OstaloFunctions {
	private String ID,Opis,Soba;
	private String Pacijent;
	private GregorianCalendar Termin;
	private Status Status;
	private String Doktor;
	public Pregled(String ID, String opis, String soba,String pacijent, String doktor, GregorianCalendar termin,
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
	public GregorianCalendar getTermin() {
		return Termin;
	}
	public void setTermin(GregorianCalendar termin) {
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
		String ispis = String.valueOf(this.Termin.get(Calendar.YEAR))+"-"+String.valueOf(this.Termin.get(Calendar.MONTH))+"-"+String.valueOf(this.Termin.get(Calendar.DAY_OF_MONTH))+" "+String.valueOf(this.Termin.get(Calendar.HOUR))+":"+String.valueOf(this.Termin.get(Calendar.MINUTE));
		return ispis;
	}
	
	
}

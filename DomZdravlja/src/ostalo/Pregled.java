package ostalo;

import java.time.LocalDateTime;

import startPackage.Status;

public class Pregled {
	private String ID,Opis,Soba,Pacijent,Doktor;
	private LocalDateTime Termin;
	private Status Status;
	public Pregled(String iD, String opis, String soba, String pacijent, String doktor, LocalDateTime termin,
			startPackage.Status status) {
		super();
		ID = iD;
		Opis = opis;
		Soba = soba;
		Pacijent = pacijent;
		Doktor = doktor;
		Termin = termin;
		Status = status;
	}
	
	
	
}

package accounts;

import startPackage.SluzbaSestre;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MedicinskaSestra extends Zaposleni {
	private SluzbaSestre Sluzba;
	private int plata;
	String stanje;
	public MedicinskaSestra(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		
		//Pozivam funkcije koje setuju sluzbu i platu da ne bi morao da ih prenosim kao parametre :)
		namesti(this);
		if(uzmiStanje(username)) {
			this.stanje="aktivan";
		}
		else {
			this.stanje = "ugasen";
		}
		
	}
	public SluzbaSestre getSluzba() {
		return Sluzba;
	}
	public void setSluzba(SluzbaSestre sluzba) {
		Sluzba = sluzba;
	}
	public int getPlata() {
		return plata;
	}
	public void setPlata(int plata) {
		this.plata = plata;
	}
	public void namesti(MedicinskaSestra instanca) {
		String fajl = getText("src/accounts/sestre.txt");
		String[] linijefajla = fajl.split("\\;");
		for(int i=0;i<linijefajla.length;i++) {
			String[] acc = linijefajla[i].split("\\|");
			if(acc[0].equalsIgnoreCase(instanca.getJmbg())) {
				setPlata(Integer.valueOf(acc[2]));
				setSluzba(SluzbaSestre.valueOf(acc[1]));
			}
		}
	}
	public String getStanje() {
		return stanje;
	}
	public void setStanje(String stanje) {
		this.stanje=stanje;
	}
	
	
	
	

}

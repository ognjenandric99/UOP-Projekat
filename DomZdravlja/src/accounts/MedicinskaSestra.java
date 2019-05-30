package accounts;

import startPackage.SluzbaSestre;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MedicinskaSestra extends Zaposleni {
	private SluzbaSestre Sluzba;
	private int plata;
	public MedicinskaSestra(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.plata = 123;
		this.Sluzba = Sluzba.SluzbaOpsteMedicine;
	}




	public SluzbaSestre getSluzba() {
		return Sluzba;
	}


	public void setSluzba(SluzbaSestre sluzba) {
		Sluzba = sluzba;
	}
	
	
	
	

}

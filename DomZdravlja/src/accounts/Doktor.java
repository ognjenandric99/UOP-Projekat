package accounts;

import java.util.ArrayList;

import ostalo.Pregled;
import startPackage.Sluzba;

public class Doktor extends Zaposleni{
	private String ListaPregleda;
	private String Specijalizacija;
	private Sluzba Sluzba;
//Konstruktor za Doktora
	public Doktor(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol) {
		
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.ListaPregleda = "123";
		this.Specijalizacija = "123";
		this.Sluzba = Sluzba.SluzbaOpsteMedicine;
	}
	
	public String getListaPregleda() {
		return ListaPregleda;
	}

	public void setListaPregleda(String listaPregleda) {
		ListaPregleda = listaPregleda;
	}

	public String getSpecijalizacija() {
		return Specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		Specijalizacija = specijalizacija;
	}

	public Sluzba getSluzba() {
		return Sluzba;
	}

	public void setSluzba(Sluzba sluzba) {
		Sluzba = sluzba;
	}
	
}

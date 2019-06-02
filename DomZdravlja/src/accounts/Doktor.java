package accounts;

import java.util.ArrayList;

import ostalo.Pregled;
import startPackage.Sluzba;

public class Doktor extends Zaposleni{
	private ArrayList<Pregled> ListaPregleda = new ArrayList<Pregled>();
	private String Specijalizacija;
	private Sluzba Sluzba;
	private int plata;
	String stanje;
//Konstruktor za Doktora
	public Doktor(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol) {
		
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		namesti(this);
		getSluzba();
		this.ListaPregleda = ucitajPreglede(this);
		if(uzmiStanje(username)) {
			this.stanje="aktivan";
		}
		else {
			this.stanje = "ugasen";
		}
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

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	public void namesti(Doktor instanca) {
		String fajl = getText("src/accounts/doktori.txt");
		String[] linijefajla = fajl.split("\\;");
		for(int i=0;i<linijefajla.length;i++) {
			String[] acc = linijefajla[i].split("\\|");
			if(acc[0].equalsIgnoreCase(instanca.getJmbg())) {
				setPlata(Integer.valueOf(acc[3]));
				setSpecijalizacija(acc[2]);
				setSluzba(Sluzba.valueOf(acc[1]));
			}
		}
	}



	public ArrayList<Pregled> getListaPregleda() {
		return ListaPregleda;
	}



	public void setListaPregleda(ArrayList<Pregled> listaPregleda) {
		ListaPregleda = listaPregleda;
	}
	public String getStanje() {
		return stanje;
	}
	public void setStanje(String stanje) {
		this.stanje=stanje;
	}
	
}

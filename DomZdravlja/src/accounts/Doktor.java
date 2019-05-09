package accounts;

import java.util.ArrayList;

import ostalo.Pregled;
import startPackage.Sluzba;

public class Doktor extends Zaposleni{
	private ArrayList<Pregled> ListaPregleda;
	private String Specijalizacija;
	private Sluzba Sluzba;
//Konstruktor za Doktora
	public Doktor(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol, ArrayList<Pregled> listaPregleda, int plata,
			String specijalizacija, startPackage.Sluzba sluzba) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol,plata);
		this.ListaPregleda = listaPregleda;
		this.Specijalizacija = specijalizacija;
		this.Sluzba = sluzba;
		System.out.println("Doktor : "+ime+" "+prezime);
		System.out.println("Izaberite opciju koju zelite :\n\t1)Pogledaj Spisak Pregleda\n\t2)Promeni stanje pregleda\n\t0)Log Out");
	}
	
	public void pogledajSpisakPregleda() {
		System.out.println("Pogledao spisak pregleda.");
	}
	public void promeniStanjePregleda() {
		System.out.println("Promenio stanje pregelda.");
		getText("src/accounts/accounts.txt");
		
	}
	
}

package accounts;

import startPackage.SluzbaSestre;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MedicinskaSestra extends Zaposleni {
	private SluzbaSestre Sluzba;

	public MedicinskaSestra(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol, int plata, SluzbaSestre sluzba) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol, plata);
		this.Sluzba = sluzba;
		System.out.println("Izaberite opciju koju zelite :\n\t1)Dodaj Korisnika\n\t2)Zakazi Pregled\n\t3)Izdaj Racuns\n\t4)Ispisi usere\n\t0)Log Out");

	}


	public void dodajKorisnika(String username,String password, String jmbg, String tip, String ime, String prezime, String adresa, String brtelefona,String pol) {
		try {
			File file = new File("src/accounts/accounts.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(username+"|"+password+"|"+jmbg+"|"+tip+"|"+ime+"|"+prezime+"|"+adresa+"|"+brtelefona+"|"+pol);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void zakaziPregled(String idpregleda,String pacijent,String doktor, String opis, String soba, String termin, String status) {
		try {
			File file = new File("src/ostalo/pregledi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(idpregleda+"|"+pacijent+"|"+doktor+"|"+opis+"|"+soba+"|"+termin+"|"+status);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void dodajknjizicu(String idknjizice, String datumisteka, String kategorija) {
		try {
			File file = new File("src/ostalo/knjizice.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(idknjizice+"|"+kategorija+"|"+datumisteka);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void izdajRacun() {
		System.out.println("Racun izdat");
	}
	public void ispisUsera() {
		System.out.println("Ispis usera");
	}
	

}

package accounts;

import startPackage.SluzbaSestre;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MedicinskaSestra extends Zaposleni {
	private SluzbaSestre Sluzba;

	public MedicinskaSestra(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol, int plata, SluzbaSestre sluzba) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol, plata);
		this.Sluzba = sluzba;
		System.out.println("Izaberite opciju koju zelite :\n\t1)Dodaj Korisnika\n\t2)Zakazi Pregled\n\t3)Izdaj Racuns\n\t4)Ispisi usere\n\t0)Log Out");
		//dodajknjizicu("1","12.06.2019.","1");
		//dodajKorisnika("KorisnikProba","KorisnikProba1","1233332112","Pacijent","Korisnik","Proba","ADresica","555333","true");
		//zakaziPregled("1","1233332112","24119999800014","Nesto ga boli glava,nmp","203","10.05.2019.","Zakazan");
		//izdajRacun("1","24111999980014","1500","false");
	}


	public void dodajKorisnika(String username,String password, String jmbg, String tip, String ime, String prezime, String adresa, String brtelefona,String pol) {
		try {
			String prethodnitext = getText1("src/accounts/accounts.txt");
			File file = new File("src/accounts/accounts.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String textzaupis = prethodnitext+username+"|"+password+"|"+jmbg+"|"+tip+"|"+ime+"|"+prezime+"|"+adresa+"|"+brtelefona+"|"+pol;
			System.out.println(textzaupis);
			writer.write(textzaupis);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void zakaziPregled(String idpregleda,String pacijent,String doktor, String opis, String soba, String termin, String status) {
		try {
			String prethodnitext = getText1("src/ostalo/pregledi.txt");
			File file = new File("src/ostalo/pregledi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(prethodnitext+idpregleda+"|"+pacijent+"|"+doktor+"|"+opis+"|"+soba+"|"+termin+"|"+status);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void dodajknjizicu(String idknjizice, String datumisteka, String kategorija) {
		try {
			String prethodnitext = getText1("src/ostalo/knjizice.txt");
			File file = new File("src/ostalo/knjizice.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(prethodnitext+idknjizice+"|"+kategorija+"|"+datumisteka);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void izdajRacun(String idpregleda, String pacijent, String cena, String placeno) {
		try {
			String prethodnitext = getText1("src/ostalo/racuni.txt");
			File file = new File("src/ostalo/racuni.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(prethodnitext+idpregleda+"|"+pacijent+"|"+cena+"|"+placeno);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void ispisUsera() {
		System.out.println("Ispis usera");
	}
	

}

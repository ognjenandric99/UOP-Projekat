package accounts;

import startPackage.SluzbaSestre;

public class MedicinskaSestra extends Zaposleni {
	private SluzbaSestre Sluzba;

	public MedicinskaSestra(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol, int plata, SluzbaSestre sluzba) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol, plata);
		this.Sluzba = sluzba;
		System.out.println("Izaberite opciju koju zelite :\n\t1)Dodaj Korisnika\n\t2)Zakazi Pregled\n\t3)Izdaj Racuns\n\t4)Ispisi usere\n\t0)Log Out");

	}


	public void dodajKorisnika() {
		System.out.println("DOdat korisnik");
	}
	public void zakaziPregled() {
		System.out.println("Zakazat pregled.");
	}
	public void izdajRacun() {
		System.out.println("Racun izdat");
	}
	public void ispisUsera() {
		System.out.println("Ispis usera");
	}
	

}

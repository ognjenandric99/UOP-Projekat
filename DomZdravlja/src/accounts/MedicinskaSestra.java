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
		try {
			File file = new File("src/accounts/accounts.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("BUDI|BUDI123|123456789123|Pacijent|Ognjen|Andric|Malina 13, Sremska Kamenica|0615561450|true");
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
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

package accounts;

public class Pacijent extends Korisnik {
	String lekar;
	
	public Pacijent(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol, String lekar) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.lekar = lekar;
		System.out.println("Pacijent : "+ime+" "+prezime);
		System.out.println("Izaberite opciju koju zelite :\n\t1)Pitaj za pregled\n\t2)Pogledaj Spisak Pregleda\n\t3)Pogledaj Spisak Racuna");
		
	}
	public static void pitajZaPregled() {
		System.out.println("Pitao za pregled");
		return;
	}
	public static void pogledajSpisakPregleda() {
		System.out.println("pogledajSpisakPregleda");
		return;
	}
	public static void pogledajSpisakRacuna() {
		System.out.println("pogledajSpisakRacuna");
		return;
	}
}
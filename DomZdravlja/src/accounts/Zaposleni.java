package accounts;

public abstract class Zaposleni extends Korisnik {
	private int Plata;

	public Zaposleni(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol, int plata) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.Plata = plata;
	}

}

package accounts;

public abstract class Zaposleni extends Korisnik {
	private int Plata;

	public Zaposleni(String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String username, String password, String uloga, Boolean pol) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.Plata = 123;
	}

	public int getPlata() {
		return Plata;
	}

	public void setPlata(int plata) {
		Plata = plata;
	}

}

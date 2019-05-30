package accounts;

public class Pacijent extends Korisnik {
	Doktor lekar;
	
	public Pacijent(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol) {
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.lekar = lekar;
		
	}
	public Doktor getLekar() {
		return lekar;
	}
	public void setLekar(Doktor lekar) {
		this.lekar = lekar;
	}
}


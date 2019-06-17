package accounts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ostalo.Knjizica;
import ostalo.Pregled;
import ostalo.Racun;

public class Pacijent extends Korisnik {
	Doktor lekar;
	String stanje;
	ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
	ArrayList<Racun> racuni = new ArrayList<Racun>();
	Knjizica knjizica;
	public Pacijent(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol){
		super(ime, prezime, jmbg, adresa, brojTelefona, username, password, uloga, pol);
		this.lekar = jmbgUDoktora(uzmiDoktora(jmbg));
		if(uzmiStanje(username)) {
			this.stanje="aktivan";
		}
		else {
			this.stanje = "ugasen";
		}
		this.knjizica = uzmiKnjizicu(jmbg);
		this.pregledi= ucitajPreglede(this);
		this.racuni = uzmiRacune(this);

	}
	public Doktor getLekar() {
		return lekar;
	}
	public void setLekar(Doktor lekar) {
		this.lekar = lekar;
	}
	public String getStanje() {
		return stanje;
	}
	public void setStanje(String stanje) {
		this.stanje=stanje;
	}
	public String uzmiDoktora(String jmbg) {
		String[] linije = getText("src/accounts/pacijenti.txt").split("\\;");
		for(int i=0;i<linije.length;i++) {
			String[] tAcc = linije[i].split("\\|");
			if(tAcc[0].equalsIgnoreCase(jmbg)) {
				return tAcc[1];
			}
		}
		return "Doslo je do greske";
	}
	public Knjizica uzmiKnjizicu(String jmbg) {
		Knjizica zavratiti = null;
		String[] linije = getText("src/ostalo/knjizice.txt").split("\\;");
		for(int i=0;i<linije.length;i++) {
			String[] tAcc = linije[i].split("\\|");
			if(tAcc[0].equalsIgnoreCase(jmbg)) {
				Knjizica knjizica = new Knjizica(this.getJmbg());
				zavratiti = knjizica;
			}
		}
		return zavratiti;
	}
	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}
	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
	public Knjizica getKnjizica() {
		return knjizica;
	}
	public void setKnjizica(Knjizica knjizica) {
		this.knjizica = knjizica;
	}
	public ArrayList<Racun> uzmiRacune(Pacijent user){
		ArrayList<Racun> racuni = new ArrayList<Racun>();
		String[] linije = getText("src/ostalo/racuni.txt").split("\\;");
		for(int i=0;i<linije.length;i++) {
			String[] tRac = linije[i].split("\\|");
			if(tRac[1].equalsIgnoreCase(user.getJmbg())) {
				Racun racun = new Racun(user, tRac[0], Integer.valueOf(tRac[2]), Boolean.valueOf(tRac[3]));
				racuni.add(racun);
			}
		}
		return racuni;
	}
}
package accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import ostalo.Knjizica;
import ostalo.Pregled;
import startPackage.Status;

public abstract class Korisnik  {
	private String Ime,Prezime,Jmbg,Adresa,BrojTelefona,Username,Password,Uloga;
	private Boolean Pol;
	public Korisnik(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol) {
		super();
		this.Ime = ime;
		this.Prezime = prezime;
		this.Jmbg = jmbg;
		this.Adresa = adresa;
		this.BrojTelefona = brojTelefona;
		this.Username = username;
		this.Password = password;
		this.Uloga = uloga;
		this.Pol = pol;
	}
	public static String getText(String path) {
		String getTextText = "";
		try {
			File file = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			while((line=reader.readLine())!=null) {
				getTextText = getTextText + line+";";
			}
			
		}
		catch (Exception e){
			System.out.println("Path ne valja!");
		}
		return getTextText;
	}
	public static String getText1(String path) {
		String getTextText = "";
		try {
			File file = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			while((line=reader.readLine())!=null) {
				getTextText = getTextText + line+"\n";
			}
			
		}
		catch (Exception e){
			System.out.println("Path ne valja!");
		}
		return getTextText;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}

	public String getJmbg() {
		return Jmbg;
	}

	public void setJmbg(String jmbg) {
		Jmbg = jmbg;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public String getBrojTelefona() {
		return BrojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		BrojTelefona = brojTelefona;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUloga() {
		return Uloga;
	}

	public void setUloga(String uloga) {
		Uloga = uloga;
	}

	public Boolean getPol() {
		return Pol;
	}

	public void setPol(Boolean pol) {
		Pol = pol;
	}
	

	public void sacuvajUsera(MedicinskaSestra user, String stanje) {
		
	}
	public void sacuvajUsera(Pacijent user, String stanje) {
		
	}
	public void sacuvajUsera(Doktor user, String stanje) {
		
	}
	public Boolean uzmiStanje(String username1) {
		String username = username1;
		Boolean vracanje = false;
		String fajl = getText("src/accounts/accounts.txt");
		String[] linije = fajl.split("\\;");
		for(int i=0;i<linije.length;i++) {
			String[] trAcc = linije[i].split("\\|");
			if(trAcc[0].equalsIgnoreCase(username) && trAcc[9].equalsIgnoreCase("aktivan")) {
				vracanje =  true;
			}
		}
		return vracanje;
	}
	public Doktor jmbgUDoktora(String jmbg) {
		ArrayList<Doktor> doktori = ucitajDoktore();
		for (Doktor doktor : doktori) {
			if(doktor.getJmbg().equalsIgnoreCase(jmbg)) {
				return doktor;
			}
		}
		return null;
	}
	public Pacijent jmbgUPacijenta(String jmbg) {
		ArrayList<Pacijent> pacijenti = ucitajPacijente();
		for (Pacijent pacijent : pacijenti) {
			if(pacijent.getJmbg().equalsIgnoreCase(jmbg)) {
				return pacijent;
			}
		}
		return null;
	}
	
	
	public ArrayList<Pacijent> ucitajPacijente() {
		ArrayList<Pacijent> pacijenti1 = new ArrayList<Pacijent>();
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("pacijent")) {
				Pacijent pac = new Pacijent(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				pacijenti1.add(pac);
				
			}
			
		}
		return pacijenti1;
	}
	public ArrayList<MedicinskaSestra> ucitajSestre() {
	ArrayList<MedicinskaSestra> sestre1 = new ArrayList<MedicinskaSestra>();
	String fajl = getText("src/accounts/accounts.txt");
	String[] accoutnsredovi = fajl.split("\\;");
	for(int i=0;i<accoutnsredovi.length;i++) {
		String[] xacc = accoutnsredovi[i].split("\\|");
		if(xacc[3].equalsIgnoreCase("MedicinskaSestra")) {
			MedicinskaSestra ses = new MedicinskaSestra(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
			sestre1.add(ses);
			
		}
		
	}
	return sestre1;
}
	public ArrayList<Doktor> ucitajDoktore() {
		ArrayList<Doktor> doktori1 = new ArrayList<Doktor>();
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("Doktor")) {
				Doktor dok = new Doktor(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				doktori1.add(dok);
			}
			
		}
		return doktori1;
	}
	
	public ArrayList<Knjizica> ucitajKnjizice(Pacijent user){
		ArrayList<Knjizica> knjizice = new ArrayList<Knjizica>();
		String linije = getText("src/ostalo/knjizice.txt");
		String[] linije1 = linije.split("\\;");
		for(int i=0;i<linije1.length;i++) {
			String[] tL = linije1[i].split("\\|");
			if(tL[0].equalsIgnoreCase(user.getJmbg())) {
				Knjizica knjizica = new Knjizica(user.getJmbg());
				knjizice.add(knjizica);
			}
		}
		return knjizice;
	}

	
	public ArrayList<Pregled> ucitajPreglede(Pacijent user){
		ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
		String linije = getText("src/ostalo/pregledi.txt");
		String[] linije1 = linije.split("\\;");
		for(int i=0;i<linije1.length;i++) {
			String[] tPr = linije1[i].split("\\|");
			if(tPr[1].equalsIgnoreCase(user.getJmbg())) {
				String now = tPr[5];
				String[] vreme = now.split("\\ ");
				String[] godina = vreme[0].split("\\-");
				String[] sati = vreme[1].split("\\:");
				
				LocalDateTime formatDateTime = LocalDateTime.of(Integer.valueOf(godina[0]),Integer.valueOf(godina[1]),Integer.valueOf(godina[2]),Integer.valueOf(sati[0]),Integer.valueOf(sati[1]));
				Pregled pregled = new Pregled(tPr[0], tPr[3], tPr[4],tPr[1],tPr[2],formatDateTime,Status.valueOf(tPr[6]));
				pregledi.add(pregled);			}
		}
		return pregledi;
	}
	public ArrayList<Pregled> ucitajPreglede(Doktor user){
		ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
		String linije = getText("src/ostalo/pregledi.txt");
		String[] linije1 = linije.split("\\;");
		for(int i=0;i<linije1.length;i++) {
			String[] tPr = linije1[i].split("\\|");
			if(tPr[2].equalsIgnoreCase(user.getJmbg()) && !tPr[6].equalsIgnoreCase("Zatrazen")) {
				String now = tPr[5];
				String[] vreme = now.split("\\ ");
				String[] godina = vreme[0].split("\\-");
				String[] sati = vreme[1].split("\\:");
				
		        LocalDateTime formatDateTime = LocalDateTime.of(Integer.valueOf(godina[0]),Integer.valueOf(godina[1]),Integer.valueOf(godina[2]),Integer.valueOf(sati[0]),Integer.valueOf(sati[1]));
				Pregled pregled = new Pregled(tPr[0], tPr[3], tPr[4],tPr[1],tPr[2],formatDateTime,Status.valueOf(tPr[6]));
				pregledi.add(pregled);
				}
		}
		return pregledi;
	}

}

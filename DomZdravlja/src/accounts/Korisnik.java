package accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class Korisnik {
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

}

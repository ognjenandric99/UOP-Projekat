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
				getTextText = getTextText + line;
			}
			
		}
		catch (Exception e){
			System.out.println("Path ne valja!");
		}
		return getTextText;
	}

}

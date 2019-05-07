package accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Korisnik {
	private String Ime,Prezime,Jmbg,Adresa,BrojTelefona,Username,Password,Uloga;
	private Boolean Pol;
	public Korisnik(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String username,
			String password, String uloga, Boolean pol) {
		super();
		Ime = ime;
		Prezime = prezime;
		Jmbg = jmbg;
		Adresa = adresa;
		BrojTelefona = brojTelefona;
		Username = username;
		Password = password;
		Uloga = uloga;
		Pol = pol;
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

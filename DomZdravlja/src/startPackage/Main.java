package startPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import accounts.Doktor;
import accounts.Korisnik;
import accounts.Pacijent;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("LOGIN FUNKCIJA22222");
		System.out.println("LOGIN FUNKCIJA22222");
			String accountovi = getText("src/accounts/accounts.txt");
			System.out.println(accountovi);
	}
	public static Boolean login() {
		System.out.println("Da");
		return true;
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
}

package startPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import accounts.Doktor;
import accounts.Korisnik;
import accounts.Pacijent;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			login();
			String accountovi = getText("src/accounts/accounts.txt");
	}
	public static void login() {
		System.out.println("Unesite Vase podatke da bi ste se prijavili na sistem.");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Username : ");
		String loginusername = scanner.nextLine();
		
		System.out.println("Password : ");
		String loginpassword = scanner.nextLine();
		
		//Cisto da izbacim error
		String izabranilekar="";
		//Zavrsava se uzimanje podataka
		String accountovi = getText("src/accounts/accounts.txt");
		String[] accountovi1=accountovi.split(";");
		//Uzeo sve accountove i splitujem po redovima
		for(int i=0;i<accountovi1.length;i++) {
			//For petlja ide po svakom redu
			String trenutnavrednost = accountovi1[i];
			String[] accountovi2 = trenutnavrednost.split("\\|");//Splitujem delimiter
			if(loginusername.equalsIgnoreCase(accountovi2[0])&& loginpassword.equalsIgnoreCase(accountovi2[1])) {
				if(accountovi2[3].equals("Pacijent")) {
					String izabranilekartext = getText("src/accounts/pacijenti.txt");
					System.out.println(izabranilekartext);
					//Hocu da uzmem izabranog lekara
					String[] izabranilekari = izabranilekartext.split(";");
					for(int j=0;j<izabranilekari.length;j++) {
						String IzabraniLekarRed = izabranilekari[j];
						
						String[] IzabraniLekarSplit = IzabraniLekarRed.split("\\|");
						if(IzabraniLekarSplit[0].equals(accountovi2[2])) {
							 izabranilekar = IzabraniLekarSplit[1];
						}
					}
					
					
					
					
					
					Pacijent pacijent = new Pacijent(accountovi2[4], accountovi2[5], accountovi2[2],accountovi2[6],accountovi2[7], accountovi2[0],
							accountovi2[1], accountovi2[3],Boolean.parseBoolean(accountovi2[8]), izabranilekar);
					System.out.println("Pacijent");
					scanner.close();
					return;
				}
				else if (accountovi2[3].equals("MedicinskaSestra")) {
					System.out.println("Medicinska Sestra");
				}
				else if (accountovi2[3].equals("Doktor")) {
					System.out.println("Doktor");
				}
				else {
					System.out.println("Doslo je do greske, pokusajte ponovo.");
					login();
				}
			}
		}
		login();
		
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

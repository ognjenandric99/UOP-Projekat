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
	public static Boolean login() {
		System.out.println("Unesite Vase podatke da bi ste se prijavili na sistem.");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Username : ");
		String loginusername = scanner.nextLine();
		
		System.out.println("Password : ");
		String loginpassword = scanner.nextLine();
		
		scanner.close();
		
		String accountovi = getText("src/accounts/accounts.txt");
		String[] accountovi1=accountovi.split(";");
		
		for(int i=0;i<accountovi1.length;i++) {
			String trenutnavrednost = accountovi1[i];
			String[] accountovi2 = trenutnavrednost.split("\\|");
			if(loginusername.equalsIgnoreCase(accountovi2[0])&& loginpassword.equalsIgnoreCase(accountovi2[1])) {
				if(accountovi2[3].equals("Pacijent")) {
					System.out.println("Pacijent");
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
				return true;
			}
		}
		
		return false;
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

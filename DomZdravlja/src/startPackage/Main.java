package startPackage;
//sxasd
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import accounts.Doktor;
import accounts.Korisnik;
import accounts.MedicinskaSestra;
import accounts.Pacijent;
import ostalo.Pregled;

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
		String Sluzba = "";
		Integer Plata = 0;
		String Specijalizacija = "";
		//Zavrsava se uzimanje podataka
		String accountovi = getText("src/accounts/accounts.txt");
		String[] accountovi1=accountovi.split(";");
		//Uzeo sve accountove i splitujem po redovima
		for(int i=0;i<accountovi1.length;i++) {
			//For petlja ide po svakom redu
			String trenutnavrednost = accountovi1[i];
			String[] accountovi2 = trenutnavrednost.split("\\|");//Splitujem delimiter
			if(loginusername.equalsIgnoreCase(accountovi2[0])&& loginpassword.equals(accountovi2[1])) {
				System.out.println("\n");
				
				/**PACIJENT LOGIN
				 * 
				 * 
				 * 
				 */
				if(accountovi2[3].equals("Pacijent")) {
					String izabranilekartext = getText("src/accounts/pacijenti.txt");
					//Hocu da uzmem izabranog lekara
					String[] izabranilekari = izabranilekartext.split(";");
					for(int j=0;j<izabranilekari.length;j++) {
						String IzabraniLekarRed = izabranilekari[j];
						
						String[] IzabraniLekarSplit = IzabraniLekarRed.split("\\|");
						if(IzabraniLekarSplit[0].equals(accountovi2[2])) {
							 izabranilekar = IzabraniLekarSplit[1];
						}
					}
					Pacijent User = new Pacijent(accountovi2[4], accountovi2[5], accountovi2[2],accountovi2[6],accountovi2[7], accountovi2[0],
							accountovi2[1], accountovi2[3],Boolean.parseBoolean(accountovi2[8]), izabranilekar);
					scanner.close();
					return;
				}
				
				
				/**Medicinska Sestra Login
				 * 
				 * 
				 * 
				 */
				else if (accountovi2[3].equals("MedicinskaSestra")) {
					
					String sluzbasestretext = getText("src/accounts/sestre.txt");
					//Hocu da uzmem izabranog lekara
					String[] sluzbasestrearray = sluzbasestretext.split(";");
					for(int j=0;j<sluzbasestrearray.length;j++) {
						String SluzbaSestreRed = sluzbasestrearray[j];
						
						String[] SluzbaSestre = SluzbaSestreRed.split("\\|");
						if(SluzbaSestre[0].equals(accountovi2[2])) {
							 Sluzba = SluzbaSestre[1];
							 Plata = Integer.parseInt(SluzbaSestre[2]);
						}
					}
					
					
					MedicinskaSestra User = new MedicinskaSestra(accountovi2[4], accountovi2[5], accountovi2[2],accountovi2[6],accountovi2[7], accountovi2[0],
							accountovi2[1], accountovi2[3],Boolean.parseBoolean(accountovi2[8]), Plata , SluzbaSestre.valueOf("SluzbaOpsteMedicine"));
					scanner.close();
					
					
					return;
				}
				
				
				
				
				
				
				
				/**Doktor Login
				 * 
				 * 
				 * 
				 */
				else if (accountovi2[3].equals("Doktor")) {
					//Uzimam specijalizaciju i sluzbu i platu
					String sluzbaispectext = getText("src/accounts/doktori.txt");
					//Uzimam Sluzbu Spec I Platu
					String[] sluzbaispecarray = sluzbaispectext.split(";");
					for(int j=0;j<sluzbaispecarray.length;j++) {
						String SluzbaISpecDoktoraRed = sluzbaispecarray[j];
						
						String[] SluzbaISpecDoktora = SluzbaISpecDoktoraRed.split("\\|");
						if(SluzbaISpecDoktora[0].equals(accountovi2[2])) {
							 Sluzba = SluzbaISpecDoktora[1];
							 Specijalizacija = SluzbaISpecDoktora[2];
							 Plata = Integer.parseInt(SluzbaISpecDoktora[3]);
						}
					}
					/**
					 * 
					 * Moram da odradim za Pregled da vidim kako izgleda datetime, i sve ostalo
					 * 
					Doktor User = new Doktor(accountovi2[4], accountovi2[5], accountovi2[2],accountovi2[6],accountovi2[7], accountovi2[0],
							accountovi2[1], accountovi2[3],Boolean.parseBoolean(accountovi2[8]), ArrayList<Pregled> listaPregleda, int plata,
							String specijalizacija, startPackage.Sluzba sluzba);**/
					
					
				}
				else {
					System.out.println("Doslo je do greske, pokusajte ponovo.");
					
					return;
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
}

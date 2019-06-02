package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import accounts.Doktor;
import accounts.MedicinskaSestra;
import accounts.Pacijent;
import ostalo.Knjizica;
import ostalo.Pregled;
import ostalo.Racun;
import startPackage.Status;

public class KontrolnaTacka2 extends GuiFunctions{
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	
	public KontrolnaTacka2() {
		Pacijent pacijent1 = pacijenti.get(0);
		Knjizica knjizica = pacijent1.getKnjizica();
		Doktor doktor1 = doktori.get(0);
		MedicinskaSestra sestra1 = sestre.get(0);
		String IDPregleda = pacijent1.getPregledi().get(0).getID();
		int CenaPregleda = 1500;
		Boolean StanjeRacuna = false;
		Racun racun = new Racun(pacijent1, IDPregleda, CenaPregleda, StanjeRacuna);
		String str = "2019-10-05 12:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime termin = LocalDateTime.parse(str, formatter);
		Status statusPregleda = Status.Zakazan;
		Pregled pregled = new Pregled("1", "Nesto ga boli glava,nmp", "203" , "1233332112", "22041976800052",termin,statusPregleda);
		//OVO GORE SU OBJEKTI KOJI CE SE KROZ PROJEKAT SAMI LOADOVATI
		//Sve izmene odraditi tako da se koriste set metode vec datih objekata
		//Primer
		//        Pacijent pacijent1Izmenjen = pacijent1;
		//     pacijent1Izmenjen.setBrojTelefona("23232323");
		//I tako dalje...
		Boolean izvrsi = dodajPacijenta1(pacijent1);
		System.out.println(izvrsi);
		//Lista funkcija (da Vam olaksam)
		//1)dodajPacijenta1(Pacijent user)
		//2)izmeniPacijenta1(Pacijent user) -> Prethodno ga izmenite sa set metodama
		//3)obrisiPacijenta1(Pacijent user)
		//4)dodajSestru1(MedicinskaSestra user)
		//5)izmeniSestru1(MedicinskaSestra user) -> Prethodno ga izmenite sa set metodama
		//6)obrisiSestru1(MedicinskaSestra user)
		//7)dodajDoktora1(Doktor user)
		//8)izmeniDoktora1(Doktor user) -> Prethodno ga izmenite sa set metodama
		//9)obrisiDoktora1(Doktor user)
		//10)zatraziPregled1(Pregled pregled)
		//11)promeniStanjePregleda(Pregled pregled, Status novoStanje)
		//12)promeniOpisPregleda(Pregled pregled,String opis)
		//13)izdajRacun(Racun racun)
		
		//Sve metode su tipa Boolean radi ispitivanja njihove funkcionalnosti
		//
	}
	public Boolean dodajPacijenta1(Pacijent user) {
		Boolean uspeo = false;
		String zaDodati = user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|"+user.getStanje();
		String zaDodati1 = user.getJmbg()+"|"+user.getLekar().getJmbg();
		try {
			dodajUFajl("src/accounts/accounts.txt",zaDodati);
			dodajUFajl("src/accounts/pacijenti.txt",zaDodati1);
			uspeo = true;
		} catch (Exception e) {
		}
		
		return uspeo;
	}
	public Boolean izmeniPacijenta1(Pacijent user) {
		Boolean uspeo = false;
		try {
			zameniText(user);
			uspeo =true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return uspeo;
	}
	public Boolean obrisiPacijenta1(Pacijent user) {
		Boolean uspeo = false;
		String staritext = "";
		String[] textFajla = getText("src/accounts/accounts.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
				staritext += user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|ugasen\n";
				uspeo = true;
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/accounts/accounts.txt", staritext);
		return uspeo;
	}
	
////////////////////////////////////////////////////////////
	
	public Boolean dodajSestru1(MedicinskaSestra user) {
		Boolean uspeo = false;
		String zaDodati = user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|"+user.getStanje();
		String zaDodati1 = user.getJmbg()+"|"+user.getSluzba()+"|"+user.getPlata();
		try {
			dodajUFajl("src/accounts/accounts.txt",zaDodati);
			dodajUFajl("src/accounts/sestre.txt",zaDodati1);
			uspeo = true;
		} catch (Exception e) {
		}
		
		return uspeo;
	}
	public Boolean izmeniSestru1(MedicinskaSestra user) {
		Boolean uspeo = false;
		try {
			zameniText(user);
			uspeo =true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return uspeo;
	}
	public Boolean obrisiSestru1(MedicinskaSestra user) {
		Boolean uspeo = false;
		String staritext = "";
		String[] textFajla = getText("src/accounts/accounts.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
				staritext += user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|ugasen\n";
				uspeo = true;
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/accounts/accounts.txt", staritext);
		return uspeo;
	}
////////////////////////////////////////////////////////////
	public Boolean dodajDoktora1(Doktor user) {
		Boolean uspeo = false;
		String zaDodati = user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|"+user.getStanje();
		String zaDodati1 = user.getJmbg()+"|"+user.getSluzba()+"|"+user.getSpecijalizacija()+"|"+user.getPlata();
		try {
			dodajUFajl("src/accounts/accounts.txt",zaDodati);
			dodajUFajl("src/accounts/doktori.txt",zaDodati1);
			uspeo = true;
		} catch (Exception e) {
		}
		
		return uspeo;
	}
	public Boolean izmeniDoktora1(Doktor user) {
		Boolean uspeo = false;
		try {
			zameniText(user);
			uspeo =true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return uspeo;
	}
	public Boolean obrisiDoktora1(Doktor user) {
		Boolean uspeo = false;
		String staritext = "";
		String[] textFajla = getText("src/accounts/accounts.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
				staritext += user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|ugasen\n";
				uspeo = true;
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/accounts/accounts.txt", staritext);
		return uspeo;
	}
////////////////////////////////////////////////////////////
	public Boolean zatraziPregled1(Pregled pregled) {
		Boolean uspeo = false;
		String zaDodati = pregled.getID()+"|"+pregled.getPacijent()+"|"+pregled.getDoktor()+"|"+pregled.getOpis()+"|"+pregled.getSoba()+"|"+pregled.getTermin()+"|"+pregled.getStatus();
		try {
			dodajUFajl("src/ostalo/pregledi.txt",zaDodati);
			uspeo = true;
		} catch (Exception e) {
		}
		
		return uspeo;
	}
	
	public Boolean promeniStanjePregleda(Pregled pregled,Status novoStanje) {
		Boolean uspeo = false;
		pregled.setStatus(novoStanje);
		try {
			zameniText(pregled);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return uspeo;
	}
	public Boolean promeniOpisPregleda(Pregled pregled,String opis) {
		Boolean uspeo = false;
		pregled.setOpis(opis);
		try {
			zameniText(pregled);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return uspeo;
	}
////////////////////////////////////////////////////////////
	public Boolean izdajRacun(Racun racun) {
		Boolean uspeo = false;
		String zaDodati = racun.getPregled()+"|"+racun.getPacijent()+"|"+racun.getCena()+"|"+racun.getPlaceno();
		try {
			dodajUFajl("src/ostalo/racuni.txt",zaDodati);
			uspeo = true;
		} catch (Exception e) {
		}
		
		return uspeo;
	}
////////////////////////////////////////////////////////////
	public Boolean promeniDatumKnjizice(Knjizica knjizica, Pacijent user, String NoviDatum) {
		Boolean uspeo = false;
		try {
			String staritext = "";
			String[] textFajla = getText("src/ostalo/knjizice.txt").split("\\;");
			for(int i=0;i<textFajla.length;i++) {
				String[] tAcc = textFajla[i].split("\\|");
				if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
					staritext += tAcc[0]+"|"+tAcc[1]+"|"+NoviDatum+"|"+tAcc[3]+"\n";
				}
				else {
					staritext += String.join("|", tAcc)+"\n";
				}
			}
			ispisiUFajl("src/ostalo/knjizice.txt", staritext);
			
			uspeo = true;
		} catch (Exception e) {
			// TODO: handle exception
		}{
			
		}
		return uspeo;
	}
	
	
	public void dodajUFajl(String path,String dodatak) {
		String prethodno = getTextZaNastaviti(path);
		try {
			File file = new File(path);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(prethodno+dodatak);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void ispisiUFajl(String path,String ispis) {
		try {
			File file = new File(path);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(ispis);
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	public void zameniText(Pacijent user) {
		String staritext = "";
		String[] textFajla = getText("src/accounts/accounts.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
				staritext += user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|"+user.getStanje()+"\n";
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/accounts/accounts.txt", staritext);
		
		String staritext1 = "";
		String[] textFajla1 = getText("src/accounts/pacijenti.txt").split("\\;");
		for(int i=0;i<textFajla1.length;i++) {
			String[] tAcc1 = textFajla1[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc1[0])) {
				staritext1 += user.getJmbg()+"|"+user.getLekar().getJmbg()+"\n";
			}
			else {
				staritext1 += String.join("|", tAcc1)+"\n";
			}
		}
		ispisiUFajl("src/accounts/pacijenti.txt", staritext);
	}
	public void zameniText(MedicinskaSestra user) {
		String staritext = "";
		String[] textFajla = getText("src/accounts/accounts.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
				staritext += user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|"+user.getStanje()+"\n";
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/accounts/accounts.txt", staritext);
		
		String staritext1 = "";
		String[] textFajla1 = getText("src/accounts/sestre.txt").split("\\;");
		for(int i=0;i<textFajla1.length;i++) {
			String[] tAcc1 = textFajla1[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc1[0])) {
				staritext1 += user.getJmbg()+"|"+user.getSluzba()+"|"+user.getPlata()+"\n";
			}
			else {
				staritext1 += String.join("|", tAcc1)+"\n";
			}
		}
		ispisiUFajl("src/accounts/sestre.txt", staritext);
	}
	public void zameniText(Doktor user) {
		String staritext = "";
		String[] textFajla = getText("src/accounts/accounts.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc[0])) {
				staritext += user.getUsername()+"|"+user.getPassword()+"|"+user.getJmbg()+"|"+user.getUloga()+"|"+user.getIme()+"|"+user.getPrezime()+"|"+user.getAdresa()+"|"+user.getBrojTelefona()+"|"+user.getPol()+"|"+user.getStanje()+"\n";
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/accounts/accounts.txt", staritext);
		
		String staritext1 = "";
		String[] textFajla1 = getText("src/accounts/doktori.txt").split("\\;");
		for(int i=0;i<textFajla1.length;i++) {
			String[] tAcc1 = textFajla1[i].split("\\|");
			if(user.getUsername().equalsIgnoreCase(tAcc1[0])) {
				staritext1 += user.getJmbg()+"|"+user.getSluzba()+"|"+user.getSpecijalizacija()+"|"+user.getPlata()+"\n";
			}
			else {
				staritext1 += String.join("|", tAcc1)+"\n";
			}
		}
		ispisiUFajl("src/accounts/doktori.txt", staritext);
	}
	public void zameniText(Pregled pregled) {
		String staritext = "";
		String[] textFajla = getText("src/ostalo/pregledi.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(pregled.getID().equalsIgnoreCase(tAcc[0])) {
				staritext +=pregled.getID()+"|"+pregled.getPacijent()+"|"+pregled.getDoktor()+"|"+pregled.getOpis()+"|"+pregled.getSoba()+"|"+pregled.getTermin()+"|"+pregled.getStatus();
				
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/ostalo/pregledi.txt", staritext);
		
	}
	
	//Gotova kontrolna tacka 2
	//Za "Izmeni" funkcije, prvo konstruisati nov objekat koji treba da zameni stari
	//OBAVEZNO OSTAVITI isti kljuc da bi radilo, u suprotnosti funkcija nece nista izvrsiti
	
}

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
		
	}
	public Boolean dodajPacijenta1(Pacijent user) {
		Boolean uspeo = false;
		pacijenti.add(user);
		sacuvajListe(pacijenti,doktori,sestre);
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
	public Boolean sacuvajListe(ArrayList<Pacijent> pacijenti,ArrayList<Doktor> doktori, ArrayList<MedicinskaSestra> sestre) {
		String accounts = "";
		String pacij = "";
		String dokt = "";
		String ses = "";
		for (Pacijent pacijent : pacijenti) {
			accounts += pacijent.getUsername()+"|"+pacijent.getPassword()+"|"+pacijent.getJmbg()+"|"+pacijent.getUloga()+"|"+pacijent.getIme()+"|"+pacijent.getPrezime()+"|"+pacijent.getAdresa()+"|"+pacijent.getBrojTelefona()+"|"+pacijent.getPol().toString()+"|"+pacijent.getStanje()+"\n";
			pacij += pacijent.getJmbg()+"|"+pacijent.getLekar().getJmbg()+"\n";
		}
		for (MedicinskaSestra sestra : sestre) {
			accounts += sestra.getUsername()+"|"+sestra.getPassword()+"|"+sestra.getJmbg()+"|"+sestra.getUloga()+"|"+sestra.getIme()+"|"+sestra.getPrezime()+"|"+sestra.getAdresa()+"|"+sestra.getBrojTelefona()+"|"+sestra.getPol().toString()+"|"+sestra.getStanje()+"\n";
			ses += sestra.getJmbg()+"|"+sestra.getSluzba().toString()+"|"+String.valueOf(sestra.getPlata())+"\n";
		}
		for (Doktor doktor : doktori) {
			accounts += doktor.getUsername()+"|"+doktor.getPassword()+"|"+doktor.getJmbg()+"|"+doktor.getUloga()+"|"+doktor.getIme()+"|"+doktor.getPrezime()+"|"+doktor.getAdresa()+"|"+doktor.getBrojTelefona()+"|"+doktor.getPol().toString()+"|"+doktor.getStanje()+"\n";
			dokt += doktor.getJmbg()+"|"+doktor.getSluzba().toString()+"|"+doktor.getSpecijalizacija()+"|"+doktor.getPlata()+"\n";
		}
		try {
			ispisiUFajl("src/accounts/accounts.txt", accounts);
			ispisiUFajl("src/accounts/pacijenti.txt", pacij);
			ispisiUFajl("src/accounts/sestre.txt", ses);
			ispisiUFajl("src/accounts/doktori.txt", dokt);
		} catch (Exception e) {
			System.out.println("Doslo je do greskse prilikom ispisa u fajl.");
		}
		return false;
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
	public Boolean obrisiPregled(Pregled pregled) {
		Boolean uspeo = false;
		String staritext = "";
		String[] textFajla = getText("src/ostalo/pregledi.txt").split("\\;");
		for(int i=0;i<textFajla.length;i++) {
			String[] tAcc = textFajla[i].split("\\|");
			if(pregled.getID().equalsIgnoreCase(tAcc[0])) {
				uspeo = true;
			}
			else {
				staritext += String.join("|", tAcc)+"\n";
			}
		}
		ispisiUFajl("src/ostalo/pregledi.txt", staritext);
		
		String staritextr ="";
		String[] textfajlar = getText("src/ostalo/racuni.txt").split("\\;");
		for(int i=0;i<textfajlar.length;i++) {
			String[] tra = textfajlar[i].split("\\|");
			if(tra[0].equalsIgnoreCase(pregled.getID())) {
				uspeo = true;
			}
			else {
				staritextr+=String.join("|", tra)+"\n";
			}
		}
		ispisiUFajl("src/ostalo/racuni.txt", staritextr);
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
	public Boolean dodajKnjizicu(Knjizica knjizica) {

		//dodajUFajl("src/ostalo/knjizice.txt", ispis);
		return true;
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
	public void ocistifajlove() {
		ArrayList<Pacijent> ciscenjePacijenti = ucitajPacijente();
		ArrayList<MedicinskaSestra> ciscenjeMedicinskaSestra = ucitajSestre();
		ArrayList<Doktor> ciscenjeDoktori=ucitajDoktore();
		
		ArrayList<Pacijent> ociscenPacijent = new ArrayList<Pacijent>();
		ArrayList<MedicinskaSestra> ociscenaSestra = new ArrayList<MedicinskaSestra>();
		ArrayList<Doktor> ociscenDoktor = new ArrayList<Doktor>();
		
		for (int i = ciscenjePacijenti.size()-1; i>=0 ; i--) {
			int brojac = 0;
			for (Pacijent pacijent : ociscenPacijent) {
				
				if(pacijent.getJmbg().equalsIgnoreCase(ciscenjePacijenti.get(i).getJmbg())) {
					brojac++;
				}
				
			}
			if(brojac==0) {
				ociscenPacijent.add(ciscenjePacijenti.get(i));
			}
			
		}
		for (int i = ciscenjeMedicinskaSestra.size()-1; i>=0 ; i--) {
			int brojac = 0;
			for (MedicinskaSestra sestra : ociscenaSestra) {
				
				if(sestra.getJmbg().equalsIgnoreCase(ciscenjeMedicinskaSestra.get(i).getJmbg())) {
					brojac++;
				}
				
			}
			if(brojac==0) {
				ociscenaSestra.add(ciscenjeMedicinskaSestra.get(i));
			}
			
			
		}
		for (int i = ciscenjeDoktori.size()-1; i>=0 ; i--) {
			int brojac = 0;
			for (Doktor doktor : ociscenDoktor) {
				
				if(doktor.getJmbg().equalsIgnoreCase(ciscenjeDoktori.get(i).getJmbg())) {
					brojac++;
				}
				
			}
			if(brojac==0) {
				ociscenDoktor.add(ciscenjeDoktori.get(i));
			}
			
		}
		sacuvajListe(ociscenPacijent, ociscenDoktor, ociscenaSestra);
	}
	public void racunUFajl(Racun racun) {
		Boolean postojiVec = false;
		String fajl = getText("src/ostalo/racuni.txt");
		String[] linije = fajl.split("\\;");
		for(int i=0;i<linije.length;i++) {
			String[] racunt = linije[i].split("\\|");
			if(racunt[0].equalsIgnoreCase(racun.getPregled())) {
				postojiVec = true;
			}
		}
		if(postojiVec==false) {
			String dodatak = racun.getPregled()+"|"+racun.getPacijent().getJmbg()+"|"+String.valueOf(racun.getCena())+"|"+String.valueOf(racun.getPlaceno()).toString()+"\n";
			dodajUFajl("src/ostalo/racuni.txt", dodatak);
		}
		else {
			System.out.println("Racun za taj pregled vec postoji.");
		}
	}
}

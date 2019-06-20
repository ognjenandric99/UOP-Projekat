package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import accounts.Doktor;
import accounts.MedicinskaSestra;
import accounts.Pacijent;
import ostalo.Knjizica;
import ostalo.Pregled;
import ostalo.Racun;
import startPackage.Status;

public class GuiFunctions extends JFrame {
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	
	public ArrayList<Pacijent> ucitajPacijente() {
		ArrayList<Pacijent> pacijenti1 = new ArrayList<Pacijent>();
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("pacijent")) {
				Pacijent pac = new Pacijent(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				if(!pacijenti1.contains(pac)) {
					pacijenti1.add(pac);
				}
				
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
	public ArrayList<Pregled> ucitajPreglede(){
		ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
		String linije = getText("src/ostalo/pregledi.txt");
		String[] linije1 = linije.split("\\;");
		for(int i=0;i<linije1.length;i++) {
			String[] tPr = linije1[i].split("\\|");
				String now = tPr[5];
				String[] vreme = now.split("\\ ");
				String[] godina = vreme[0].split("\\-");
				String[] sati = vreme[1].split("\\:");
				
				LocalDateTime formatDateTime = LocalDateTime.of(Integer.valueOf(godina[0]),Integer.valueOf(godina[1]),Integer.valueOf(godina[2]),Integer.valueOf(sati[0]),Integer.valueOf(sati[1]));
				Pregled pregled = new Pregled(tPr[0], tPr[3], tPr[4],tPr[1],tPr[2],formatDateTime,Status.valueOf(tPr[6]));
				
				pregledi.add(pregled);
				
		}
		return pregledi;
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
	public static String getTextZaNastaviti(String path) {
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
	public Pacijent jmbgUPacijenta(String jmbg) {
		ArrayList<Pacijent> pacijenti = ucitajPacijente();
		for (Pacijent pacijent : pacijenti) {
			if(pacijent.getJmbg().equalsIgnoreCase(jmbg)) {
				return pacijent;
			}
		}
		return null;
	}
	
	public MedicinskaSestra jmbgUMSestru(String jmbg) {
		ArrayList<MedicinskaSestra> sestre = ucitajSestre();
		for (MedicinskaSestra sestra : sestre) {
			if(sestra.getJmbg().equalsIgnoreCase(jmbg)) {
				return sestra;
			}
		}
		return null;
	}
	
	public Doktor jmbgUDoktora(String jmbg) {
		if(jmbg.equalsIgnoreCase("X")) {
			return null;
		}
		ArrayList<Doktor> doktori = ucitajDoktore();
		for (Doktor doktor : doktori) {
			if(doktor.getJmbg().equalsIgnoreCase(jmbg)) {
				return doktor;
			}
		}
		return null;
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
	public Boolean izmeniPacijenta(Pacijent target) {
		String linijaZaFajlAccounts = target.getUsername()+"|"+target.getPassword()+"|"+target.getJmbg()+"|"+target.getUloga()+"|"+target.getIme()+"|"+target.getPrezime()+"|"+target.getAdresa()+"|"+target.getBrojTelefona()+"|"+target.getPol().toString()+"|"+target.getStanje()+"\n";
		String linijaZaFajlPacijenti = target.getJmbg()+"|"+target.getLekar().getJmbg()+"\n";
		return false;
		
	}
	public LocalDateTime formatiranUDateTime(String formatiran){
		String[] vremena = formatiran.split("\\ ");
		String[] godina = vremena[0].split("\\-");
		String[] sati = vremena[1].split("\\:");
		try {
			LocalDateTime vreme = LocalDateTime.of(Integer.valueOf(godina[0]),Integer.valueOf(godina[1]),Integer.valueOf(godina[2]),Integer.valueOf(sati[0]),Integer.valueOf(sati[1]));
			return vreme;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
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
	
}

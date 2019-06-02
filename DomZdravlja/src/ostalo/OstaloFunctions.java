package ostalo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import accounts.Doktor;
import accounts.MedicinskaSestra;
import accounts.Pacijent;

public class OstaloFunctions {
	public ArrayList<Pacijent> ucitajPacijente() {
		ArrayList<Pacijent> pacijenti1 = new ArrayList<Pacijent>();
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("pacijent")) {
				Pacijent pac = new Pacijent(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				pacijenti1.add(pac);
				
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
		ArrayList<Doktor> doktori = ucitajDoktore();
		for (Doktor doktor : doktori) {
			if(doktor.getJmbg().equalsIgnoreCase(jmbg)) {
				return doktor;
			}
		}
		return null;
	}
	
}

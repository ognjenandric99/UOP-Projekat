package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import org.w3c.dom.UserDataHandler;

import accounts.Pacijent;
import accounts.Doktor;
import accounts.MedicinskaSestra;

import net.miginfocom.swing.MigLayout;


public class MainGui extends JFrame {
	/**Meni Opcije za Medicinsku Sestru**/
	JMenuBar meniBar = new JMenuBar();
	JMenu meniKorisnici = new JMenu("Korisnici");
	JMenuItem dodajKorisnika = new JMenuItem("Dodaj korisnika");
	JMenuItem pogledajKorisnika = new JMenuItem("Pogledaj korisnika");
	JMenuItem izmeniKorisnika = new JMenuItem("Izmeni korisnika");
	JMenuItem obrisiKorisnika = new JMenuItem("Obrisi Korisnika");
	
	
	JMenu meniPregledi = new JMenu("Pregledi");
	JMenuItem zakaziPregled = new JMenuItem("Zakazi pregled");
	JMenuItem prikaziZatrazene = new JMenuItem("Zatrazeni pregledi");
	JMenuItem prikaziZakazane = new JMenuItem("Zakazani pregledi");
	JMenuItem prikaziGotove = new JMenuItem("Gotovi pregledi");
	
	JMenu meniRacuni = new JMenu("Racuni");
	JMenuItem izdajRacun = new JMenuItem("Izdaj racun");
	
	JMenu profil = new JMenu("Moj Profil");
	JMenuItem prikaziProfil = new JMenuItem("Prikazi profil");
	JMenuItem logOut = new JMenuItem("Log out");
	
	
	
	/*KRAJ OPCIJA ZA MEDICINSKU SESTRU*/
	
	public MainGui(Pacijent user) {
		prozor();
		
	}
	public MainGui(Doktor user) {
		prozor();
		
	}
	public MainGui(MedicinskaSestra user) {
		prozor();
		opcijeSestra();
		eventsSestra(user);
	}
	
	
	public void prozor() {
		setTitle("Dom Zdravlja");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/2;
		int visinaprozora = (int)screenSize.getHeight()/2;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);
		initGUI();
		
		
	}
	
	public void opcijeSestra() {
		
		setJMenuBar(meniBar);
		meniBar.add(meniKorisnici);
		meniKorisnici.add(dodajKorisnika);
		meniKorisnici.add(izmeniKorisnika);
		meniKorisnici.add(pogledajKorisnika);
		meniKorisnici.add(obrisiKorisnika);
		
		meniBar.add(meniPregledi);
		meniPregledi.add(zakaziPregled);
		meniPregledi.add(prikaziZatrazene);
		meniPregledi.add(prikaziZakazane);
		meniPregledi.add(prikaziGotove);
		
		meniBar.add(meniRacuni);
		meniRacuni.add(izdajRacun);
		
		meniBar.add(profil);
		
		//Odavde ide na desni deo 
		 meniBar.add(Box.createGlue());
		 meniBar.add(profil);
		 profil.add(prikaziProfil);
		 profil.add(logOut);
		 
		 
		 
	}

	public String[] izvuci(String stvar, Integer broj, String path) {
		String izvucenfajl = getText(path);
		String[] rezultat = null;
		String[] splitovano = izvucenfajl.split("\\;");
		for(int i=0;i<splitovano.length;i++) {
			String[] trenutanred = splitovano[i].split("\\|");
			if(stvar.equals(trenutanred[broj])) {
				rezultat = trenutanred;
			}
		}
		return rezultat;
	}

	public void initGUI() {
		
		MigLayout mig = new MigLayout("al center center,wrap 2");
		setLayout(mig);
		
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
	public void eventsSestra(MedicinskaSestra user) {
		
		
		
		
		logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGui.this.setVisible(false);
				MainGui.this.dispose();
				Login login = new Login();
				login.setVisible(true);
				
				
			}
			
		});
	}
	
}

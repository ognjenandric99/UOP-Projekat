package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import accounts.Pacijent;
import accounts.Doktor;
import accounts.MedicinskaSestra;

import net.miginfocom.swing.MigLayout;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;
import startPackage.TipKnjizice;


public class TabelaKorisnik extends KontrolnaTacka2 {
	
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	
	private JLabel labela = new JLabel("oasdoasodoasd");
	
	public TabelaKorisnik() {
		prozor();
		initGUI();
	}
	
	public TabelaKorisnik(Pacijent pacijent) {
		prozor();
		initGUI(pacijent);
	}
	public TabelaKorisnik(MedicinskaSestra sestra) {
		prozor();
		initGUI(sestra);
	}
	public TabelaKorisnik(Doktor doktor) {
		prozor();
		initGUI(doktor);
	}
	public void prozor(){
		setTitle("Dom Zdravlja");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/2;
		int visinaprozora = (int)screenSize.getHeight()/2;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);
		events();
	
	}
	public void initGUI(Doktor doktor) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		
		
		String[] zaglavlja = new String[] { "Username", "Password", "JMBG","Uloga","Ime","Prezime","Adresa","Broj Telefona","Pol","Status Naloga"  ,"Sluzba","Specijalizacija","Plata"};
		Object[][] sadrzaj = new Object[1][zaglavlja.length];
		for(int i=0; i<sadrzaj.length; i++) {
			sadrzaj[i][0] = doktor.getUsername();
			sadrzaj[i][1] = doktor.getPassword();
			sadrzaj[i][2] = doktor.getJmbg();
			sadrzaj[i][3] = doktor.getUloga();
			sadrzaj[i][4] = doktor.getIme();
			sadrzaj[i][5] = doktor.getPrezime();
			sadrzaj[i][6] = doktor.getAdresa();
			sadrzaj[i][7] = doktor.getBrojTelefona();
			if(doktor.getPol()==true) {
				sadrzaj[i][8]="Musko";
			}
			else {
				sadrzaj[i][8]="Zensko";
			}
			sadrzaj[i][9] = doktor.getStanje();
			sadrzaj[i][10]= doktor.getSluzba().toString();
			sadrzaj[i][11]=doktor.getSpecijalizacija();
			sadrzaj[i][12]=String.valueOf(doktor.getPlata());
		
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlja);
		JTable tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(tabela){
		      @Override
		      public Dimension getPreferredSize() {
		        return new Dimension(sirinaprozora, visinaprozora);
		      }
		    };;
		add(scrollPane);
	}
	
	public void initGUI(MedicinskaSestra sestra) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		
		
		String[] zaglavlja = new String[] { "Username", "Password", "JMBG","Uloga","Ime","Prezime","Adresa","Broj Telefona","Pol","Status Naloga"  ,"Sluzba","Plata"};
		Object[][] sadrzaj = new Object[1][zaglavlja.length];
		for(int i=0; i<sadrzaj.length; i++) {
			sadrzaj[i][0] = sestra.getUsername();
			sadrzaj[i][1] = sestra.getPassword();
			sadrzaj[i][2] = sestra.getJmbg();
			sadrzaj[i][3] = sestra.getUloga();
			sadrzaj[i][4] = sestra.getIme();
			sadrzaj[i][5] = sestra.getPrezime();
			sadrzaj[i][6] = sestra.getAdresa();
			sadrzaj[i][7] = sestra.getBrojTelefona();
			if(sestra.getPol()==true) {
				sadrzaj[i][8]="Musko";
			}
			else {
				sadrzaj[i][8]="Zensko";
			}
			sadrzaj[i][9] = sestra.getStanje();
			sadrzaj[i][10]= sestra.getSluzba().toString();
			sadrzaj[i][11]=String.valueOf(sestra.getPlata());
		
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlja);
		JTable tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(tabela){
		      @Override
		      public Dimension getPreferredSize() {
		        return new Dimension(sirinaprozora, visinaprozora);
		      }
		    };;
		add(scrollPane);
	}
	
	public void initGUI(Pacijent pacijent) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		
		
		String[] zaglavlja = new String[] { "Username", "Password", "JMBG","Uloga","Ime","Prezime","Adresa","Broj Telefona","Pol","Status Naloga"  ,"Istek Knjizice","Kategorija Knjizice"};
		Object[][] sadrzaj = new Object[1][zaglavlja.length];
		for(int i=0; i<sadrzaj.length; i++) {
			sadrzaj[i][0] = pacijent.getUsername();
			sadrzaj[i][1] = pacijent.getPassword();
			sadrzaj[i][2] = pacijent.getJmbg();
			sadrzaj[i][3] = pacijent.getUloga();
			sadrzaj[i][4] = pacijent.getIme();
			sadrzaj[i][5] = pacijent.getPrezime();
			sadrzaj[i][6] = pacijent.getAdresa();
			sadrzaj[i][7] = pacijent.getBrojTelefona();
			if(pacijent.getPol()==true) {
				sadrzaj[i][8]="Musko";
			}
			else {
				sadrzaj[i][8]="Zensko";
			}
			sadrzaj[i][9] = pacijent.getStanje();
			sadrzaj[i][10]= pacijent.getKnjizica().vratiFormatiranDatum();
			sadrzaj[i][11]= pacijent.getKnjizica().getKategorija().toString();
		
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlja);
		JTable tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(tabela){
		      @Override
		      public Dimension getPreferredSize() {
		        return new Dimension(sirinaprozora, visinaprozora);
		      }
		    };;
		add(scrollPane);
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		
		int sabrano = pacijenti.size()+sestre.size()+doktori.size();
		
		String[] zaglavlja = new String[] { "Username", "Password", "JMBG","Uloga","Ime","Prezime","Adresa","Broj Telefona","Pol","Status Naloga"  ,"Istek Knjizice","Kategorija Knjizice" , "Sluzba" , "Specijalizacija","Plata"};
		Object[][] sadrzaj = new Object[sabrano][zaglavlja.length];
		int i=0;
		while(i<sabrano) {
			for (Pacijent pacijent : pacijenti) {
				sadrzaj[i][0] = pacijent.getUsername();
				sadrzaj[i][1] = pacijent.getPassword();
				sadrzaj[i][2] = pacijent.getJmbg();
				sadrzaj[i][3] = pacijent.getUloga();
				sadrzaj[i][4] = pacijent.getIme();
				sadrzaj[i][5] = pacijent.getPrezime();
				sadrzaj[i][6] = pacijent.getAdresa();
				sadrzaj[i][7] = pacijent.getBrojTelefona();
				if(pacijent.getPol()==true) {
					sadrzaj[i][8]="Musko";
				}
				else {
					sadrzaj[i][8]="Zensko";
				}
				sadrzaj[i][9] = pacijent.getStanje();
				sadrzaj[i][10]= pacijent.getKnjizica().vratiFormatiranDatum();
				sadrzaj[i][11]= pacijent.getKnjizica().getKategorija().toString();
				i++;
			}
			for (MedicinskaSestra sestra : sestre) {
				sadrzaj[i][0] = sestra.getUsername();
				sadrzaj[i][1] = sestra.getPassword();
				sadrzaj[i][2] = sestra.getJmbg();
				sadrzaj[i][3] = sestra.getUloga();
				sadrzaj[i][4] = sestra.getIme();
				sadrzaj[i][5] = sestra.getPrezime();
				sadrzaj[i][6] = sestra.getAdresa();
				sadrzaj[i][7] = sestra.getBrojTelefona();
				if(sestra.getPol()==true) {
					sadrzaj[i][8]="Musko";
				}
				else {
					sadrzaj[i][8]="Zensko";
				}
				sadrzaj[i][9] = sestra.getStanje();
				sadrzaj[i][12]= sestra.getSluzba().toString();
				sadrzaj[i][14]= String.valueOf(sestra.getPlata());
				i++;
			}
			
			for (Doktor doktor : doktori) {
				sadrzaj[i][0] = doktor.getUsername();
				sadrzaj[i][1] = doktor.getPassword();
				sadrzaj[i][2] = doktor.getJmbg();
				sadrzaj[i][3] = doktor.getUloga();
				sadrzaj[i][4] = doktor.getIme();
				sadrzaj[i][5] = doktor.getPrezime();
				sadrzaj[i][6] = doktor.getAdresa();
				sadrzaj[i][7] = doktor.getBrojTelefona();
				if(doktor.getPol()==true) {
					sadrzaj[i][8]="Musko";
				}
				else {
					sadrzaj[i][8]="Zensko";
				}
				sadrzaj[i][9] = doktor.getStanje();
				sadrzaj[i][12]= doktor.getSluzba().toString();
				sadrzaj[i][13]= doktor.getSpecijalizacija();
				sadrzaj[i][14]= String.valueOf(doktor.getPlata());
				i++;
			}
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlja);
		JTable tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(tabela){
		      @Override
		      public Dimension getPreferredSize() {
		        return new Dimension(sirinaprozora, visinaprozora);
		      }
		    };;
		add(scrollPane);
	}
	public void events() {
		
	}
}

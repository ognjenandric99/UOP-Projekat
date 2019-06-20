package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.UserDataHandler;

import accounts.Pacijent;
import accounts.Doktor;
import accounts.MedicinskaSestra;

import net.miginfocom.swing.MigLayout;
import ostalo.Pregled;


public class MainGui extends GuiFunctions{
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	/**Meni Opcije za Medicinsku Sestru**/
	JMenuBar meniBar = new JMenuBar();
	JMenu meniKorisnici = new JMenu("Korisnici");
	JMenuItem dodajKorisnika = new JMenuItem("Dodaj korisnika");
	JMenuItem pogledajKorisnika = new JMenuItem("Pogledaj korisnika");
	JMenuItem izmeniKorisnika = new JMenuItem("Izmeni korisnika");
	JMenuItem prikaziKorisnike = new JMenuItem("Prikazi korisnike");
	
	
	JMenu meniPregledi = new JMenu("Pregledi");
	JMenuItem zakaziPregled = new JMenuItem("Zakazi pregled");
	JMenuItem prikaziZatrazene = new JMenuItem("Zatrazeni pregledi");
	JMenuItem prikaziZakazane = new JMenuItem("Zakazani pregledi");
	JMenuItem prikaziGotove = new JMenuItem("Gotovi pregledi");
	JMenuItem mojiPregledi = new JMenuItem("Moji pregledi");
	JMenuItem izmeniPregled = new JMenuItem("Izmeni pregled");
	JMenuItem prikaziSvePreglede = new JMenuItem("Prikazi sve preglede");
	
	JMenu meniRacuni = new JMenu("Racuni");
	JMenuItem izdajRacun = new JMenuItem("Izdaj racun");
	JMenuItem mojiRacuni = new JMenuItem("Moji racuni");
	
	JMenu profil = new JMenu("Moj Profil");
	JMenuItem prikaziProfil = new JMenuItem("Prikazi profil");
	JMenuItem logOut = new JMenuItem("Log out");
	
	JLabel lab = new JLabel("xD");
	
	/*KRAJ OPCIJA ZA MEDICINSKU SESTRU*/
	
	public MainGui(Pacijent user) {
		prozor();
		opcijePacijent();
		eventsPacijent(user);
	}
	public MainGui(Doktor user) {
		prozor();
		opcijeDoktor();
		eventsDoktora(user);
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
	public void opcijePacijent() {
		setJMenuBar(meniBar);
		meniBar.add(meniPregledi);
		meniPregledi.add(zakaziPregled);
		meniPregledi.add(mojiPregledi);
		
		meniBar.add(meniRacuni);
		meniRacuni.add(mojiRacuni);
		
		meniBar.add(profil);
		
		//Odavde ide na desni deo 
		 meniBar.add(Box.createGlue());
		 meniBar.add(profil);
		 profil.add(logOut);
	}
	public void opcijeSestra() {
		
		setJMenuBar(meniBar);
		meniBar.add(meniKorisnici);
		meniKorisnici.add(dodajKorisnika);
		meniKorisnici.add(izmeniKorisnika);
		meniKorisnici.add(pogledajKorisnika);
		meniKorisnici.add(prikaziKorisnike);
		
		meniBar.add(meniPregledi);
		meniPregledi.add(prikaziZatrazene);
		meniPregledi.add(prikaziGotove);
		meniPregledi.add(prikaziZakazane);
		meniPregledi.add(prikaziSvePreglede);
		
		meniBar.add(meniRacuni);
		meniRacuni.add(izdajRacun);
		
		meniBar.add(profil);
		
		//Odavde ide na desni deo 
		 meniBar.add(Box.createGlue());
		 meniBar.add(profil);
		 profil.add(prikaziProfil);
		 profil.add(logOut);
		 
		 
		 
	}
	public void opcijeDoktor() {

		setJMenuBar(meniBar);
		
		meniBar.add(meniPregledi);
		meniPregledi.add(prikaziZakazane);
		meniPregledi.add(prikaziSvePreglede);
		
		
		//Odavde ide na desni deo 
		 meniBar.add(Box.createGlue());
		 meniBar.add(profil);
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
		
		
		prikaziProfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Profil profil = new Profil(user.getJmbg().toString(),user);
				profil.setVisible(true);
				
			}
		});
		
		dodajKorisnika.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Profil profil = new Profil(user);
				profil.setVisible(true);
			}
		});
		
		pogledajKorisnika.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String jmbgKorisnika = (String)JOptionPane.showInputDialog(null, "Unesite JMBG Korisnika kojeg zelite da pogledate",
						"Pregled Korisnika", JOptionPane.PLAIN_MESSAGE, null,
						null, "");
				
				if(jmbgKorisnika.length()==13) {
					Boolean stanje = false;
					
					for (Pacijent pac : pacijenti) {
						if(pac.getJmbg().equalsIgnoreCase(jmbgKorisnika)) {
							stanje=true;
							TabelaKorisnik tabela = new TabelaKorisnik(pac);
							tabela.setVisible(true);
						}
					}
					
					for (MedicinskaSestra sestra : sestre) {
						if(sestra.getJmbg().equalsIgnoreCase(jmbgKorisnika)) {
							stanje=true;
							TabelaKorisnik tabela = new TabelaKorisnik(sestra);
							tabela.setVisible(true);
						}
					}
					for (Doktor doktor : doktori) {
						if(doktor.getJmbg().equalsIgnoreCase(jmbgKorisnika)) {
							stanje=true;
							TabelaKorisnik tabela = new TabelaKorisnik(doktor);
							tabela.setVisible(true);
						}
					}
					if(stanje==false) {
						JOptionPane.showMessageDialog( null, "JMBG koji ste uneli ne pripada ni jednom korisniku.",
								"Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog( null, "Molimo Vas, unesite tacan JMBG.",
							"Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		prikaziKorisnike.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TabelaKorisnik tabela = new TabelaKorisnik();
				tabela.setVisible(true);
			}
		});
		
		izmeniKorisnika.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String jmbgKorisnika = (String)JOptionPane.showInputDialog(null, "Unesite JMBG Korisnika kojeg zelite da izmenite",
						"Izmena Korisnika", JOptionPane.PLAIN_MESSAGE, null,
						null, "");
				if(jmbgKorisnika.length()==13){
					Profil profil = new Profil(jmbgKorisnika, user);
					profil.setVisible(true);
				}
			}
		});
		
		prikaziZatrazene.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TabelaPregled pregled = new TabelaPregled("Zatrazen",user);
				pregled.setVisible(true);
			}
		});
		
		prikaziSvePreglede.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TabelaPregled tabela = new TabelaPregled(user);
				tabela.setVisible(true);
			}
		});
		
		prikaziZakazane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TabelaPregled pregled = new TabelaPregled("Zakazan",user);
				pregled.setVisible(true);
			}
		});
		
		prikaziGotove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TabelaPregled pregled = new TabelaPregled("Zavrsen",user);
				pregled.setVisible(true);
			}
		});
		
		izdajRacun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IzdajRacun racun = new IzdajRacun(user);
				racun.setVisible(true);
			}
		});
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
	public void eventsPacijent(Pacijent user) {
		mojiPregledi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TabelaPregled tabela = new TabelaPregled(user);
				tabela.setVisible(true);
			}
		});
		logOut.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MainGui.this.setVisible(false);
						MainGui.this.dispose();
						Login login = new Login();
						login.setVisible(true);
						
						
					}
					
				});
		zakaziPregled.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String opisPregleda = (String)JOptionPane.showInputDialog(null, "Unesite opis pregleda",
						"Zakazivanje pregleda", JOptionPane.PLAIN_MESSAGE, null,
						null, "");
				if(opisPregleda.length()!=0) {
					ArrayList<Pregled> pregledi = ucitajPreglede();
					int id = pregledi.size()+1;
					String dodatak = id+"|"+user.getJmbg()+"|"+user.getLekar().getJmbg()+"|"+opisPregleda+"|X|9999-12-31 23:59|Zatrazen";
					dodajUFajl("src/ostalo/pregledi.txt", dodatak);
				}
				else {
					JOptionPane.showMessageDialog( null, "Morate uneti opis pregleda prilikom zakazivanja.",
							"Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	public void eventsDoktora(Doktor user) {
		
	}
}

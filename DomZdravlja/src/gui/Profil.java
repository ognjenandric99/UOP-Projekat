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
import javax.swing.JTextField;

import accounts.Pacijent;
import accounts.Doktor;
import accounts.MedicinskaSestra;

import net.miginfocom.swing.MigLayout;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;
import startPackage.TipKnjizice;


public class Profil extends KontrolnaTacka2 {
	
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	
	
	private JLabel lUserJmbg = new JLabel("JMBG : ");
	private JTextField txtUserJmbg = new JTextField(20);
	
	private JLabel lUserUsername = new JLabel("Username : ");
	private JTextField txtUserUsername = new JTextField(20);
	
	private JLabel lUserPassword = new JLabel("Password : ");
	private JPasswordField txtUserPassword = new JPasswordField(20);
	
	private JLabel lUserSluzba = new JLabel("Sluzba : ");
	private JComboBox<SluzbaSestre> comboSluzbaS = new JComboBox<SluzbaSestre>();
	private JComboBox<Sluzba> comboSluzba = new JComboBox<Sluzba>();
	
	private JLabel lUserPlata = new JLabel("Plata : ");
	private JTextField txtUserPlata = new JTextField(20);
	
	private JLabel lUserSpecijalizacija = new JLabel("Specijalizacija : ");
	private JTextField txtUserSpecijalizacija = new JTextField(20);
	
	private JLabel lUserIme = new JLabel("Ime : ");
	private JTextField txtUserIme = new JTextField(20);
	
	private JLabel lUserPrezime = new JLabel("Prezime : ");
	private JTextField txtUserPrezime  = new JTextField(20);

	private JLabel lUserAdresa = new JLabel("Adresa : ");
	private JTextField txtUserAdresa = new JTextField(20);
	
	private JLabel lUserBrojTelefona = new JLabel("Broj Telefona : ");
	private JTextField txtUserBrojTelefona = new JTextField(20);
	
	private JButton btnSacuvaj = new JButton("Sacuvaj");
	private JButton btnCancel = new JButton("Cancel");
	
	private JLabel lUserTip = new JLabel("Tip accounta : ");
	private JComboBox<String> comboTip = new JComboBox<String>();
	
	private JLabel lUserPol = new JLabel("Pol : ");
	private JComboBox<String> comboPol = new JComboBox<String>();
	
	private JLabel lUserStatus = new JLabel("Aktivan nalog : ");
	private JCheckBox checkBoxStatus = new JCheckBox();
	
	private JLabel lIzabraniLekar = new JLabel("JMBG izabranog lekara : ");
	private JTextField txtIzabraniLekarJMBG = new JTextField(20);
	
	private JLabel lPacijentKnjizica = new JLabel("Kategorija Knjizice");
	private JComboBox<TipKnjizice> comboTipKnjizice = new JComboBox<TipKnjizice>();
	
	private JLabel lPacijentKnjizicaIstek = new JLabel("Istek knjizice (U FORMATU : YYYY-MM-DD)");
	private JTextField txtIstekKnjizice = new JTextField(20);
	
	public Profil(MedicinskaSestra user) {
		prozor(user);
	}
	public Profil(String jmbgtarget,MedicinskaSestra user) {
		prozor(jmbgtarget,user);
		ocistifajlove();
	}
	public void prozor(MedicinskaSestra user) {
		setTitle("Dom Zdravlja");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/4;
		int visinaprozora = (int)screenSize.getHeight()/2;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);
		initGUI(user);
		eventsSestra(user);
	}
	public void prozor(String jmbgtarget,MedicinskaSestra user) {
		setTitle("Dom Zdravlja");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/4;
		int visinaprozora = (int)screenSize.getHeight()/2;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);
		
		
		
		
		Pacijent target1 = jmbgUPacijenta(jmbgtarget);
		MedicinskaSestra target2 = jmbgUMSestru(jmbgtarget);
		Doktor target3 = jmbgUDoktora(jmbgtarget);
		if(target1!=null) {
			initGUI(target1,user);
			eventsSestra(target1.getUloga(),user);
		}
		else if (target2!=null) {
			initGUI(target2,user);
			eventsSestra(target2.getUloga(),user);
		}
		else if(target3!=null) {
			initGUI(target3,user);
			eventsSestra(target3.getUloga(),user);
		}
		else if(target1==null && target2==null && target3==null) {
			initGUI(user);
			eventsSestra(user);
		}
		else {
			System.out.println("Doslo je do greskse prilikom provere da li su objekti prazni kod prozora.");
		}
		
		
		
		
		
		
	}
	
	

	public void initGUI(Pacijent target,MedicinskaSestra user) {
		
			MigLayout mig = new MigLayout("wrap 2");
			setLayout(mig);
			
			txtUserJmbg.setText(target.getJmbg());
			add(lUserJmbg);add(txtUserJmbg);
			
			txtUserUsername.setText(target.getUsername());
			add(lUserUsername);add(txtUserUsername);
			
			txtUserPassword.setText(target.getPassword());
			add(lUserPassword);add(txtUserPassword);
			
			
			txtUserIme.setText(target.getIme());
			add(lUserIme);add(txtUserIme);
			
			txtUserPrezime.setText(target.getPrezime());
			add(lUserPrezime);add(txtUserPrezime);
			
			txtUserAdresa.setText(target.getAdresa());
			add(lUserAdresa);add(txtUserAdresa);
			
			txtUserBrojTelefona.setText(target.getBrojTelefona());
			add(lUserBrojTelefona);add(txtUserBrojTelefona);
			
			add(lUserTip);
			comboTip.addItem("Pacijent");
			comboTip.addItem("MedicinskaSestra");
			comboTip.addItem("Doktor");
			add(comboTip);
			izmenaProzoraZbogTipa(user);
			comboTip.setSelectedItem(String.valueOf(target.getUloga()));
			
			add(lUserPol);
			comboPol.addItem("Musko");
			comboPol.addItem("Zensko");
			add(comboPol);
			if(target.getPol()) {
				comboPol.setSelectedItem("Musko");
			}
			else {
				comboPol.setSelectedItem("Zensko");
			}
			
			add(lIzabraniLekar);
			txtIzabraniLekarJMBG.setText(target.getLekar().getJmbg());
			add(txtIzabraniLekarJMBG);
			
			add(lPacijentKnjizica);add(comboTipKnjizice);
			comboTipKnjizice.addItem(TipKnjizice.prvi);
			comboTipKnjizice.addItem(TipKnjizice.drugi);
			comboTipKnjizice.addItem(TipKnjizice.treci);
			add(lPacijentKnjizicaIstek);add(txtIstekKnjizice);
			
			checkBoxStatus.setSelected (uzmiStanje(target.getUsername()));
			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
		}

	public void initGUI(MedicinskaSestra target,MedicinskaSestra user) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		txtUserJmbg.setText(target.getJmbg());
		add(lUserJmbg);add(txtUserJmbg);
		
		txtUserUsername.setText(target.getUsername());
		add(lUserUsername);add(txtUserUsername);
		
		txtUserPassword.setText(target.getPassword());
		add(lUserPassword);add(txtUserPassword);
		//SLUZBA COMBO BOX
		add(lUserSluzba);
		comboSluzbaS.addItem(SluzbaSestre.SluzbaOpsteMedicine);
		comboSluzbaS.addItem(SluzbaSestre.SluzbaZaPravnoIEkonomskePoslove);
		comboSluzbaS.addItem(SluzbaSestre.SluzbaZaTehnickePoslove);
		comboSluzbaS.addItem(SluzbaSestre.SluzbaZdravstveneZastiteDece);
		comboSluzbaS.addItem(SluzbaSestre.SluzbaZdravstveneZastiteRadnika);
		comboSluzbaS.addItem(SluzbaSestre.StomatoloskaSluzba);
		add(comboSluzbaS);
		comboSluzbaS.setSelectedItem(target.getSluzba());
		
		
		txtUserPlata.setText(String.valueOf(target.getPlata()));
		add(lUserPlata);add(txtUserPlata);
		
		txtUserIme.setText(target.getIme());
		add(lUserIme);add(txtUserIme);
		
		txtUserPrezime.setText(target.getPrezime());
		add(lUserPrezime);add(txtUserPrezime);
		
		txtUserAdresa.setText(target.getAdresa());
		add(lUserAdresa);add(txtUserAdresa);
		
		txtUserBrojTelefona.setText(target.getBrojTelefona());
		add(lUserBrojTelefona);add(txtUserBrojTelefona);
		
		add(lUserTip);
		comboTip.addItem("Pacijent");
		comboTip.addItem("MedicinskaSestra");
		comboTip.addItem("Doktor");
		add(comboTip);
		comboTip.setSelectedItem(String.valueOf(target.getUloga()));
		izmenaProzoraZbogTipa(user);
		
		add(lUserPol);
		comboPol.addItem("Musko");
		comboPol.addItem("Zensko");
		add(comboPol);
		if(target.getPol()) {
			comboPol.setSelectedItem("Musko");
		}
		else {
			comboPol.setSelectedItem("Zensko");
		}
		
		
		checkBoxStatus.setSelected (uzmiStanje(target.getUsername()));
		add(lUserStatus);add(checkBoxStatus);
		
		add(btnSacuvaj);add(btnCancel);
	}
	public void initGUI(Doktor target,MedicinskaSestra user) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		txtUserJmbg.setText(target.getJmbg());
		add(lUserJmbg);add(txtUserJmbg);
		
		txtUserUsername.setText(target.getUsername());
		add(lUserUsername);add(txtUserUsername);
		
		txtUserPassword.setText(target.getPassword());
		add(lUserPassword);add(txtUserPassword);
		//SLUZBA COMBO BOX
		add(lUserSluzba);
		comboSluzba.addItem(Sluzba.SluzbaOpsteMedicine);
		comboSluzba.addItem(Sluzba.SluzbaZdravstveneZastiteDece);
		comboSluzba.addItem(Sluzba.SluzbaZdravstveneZastiteRadnika);
		comboSluzba.addItem(Sluzba.StomatoloskaSluzba);
		add(comboSluzba);
		comboSluzba.setSelectedItem(target.getSluzba());
		//Specijalizam
		txtUserSpecijalizacija.setText(target.getSpecijalizacija());
		add(lUserSpecijalizacija);add(txtUserSpecijalizacija);
		
		txtUserPlata.setText(String.valueOf(target.getPlata()));
		add(lUserPlata);add(txtUserPlata);
		
		txtUserIme.setText(target.getIme());
		add(lUserIme);add(txtUserIme);
		
		txtUserPrezime.setText(target.getPrezime());
		add(lUserPrezime);add(txtUserPrezime);
		
		txtUserAdresa.setText(target.getAdresa());
		add(lUserAdresa);add(txtUserAdresa);
		
		txtUserBrojTelefona.setText(target.getBrojTelefona());
		add(lUserBrojTelefona);add(txtUserBrojTelefona);
		 
		add(lUserTip);
		comboTip.addItem("Pacijent");
		comboTip.addItem("MedicinskaSestra");
		comboTip.addItem("Doktor");
		add(comboTip);
		izmenaProzoraZbogTipa(user);
		comboTip.setSelectedItem(String.valueOf(target.getUloga()));
		
		add(lUserPol);
		comboPol.addItem("Musko");
		comboPol.addItem("Zensko");
		add(comboPol);
		if(target.getPol()) {
			comboPol.setSelectedItem("Musko");
		}
		else {
			comboPol.setSelectedItem("Zensko");
		}
		
		
		checkBoxStatus.setSelected (uzmiStanje(target.getUsername()));
		add(lUserStatus);add(checkBoxStatus);
		
		add(btnSacuvaj);add(btnCancel);
	}
	public void initGUI(MedicinskaSestra user) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		add(lUserJmbg);add(txtUserJmbg);
		
		add(lUserUsername);add(txtUserUsername);
		
		add(lUserPassword);add(txtUserPassword);

		add(lUserIme);add(txtUserIme);
		
		add(lUserPrezime);add(txtUserPrezime);
		
		add(lUserAdresa);add(txtUserAdresa);
		
		add(lUserBrojTelefona);add(txtUserBrojTelefona);
		 
		add(lUserTip);
		comboTip.addItem("Pacijent");
		comboTip.addItem("MedicinskaSestra");
		comboTip.addItem("Doktor");
		comboTip.setSelectedItem(null);
		add(comboTip);
		izmenaProzoraZbogTipa(user);
		
		
		add(lUserPol);
		comboPol.addItem("Musko");
		comboPol.addItem("Zensko");
		add(comboPol);
		comboPol.setSelectedItem(null);
		
		add(lIzabraniLekar);
		add(txtIzabraniLekarJMBG);
		
		checkBoxStatus.setSelected (false);
		add(lUserStatus);add(checkBoxStatus);
		
		add(btnSacuvaj);add(btnCancel);
	}
	
	public void eventsSestra(String tipusera,MedicinskaSestra user) {
		
		if(tipusera.equalsIgnoreCase("Pacijent")) {
			ocistiDugme(btnSacuvaj);
			
			btnSacuvaj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(proveriVrednostiPacijent()) {
						String pol = "";
						String status = "";
						if(comboPol.getSelectedItem().toString().equalsIgnoreCase("Musko")) {
							pol="true";
						}
						else {
							pol="false";
						}
						if(checkBoxStatus.isSelected()) {
							status="aktivan";
						}
						else {
							status="ugasen";
							}
						String dodatak = txtUserUsername.getText()+"|"+String.valueOf(txtUserPassword.getPassword())+"|"+txtUserJmbg.getText()+"|Pacijent|"+txtUserIme.getText()+"|"+txtUserPrezime.getText()+"|"+txtUserAdresa.getText()+"|"+txtUserBrojTelefona.getText()+"|"+pol+"|"+status+"\n";
						dodajUFajl("src/accounts/accounts.txt", dodatak);
						String dodatak1 = txtUserJmbg.getText()+"|"+txtIzabraniLekarJMBG.getText()+"\n";
						dodajUFajl("src/accounts/pacijenti.txt", dodatak1);
						String dodatak2 = txtUserJmbg.getText()+"|"+String.valueOf(comboTipKnjizice.getSelectedItem())+"|"+txtIstekKnjizice.getText()+"|"+"true";
						dodajUFajl("src/ostalo/knjizice.txt", dodatak2);
						ocistifajlove();
						
					}
				}
			});
		}
		else if (tipusera.equalsIgnoreCase("MedicinskaSestra")) {
			ocistiDugme(btnSacuvaj);
			
			btnSacuvaj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(proveriVrednostiSestra()) {
						String pol = "";
						String status = "";
						if(comboPol.getSelectedItem().toString().equalsIgnoreCase("Musko")) {
							pol="true";
						}
						else {
							pol="false";
						}
						if(checkBoxStatus.isSelected()) {
							status="aktivan";
						}
						else {
							status="ugasen";
							}
						String dodatak = txtUserUsername.getText()+"|"+String.valueOf(txtUserPassword.getPassword())+"|"+txtUserJmbg.getText()+"|MedicinskaSestra|"+txtUserIme.getText()+"|"+txtUserPrezime.getText()+"|"+txtUserAdresa.getText()+"|"+txtUserBrojTelefona.getText()+"|"+pol+"|"+status+"\n";
						dodajUFajl("src/accounts/accounts.txt", dodatak);
						String dodatak1 = txtUserJmbg.getText()+"|"+String.valueOf(comboSluzbaS.getSelectedItem())+"|"+txtUserPlata.getText()+"\n";
						dodajUFajl("src/accounts/sestre.txt", dodatak1);
						ocistifajlove();
					}
				}
			});
		}
		else if(tipusera.equalsIgnoreCase("Doktor")) {
			
			ocistiDugme(btnSacuvaj);
			btnSacuvaj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(proveriVrednostiDoktor()) {
						String pol = "";
						String status = "";
						if(comboPol.getSelectedItem().toString().equalsIgnoreCase("Musko")) {
							pol="true";
						}
						else {
							pol="false";
						}
						if(checkBoxStatus.isSelected()) {
							status="aktivan";
						}
						else {
							status="ugasen";
							}
						String dodatak = txtUserUsername.getText()+"|"+String.valueOf(txtUserPassword.getPassword())+"|"+txtUserJmbg.getText()+"|Doktor|"+txtUserIme.getText()+"|"+txtUserPrezime.getText()+"|"+txtUserAdresa.getText()+"|"+txtUserBrojTelefona.getText()+"|"+pol+"|"+status+"\n";
						dodajUFajl("src/accounts/accounts.txt", dodatak);
						String dodatak1 = txtUserJmbg.getText()+"|"+String.valueOf(comboSluzba.getSelectedItem())+"|"+txtUserSpecijalizacija.getText()+"|"+txtUserPlata.getText()+"\n";
						dodajUFajl("src/accounts/doktori.txt", dodatak1);
						ocistifajlove();
					}
				}
			});
			
		}
		
		else {
			System.out.println("Doslo je do greske u eventsSestra prilikom ucitavanja tipa usera");
		}
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Profil.this.setVisible(false);
				Profil.this.dispose();
			}
		});
		
		
	}
	public void eventsSestra(MedicinskaSestra user) {
		
		btnCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Profil.this.setVisible(false);
						Profil.this.dispose();
					}
				});
	}
	
	
	public void izmenaProzoraZbogTipa(MedicinskaSestra user) {
		comboTip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menjanjeTipa((String)comboTip.getSelectedItem(),user);
			}
		});
	}
	
	public void menjanjeTipa(String sadasnji, MedicinskaSestra user) {
		if(sadasnji.equalsIgnoreCase("Pacijent")) {
			getContentPane().removeAll();
			revalidate();
			repaint();
			add(lUserJmbg);add(txtUserJmbg);

			add(lUserUsername);add(txtUserUsername);
			
			add(lUserPassword);add(txtUserPassword);

			add(lUserIme);add(txtUserIme);
			
			add(lUserPrezime);add(txtUserPrezime);
			
			add(lUserAdresa);add(txtUserAdresa);
			
			add(lUserBrojTelefona);add(txtUserBrojTelefona);
			 
			add(lUserTip);
			comboTip.removeAllItems();
			comboTip.addItem("Pacijent");
			comboTip.addItem("MedicinskaSestra");
			comboTip.addItem("Doktor");
			add(comboTip);
			comboTip.setSelectedItem("Pacijent");
			
			
			add(lUserPol);
			comboPol.removeAllItems();
			comboPol.addItem("Musko");
			comboPol.addItem("Zensko");
			comboPol.setSelectedItem(null);
			add(comboPol);
			
			add(lIzabraniLekar);
			add(txtIzabraniLekarJMBG);

			add(lPacijentKnjizica);add(comboTipKnjizice);
			comboTipKnjizice.addItem(TipKnjizice.prvi);
			comboTipKnjizice.addItem(TipKnjizice.drugi);
			comboTipKnjizice.addItem(TipKnjizice.treci);
			comboTipKnjizice.setSelectedItem(null);
			add(lPacijentKnjizicaIstek);add(txtIstekKnjizice);
			
			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
			
			revalidate();
			repaint();
			eventsSestra(sadasnji, user);
		}
		else if(sadasnji.equalsIgnoreCase("Doktor")) {
			getContentPane().removeAll();
			revalidate();
			repaint();
			add(lUserJmbg);add(txtUserJmbg);
			

			add(lUserUsername);add(txtUserUsername);
			

			add(lUserPassword);add(txtUserPassword);
			//SLUZBA COMBO BOX
			add(lUserSluzba);
			comboSluzba.removeAllItems();
			comboSluzba.addItem(Sluzba.SluzbaOpsteMedicine);
			comboSluzba.addItem(Sluzba.SluzbaZdravstveneZastiteDece);
			comboSluzba.addItem(Sluzba.SluzbaZdravstveneZastiteRadnika);
			comboSluzba.addItem(Sluzba.StomatoloskaSluzba);
			comboSluzba.setSelectedItem(null);
			add(comboSluzba);
			
			
			//Specijalizam

			add(lUserSpecijalizacija);add(txtUserSpecijalizacija);
			

			add(lUserPlata);add(txtUserPlata);
			

			add(lUserIme);add(txtUserIme);
			

			add(lUserPrezime);add(txtUserPrezime);
			

			add(lUserAdresa);add(txtUserAdresa);
			

			add(lUserBrojTelefona);add(txtUserBrojTelefona);
			 
			add(lUserTip);
			comboTip.removeAllItems();
			comboTip.addItem("Pacijent");
			comboTip.addItem("MedicinskaSestra");
			comboTip.addItem("Doktor");
			add(comboTip);
			comboTip.setSelectedItem(sadasnji);

			
			add(lUserPol);
			comboPol.removeAllItems();
			comboPol.addItem("Musko");
			comboPol.addItem("Zensko");
			comboPol.setSelectedItem(null);
			add(comboPol);
			

			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
			revalidate();
			repaint();
			eventsSestra(sadasnji, user);
		}
		else if(sadasnji.equalsIgnoreCase("MedicinskaSestra")) {
			getContentPane().removeAll();
			revalidate();
			repaint();
			add(lUserJmbg);add(txtUserJmbg);
			

			add(lUserUsername);add(txtUserUsername);
			

			add(lUserPassword);add(txtUserPassword);
			//SLUZBA COMBO BOX
			add(lUserSluzba);
			comboSluzbaS.removeAllItems();
			comboSluzbaS.addItem(SluzbaSestre.SluzbaOpsteMedicine);
			comboSluzbaS.addItem(SluzbaSestre.SluzbaZaPravnoIEkonomskePoslove);
			comboSluzbaS.addItem(SluzbaSestre.SluzbaZaTehnickePoslove);
			comboSluzbaS.addItem(SluzbaSestre.SluzbaZdravstveneZastiteDece);
			comboSluzbaS.addItem(SluzbaSestre.SluzbaZdravstveneZastiteRadnika);
			comboSluzbaS.addItem(SluzbaSestre.StomatoloskaSluzba);
			comboSluzbaS.setSelectedItem(null);
			add(comboSluzbaS);
			
			
			

			add(lUserPlata);add(txtUserPlata);
			

			add(lUserIme);add(txtUserIme);
			

			add(lUserPrezime);add(txtUserPrezime);
			

			add(lUserAdresa);add(txtUserAdresa);
			

			add(lUserBrojTelefona);add(txtUserBrojTelefona);
			 
			add(lUserTip);
			comboTip.removeAllItems();
			comboTip.addItem("Pacijent");
			comboTip.addItem("MedicinskaSestra");
			comboTip.addItem("Doktor");
			add(comboTip);
			comboTip.setSelectedItem(sadasnji);

			
			add(lUserPol);
			comboPol.removeAllItems();
			comboPol.addItem("Musko");
			comboPol.addItem("Zensko");
			comboPol.setSelectedItem(null);
			add(comboPol);
			
			

			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
			revalidate();
			repaint();
			eventsSestra(sadasnji, user);
		}
		else {
			System.out.println("Doslo je do greske prilikom menjanja tipa u prozoru Profil");
		}
	}
	
	public Boolean proveriVrednostiPacijent() {
		Boolean nijeProslo = false;
		int tacno = 0;
		if(txtUserJmbg.getText().length()==13) {
			tacno ++;
		}
		else {
			System.out.println("JMBG Nije u tacnom formatu.");
		}
		if(txtUserUsername.getText().length()>5) {
			tacno ++;
		}
		else {
			System.out.println("Username je kraci od 5 karaktera.");
		}
		if(txtUserPassword.getPassword().toString().length()>5) {
			tacno ++;
		}
		else {
			System.out.println("Password je kraci od 5 karaktera.");
		}
		if(txtUserIme.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Ime usera.");
		}
		if(txtUserPrezime.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Prezime usera.");
		}
		if(txtUserAdresa.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Adresu usera.");
		}
		if(txtUserBrojTelefona.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli broj telefona usera");
		}
		if(comboPol.getSelectedItem()!=null) {
			tacno++;
		}
		else {
			System.out.println("Niste odabrali pol usera.");
		}
		if(txtIzabraniLekarJMBG.getText().length()==13) {
			tacno++;
		}
		else {
			System.out.println(txtIzabraniLekarJMBG.getText().length());
			System.out.println("JMBG Lekara nije u tacnom formatu.");
		}
		if(comboTipKnjizice.getSelectedItem()!=null) {
			tacno++;
		}
		else {
			System.out.println("Niste odabrali knjizicu.");
		}
		String[] vremeIsteka = txtIstekKnjizice.getText().split("\\-");
		Boolean mozeVreme;
		try {
			GregorianCalendar cal = new GregorianCalendar(Integer.valueOf(vremeIsteka[0]),Integer.valueOf(vremeIsteka[1]),Integer.valueOf(vremeIsteka[2]));
			mozeVreme = true;
			if(Integer.valueOf(vremeIsteka[0])<1900 || Integer.valueOf(vremeIsteka[0])>3000 || Integer.valueOf(vremeIsteka[1])>12 ||  Integer.valueOf(vremeIsteka[1])<1 ||  Integer.valueOf(vremeIsteka[1])>12 ||  Integer.valueOf(vremeIsteka[2])>31 ||  Integer.valueOf(vremeIsteka[2])<1){
				mozeVreme = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			mozeVreme = false;
		}
		if(vremeIsteka.length==3 && mozeVreme) {
			tacno++;
		}
		else {
			System.out.println("Vreme nije u tacnom formatu.");
		}

		if(tacno==11) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean proveriVrednostiSestra() {
		int tacno = 0;
		if(txtUserJmbg.getText().length()==13) {
			tacno ++;
		}
		else {
			System.out.println("JMBG Nije u tacnom formatu.");
		}
		if(txtUserUsername.getText().length()>5) {
			tacno ++;
		}
		else {
			System.out.println("Username je kraci od 5 karaktera.");
		}
		if(txtUserPassword.getPassword().toString().length()>5) {
			tacno ++;
		}
		else {
			System.out.println("Password je kraci od 5 karaktera.");
		}
		if(txtUserIme.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Ime usera.");
		}
		if(txtUserPrezime.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Prezime usera.");
		}
		if(txtUserAdresa.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Adresu usera.");
		}
		if(txtUserBrojTelefona.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli broj telefona usera");
		}
		if(comboPol.getSelectedItem()!=null) {
			tacno++;
		}
		else {
			System.out.println("Niste odabrali pol usera.");
		}
		if(comboSluzbaS.getSelectedItem()!=null) {
			tacno++;
		}
		else {
			System.out.println("Niste odabrali sluzbu.");
		}
		try {
			int op = Integer.valueOf(txtUserPlata.getText());
			if(op>0) {
				tacno++;
			}
		} catch (Exception e) {
			System.out.println("Plata ne sme da sadrzi nista drugo osim brojeva.Proverite da li ste je uneli kako treba.");
		}
		if(tacno==10) {
			return true;
		}
		else {
			return false;
		}
		
	}

	
	public Boolean proveriVrednostiDoktor() {
		int tacno = 0;
		if(txtUserJmbg.getText().length()==13) {
			tacno ++;
		}
		else {
			System.out.println("JMBG Nije u tacnom formatu.");
		}
		if(txtUserUsername.getText().length()>5) {
			tacno ++;
		}
		else {
			System.out.println("Username je kraci od 5 karaktera.");
		}
		if(txtUserPassword.getPassword().toString().length()>5) {
			tacno ++;
		}
		else {
			System.out.println("Password je kraci od 5 karaktera.");
		}
		if(txtUserIme.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Ime usera.");
		}
		if(txtUserPrezime.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Prezime usera.");
		}
		if(txtUserAdresa.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli Adresu usera.");
		}
		if(txtUserBrojTelefona.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli broj telefona usera");
		}
		if(comboPol.getSelectedItem()!=null) {
			tacno++;
		}
		else {
			System.out.println("Niste odabrali pol usera.");
		}
		if(comboSluzba.getSelectedItem()!=null) {
			tacno++;
		}
		else {
			System.out.println("Niste odabrali sluzbu doktora.");
		}
		if(txtUserSpecijalizacija.getText().length()>0) {
			tacno++;
		}
		else {
			System.out.println("Niste uneli specijalizaciju doktora.");
		}
		try {
			int pla = Integer.valueOf(txtUserPlata.getText());
			if(pla>0) {
				tacno++;
			}
		} catch (Exception e) {
			System.out.println("Niste uneli platu.");
		}
		if(tacno==11) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void ocistiDugme(JButton dugme) {
		for (ActionListener dugmeAkcija : dugme.getActionListeners()) {
			dugme.removeActionListener(dugmeAkcija);
		}
	}
	
}

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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accounts.Pacijent;
import accounts.Doktor;
import accounts.MedicinskaSestra;

import net.miginfocom.swing.MigLayout;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;


public class Profil extends GuiFunctions {
	
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
	
	public Profil(MedicinskaSestra user) {
		prozor(user);
	}
	public Profil(String jmbgtarget,MedicinskaSestra user) {
		prozor(jmbgtarget,user);
		
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
			eventsSestra(target1,user);
		}
		else if (target2!=null) {
			initGUI(target2,user);
			eventsSestra(target2,user);
		}
		else if(target3!=null) {
			initGUI(target3,user);
			eventsSestra(target3,user);
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
		izmenaProzoraZbogTipa();
		
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
		add(comboTip);
		comboTip.setSelectedItem(null);
		
		
		add(lUserPol);
		comboPol.addItem("Musko");
		comboPol.addItem("Zensko");
		add(comboPol);
		comboPol.setSelectedItem(null);
		
		checkBoxStatus.setSelected (false);
		add(lUserStatus);add(checkBoxStatus);
		
		add(btnSacuvaj);add(btnCancel);
	}
	
	public void eventsSestra(Pacijent target,MedicinskaSestra user) {
		
		btnSacuvaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String stanje = null;
				if(checkBoxStatus.isSelected()) {
					stanje="aktivan";
				}
				else {
					stanje="ugasen";
				}
				user.setJmbg(txtUserJmbg.getText());
				user.setAdresa(txtUserAdresa.getText());
				user.setBrojTelefona(txtUserBrojTelefona.getText());
				
				user.sacuvajUsera(user, stanje);
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Profil.this.setVisible(false);
				Profil.this.dispose();
			}
		});
		
		
	}
	public void eventsSestra(MedicinskaSestra target,MedicinskaSestra user) {
		
		
	}
	public void eventsSestra(Doktor target,MedicinskaSestra user) {
		
	}
	public void eventsSestra(MedicinskaSestra user) {
		
	}
	
	
	public void izmenaProzoraZbogTipa() {
		comboTip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menjanjeTipa((String)comboTip.getSelectedItem());
			}
		});
	}
	
	public void menjanjeTipa(String sadasnji) {
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
			add(comboPol);
			
			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
			revalidate();
			repaint();
			
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
			add(comboPol);
			

			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
			revalidate();
			repaint();
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
			add(comboPol);
			
			

			add(lUserStatus);add(checkBoxStatus);
			
			add(btnSacuvaj);add(btnCancel);
			revalidate();
			repaint();
		}
		else {
			System.out.println("Doslo je do greske prilikom menjanja tipa u prozoru Profil");
		}
	}
	
	
	
	
}

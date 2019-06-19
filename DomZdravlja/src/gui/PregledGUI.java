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
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import ostalo.Pregled;
import ostalo.Racun;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;
import startPackage.Status;
import startPackage.TipKnjizice;


public class PregledGUI extends KontrolnaTacka2 {
	
	private ArrayList<Pregled> pregledi = ucitajPreglede();
	
	
	private JLabel lIDPregleda = new JLabel("ID Pregleda : ");
	private JTextField txtIDPregleda = new JTextField(20);
	
	private JLabel lJMBGPac = new JLabel("JMBG Pacijenta : ");
	private JTextField txtJMBGPac = new JTextField(20);
	
	private JLabel lJMBGDok = new JLabel("JMBG Doktora : ");
	private JTextField txtJMBGDok = new JTextField(20);
	
	private JLabel lOpis = new JLabel("Opis pregleda : ");
	private JTextField txtOpis = new JTextField(20);
	
	private JLabel lSoba = new JLabel("Soba : ");
	private JTextField txtSoba = new JTextField(20);
	
	private JLabel lTermin = new JLabel("Termin : ");
	private JTextField txtTermin = new JTextField(20);
	
	private JLabel lStatus = new JLabel("Status : ");
	private JComboBox<Status> comboStatus = new JComboBox<Status>();
	
	
	
	private JButton btnSacuvaj = new JButton("Sacuvaj");
	private JButton btnCancel = new JButton("Cancel");
	
	
	
	public PregledGUI(Pregled pregled) {
		prozor();
		initGUI(pregled);
		eventsSestra();
	}
	public PregledGUI() {
		
	}
	
	public void prozor() {
		setTitle("Dom Zdravlja");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/4;
		int visinaprozora = (int)screenSize.getHeight()/4;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
	}
	
	
	public void initGUI(Pregled pregled) {
		
		txtIDPregleda.setText(pregled.getID());
		add(lIDPregleda);add(txtIDPregleda);
		
		txtJMBGPac.setText(pregled.getPacijent());
		add(lJMBGPac);add(txtJMBGPac);
		
		txtJMBGDok.setText(pregled.getDoktor());
		add(lJMBGDok);add(txtJMBGDok);
		
		txtOpis.setText(pregled.getOpis());
		add(lOpis);add(txtOpis);
		
		txtSoba.setText(pregled.getSoba());
		add(lSoba);add(txtSoba);
		
		txtTermin.setText(pregled.vratiFormatiranDatum());
		add(lTermin);add(txtTermin);
		
		comboStatus.addItem(Status.valueOf("Zatrazen"));
		comboStatus.addItem(Status.valueOf("Zakazan"));
		comboStatus.addItem(Status.valueOf("Otkazan"));
		comboStatus.addItem(Status.valueOf("Zavrsen"));
		add(lStatus);add(comboStatus);
		
		add(btnSacuvaj);add(btnCancel);
	}
	
	
	public void eventsSestra() {
		
		btnSacuvaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Boolean nasao = false;
				for (Pregled pregled : pregledi) {
					if(pregled.getID().equalsIgnoreCase(txtIDPregleda.getText())) {
						nasao=true;
						int cena = 0;
						Pacijent pac = jmbgUPacijenta(pregled.getPacijent());
						String kategorija = String.valueOf(pac.getKnjizica().getKategorija());
						if(kategorija.equalsIgnoreCase("prvi")) {
							cena = 300;
						}
						else if(kategorija.equalsIgnoreCase("drugi")) {
							cena = 50;
						}
						else if(kategorija.equalsIgnoreCase("treci")) {
							cena = 0;
						}
						else {
							System.out.println("Doslo je do greske.");
						}
						Racun racun = new Racun(jmbgUPacijenta(pregled.getPacijent()), pregled.getID(), cena, false);
						racunUFajl(racun);
					}
				}
				if(nasao==false) {
					JOptionPane.showMessageDialog( null, "ID Pregleda ne postoji u nasem sistemu.",
							"Greska", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						PregledGUI.this.setVisible(false);
						PregledGUI.this.dispose();
					}
				});
	}
	
	
}
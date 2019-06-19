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
import ostalo.Pregled;
import ostalo.Racun;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;
import startPackage.TipKnjizice;


public class IzdajRacun extends KontrolnaTacka2 {
	
	private ArrayList<Pregled> pregledi = ucitajPreglede();
	
	
	private JLabel lIDPregleda = new JLabel("ID Pregleda : ");
	private JTextField txtIDPregleda = new JTextField(20);
	
	
	
	private JButton btnSacuvaj = new JButton("Izdaj Racun");
	private JButton btnCancel = new JButton("Cancel");
	
	
	
	public IzdajRacun(MedicinskaSestra user) {
		prozor(user);
	}
	
	public void prozor(MedicinskaSestra user) {
		setTitle("Dom Zdravlja");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/4;
		int visinaprozora = (int)screenSize.getHeight()/10;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);
		initGUI(user);
		eventsSestra(user);
	}
	
	
	public void initGUI(MedicinskaSestra user) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		add(lIDPregleda);add(txtIDPregleda);
		
		add(btnSacuvaj);add(btnCancel);
	}
	
	
	public void eventsSestra(MedicinskaSestra user) {
		
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
						IzdajRacun.this.setVisible(false);
						IzdajRacun.this.dispose();
					}
				});
	}
	
	
}
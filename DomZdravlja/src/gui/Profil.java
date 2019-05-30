package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.w3c.dom.UserDataHandler;

import accounts.Pacijent;
import accounts.Doktor;
import accounts.Korisnik;
import accounts.MedicinskaSestra;

import net.miginfocom.swing.MigLayout;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;


public class Profil extends JFrame {
	
	private JLabel lUserJmbg = new JLabel("JMBG : ");
	private JTextField txtUserJmbg = new JTextField(20);
	
	private JLabel lUserSluzba = new JLabel("Sluzba : ");
	private JComboBox<SluzbaSestre> comboSluzba = new JComboBox<SluzbaSestre>();
	
	
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
	
	private JLabel lUserStatus = new JLabel("Aktivan nalog : ");
	private JCheckBox checkBoxStatus = new JCheckBox();
	
	
	public Profil(MedicinskaSestra user) {
		prozor(user);
		
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

	public void initGUI(MedicinskaSestra user) {
		
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		txtUserJmbg.setText(user.getJmbg());
		add(lUserJmbg);add(txtUserJmbg);
		
		//SLUZBA COMBO BOX
		add(lUserSluzba);
		comboSluzba.addItem(SluzbaSestre.SluzbaOpsteMedicine);
		comboSluzba.addItem(SluzbaSestre.SluzbaZaPravnoIEkonomskePoslove);
		comboSluzba.addItem(SluzbaSestre.SluzbaZaTehnickePoslove);
		comboSluzba.addItem(SluzbaSestre.SluzbaZdravstveneZastiteDece);
		comboSluzba.addItem(SluzbaSestre.SluzbaZdravstveneZastiteRadnika);
		comboSluzba.addItem(SluzbaSestre.StomatoloskaSluzba);
		add(comboSluzba);
		
		txtUserIme.setText(user.getIme());
		add(lUserIme);add(txtUserIme);
		
		txtUserPrezime.setText(user.getPrezime());
		add(lUserPrezime);add(txtUserPrezime);
		
		txtUserAdresa.setText(user.getAdresa());
		add(lUserAdresa);add(txtUserAdresa);
		
		txtUserBrojTelefona.setText(user.getBrojTelefona());
		add(lUserBrojTelefona);add(txtUserBrojTelefona);
		
		checkBoxStatus.setSelected (uzmiStanje(user.getUsername()));
		add(lUserStatus);add(checkBoxStatus);
		
		add(btnSacuvaj);add(btnCancel);
		
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
		System.out.println(vracanje);
		return vracanje;
	}
	
	
}

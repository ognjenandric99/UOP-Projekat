package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.OptionPaneUI;

import accounts.Doktor;
import accounts.MedicinskaSestra;
import accounts.Pacijent;
import net.miginfocom.swing.MigLayout;

public class Login extends JFrame {
	private ArrayList<Pacijent> pacijenti = new ArrayList<Pacijent>();
	private ArrayList<Doktor> doktori = new ArrayList<Doktor>();
	private ArrayList<MedicinskaSestra> sestre = new ArrayList<MedicinskaSestra>();
	
	
	JLabel lporuka = new JLabel("Dom Zdravlja");
	JLabel lprazno = new JLabel("");
	
	JLabel lusername = new JLabel("Username : ");
	JTextField txtusername = new JTextField(20);
	
	JLabel lpassword = new JLabel("Password : ");
	JPasswordField txtpassword = new JPasswordField(20);
	
	JButton loginbtn = new JButton("Login");
	JButton cancelbtn = new JButton("Cancel");
	public Login() {
		setTitle("Dom Zdravlja | LOGIN");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/2;
		int visinaprozora = (int)screenSize.getHeight()/2;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);

		initGUI();
		events();
		
	}
	
	public void ucitajPacijente() {
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("pacijent")) {
				Pacijent pac = new Pacijent(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				pacijenti.add(pac);
				
				
			}
			
		}
	}
	public void ucitajDoktore() {
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("Doktor") && xacc[9].equalsIgnoreCase("aktivan")) {
				Doktor dok = new Doktor(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				doktori.add(dok);
			}
			
		}
	}
	public void ucitajSestre() {
		String fajl = getText("src/accounts/accounts.txt");
		String[] accoutnsredovi = fajl.split("\\;");
		
		for(int i=0;i<accoutnsredovi.length;i++) {
			String[] xacc = accoutnsredovi[i].split("\\|");
			if(xacc[3].equalsIgnoreCase("MedicinskaSestra") && xacc[9].equalsIgnoreCase("aktivan")) {
				MedicinskaSestra ses = new MedicinskaSestra(xacc[4],xacc[5],xacc[2],xacc[6],xacc[7],xacc[0],xacc[1],xacc[3],Boolean.valueOf(xacc[8]));
				sestre.add(ses);
			}
			
		}
	}
	
	
	

	public void initGUI() {
		MigLayout mig = new MigLayout("al center center,wrap 2");
		setLayout(mig);
		
		add(lporuka,"center, span 2");
		add(lusername);add(txtusername);
		add(lpassword);add(txtpassword);
		add(lprazno);add(loginbtn,"split 2,center");add(cancelbtn);
		
	}
	
	public Boolean proverivrednosti() {
		String username = txtusername.getText().trim();
		String password = new String(txtpassword.getPassword()).trim();
		Boolean moze = true;
		
		if(username.length()<5 || password.length()<5) {
			moze = false;
		}
		
		return moze;
		
	}
	
	public void loginuj() {
		ucitajPacijente();
		ucitajDoktore();
		ucitajSestre();
		
		String username = txtusername.getText().trim();
		String password = String.valueOf(txtpassword.getPassword()).trim();
		Boolean stanje = false;
		
		for (Pacijent pacijent : pacijenti) {
			if(pacijent.getUsername().equalsIgnoreCase(username) && pacijent.getPassword().equals(password)){
				Pacijent user = pacijent;
				stanje = true;
				MainGui gui = new MainGui(user);
				gui.setVisible(true);
				this.setVisible(false);
				this.dispose();
			}
		}
		for (Doktor doktor : doktori) {
			if(doktor.getUsername().equalsIgnoreCase(username) && doktor.getPassword().equals(password)) {
				Doktor user = doktor;
				stanje = true;
				MainGui gui = new MainGui(user);
				gui.setVisible(true);
				this.setVisible(false);
				this.dispose();
			}
		}
		for (MedicinskaSestra medicinskaSestra : sestre) {
			if(medicinskaSestra.getUsername().equalsIgnoreCase(username) && medicinskaSestra.getPassword().equals(password)){
				MedicinskaSestra user = medicinskaSestra;
				stanje = true;
				MainGui gui = new MainGui(user);
				gui.setVisible(true);
				this.setVisible(false);
				this.dispose();
			}
		}
		if(stanje!=false) {
			String poruka = "Uspesno ste ulogovani!";
			JOptionPane.showMessageDialog( null, poruka,
					"Uspesan login", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog( null, "Molimo Vas da unesete tacan username i sifru.", "Greška",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public void loginProsaoPacijent(Pacijent user) {
			
	}
	public void loginProsaoDoktor(Doktor user) {
		
	}
	public void loginProsaoMedicinskaSestra(MedicinskaSestra user) {
		
	}
	public void events() {
		
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(proverivrednosti()) {
					loginuj();
				}
				else {
					JOptionPane.showMessageDialog( null, "Molimo Vas da unesete tacan username i sifru.", "Greška",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
				Login.this.setVisible(false);
			}
		});
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
}

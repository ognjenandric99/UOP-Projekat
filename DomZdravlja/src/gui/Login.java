package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
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
import ostalo.Knjizica;
import ostalo.Pregled;

public class Login extends GuiFunctions {
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	
	JLabel lporuka = new JLabel("Dom Zdravlja");
	JLabel lprazno = new JLabel("");
	
	JLabel lusername = new JLabel("Username : ");
	JTextField txtusername = new JTextField(20);
	
	JLabel lpassword = new JLabel("Password : ");
	JPasswordField txtpassword = new JPasswordField(20);
	
	JButton loginbtn = new JButton("Login");
	JButton cancelbtn = new JButton("Cancel");
	public Login() {
		proveritiKnjizice();
		setTitle("Dom Zdravlja | LOGIN");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth()/2;
		int visinaprozora = (int)screenSize.getHeight()/2;
		setSize(sirinaprozora,visinaprozora);
		setResizable(true);

		initGUI();
		events();
		
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
	
	
	public void proveritiKnjizice() {
		ArrayList<Knjizica> knjizice = new ArrayList<Knjizica>();
		ArrayList<Pacijent> pac1 = ucitajPacijente();
		for (Pacijent pacijent : pac1) {
			Knjizica knj = pacijent.getKnjizica();
			if(LocalDateTime.now().compareTo(knj.getDatumIsteka())>=0) {
				knj.setAktivna(false);
			}
			knjizice.add(knj);
		}
		String text = "";
		for (Knjizica knjizica : knjizice) {
			String istek = knjizica.getDatumIsteka().getYear()+"-"+knjizica.getDatumIsteka().getMonthValue()+"-"+knjizica.getDatumIsteka().getDayOfMonth();
			text += knjizica.getPacijent()+"|"+knjizica.getKategorija()+"|"+istek+"|"+knjizica.getAktivna().toString()+"\n";
		}
		ispisiUFajl("src/ostalo/knjizice.txt", text);
	}
	public void ocistiKnjizice() {
		ArrayList<Knjizica> knjizice = new ArrayList<Knjizica>();
		String[] linije = getText("src/ostalo/knjizice.txt").split("\\;");
		for (String string : linije) {
			String[] tk = string.split("\\|");
			Boolean stanje = false;
			for (Knjizica knj : knjizice) {
				if(knj.getPacijent().equalsIgnoreCase(tk[0])) {
					stanje=true;
				}
			}
			if(stanje==false) {
				Knjizica knjizica = new Knjizica(tk[0]);
				knjizice.add(knjizica);
			}
		}
		sacuvajKnjiziceUFajl(knjizice);
		
	}
	
}

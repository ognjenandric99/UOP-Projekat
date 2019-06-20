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
import ostalo.Pregled;
import startPackage.Sluzba;
import startPackage.SluzbaSestre;
import startPackage.TipKnjizice;


public class TabelaPregled extends KontrolnaTacka2 {
	
	private ArrayList<Pacijent> pacijenti = ucitajPacijente();
	private ArrayList<Doktor> doktori = ucitajDoktore();
	private ArrayList<MedicinskaSestra> sestre = ucitajSestre();
	
	private ArrayList<Pregled> pregledi1;
	private ArrayList<Pregled> pregledi;
	
	private JButton izmeni = new JButton("Pogledaj");
	private JButton obrisi = new JButton("Obrisi");
	
	
	TabelaPregled(Pacijent user){
		prozor();
		initGUI(user);
	}
	TabelaPregled(MedicinskaSestra user){
		prozor();
		initGUI(user);
	}
	TabelaPregled(Doktor user){
		prozor();
		initGUI(user);
	}
	TabelaPregled(String tip,MedicinskaSestra user){
		prozor();
		initGUI(tip,user);
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
		add(izmeni);
		pregledi = ucitajPreglede(doktor);
		String[] zaglavlja = new String[] { "ID Pregleda", "JMBG Pacijenta", "JMBG Doktora ","Opis pregleda","Soba","Termin","Status"};
		Object[][] sadrzaj = new Object[pregledi.size()][zaglavlja.length];
		int i=0;
		while(i<pregledi.size()) {
			sadrzaj[i][0] = pregledi.get(i).getID();
			sadrzaj[i][1] = pregledi.get(i).getPacijent();
			sadrzaj[i][2] = pregledi.get(i).getDoktor();
			sadrzaj[i][3] = pregledi.get(i).getOpis();
			sadrzaj[i][4] = pregledi.get(i).getSoba();
			sadrzaj[i][5] = pregledi.get(i).vratiFormatiranDatum();
			sadrzaj[i][6] =  String.valueOf(pregledi.get(i).getStatus()).toString();
			i++;
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
		izmeni.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						izmeniPregled(pregledi.get(tabela.getSelectedRow()),doktor.getJmbg());
					}
				});
	}
	
	public void initGUI(MedicinskaSestra sestra) {
		
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		add(izmeni);add(obrisi);
		pregledi = ucitajPreglede();
		String[] zaglavlja = new String[] { "ID Pregleda", "JMBG Pacijenta", "JMBG Doktora ","Opis pregleda","Soba","Termin","Status"};
		Object[][] sadrzaj = new Object[pregledi.size()][zaglavlja.length];
		int i=0;
		while(i<pregledi.size()) {
			sadrzaj[i][0] = pregledi.get(i).getID();
			sadrzaj[i][1] = pregledi.get(i).getPacijent();
			sadrzaj[i][2] = pregledi.get(i).getDoktor();
			sadrzaj[i][3] = pregledi.get(i).getOpis();
			sadrzaj[i][4] = pregledi.get(i).getSoba();
			sadrzaj[i][5] = pregledi.get(i).vratiFormatiranDatum();
			sadrzaj[i][6] = String.valueOf(pregledi.get(i).getStatus()).toString();
			i++;
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
		
		izmeni.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						izmeniPregled(pregledi.get(tabela.getSelectedRow()),sestra.getJmbg());
					}
				});
		obrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if(tabela.getSelectedRow()>-1) {
						obrisiPregled(pregledi.get(tabela.getSelectedRow()));
						JOptionPane.showMessageDialog( null, "Uspesno ste obrisali pregled.",
								"Info", JOptionPane.INFORMATION_MESSAGE);
						TabelaPregled.this.dispose();
						TabelaPregled.this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog( null, "Molimo Vas da prvo oznacite red.",
								"Info", JOptionPane.INFORMATION_MESSAGE);
					}
					
					
			}
		});
	}
	
	public void initGUI(Pacijent pacijent) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		add(izmeni);
		pregledi = ucitajPreglede(pacijent);
		String[] zaglavlja = new String[] { "ID Pregleda", "JMBG Pacijenta", "JMBG Doktora ","Opis pregleda","Soba","Termin","Status"};
		Object[][] sadrzaj = new Object[pregledi.size()][zaglavlja.length];
		int i=0;
		while(i<pregledi.size()) {
			sadrzaj[i][0] = pregledi.get(i).getID();
			sadrzaj[i][1] = pregledi.get(i).getPacijent();
			sadrzaj[i][2] = pregledi.get(i).getDoktor();
			sadrzaj[i][3] = pregledi.get(i).getOpis();
			sadrzaj[i][4] = pregledi.get(i).getSoba();
			sadrzaj[i][5] = pregledi.get(i).vratiFormatiranDatum();
			sadrzaj[i][6] =  String.valueOf(pregledi.get(i).getStatus()).toString();
			i++;
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
		izmeni.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(pregledi.get(tabela.getSelectedRow()).getStatus().toString().equalsIgnoreCase("Zatrazen")) {
							izmeniPregled(pregledi.get(tabela.getSelectedRow()),pacijent.getJmbg());
						}
						else {
							System.out.println("Pregled nije u statusu ,,Zatrazen'' stoga je pacijentu zabranjeno menjanje njegovih atributa.");
						}
					}
				});
	}
	
	public void izmeniPregled(Pregled pregled, String jmbg) {
		PregledGUI pregled1 = new PregledGUI(pregled,jmbg);
		pregled1.setVisible(true);
	}
	
	public void initGUI(String tip, MedicinskaSestra user) {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sirinaprozora = (int)screenSize.getWidth();
		int visinaprozora = (int)screenSize.getHeight();
		add(izmeni);add(obrisi);
		
		pregledi1 = ucitajPreglede();
		pregledi= new ArrayList<Pregled>();
		for (Pregled pregled : pregledi1) {
					if(pregled.getStatus().toString().equalsIgnoreCase(tip)) {
						pregledi.add(pregled);
					}
		}
		String[] zaglavlja = new String[] { "ID Pregleda", "JMBG Pacijenta", "JMBG Doktora ","Opis pregleda","Soba","Termin","Status"};
		Object[][] sadrzaj = new Object[pregledi.size()][zaglavlja.length];
		int i=0;
		
		
		while(i<pregledi.size()) {
			if(pregledi.get(i).getStatus().toString().equalsIgnoreCase(tip)) {
				sadrzaj[i][0] = pregledi.get(i).getID();
				sadrzaj[i][1] = pregledi.get(i).getPacijent();
				sadrzaj[i][2] = pregledi.get(i).getDoktor();
				sadrzaj[i][3] = pregledi.get(i).getOpis();
				sadrzaj[i][4] = pregledi.get(i).getSoba();
				sadrzaj[i][5] = pregledi.get(i).vratiFormatiranDatum();
				sadrzaj[i][6] =  String.valueOf(pregledi.get(i).getStatus()).toString();
				i++;
			}
			else {
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
		
		izmeni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				izmeniPregled(pregledi.get(tabela.getSelectedRow()),user.getJmbg());
			}
		});
	}
	public void events() {
		
	}
}

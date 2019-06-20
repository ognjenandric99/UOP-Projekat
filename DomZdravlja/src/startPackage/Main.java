package startPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import accounts.Doktor;
import accounts.Korisnik;
import accounts.MedicinskaSestra;
import accounts.Pacijent;
import gui.Login;
import gui.MainGui;
import ostalo.Pregled;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Login login = new Login();
		//login.setVisible(true);
		
		
		/*
		 * ODRADITI SLEDECE

		 * 4) Pacijent - Zakazivanje pregleda - Status Zatrazen
		 * 6) Doktor - menjanje statusa pregleda u Otkazan/Zavrsen
		 * 7) Sestra - Zatrazen u zakazan/otkazan
		 * 9)Pacijent - Menjanje opisa kada je status Zatrazen
		 * ODRADITI MENJANJE PREGLEDA
		 * 
		 */
		
		DateTimeFormatter fr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date datum = new Date();
		LocalDateTime vreme = LocalDateTime.now();
		LocalDateTime nekoVreme = LocalDateTime.of(1999,10,11,12,12);
		
		
		System.out.println(sdf.format(datum));
		System.out.println(fr.format(vreme));
		System.out.println(fr.format(nekoVreme));
		
	}
}

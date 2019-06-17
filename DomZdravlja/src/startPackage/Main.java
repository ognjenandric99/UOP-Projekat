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
		Login login = new Login();
		login.setVisible(true);
		
	}
}

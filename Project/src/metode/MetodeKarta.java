package metode;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import Pretraga_Sortiranje.PSKarata;
import korisnici.Biletar;
import korisnici.Korisnik;
import korisnici.Menadzer;
import mein.Main;
import organizacija.Karta;
import organizacija.Sediste;
import poz.Izvodjenje;
import unos.Utility;

public class MetodeKarta {
	private static Scanner sc;
	public static String path = "." + System.getProperty("file.separator");

	public static void ucitajKartu(ArrayList<Karta> prodateKarte) throws IOException, ParseException {
		BufferedReader bf = new BufferedReader(new FileReader(path + "karte.txt"));
		String linija;
		while ((linija = bf.readLine()) != null) {
			try{
				Karta karta = new Karta(linija);
				prodateKarte.add(karta);
				
			}catch (Exception e){
				//do nothing
			}
		}
	}

	public static void upisiKartu(ArrayList<Karta> prodateKarte) throws IOException {
		PrintWriter p = new PrintWriter(new FileWriter(path + "karte.txt"));
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		for (Karta karta : prodateKarte) {
			Date datumKarte = karta.getVremeIzdavanja();
			String datumZaUpis = formater.format(datumKarte);
			String linija = String.format("%s|%s|%s|%s|%s|%s", karta.getSerijskiBroj(), karta.getCena(),
					karta.getPopust(), datumZaUpis, karta.getIzvodjenje().getIndentifikator(), karta.getSediste());

			p.append(linija);
			p.append("\n");
		}
		p.close();
	}

	public static void mogucaIzvodjenja(ArrayList<Izvodjenje> listaMogucihIzvodjenja,
			ArrayList<Izvodjenje> listaIzvodjenja) throws ParseException {

		SimpleDateFormat sp = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		Date now = new Date();
		String nowKonverted = sp.format(now);
		Date datumZaKomparacijuTrenutni = sp.parse(nowKonverted);
		for (int i = 0; i < listaIzvodjenja.size(); i++) {
			Date datumIzvodjenja = listaIzvodjenja.get(i).getVremePocetka();
			String datumIzvodjenjaKonverted = sp.format(datumIzvodjenja);
			Date datumIzvodjenjaZaKomparaciju = sp.parse(datumIzvodjenjaKonverted);
			if (datumIzvodjenjaZaKomparaciju.after(datumZaKomparacijuTrenutni)) {
				for (int j = 0; j < listaIzvodjenja.get(i).getScena().getSedista().size(); j++) {
					if ((listaIzvodjenja.get(i).getScena().getSedista().size() > 0)) {
						listaMogucihIzvodjenja.add(listaIzvodjenja.get(i));
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	public static void prodajKartu(ArrayList<Izvodjenje> listaIzvodjenja, ArrayList<Karta> prodateKarte,
			Korisnik aktivanKorisnik, ArrayList<Karta> listaKarata) throws ParseException, IOException {

		Sediste sed = null;
		int popust = 0;
		double cena = 0;
		Date trenutnoVreme = new Date();
		sc = new Scanner(System.in);
		Random ranInden = new Random();
		int serijskiBroj = ranInden.nextInt(8999) + 1000;
		String konvert = String.valueOf(serijskiBroj);
		char random_3_Char = (char) (ranInden.nextInt(26) + 'A');
		String sB = konvert + random_3_Char;
		Izvodjenje izv = null;
		ArrayList<Izvodjenje> mogucaIzvodjenja = new ArrayList<Izvodjenje>();
		mogucaIzvodjenja(mogucaIzvodjenja, listaIzvodjenja);
		MetodeIzvodjenja.prikaziIzvodjena(mogucaIzvodjenja);

		int indentifikator = 0;
		boolean pronasao = false;
		while (pronasao == false) {
			System.out.print("Unesite indentifikator izvodjenja: ");
			indentifikator = Utility.unesiInt(sc);
			int i;
			for (i = 0; i < mogucaIzvodjenja.size(); i++) {
				if (indentifikator == mogucaIzvodjenja.get(i).getIndentifikator()) {
					izv = mogucaIzvodjenja.get(i);
					System.out.println(mogucaIzvodjenja.get(i));
					pronasao = true;
					break;
				}
			}
			if (i == mogucaIzvodjenja.size()) {
				pronasao = false;
			}
		}
		for (int i = 0; i < izv.getScena().getSedista().size(); i++) {
			System.out.println(izv.getScena().getSedista().get(i) + " ");
		}
		boolean foundOne = false;
		while (foundOne == false) {
			System.out.print("Unesite red: ");
			int red = Utility.unesiInt(sc);
			System.out.print("Unesite broj: ");
			int broj = Utility.unesiInt(sc);
			for (int i = 0; i < izv.getScena().getSedista().size(); i++) {
				boolean pronasao2 = false;
				sed = izv.getScena().getSedista().get(i);
				if ((red == sed.getRed()) && broj == sed.getBroj()) {
					sed = izv.getScena().getSedista().get(i);
					izv.getScena().getSedista().remove(sed);
					foundOne = true;
					break;
				}
			}
		}
		System.out.print("Da li zelite da unese popust?(Da/Ne): ");
		String odluka = Utility.unesiOdluku(sc);
		if (odluka.equalsIgnoreCase("Da")) {
			System.out.print("Unesite popust: ");
			popust = Utility.unesiInt(sc);
			double cenaSaPopustom = (izv.getCenaKarte() / 100 * (100 - popust));
			cena = cenaSaPopustom;
		} else if (odluka.equalsIgnoreCase("Ne")) {
			popust = 0;
			cena = izv.getCenaKarte();
		}
		Karta karta = new Karta(sB, cena, popust, trenutnoVreme, izv, sed);
		listaKarata.add(karta);

		ArrayList<Karta> prodKarte = new ArrayList<Karta>();
		if (((Biletar) aktivanKorisnik).getProdatekarte() != null) {
			prodKarte.addAll(((Biletar) aktivanKorisnik).getProdatekarte());
			prodKarte.add(karta);
			((Biletar) aktivanKorisnik).setProdatekarte(prodKarte);
			//izv.setProdatekarte(prodKarte);
		} else {
			prodKarte.add(karta);
			((Biletar) aktivanKorisnik).setProdatekarte(prodKarte);
			//izv.setProdatekarte(prodKarte);
		}
		if(izv.getProdatekarte()== null){
			ArrayList<Karta> k = new ArrayList<Karta>();
			k.add(karta);
			izv.setProdatekarte(k);
		}else{
			ArrayList<Karta> k = new ArrayList<Karta>();
			k.addAll(izv.getProdatekarte());
			k.add(karta);
			izv.setProdatekarte(k);
		}

		MetodeKorisnika.upisKorisnika(Main.listaKorisnika);
		MetodeKarta.upisiKartu(Main.listaKarata);
		MetodeIzvodjenja.upisiIzvodjenje(Main.izvodjenje);
		MetodeScena.upisiScene(Main.listaScena);
		System.out.printf("Karta '%s' je upisana korisniku %-5s  %s", karta.getSerijskiBroj(), aktivanKorisnik.getIme(),
				aktivanKorisnik.getPrezime());
		System.out.println();
	}

	public static void pronalazenjeKartePoSerijskomBroju(ArrayList<Karta> listaKarata) {
		sc = new Scanner(System.in);
		System.out.print("Unesite serijski broj karte: ");
		String sBroj = Utility.unesiTekst(sc);
		boolean pronasao = false;
		for (Karta k : listaKarata) {
			if (sBroj.equalsIgnoreCase(k.getSerijskiBroj())) {
				pronasao = true;
				break;
			}
		}
		if (pronasao == false) {
			System.out.println("Karta ne postoji! Pokusajte ponovo....");
			pronalazenjeKartePoSerijskomBroju(listaKarata);
		}
	}

	public static void prikazKarta(ArrayList<Karta> listaKarata) {

		String n = String.format("%-19s |%-29s |%-15s |%-8s |%-8s |%-30s |%-5s |%-10s", "Naziv predstave",
				"Vreme pocetka", "Naziv scene", "Cena", "Popust", "Vreme izdavanja", "Serijski broj", "Sediste");

		System.out.println(n);
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");
		for (Karta k : listaKarata) {
			System.out.println(k.toString());
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------");

		}

	}

	public static void prikazSvihKarata(ArrayList<Karta> listaKarata) {
		sc = new Scanner(System.in);
		boolean uradio = false;
		while (uradio == false) {
			System.out.println(
					"Izaberite kakav prikaz zelite\n1----> Vreme izdavanja\n2----> Popust\n3----> Naziv predstave,Vreme , popust");
			int unos = Utility.unesiInt(sc);
			switch (unos) {
			case 1:
				PSKarata.sortiranjePoVremenuIzdavanja(listaKarata);
				uradio = true;
				break;
			case 2:
				PSKarata.sortiranjePoPopustu(listaKarata);
				uradio = true;
				break;
			case 3:
				PSKarata.sortiranjePoNazivuVremenuPocetkaVremenuIzvodjenja(listaKarata);
				uradio = true;
				break;
			}
		}

		prikazKarta(listaKarata);

	}

	public static void izvodjenja(ArrayList<Izvodjenje> listaIzvodjenja) {
		for (Izvodjenje i : listaIzvodjenja) {
			if (i.getProdatekarte() != null) {
				i.setAktivnost(true);
			}
		}

	}

}

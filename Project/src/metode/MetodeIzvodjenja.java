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

import Pretraga_Sortiranje.PSizvodjenja;
import mein.Main;
import organizacija.Karta;
import poz.Izvodjenje;
import poz.Predstava;
import poz.Scena;
import unos.Utility;

public class MetodeIzvodjenja {
	private static Scanner sc;
	public static String path = "." + System.getProperty("file.separator");

	public static void upisiIzvodjenje(ArrayList<Izvodjenje> izvodjenje) throws IOException {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		PrintWriter upis = new PrintWriter(new FileWriter(path + "izvodjenje.txt"));
		for (Izvodjenje i : izvodjenje) {
			String nazivPredstave = i.getPredstava().getNaziv();
			String nazivScene = i.getScena().getNaziv();
			Date datumIzObjekta = i.getVremePocetka();
			String vreme = formater.format(datumIzObjekta);
			if (i.getProdatekarte() != null) {
				if (i.getProdatekarte().size() > 0) {
					String[] prodateKarte = new String[i.getProdatekarte().size()];
					for (int a = 0; a < i.getProdatekarte().size(); a++) {
						prodateKarte[a] = i.getProdatekarte().get(a).getSerijskiBroj();
					}
					String prodateKarteZaUpis = String.join(",", prodateKarte);
					String linijaZaUpis = String.format("%s|%s|%s|%s|%s|%s|%s", i.getIndentifikator(), vreme,
							i.getCenaKarte(), nazivPredstave, nazivScene, prodateKarteZaUpis, i.getAktivnost());
					upis.append(linijaZaUpis);
					upis.append("\n");
				}
			} else {
				String linijaZaUpis = String.format("%s|%s|%s|%s|%s|%s|%s", i.getIndentifikator(), vreme,
						i.getCenaKarte(), nazivPredstave, nazivScene, "null", i.getAktivnost());
				upis.append(linijaZaUpis);
				upis.append("\n");

			}

		}
		upis.close();

	}

	public static void ucitajIzvodjenje(ArrayList<Izvodjenje> izvodjenje) throws IOException, ParseException {
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(new FileReader(path + "izvodjenje.txt"));
		String linija;
		while ((linija = bf.readLine()) != null) {
			try{
				Izvodjenje izv = new Izvodjenje(linija);
				izvodjenje.add(izv);
				
			}catch (Exception e){
				//do nothing
			}
		}
		bf.close();
	}

	public static void prikaziIzvodjena(ArrayList<Izvodjenje> izvodjenje) {
		System.out.printf("%-8s %-30s %-11s %-21s %-10s", "Indentifikator", "Vreme pocetka", "Cena karte",
				"Naziv predstave", "Naziv Scene");
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------");
		for (Izvodjenje izv : izvodjenje) {
			if (izv.getAktivnost() != false) {
				System.out.println(izv.toString());
				System.out.println(
						"------------------------------------------------------------------------------------------");
			}
		}
	}

	public static void dodajIzvodjenje(ArrayList<Izvodjenje> izvodjenje, ArrayList<Predstava> pred,
			ArrayList<Scena> scena) throws ParseException {

		sc = new Scanner(System.in);
		boolean flag = false;
		String naziv;
		Date vrIzvodjenja = null;
		Random random = new Random();
		int indentifikator = random.nextInt(8999) + 1000;
		Predstava p = null;

		while (flag == false) {
			System.out.println("Unesite naziv predstave");
			naziv = Utility.unesiTekst(sc);
			int i;
			for (i = 0; i < pred.size(); i++) {
				if (naziv.equalsIgnoreCase(pred.get(i).getNaziv())) {
					p = pred.get(i);
					flag = true;
				}

			}
			if (flag == false) {
				System.out.println("Nisam pronasao predstavu");
			}
		}
		boolean validanDatum = false;
		while (validanDatum == false) {

			System.out.println("Unesite Vreme izvodjenja(dd-MM-yyyy HH-mm)");
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm");
			vrIzvodjenja = Utility.unesiVreme(sc);
			Date datumZaKonvertovanje = new Date();
			String sadasnjeVreme = format.format(datumZaKonvertovanje);
			Date konvertovaniTrenutniDatum = format.parse(sadasnjeVreme);
			if (vrIzvodjenja.before(konvertovaniTrenutniDatum)) {
				System.out.println("Uneli ste datum iz proslosti");
			} else if (vrIzvodjenja.after(konvertovaniTrenutniDatum)) {
				validanDatum = true;
			}
		}
		System.out.println("Unesite Cenu izvodjenja");
		double cena = Utility.unesiDouble(sc);
		String parametarZaPretraguScene;
		Scena scenaObj = null;
		boolean flag2 = false;
		MetodeScena.prikaziScene(Main.listaScena);
		while (flag2 == false) {
			System.out.print("Unesite naziv scene: ");
			parametarZaPretraguScene = Utility.unesiTekst(sc);
			int i;
			for (i = 0; i < scena.size(); i++) {
				if (parametarZaPretraguScene.equalsIgnoreCase(scena.get(i).getNaziv())) {
					scenaObj = scena.get(i);
					flag2 = true;
					break;
				}
			}
			if (i == scena.size()) {
				System.out.println("Ne postojeca scena!!!");
				flag2 = false;
			}
		}
		ArrayList<Karta> prodateKarte = null;
		boolean aktivnost = true;

		Izvodjenje i = new Izvodjenje(indentifikator, vrIzvodjenja, cena, p, scenaObj, aktivnost, prodateKarte);
		izvodjenje.add(i);
	}

	public static void inicijalizujKarte(ArrayList<Karta> listaKarata, ArrayList<Izvodjenje> izvodjenje)
			throws IOException, ParseException {
		BufferedReader bf = new BufferedReader(new FileReader(path + "karte.txt"));
		String linija;
		ArrayList<String> stringoviKarata = new ArrayList<String>();
		while ((linija = bf.readLine()) != null) {
			stringoviKarata.add(linija);
		}
		for (Izvodjenje izv : izvodjenje) {
			ArrayList<Karta> listaKarataIzvodjenje = new ArrayList<Karta>();
			for (int i = 0; i < stringoviKarata.size(); i++) {
				Karta k = new Karta(stringoviKarata.get(i));
				if (izv.getIndentifikator() == k.getIzvodjenje().getIndentifikator()) {
					ArrayList<Karta> nzm = new ArrayList<Karta>();
					listaKarataIzvodjenje.add(k);
					nzm.addAll(listaKarataIzvodjenje);
					izv.setProdatekarte(nzm);
				}
			}
		}
	}

	public static void obrisiIzvodjenje(ArrayList<Izvodjenje> izvodjenje) throws IOException {
		sc = new Scanner(System.in);
		ArrayList<Izvodjenje> listaIzvodjenja = new ArrayList<Izvodjenje>();
		System.out.println("Izvodjenja koja mozete obrisati....");
		for (Izvodjenje izv : izvodjenje) {
			if (izv.getProdatekarte() == null || izv.getProdatekarte().isEmpty()) {
				System.out.println(izv.toString());
				System.out.println("----------------------------------------------------------------------------");
			}
		}
		System.out.print("Unesite indentifikator izvodjenja: ");
		String indent = Utility.unesiTekst(sc);
		int indentifikator = Integer.valueOf(indent);
		boolean pronasaoIzv = false;
		for (Izvodjenje i : izvodjenje) {
			if (i.getProdatekarte() == null || i.getProdatekarte().isEmpty()) {
				if (indentifikator == i.getIndentifikator()) {
					pronasaoIzv = true;
					i.setAktivnost(false);
				}
			}
			
		}if(pronasaoIzv == false){
			System.out.println("Pogresno ste uneli: ");
			obrisiIzvodjenje(izvodjenje);
		}
		upisiIzvodjenje(izvodjenje);

		/*
		 * sc = new Scanner(System.in);
		 * System.out.print("Unesite intentifikator izvodjenja: "); String naziv
		 * = Utility.unesiTekst(sc); Izvodjenje i = null; boolean
		 * pronasaoIzvodjenje = false; for (Izvodjenje izv : izvodjenje) { if
		 * (izv.getAktivnost() != false) { if
		 * (naziv.equalsIgnoreCase(String.valueOf(izv.getIndentifikator()))) { i
		 * = izv; pronasaoIzvodjenje = true; System.out.println(i.toString()); }
		 * } } int brojac = listaKarata.size(); if (pronasaoIzvodjenje == true)
		 * { for (Karta k : listaKarata) { brojac--; if (i.getIndentifikator()
		 * == k.getIzvodjenje().getIndentifikator()) { pronasaoIzvodjenje =
		 * false; System.out.println("Prodata je karta za ovu predstave!!!");
		 * brojac++; } else if (brojac == 0) { i.setAktivnost(false); } } } if
		 * (pronasaoIzvodjenje == false ) { obrisiIzvodjenje(izvodjenje,
		 * listaKarata); }
		 */
	}

	public static void pretragaIzvodjenja(ArrayList<Izvodjenje> izvodjenje) {
		sc = new Scanner(System.in);
		System.out.println("Odaberite nacin pretrage:" + "\n1----> Pretraga po nazivu predstave"
				+ "\n2----> Pretraga po tipu predstave" + "\n3----> Pretraga po godini premijere"
				+ "\n4----> Pretraga po reziseru" + "\n5---->Naziv Reziser Glumac"
				+ "\n7----> Pretraga po vreenu izvodjenja" + "\n8----> Pretraga po nazivu scene");
		int komanda = Utility.unesiInt(sc);
		switch (komanda) {
		case 1:
			PSizvodjenja.pretragaIzvodjenjaNaziv(izvodjenje);
			break;
		case 2:
			PSizvodjenja.pretragaIzvodjenjaTipPredstave(izvodjenje);
			break;
		case 3:
			PSizvodjenja.pretragaIzvodjenjaGodinaPremijere(izvodjenje);
			break;
		case 4:
			PSizvodjenja.pretragaIzvodjenjaReziser(izvodjenje);
			break;
		case 5:
			PSizvodjenja.pretragaIzvodjenjaNRG(izvodjenje);
			break;
		case 6:
			PSizvodjenja.pretragaIzvodjenjaPoVremenu(izvodjenje);
			break;
		case 7:
			PSizvodjenja.pretragaIzvodjenjaNazivScene(izvodjenje);
			break;
		}

	}

}

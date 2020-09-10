package metode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import korisnici.Biletar;
import korisnici.Korisnik;
import korisnici.Menadzer;
import mein.Main;
import organizacija.Karta;
import poz.Predstava;
import unos.Utility;

public class MetodeKorisnika {
	static Scanner sc;
	public static String path = "."+System.getProperty("file.separator");
	@SuppressWarnings("unused")
	public static void dodajKorisnika(ArrayList<Korisnik> korisnik) throws IOException {
		sc = new Scanner(System.in);
		String korisnickoIme = null;
		String lozinka = null;
		ArrayList<Predstava> dodatePredstave = new ArrayList<Predstava>();
		ArrayList<Karta> prodateKarte = new ArrayList<Karta>();
		System.out.println("1----> Menadzer\n2----> Biletar");
		Integer unos = Utility.unesiInt(sc);
		System.out.print("unesite ime: ");
		String ime = Utility.unesiTekst(sc);
		System.out.print("unesite prezime: ");
		String prezime = Utility.unesiTekst(sc);

		boolean flag = false;
		while (flag == false) {
			System.out.print("Unesite korosnickoIme: ");
			korisnickoIme = Utility.unesiTekst(sc);
			int i;
			for (i = 0; i < korisnik.size(); i++) {
				if (korisnickoIme.equals(korisnik.get(i).getKorisnickoIme())) {
					System.out.println("Ne mozete koristiti vec postojece korisnicko ime");
					break;
				}

			}

			if (i == korisnik.size()) {
				flag = true;
			}
		}

		boolean provera = false;
		while (provera == false) {
			System.out.print("unesite lozinka: ");
			lozinka = Utility.unesiTekst(sc);
			if (lozinka.equals(korisnickoIme)) {
				System.out.println("Sifra mora biti razlicita od korisnickog imena!");

			}
			if (!lozinka.equals(korisnickoIme)) {
				provera = true;
			}
		}
		if (unos == 1) {
			Menadzer men = new Menadzer();
			men.setAktivnost(true);
			men.setIme(ime);
			men.setPrezime(prezime);
			men.setKorisnickoIme(korisnickoIme);
			men.setLozinka(lozinka);
			men.setDodatePredstave(null);
			korisnik.add(men);

		} else if (unos == 2) {
			Biletar bil = new Biletar();
			bil.setAktivnost(true);
			bil.setIme(ime);
			bil.setPrezime(prezime);
			bil.setKorisnickoIme(korisnickoIme);
			bil.setLozinka(lozinka);
			bil.setProdatekarte(null);
			korisnik.add(bil);
		} else {
			System.out.println("Greska,ponovo!");
			dodajKorisnika(korisnik);
		}
	}

	public static void upisKorisnika(ArrayList<Korisnik> k) throws IOException {
		PrintWriter upis = new PrintWriter(
				new FileWriter(path + "korisnici.txt"));
		Korisnik kor = null;
		int i = 0;
		while (i < k.size()) {
			kor = k.get(i);
			i++;
			if (kor instanceof Menadzer) {
				Menadzer m = (Menadzer) kor;
				if (m.getDodatePredstave() != null){
				String[] naziviPredstava = new String[m.getDodatePredstave().size()];
				for (int j = 0; j < m.getDodatePredstave().size(); j++) {
					naziviPredstava[j] = m.getDodatePredstave().get(j).getNaziv();
				}
				String naziviPredstavaZaUpis = String.join(",",naziviPredstava);
				String linija = String.format("%s|%s|%s|%s|%s|%s|%s", "M", m.getKorisnickoIme(), m.getLozinka(),
						m.getIme(), m.getPrezime(), m.getAktivnost(),naziviPredstavaZaUpis);
				upis.append(linija);
				upis.append("\n");
				}else if (m.getDodatePredstave() == null){
					String linija = String.format("%s|%s|%s|%s|%s|%s|%s", "M", m.getKorisnickoIme(), m.getLozinka(),
							m.getIme(), m.getPrezime(), m.getAktivnost(),"null");
					upis.append(linija);
					upis.append("\n");
				}
			} 
			
			else if (kor instanceof Biletar) {
				Biletar b = (Biletar) kor;
				if(b.getProdatekarte() != null){
					String[] prodateKarte = new String[b.getProdatekarte().size()];
					for(int a = 0;a < b.getProdatekarte().size();a++){
						prodateKarte[a] = b.getProdatekarte().get(a).getSerijskiBroj();
					}
					String serijskiBrojevikarata = String.join(",", prodateKarte);
					String linija = String.format("%s|%s|%s|%s|%s|%s|%s", "B", b.getKorisnickoIme(), b.getLozinka(),
							b.getIme(), b.getPrezime(), b.getAktivnost(), serijskiBrojevikarata);
					upis.append(linija);
					upis.append("\n");
					
				}else if (b.getProdatekarte() == null){
					String linija = String.format("%s|%s|%s|%s|%s|%s|%s", "B", b.getKorisnickoIme(), b.getLozinka(),
							b.getIme(), b.getPrezime(), b.getAktivnost(), "null");
					upis.append(linija);
					upis.append("\n");
				}
			}
		}
		upis.close();
	}

	public static void ucitavanjeKorisnika(ArrayList<Korisnik> korisnik) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader read = new BufferedReader(
				new FileReader(path + "korisnici.txt"));
		String linija = null;
		while ((linija = read.readLine()) != null) {
			try{
				String[] podeljenString = linija.split("\\|");
				if (podeljenString[0].equals("M")) {
					Menadzer menadzer = new Menadzer(linija);
					korisnik.add(menadzer);
				} else if (podeljenString[0].equals("B")) {
					Biletar biletar = new Biletar(linija);
					korisnik.add(biletar);
				}
				
			}catch (Exception e){
				// do nothing
			}
		}
	}

	public static Korisnik prijava(ArrayList<Korisnik> k) {
		Korisnik kor = null;
		sc = new Scanner(System.in);
		System.out.print("Unesite username:");
		String userName = Utility.unesiTekst(sc);
		System.out.print("Unesite password:");
		String passWord = Utility.unesiTekst(sc);
		boolean flag = false;
		for (int i = 0; i < k.size(); i++) {
			kor = k.get(i);
			if (userName.equals(kor.getKorisnickoIme()) && (passWord.equals(kor.getLozinka()))
					&& kor.getAktivnost() == (true)) {
				if (kor instanceof Menadzer) {
					System.out.println("Menadzer\n" + kor.getIme() + " " + kor.getPrezime());
					Main.aktivanKorisnik = kor;
					flag = true;
				} else if (kor instanceof Biletar) {
					System.out.println("Biletar\n" + kor.getIme() + " " + kor.getPrezime());
					flag = true;
					Main.aktivanKorisnik = kor;
				}
			}
		}
		if (flag == false) {

			System.out.println("Greska,Pokusajte ponovo");
			prijava(k);
		}
		return kor;
	}

	public static void izmeniKorisnika(ArrayList<Korisnik> korisnik) {
		sc = new Scanner(System.in);
		System.out.println("Unesite pretragu: ");
		String pretraga = Utility.unesiTekst(sc);
		boolean zavrsioIzmenu = false;
		for (Korisnik k : korisnik) {
			if (k.getAktivnost() != false) {
				if (pretraga.equals(k.getKorisnickoIme())) {
					System.out.print("Ime: ");
					k.setIme(Utility.unesiTekst(sc));
					System.out.print("Prezime: ");
					k.setPrezime(Utility.unesiTekst(sc));
					System.out.print("Unesite lozinku: ");
					k.setLozinka(Utility.unesiTekst(sc));
					zavrsioIzmenu = true;
				}
			}
		}
		if (zavrsioIzmenu == false) {
			System.out.println("Nije pronasao nista");
			izmeniKorisnika(korisnik);
		}
	}

	public static void brisanjeKorisnika(ArrayList<Korisnik> korisnik) throws IOException {

		sc = new Scanner(System.in);
		System.out.println("Unesite userName korisnika koje zelite da obrisete: ");
		String username = Utility.unesiTekst(sc);
		boolean pronasao = false;
		for (int i = 0; i < korisnik.size(); i++) {
			if (korisnik.get(i).getAktivnost() != false) {
				if (username.equals(korisnik.get(i).getKorisnickoIme())) {
					korisnik.get(i).setAktivnost(false);
					System.out.println("Korisnik " + korisnik.get(i).getIme() + " " + korisnik.get(i).getPrezime()
							+ " je neaktivan");
					pronasao = true;

				}
			}
		}
		if (pronasao == false) {
			System.out.println("Pokusajte ponovo! ");
			brisanjeKorisnika(korisnik);
		}

	}

	public static void prikazKorisnika(ArrayList<Korisnik> korisnik) {
		System.out.printf("%-15s %-15s %-15s ","Korisnicko ime","Ime","Prezime");
		System.out.println();
		System.out.println("-------------------------------------------------");
		for (Korisnik k : korisnik) {
			if (k.getAktivnost() != false) {
				if (k instanceof Menadzer) {
					System.out.println(k.toString());
					System.out.println("-------------------------------------------------");
				} else if (k instanceof Biletar) {
					System.out.println(k.toString());
					System.out.println("-------------------------------------------------");
				}
				
			}
		}
	}

}

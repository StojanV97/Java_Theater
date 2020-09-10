package metode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Pretraga_Sortiranje.PSpredstava;
import korisnici.Korisnik;
import korisnici.Menadzer;
import mein.Main;
import organizacija.Karta;
import poz.Predstava;
import poz.TipPredstave;
import unos.Utility;

public class MetodePredstava {
	private static Scanner sc;

	public static String path = "."+System.getProperty("file.separator");

	public static void dodajPredstavu(ArrayList<Predstava> predstava, ArrayList<Predstava> listaDodatihPredstava,
			Korisnik k) throws IOException, ParseException {
	
		boolean pronasao = false;
		sc = new Scanner(System.in);
		String naziv = null;
		while (pronasao == false) {
			int i;
			System.out.print("Unesite naziv predstave:");
			naziv = Utility.unesiTekst(sc);
			for (i = 0; i < predstava.size(); i++) {
				if (naziv.equalsIgnoreCase(predstava.get(i).getNaziv())) {
					System.out.println("vec postoji predstava sa ovim nazivom!");
					break;
				}
			}
			if (i == predstava.size()) {
				pronasao = true;
			}
		}
		System.out.println("Unesite rezisere");
		String reziser = Utility.unesiTekst(sc);
		System.out.println("Unesite glumce");
		String glumci = Utility.unesiTekst(sc);
		System.out.println("Unesite tip Predstave");
		TipPredstave tipPredstave = Utility.unesiEnum(sc);
		System.out.println("Unesite produkciju");
		String produkcija = Utility.unesiTekst(sc);
		System.out.println("Unesite opis");
		String opis = Utility.unesiTekst(sc);
		boolean trajanjeAproved = false;
		int trajanje = 45;
		while(trajanjeAproved == false){
			System.out.println("Unesite trajanje");
			trajanje = Utility.unesiInt(sc);
			if(trajanje >= 45 && trajanje < 300){
				trajanjeAproved = true;
				
			}else{
				System.out.println("Unesite ponovo: ");
			}
		}
		int godinaPremijere = 0;
		boolean godinaAproved = false;
		while (godinaAproved == false){
			System.out.println("Unesite godinu premijere");
			godinaPremijere = Utility.unesiInt(sc);	
			if(godinaPremijere > 1400 && godinaPremijere <= 2017){
				godinaAproved = true;
			}else{
				System.out.println("Nevazeca godina... unesite ponovo: ");
			}
		}

		boolean aktivnost = true;
		Predstava pred = new Predstava(naziv, tipPredstave, reziser, glumci, trajanje, produkcija, godinaPremijere,
				opis, aktivnost);
		predstava.add(pred);
		ArrayList<Predstava> dodataPred = new ArrayList<Predstava>();
		if (((Menadzer) k).getDodatePredstave() != null){
		dodataPred.addAll(((Menadzer) k).getDodatePredstave());
		dodataPred.add(pred);
		((Menadzer) k).setDodatePredstave(dodataPred);
		}else{
			dodataPred.add(pred);
			((Menadzer) k).setDodatePredstave(dodataPred);
		}
		
		System.out.printf("Predstava '%s' je upisana korisniku %-5s  %s", pred.getNaziv(), k.getIme(), k.getPrezime());
		System.out.println();
	}

	public static void izmenaPredstave(ArrayList<Predstava> predstava) {
		sc = new Scanner(System.in);
		System.out.print("Unesite naziv predstave koju zelite da izmenite: ");
		boolean uspesno = false;
		String unos = Utility.unesiTekst(sc);
		for (Predstava p : predstava) {
			if (unos.equals(p.getNaziv()) && p.getAktivnost() != false) {
				String naziv = null;
				boolean provera = false;
				while (provera == false) {
					System.out.print("Nov naziv: ");
					naziv = Utility.unesiTekst(sc);
					int j;
					for (j = 0; j < predstava.size(); j++) {
						if (naziv.equalsIgnoreCase(predstava.get(j).getNaziv())
								&& predstava.get(j).getAktivnost() != false) {
							System.out.println("Naziv vec postoji,iskoristite drugi!");
							break;
						}
					}
					if (j == predstava.size()) {
						provera = true;
					}
				}
				p.setNaziv(naziv);
				System.out.print("Nov resizer: ");
				p.setReziser(Utility.unesiTekst(sc));
				System.out.print("Novi Glumci: ");
				p.setGlumci(Utility.unesiTekst(sc));
				System.out.print("Nov opis predstave: ");
				p.setOpis(Utility.unesiTekst(sc));
				System.out.print("Nova produkcija: ");
				p.setOpis(Utility.unesiTekst(sc));
				System.out.print("Nov tipPredstave: ");
				TipPredstave tp = Utility.unesiEnum(sc);
				p.setTipPredstave(tp);
				System.out.print("Nova godina premijere: ");
				p.setGodinaPremijere(Utility.unesiInt(sc));
				System.out.print("Novo trajanje(min): ");
				p.setTrajanje(Utility.unesiInt(sc));
				uspesno = true;
			}
		}
		if (uspesno == false) {
			System.out.println("Predstava ne postoji!Pretrazite ponovo ? (da/Enter za izlazak)");
			String odluka = sc.nextLine();
			if (odluka.equalsIgnoreCase("da")) {
				izmenaPredstave(predstava);
			}
		}
	}

	public static void brisanjePredstava(ArrayList<Predstava> svePredstave, ArrayList<Karta> listaKarata) {
		sc = new Scanner(System.in);
		System.out.print("Unesite naziv predstave koju zelite da obrisete: ");
		String naziv = Utility.unesiTekst(sc);
		Predstava predstave = null;
		boolean pronasaoPredstavu = false;
		for (Predstava p : svePredstave) {
			if (p.getAktivnost() != false) {
				if (naziv.equalsIgnoreCase(p.getNaziv())) {
					predstave = p;
					pronasaoPredstavu = true;
				}
			}
		}
		int brojac = listaKarata.size();
		if (pronasaoPredstavu == true) {
			for (Karta k : listaKarata) {
				brojac--;
				if (predstave.getNaziv().equals(k.getIzvodjenje().getPredstava().getNaziv())) {
					pronasaoPredstavu = false;
					System.out.println("Prodata je karta za ovu predstave!!!");
					brojac++;
				} else if (brojac == 0) {
					predstave.setAktivnost(false);
				}
			}
		}
		if (pronasaoPredstavu == false && brojac != 0) {
			System.out.println("Pokusajte opet(da/Enter za izlazak)");
			String odluka = sc.nextLine();
			if (odluka.equalsIgnoreCase("da")) {
				brisanjePredstava(svePredstave, listaKarata);
			}
		}
	}

	public static void upisPredstave(ArrayList<Predstava> predstave) throws IOException {
		PrintWriter upis = new PrintWriter(
				new FileWriter(path + "predstave.txt"));
		for (Predstava p : predstave) {
			String linija = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s", p.getNaziv(), p.getTipPredstave(),
					p.getReziser(), p.getGlumci(), p.getTrajanje(), p.getProdukcija(), p.getGodinaPremijere(),
					p.getOpis(), (p.getAktivnost()));
			upis.append(linija);
			upis.append("\n");
		}
		upis.close();
	}

	public static void ucitavanjePredstava(ArrayList<Predstava> predstave) throws IOException {
		BufferedReader bf = new BufferedReader(
				new FileReader(path+ "predstave.txt"));
		String linija;
		while ((linija = bf.readLine()) != null) {
			try{				
				Predstava predstava = new Predstava(linija);
				predstave.add(predstava);
			}catch (Exception e){
				// do nothing
			}
		}
		bf.close();
	}

	public static void pronalazenjePredstava(ArrayList<Predstava> pred) {
		sc = new Scanner(System.in);
		boolean foundOne = false;
		System.out.println("Unesite naziv predstave: ");
		String naziv = Utility.unesiTekst(sc);
		for (int i = 0; i < pred.size(); i++) {
			Predstava predstava = pred.get(i);
			if (naziv.equalsIgnoreCase(predstava.getNaziv()) && predstava.getAktivnost() != false) {
				System.out.println(pred.get(i));
				foundOne = true;
			}
		}
		if (foundOne == false) {
			System.out.println("Ne postojeca predstava");
			pronalazenjePredstava(pred);
		}
	}

	public static void pretragaIzbor(ArrayList<Predstava> pred) {
		sc = new Scanner(System.in);
		System.out.println("1----> Pretraga po nazivu\n2----> Pretraga po godini");
		int unos = Utility.unesiInt(sc);
		switch (unos) {
		case 1:
			PSpredstava.pretragaPredstavaNaziv(pred);
			break;
		case 2:
			PSpredstava.pretragaGodina(pred);
			break;
		default:
			System.out.println("Uneli ste pogresnu komandu");
			pretragaIzbor(pred);
		}
	}

	public static void sortiranjePredstava(ArrayList<Predstava> pred) {
		sc = new Scanner(System.in);
		System.out.println("1----> Sotiranje po nazivu predstave\n2----> Sortiranje pok godini premijere");
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			PSpredstava.sortiranjePredstavaNaziv(pred);
		} else if (unos == 2) {
			PSpredstava.sortiranjePredstavaGodina(pred);
		} else {
			sortiranjePredstava(pred);
		}

	}

	public static void prikazPredstava(ArrayList<Predstava> svePredstave) {
		System.out.printf("%-24s %-15s %-40s %-25s %-15s %-15s %-15s %-15s ","Naziv","Reziser","Glumci","Opis","Produkcija","TipPredstave","Trajanje","Godina Premijere");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Predstava p : svePredstave) {
			if(p.getAktivnost() != false){
			System.out.println(p.toString());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
		}
	}

}

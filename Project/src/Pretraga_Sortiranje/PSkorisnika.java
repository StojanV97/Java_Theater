package Pretraga_Sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import korisnici.Biletar;
import korisnici.Korisnik;
import korisnici.Menadzer;
import metode.MetodeKorisnika;
import organizacija.Karta;
import poz.Predstava;
import unos.Utility;

public class PSkorisnika {

	private static Scanner sc;
	public static void pretraga(ArrayList<Korisnik> listaKorisnik){
		System.out.println("1----> Pretraga po korisnickom imenu\n2----> Pretraga po imenu i prezimenu");
		sc = new Scanner(System.in);
		boolean zavrsio = false;
		while (zavrsio == false) {
			System.out.println("Unesite komandu...");
			int komanda = Utility.unesiInt(sc);
			switch (komanda) {
			case 1:
				pretragaKorisnikaPoKorisnickomImenu(listaKorisnik);
				zavrsio = true;
				break;

			case 2:
				pretragaKorisnikaPoImenuIPrezimenu(listaKorisnik);
				zavrsio = true;
				break;
			}
		}
	}
	public static void pretagaKorisnikaPoPredstaviIKarti(ArrayList<Korisnik> korisnik,ArrayList<Predstava> predstav,ArrayList<Karta> karte){
		sc = new Scanner(System.in);

	
	
	}

	public static void pretragaKorisnikaPoKorisnickomImenu(ArrayList<Korisnik> listaKorisnik) {
		sc = new Scanner(System.in);
		String userName = null;
		ArrayList<Korisnik> zaSort = new ArrayList<Korisnik>();
		System.out.print("Unesite korisniko ime: ");
		userName = sc.nextLine();

		boolean pronasao = false;
		for (Korisnik k : listaKorisnik) {
			if ((k.getKorisnickoIme().toLowerCase()).contains(userName.toLowerCase()) && k instanceof Menadzer) {
				zaSort.add(k);
				pronasao = true;
			} else if (k.getKorisnickoIme().contains(userName) && k instanceof Biletar) {
				zaSort.add(k);
				pronasao = true;
			}else if (userName.isEmpty()){
				zaSort.add(k);
			}

		}
		if (pronasao == false) {
			System.out.println("Nije pronasao korisnika!  ");
			pretragaKorisnikaPoKorisnickomImenu(listaKorisnik);
		}
		sortiranje(zaSort);
		MetodeKorisnika.prikazKorisnika(zaSort);
	}

	public static void pretragaKorisnikaPoImenuIPrezimenu(ArrayList<Korisnik> listaKorisnik) {
		sc = new Scanner(System.in);
		ArrayList<Korisnik> zaSort = new ArrayList<Korisnik>();
		System.out.println("Unesite ime");
		String ime = Utility.unesiTekst(sc);
		System.out.println("Unesite prezime");
		String prezime = Utility.unesiTekst(sc);
		boolean pronasao = false;
		for (Korisnik k : listaKorisnik) {
			if ((k.getIme().toLowerCase()).contains(ime.toLowerCase()) && k.getPrezime().contains(prezime)
					&& k instanceof Menadzer) {
				//System.out.println(((Menadzer) k).toString());
				zaSort.add(k);
				pronasao = true;
			} else if (k.getIme().contains(ime) && k.getPrezime().contains(prezime) && k instanceof Biletar) {
				//System.out.println(((Biletar) k).toString());
				zaSort.add(k);
				pronasao = true;
			}

		}
		if (pronasao == false) {
			System.out.println("Nije pronasao korisnika!  ");
			pretragaKorisnikaPoKorisnickomImenu(listaKorisnik);
		}
		sortiranje(zaSort);
		MetodeKorisnika.prikazKorisnika(zaSort);
		
	}

	public static void sortiranje(ArrayList<Korisnik> listaKorisnik) {
		System.out.println(
				"1----> Sortiranje po korisnickom imenu\n2----> sortiranje po imenu i prezimenu\n3----> Sortiranje po tipu korisnika");
		sc = new Scanner(System.in);
		boolean zavrsio = false;
		while (zavrsio == false) {
			System.out.println("Unesite komandu...");
			int komanda = Utility.unesiInt(sc);
			switch (komanda) {
			case 1:
				sortiranjeKorisnikaPoKorisnickoImenu(listaKorisnik);
				zavrsio = true;
				break;

			case 2:
				sortiranjeKorisnikaPoImenuIPrezimenu(listaKorisnik);
				zavrsio = true;
				break;
			case 3:
				sortiranjePoTipuKorisnika(listaKorisnik);
				zavrsio = true;
				break;
			}
		}
	}

	public static void sortiranjeKorisnikaPoKorisnickoImenu(ArrayList<Korisnik> listaKorisnik) {
		sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(listaKorisnik, new Comparator<Korisnik>() {

				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {

					return Integer.valueOf(arg1.getKorisnickoIme().compareTo(arg0.getKorisnickoIme()));
				}

			});
		} else if (i == 2) {
			Collections.sort(listaKorisnik, new Comparator<Korisnik>() {

				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {

					return Integer.valueOf(arg0.getKorisnickoIme().compareTo(arg1.getKorisnickoIme()));
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjeKorisnikaPoKorisnickoImenu(listaKorisnik);
		}
	}

	public static void sortiranjeKorisnikaPoImenuIPrezimenu(ArrayList<Korisnik> listaKorisnik) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(listaKorisnik, new Comparator<Korisnik>() {

				@Override
				public int compare(Korisnik arg, Korisnik arg1) {
					int vrednostIme = arg1.getIme().compareTo(arg.getIme());
					int vrednostPrezime = arg1.getPrezime().compareTo(arg.getPrezime());
					return vrednostIme + vrednostPrezime;
				}

			});
		} else if (i == 2) {
			Collections.sort(listaKorisnik, new Comparator<Korisnik>() {

				@Override
				public int compare(Korisnik arg, Korisnik arg1) {
					int vrednostIme = arg.getIme().compareTo(arg1.getIme());
					int vrednostPrezime = arg.getPrezime().compareTo(arg1.getPrezime());
					return vrednostIme + vrednostPrezime;
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjeKorisnikaPoImenuIPrezimenu(listaKorisnik);
		}
	}

	public static void sortiranjePoTipuKorisnika(ArrayList<Korisnik> listaKorisnika) {
		sc = new Scanner(System.in);
		ArrayList<Menadzer> men = new ArrayList<Menadzer>();
		ArrayList<Biletar> bil = new ArrayList<Biletar>();
		ArrayList<Korisnik> novaListaKorisnika = new ArrayList<Korisnik>();
		for (Korisnik k : listaKorisnika) {
			if (k instanceof Menadzer) {
				men.add((Menadzer) k);
			} else if (k instanceof Biletar) {
				bil.add((Biletar) k);
			}
		}
		System.out.println("1----> Menadzeri\n2----> Biletari");
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			for (Menadzer m : men) {
				novaListaKorisnika.addAll(men);
				break;
			}
			for (Biletar b : bil) {
				novaListaKorisnika.addAll(bil);
				break;
			}
		} else if (unos == 2) {
			for (Biletar b : bil) {
				novaListaKorisnika.addAll(bil);
				break;
			}
			for (Menadzer m : men) {
				novaListaKorisnika.addAll(men);
				break;
			}
		} else {
			System.out.println("Uneli ste nepostojecu komandu,pokusajte ponovo...");
			sortiranjePoTipuKorisnika(listaKorisnika);
		}

	}

}

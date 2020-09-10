package Pretraga_Sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import organizacija.Karta;
import poz.Izvodjenje;
import unos.Utility;

public class PSKarata {

	static Scanner sc;

	public static void sortiranjePoVremenuIzdavanja(ArrayList<Karta> listaKarata) {
		System.out.println("1----> Opadajuci\n2----> Rastuci");
		sc = new Scanner(System.in);
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			Collections.sort(listaKarata, new Comparator<Karta>() {

				@Override
				public int compare(Karta arg0, Karta arg1) {
					// TODO Auto-generated method stub
					return arg0.getVremeIzdavanja().compareTo(arg1.getVremeIzdavanja());
				}

			});
		} else if (unos == 2) {
			Collections.sort(listaKarata, new Comparator<Karta>() {
				@Override
				public int compare(Karta arg0, Karta arg1) {
					return arg1.getVremeIzdavanja().compareTo(arg0.getVremeIzdavanja());
				}

			});
		} else {
			System.out.println("Uneli ste pogresno...");
			sortiranjePoVremenuIzdavanja(listaKarata);
		}
	}

	public static void sortiranjePoPopustu(ArrayList<Karta> listaKarata) {
		System.out.println("1----> Opadajuci\n2----> Rastuci");
		sc = new Scanner(System.in);
		System.out.println("Unesite komandu! ");
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			Collections.sort(listaKarata, new Comparator<Karta>() {
				@Override
				public int compare(Karta arg0, Karta arg1) {
					return arg1.getPopust() - arg0.getPopust();
				}

			});
		} else if (unos == 2) {
			Collections.sort(listaKarata, new Comparator<Karta>() {
				@Override
				public int compare(Karta arg0, Karta arg1) {
					return arg0.getPopust() - arg1.getPopust();
				}

			});
		} else {
			System.out.println("Uneli ste pogresno...");
			sortiranjePoPopustu(listaKarata);
		}
	}

	public static void sortiranjePoNazivuVremenuPocetkaVremenuIzvodjenja(ArrayList<Karta> listaKarata) {
		System.out.println("1----> Opadajuci\n2----> Rastuci");
		sc = new Scanner(System.in);
		System.out.println("Unesite komandu! ");
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			Collections.sort(listaKarata, new Comparator<Karta>() {
				@Override

				public int compare(Karta arg0, Karta arg1) {
					int naziv = arg0.getIzvodjenje().getPredstava().getNaziv()
							.compareTo(arg1.getIzvodjenje().getPredstava().getNaziv());
					int vremeIzdavanja = arg0.getVremeIzdavanja().compareTo(arg1.getVremeIzdavanja());
					int vremePocetka = arg0.getIzvodjenje().getVremePocetka()
							.compareTo(arg1.getIzvodjenje().getVremePocetka());

					return naziv + vremeIzdavanja + vremePocetka;
				}

			});
		} else if (unos == 2) {
			Collections.sort(listaKarata, new Comparator<Karta>() {
				@Override

				public int compare(Karta arg0, Karta arg1) {
					int naziv = arg1.getIzvodjenje().getPredstava().getNaziv()
							.compareTo(arg0.getIzvodjenje().getPredstava().getNaziv());
					int vremeIzdavanja = arg1.getVremeIzdavanja().compareTo(arg0.getVremeIzdavanja());
					int vremePocetka = arg1.getIzvodjenje().getVremePocetka()
							.compareTo(arg0.getIzvodjenje().getVremePocetka());

					return naziv + vremeIzdavanja + vremePocetka;
				}

			});
		} else {
			System.out.println("Unesite ste pogresno...");
			sortiranjePoNazivuVremenuPocetkaVremenuIzvodjenja(listaKarata);
		}
	}
}

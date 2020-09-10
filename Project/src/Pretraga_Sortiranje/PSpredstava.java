package Pretraga_Sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import metode.MetodePredstava;
import poz.Predstava;
import unos.Utility;

public class PSpredstava {
	private static Scanner sc;

	// Sortiranje i Pretraga za Predstave{
	// ===========================================================================================================================
	public static void sortiranjePredstavaNaziv(ArrayList<Predstava> pred) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(pred, new Comparator<Predstava>() {
				@Override
				public int compare(Predstava a, Predstava b) {
					return Integer.valueOf(b.getNaziv().compareTo(a.getNaziv()));
				}
			});
		} else if (i == 2) {
			Collections.sort(pred, new Comparator<Predstava>() {
				@Override
				public int compare(Predstava a, Predstava b) {
					return Integer.valueOf(a.getNaziv().compareTo(b.getNaziv()));
				}
			});
		} else {
			sortiranjePredstavaNaziv(pred);
		}
	}

	public static void sortiranjePredstavaGodina(ArrayList<Predstava> pred) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(pred, new Comparator<Predstava>() {
				@Override
				public int compare(Predstava a, Predstava b) {
					return b.getGodinaPremijere() - a.getGodinaPremijere();
				}
			});
		} else if (i == 2) {
			Collections.sort(pred, new Comparator<Predstava>() {
				@Override
				public int compare(Predstava a, Predstava b) {
					return a.getGodinaPremijere() - b.getGodinaPremijere();

				}
			});
		} else {
			sortiranjePredstavaGodina(pred);
		}
	}

	public static void pretragaPredstavaNaziv(ArrayList<Predstava> pred) {
		ArrayList<Predstava> predstavaPretraga = new ArrayList<Predstava>();
		sc = new Scanner(System.in);
		boolean flag = false;
		System.out.print("Unesi naziv predstave: ");
		String unos = sc.nextLine().toLowerCase();
		for (Predstava p : pred) {
			if ((p.getNaziv().toLowerCase()).contains(unos) && p.getAktivnost() != false) {
				//System.out.println(p.toString());
				predstavaPretraga.add(p);
				flag = true;
			} else if (unos.isEmpty()) {
				//System.out.println(p.toString());
				predstavaPretraga.add(p);
			}

		}
		if (flag == false) {
			System.out.println("Nije pronadjena predstava\nPokusajte ponovo: ");
			pretragaPredstavaNaziv(pred);
		}
		MetodePredstava.sortiranjePredstava(predstavaPretraga);
		MetodePredstava.prikazPredstava(predstavaPretraga);

	}

	public static void pretragaGodina(ArrayList<Predstava> pred) {
		ArrayList<Predstava> predstavaPretraga = new ArrayList<Predstava>();
		sc = new Scanner(System.in);
		boolean foundOne = false;
		System.out.println("Unesite godinu predstave: ");
		String godina = sc.nextLine().toLowerCase();
		for (int i = 0; i < pred.size(); i++) {
			Predstava predstava = pred.get(i);
			String nesto = String.valueOf(predstava.getGodinaPremijere());
			if (nesto.contains(godina) && predstava.getAktivnost() != false) {
				//System.out.println(pred.get(i));
				predstavaPretraga.add(predstava);
				foundOne = true;
			} else if (godina.isEmpty()) {
				//System.out.println(pred.get(i));
				predstavaPretraga.add(predstava);	
			}
		}
		if (foundOne == false) {
			pretragaGodina(pred);
			System.out.println("Ne postojeca godina");
		}
		MetodePredstava.sortiranjePredstava(predstavaPretraga);
		MetodePredstava.prikazPredstava(predstavaPretraga);
	}
	// Sortitranje i Pretraga za Predstave }
	// =============================================================================================================================

}

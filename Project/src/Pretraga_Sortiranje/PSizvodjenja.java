package Pretraga_Sortiranje;

import java.util.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import metode.MetodeIzvodjenja;
import poz.Izvodjenje;
import poz.TipPredstave;
import unos.Utility;

public class PSizvodjenja {
	private static Scanner sc;

	// Pretraga i Sortiranje Izvodjenja
	// ==========================================================================================================================
	public static void pretragaIzvodjenjaNaziv(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjaPretraga = new ArrayList<Izvodjenje>();
		sc = new Scanner(System.in);
		boolean flag = false;
		System.out.print("Unesite naziv predstave: ");
		String naziv = sc.nextLine();
		for (Izvodjenje i : izvodjenje) {
			if ((i.getPredstava().getNaziv().toLowerCase()).contains(naziv.toLowerCase())) {
				// System.out.println(i.toString());
				izvodjenjaPretraga.add(i);
				flag = true;

			} else if (naziv.isEmpty()) {
				// System.out.println(i.toString());
				izvodjenjaPretraga.add(i);
			}
		}
		if (flag == false) {
			System.out.println("Nije pronadjeno izvodjenje");
			pretragaIzvodjenjaNaziv(izvodjenje);
		}
		sortiranje(izvodjenjaPretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjaPretraga);

	}

	public static void pretragaIzvodjenjaTipPredstave(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjaPretraga = new ArrayList<Izvodjenje>();

		sc = new Scanner(System.in);
		boolean flag = false;
		System.out.print("Unesite tip predstave: ");
		String naziv = sc.nextLine();
		// TipPredstave naziv = Utility.unesiEnum(sc);
		for (Izvodjenje i : izvodjenje) {
			if ((String.valueOf(i.getPredstava().getTipPredstave()).toLowerCase()).contains(naziv.toLowerCase())) {
				// System.out.println(i.toString());
				izvodjenjaPretraga.add(i);
				flag = true;

			} else if (naziv.isEmpty()) { // unos enuma za prazan unos
				// System.out.println(i.toString());
				izvodjenjaPretraga.add(i);
			}
		}
		if (flag == false) {
			System.out.println("Nije pronadjeno izvodjenje");
			pretragaIzvodjenjaTipPredstave(izvodjenje);
		}
		sortiranje(izvodjenjaPretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjaPretraga);

	}

	public static void pretragaIzvodjenjaGodinaPremijere(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjePretraga = new ArrayList<Izvodjenje>();
		sc = new Scanner(System.in);
		boolean foundOne = false;
		System.out.println("Unesite godinu predstave: ");
		String godina = sc.nextLine();
		for (int i = 0; i < izvodjenje.size(); i++) {
			Izvodjenje izv = izvodjenje.get(i);
			String nesto = String.valueOf(izv.getPredstava().getGodinaPremijere());
			if (nesto.contains(godina)) {
				// System.out.println(izv);
				izvodjenjePretraga.add(izv);
				foundOne = true;
			} else if (godina.isEmpty()) {
				// System.out.println(izv);
				izvodjenjePretraga.add(izv);
			}

		}
		if (foundOne == false) {
			System.out.println("Ne postojeca godina ili niste uneli godinu :p ");
			pretragaIzvodjenjaGodinaPremijere(izvodjenje);
		}
		sortiranje(izvodjenjePretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjePretraga);

	}

	public static void pretragaIzvodjenjaReziser(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjePretraga = new ArrayList<Izvodjenje>();

		sc = new Scanner(System.in);
		boolean flag = false;
		System.out.print("Unesite rezisera: ");
		String naziv = sc.nextLine();
		for (Izvodjenje i : izvodjenje) {
			if (i.getPredstava().getReziser().contains(naziv)) {
				// System.out.println(i.toString());
				izvodjenjePretraga.add(i);
				flag = true;

			} else if (naziv.isEmpty()) {
				// System.out.println(i.toString());
				izvodjenjePretraga.add(i);
			}
		}
		if (flag == false) {
			System.out.println("Nije pronadjeno izvodjenje");
			pretragaIzvodjenjaReziser(izvodjenje);
		}
		sortiranje(izvodjenjePretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjePretraga);

	}

	public static void pretragaIzvodjenjaNRG(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjePretraga = new ArrayList<Izvodjenje>();
		sc = new Scanner(System.in);
		boolean flag = false;
		System.out.print("Unesite naziv predstave: ");
		String naziv = Utility.unesiTekstZaPredstavu(sc);
		System.out.println("Unesite rezisera: ");
		String reziser = Utility.unesiTekstZaPredstavu(sc);
		System.out.println("Unesite glumca");
		String glumac = Utility.unesiTekstZaPredstavu(sc);
		for (Izvodjenje i : izvodjenje) {
			if (i.getPredstava().getNaziv().toLowerCase().contains(naziv.toLowerCase())
					&& i.getPredstava().getReziser().toLowerCase().contains(reziser.toLowerCase())
					&& i.getPredstava().getGlumci().toLowerCase().contains(glumac.toLowerCase())) {
				// System.out.println(i.toString());
				izvodjenjePretraga.add(i);
				flag = true;

			} else if (naziv.isEmpty() || reziser.isEmpty() || glumac.isEmpty()) {
				// System.out.println(i.toString());
				izvodjenjePretraga.add(i);
			}
		}
		if (flag == false) {
			System.out.println("Nije pronadjeno izvodjenje");
			pretragaIzvodjenjaNRG(izvodjenje);
		}
		sortiranje(izvodjenjePretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjePretraga);

	}

	public static void pretragaIzvodjenjaNazivScene(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjePretraga = new ArrayList<Izvodjenje>();
		sc = new Scanner(System.in);
		boolean flag = false;
		System.out.print("Unesite naziv scene za pretragu: ");
		String naziv = Utility.unesiTekstZaPredstavu(sc);
		for (Izvodjenje i : izvodjenje) {
			if (i.getScena().getNaziv().contains(naziv)) {
				// System.out.println(i.toString());
				izvodjenjePretraga.add(i);
				flag = true;

			} else if (naziv.isEmpty()) {
				// System.out.println(i.toString());
				izvodjenjePretraga.add(i);
			}
		}
		if (flag == false) {
			System.out.println("Nije pronadjeno izvodjenje");
			pretragaIzvodjenjaNazivScene(izvodjenje);
		}
		sortiranje(izvodjenjePretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjePretraga);

	}

	public static void pretragaIzvodjenjaPoVremenu(ArrayList<Izvodjenje> izvodjenje) {
		ArrayList<Izvodjenje> izvodjenjePretraga = new ArrayList<Izvodjenje>();
		sc = new Scanner(System.in);
		System.out.print("Unesite pocetno vreme: ");
		Date pocetnoVreme = Utility.unesiVreme(sc);
		System.out.print("Unesite krajnje vreme: ");
		Date krajnjeVreme = Utility.unesiVreme(sc);
		int i;
		boolean pronasao = false;
		for (i = 0; i < izvodjenje.size(); i++) {
			if (pocetnoVreme.before(izvodjenje.get(i).getVremePocetka())
					&& krajnjeVreme.after(izvodjenje.get(i).getVremePocetka())) {
				// System.out.println(izvodjenje.toString());
				izvodjenjePretraga.add(izvodjenje.get(i));
				pronasao = true;
			} else if (pocetnoVreme.equals(izvodjenje.get(i).getVremePocetka())
					|| krajnjeVreme.equals(izvodjenje.get(i).getVremePocetka())) {
				System.out.println(izvodjenje.toString());
				izvodjenjePretraga.add(izvodjenje.get(i));
				pronasao = true;

			}

		}
		if (pronasao == false) {
			System.out.println("Ne postoje izvodjenja u ovo vremenskom intervalu!\nPokusajte ponovo: ");
			pretragaIzvodjenjaPoVremenu(izvodjenje);
		}
		sortiranje(izvodjenjePretraga);
		MetodeIzvodjenja.prikaziIzvodjena(izvodjenjePretraga);
	}
	// Sortiranje
	// ======================================================================================================================================================

	public static void sortiranjeIzvodjenjaNazivPredstave(ArrayList<Izvodjenje> izvodjenja) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					return Integer.valueOf(b.getPredstava().getNaziv().compareTo(a.getPredstava().getNaziv()));
				}
			});
		} else if (i == 2) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					return Integer.valueOf(a.getPredstava().getNaziv().compareTo(b.getPredstava().getNaziv()));
				}
			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjeIzvodjenjaNazivPredstave(izvodjenja);
		}
	}

	public static void sortiranjeIzvodjenjaTipuPredstave(ArrayList<Izvodjenje> izvodjenja) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					String tip1 = String.valueOf(a.getPredstava().getTipPredstave());
					String tip2 = String.valueOf(b.getPredstava().getTipPredstave());

					return Integer.valueOf(tip1.compareTo(tip2));
				}
			});
		} else if (i == 2) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					String tip1 = String.valueOf(a.getPredstava().getTipPredstave());
					String tip2 = String.valueOf(b.getPredstava().getTipPredstave());

					return Integer.valueOf(tip2.compareTo(tip1));
				}
			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjeIzvodjenjaTipuPredstave(izvodjenja);
		}
	}

	public static void sortiranjeIzvodjenjaGP(ArrayList<Izvodjenje> izvodjenja) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					return b.getPredstava().getGodinaPremijere() - a.getPredstava().getGodinaPremijere();
				}
			});
		} else if (i == 2) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					return a.getPredstava().getGodinaPremijere() - b.getPredstava().getGodinaPremijere();
				}
			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjeIzvodjenjaGP(izvodjenja);
		}
	}

	public static void sortiranjeIzvodjenjaNazivuScene(ArrayList<Izvodjenje> izvodjenja) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		sc = new Scanner(System.in);
		int i = Utility.unesiInt(sc);
		if (i == 1) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					return Integer.valueOf(b.getScena().getNaziv().compareTo(a.getScena().getNaziv()));
				}
			});
		} else if (i == 2) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {
				@Override
				public int compare(Izvodjenje a, Izvodjenje b) {
					return Integer.valueOf(a.getScena().getNaziv().compareTo(b.getScena().getNaziv()));
				}
			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjeIzvodjenjaNazivuScene(izvodjenja);
		}
	}

	public static void sortiranjePoVremenuPocetka(ArrayList<Izvodjenje> izvodjenja) {
		System.out.println("1 Opadajuci\n 2 Rastuci");
		sc = new Scanner(System.in);
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {

				@Override
				public int compare(Izvodjenje arg0, Izvodjenje arg1) {
					// TODO Auto-generated method stub
					return arg0.getVremePocetka().compareTo(arg1.getVremePocetka());
				}

			});
		} else if (unos == 2) {
			Collections.sort(izvodjenja, new Comparator<Izvodjenje>() {

				@Override
				public int compare(Izvodjenje o1, Izvodjenje o2) {
					// TODO Auto-generated method stub
					return o2.getVremePocetka().compareTo(o1.getVremePocetka());
				}

			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjePoVremenuPocetka(izvodjenja);
		}

	}

	public static void sortiranjePoNazivuIVremenu(ArrayList<Izvodjenje> izvodjenje) {
		sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n 2 Rastuci");
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			Collections.sort(izvodjenje, new Comparator<Izvodjenje>() {

				@Override
				public int compare(Izvodjenje o1, Izvodjenje o2) {
					int vrednost1 = o1.getPredstava().getNaziv().compareTo(o2.getPredstava().getNaziv());
					int vrednost2 = o1.getVremePocetka().compareTo(o2.getVremePocetka());
					return vrednost1 + vrednost2;
				}

			});
		} else if (unos == 2) {
			Collections.sort(izvodjenje, new Comparator<Izvodjenje>() {

				@Override
				public int compare(Izvodjenje o1, Izvodjenje o2) {
					int vrednost1 = o2.getPredstava().getNaziv().compareTo(o1.getPredstava().getNaziv());
					int vrednost2 = o2.getVremePocetka().compareTo(o1.getVremePocetka());
					return vrednost1 + vrednost2;
				}

			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjePoNazivuIVremenu(izvodjenje);
		}

	}

	public static void sortiranjePoTipuIGodiniPremijete(ArrayList<Izvodjenje> izvodjenje) {
		sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int unos = Utility.unesiInt(sc);
		if (unos == 1) {
			Collections.sort(izvodjenje, new Comparator<Izvodjenje>() {

				@Override
				public int compare(Izvodjenje o1, Izvodjenje o2) {
					String tip1 = String.valueOf(o1.getPredstava().getTipPredstave());
					String tip2 = String.valueOf(o2.getPredstava().getTipPredstave());

					int vrednost1 = tip1.compareTo(tip2);
					int vrednost2 = o1.getVremePocetka().compareTo(o2.getVremePocetka());
					return vrednost1 + vrednost2;
				}

			});
		} else if (unos == 2) {
			Collections.sort(izvodjenje, new Comparator<Izvodjenje>() {

				@Override
				public int compare(Izvodjenje o1, Izvodjenje o2) {
					String tip1 = String.valueOf(o1.getPredstava().getTipPredstave());
					String tip2 = String.valueOf(o2.getPredstava().getTipPredstave());

					int vrednost1 = tip2.compareTo(tip1);
					int vrednost2 = o2.getVremePocetka().compareTo(o1.getVremePocetka());
					return vrednost1 + vrednost2;

				}

			});
		} else {
			System.out.println("Niste uneli pravilno,pokusajte opet");
			sortiranjePoNazivuIVremenu(izvodjenje);
		}

	}

	public static void sortiranje(ArrayList<Izvodjenje> izvodjenjaPretraga) {
		sc = new Scanner(System.in);
		System.out.println("Unesi komandu:\n1----> Sortiranje po nazivu predstave."
				+ "\n2----> Sortiranje po vremenu pocetka."
				+ "\n3----> Sortiranje po nazive predstave i vremenu pocetka."
				+ "\n4----> Sortiranje po tipu predstave." + "\n5----> Sortiranje po godini premijere."
				+ "\n6----> Sortiranje po tipu predstave i godini premijere." + "\n7----> Sortiranje po nazivu sene.");
		boolean uradio = false;
		while (uradio == false) {
			System.out.print("Unesite komandu: ");
			int komanda = Utility.unesiInt(sc);
			switch (komanda) {
			case 1:
				sortiranjeIzvodjenjaNazivPredstave(izvodjenjaPretraga);
				uradio = true;
				break;
			case 2:
				sortiranjePoVremenuPocetka(izvodjenjaPretraga);
				uradio = true;
				break;
			case 3:
				sortiranjePoNazivuIVremenu(izvodjenjaPretraga);
				uradio = true;
				break;
			case 4:
				sortiranjeIzvodjenjaTipuPredstave(izvodjenjaPretraga);
				uradio = true;
				break;
			case 5:
				sortiranjeIzvodjenjaGP(izvodjenjaPretraga);
				uradio = true;
				break;
			case 6:
				sortiranjePoTipuIGodiniPremijete(izvodjenjaPretraga);
				uradio = true;
				break;
			case 7:
				sortiranjeIzvodjenjaNazivuScene(izvodjenjaPretraga);
				uradio = true;
				break;

			}
			
		}
	}

}

package unos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import poz.TipPredstave;

public class Utility {
	static Scanner sc;

	public static String unesiTekst(Scanner sc) {
		boolean flag = false;
		String tekst = null;
		while (flag == false) {
		    tekst = sc.nextLine();
			if (tekst.isEmpty()) {
				System.out.println("Unesite Ponovo: ");
				flag = false;
			}else{
				flag = true;
			}
		}
		return tekst;

	}

	public static String unesiTekstZaPredstavu(Scanner sc) {
		String tekst = sc.nextLine();
		return tekst;
	}

	public static Integer unesiInt(Scanner sc) {
		int unos = 0;
		boolean ocitan = false;
		while (ocitan != true) {
			try {
				unos = sc.nextInt();
				ocitan = true;
			} catch (Exception e) {
				System.out.print("Uneli ste pogresno\n --- Pokusajte ponovo: ");
			}
			sc.nextLine();
		}
		return unos;

	}

	public static Double unesiDouble(Scanner sc) {
		double unos = 0;
		boolean ocitan = false;
		while (ocitan != true) {
			try {
				unos = sc.nextDouble();
				ocitan = true;

			} catch (Exception e) {
				System.err.print("Uneli ste pogresno --- Pokusajte ponovo:\n");

			}
			sc.nextLine();
		}
		return unos;
	}

	public static float unesiFloat(Scanner sc) {
		float unos = 0;
		boolean ocitan = false;
		while (ocitan != true) {
			try {
				unos = sc.nextInt();
				ocitan = true;
			} catch (Exception e) {
				System.err.print("Uneli ste pogresno --- Pokusajte ponovo:\n");
			}
			sc.nextLine();
		}
		return unos;

	}

	public static Date unesiDatum(Scanner sc) {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		boolean ocitan = false;
		Date date = null;
		while (ocitan != true) {
			try {
				date = formater.parse(unesiTekst(sc));
				ocitan = true;
			} catch (ParseException e) {
				// e.printStackTrace();
				System.err.println("Unesite ponovo: ");
			}

		}
		return date;
	}

	public static Date unesiVreme(Scanner sc) {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		boolean ocitan = false;
		Date date = null;
		while (ocitan != true) {
			try {
				date = formater.parse(unesiTekst(sc));
				ocitan = true;
			} catch (ParseException e) {
				// e.printStackTrace();
				System.err.println("Unesite ponovo: ");
			}

		}
		return date;
	}

	public static TipPredstave unesiEnum(Scanner sc) {
		String enum1;
		boolean nadjen = false;
		TipPredstave tipPredstave = null;
		while (nadjen == false) {
			try {
				enum1 = sc.nextLine();
				tipPredstave = TipPredstave.valueOf(enum1.toUpperCase());
				nadjen = true;
			} catch (IllegalArgumentException e) {
				System.err.print("Nepostojeci Enum\n Pokusajte ponovo: ");
			}

		}
		return tipPredstave;
	}

	public static String unesiOdluku(Scanner scan) {
		String odluka = "ne";
		boolean notRead = true;
		do {
			odluka = Utility.unesiTekst(scan);
			if (odluka.equalsIgnoreCase("da") || odluka.equalsIgnoreCase("ne"))
				notRead = false;
		} while (notRead);
		return odluka;
	}
}

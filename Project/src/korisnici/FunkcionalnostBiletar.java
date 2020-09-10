package korisnici;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Pretraga_Sortiranje.PSpredstava;
import mein.Main;
import metode.MetodeIzvodjenja;
import metode.MetodeKarta;
import metode.MetodePredstava;
import unos.Utility;

public class FunkcionalnostBiletar {
	private static Scanner sc;

	public static void interakcijaSaKorisnikomBiletar() throws ParseException, IOException {
		sc = new Scanner(System.in);
		
		boolean zavrsi = false;
		while (zavrsi == false) {
			System.out.println("Ponudjene funkcionalnosi: ");
			System.out.println(
					"1----> Pronalazenje Predstave" + "\n2----> Pretraga predstava" + "\n3----> Pretraga izvodjenja"
							+ "\n4----> Prodaja karte" + "\n5----> Pronalazenje karte po serjskom broju"
							+ "\n6----> Prikaz svih karata " + "\n0----> Odjava biletara");
			
			System.out.print("Unesite komandu: ");
			int komanda = Utility.unesiInt(sc);
			switch (komanda) {
			case 1:
				MetodePredstava.pronalazenjePredstava(Main.svePredstave);
				break;
			case 2:
				MetodePredstava.pretragaIzbor(Main.svePredstave);
				break;
			case 3:
				MetodeIzvodjenja.pretragaIzvodjenja(Main.izvodjenje);
				break;
			case 4:
				MetodeKarta.prodajKartu(Main.izvodjenje, Main.prodateKarte, Main.aktivanKorisnik,Main.listaKarata);
				//MetodeIzvodjenja.inicijalizujKarte(Main.listaKarata, Main.izvodjenje);
				//MetodeIzvodjenja.prikaziIzvodjena(Main.izvodjenje);
				//MetodeIzvodjenja.upisiIzvodjenje(Main.izvodjenje);
				//MetodeKarta.izvodjenja(Main.izvodjenje);
				break;
			case 5:
				MetodeKarta.pronalazenjeKartePoSerijskomBroju(Main.listaKarata);
				break;
			case 6:
				MetodeKarta.prikazSvihKarata(Main.listaKarata);
				// MetodeKarta.prikazKarta(Main.listaKarata);
				break;

			case 0:
				zavrsi = true;
				break;
			}

		}
	}

}

package korisnici;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Pretraga_Sortiranje.PSkorisnika;
import mein.Main;
import metode.MetodeIzvodjenja;
import metode.MetodeKarta;
import metode.MetodeKorisnika;
import metode.MetodePredstava;
import metode.MetodeScena;
import poz.Predstava;
import unos.Utility;

public class FunkcionalnostMenadzer {
	private static Scanner sc;

	public static void interakcijaSaKorisnikom() throws ParseException, IOException {
		sc = new Scanner(System.in);
		
	
		boolean zavrsi = false;
		while (zavrsi == false) {
		
			System.out.println("Ponudjene funkcionalnosi: ");
			System.out.println("1----> Unos nove Predstave" + "\n2----> Izmena postojece predstave"
					+ "\n3----> Unos Izvodjenja" + "\n4----> Pretraga izvodjenja" + "\n5----> Pretraga karte"
					+ "\n6----> Prikaz svih karata" + "\n7----> Pretraga korisnika" + "\n8----> Unos korisnika"
					+ "\n9----> Izmena korisnika" + "\n10---> Brisanje korisnika" + 
					"\n11---> Unos nove scene" +"\n13---> Brisanje Izvodjenja"+"\n12---> Brisanje scene"+"\n14---> Brisanje predstave"
					+"\n0----> Odjava menadzera");

			System.out.print("Uneste komandu: ");
						
			int komanda = Utility.unesiInt(sc);
			switch (komanda) {
			case 1:
				MetodePredstava.dodajPredstavu(Main.svePredstave, Main.listaDodatihPredstava, Main.aktivanKorisnik);
				MetodeKorisnika.prikazKorisnika(Main.listaKorisnika);
				MetodeKorisnika.upisKorisnika(Main.listaKorisnika);
				MetodePredstava.upisPredstave(Main.svePredstave);
				break;
			case 2:
				MetodePredstava.izmenaPredstave(Main.svePredstave);
				MetodePredstava.upisPredstave(Main.svePredstave);
				break;
			case 3:
				MetodePredstava.prikazPredstava(Main.svePredstave);
				MetodeIzvodjenja.dodajIzvodjenje(Main.izvodjenje, Main.svePredstave, Main.listaScena);
				//MetodeIzvodjenja.upisiIzvodjenje(Main.izvodjenje);
				
				break;
			case 4:
				MetodeIzvodjenja.pretragaIzvodjenja(Main.izvodjenje);
				break;
			case 5:
				MetodeKarta.pronalazenjeKartePoSerijskomBroju(Main.listaKarata);
				break;
			case 6:
				MetodeKarta.prikazSvihKarata(Main.listaKarata);;
				break;
			case 7:
				PSkorisnika.pretraga(Main.listaKorisnika);
				break;
			case 8:
				MetodeKorisnika.dodajKorisnika(Main.listaKorisnika);
				MetodeKorisnika.upisKorisnika(Main.listaKorisnika);
				break;
			case 9:
				MetodeKorisnika.izmeniKorisnika(Main.listaKorisnika);
				MetodeKorisnika.upisKorisnika(Main.listaKorisnika);
				break;
			case 10:
				MetodeKorisnika.brisanjeKorisnika(Main.listaKorisnika);
				MetodeKorisnika.upisKorisnika(Main.listaKorisnika);
				break;
			case 11:
				MetodeScena.unesiScenu(Main.listaScena);
				MetodeScena.upisiScene(Main.listaScena);
				break;
			case 12:
				MetodeScena.obrisiScenu(Main.listaScena, Main.listaKarata);
				MetodeScena.upisiScene(Main.listaScena);
				break;
			case 13:
				//MetodeIzvodjenja.prikaziIzvodjena(Main.izvodjenje);
				MetodeIzvodjenja.obrisiIzvodjenje(Main.izvodjenje);
				//MetodeIzvodjenja.upisiIzvodjenje(Main.izvodjenje);
				break;
			case 14:
				MetodePredstava.prikazPredstava(Main.svePredstave);
				MetodePredstava.brisanjePredstava(Main.svePredstave, Main.listaKarata);
				MetodePredstava.upisPredstave(Main.svePredstave);
				break;
			case 0:
				zavrsi = true;
				break;
			}

		}
	}

}

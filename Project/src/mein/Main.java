package mein;

import java.io.IOException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Pretraga_Sortiranje.PSKarata;
import Pretraga_Sortiranje.PSizvodjenja;
import Pretraga_Sortiranje.PSkorisnika;
import korisnici.Biletar;
import korisnici.FunkcionalnostMenadzer;
import korisnici.FunkcionalnostBiletar;
import korisnici.Korisnik;
import korisnici.Menadzer;
import poz.Izvodjenje;
import poz.Predstava;
import poz.Scena;
import unos.Utility;
import metode.MetodeIzvodjenja;
import metode.MetodeKarta;
import metode.MetodeKorisnika;
import metode.MetodePredstava;
import metode.MetodeScena;
import organizacija.Karta;

public class Main {
	public static Scanner sc;
	public static ArrayList<Predstava> svePredstave = new ArrayList<Predstava>();
	public static ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
	public static ArrayList<Scena> listaScena = new ArrayList<Scena>();
	public static ArrayList<Predstava> listaDodatihPredstava = new ArrayList<Predstava>();
	public static ArrayList<Izvodjenje> izvodjenje = new ArrayList<Izvodjenje>();
	public static ArrayList<Karta> listaKarata = new ArrayList<Karta>();
	public static ArrayList<Karta> prodateKarte = new ArrayList<Karta>();
	public static Korisnik aktivanKorisnik;

	public static void main(String[] args) throws IOException, ParseException {
		sc = new Scanner(System.in);
		boolean zavrsio = false;
		MetodePredstava.ucitavanjePredstava(svePredstave);
		MetodeScena.ucitajScene(listaScena);
		MetodeIzvodjenja.ucitajIzvodjenje(izvodjenje);
		MetodeKarta.ucitajKartu(listaKarata);
		MetodeKorisnika.ucitavanjeKorisnika(listaKorisnika);
		MetodeIzvodjenja.inicijalizujKarte(listaKarata, izvodjenje);

		
		
		while (zavrsio == false) {
			if(listaKorisnika.size() == 0){
				System.out.println("Nema korisnika u bazi.. Unesi: ");
				MetodeKorisnika.dodajKorisnika(listaKorisnika);
			}else{

				System.out.println("Prijava korisnika....");
				MetodeKorisnika.prijava(listaKorisnika);
				if (aktivanKorisnik instanceof Menadzer) {
					FunkcionalnostMenadzer.interakcijaSaKorisnikom();
					System.out.println("Izlazak iz programa(da/Enter)");
					String odluka = sc.nextLine();
					if (odluka.equalsIgnoreCase("da")) {
						MetodeIzvodjenja.upisiIzvodjenje(izvodjenje);
						zavrsio = true;
					}

				}
				else if (aktivanKorisnik instanceof Biletar) {
					FunkcionalnostBiletar.interakcijaSaKorisnikomBiletar();
					System.out.println("Izlazak iz programa(da/Enter)");
					String odluka = sc.nextLine();
					if (odluka.equalsIgnoreCase("da")) {
						MetodeIzvodjenja.upisiIzvodjenje(izvodjenje);
						zavrsio = true;
					}
				}
			}

		}
	}

}

package organizacija;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mein.Main;
import poz.Izvodjenje;

public class Karta {
	String serijskiBroj;
	double cena;
	int popust;
	Date vremeIzdavanja;
	Izvodjenje izvodjenje;
	Sediste sediste;

	public Karta() {

	}

	@Override
	public String toString() {
		return String.format("%-19s |%-15s |%-15s |%-8s |%-8s |%-30s |%-15s |%-15s", izvodjenje.getPredstava().getNaziv(),
				izvodjenje.getVremePocetka(), izvodjenje.getScena().getNaziv(), String.valueOf(cena), popust,vremeIzdavanja,serijskiBroj,sediste);
	}

	public Karta(String serijskiBroj, double cena, int popust, Date vremeIzdavanja, Izvodjenje izvodjenje,
			Sediste sediste) {
		this.serijskiBroj = serijskiBroj;
		this.cena = cena;
		this.popust = popust;
		this.vremeIzdavanja = vremeIzdavanja;
		this.izvodjenje = izvodjenje;
		this.sediste = sediste;

	}

	@SuppressWarnings("unused")
	public Karta(String linija) throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		
		String[] podeljen = linija.split("\\|");
		this.serijskiBroj = podeljen[0];
		this.cena = Double.valueOf(podeljen[1]);
		this.popust = Integer.valueOf(podeljen[2]);
		this.vremeIzdavanja = formater.parse(podeljen[3]);
		for (Izvodjenje izv : Main.izvodjenje) {
			if (Integer.valueOf(podeljen[4]) == (izv.getIndentifikator())) {
				this.izvodjenje = izv;
			}
		}
		String[] podeljenSediste = podeljen[5].split("\\:");
		Sediste sediste = new Sediste(Integer.valueOf(podeljenSediste[0]), Integer.valueOf(podeljenSediste[1]));
		this.sediste = sediste;

	}

	public String getSerijskiBroj() {
		return serijskiBroj;
	}

	public void setSerijskiBroj(String serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public Date getVremeIzdavanja() {
		return vremeIzdavanja;
	}

	public void setVremeIzdavanja(Date vremeIzdavanja) {
		this.vremeIzdavanja = vremeIzdavanja;
	}

	public Izvodjenje getIzvodjenje() {
		return izvodjenje;
	}

	public void setIzvodjenje(Izvodjenje izvodjenje) {
		this.izvodjenje = izvodjenje;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

}

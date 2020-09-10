package poz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mein.Main;
import organizacija.Karta;

public class Izvodjenje {
	int indentifikator;
	Date vremePocetka;
	double cenaKarte;
	Predstava predstava;
	Scena scena;
	ArrayList<Karta> prodatekarte;
	boolean aktivnost;

	public Izvodjenje() {

	}

	public Izvodjenje(int indentifikator, Date vremePocetka, double cenaKarte, Predstava predstava, Scena scena,
			boolean aktivnost, ArrayList<Karta> prodateKarte) {

		this.indentifikator = indentifikator;
		this.vremePocetka = vremePocetka;
		this.cenaKarte = cenaKarte;
		this.predstava = predstava;
		this.scena = scena;
		this.prodatekarte = prodateKarte;
		this.aktivnost = aktivnost;

	}

	public Izvodjenje(String line) throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		String[] podeljen = line.split("\\|");
		this.indentifikator = Integer.valueOf(podeljen[0]);
		this.vremePocetka = formater.parse(podeljen[1]);
		this.cenaKarte = Double.valueOf(podeljen[2]);
		for (Predstava p : Main.svePredstave) {
			if (podeljen[3].equalsIgnoreCase(p.getNaziv())) {
				this.predstava = p;
			}
		}
		for (Scena sc : Main.listaScena) {
			if (podeljen[4].equalsIgnoreCase(sc.getNaziv())) {
				this.scena = sc;
			}
		}
		if(!podeljen[5].equals("null")){
			this.prodatekarte = new ArrayList<Karta>();
			String[] podeljeneKarte = podeljen[5].split("\\,");
			for(Karta k : Main.listaKarata){
				for(int i = 0;i < podeljeneKarte.length; i++){
					if(podeljeneKarte[i].equals(k.getSerijskiBroj())){
						this.prodatekarte.add(k);
					}
				}
			}
		}

		this.aktivnost = Boolean.valueOf(podeljen[6]);

	}

	@Override
	public String toString() {
		return String.format("%-14s |%-20s |%-10s |%-20s |%-10s ", indentifikator,vremePocetka,cenaKarte,this.getPredstava().getNaziv(),this.scena.getNaziv());
		
	}
	

	public int getIndentifikator() {
		return indentifikator;
	}



	public void setIndentifikator(int indentifikator) {
		this.indentifikator = indentifikator;
	}

	public Date getVremePocetka() {
		return vremePocetka;
	}

	public void setVremePocetka(Date vremePocetka) {
		this.vremePocetka = vremePocetka;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public Predstava getPredstava() {
		return predstava;
	}

	public void setPredstava(Predstava predstava) {
		this.predstava = predstava;
	}

	public Scena getScena() {
		return scena;
	}

	public void setScena(Scena scena) {
		this.scena = scena;
	}

	public ArrayList<Karta> getProdatekarte() {
		return prodatekarte;
	}

	public void setProdatekarte(ArrayList<Karta> prodatekarte) {
		this.prodatekarte = prodatekarte;
	}

	public Boolean getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}

}

package poz;

import java.util.ArrayList;

import organizacija.Sediste;

public class Scena {

	String naziv;
	String tipTonskogZapisa;
	ArrayList<TipPredstave> podrzaniTipovi;
	ArrayList<Sediste> sedista;
	boolean aktivnost;

	public Scena() {

	}

	public Scena(String naziv, String tipTonskogZapisa, boolean aktivnost, ArrayList<TipPredstave> podrzaniTipovi,
			ArrayList<Sediste> sedista) {
		this.naziv = naziv;
		this.tipTonskogZapisa = tipTonskogZapisa;
		this.podrzaniTipovi = podrzaniTipovi;
		this.sedista = sedista;
		this.aktivnost = aktivnost;

	}

	public Scena(String line) {
		String[] podeljen = line.split("\\|");
		this.naziv = podeljen[0];
		this.tipTonskogZapisa = podeljen[1];
		this.aktivnost = Boolean.valueOf(podeljen[2]);
		this.podrzaniTipovi = new ArrayList<TipPredstave>();
		String[] zaListuTP = podeljen[3].split("\\,");
		for (String s : zaListuTP) {
			TipPredstave tp = TipPredstave.valueOf(s);
			this.podrzaniTipovi.add(tp);
		}
		this.sedista = new ArrayList<Sediste>();
		String[] zaListuSedista = podeljen[4].split("\\,");
		for (int i = 0; i < zaListuSedista.length; i++) {
			String sediste = zaListuSedista[i];
			String[] dodelaSedista = sediste.split("\\:");
			Sediste sed = new Sediste(Integer.valueOf(dodelaSedista[0]), Integer.valueOf(dodelaSedista[1]));
			this.sedista.add(sed);
		}

	}

	@Override
	public String toString() {
		return String.format("%-15s %-15s  %-25s  %-15s", naziv,tipTonskogZapisa,podrzaniTipovi,sedista.size());
	
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTipTonskogZapisa() {
		return tipTonskogZapisa;
	}

	public void setTipTonskogZapisa(String tipTonskogZapisa) {
		this.tipTonskogZapisa = tipTonskogZapisa;
	}

	public ArrayList<TipPredstave> getPodrzaniTipovi() {
		return podrzaniTipovi;
	}

	public void setPodrzaniTipovi(ArrayList<TipPredstave> podrzaniTipovi) {
		this.podrzaniTipovi = podrzaniTipovi;
	}

	public ArrayList<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(ArrayList<Sediste> sedista) {
		this.sedista = sedista;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}

	public boolean getAktivnot() {
		return aktivnost;
	}

}

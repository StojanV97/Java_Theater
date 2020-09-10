package korisnici;

import java.util.ArrayList;

import mein.Main;
import poz.Predstava;

public class Menadzer extends Korisnik {
	public ArrayList<Predstava> dodatePredstave;


	public Menadzer() {

	}

	public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime,boolean aktivnost,
			ArrayList<Predstava> dodatePredstave) {
		super(korisnickoIme, lozinka, ime, prezime, aktivnost);
		this.dodatePredstave = dodatePredstave;
	}

	public Menadzer(String line) {
		String[] podeljen = line.split("\\|");
		super.setKorisnickoIme(podeljen[1]);
		super.setLozinka(podeljen[2]);
		super.setIme(podeljen[3]);
		super.setPrezime(podeljen[4]);
		super.setAktivnost(Boolean.valueOf(podeljen[5]));
		if (!(podeljen[6].equals("null"))) {
			this.dodatePredstave = new ArrayList<Predstava>();
			String[] pred = podeljen[6].split("\\,");
			for (Predstava p : Main.svePredstave) {
				for (int a = 0; a < pred.length; a++) {
					if (pred[a].equals(p.getNaziv())) {
						dodatePredstave.add(p);
					}
				}
			}
		}else{
			this.dodatePredstave = null;
		}
	}
	@Override
	public String toString() {
		return String.format("%-15s %-15s %-15s %s", korisnickoIme,ime,prezime,dodatePredstave);
	}
	

	public ArrayList<Predstava> getDodatePredstave() {
		return dodatePredstave;
	}


	public void setDodatePredstave(ArrayList<Predstava> dodatePredstave) {
		this.dodatePredstave = dodatePredstave;
	}

}

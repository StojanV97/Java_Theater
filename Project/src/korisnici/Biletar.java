package korisnici;

import java.util.ArrayList;

import mein.Main;
import organizacija.Karta;

public class Biletar extends Korisnik {
	public ArrayList<Karta> prodateKarte;


	public Biletar() {

	}

	public Biletar(String korisnickoIme, String lozinka, String ime, String prezime, boolean aktivnost,
			ArrayList<Karta> prodateKarte) {
		super(korisnickoIme, lozinka, ime, prezime, aktivnost);
		this.prodateKarte = prodateKarte;

	}

	public Biletar(String line) {
		String[] podeljen = line.split("\\|");
		super.setKorisnickoIme(podeljen[1]);
		super.setLozinka(podeljen[2]);
		super.setIme(podeljen[3]);
		super.setPrezime(podeljen[4]);
		super.setAktivnost(Boolean.valueOf(podeljen[5]));
		if (!podeljen[6].equals("null")){
			this.prodateKarte = new ArrayList<Karta>();
			String[] podeljenKarte = podeljen[6].split("\\,");
			//System.out.println(podeljenKarte[0]);
			for(Karta k : Main.listaKarata){
				for(int i = 0;i< podeljenKarte.length;i++){
					if(podeljenKarte[i].equals(k.getSerijskiBroj())){
						this.prodateKarte.add(k);
					}
				}
			}
		}else{
			this.prodateKarte = null;
		}

	}

	public String toString() {
		return String.format("%-15s %-15s %-15s ", korisnickoIme,ime,prezime);
	}

	public ArrayList<Karta> getProdatekarte() {
		return prodateKarte;
	}

	

	public void setProdatekarte(ArrayList<Karta> prodatekarte) {
		this.prodateKarte = prodatekarte;
	}

}

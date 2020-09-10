package poz;

public class Predstava {
	String naziv;
	TipPredstave tipPredstave;
	String reziser;
	String glumci;
	int trajanje;
	String produkcija;
	int godinaPremijere;
	String opis;
	boolean aktivnost;

	public Predstava() {

	}

	public Predstava(String naziv, TipPredstave tipPredstave, String reziser, String glumci, int trajanje,
			String produkcija, int godinaPremijere, String opis,boolean aktivnost) {

		this.naziv = naziv;
		this.tipPredstave = tipPredstave;
		this.reziser = reziser;
		this.glumci = glumci;
		this.trajanje = trajanje;
		this.produkcija = produkcija;
		this.godinaPremijere = godinaPremijere;
		this.opis = opis;
		this.aktivnost = aktivnost;

	}
	
	public Predstava(String line){
		String[] podeljen = line.split("\\|");
		this.naziv = podeljen[0];
		this.reziser = podeljen[2];
		this.glumci = podeljen[3];
		this.opis = podeljen[7];
		this.produkcija = podeljen[5];
		this.tipPredstave = TipPredstave.valueOf(podeljen[1].toUpperCase());
		this.trajanje = Integer.parseInt(podeljen[4]);
		this.godinaPremijere = Integer.parseInt(podeljen[6]);
		this.aktivnost = Boolean.valueOf(podeljen[8]);
	}

	@Override
	public String toString() {
		return String.format("%-24s %-15s %-40s %-25s %-15s %-15s %-15s %-15s ", naziv,reziser,glumci,opis,produkcija,tipPredstave,trajanje,godinaPremijere);
		
	}
	

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipPredstave getTipPredstave() {
		return tipPredstave;
	}

	public void setTipPredstave(TipPredstave tipPredstave) {
		this.tipPredstave = tipPredstave;
	}

	public String getReziser() {
		return reziser;
	}

	public void setReziser(String reziser) {
		this.reziser = reziser;
	}

	public String getGlumci() {
		return glumci;
	}

	public void setGlumci(String glumci) {
		this.glumci = glumci;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public String getProdukcija() {
		return produkcija;
	}

	public void setProdukcija(String produkcija) {
		this.produkcija = produkcija;
	}

	public int getGodinaPremijere() {
		return godinaPremijere;
	}

	public void setGodinaPremijere(int godinaPremijere) {
		this.godinaPremijere = godinaPremijere;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Boolean getAktivnost(){
		return aktivnost;
	}
	public void setAktivnost(boolean aktivnost){
		this.aktivnost = aktivnost;
	}


}

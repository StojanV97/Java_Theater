package korisnici;

public abstract class Korisnik {
	public String korisnickoIme;
	public String lozinka;
	public String ime;
	public String prezime;
	boolean aktivnost;

	public Korisnik() {

	}

	public Korisnik(String korisnkckoIme, String lozinka, String ime, String prezime,boolean aktivnost) {

	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public void setAktivnost(boolean aktivnost){
		this.aktivnost = aktivnost;
	}
	public boolean getAktivnost(){
		return aktivnost;
	}



}

package organizacija;

public class Sediste {
	public int red;
	public int broj;

	
	public Sediste(){
		
	}
	
	public Sediste(int red,int broj){
		this.red = red;
		this.broj = broj;
		//this.aktivnost = aktivnost;
	}
	@Override
	public String toString() {
		return  (red +":" +broj) ;
	}

	public int getRed() {
		return red;
	}


	public void setRed(int red) {
		this.red = red;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	
	
}

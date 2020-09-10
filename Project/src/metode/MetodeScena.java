package metode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mein.Main;
import organizacija.Karta;
import organizacija.Sediste;
import poz.Scena;
import poz.TipPredstave;
import unos.Utility;

public class MetodeScena {
	static Scanner sc;
	public static String path = "."+System.getProperty("file.separator");

	public static void ucitajScene(ArrayList<Scena> scena) throws IOException {
		BufferedReader bf = new BufferedReader(
				new FileReader(path+"scena.txt"));
		String linija = null;
		while ((linija = bf.readLine()) != null) {
			try{
				Scena scenaObj = new Scena(linija);
				scena.add(scenaObj);
				
			}catch (Exception e){
				//do nothing
			}
		}
		bf.close();
	}

	public static void prikaziScene(ArrayList<Scena> listaScena) {
		System.out.printf("%-15s %-15s %-27s %-15s ","Naziv scene","Tip ton. Zap.","Podtipovi predstave","Broj sedista");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		for (Scena sc : listaScena) {
			if(sc.getAktivnot() != false){
			System.out.println(sc.toString());
			System.out.println("-----------------------------------------------------------------------");
		}
		}
	}

	public static void upisiScene(ArrayList<Scena> scena) throws IOException {
		PrintWriter upis = new PrintWriter(
				new FileWriter(path + "scena.txt"));
		String[] spojenaSedista = null;
		for (Scena sc : scena) {
			spojenaSedista = new String[sc.getSedista().size()];
			for (int i = 0; i < sc.getSedista().size(); i++) {
				spojenaSedista[i] = String.format("%s:%s", String.valueOf(sc.getSedista().get(i).getRed()),
						String.valueOf(sc.getSedista().get(i).getBroj()));
			}
			String sedista = String.join(",", spojenaSedista);

			String[] spojeniTipovi = new String[sc.getPodrzaniTipovi().size()];
			for (int j = 0; j < sc.getPodrzaniTipovi().size(); j++) {
				spojeniTipovi[j] = String.valueOf(sc.getPodrzaniTipovi().get(j));

			}
			String podTipovi = String.join(",", spojeniTipovi);

			String linija = sc.getNaziv() + "|" + sc.getTipTonskogZapisa() + "|" + sc.getAktivnot() + "|" + podTipovi
					+ "|" + sedista;

			upis.append(linija);
			upis.append("\n");

		}
		upis.close();

	}
	
	public static void unesiScenu(ArrayList<Scena> scena) {
		sc = new Scanner(System.in);
		boolean pronasao = false;
		String nazivScene = null;
		while (pronasao == false) {
			int i;
			System.out.print("Unesi naziv scene: ");
			nazivScene = Utility.unesiTekst(sc);
			for (i = 0; i < scena.size(); i++) {
				if (nazivScene.equals(scena.get(i).getNaziv())) {
					System.out.println("Morate uneti drugi naziv:");
					break;
				}

			}
			if (i == scena.size()) {
				pronasao = true;
			}
		}
		System.out.print("Unesite tipTonskog zapisa: ");
		String tTZ = Utility.unesiTekst(sc);
		System.out.print("Unesite sediste(red): ");
		int a = Utility.unesiInt(sc);
		System.out.print("Unesite sediste(kolona): ");
		int b = Utility.unesiInt(sc);
		ArrayList<Sediste> listaSedista = new ArrayList<Sediste>();
		System.out.println("Moguca Sedista su: ");
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				Sediste sediste = new Sediste(i + 1, j + 1);
				System.out.print(sediste + " ");
				listaSedista.add(sediste);
			}
			System.out.println();

		}
		ArrayList<TipPredstave> listaTipova = new ArrayList<TipPredstave>();
		boolean ptpA = false;
		while(ptpA == false){
			System.out.print("Unesite tipPredstave(Broj podrzanih tipova predstave : (1,2,3): ");
			int unos = Utility.unesiInt(sc);
			if(unos > 0 && unos <=3){
				for (int j = 0; j < unos; j++) {
					System.out.println("unesite podtip");
					TipPredstave tip = Utility.unesiEnum(sc); 	
					listaTipova.add(tip);
					ptpA = true;
				}							
			}else{
				System.out.println("Pokusajte ponovo...");
			}
		}

		boolean aktivnost = true;
		Scena scena1 = new Scena(nazivScene, tTZ, aktivnost, listaTipova, listaSedista);
		scena.add(scena1);
		System.out.println(scena.toString());

	}

	public static void obrisiScenu(ArrayList<Scena> listaScena, ArrayList<Karta> listaKarata) {
		sc = new Scanner(System.in);
		System.out.print("Unesite naziv scene koju zelite da obrisete: ");
		String naziv = Utility.unesiTekst(sc);
		Scena scena = null;
		boolean pronasaoScenu = false;
		for (Scena sc : listaScena) {
			if (sc.getAktivnot() != false){
			if (naziv.equalsIgnoreCase(sc.getNaziv())) {
				scena = sc;
				pronasaoScenu = true;
			}
		}
		}
		int brojac = listaKarata.size();
		if (pronasaoScenu == true) {
			for (Karta k : listaKarata) {
				brojac--;
				if (scena.getNaziv().equals(k.getIzvodjenje().getScena().getNaziv())) {
					System.out.println("Prodata je karta za ovu scen!!!");
					pronasaoScenu = false;
					brojac++;
				} else if (brojac == 0) {
					scena.setAktivnost(false);
					//System.out.println(scena.toString());
				}
			}
		}
		if (pronasaoScenu == false && brojac != 0) {
			System.out.println("Scena nije pronadjena!");
			obrisiScenu(listaScena, listaKarata);
		}
	}
}

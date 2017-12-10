import java.util.Scanner;

public class Oyuncu {
	/*
	 * Bu Class Oyuncu'larý temsil etmektedir.
	 */

	char harf;
	boolean insanmiKontrolu;
	String kullaniciAdi;
	Tahta tahta;
	Scanner scan = new Scanner(System.in);

	public Oyuncu(Tahta t) {
		// Sadece oyun tahtasý parametresi alan yapýcý
		harf = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = "SÝZ";
		tahta = t;
	}

	public Oyuncu(String kAdi, Tahta t) {
		// Kullanýcý adýný ve oyun tahtasýný parametre olarak alan yapýcý
		harf = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = kAdi;
		tahta = t;
	}

	public Oyuncu(boolean insanmi, Tahta t) {
		// insanMý kontrolünü ve oyun tahtasýný parametre olarak alan yapýcý.
		insanmiKontrolu = insanmi;
		tahta = t;

		if (insanmi) {
			kullaniciAdi = "SÝZ";
			harf = 'X';
		} else {
			kullaniciAdi = "PC";
			harf = 'O';
		}
	}

	public Oyuncu(boolean insanmi, char harf, Tahta t) {
		// insanMý kontrolünü ve seçilen harfi parametre olarak alan yapýcý
		this.insanmiKontrolu = insanmi;
		this.harf = harf;
		this.tahta = t;
	}

	public Oyuncu(String kadi, boolean insanmi, char harf, Tahta t) {
		// Kullanýcý adýný, insanMý kontrolünü ve harfi alan yapýcý.
		this.insanmiKontrolu = insanmi;
		this.harf = harf;
		this.tahta = t;
		this.kullaniciAdi = kadi;
	}

	public char karakteriAl() {
		return harf;
	}

	public String kullaniciAdiAl() {
		return kullaniciAdi;
	}

	boolean oyuncuTurunuAl() {
		return insanmiKontrolu;
	}

	String oyuncununHamlesiniAl() {
		if (insanmiKontrolu) {
			return insanOyuncuHamlesiniKotrol();
		} else
			return bilgisayarHamlesiUret();
	}

	String insanOyuncuHamlesiniKotrol() {
		// Oyuncunun hamlesini okuyup döndüren method
		System.out.println("Lütfen hamlenizi giriniz: (Oyunu kaydetmek için \"KAYDET\" yazýnýz)");
		String hamle = scan.nextLine();
		if (hamle.equalsIgnoreCase("KAYDET")) {
			return hamle;
		}
		return hamle + " " + harf;
	}

	String bilgisayarHamlesiUret() {
		// random bir bilgisayar hamlesi üreten method.
		int n = tahta.oyunTahtasiAl().length;
		int i = randomWithRange(0, n - 1);
		int j = randomWithRange(0, n - 1);
		char hucreHarf = (char) (j + 65);
		return Character.toString(hucreHarf) + i + " " + harf;

	}

	static int randomWithRange(int min, int max) {
		// random bir sayý üreten method.
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}

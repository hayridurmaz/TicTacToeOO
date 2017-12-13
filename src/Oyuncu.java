
import java.util.Scanner;

public class Oyuncu {

	/*
	 * Bu Class Oyuncu'larý temsil etmektedir.
	 */

	char karakter;
	boolean insanmiKontrolu;
	String kullaniciAdi;
	Tahta tahta;
	Scanner scan;

	public Oyuncu(Tahta t) {
		// Sadece oyun tahtasý parametresi alan yapýcý
		karakter = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = "INSAN";
		tahta = t;
	}

	public Oyuncu(String kAdi, Tahta t) {
		// Kullanýcý adýný ve oyun tahtasýný parametre olarak alan yapýcý
		karakter = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = kAdi;
		tahta = t;
	}

	public Oyuncu(boolean insanmi, Tahta t) {
		// insanMý kontrolünü ve oyun tahtasýný parametre olarak alan yapýcý.
		insanmiKontrolu = insanmi;
		tahta = t;

		if (insanmi) {
			kullaniciAdi = "INSAN";
			karakter = 'X';
		} else {
			kullaniciAdi = "PC";
			karakter = 'O';
		}
	}

	public Oyuncu(String kAdi, boolean insanmi, Tahta t) {
		// insanMý kontrolünü ve oyun tahtasýný parametre olarak alan yapýcý.
		insanmiKontrolu = insanmi;
		tahta = t;

		if (insanmi) {
			kullaniciAdi = kAdi;
			karakter = 'X';
		} else {
			kullaniciAdi = kAdi;
			karakter = 'O';
		}
	}

	public Oyuncu(boolean insanmi, char harf, Tahta t) {
		// insanMý kontrolünü ve seçilen harfi parametre olarak alan yapýcý
		this.insanmiKontrolu = insanmi;
		this.karakter = harf;
		this.tahta = t;
	}

	public Oyuncu(String kadi, boolean insanmi, char harf, Tahta t) {
		// Kullanýcý adýný, insanMý kontrolünü ve harfi alan yapýcý.
		this.insanmiKontrolu = insanmi;
		this.karakter = harf;
		this.tahta = t;
		this.kullaniciAdi = kadi;
	}

	public char karakteriAl() {
		return karakter;
	}

	public String kullaniciAdiAl() {
		return kullaniciAdi;
	}

	boolean oyuncuTurunuAl() {
		return insanmiKontrolu;
	}

	String insanOyuncuHamlesiniKotrol() {
		// Oyuncunun hamlesini okuyup döndüren method
		scan = new Scanner(System.in);
		System.out.println("Lütfen hamlenizi giriniz: (Oyunu kaydetmek için \"KAYIT\" yazýnýz)");
		String hamle = scan.nextLine();
		if (hamle.equalsIgnoreCase("KAYIT")) {
			return hamle;
		}
		return hamle + " " + karakter;
	}

	String bilgisayarHamlesiUret() {
		// random bir bilgisayar hamlesi üreten method.
		int n = tahta.oyunPanosuAl().length;
		int i = randomWithRange(0, n - 1);
		int j = randomWithRange(0, n - 1);
		char hucreHarf = (char) (j + 65);
		return Character.toString(hucreHarf) + i + " " + karakter;

	}

	String oyuncununHamlesiniAl() {
		if (insanmiKontrolu) {
			return insanOyuncuHamlesiniKotrol();
		} else {
			return bilgisayarHamlesiUret();
		}
	}

	static int randomWithRange(int min, int max) {
		// random bir sayý üreten method.
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}

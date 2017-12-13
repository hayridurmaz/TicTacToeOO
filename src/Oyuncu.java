
import java.util.Scanner;

public class Oyuncu {

	/*
	 * Bu Class Oyuncu'lar� temsil etmektedir.
	 */

	char karakter;
	boolean insanmiKontrolu;
	String kullaniciAdi;
	Tahta tahta;
	Scanner scan;

	public Oyuncu(Tahta t) {
		// Sadece oyun tahtas� parametresi alan yap�c�
		karakter = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = "INSAN";
		tahta = t;
	}

	public Oyuncu(String kAdi, Tahta t) {
		// Kullan�c� ad�n� ve oyun tahtas�n� parametre olarak alan yap�c�
		karakter = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = kAdi;
		tahta = t;
	}

	public Oyuncu(boolean insanmi, Tahta t) {
		// insanM� kontrol�n� ve oyun tahtas�n� parametre olarak alan yap�c�.
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
		// insanM� kontrol�n� ve oyun tahtas�n� parametre olarak alan yap�c�.
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
		// insanM� kontrol�n� ve se�ilen harfi parametre olarak alan yap�c�
		this.insanmiKontrolu = insanmi;
		this.karakter = harf;
		this.tahta = t;
	}

	public Oyuncu(String kadi, boolean insanmi, char harf, Tahta t) {
		// Kullan�c� ad�n�, insanM� kontrol�n� ve harfi alan yap�c�.
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
		// Oyuncunun hamlesini okuyup d�nd�ren method
		scan = new Scanner(System.in);
		System.out.println("L�tfen hamlenizi giriniz: (Oyunu kaydetmek i�in \"KAYIT\" yaz�n�z)");
		String hamle = scan.nextLine();
		if (hamle.equalsIgnoreCase("KAYIT")) {
			return hamle;
		}
		return hamle + " " + karakter;
	}

	String bilgisayarHamlesiUret() {
		// random bir bilgisayar hamlesi �reten method.
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
		// random bir say� �reten method.
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}

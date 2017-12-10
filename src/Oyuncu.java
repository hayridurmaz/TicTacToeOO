import java.util.Scanner;

public class Oyuncu {
	/*
	 * Bu Class Oyuncu'lar� temsil etmektedir.
	 */

	char harf;
	boolean insanmiKontrolu;
	String kullaniciAdi;
	Tahta tahta;
	Scanner scan = new Scanner(System.in);

	public Oyuncu(Tahta t) {
		// Sadece oyun tahtas� parametresi alan yap�c�
		harf = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = "S�Z";
		tahta = t;
	}

	public Oyuncu(String kAdi, Tahta t) {
		// Kullan�c� ad�n� ve oyun tahtas�n� parametre olarak alan yap�c�
		harf = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = kAdi;
		tahta = t;
	}

	public Oyuncu(boolean insanmi, Tahta t) {
		// insanM� kontrol�n� ve oyun tahtas�n� parametre olarak alan yap�c�.
		insanmiKontrolu = insanmi;
		tahta = t;

		if (insanmi) {
			kullaniciAdi = "S�Z";
			harf = 'X';
		} else {
			kullaniciAdi = "PC";
			harf = 'O';
		}
	}

	public Oyuncu(boolean insanmi, char harf, Tahta t) {
		// insanM� kontrol�n� ve se�ilen harfi parametre olarak alan yap�c�
		this.insanmiKontrolu = insanmi;
		this.harf = harf;
		this.tahta = t;
	}

	public Oyuncu(String kadi, boolean insanmi, char harf, Tahta t) {
		// Kullan�c� ad�n�, insanM� kontrol�n� ve harfi alan yap�c�.
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
		// Oyuncunun hamlesini okuyup d�nd�ren method
		System.out.println("L�tfen hamlenizi giriniz: (Oyunu kaydetmek i�in \"KAYDET\" yaz�n�z)");
		String hamle = scan.nextLine();
		if (hamle.equalsIgnoreCase("KAYDET")) {
			return hamle;
		}
		return hamle + " " + harf;
	}

	String bilgisayarHamlesiUret() {
		// random bir bilgisayar hamlesi �reten method.
		int n = tahta.oyunTahtasiAl().length;
		int i = randomWithRange(0, n - 1);
		int j = randomWithRange(0, n - 1);
		char hucreHarf = (char) (j + 65);
		return Character.toString(hucreHarf) + i + " " + harf;

	}

	static int randomWithRange(int min, int max) {
		// random bir say� �reten method.
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}

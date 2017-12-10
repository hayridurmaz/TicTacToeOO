import java.util.Scanner;

public class Oyuncu {

	char harf;
	boolean insanmiKontrolu;
	String kullaniciAdi;
	Tahta tahta;
	Scanner scan = new Scanner(System.in);

	public Oyuncu(Tahta t) {
		harf = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = "BO�";
		tahta = t;
	}

	public Oyuncu(String kAdi, Tahta t) {
		harf = 'X';
		insanmiKontrolu = true;
		kullaniciAdi = kAdi;
		tahta = t;
	}

	public Oyuncu(boolean insanmi, Tahta t) {
		insanmiKontrolu = insanmi;
		tahta = t;
		kullaniciAdi="BO�";
		if (insanmi) {
			harf = 'X';
		} else {
			harf = 'O';
		}
	}

	public Oyuncu(boolean insanmi, char harf, Tahta t) {
		this.insanmiKontrolu = insanmi;
		this.harf = harf;
		this.tahta = t;
	}
	public Oyuncu(String kadi, boolean insanmi, char harf, Tahta t) {
		this.insanmiKontrolu = insanmi;
		this.harf = harf;
		this.tahta = t;
		this.kullaniciAdi=kadi;
	}

	public char karakteriAl() {
		return harf;
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
		System.out.println("L�tfen hamlenizi giriniz: ");
		String hamle = scan.nextLine();
		return hamle+" "+harf;
	}

	String bilgisayarHamlesiUret() {
		int n = tahta.oyunTahtasiAl().length;
		int i = randomWithRange(0, n - 1);
		int j = randomWithRange(0, n - 1);
		char hucreHarf = (char) (j + 65);
		return Character.toString(hucreHarf) + i + " " + harf;

	}

	static int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}
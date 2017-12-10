import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Tahta tahta;
		Oyuncu o1, o2;
		String oyunSecenek = "";
		while (!(oyunSecenek.equals("1") || oyunSecenek.equals("2"))) {
			System.out.println("Merhaba, kay�tl� bir oyun y�klemek i�in 1, Yeni oyun i�in 2 giriniz: ");
			oyunSecenek = scan.next();
		}

		String hamle1, hamle2;
		tahta = Tahta.kayitliOyunAl();
		if (oyunSecenek.equals("1") && tahta != null) {
			o1 = tahta.oyuncu1Al();
			o2 = tahta.oyuncu2Al();
			tahta.oyunTahtasiYazdir();

		} else {
			System.out.println("Yeni oyun kurulumu\nKullan�c� Ad�n�z� giriniz: ");
			String kAdi = scan.next();
			System.out
					.println("Oyun tahtas�n�n ka�a ka�l�k olmas�n� istiyorsan�z giriniz(�rne�in 3x3 i�in 3 giriniz): ");
			int N = 3;
			try {
				N = scan.nextInt();
			} catch (Exception e) {
				System.err.println(
						"Yanl�� input girdiniz. Tam say� olmas� gerekiyor. Varsay�lan de�er:3 olarak girildi.");
			}

			System.out.println(
					"Se�mek istedi�iniz karakteri giriniz (X / O). Otomatik se�mek i�in ba�ka bir �ey giriniz");
			String girdiKarakter = scan.next();

			char girdiChar;

			if (girdiKarakter.equalsIgnoreCase("X")) {
				girdiChar = 'X';
			} else if (girdiKarakter.equalsIgnoreCase("O")) {
				girdiChar = 'O';
			} else
				girdiChar = Character.MIN_VALUE;

			tahta = new Tahta(N);
			// o1, o2;
			if (girdiChar == Character.MIN_VALUE) {
				o1 = new Oyuncu(true, tahta);
				o2 = new Oyuncu(false, tahta);
			} else {
				o1 = new Oyuncu(kAdi, true, girdiChar, tahta);
				if (girdiChar == 'X') {
					o2 = new Oyuncu(false, tahta);
				} else {
					o2 = new Oyuncu("PC", false, 'X', tahta);
				}
			}

			tahta.oyuncuEkle(o1, o2);
		}

		while (true) {
			hamle1 = o1.oyuncununHamlesiniAl();
			if (hamle1.equalsIgnoreCase("KAYDET")) {
				if (tahta.oyunKaydet(o1, o2)) {
					System.out.println("Oyun ba�ar�yla kaydedildi.. G�r��mek �zere..");
					System.exit(0);
				}
			}
			try {
				boolean hamleDogrumu = tahta.hamleyiYaz(hamle1.substring(0, 2), hamle1.substring(3).charAt(0),
						o1.insanmiKontrolu);
				while (!hamleDogrumu) {
					hamle1 = o1.oyuncununHamlesiniAl();
					hamleDogrumu = tahta.hamleyiYaz(hamle1.substring(0, 2), hamle1.substring(3).charAt(0),
							o1.insanmiKontrolu);
				}

			} catch (Exception e) {
				System.err.println("Yanl�� inputn girdiniz. Girdi �rne�i: \"A1\"");
			}

			tahta.oyunTahtasiYazdir();
			if (tahta.kazanan(o1.harf)) {
				System.out.println(o1.kullaniciAdiAl() + " " + o1.karakteriAl() + " karakteriyle KAZANDI!");
				break;
			}
			if (tahta.beraberlikKontrol()) {
				System.out.println("BERABERE");
				break;
			}

			hamle2 = o2.oyuncununHamlesiniAl();
			try {
				boolean hamleDogrumu2 = tahta.hamleyiYaz(hamle2.substring(0, 2), hamle2.substring(3).charAt(0),
						o2.insanmiKontrolu);
				while (!hamleDogrumu2) {
					hamle2 = o2.oyuncununHamlesiniAl();
					hamleDogrumu2 = tahta.hamleyiYaz(hamle2.substring(0, 2), hamle2.substring(3).charAt(0),
							o2.insanmiKontrolu);
				}
			} catch (Exception e) {
				System.err.println("Bir�eyler yanl�� gitti, Bilgisayar input hatas�?");
			}
			tahta.oyunTahtasiYazdir();
			if (tahta.kazanan(o2.harf)) {
				System.out.println(o2.kullaniciAdiAl() + " " + o2.karakteriAl() + " karakteriyle KAZANDI!");
				break;
			}
			if (tahta.beraberlikKontrol()) {
				System.out.println("BERABERE");
				break;
			}

		}

		// System.out.println(tahta.oyunKaydet(o1, o2));
		scan.close();
	}

}

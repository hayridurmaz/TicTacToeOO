import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Driver methodu.
		Scanner scan = new Scanner(System.in);
		Tahta tahta;
		Oyuncu o1, o2;
		String menuGirdi = "";

		while (!(menuGirdi.equals("1") || menuGirdi.equals("2"))) {
			System.out.println("Hoþgeldiniz.\nMenü\n(1)Yeni Oyun\n(2)Kayýtlý oyun yükle");
			menuGirdi = scan.next();
		}

		String hamle1, hamle2;

		if (menuGirdi.equals("2")) { // Kayýtlý oyun varsa
			tahta = Tahta.dosyadanAktar(); // kayýtlý oyunu al
			if (tahta == null) {
				System.exit(0);
			}
			o1 = tahta.birinciOyuncuAl();
			o2 = tahta.ikinciOyuncuAl();
			tahta.oyunPanosuYazdir();

		} else {// Yeni oyuna baþla
			System.out.println("Yeni oyun kurulumu\nKullanýcý Adýnýzý giriniz: ");
			String kAdi = scan.next();
			System.out.println("Oyun tahtasý boyutu:\n(1) 3x3\n(2)5x5\n(3)7x7 ");
			String tahtaBoyutu = scan.next();
			int N = 3;
			switch (tahtaBoyutu) {
			case "1":
				N = 3;
				break;
			case "2":
				N = 5;
				break;
			case "3":
				N = 7;
				break;
			default:
				System.err.println("Yanlýþ girdi girdiniz");
				System.exit(0);
			}

			System.out.println("Seçmek istediðiniz karakteri giriniz:\n(1)X\n(2)O\n(3)Farketmez");
			String girdiKarakter = scan.next();

			char girdiChar;

			if (girdiKarakter.equalsIgnoreCase("1")) {
				girdiChar = 'X';
			} else if (girdiKarakter.equalsIgnoreCase("2")) {
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

		tahta.oyunPanosuYazdir();
		System.out.println("Örnek hamle: \"B1\"");
		while (true) {
			hamle1 = o1.oyuncununHamlesiniAl();
			if (hamle1.equalsIgnoreCase("KAYIT")) {
				if (tahta.oyunKayit()) {
					System.out.println("Oyun baþarýyla kaydedildi..");
					System.exit(0);
				}
			}
			try {
				boolean hamleKontrolu1 = tahta.hamleyiYaz(hamle1.substring(0, 2), hamle1.substring(3).charAt(0),
						o1.insanmiKontrolu);
				while (!hamleKontrolu1) {
					hamle1 = o1.oyuncununHamlesiniAl();
					hamleKontrolu1 = tahta.hamleyiYaz(hamle1.substring(0, 2), hamle1.substring(3).charAt(0),
							o1.insanmiKontrolu);
				}

			} catch (Exception e) {
				System.err.println("Yanlýþ girdi girdiniz. Örnek hamle: \"B1\"");
			}

			tahta.oyunPanosuYazdir();
			if (tahta.galipMi(o1.karakter)) {
				System.out.println(
						o1.kullaniciAdiAl() + " isimli kullanýcý " + o1.karakteriAl() + " karakteriyle KAZANDI!");
				break;
			}
			if (tahta.beraberlikKontrol()) {
				System.out.println("BERABERE");
				break;
			}

			hamle2 = o2.oyuncununHamlesiniAl();
			try {
				boolean hamleKontrolu2 = tahta.hamleyiYaz(hamle2.substring(0, 2), hamle2.substring(3).charAt(0),
						o2.insanmiKontrolu);
				while (!hamleKontrolu2) {
					hamle2 = o2.oyuncununHamlesiniAl();
					hamleKontrolu2 = tahta.hamleyiYaz(hamle2.substring(0, 2), hamle2.substring(3).charAt(0),
							o2.insanmiKontrolu);
				}
			} catch (Exception e) {
				System.err.println("Birþeyler yanlýþ gitti");
			}
			tahta.oyunPanosuYazdir();
			if (tahta.galipMi(o2.karakter)) {
				System.out.println(
						o2.kullaniciAdiAl() + " isimli kullanýcý " + o2.karakteriAl() + " karakteriyle KAZANDI!");
				break;
			}
			if (tahta.beraberlikKontrol()) {
				System.out.println("BERABERE");
				break;
			}

		}
		scan.close();
	}

}

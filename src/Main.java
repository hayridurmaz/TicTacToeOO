import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Merhaba, Kullanýcý Adýnýzý giriniz: ");
		String kAdi = scan.nextLine();
		System.out.println("Oyun tahtasýnýn kaça kaçlýk olmasýný istiyorsanýz giriniz(Örneðin 3x3 için 3 giriniz): ");
		int N = 3;
		try {
			N = scan.nextInt();
		} catch (Exception e) {
			System.err.println("Yanlýþ input girdiniz. Tam sayý olmasý gerekiyor. Varsayýlan deðer:3 olarak girildi.");
		}

		System.out.println("Seçmek istediðiniz karakteri giriniz (X / O). Otomatik seçmek için baþka bir þey giriniz");
		String girdiKarakter = scan.next();

		char girdiChar;

		if (girdiKarakter.equalsIgnoreCase("X")) {
			girdiChar = 'X';
		} else if (girdiKarakter.equalsIgnoreCase("O")) {
			girdiChar = 'O';
		} else
			girdiChar = Character.MIN_VALUE;

		Tahta tahta = new Tahta(N);
		Oyuncu o1, o2;
		if (girdiChar == Character.MIN_VALUE) {
			o1 = new Oyuncu(true, tahta);
			o2 = new Oyuncu(false, tahta);
		} else {
			o1 = new Oyuncu(kAdi, true, girdiChar, tahta);
			if (girdiChar == 'X') {
				o2 = new Oyuncu(false, tahta);
			} else {
				o2 = new Oyuncu("PC",false, 'X', tahta);
			}
		}

		String hamle1, hamle2;
		while (true) {
			hamle1 = o1.oyuncununHamlesiniAl();
			try {
				boolean hamleDogrumu = tahta.hamleyiYaz(hamle1.substring(0, 2), hamle1.substring(3).charAt(0),
						o1.insanmiKontrolu);
				while (!hamleDogrumu) {
					hamle1 = o1.oyuncununHamlesiniAl();
					hamleDogrumu = tahta.hamleyiYaz(hamle1.substring(0, 2), hamle1.substring(3).charAt(0),
							o1.insanmiKontrolu);
				}

			} catch (Exception e) {
				System.err.println("Yanlýþ inputn girdiniz. Girdi örneði: \"A1\"");
			}

			tahta.oyunTahtasiYazdir();
			if (tahta.kazanan(o1.harf)) {
				System.out.println(o1.kullaniciAdiAl() + " "+o2.karakteriAl()+" karakteriyle KAZANDI!");
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
				System.err.println("Birþeyler yanlýþ gitti, Bilgisayar input hatasý?");
			}
			tahta.oyunTahtasiYazdir();
			if (tahta.kazanan(o2.harf)) {
				System.out.println(o2.kullaniciAdiAl() + " "+o2.karakteriAl()+" karakteriyle KAZANDI!");
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

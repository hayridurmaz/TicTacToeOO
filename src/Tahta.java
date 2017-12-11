import java.io.*;
import java.util.*;

public class Tahta {
	/*
	 * Bu class bir oyun tahtasýný temsil ediyor.
	 * 
	 */
	char[][] oyunPanosu;
	int N;
	int toplamHamleAdedi;
	int anlikI, anlikJ;
	Oyuncu oyuncu1, oyuncu2;

	public Tahta(int uzunluk) {
		// Tahtanýn uzunluðunu alan yapýcý
		oyunPanosu = new char[uzunluk][uzunluk];
		toplamHamleAdedi = 0;
		N = uzunluk;
	}

	public Tahta(char[][] oPanosu) {
		// Char arrayinden Oyun tahtasý yaratan yapýcý
		oyunPanosu = oPanosu;
		toplamHamleAdedi = toplamHamleSayisi(oPanosu);
		N = oPanosu.length;
	}

	public void oyuncuEkle(Oyuncu o1, Oyuncu o2) {
		// Oyun tahtasýna oyuncu eklemek için kullanýlan method.
		this.oyuncu1 = o1;
		this.oyuncu2 = o2;
	}

	public char[][] oyunPanosuAl() {
		return oyunPanosu;
	}

	public boolean hamleyiYaz(String nereyeYaz, char oyuncuHarf, boolean insanmiKontrolu) {
		// Oyuncunun yaptýðý bir hamleyi oyun tahtasýna yazan method
		if (nereyeYaz.length() != 2) {
			System.err.println("Yanlýþ input girdiniz. ");
			return false;
		}
		int i, j;
		char harf = nereyeYaz.charAt(0);
		try {

			i = Integer.parseInt(nereyeYaz.charAt(1) + "");
			if (insanmiKontrolu) {
				i = i - 1;
			}
			j = harfiSayiyaCevir(harf);
			if (j < 0 || j > 6) {
				System.err.println("Yanlýþ bir harf girdiniz");
				return false;
			}
			if (oyunPanosu[i][j] != Character.MIN_VALUE) {
				return false;
			}
			toplamHamleAdedi++;
			anlikI = i;
			anlikJ = j;
			oyunPanosu[i][j] = oyuncuHarf;
			return true;
		} catch (Exception e) {
			System.err.println("Yanlýþ bir girdi girdiniz.");
			return false;
		}

	}

	public int harfiSayiyaCevir(char a) {
		return (int) (a - 65);
	}

	public boolean galipMi(char oyuncuHarf) {
		// parametre olarak aldýðý karakterin kazanýp kazanmadýðýný kontrol eden
		// method.
		int galibiyetSayisi = 4;
		if (N == 3) {
			galibiyetSayisi = 3;
		}

		int galibiyetKontrol;

		// aþaðý doðru bakýyor
		galibiyetKontrol = 0;
		for (int i = 0; i < N; i++) {
			// System.out.println("winlook "+winLook);

			if (oyunPanosu[i][anlikJ] == oyuncuHarf)
				galibiyetKontrol++;
			else {
				galibiyetKontrol = 0;
			}
			if (galibiyetKontrol == galibiyetSayisi)
				return true;
		}

		// saðdan sola doðru bakýyor
		galibiyetKontrol = 0;
		for (int j = 0; j < N; j++) {

			if (oyunPanosu[anlikI][j] == oyuncuHarf)
				galibiyetKontrol++;
			else {
				galibiyetKontrol = 0;
			}
			if (galibiyetKontrol == galibiyetSayisi)
				return true;

		}

		// sað üstten sol alta bakýyor
		galibiyetKontrol = 0;
		for (int i = anlikI, j = anlikJ; i >= 0 && j >= 0; i--, j--) {

			if (oyunPanosu[i][j] == oyuncuHarf) {
				galibiyetKontrol++;
			} else {
				galibiyetKontrol = 0;
			}
			if (galibiyetKontrol == galibiyetSayisi)
				return true;

		}
		if (oyunPanosu[anlikI][anlikJ] == oyuncuHarf) {
			galibiyetKontrol--;
		}
		for (int i = anlikI, j = anlikJ; i < N && j < N; i++, j++) {

			if (oyunPanosu[i][j] == oyuncuHarf) {
				galibiyetKontrol++;
			} else {
				galibiyetKontrol = 0;
			}
			if (galibiyetKontrol == galibiyetSayisi + 1)
				return true;
		}

		// sol üstten sað alta bakýyor
		galibiyetKontrol = 0;
		for (int i = anlikI, j = anlikJ; i >= 0 && j < N; i--, j++) {

			if (oyunPanosu[i][j] == oyuncuHarf) {
				galibiyetKontrol++;
			} else {
				galibiyetKontrol = 0;
			}
			// System.out.println("winlook "+winLook);
			if (galibiyetKontrol == galibiyetSayisi)
				return true;

		}
		if (oyunPanosu[anlikI][anlikJ] == oyuncuHarf) {
			galibiyetKontrol--;
		}
		for (int i = anlikI, j = anlikJ; i < N && j >= 0; i++, j--) {

			if (oyunPanosu[i][j] == oyuncuHarf) {
				galibiyetKontrol++;
			} else {
				galibiyetKontrol = 0;
			}
			// System.out.println("winlook "+winLook);
			if (galibiyetKontrol == galibiyetSayisi)
				return true;
		}
		return false;
	}

	public boolean beraberlikKontrol() {
		// Beraberliði kontrol eden method.
		return toplamHamleAdedi >= N * N;
	}

	private int toplamHamleSayisi(char[][] oTahtasi) {
		int adet = 0;
		for (int i = 0; i < oTahtasi.length; i++) {
			for (int j = 0; j < oTahtasi[i].length; j++) {
				if (oTahtasi[i][j] != Character.MIN_VALUE) {
					adet++;
				}
			}
		}
		return adet;
	}

	public Oyuncu birinciOyuncuAl() {
		return oyuncu1;
	}

	public Oyuncu ikinciOyuncuAl() {
		return oyuncu2;
	}

	public void oyunPanosuYazdir() {
		// Oyun tahtasýný uygun formatta yazdýran method.
		System.out.print(" ");
		for (int i = 0; i < oyunPanosu.length; i++) {
			int harf = i + 65;
			System.out.print((char) harf + " ");
		}
		System.out.println();
		for (int i = 0; i < oyunPanosu.length; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < oyunPanosu[0].length; j++) {
				if (oyunPanosu[i][j] == Character.MIN_VALUE) {
					System.out.print("  ");
				} else {
					System.out.print(oyunPanosu[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public boolean oyunKayit() {
		// Oyunu kaydeden method.
		try {
			File file = new File("TicTacToeOO/src/Kayit.txt");// DÝKKATET!!!!!!
			if (!file.exists()) {
				file.createNewFile();
				dosyayaYaz(file, "KAYITLIOYUNYOK");
			}

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			if (!br.readLine().equals("KAYITLIOYUNYOK")) {
				System.out.println("Zaten kayýtlý bir oyun var!");
				br.close();
				return false;
			}
			dosyayaYaz(file, "");
			br.close();

			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			String yazýlacak = yazilacakStringAl();
			pw.write(yazýlacak);
			pw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Dosyaya yazarken hata!");
			return false;
		}
	}

	private String yazilacakStringAl() {
		String yazýlacak = "";
		yazýlacak += N + "\n" + toplamHamleAdedi + "\n" + anlikI + "\n" + anlikJ + "\n";
		yazýlacak += oyuncu1.kullaniciAdiAl() + "\n" + oyuncu1.karakter + "\n" + oyuncu1.insanmiKontrolu + "\n";
		yazýlacak += oyuncu2.kullaniciAdiAl() + "\n" + oyuncu2.karakter + "\n" + oyuncu2.insanmiKontrolu + "\n";

		for (int i = 0; i < oyunPanosu.length; i++) {
			for (int j = 0; j < oyunPanosu[i].length; j++) {
				yazýlacak += oyunPanosu[i][j] + " ";
			}
			yazýlacak = yazýlacak + "\n";
		}
		return yazýlacak;
	}

	private static boolean dosyayaYaz(File file, String str) {
		// Dosyayý tamaamen temizleyip parametre olarak aldýðý stringi yazan
		// method.
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print(str);
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("Dosyaya yazarken hata");
			e.printStackTrace();// SONRADAN SÝL
			return false;
		}
	}

	public static Tahta dosyadanAktar() {
		// Kayýtlý oyunu programa aktaran method.
		try {
			File file = new File("TicTacToeOO/src/Kayit.txt");// DÝKKATET!!!!!!
			if (!file.exists()) {
				System.err.println("Kayýtlý oyun bulunamadý");
				return null;
			}

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String ilkSatir = br.readLine();
			if (ilkSatir.equals("KAYITLIOYUNYOK")) {
				System.err.println("Kayýtlý oyun bulunamadý!");
				br.close();
				return null;
			}
			String satir;
			ArrayList<String> Satirlar = new ArrayList<>();
			Satirlar.add(ilkSatir);
			while ((satir = br.readLine()) != null) {
				Satirlar.add(satir);
			}
			br.close();
			int size = Integer.parseInt(Satirlar.get(0));
			System.out.println(size);
			char[][] kayitliOyun = new char[size][size];
			int index = 10;
			for (int i = 0; i < kayitliOyun.length; i++) {
				String str = Satirlar.get(index);
				for (int j = 0; j < kayitliOyun[i].length; j++) {
					char temp = str.charAt(2 * j);
					if (temp == ' ') {
						temp = Character.MIN_VALUE;
					}

					kayitliOyun[i][j] = temp;
				}
				index++;
			}
			Tahta tahta = new Tahta(kayitliOyun);
			tahta.toplamHamleAdedi = Integer.parseInt(Satirlar.get(1).trim());
			tahta.anlikI = Integer.parseInt(Satirlar.get(2).trim());
			tahta.anlikJ = Integer.parseInt(Satirlar.get(3).trim());
			Oyuncu o1 = new Oyuncu(Satirlar.get(4), Boolean.parseBoolean(Satirlar.get(6).trim()),
					Satirlar.get(5).charAt(0), tahta);
			Oyuncu o2 = new Oyuncu(Satirlar.get(7), Boolean.parseBoolean(Satirlar.get(9).trim()),
					Satirlar.get(8).charAt(0), tahta);

			tahta.oyuncuEkle(o1, o2);
			dosyayaYaz(file, "KAYITLIOYUNYOK");
			return tahta;
		} catch (Exception e) {
			System.err.println("Kayýtlý oyun alýnýrken hata");
			e.printStackTrace();
			return null;
		}
	}
}

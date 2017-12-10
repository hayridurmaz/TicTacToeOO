import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tahta {
	/*
	 * Bu class bir oyun tahtasýný temsil ediyor.
	 * 
	 */
	char[][] oyunTahtasi;
	int N, toplamHamleCount;
	int currentI, currentJ;
	Oyuncu oyuncu1, oyuncu2;

	public Tahta(int n) {
		// Tahtanýn uzunluðunu alan yapýcý
		oyunTahtasi = new char[n][n];
		toplamHamleCount = 0;
		N = n;
	}

	public Tahta(char[][] oTahtasi) {
		// Char arrayinden Oyun tahtasý yaratan yapýcý
		oyunTahtasi = oTahtasi;
		toplamHamleCount = getToplamHamleCount(oTahtasi);
		N = oTahtasi.length;
	}

	public void oyuncuEkle(Oyuncu o1, Oyuncu o2) {
		// Oyun tahtasýna oyuncu eklemek için kullanýlan method.
		this.oyuncu1 = o1;
		this.oyuncu2 = o2;
	}

	public char[][] oyunTahtasiAl() {
		return oyunTahtasi;
	}

	public void oyunTahtasiYazdir() {
		// Oyun tahtasýný uygun formatta yazdýran method.
		System.out.print(" ");
		for (int i = 0; i < oyunTahtasi.length; i++) {
			int harf = i + 65;
			System.out.print((char) harf + " ");
		}
		System.out.println();
		for (int i = 0; i < oyunTahtasi.length; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < oyunTahtasi[0].length; j++) {
				if (oyunTahtasi[i][j] == Character.MIN_VALUE) {
					System.out.print("  ");
				} else {
					System.out.print(oyunTahtasi[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public boolean hamleyiYaz(String koordinat, char oyuncu, boolean insanmi) {
		// Oyuncunun yaptýðý bir hamleyi oyun tahtasýna yazan method
		if (koordinat.length() != 2) {
			System.err.println("Yanlýþ input girdiniz. Girdi örneði: \"A1\"");
			return false;
		}
		int i, j;
		char harf = koordinat.charAt(0);
		try {

			i = Integer.parseInt(koordinat.charAt(1) + "");
			if (insanmi) {
				i--;
			}
			switch (harf) {
			case 'A':
				j = 0;
				break;
			case 'B':
				j = 1;
				break;
			case 'C':
				j = 2;
				break;
			case 'D':
				j = 3;
				break;
			case 'E':
				j = 4;
				break;
			case 'F':
				j = 5;
				break;
			case 'G':
				j = 6;
				break;
			default:
				System.err.println("Olmayan bir harf girdiniz");
				return false;
			}
			if (oyunTahtasi[i][j] != Character.MIN_VALUE) {
				return false;
			}
			toplamHamleCount++;
			currentI = i;
			currentJ = j;
			oyunTahtasi[i][j] = oyuncu;
			return true;
		} catch (Exception e) {
			System.err.println("Yanlýþ input girdiniz. Girdi örneði: \"A1\"");
			return false;
		}

	}

	public boolean kazanan(char oyuncu) {
		// parametre olarak aldýðý karakterin kazanýp kazanmadýðýný kontrol eden
		// method.
		int kacTaneyeBak = 4;
		if (N == 3) {
			kacTaneyeBak = 3;
		}

		System.out.println("OYUNCU: " + oyuncu);
		int winLook;

		if (oyuncu == 'X') {

			// aþaðý doðru bakýyor
			winLook = 0;
			for (int i = 0; i < N; i++) {
				// System.out.println("winlook "+winLook);

				if (oyunTahtasi[i][currentJ] == 'X')
					winLook++;
				else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak)
					return true;
			}

			// saðdan sola doðru bakýyor
			winLook = 0;
			for (int j = 0; j < N; j++) {

				if (oyunTahtasi[currentI][j] == 'X')
					winLook++;
				else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak)
					return true;

			}

			// sað üstten sol alta bakýyor
			winLook = 0;
			for (int i = currentI, j = currentJ; i >= 0 && j >= 0; i--, j--) {

				if (oyunTahtasi[i][j] == 'X') {
					winLook++;
				} else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak)
					return true;

			}
			if (oyunTahtasi[currentI][currentJ] == 'X') {
				winLook--;
			}
			for (int i = currentI, j = currentJ; i < N && j < N; i++, j++) {

				if (oyunTahtasi[i][j] == 'X') {
					winLook++;
				} else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak + 1)
					return true;
			}

			// sol üstten sað alta bakýyor
			winLook = 0;
			for (int i = currentI, j = currentJ; i >= 0 && j < N; i--, j++) {

				if (oyunTahtasi[i][j] == 'X') {
					winLook++;
				} else {
					winLook = 0;
				}
				// System.out.println("winlook "+winLook);
				if (winLook == kacTaneyeBak)
					return true;

			}
			if (oyunTahtasi[currentI][currentJ] == 'X') {
				winLook--;
			}
			for (int i = currentI, j = currentJ; i < N && j >= 0; i++, j--) {

				if (oyunTahtasi[i][j] == 'X') {
					winLook++;
				} else {
					winLook = 0;
				}
				// System.out.println("winlook "+winLook);
				if (winLook == kacTaneyeBak)
					return true;
			}
			return false;

		} else {

			// aþaðý doðru bakýyor
			winLook = 0;
			for (int i = 0; i < N; i++) {
				// System.out.println("winlook "+winLook);

				if (oyunTahtasi[i][currentJ] == 'O')
					winLook++;
				else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak)
					return true;
			}

			// saðdan sola doðru bakýyor
			winLook = 0;
			for (int j = 0; j < N; j++) {

				if (oyunTahtasi[currentI][j] == 'O')
					winLook++;
				else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak)
					return true;

			}

			// sað üstten sol alta bakýyor
			winLook = 0;
			for (int i = currentI, j = currentJ; i >= 0 && j >= 0; i--, j--) {

				if (oyunTahtasi[i][j] == 'O') {
					winLook++;
				} else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak)
					return true;

			}
			if (oyunTahtasi[currentI][currentJ] == 'O') {
				winLook--;
			}
			for (int i = currentI, j = currentJ; i < N && j < N; i++, j++) {

				if (oyunTahtasi[i][j] == 'O') {
					winLook++;
				} else {
					winLook = 0;
				}
				if (winLook == kacTaneyeBak + 1)
					return true;
			}

			// sol üstten sað alta bakýyor
			winLook = 0;
			for (int i = currentI, j = currentJ; i >= 0 && j < N; i--, j++) {

				if (oyunTahtasi[i][j] == 'O') {
					winLook++;
				} else {
					winLook = 0;
				}
				// System.out.println("winlook "+winLook);
				if (winLook == kacTaneyeBak)
					return true;

			}
			if (oyunTahtasi[currentI][currentJ] == 'O') {
				winLook--;
			}
			for (int i = currentI, j = currentJ; i < N && j >= 0; i++, j--) {

				if (oyunTahtasi[i][j] == 'X') {
					winLook++;
				} else {
					winLook = 0;
				}
				// System.out.println("winlook "+winLook);
				if (winLook == kacTaneyeBak)
					return true;
			}
			return false;

		}
	}

	public boolean beraberlikKontrol() {
		// Beraberliði kontrol eden method.
		return toplamHamleCount == Math.pow(N, 2);
	}

	private static int getToplamHamleCount(char[][] oTahtasi) {
		int count = 0;
		for (int i = 0; i < oTahtasi.length; i++) {
			for (int j = 0; j < oTahtasi[i].length; j++) {
				if (oTahtasi[i][j] != Character.MIN_VALUE) {
					count++;
				}
			}
		}
		return count;
	}

	public Oyuncu oyuncu1Al() {
		return oyuncu1;
	}

	public Oyuncu oyuncu2Al() {
		return oyuncu2;
	}

	public boolean oyunKaydet(Oyuncu o1, Oyuncu o2) {
		// Oyunu kaydeden method.
		try {
			File file = new File("src/oyunKayit.txt");// DÝKKATET!!!!!!
			if (!file.exists()) {
				file.createNewFile();
				dosyayiTemizle(file, "NOGAMES");
			}

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			if (!br.readLine().equals("NOGAMES")) {
				System.out.println("Zaten kayýtlý bir oyun var!");
				br.close();
				return false;
			}
			dosyayiTemizle(file, "");
			br.close();

			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			String yazýlacak = "";
			yazýlacak += N + "\n" + toplamHamleCount + "\n" + currentI + "\n" + currentJ + "\n";
			yazýlacak += o1.kullaniciAdiAl() + "\n" + o1.harf + "\n" + o1.insanmiKontrolu + "\n";
			yazýlacak += o2.kullaniciAdiAl() + "\n" + o2.harf + "\n" + o2.insanmiKontrolu + "\n";
			yazýlacak += "TAHTA\n";

			for (int i = 0; i < oyunTahtasi.length; i++) {
				for (int j = 0; j < oyunTahtasi[i].length; j++) {
					yazýlacak += oyunTahtasi[i][j] + " ";
				}
				yazýlacak = yazýlacak + "\n";
			}

			pw.write(yazýlacak);
			pw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Dosyaya yazarken hata!");
			return false;
		}
	}

	private static boolean dosyayiTemizle(File file, String str) {
		// Dosyayý tamaamen temizleyip parametre olarak aldýðý stringi yazan
		// method.
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print(str);
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("Dosyayý temizlerken hata");
			e.printStackTrace();// SONRADAN SÝL
			return false;
		}
	}

	public static Tahta kayitliOyunAl() {
		// Kayýtlý oyunu programa aktaran method.
		try {
			File file = new File("src/oyunKayit.txt");// DÝKKATET!!!!!!
			if (!file.exists()) {
				System.err.println("Kayýtlý oyun bulunamadý");
				return null;
			}

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String firstLine = br.readLine();
			if (firstLine.equals("NOGAMES")) {
				System.out.println("Kayýtlý oyun bulunamadý!");
				br.close();
				return null;
			}
			String line;
			ArrayList<String> Satirlar = new ArrayList<>();
			Satirlar.add(firstLine);
			while ((line = br.readLine()) != null) {
				Satirlar.add(line);
			}
			br.close();
			int n = Integer.parseInt(Satirlar.get(0));
			System.out.println(n);
			char[][] kaydedilenCharArray = new char[n][n];
			int index = 11;
			for (int i = 0; i < kaydedilenCharArray.length; i++) {
				String str = Satirlar.get(index);
				for (int j = 0; j < kaydedilenCharArray[i].length; j++) {
					char temp = str.charAt(2 * j);
					if (temp == ' ') {
						temp = Character.MIN_VALUE;
					}

					kaydedilenCharArray[i][j] = temp;
				}
				index++;
			}
			Tahta t = new Tahta(kaydedilenCharArray);
			t.toplamHamleCount = Integer.parseInt(Satirlar.get(1).trim());
			t.currentI = Integer.parseInt(Satirlar.get(2).trim());
			t.currentJ = Integer.parseInt(Satirlar.get(3).trim());
			Oyuncu o1 = new Oyuncu(Satirlar.get(4), Boolean.parseBoolean(Satirlar.get(6).trim()),
					Satirlar.get(5).charAt(0), t);
			Oyuncu o2 = new Oyuncu(Satirlar.get(7), Boolean.parseBoolean(Satirlar.get(9).trim()),
					Satirlar.get(8).charAt(0), t);

			t.oyuncuEkle(o1, o2);
			dosyayiTemizle(file, "NOGAMES");
			return t;
		} catch (Exception e) {
			System.err.println("Kayýtlý oyun alýnýrken hata");
			e.printStackTrace();
			return null;
		}
	}
}

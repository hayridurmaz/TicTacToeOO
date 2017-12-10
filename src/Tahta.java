import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Tahta {
	char[][] oyunTahtasi;
	// int[] kazananKontrolX, kazananKontrolO;
	int N, toplamHamleCount;
	int currentI, currentJ;

	public Tahta(int n) {
		oyunTahtasi = new char[n][n];
		toplamHamleCount = 0;
		// kazananKontrolX = new int[n + n + 2];
		// kazananKontrolO = new int[n + n + 2];
		N = n;
	}

	public Tahta(char[][] oTahtasi) {
		oyunTahtasi = oTahtasi;
		toplamHamleCount = getToplamHamleCount(oTahtasi);
		// kazananKontrolX = new int[oTahtasi.length + oTahtasi.length + 2];
		// kazananKontrolO = new int[oTahtasi.length + oTahtasi.length + 2];
		N = oTahtasi.length;
	}

	public char[][] oyunTahtasiAl() {
		return oyunTahtasi;
	}

	public void oyunTahtasiYazdir() {
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
		if (koordinat.length() != 2) {
			System.err.println("Yanl�� input girdiniz. Girdi �rne�i: \"A1\"");
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

			// win kontrol
			// if (oyuncu == 'X') {
			// xCount++;
			//// kazananKontrolX[i]++;
			//// kazananKontrolX[j + N]++;
			//// if (i == j) {
			//// kazananKontrolX[2 * N]++;
			//// }
			//// if (N - 1 - j == i) {
			//// kazananKontrolX[2 * N + 1]++;
			//// }
			// }
			// if (oyuncu == 'O') {
			// oCount++;
			//// kazananKontrolO[i]++;
			//// kazananKontrolO[j + N]++;
			//// if (i == j) {
			//// kazananKontrolO[2 * N]++;
			//// }
			//// if (N - 1 - j == i) {
			//// kazananKontrolO[2 * N + 1]++;
			//// }
			// }
			toplamHamleCount++;
			currentI = i;
			currentJ = j;
			oyunTahtasi[i][j] = oyuncu;
			return true;
		} catch (Exception e) {
			System.err.println("Yanl�� input girdiniz. Girdi �rne�i: \"A1\"");
			// System.err.println("Bir �eyler yanl�� gitti");
			// System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean kazanan(char oyuncu) {

		int kacTaneyeBak = 4;
		if (N == 3) {
			kacTaneyeBak = 3;
		}

		System.out.println("OYUNCU: " + oyuncu);
		int winLook;

		if (oyuncu == 'X') {

			// a�a�� do�ru bak�yor
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

			// sa�dan sola do�ru bak�yor
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

			// sa� �stten sol alta bak�yor
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

			// sol �stten sa� alta bak�yor
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

			// a�a�� do�ru bak�yor
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

			// sa�dan sola do�ru bak�yor
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

			// sa� �stten sol alta bak�yor
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

			// sol �stten sa� alta bak�yor
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

	public boolean oyunKaydet(Oyuncu o1, Oyuncu o2) {
		try {
			File file = new File("TicTacToeOO/oyunKayit.txt");// D�KKATET!!!!!!
			if (!file.exists()) {
				file.createNewFile();
			}

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			if (!br.readLine().equals("NOGAMES")) {
				System.out.println("Zaten kay�tl� bir oyun var!");
				br.close();
				return false;
			}
			br.close();

			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			String yaz�lacak = "";
			yaz�lacak += N + "\n" + toplamHamleCount + "\n" + currentI + "\n" + currentJ + "\n";
			yaz�lacak += o1.kullaniciAdiAl() + "\n" + o1.harf + "\n" + o1.insanmiKontrolu + "\n";
			yaz�lacak += o2.kullaniciAdiAl() + "\n" + o2.harf + "\n" + o2.insanmiKontrolu + "\n";
			yaz�lacak += "TAHTA\n";

			for (int i = 0; i < oyunTahtasi.length; i++) {
				for (int j = 0; j < oyunTahtasi[i].length; j++) {
					yaz�lacak += oyunTahtasi[i][j] + " ";
				}
				yaz�lacak = yaz�lacak + "\n";
			}

			pw.write(yaz�lacak);
			pw.close();
			return true;
		} catch (Exception e) {
			System.err.println("Dosyaya yazarken hata!");
			return false;
		}
	}

	public boolean kayitliOyunAl() {
		// TODO: Not implemented!
		return false;
	}
}

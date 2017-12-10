
public class Tahta {
	char[][] oyunTahtasi;
	int[] kazananKontrolX, kazananKontrolO;
	int N, xCount = 0, oCount = 0, toplamHamleCount = 0;
	int currentI, currentJ;

	public Tahta(int n) {
		oyunTahtasi = new char[n][n];
		kazananKontrolX = new int[n + n + 2];
		kazananKontrolO = new int[n + n + 2];
		N = n;
	}

	public Tahta(char[][] oTahtasi) {
		oyunTahtasi = oTahtasi;
		kazananKontrolX = new int[oTahtasi.length + oTahtasi.length + 2];
		kazananKontrolO = new int[oTahtasi.length + oTahtasi.length + 2];
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

			// win kontrol
			if (oyuncu == 'X') {
				xCount++;
				kazananKontrolX[i]++;
				kazananKontrolX[j + N]++;
				if (i == j) {
					kazananKontrolX[2 * N]++;
				}
				if (N - 1 - j == i) {
					kazananKontrolX[2 * N + 1]++;
				}
			}
			if (oyuncu == 'O') {
				oCount++;
				kazananKontrolO[i]++;
				kazananKontrolO[j + N]++;
				if (i == j) {
					kazananKontrolO[2 * N]++;
				}
				if (N - 1 - j == i) {
					kazananKontrolO[2 * N + 1]++;
				}
			}
			toplamHamleCount++;
			currentI = i;
			currentJ = j;
			oyunTahtasi[i][j] = oyuncu;
			return true;
		} catch (Exception e) {
			System.err.println("Yanlýþ input girdiniz. Girdi örneði: \"A1\"");
			// System.err.println("Bir þeyler yanlýþ gitti");
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
			// olmadý amkqqqqq

			// for (int i = 0; i < kazananKontrolX.length; i++) {
			// // System.err.println(i+":(X) "+kazananKontrolX[i]);
			// if (kazananKontrolX[i] >= N) {
			// return true;
			// }
			//
			// }
			// return false;

			// for (int xt = 0; xt < 5; xt++) {
			// int count = 0;
			// for (int i = xt, j = 0; i < 5 && j < 5; i++, j++) {
			// if (oyunTahtasi[i][j] == 'X') {
			// count++;
			// }
			// }
			// if (count >= 4) {
			// return true;
			// }
			// }
			//
			// for (int xt = 0; xt < 5; xt++) {
			// for (int i = 0, j = xt; i < 5 && j < 5; i++, j++) {
			// System.out.println(x + " - " + y);
			// }
			//
			// System.out.println("---");
			// }

			// System.out.println("currentI: "+currentI);
			// System.out.println("currentJ: "+currentJ);

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
			// if (oCount < N) {
			// return false;
			// } else {
			// for (int i = 0; i < kazananKontrolO.length; i++) {
			// // System.err.println(i+":(O) "+kazananKontrolO[i]);
			// if (kazananKontrolO[i] == N) {
			// return true;
			// }
			// }
			// return false;
			// }
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

	public void kazandimi() {

	}

	public boolean beraberlikKontrol() {
		return toplamHamleCount == Math.pow(N, 2);
	}
}

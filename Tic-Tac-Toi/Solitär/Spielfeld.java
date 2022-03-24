import java.util.Scanner;

public class Spielfeld {
	private char[][] feld = { { '#', '#', '#', '#', '#' }, { '#', '#', '#', '#', '#' }, { '#', '#', '#', '#', '#' },
			{ '#', '#', '-', '#', '#' }, { '#', '#', '#', '#', '#' } };

	public Spielfeld() {

	}

	public void ausgabe() {
		System.out.println("   0 1 2 3 4");
		for (int i = 0; i < this.feld.length; i++) {
			System.out.print(i + "| ");
			for (int j = 0; j < this.feld[0].length; j++) {
				System.out.print(feld[i][j] + " ");
			}
			System.out.println();

		}

	}

	public void start() {
		Scanner sc = new Scanner(System.in);
		do {
			this.ausgabe();
			System.out.println("Bitte Kordinat eingeben");
			System.out.println("Zeile: ");
			int x = sc.nextInt();
			System.out.println("Spalte: ");
			int y = sc.nextInt();
			System.out.println("Ziel: ");
			System.out.println("Zeile: ");
			int x1 = sc.nextInt();
			System.out.println("Spalte: ");
			int y1 = sc.nextInt();
			if (x >= 0 && x <= 4 && x1 >= 0 && x1 <= 4 && y >= 0 && y <= 4 && y1 >= 0 && y1 <= 4)
				zug(x, y, x1, y1);
			else
				System.out.println("falshe Kordinat");
		} while (!this.gewinn() || !this.verlust());

	}

	public void zug(int x, int y, int x1, int y1) {
		if (feld[x][y] == '#' && feld[x1][y1] == '-') {
			if (x - x1 == 2 || x1 - x == 2 || x - x1 == 0) {
				if (y - y1 == 2 || y1 - y == 2 || y == y1) {
					if (y == y1) {
						if (x > x1) {
							if (feld[x1 + 1][y1] == '#') {
								feld[x1][y1] = '#';
								feld[x1 + 1][y1] = '-';
								feld[x][y] = '-';
							} else {
								System.out.println("falsch");
							}
						} else {
							if (feld[x + 1][y] == '#') {
								feld[x][y] = '-';
								feld[x + 1][y] = '-';
								feld[x1][y1] = '#';
							} else {
								System.out.println("falsch");
							}
						}
					} else if (x == x1) {
						if (y > y1) {
							if (feld[x1][y1 + 1] == '#') {
								feld[x1][y1] = '#';
								feld[x1][y1 + 1] = '-';
								feld[x][y] = '-';
							} else {
								System.out.println("falsch");
							}
						} else {
							if (feld[x][y + 1] == '#') {
								feld[x][y] = '-';
								feld[x][y + 1] = '-';
								feld[x1][y1] = '#';
							} else {
								System.out.println("falsch");
							}
						}
					} else {
						System.out.println("das Ziel ist so weit");
					}
				} else {
					System.out.println("das Ziel ist so weit");
				}

			} else {
				System.out.println("das Ziel ist so weit");
			}
		} else {
			System.out.println("Ursprung soll (#) und Ziel soll (-)");
		}
	}

	public boolean gewinn() {
		boolean erg = false;
		int zaehler = 0;
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
				if (feld[i][j] == '#')
					zaehler++;
			}
		}
		if (zaehler == 1) {
			erg = true;
			System.out.println("Gewinn");}
		return erg;
	}

	public boolean verlust() {
		boolean erg = false;
		int zaehler = 0;
		for (int i = 1; i < feld.length-1; i++) {
			for (int j = 1; j < feld[0].length-1; j++) {
				if (feld[i][j] == '#') {
					if (feld[i - 1][j] == '-' && feld[i + 1][j] == '-' && feld[i][j - 1] == '-'
							&& feld[i][j + 1] == '-') {
						zaehler++;
					}
				}
			}
		}
		for (int i = 0; i < feld.length; i++) {
			if (feld[0][i] == '#') {
				if (i == 0) {
					if (feld[0][i + 1] == '-' && feld[i + 1][0] == '-')
						zaehler++;
				} else if (i == feld.length - 1) {
					if (feld[0][i - 1] == '-' && feld[1][i] == '-')
						zaehler++;
				} else {
					if (feld[0][i + 1] == '-' && feld[0][i - 1] == '-' && feld[1][i] == '-')
						zaehler++;
				}
				if (feld[feld.length - 1][i] == '#') {
					if (i == 0) {
						if (feld[feld.length - 1][i + 1] == '-' && feld[feld.length - 2][0] == '-')
							zaehler++;
					} else if (i == feld.length - 1) {
						if (feld[i][i - 1] == '-' && feld[i - 1][i] == '-')
							zaehler++;
					} else {
						if (feld[feld.length - 1][i + 1] == '-' && feld[feld.length - 1][i - 1] == '-'
								&& feld[feld.length - 2][i] == '-')
							zaehler++;
					}

				}
				if (i != 0 && i != feld.length - 1) {
					if (feld[i][0] == '#') {
						if (feld[i - 1][0] == '-' && feld[i + 1][0] == '-' && feld[i][1] == '-')
							zaehler++;
					}
				}
				if (i != 0 && i != feld.length - 1) {
					if (feld[feld.length - 1][i] == '#') {
						if (feld[feld.length - 1][i + 1] == '-' && feld[feld.length - 1][i - 1] == '-'
								&& feld[feld.length - 2][i] == '-')
							zaehler++;
					}
				}

			}
		}
		if (zaehler > 1) {
			erg = true;
			System.out.println("verlust");}
		return erg;
	}
}
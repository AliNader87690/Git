import java.util.Scanner;

public class Spielfeld {
	private int[][] feld;
	private Spieler[] spieler;

	public Spielfeld() {
		feld = new int[6][7];
		spieler = new Spieler[2];
	}

	public int getSpalteLength() {
		return feld[0].length;
	}

	public int getZeileLength() {
		return feld.length;
	}

	public void add(Spieler s) {
		for (int i = 0; i < spieler.length; i++) {
			if (spieler[i] == null)
				spieler[i] = s;
		}
	}

	public void zeigespieler() {
		for (int i = 0; i < spieler.length; i++) {
			System.out.println(spieler[i].getName());
		}
	}

	public void initialisiereSpielfeld() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
				feld[i][j] = 0;
			}
		}
	}

	public void druckeSpielfeld() {
		for (int i = 0; i < feld.length; i++) {
			System.out.print(i + 1 + "|");
			for (int j = 0; j < feld[0].length; j++) {
				System.out.print(" " + feld[i][j] + " |");
			}
			System.out.println();
		}
		System.out.println(" =============================");
		System.out.println("   1   2   3   4   5   6   7");
	}

	public void setzespielstein( int y, Spieler s) {
		for(int i=feld.length-1;i>=0;i--) {
			if(feld[i][y-1]==0) {
				feld[i][y-1]=s.getSpielstein();
				return;
			}
		}
	}

	public boolean testeSieg() {

		boolean erg = false;
		for (int i = 0; i < feld.length; i++) {
			int zaehler1 = 0;
			int zaehler2 = 0;

			for (int j = 0; j < feld[0].length; j++) {
				if (feld[i][j] == spieler[0].getSpielstein()) {
					zaehler1++;
					if (zaehler1 == 4) {
						System.out.println(spieler[0].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler1 = 0;
				if (feld[i][j] == spieler[1].getSpielstein()) {
					zaehler2++;
					if (zaehler2 == 4) {
						System.out.println(spieler[1].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler2 = 0;
			}
		}
		for (int i = 0; i < feld[0].length; i++) {
			int zaehler3 = 0;
			int zaehler4 = 0;
			for (int j = 0; j < feld.length; j++) {
				if (feld[j][i] == spieler[0].getSpielstein()) {
					zaehler3++;
					if (zaehler3 == 4) {
						System.out.println(spieler[0].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler3 = 0;
				if (feld[j][i] == spieler[1].getSpielstein()) {
					zaehler4++;
					if (zaehler4 == 4) {
						System.out.println(spieler[1].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler4 = 0;
			}
		}
		for (int i = 0; i < feld.length; i++) {
			int zaehler1 = 0;
			int zaehler2 = 0;
			int zaehler3 = 0;
			int zaehler4 = 0;
			int j = i;
			int a = 0;
			while (j < feld.length) {
				if (feld[j][a] == spieler[0].getSpielstein()) {
					zaehler1++;
					if (zaehler1 == 4) {
						System.out.println(spieler[0].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler1 = 0;
				if (feld[j][a] == spieler[1].getSpielstein()) {
					zaehler2++;
					if (zaehler2 == 4) {
						System.out.println(spieler[1].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler2 = 0;
				if (feld[a][j] == spieler[0].getSpielstein()) {
					zaehler3++;
					if (zaehler3 == 4) {
						System.out.println(spieler[0].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler3 = 0;
				if (feld[a][j] == spieler[1].getSpielstein()) {
					zaehler4++;
					if (zaehler4 == 4) {
						System.out.println(spieler[1].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler4 = 0;
				a++;
				j++;
			}
		}

		for (int i = feld.length - 1; i >= 0; i--) {
			int zaehler1 = 0;
			int zaehler2 = 0;
			int j = i;
			int a = 0;
			while (j >= 0) {
				if (feld[j][a] == spieler[0].getSpielstein()) {
					zaehler1++;
					if (zaehler1 == 4) {
						System.out.println(spieler[0].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler1 = 0;
				if (feld[j][a] == spieler[1].getSpielstein()) {
					zaehler2++;
					if (zaehler2 == 4) {
						System.out.println(spieler[1].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler2 = 0;

				a++;
				j--;
			}
		}
		for (int i = 0; i < feld.length; i++) {
			int zaehler3 = 0;
			int zaehler4 = 0;
			int b = feld.length - 1;
			int j = i;
			while (j < feld.length) {
				if (feld[j][b] == spieler[0].getSpielstein()) {
					zaehler3++;
					if (zaehler3 == 4) {
						System.out.println(spieler[0].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler3 = 0;
				if (feld[j][j] == spieler[1].getSpielstein()) {
					zaehler4++;
					if (zaehler4 == 4) {
						System.out.println(spieler[1].getName() + " hat gewonnen");
						return true;
					}
				} else
					zaehler4 = 0;
				j++;
				b--;
			}

		}
		return erg;
	}

	public boolean verlust() {
		boolean erg = false;
		int zaehler = 0;
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
				if (feld[i][j] == 0) {
					zaehler++;
				}
			}
		}
		if (zaehler == 0) {
			erg = true;
			System.out.println("Niemand hat gewonnen");
		}
		return erg;
	}
}

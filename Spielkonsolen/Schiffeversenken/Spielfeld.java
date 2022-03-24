import java.util.Random;
import java.util.Scanner;

public class Spielfeld {
	private char[][] feld = new char[10][10];
	private char[][] feld2 = new char[10][10];

	public Spielfeld() {

	}

	public void start() {
		Scanner sc = new Scanner(System.in);
		setShipPc();
		setGship();
		updatePc();
		printfieldes();
		/*setShip();
		setGship();
	    update();
	    printfieldes();*/
	}

	public void update() {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int x;
		int y;
		do {
			do {
				printGspielFeld();
				System.out.println("Shoot Punkt Reihe: ");
				char x1 = sc.next().charAt(0);
				x = x1 - 65;
				System.out.println("Shoot Punkt Spalte: ");
				int y1 = sc.nextInt();
				y = y1 - 1;
			} while (!shoot(x, y));
			printGspielFeld();
			do {
				x = r.nextInt(10);
				y = r.nextInt(10);
			} while (!Gshoot(x,y));
			printField();
		} while (!gewinn() || !Ggewinn());

	}
	public void updatePc() {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int x;
		int y;
		do {
			do {
				printGspielFeld();
				 x = r.nextInt(10);
				 y = r.nextInt(10);
			} while (!shootPc(x, y));
				if(gewinn()) {
					return;
				}
			printGspielFeld();
			do {
				x = r.nextInt(10);
				y = r.nextInt(10);
			} while (!Gshoot(x,y));
			printField();
		} while (!Ggewinn());
	}


	public void printField() {
		System.out.println("Spieler: ");
		System.out.println("  1 2 3 4 5 6 7 8 9 10 ");
		char a = 'A';
		for (int i = 0; i < feld.length; i++) {
			System.out.print(a++ + " ");
			for (int j = 0; j < feld.length; j++) {
				System.out.print(feld[i][j] + " ");

			}
			System.out.println();
		}
	}
	public void printSpielField() {
		System.out.println("Spieler: ");
		System.out.println("  1 2 3 4 5 6 7 8 9 10 ");
		char a = 'A';
		for (int i = 0; i < feld.length; i++) {
			System.out.print(a++ + " ");
			for (int j = 0; j < feld.length; j++) {
				if(feld[i][j]!='S') {
				System.out.print(feld[i][j] + " ");
				}
				else {
					System.out.print(' '+" ");
				}
			}
			System.out.println();
		}
	}
	public void printGspielFeld() {
		System.out.println("Gaegner: ");
		System.out.println("  1 2 3 4 5 6 7 8 9 10 ");
		char a = 'A';
		for (int i = 0; i < feld2.length; i++) {
			System.out.print(a++ + " ");
			for (int j = 0; j < feld2.length; j++) {
				if(feld2[i][j]!='S') {
				System.out.print(feld2[i][j] + " ");
				}
				else {
					System.out.print(' '+" ");
				}
			}
			System.out.println();
		}
	}
	public void printfieldes() {
		printField();
		System.out.println("Gaegner: ;");
		System.out.println("  1 2 3 4 5 6 7 8 9 10 ");
		char a = 'A';
		for (int i = 0; i < feld2.length; i++) {
			System.out.print(a++ + " ");
			for (int j = 0; j < feld2.length; j++) {
				System.out.print(feld2[i][j] + " ");

			}
			System.out.println();
		}
	}
	
	public void setGship() {
		Random r = new Random();
		int zaehler = 0;
		do {
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			int x1 = r.nextInt(9);
			int y1 = r.nextInt(9);
			if (setGUBoot(x, y, x1, y1)) 
				zaehler++;
				
		} while (zaehler < 3);
		zaehler = 0;
		do {
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			int x1 = r.nextInt(9);
			int y1 = r.nextInt(9);
			if (setGZer(x, y, x1, y1)) 
				zaehler++;
		} while (zaehler < 2);
		zaehler = 0;
		do {
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			int x1 = r.nextInt(9);
			int y1 = r.nextInt(9);
			if (setGKreuzer(x, y, x1, y1)) 
				zaehler++;
		} while (zaehler < 1);
		zaehler = 0;
		do {
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			int x1 = r.nextInt(9);
			int y1 = r.nextInt(9);
			if (setGSchlachtschiff(x, y, x1, y1)) 
				zaehler++;		
		} while (zaehler < 1);
	}

	public boolean gewinn() {
		boolean erg = true;
		for (int i = 0; i < feld2.length; i++) {
			for (int j = 0; j < feld2[0].length; j++) {
				if (feld2[i][j] == 'S') {
					return false;
				}
			}
		}
		System.out.println("Spieler hat gewonnen");
		return erg;
	}

	public boolean Ggewinn() {
		boolean erg = true;
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
				if (feld[i][j] == 'S') {
					return false;
				}
			}
		}
		System.out.println("Gaegner hat gewonnen");
		return erg;
	}

	public void setShip() {
		int zaehler = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			printField();
			System.out.println("Bitte U-Boot der Lange 2 setzen");
			System.out.println("Start Punkt Reihe: ");
			char x2 = sc.next().charAt(0);
			int x = x2 - 65;
			System.out.println("Start Punkt Spalte: ");
			int y3 = sc.nextInt();
			int y = y3 - 1;
			System.out.println("End Punkt Reihe: ");
			char x3 = sc.next().charAt(0);
			int x1 = x3 - 65;
			System.out.println("End Punkt Spalte: ");
			int y2 = sc.nextInt();
			int y1 = y2 - 1;
			if (this.setUBoot(x, y, x1, y1)) {
				zaehler++;
				
				}
		} while (zaehler < 3);
		zaehler = 0;
		do {
			printField();
			System.out.println("Bitte Zerstroer  der Lange 3 setzen");
			System.out.println("Start Punkt Reihe: ");
			char x2 = sc.next().charAt(0);
			int x = x2 - 65;
			System.out.println("Start Punkt Spalte: ");
			int y3 = sc.nextInt();
			int y = y3 - 1;
			System.out.println("End Punkt Reihe: ");
			char x3 = sc.next().charAt(0);
			int x1 = x3 - 65;
			System.out.println("End Punkt Spalte: ");
			int y2 = sc.nextInt();
			int y1 = y2 - 1;
			if (this.setZer(x, y, x1, y1)) {
				zaehler++;
			}
		} while (zaehler < 2);
		zaehler = 0;
		do {
			printField();
			System.out.println("Bitte Kreuzer  der Lange 4 setzen");
			System.out.println("Start Punkt Reihe: ");
			char x2 = sc.next().charAt(0);
			int x = x2 - 65;
			System.out.println("Start Punkt Spalte: ");
			int y3 = sc.nextInt();
			int y = y3 - 1;
			System.out.println("End Punkt Reihe: ");
			char x3 = sc.next().charAt(0);
			int x1 = x3 - 65;
			System.out.println("End Punkt Spalte: ");
			int y2 = sc.nextInt();
			int y1 = y2 - 1;
			if (this.setKreuzer(x, y, x1, y1)) {
				zaehler++;
			}
		} while (zaehler < 1);
		do {
			printField();
			System.out.println("Bitte Schlachtschiff  der Lange 5 setzen");
			System.out.println("Start Punkt Reihe: ");
			char x2 = sc.next().charAt(0);
			int x = x2 - 65;
			System.out.println("Start Punkt Spalte: ");
			int y3 = sc.nextInt();
			int y = y3 - 1;
			System.out.println("End Punkt Reihe: ");
			char x3 = sc.next().charAt(0);
			int x1 = x3 - 65;
			System.out.println("End Punkt Spalte: ");
			int y2 = sc.nextInt();
			int y1 = y2 - 1;
			if (this.setSchlachtschiff(x, y, x1, y1)) {
				zaehler++;
				printField();
			}
		} while (zaehler < 1);
	}

	public boolean setUBoot(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 1 || x1 - x == 1 || y - y1 == 1 || y1 - y == 1) {
				if (x >= 0 && x < feld.length && y >= 0 && y < feld.length && x1 >= 0 && x1 < feld.length && y1 >= 0
						&& y1 < feld.length) {
					if (feld[x][y] != 'S' && feld[x1][y1] != 'S') {
						feld[x][y] = 'S';
						feld[x1][y1] = 'S';
					}
				} else {
					System.out.println("Falsche eingabe");
					return false;
				}

			} else {
				System.out.println("lang soll 2");
				return false;
			}
		} else {
			System.out.println("Falsche eingabe");
			return false;
		}
		return erg;
	}

	public boolean setGUBoot(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 1 || x1 - x == 1 || y - y1 == 1 || y1 - y == 1) {
				if (x >= 0 && x < feld2.length && y >=0 && y < feld2.length && x1 >= 0 && x1 < feld2.length && y1 >= 0 && y1 < feld.length) {
						
					if (feld2[x][y] != 'S' && feld2[x1][y1] != 'S') {
						feld2[x][y] = 'S';
						feld2[x1][y1] = 'S';
					}
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}
		return erg;
	}

	public boolean setGZer(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 2 || x1 - x == 2 || y - y1 == 2 || y1 - y == 2) {
				if (x >= 0 && x < feld2.length && y >= 0 && y < feld2.length && x1 >= 0 && x1 < feld2.length && y1 >=0 && y1 < feld.length) {
						
					if (feld2[x][y] != 'S' && feld2[x1][y1] != 'S') {
						feld2[x][y] = 'S';
						feld2[x1][y1] = 'S';
						if (x == x1) {
							if (y > y1) {
								feld2[x][y1 + 1] = 'S';
							} else {
								feld2[x][y + 1] = 'S';
							}
						} else {
							if (x > x1) {
								feld2[x1 + 1][y] = 'S';
							} else {
								feld2[x + 1][y] = 'S';
							}
						}
					} else {
						return false;
					}

				} else {
					return false;
				}
			} else {
				return false;
			}

		}else{
			return false;}
		
		return erg;
	}

	public boolean setZer(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 2 || x1 - x == 2 || y - y1 == 2 || y1 - y == 2) {
				if (x >= 0 && x < feld.length && y >= 0 && y < feld.length && x1 >= 0 && x1 < feld.length && y1 >= 0 && y1 < feld.length) {
						
					if (feld[x][y] != 'S' && feld[x1][y1] != 'S') {
						feld[x][y] = 'S';
						feld[x1][y1] = 'S';
						if (x == x1) {
							if (y > y1) {
								feld[x][y1 + 1] = 'S';
							} else {
								feld[x][y + 1] = 'S';
							}
						} else {
							if (x > x1) {
								feld[x1 + 1][y] = 'S';
							} else {
								feld[x + 1][y] = 'S';
							}
						}
					} else {
						System.out.println("Falsche eingabe");
						return false;
					}

				} else {
					System.out.println("lang soll 3");
					return false;
				}
			} else {
				System.out.println("Falsche eingabe");
				return false;
			}

		}else {
			System.out.println("Falsche eingabe");
			return false;
		}
		return erg;
	}

	public boolean setKreuzer(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 3 || x1 - x == 3 || y - y1 == 3 || y1 - y == 3) {
				if (x >= 0 && x < feld.length && y >= 0 && y < feld.length && x1 >= 0 && x1 < feld.length && y1 >= 0
						&& y1 < feld.length) {

					if (feld[x][y] != 'S' && feld[x1][y1] != 'S') {
						feld[x][y] = 'S';
						feld[x1][y1] = 'S';
						if (x == x1) {
							if (y > y1) {
								feld[x][y1 + 1] = 'S';
								feld[x][y1 + 2] = 'S';
							} else {
								feld[x][y + 1] = 'S';
								feld[x][y + 2] = 'S';
							}
						} else {
							if (x > x1) {
								feld[x1 + 1][y] = 'S';
								feld[x1 + 2][y] = 'S';
							} else {
								feld[x + 1][y] = 'S';
								feld[x + 2][y] = 'S';
							}
						}
					} else {
						System.out.println("Falsche eingabe");
						return false;
					}

				} else {
					System.out.println("lang soll 4");
					return false;
				}
			} else {
				System.out.println("Falsche eingabe");
				return false;
			}

		}else {
			System.out.println("Falsche eingabe");
			return false;
		}
		return erg;
	}

	public boolean setGKreuzer(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 3 || x1 - x == 3 || y - y1 == 3 || y1 - y == 3) {
				if (x >= 0 && x < feld2.length && y >= 0 && y < feld2.length && x1 >= 0 && x1 < feld2.length && y1 >= 0
						&& y1 < feld2.length) {

					if (feld2[x][y] != 'S' && feld2[x1][y1] != 'S') {
						feld2[x][y] = 'S';
						feld2[x1][y1] = 'S';
						if (x == x1) {
							if (y > y1) {
								feld2[x][y1 + 1] = 'S';
								feld2[x][y1 + 2] = 'S';
							} else {
								feld2[x][y + 1] = 'S';
								feld2[x][y + 2] = 'S';
							}
						} else {
							if (x > x1) {
								feld2[x1 + 1][y] = 'S';
								feld2[x1 + 2][y] = 'S';
							} else {
								feld2[x + 1][y] = 'S';
								feld2[x + 2][y] = 'S';
							}
						}
					} else {
						return false;
					}

				} else {
					return false;
				}
			} else {
				return false;
			}

		}else {
			return false;
		}
		return erg;
	}

	public boolean setSchlachtschiff(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 4 || x1 - x == 4 || y - y1 == 4 || y1 - y == 4) {
				if (x >= 0 && x < feld.length && y >= 0 && y < feld.length && x1 >= 0 && x1 < feld.length && y1 >= 0 && y1 < feld.length) {
						

					if (feld[x][y] != 'S' && feld[x1][y1] != 'S') {
						feld[x][y] = 'S';
						feld[x1][y1] = 'S';
						if (x == x1) {
							if (y > y1) {
								feld[x][y1 + 1] = 'S';
								feld[x][y1 + 2] = 'S';
								feld[x][y1 + 3] = 'S';
							} else {
								feld[x][y + 1] = 'S';
								feld[x][y + 2] = 'S';
								feld[x][y + 3] = 'S';
							}
						} else {
							if (x > x1) {
								feld[x1 + 1][y] = 'S';
								feld[x1 + 2][y] = 'S';
								feld[x1 + 3][y] = 'S';
							} else {
								feld[x + 1][y] = 'S';
								feld[x + 2][y] = 'S';
								feld[x + 3][y] = 'S';
							}
						}
					} else {
						System.out.println("Falsche eingabe");
						return false;
					}

				} else {
					System.out.println("lang soll 5");
					return false;
				}
			} else {
				System.out.println("Falsche eingabe");
				return false;
			}

		}else {
			System.out.println("Falsche eingabe");
			return false;
		}
		return erg;
	}

	public boolean setGSchlachtschiff(int x, int y, int x1, int y1) {
		boolean erg = true;
		if (x == x1 || y == y1) {
			if (x - x1 == 4 || x1 - x == 4 || y - y1 == 4 || y1 - y == 4) {
				if (x >= 0 && x < feld2.length && y >= 0 && y < feld2.length && x1 >=0 && x1 < feld2.length && y1 >= 0
						&& y1 < feld2.length) {

					if (feld2[x][y] != 'S' && feld2[x1][y1] != 'S') {
						feld2[x][y] = 'S';
						feld2[x1][y1] = 'S';
						if (x == x1) {
							if (y > y1) {
								feld2[x][y1 + 1] = 'S';
								feld2[x][y1 + 2] = 'S';
								feld2[x][y1 + 3] = 'S';
							} else {
								feld2[x][y + 1] = 'S';
								feld2[x][y + 2] = 'S';
								feld2[x][y1 + 3] = 'S';
							}
						} else {
							if (x > x1) {
								feld2[x1 + 1][y] = 'S';
								feld2[x1 + 2][y] = 'S';
								feld2[x1 + 3][y] = 'S';
							} else {
								feld2[x + 1][y] = 'S';
								feld2[x + 2][y] = 'S';
								feld2[x + 3][y] = 'S';
							}
						}
					} else {
						return false;
					}

				} else {
					return false;
				}
			} else {
				return false;
			}

		}else {
			return false;
		}
		return erg;
	}

	public boolean shoot(int x, int y) {
		boolean erg = true;

		if (x >= 0 && x < 10 && y >= 0 && y < 10) {
			if (feld2[x][y] != 'O' && feld2[x][y] != '*') {
				if (feld2[x][y] == 'S') {
					feld2[x][y] = '*';
				} else {
					feld2[x][y] = 'O';
					
				}
			} else {
				System.out.println("Falsche eingabe");
				return false;
			}
		} else {
			System.out.println("Falsche eingabe");
			return false;
		}

		return erg;
	}

	public boolean Gshoot(int x, int y) {
		boolean erg = true;

		if (x >= 0 && x < 10 && y >= 0 && y < 10) {
			if (feld[x][y] != 'O' && feld[x][y] != '*') {
				if (feld[x][y] == 'S') {
					feld[x][y] = '*';
				} else {
					feld[x][y] = 'O';
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

		return erg;
	}
	public boolean shootPc(int x,int y) {
		boolean erg = true;

		if (x >= 0 && x < 10 && y >= 0 && y < 10) {
			if (feld2[x][y] != 'O' && feld2[x][y] != '*') {
				if (feld2[x][y] == 'S') {
					feld2[x][y] = '*';
				} else {
					feld2[x][y] = 'O';
					
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

		return erg;
	}
	public void setShipPc() {
		int zaehler = 0;
		Scanner sc = new Scanner(System.in);
		Random r=new Random();
		
		do {
			printField();
			int x = r.nextInt(10);
			int y = r.nextInt(10);

			int x1 =r.nextInt(10);
			int y1 = r.nextInt(10);
			if (this.setUBoot(x, y, x1, y1)) {
				zaehler++;
				
				}
		} while (zaehler < 3);
		zaehler = 0;
		do {
			printField();;
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			int x1 = r.nextInt(10);
			int y1 = r.nextInt(10);
			if (this.setZer(x, y, x1, y1)) {
				zaehler++;
			}
		} while (zaehler < 2);
		zaehler = 0;
		do {
			printField();
			int x = r.nextInt(10);
			int y = r.nextInt(10);;
			int x1 = r.nextInt(10);
			int y1 = r.nextInt(10);
			if (this.setKreuzer(x, y, x1, y1)) {
				zaehler++;
			}
		} while (zaehler < 1);
		do {
			printField();
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			int x1 = r.nextInt(10);
			int y1 = r.nextInt(10);
			if (this.setSchlachtschiff(x, y, x1, y1)) {
				zaehler++;
				printField();
			}
		} while (zaehler < 1);
	}
}
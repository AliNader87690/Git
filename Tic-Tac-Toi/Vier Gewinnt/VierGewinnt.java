import java.util.Scanner;

public class VierGewinnt {

	public static void main(String[] args) {

		Spielfeld s = new Spielfeld();
		Spieler p1 = new Spieler("");
		Spieler p2 = new Spieler("");
		s.add(p1);
		s.add(p2);
		s.initialisiereSpielfeld();
		start(p1, p2, s);
		

	}

	public static void start(Spieler p1, Spieler p2, Spielfeld s) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Der erste Spieler:");
		String n = sc.next();
		p1.setName(n);
		System.out.println();
		System.out.println("Der zweite Spieler");
		String m = sc.next();
		p2.setName(m);
		System.out.println();
		int y;
		int y1;
		do {
			do {
				s.druckeSpielfeld();
				System.out.println("Spieler: " + p1.getName());
				System.out.println("Bitte Kordinat eingeben");
				System.out.println("Spalte: ");
				y = sc.nextInt();
				if (y > 0 && y <= s.getSpalteLength()) {
					s.setzespielstein( y, p1);
					if (s.testeSieg() || s.verlust())
						return;
				} else
					System.out.println("falsche Kordinat");

			} while (!( y > 0 && y <= s.getSpalteLength()));
			do {
				s.druckeSpielfeld();
				System.out.println("Spieler: " + p2.getName());
				System.out.println("Bitte Kordinat eingeben");
				System.out.println("Spalte: ");
				y1 = sc.nextInt();
				if ( y1 > 0 && y1 <= s.getSpalteLength()) {
					s.setzespielstein( y1, p2);
					if (s.testeSieg() || s.verlust())
						return;
				} else
					System.out.println("falsche Kordinat");
			} while (!( y1 > 0 && y1 <= s.getSpalteLength()));
		} while (true);
	}
}

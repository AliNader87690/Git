
public class Main {

	public static void main(String[] args) {
		Spielfeld s=new Spielfeld();
		Spieler s1= new Spieler("A");
		Spieler s2=new Spieler("B");
		s.initialisiereSpielfeld();
		s.addSpieler(s1);
		s.addSpieler(s2);
		s.start();

	}

}

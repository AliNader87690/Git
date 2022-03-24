
public class Spieler {
private String name;
private static int spielernummer=1;
private  int spielstein=spielernummer;
public void setName(String name) {
	this.name = name;
}

public Spieler(String name) {
	this.name=name;
	spielernummer++;
}
public String getName() {
	return name;
}
public static int getSpielernummer() {
	return spielernummer;
}
public int getSpielstein() {
	return this.spielstein;
}

}

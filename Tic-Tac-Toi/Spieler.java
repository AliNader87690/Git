
public class Spieler {
	private String name;
private int[]feld= {0,0,1,1,2,2};
public Spieler(String name) {
	this.name=name;
}
public String getName() {
	return name;
}
public int getSpielstein1(int n) {
	
for(int i=0;i<feld.length;i++) {
	if(feld[i]==n) {
		feld[i]=-1;
		return n;
	}
}
return -1;
}

}

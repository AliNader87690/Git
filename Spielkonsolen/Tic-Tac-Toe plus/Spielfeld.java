import java.util.Scanner;

public class Spielfeld {
private String [][]feld;
private int [][]feld2;
private Spieler[]spieler;
public Spielfeld() {
	feld=new String[3][3];
	feld2=new int[3][3];
	spieler=new Spieler[2];
}
public Spielfeld(int n) {
	if((n%2==n))
		n+=1;
	feld=new String[n][n];
	spieler=new Spieler[2];
}
public void initialisiereSpielfeld() {
	for (int i = 0; i < feld.length; i++) {
		for (int j = 0; j < feld[0].length; j++) {
			feld[i][j] = " ";
		}
	}
		for (int i = 0; i < feld2.length; i++) {
			for (int j = 0; j < feld2[0].length; j++) {
				feld2[i][j] = 0;
			}
		}
}

public void ausgabe() {
	
	System.out.println("   1  2  3 ");
	for(int i=0;i<feld.length;i++) {
		System.out.print(i+1+"|");
		for(int j=0;j<feld[0].length;j++) {
			System.out.print(feld[i][j]+" |");
		}
		System.out.println();
		System.out.println();
	}
}
public void addSpieler(Spieler s) {
	for(int i=0;i<spieler.length;i++) {
		if(spieler[i]==null)
			spieler[i]=s;
	}
}
public void start() {
	Scanner sc= new Scanner(System.in);
	int x;
	int y;
	Spieler s1=spieler[0];
	Spieler s2=spieler[1];
	do {
	do {
		ausgabe();
		System.out.println("Spieler: 1 (X)");
		System.out.println("Bitte Kordinat eingeben");
		System.out.println("Zeile: ");
		int x1=sc.nextInt();
		x=x1-1;
		System.out.println("Spalte: ");
		int y1=sc.nextInt();
		y=y1-1;
	}while(!setzeSpielstein1(x,y,s1));
	if(testeSieg()) {
		return;
	}
	do {
		ausgabe();
		System.out.println("Spieler: 2 (O)");
		System.out.println("Bitte Kordinat eingeben");
		System.out.println("Zeile: ");
		int x1=sc.nextInt();
		x=x1-1;
		System.out.println("Spalte: ");
		int y1=sc.nextInt();
		y=y1-1;
	}while(!setzeSpielstein2(x,y,s2));
	if(testeSieg()) {
		return;
	}
	}while(!verlust());
}
public boolean setzeSpielstein1(int x,int y,Spieler s) {
	boolean erg=false;
	String stein = null;
	int wahl=0;
	Scanner sc= new Scanner(System.in);
	if(x<0 || x>=feld.length || y<0 || y>=feld[0].length) {
		System.out.println("Falsche Kordinat");
		return false;
	}else {
		do {
	System.out.println("Bitte Spielstein zw'0'und'2'");
	 wahl=sc.nextInt();
	 int w=s.getSpielstein1(wahl);
	if(wahl==0 && w==0) {
		stein="X0";
	}else if(wahl==1 && w==1 ) {
		stein="X1";
	}else if(wahl==2 && w==2) {
		stein="X2";
	}else System.out.println("Falsche stein");	
	}while(stein==null);
	if(feld2[x][y]== 0) {
		feld[x][y]=stein;
		feld2[x][y]=1;
		return true;
	}else if(feld2[x][y]==10 && wahl>0){
		feld[x][y]=stein;
		feld2[x][y]=2;
		return true;
	}else if(feld2[x][y]==11 && wahl>1) {
		feld[x][y]=stein;
		feld2[x][y]=3;
		return true;
	}else {
		System.out.println("Falshe eingabe");
		
	}
	}
	return erg;
	
}
public boolean setzeSpielstein2(int x,int y,Spieler s) {
	boolean erg=false;
	String stein = null;
	int wahl=0;
	Scanner sc= new Scanner(System.in);
	if(x<0 || x>=feld.length || y<0 || y>=feld[0].length) {
		System.out.println("Falsche Kordinat");
		return false;
	}else {
		do {
	System.out.println("Bitte Spielstein zw'0'und'2'");
	 wahl=sc.nextInt();
	 int w=s.getSpielstein1(wahl);
	if(wahl==0 && w==0) {
		stein="O0";
	}else if(wahl==1 && w==1 ) {
		stein="O1";
	}else if(wahl==2 && w==2) {
		stein="O2";
	}else System.out.println("Falsche stein");	
	}while(stein==null);
	if(feld2[x][y]== 0) {
		feld[x][y]=stein;
		feld2[x][y]=10;
		return true;
	}else if(feld2[x][y]==1 && wahl>0){
		feld[x][y]=stein;
		feld2[x][y]=11;
		return true;
	}else if(feld2[x][y]==2 && wahl>1) {
		feld[x][y]=stein;
		feld2[x][y]=12;
		return true;
	}else {
		System.out.println("Falshe eingabe");
		
	}
	}
	return erg;
	
}
public boolean testeSieg() {

	boolean erg = false;
	for (int i = 0; i < feld.length; i++) {
		int zaehler1 = 0;
		int zaehler2 = 0;

		for (int j = 0; j < feld[0].length; j++) {
			if (feld[i][j] == "X0" || feld[i][j] == "X1" || feld[i][j] == "X2") {
				zaehler1++;
				if (zaehler1 == 3) {
					System.out.println(spieler[0].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler1 = 0;
			if (feld[i][j] == "O0"|| feld[i][j] == "O1" || feld[i][j] == "O2") {
				zaehler2++;
				if (zaehler2 == 3) {
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
			if (feld[j][i] == "X0" || feld[j][i] == "X1" || feld[j][i] == "X2" ) {
				zaehler3++;
				if (zaehler3 == 3) {
					System.out.println(spieler[0].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler3 = 0;
			if (feld[j][i] =="O0" || feld[j][i] =="O1" || feld[j][i] =="O2") {
				zaehler4++;
				if (zaehler4 == 3) {
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
			if (feld[j][a] =="X0" || feld[j][a] =="X1" || feld[j][a] =="X2") {
				zaehler1++;
				if (zaehler1 == 3) {
					System.out.println(spieler[0].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler1 = 0;
			if (feld[j][a] =="O0" || feld[j][a] =="O1" || feld[j][a] =="O2") {
				zaehler2++;
				if (zaehler2 == 3) {
					System.out.println(spieler[1].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler2 = 0;
			if (feld[a][j] == "X0" || feld[a][j] == "X1" || feld[a][j] == "X2") {
				zaehler3++;
				if (zaehler3 == 3) {
					System.out.println(spieler[0].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler3 = 0;
			if (feld[a][j] =="O0" || feld[a][j] =="O1" || feld[a][j] =="O2"  ) {
				zaehler4++;
				if (zaehler4 == 3) {
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
			if (feld[j][a] =="X0" || feld[j][a] =="X1" || feld[j][a] =="X2") {
				zaehler1++;
				if (zaehler1 == 3) {
					System.out.println(spieler[0].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler1 = 0;
			if (feld[j][a] =="O0" || feld[j][a] =="O1" || feld[j][a] =="O2") {
				zaehler2++;
				if (zaehler2 == 3) {
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
			if (feld[j][b] == "X0" || feld[j][b] == "X1" || feld[j][b] == "X2") {
				zaehler3++;
				if (zaehler3 == 3) {
					System.out.println(spieler[0].getName() + " hat gewonnen");
					return true;
				}
			} else
				zaehler3 = 0;
			if (feld[j][j] =="O0" || feld[j][j] =="O1" || feld[j][j] =="O2") {
				zaehler4++;
				if (zaehler4 == 3) {
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
	for (int i = 0; i < feld2.length; i++) {
		for (int j = 0; j < feld2[0].length; j++) {
			if (feld2[i][j] == 0) {
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

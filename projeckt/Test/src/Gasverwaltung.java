/**@author Moamen Alkatib 7206217
 * @author Ali Nader 7208457
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Gasverwaltung
 *
 */
public class Gasverwaltung {
	private static final String url = "jdbc:oracle:thin:@172.22.112.100:1521:fbpool";
	private static final String user = "C##FBPOOL07";
	private static final String password = "oracle";
	/**
	 * <p>die Methode gibt zuerst alle bereits existieren Tabellen nummeriert aus und erwartet eine 
	 * {@code int} Eingabe, die eine Tabellennummer repräsentiert und für die Selektierung der Tabelle verwendet wird. Danach 
	 *  wird einen bestimmten Ausschnitt (nur sinnvolle Informationen) der Tabelle formatiert angezeigt.
	 *  </p>
	 */
	public static void showTabelle() {
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Scanner sc = new Scanner(System.in);
			String eingabe = "";
			boolean temp = true;
			do {
				System.out.printf(
						"wählen Sie eine der folgenden Tabellen:\n(1)Firma\n(2)Anbieter\n(3)Tarif\n(4)Buchung\n(5)Verbraucher\n(6)Zähler\n(7)Adresse\n");
				System.out.print("Eingabe:");
				eingabe = sc.next();
				if (eingabe.compareTo("1") == 0 || eingabe.compareTo("2") == 0 || eingabe.compareTo("3") == 0
						|| eingabe.compareTo("4") == 0 || eingabe.compareTo("5") == 0 || eingabe.compareTo("6") == 0
						|| eingabe.compareTo("7") == 0)
					temp = false;
				else
					System.out.println("Bitte nur Zahlen zwischen 1-7 eingeben!");
			} while (temp);
			String sqlString = "";
			switch (Integer.parseInt(eingabe)) {
			case 1:
				sqlString = "Select Firmaname,ort,telefonnummer,emailadresse from firma";
				break;
			case 2:
				sqlString = "select firmaname, typ as TarifTyp from (anbieter join firma using(firmaid)) join tarif using(tarifid)";
				break;
			case 3:
				sqlString = "select  typ,arbeitspreis,laufzeit,verlaengerung,KUENDIGUNGSFRIST,preisgarantie,abschlagzahlung,neukundebonus from tarif ";
				break;
			case 4:
				sqlString = "select anrede,vorname,nachname,geburtsdatum,verbraucher.telefonnummer,typ,firmaname from ((((((zaehler join adresse using(aid)) join Buchung using(buchungid)) join verbraucher using(verbraucherid)) join Anbieter using(Anbieterid)) join Tarif using(tarifid)) join Firma using(firmaid))\r\n";
				break;
			case 5:
				sqlString = "select anrede,vorname,nachname,geburtsdatum,telefonnummer from verbraucher";
				break;
			case 6:
				sqlString = "select anrede,vorname,nachname,ZAEHLERID as Zaehlernummer,ZUSTAND,STADT,PLZ,STRASSE,HAUSNUMMER,WOHNFLAECHE,firmaname,typ from ((((((zaehler join adresse using(aid))join buchung using(buchungid))"
						+ " join verbraucher using(verbraucherid)) join anbieter using(anbieterid)) join tarif using(tarifid)) join firma using(firmaid))";
				break;
			default:
				sqlString = "select stadt,strasse,plz,hausnummer from adresse";
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			int i = 1;
			int attributeanzahl = rs.getMetaData().getColumnCount();
			while (i <= attributeanzahl) {
				System.out.printf("%-20s\t", rs.getMetaData().getColumnName(i));
				i++;
			}
			System.out.println();
			while (rs.next()) {
				for (i = 1; i <= attributeanzahl; i++) {
					Object o = rs.getObject(i);
					if (o instanceof java.sql.Timestamp) {
						java.sql.Timestamp td = (java.sql.Timestamp) o;
						int day = td.getDate();
						int mounth = td.getMonth() + 1;
						int year = td.getYear() + 1900;
						String gdatum = year + "." + mounth + "." + day;
						System.out.printf("%-20s\t", gdatum);
					} else
						System.out.printf("%-20s\t", o);
				}
				System.out.println();
			}
		} catch (Exception e) {
			if (e instanceof SQLException)
				System.out.println("ungültige Eingabe");
			else
				System.out.println("Fehlermeldung");
		}
	}
	/**
	 * <p>Die Methode wird für das einfuegen eines neuen Tupels zu einer bestimmten Tabelle verwendet.
	 * @param tabellename :Name der Tabelle
	 * @param werte :Werte des Tupel als {@code String}<br>
	 * <strong>Note: </strong>die Eingabe muss dem Muster entsprechen {@code "value,value,..,..,usw"} wobei {@code String} in ' ' gesetzt werden muss
	 * @return entweder 1 beim Erfolg oder 0 bei Misserfolg.
	 */
	public static int tupelEinfuegen(String tabellename, String werte) {
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String sqlString = "insert into " + tabellename + " Values( " + werte + " )";
			Statement stmt = con.createStatement();
			int anzahl = stmt.executeUpdate(sqlString);
			stmt.execute("commit");
			return anzahl;
		} catch (Exception e) {
			if (e instanceof SQLException) {
				System.out.println("ungültige Eingabe");
				return 0;
			}
			else
				return 0;
		}
	}
	/**
	 * <p>Die Methode wird für das ändern des Inhalt von bereits existieren Tupel verwendet<p>
	 * @param tabellename :Name der Tabelle
	 * @param werte : Hier werden die neuen werte eingegeben. Die Eingabe muss dem Muster entsprechen "Attributname=wert,Attributname=wert,use."<br><strong>Note: </strong>{@code String} muss in '' gesetzt werden'   
	 * @param Condition :hier ist es zu bestimmen welche Tupel zu ändern sind.Die Eingabe muss dem Muster entsprechen "Attributname=wert,Attributname=wert,usw"<br><strong>Note:</strong>{@code String} muss in '' gesetzt werden '
	 * @return 0 bei Misserfolg oder die {@code Anzahl} der geänderten Tupel
	 */
	
	public static int tupelAendern(String tabellename,String werte,String Condition) {
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			Scanner sc = new Scanner(System.in);
			String sqlString = "Update " + tabellename + " set " + werte + " where " + Condition;
			Statement stmt = con.createStatement();
			int anzahl = stmt.executeUpdate(sqlString);
			stmt.execute("commit");
			return anzahl;
		} catch (Exception e) {
			if (e instanceof SQLException) {
				System.out.println("ungültige Eingabe");
				return 0;
			}
			else
				return 0;
		}
	}
	/**
	 * <p>die Methode erlaubt es einen oder mehrere Datensätze zu löschen</p>
	 * @param tabellename :Name der Tablle
	 * @param Condition :hier wird die Auswahlbedignung zum selektieren von Tupel eingegeben. 
	 * die Eingabe muss dem Muster entsprechen "Attributname=wert,Attributname=wert,usw"
	 * <br><strong>Note:</strong> {@code String} muss in '' gesetzt werden.  
	 * @return 0 beim Misserfolg oder die {@code Anzahl} der erfolgreich gelöschten Tupel
	 */
	public static int tupelLoeschen(String tabellename,String Condition) {
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			Scanner sc = new Scanner(System.in);
			String sqlString = "Delete from " + tabellename + " where " + Condition;
			Statement stmt = con.createStatement();
			int anzahl = stmt.executeUpdate(sqlString);
			stmt.execute("commit");
			return anzahl;
		} catch (Exception e) {
			if (e instanceof SQLException) {
				System.out.println("ungültige Eingabe");
				return 0;
			}
			else
				return 0;
		}
	}
	/**
	 * 	<p>Die Methode Zeigt zuerst alle übergeordneten Tarife an und erfordert dann eine {@code String} Eingabe.Die Eingabe muss mit einer von der Übergeordneten Tarife oder mit dem Prozent(%) Zeichen übereinstimmen  
	 *  im nächsten Schritt wird die Anzahl der untergordeneten Tarife gemäß der Eingabe auf der Konsole ausgegeben
	 */
	public static void unterTarif() {
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			String sql = "select typ " + " from tarif" + " where tarif.parentid is null";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Die Übergeordnete Tarife sind:");
			while (rs.next()) {
				String s=rs.getString(1);
				System.out.printf("%s\n", s);
			}
			System.out.println();
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Wählen Sie von Welchen Tarif sollen die Anzahl der untergeordnete Tarife berechnet. wenn Sie die alle selektieren wollen dann geben Sie % ein ");
			String typ = sc.next();
			String sqlString = "select count(b.parentid)" + " from tarif a join tarif b on a.tarifid=b.parentid"
					+ " where a.typ like '" + typ + "' and b.parentid is not null";
			ResultSet rs2 = stmt.executeQuery(sqlString);
			if (rs2.next())
				System.out.println("Die Anzahl ist=" + rs2.getInt(1));
		} catch (Exception e) {
			if (e instanceof SQLException)
				System.out.println("ungültige Eingabe");
			else
				System.out.println("Fehlermeldung");
		}
	  }
}

package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import modul.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static controller.Haupklasse.c;
import static controller.Haupklasse.em;

public class Controller {


    public int hauptMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gasversorgung \n\nBitte Waehlen Sie eine Aktion: \n" +
                "Clientseitig \n---------- \n" +
                "1. Vertrag abschliessen \n2. Vertrag kuendigen \n3. Vertrag bearbeiten \n4. Angebote anzeigen\n\n" +
                "Systemseite \n-----------" +
                " \n5. Tabelle Angebot \n6. Tabelle Adresse " +
                "\n7. Tabelle Anbieter \n8. Tabelle Geschaeftskunde \n9. Tabelle Privatkunde " +
                "\n10. Tabelle Tarif \n11. Tabelle Vertrag \n12. Tabelle Zaehler \n13. Tabelle Anbieter_Telefon anzeigen \n14. Abbrechen");
        return scanner.nextInt();
    }

    public void vertragAbschliessen(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Vertrag vertrag;
        int kundeEingabe;
        Long kundennumemr;
        int adresseEingabe;
        Long zaehlerID;
        String eingabe2 = null;
        Privatkunde privatkunde = null;
        Geschaeftskunde geschaeftskunde = null;
        Adresse adresse = null;
        Zaehler zaehler = null;
        Long adresseID;
        Angebot angebot;

        System.out.println("Vertrag Abschliessen \n----------------");
        System.out.println("Sind Sie Neukunde?\n1. Ja\n2. Nein");
        kundeEingabe = scanner.nextInt();
        if(kundeEingabe == 1) {
            System.out.println("Sind Sie ein: \n 1. Privatkunde \n 2. Geschaeftskunde");
            kundeEingabe = scanner.nextInt();
            if(kundeEingabe == 1) {
                privatkunde = createPrivatkunde(scanner);
            } else if(kundeEingabe == 2) {
                geschaeftskunde = createGeschaeftskunde(scanner);
            }
        } else {
            System.out.println("Sind Sie ein: \n 1. Privatkunde \n 2. Geschaeftskunde");
            kundeEingabe = scanner.nextInt();
            if(kundeEingabe == 1) {
               privatkundeAnzeigen(em);
                System.out.println("Kundennummer eingeben:");
               kundennumemr = scanner.nextLong();
               privatkunde = em.find(Privatkunde.class, kundennumemr);
               if(privatkunde == null) {
                   return;
               }
            } else if(kundeEingabe == 2) {
                geschaeftskundeAnzeigen(em);
                System.out.println("Kundennummer eingeben:");
                kundennumemr = scanner.nextLong();
                geschaeftskunde = em.find(Geschaeftskunde.class, kundennumemr);
                if(geschaeftskunde == null) {
                    return;
                }
            }
        }
        angebot = angebotList(em);
        System.out.println("Adresse vorhanden?");
        System.out.println("1. Ja\n2. Nein");
        adresseEingabe = scanner.nextInt();
        if(adresseEingabe == 1) {
            adressenAnzeigen(em);
            System.out.println("AdresseID eingeben:");
            adresseID = scanner.nextLong();
            adresse = em.find(Adresse.class, adresseID);
            if(adresse == null) {
                return;
            }
            int count = 0;
            for (Zaehler z : adresse.getZaehlerList()) {
                if(z.getVertrag() == null) {
                    count++;
                }
            }
            if(count > 0) {
                for (Zaehler z : adresse.getZaehlerList()) {
                    if (z.getVertrag() == null) {
                        System.out.println(z);
                    }
                }
                System.out.println("Zaehlernummer eingeben:");
                zaehlerID = scanner.nextLong();
                Zaehler_AdressePK pk = new Zaehler_AdressePK(adresseID);
                pk.setZaehlernummer(zaehlerID);
                zaehler = em.find(Zaehler.class, pk);
                if (zaehler == null) {
                    return;
                }
            } else {
                System.out.println("Diese Adresse hat keinen freien Zaehler         Bitte neuen Zaehler erstellen...");
                zaehler = createZaehler(scanner);
            }
                System.out.println("Vertrag-Informationen eingeben: \n---------------");
                vertrag = createVertrag(scanner);

                //Beziehungen: Kunde_Zaehler, Vertrag_Angebot herstellen
                if(kundeEingabe == 1) {
                    privatkunde.addZaehler(zaehler);
                    vertrag.addAngebot(angebot);
                    zaehler.addVertrag(vertrag);
                } else if(kundeEingabe == 2) {
                    geschaeftskunde.addZaehler(zaehler);
                    vertrag.addAngebot(angebot);
                    zaehler.addVertrag(vertrag);
                }
                em.getTransaction().begin();
                if (privatkunde != null) {
                    em.persist(privatkunde);

                }else {
                    em.persist(geschaeftskunde);
                }
                em.persist(zaehler);
                em.persist(vertrag);
                em.getTransaction().commit();

        } else {
                adresse = createAdresse(scanner);
                zaehler = createZaehler(scanner);
                zaehler.setPrimaryKey(adresse.getAdresseID());
                //Beziehung Adresse_Zaehler herstellen
                 adresse.addZaehler(zaehler);

                 System.out.println("Vertrag-Informationen eingeben: \n---------------");
                vertrag = createVertrag(scanner);

            //Beziehungen: Kunde_Zaehler, Vertrag_Angebot herstellen
            if(privatkunde != null) {
                privatkunde.addZaehler(zaehler);
                vertrag.addAngebot(angebot);
                zaehler.addVertrag(vertrag);
            } else {
                geschaeftskunde.addZaehler(zaehler);
                vertrag.addAngebot(angebot);
                zaehler.addVertrag(vertrag);
            }

            em.getTransaction().begin();
            if (privatkunde != null) {
                em.persist(privatkunde);

            }else {
                em.persist(geschaeftskunde);
            }
            em.persist(adresse);
            em.persist(zaehler);
            em.persist(vertrag);
            em.getTransaction().commit();
            }
    }

    public void vertragBearbeiten(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        String eingabe = null;
        double eingabe2 = 0.0;
        Long vertragsnummer = 0L;
        Vertrag vertrag = null;
        Long kundennummer = 0L;
        Long tarifID = 0L;
        Kunde kunde;
        int check = 0;
        try {
            System.out.println("\n\nBitte die Kundennummer eingeben:");
            kundennummer = scanner.nextLong();
            kunde = em.find(Kunde.class, kundennummer);
            if(kunde == null) {
                System.out.println("\n\nKunde nicht vorhanden!!\n\n");
                return;
            }
            Query query = em.createQuery("SELECT v from  Vertrag v");
            List<Vertrag> vertragList = (List<Vertrag>) query.getResultList();
            for(Vertrag v : vertragList) {
                if(v.getZaehler().getKunde().getKundennummer() == kundennummer)
                    System.out.println(v);
            }
            System.out.println("Vertragsaenderung \n\nBitte geben Sie die Vertragsnummer ein:");
            vertragsnummer = scanner.nextLong();
            vertrag = em.find(Vertrag.class, vertragsnummer);
            if(vertrag == null) {
                System.out.println("Vertrag nicht gefunden");
                return;
            }
            System.out.println("---Sie moechten Ihren Vertrag aktualisieren---");
            System.out.println("Abschlagsbetrag aktualisieren?      aktuell ist " + vertrag.getAbschlagsbetrag() + " Euro monatlich");
            System.out.println("1. Ja\n 2. Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neuen Abschlagsbetrag eingeben:");
                eingabe2 = scanner.nextDouble();
                vertrag.setAbschlagsbetrag(eingabe2);
            }
            System.out.println("Rechnungsadresse aktualisieren?     aktuell ist " + vertrag.getRechnungsadresse());
            System.out.println("1. Ja\n 2.  Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neue Rechnungsadresse eingeben:");
                eingabe = scanner.next();
                vertrag.setRechnungsadresse(eingabe);
            }
            System.out.println("Tarif aktualisieren?     aktuell ist " + vertrag.getAngebot().getTarif());
            System.out.println("1. Ja\n 2.  Nein");
            check = scanner.nextInt();
            if(check == 1){
                Query query1 = em.createQuery("SELECT a FROM Angebot a");
                List<Angebot> angebots = (List<Angebot>) query1.getResultList();
                for (Angebot a :angebots) {
                    if(a.getAnbieter().getAnbieterID() == vertrag.getAngebot().getAnbieter().getAnbieterID()) {
                        System.out.println(a.getTarif());
                    }
                }
                System.out.println("Neue TarifID eingeben:");
                tarifID = scanner.nextLong();
                Tarif tarif = em.find(Tarif.class, tarifID);
                Angebot angebot = new Angebot(vertrag.getAngebot().getAnbieter(), tarif);
                vertrag.setAngebot(angebot);
                em.getTransaction().begin();
                em.getTransaction().commit();
            }

        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Vertrag existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }
    }

    public void vertragKuendigen(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long vertragsnummer = 0L;
        Long kundennummer = 0L;
        Kunde kunde;

        try{
            System.out.println("\n\nBitte die Kundennummer eingeben:");
            kundennummer = scanner.nextLong();
            kunde = em.find(Kunde.class, kundennummer);
            if(kunde == null) {
                System.out.println("\n\nKunde nicht vorhanden!!\n\n");
                return;
            }
            Query query = em.createQuery("SELECT v from  Vertrag v");
            List<Vertrag> vertragList = (List<Vertrag>) query.getResultList();
            for(Vertrag v : vertragList) {
                if(v.getZaehler().getKunde().getKundennummer() == kundennummer)
                    System.out.println(v);
            }
            System.out.println("\nBitte geben Sie die Vertragsnummer ein:");
            vertragsnummer = scanner.nextLong();
            Vertrag vertrag = em.find(Vertrag.class, vertragsnummer);
            vertrag.getAngebot().getVertragset().remove(vertrag);
            vertrag.getZaehler().setVertrag(null);
            em.remove(vertrag);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }
    }

    public Angebot angebotList(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe1;
        Long eingabe2;

        Query query = em.createQuery("SELECT a from Angebot a");
        List<Angebot> angebots = (List<Angebot>) query.getResultList();
        for (Angebot a : angebots) {
            System.out.println(a);
        }
        System.out.println("Geben Sie die AnbieterID und die dazu gehörige TarifID aus der Angebot-Liste");
        System.out.println("AnbieterID eingeben:");
        eingabe1 = scanner.nextLong();
        System.out.println("TarifID eingeben:");
        eingabe2 = scanner.nextLong();

        return em.find(Angebot.class, new Angebot_PK(eingabe1, eingabe2));
    }

    public void angebotErstellen(EntityManager em) {
        Scanner sc = new Scanner(System.in);
        Angebot angebot = null;
        try{
            Anbieter anbieter = anbieterHilfsMethode_AngebotErstellen(sc,em);
            if (anbieter == null) return;

            Tarif tarif = tarifHilfsMethode_AngebotErstellen(sc,em);
            if (tarif == null) return;

            angebot = new Angebot(anbieter, tarif);
            em.persist(angebot);
        }catch (Exception e) {
            System.out.println("Ungueltige Eingabe bitte wiederholen!!!");
        }
    }

    /*
    public void angebotLoeschen() {
        boolean exception;
        Scanner sc = new Scanner(System.in);
        do {
            exception = false;
            try {
                System.out.println("Anbieter Id eingeben");
                Long eingabe1 = sc.nextLong();
                System.out.println("Tarif Id eingeben");
                Long eingabe2 = sc.nextLong();
                em.getTransaction().begin();

                Angebot angebot = new Angebot();
                angebot.setId(new Angebot_PK(eingabe1, eingabe2));
                angebot = em.find(Angebot.class, angebot.getId());
                if (angebot != null) {
                    em.remove(angebot);
                } else System.out.println("falshe ID");

            } catch (Exception e) {
                System.out.println("falsche eingabe");
                exception = true;
            }
        } while (exception == true);
        em.getTransaction().commit();
    }

     */

    private Tarif tarifHilfsMethode_AngebotErstellen(Scanner sc,EntityManager em) {
        Tarif tarif = null;
        int tarifVorhanden;
        boolean eingabe = false;
        int tarifId;
        String name = null;
        double bonus = 0;
        boolean pGarantie = false;
        int kFrist = 0;
        int mLaufzeit = 0;
        double aPreis = 0;
        double gPreis = 0;

        do {
            try {
                System.out.println("Tarif vorhanden: \n 1- ja\n 2-nein\n 3-Exit");
                tarifVorhanden = sc.nextInt();
                sc.nextLine();
                if (tarifVorhanden == 1) {
                    eingabe = true;
                    do {
                        try {
                            int exit = 0;
                            System.out.println("tarifId eingeben");
                            tarifId = sc.nextInt();
                            tarif = em.find(Tarif.class, tarifId);
                            if (tarif == null) {
                                System.out.println("falsche ID\n 0-neu vesuchen \n 1-exite");
                                exit = sc.nextInt();
                                if (exit == 1) {
                                    return null;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("falsche eingabe");
                        }
                    } while (tarif == null);

                } else if (tarifVorhanden == 2) {
                    eingabe = true;
                    do {
                        try {


                            System.out.println("Name eingeben");
                            name = sc.nextLine();

                            System.out.println("neu kunden bonus eingeben");
                            bonus = sc.nextDouble();

                            System.out.println("preisgarantie : 1.true \n 2.false");
                            int eingabe2 = sc.nextInt();
                            if (eingabe2 == 1) {
                                pGarantie = true;
                            }

                            System.out.println("Kundigungsfrist eingeben(im Monat z.B: 3");
                            kFrist = sc.nextInt();

                            System.out.println("Mindistlaufzeit eingeben(im Monat z.B: 3");
                            mLaufzeit = sc.nextInt();

                            System.out.println("Arbeitspreis pro Kwh eingeben");
                            aPreis = sc.nextDouble();

                            System.out.println("Grund preis pro Jahr  eingeben");
                            gPreis = sc.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("falsche eingabe");
                            sc.nextLine();
                        }

                    } while (name == null || gPreis == 0 || aPreis == 0);

                    tarif = new Tarif(name, gPreis, aPreis, mLaufzeit, kFrist, pGarantie, bonus);


                    em.persist(tarif);


                } else if (tarifVorhanden == 3) {
                    return null;
                } else System.out.println("falsche eingabe");

            } catch (InputMismatchException e) {
                System.out.println("falsche eingabe");
            }
        } while (eingabe == false);

        return tarif;
    }

    private Anbieter anbieterHilfsMethode_AngebotErstellen(Scanner sc,EntityManager em) {

        int anbieterVorhanden;
        boolean eingabe = false;
        int anbieterId;
        Anbieter anbieter=new Anbieter() ;
        String name;
        String email;
        String ort;
        String telefon;
        do {
            System.out.println("Anbieter vorhanden? \n1. Ja\n2. Nein\n3. Exit");
            anbieterVorhanden = sc.nextInt();
            sc.nextLine();
            if (anbieterVorhanden == 1) {
                eingabe = true;
                c.angebotAnzeigen(em);
                do {
                    System.out.println("AnbieterId eingeben");
                    anbieterId = sc.nextInt();
                    anbieter = em.find(Anbieter.class, anbieterId);
                    if (anbieter == null) {
                        int exit = 0;
                        System.out.println("Falsche ID\n1. Neu versuchen \n2. Exit");
                        exit = sc.nextInt();
                        if (exit == 2) {
                            return null;
                        }
                    }
                } while (anbieter == null);

            } else if (anbieterVorhanden == 2) {
                eingabe = true;
                do {
                    System.out.println("Name eingeben");
                    name = sc.nextLine();

                    System.out.println("E-Mail eingeben");
                    email = sc.nextLine();

                    System.out.println("Ort eingeben");
                    ort = sc.nextLine();

                    System.out.println("Telefonnummer eingeben");
                    telefon = sc.nextLine();
                } while (name == null || email == null || ort == null || telefon == null);

                anbieter.setName(name);
                anbieter.setOrt(ort);
                anbieter.setEmail(email);
                anbieter.addTelefonnummer(telefon);
                em.persist(anbieter);
            } else System.out.println("Falsche Eingabe");

        } while (eingabe == false);
        return anbieter;
    }



    /*
     *
     * -----------------------UPDATE METHODEN-------------------------------------------------------------------------------------------
     * */

    private Adresse updateAdresse(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Adresse adresse = new Adresse();
        String eingabe = null;
        Long adresseID = 0L;
        int check = 0;
        System.out.println("Adresse aktualisieren \n ---------------- \n");
        try {
            System.out.println(" \n\nBitte geben Sie die AdresseID ein:");
            adresseID = scanner.nextLong();
            adresse = em.find(Adresse.class, adresseID);
            if(adresse == null) {
                System.out.println("Adresse nicht gefunden");
                return null;
            }
            System.out.println("---Sie moechten Ihre Adresse aktualisieren---");
            System.out.println("Strasse aktualisieren?      aktuell ist " + adresse.getStrasse());
            System.out.println("1. Ja\n 2. Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neue Strasse eingeben:");
                eingabe = scanner.next();
                adresse.setStrasse(eingabe);
            }
            System.out.println("Hausnummer aktualisieren?     aktuell ist " + adresse.getHausnummer());
            System.out.println("1. Ja\n 2.  Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neue Hausnummer eingeben:");
                eingabe = scanner.next();
                adresse.setHausnummer(eingabe);
            }
            System.out.println("PLZ aktualisieren?     aktuell ist " + adresse.getPlz());
            System.out.println("1. Ja\n2. Nein");
            check = scanner.nextInt();
            if(check == 1) {
                System.out.println("Neue PLZ eingeben:");
                eingabe = scanner.next();
                adresse.setPlz(eingabe);
            }
            System.out.println("Stadt aktualisieren?     aktuell ist " + adresse.getStadt());
            System.out.println("1. Ja\n2. Nein");
            check = scanner.nextInt();
            if(check == 1) {
                System.out.println("Neue Stadt eingeben:");
                eingabe = scanner.next();
                adresse.setStadt(eingabe);
            }
            System.out.println("Land aktualisieren?     aktuell ist " + adresse.getLand());
            System.out.println("1. Ja\n2. Nein");
            check = scanner.nextInt();
            if(check == 1) {
                System.out.println("Neues Land eingeben:");
                eingabe = scanner.next();
                adresse.setLand(eingabe);
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Vertrag existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }
        return adresse;
    }

    private Privatkunde updatePrivatkunde(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Privatkunde privatkunde = new Privatkunde();
        String eingabe;
        int eingabe2;
        Long kundennummer;
        int check = 0;
        System.out.println("Privatkunde aktualisieren \n ---------------- \n");
        try {
            System.out.println(" \n\nBitte geben Sie die Kundennummer ein:");
            kundennummer = scanner.nextLong();
            privatkunde = em.find(Privatkunde.class, kundennummer);
            if(privatkunde == null) {
                System.out.println("Kunde nicht gefunden");
                return null;
            }
            System.out.println("---Sie moechten Ihre Daten aktualisieren---");
            System.out.println("Anrede aktualisieren?      aktuell ist " + privatkunde.getAnrede());
            System.out.println("1. Ja\n 2. Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neue Anrede eingeben:");
                System.out.println("1. Herr\n2. Frau");
                eingabe2 = scanner.nextInt();
                if(eingabe2 == 1) {
                    privatkunde.setAnrede("Herr");
                } else if(eingabe2 == 2) {
                    privatkunde.setAnrede("Frau");
                }

            }
            System.out.println("Vorname aktualisieren?     aktuell ist " + privatkunde.getVorname());
            System.out.println("1. Ja\n 2.  Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neuer Vorname eingeben:");
                eingabe = scanner.next();
                privatkunde.setVorname(eingabe);
            }

            System.out.println("nachname aktualisieren?     aktuell ist " + privatkunde.getNachname());
            System.out.println("1. Ja\n 2.  Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neuer nachname eingeben:");
                eingabe = scanner.next();
                privatkunde.setNachname(eingabe);
            }
            System.out.println("Telefonnummer aktualisieren?     aktuell ist " + privatkunde.getTelefonnummer());
            System.out.println("1. Ja\n2. Nein");
            check = scanner.nextInt();
            if(check == 1) {
                System.out.println("Neue Telefonnummer eingeben:");
                eingabe = scanner.next();
                privatkunde.setTelefonnummer(eingabe);
            }
            System.out.println("Geburtsdatum aktualisieren?     aktuell ist " + privatkunde.getGeburtsdatum());
            System.out.println("1. Ja\n2. Nein");
            check = scanner.nextInt();
            if(check == 1) {
                System.out.println("Neues Geburtsdatum eingeben:    Fortmat: TT/MM/JJ");
                eingabe = scanner.next();
                privatkunde.setGeburtsdatum(new Date(eingabe));
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Privatkunde existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }
        return privatkunde;
    }

    private Geschaeftskunde updateGeschaeftskunde(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Geschaeftskunde geschaeftskunde = new Geschaeftskunde();
        String eingabe;
        int eingabe2;
        Long kundennummer;
        int check = 0;
        System.out.println("Geschaeftskunde aktualisieren \n ---------------- \n");
        try {
            System.out.println(" \n\nBitte geben Sie die Kundennummer ein:");
            kundennummer = scanner.nextLong();
            geschaeftskunde = em.find(Geschaeftskunde.class, kundennummer);
            if(geschaeftskunde == null) {
                System.out.println("Kunde nicht gefunden");
                return null;
            }
            System.out.println("---Sie moechten Ihre Daten aktualisieren---");
            System.out.println("Ansprechpartner aktualisieren?      aktuell ist " + geschaeftskunde.getAnsprechpartner());
            System.out.println("1. Ja\n 2. Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neuer Ansprechpartner eingeben:");
                eingabe = scanner.next();
               geschaeftskunde.setAnsprechpartner(eingabe);

            }
            System.out.println("Firmenname aktualisieren?     aktuell ist " + geschaeftskunde.getFirmenname());
            System.out.println("1. Ja\n 2.  Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Neuer Fiermenname eingeben:");
                eingabe = scanner.next();
                geschaeftskunde.setFirmenname(eingabe);
            }
            System.out.println("Telefonnummer aktualisieren?     aktuell ist " + geschaeftskunde.getTelefonnummer());
            System.out.println("1. Ja\n2. Nein");
            check = scanner.nextInt();
            if(check == 1) {
                System.out.println("Neue Telefonnummer eingeben:");
                eingabe = scanner.next();
                geschaeftskunde.setTelefonnummer(eingabe);
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Geschaeftskunde existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }
        return geschaeftskunde;
    }

    private Zaehler updateZaehler(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Zaehler  zaehler = new Zaehler();
        Long zaehlerstand;
        Long zaehlernummer;
        Long kundennummer;
        Long adresseID;
        int check;
        System.out.println("Zaehler aktualisieren \n ---------------- \n");
        try {
            zaehlernAnzeigen(em);
            System.out.println(" \n\nBitte geben Sie die AdresseID ein:");
            adresseID = scanner.nextLong();
            System.out.println(" \n\nBitte geben Sie die Zaehlernummer ein:");
            zaehlernummer = scanner.nextLong();
            Zaehler_AdressePK pk = new Zaehler_AdressePK(adresseID);
            pk.setZaehlernummer(zaehlernummer);
            zaehler = em.find(Zaehler.class, pk);
            if(zaehler == null) {
                System.out.println("Zaehler nicht gefunden");
                return null;
            }
            System.out.println("---Sie moechten Ihre Zaehler-Daten aktualisieren---");

            System.out.println("Zaehlerstand aktualisieren?      aktuell ist " + zaehler.getZaehlerstand());
            System.out.println("1. Ja\n 2. Nein");
            check = scanner.nextInt();
            if(check == 1){
                zaehlerstand = scanner.nextLong();
                zaehler.setZaehlerstand(zaehlerstand);
            }
            System.out.println("Inhaber (Kunde) aktualisieren?      aktuell ist " + zaehler.getKunde());
            System.out.println("1. Ja\n 2. Nein");
            check = scanner.nextInt();
            if(check == 1){
                System.out.println("Kunde vorhanden? \n1. Ja\n2. Nein");
                check = scanner.nextInt();
                if(check == 1) {
                    kundeAnzeigen(em);
                    System.out.println("Neue Kundennummer eingeben:");
                    kundennummer = scanner.nextLong();
                    Kunde  kunde = em.find(Kunde.class, kundennummer);
                    if(kunde == null) {
                        throw new IllegalArgumentException("Kunde nicht gefunden");
                    }
                    zaehler.getKunde().getZaehlerList().remove(zaehler);
                    kunde.addZaehler(zaehler);
                } else if(check == 2) {
                    System.out.println("Sind Sie: \n1. Privatkunde\n2. Geschaeftskunde");
                    check = scanner.nextInt();
                    if(check == 1) {
                        Privatkunde privatkunde = createPrivatkunde(scanner);
                        privatkunde.addZaehler(zaehler);
                        em.persist(privatkunde);
                    } else if(check == 2) {
                        Geschaeftskunde geschaeftskunde = createGeschaeftskunde(scanner);
                        geschaeftskunde.addZaehler(zaehler);
                        em.persist(geschaeftskunde);
                    }
                }
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER "+e.getMessage()+" existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }
        return zaehler;
    }

    public  void updateAnbieter(EntityManager em) {
        Scanner sc = new Scanner(System.in);
        int anbieterId;
        int eingabe = 0;
        Anbieter anbieter= null;
        anbieterAnzeigen(em);
        do {
            try {

                System.out.println("AnbieterId eingeben");
                anbieterId = sc.nextInt();
                anbieter = em.find(Anbieter.class, anbieterId);
                if (anbieter == null) {
                    int exit = 0;
                    System.out.println("Falsche ID\n1. Neu versuchen \n2. Exit");
                    exit = sc.nextInt();
                    if (exit == 2) {
                        return;
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Falsche Eingabe");
                sc.nextLine();
            }
        } while (anbieter == null);
        do {
            try {
                System.out.println("1. Name aendern\n2. E-mail aendern \n3. Ort aendern\n4. Telefonnummer einfuegen\n5. Telefonnummer loeschen\n6. Exit");
                eingabe = sc.nextInt();
                switch (eingabe) {
                    case 1:
                        System.out.println("Neuer Name eingeben: ");
                        String name = sc.next();
                        anbieter.setName(name);
                        break;

                    case 2:
                        System.out.println("Neue E-mail eingeben: ");
                        String email = sc.next();
                        anbieter.setEmail(email);
                        break;

                    case 3:
                        System.out.println("Neuer Ort eingeben: ");
                        String ort = sc.next();
                        anbieter.setOrt(ort);
                        break;

                    case 4:
                        System.out.println("Telefonnummer eingeben");
                        String telefon = sc.next();
                        anbieter.getTelefonList().add(telefon);
                        break;

                    case 5:
                        anbieter_telefonAnzeigen(em);
                        System.out.println("Telefonnummer eingeben");
                        String telefon2 = sc.next();
                        anbieter.getTelefonList().remove(telefon2);
                        break;

                    default:
                        System.out.println("Ungueltige Eingabe");
                }
            } catch (InputMismatchException e) {
                System.out.println("Falsche Eingabe");
            }

        } while (eingabe != 6);
    }

    public  void updateTarif(EntityManager em) {
        Scanner sc = new Scanner(System.in);
        int tarifId;
        int eingabe = 0;
        Tarif tarif;
        tarifAnzeigen(em);
        do {
            System.out.println("TarifId eingeben");
            tarifId = sc.nextInt();

            tarif = em.find(Tarif.class, tarifId);
            if (tarif == null) {
                int exit = 0;
                System.out.println("Falsche ID\n1. Neu Versuchen \n2. Exit");
                exit = sc.nextInt();
                if (exit == 2) {
                    return;
                }
            }
        } while (tarif == null);

        try {
            tarif=tarifHilfsMethode_UpdateTarif(tarif, sc,em);
        } catch (InputMismatchException e) {
            System.out.println("Falsche Eingabe");
        }


    }

    private  Tarif tarifHilfsMethode_UpdateTarif(Tarif tarif, Scanner sc,EntityManager em) {
        int eingabe=0;
        double bonus=0;
        int eingabe2=0;
        int kFrist=0;
        int mLaufzeit;
        double aPreis=0;
        double gPreis=0;

        do {
            try {
                System.out.println("1. Bezeichnung aendern\n2. Kunden-Bonus aendern \n3. Preisgarantie aendern\n4. Kundigungsfrist aendern\n5. Mindestlaufzeit aendern\n6. Arbeitspreis pro kWh aendern\n7. Grundpreis pro Jahr aendern\n8. Speichern");
                eingabe = sc.nextInt();

                switch (eingabe) {
                    case 1:
                        System.out.println("Neue Bezeichnung  eingeben: ");
                        String bezeichnung = sc.next();
                        tarif.setTarifbezeichnung(bezeichnung);
                        break;

                    case 2:
                        System.out.println("Neues Kunden-Bonus eingeben: ");
                        bonus = sc.nextDouble();
                        tarif.setNeukundenBonus(bonus);
                        break;

                    case 3:
                        System.out.println("Preisgarantie:\n1.true \n2.false");
                        eingabe2 = sc.nextInt();
                        if (eingabe2 == 1) {
                            tarif.setPreisgarantie(true);
                        } else tarif.setPreisgarantie(false);
                        break;

                    case 4:
                        System.out.println("Kuendigungsfrist eingeben");
                        kFrist = sc.nextInt();
                        tarif.setKuendigungsfrist(kFrist);
                        break;

                    case 5:
                        System.out.println("Mindestlaufzeit eingeben");
                        mLaufzeit = sc.nextInt();
                        tarif.setMindestlaufzeit(mLaufzeit);
                        break;

                    case 6:
                        System.out.println("Arbeitspreis pro kWh eingeben");
                        aPreis = sc.nextDouble();
                        tarif.setArbeitKWH(aPreis);
                        break;

                    case 7:
                        System.out.println("Grundpreis pro Jahr eingeben");
                        gPreis = sc.nextDouble();
                        tarif.setGrundpreis(gPreis);
                        break;
                    default:

                }
            }catch (InputMismatchException e){

                System.out.println("Falsche Eingabe ");
                sc.nextLine();
            }

        }while (eingabe != 8);
        return  tarif;

    }

    public void angebotBearbeiten() {
        boolean exception;
        Scanner sc = new Scanner(System.in);
        Long eingabe;
        Long eingabe2;
        do {
            exception = false;
            try {
                angebotAnzeigen(em);
                System.out.println("\nAnbeiterId eingeben");
                eingabe = sc.nextLong();
                System.out.println("\nTarifId eingeben");
                eingabe2 = sc.nextLong();
                Angebot angebot = em.find(Angebot.class, new Angebot_PK(eingabe,eingabe2));
                if (angebot != null) {
                    do {
                        System.out.println("1. Anbieter aendern\n2. Tarif aendern\n 3. Speichern");
                        eingabe2 = sc.nextLong();
                        if (eingabe2 == 1) {
                            Anbieter anbieter = anbieterHilfsMethode_AngebotErstellen(sc,em);
                            angebot.setAnbieter(anbieter);

                        } else if (eingabe2 == 2) {
                            Tarif tarif = tarifHilfsMethode_AngebotErstellen(sc,em);
                            angebot.setTarif(tarif);
                        } else if (eingabe2 == 3) {
                            break;
                        }
                    } while (eingabe2 != 3);
                } else System.out.println("falshe ID");

            } catch (Exception e) {
                System.out.println("falsche eingabe");
                exception = true;
                sc.nextLine();
            }
        } while (exception == true);
        em.getTransaction().commit();
    }

    public static Angebot updateAngebot(EntityManager em) {
        boolean exception;
        Angebot angebot=null;
        Scanner sc = new Scanner(System.in);
        Long eingabe;
        Long eingabe2;
        do {
            exception = false;
            try {
                c.angebotAnzeigen(em);
                System.out.println("Anbeiter Id eingeben");
                eingabe = sc.nextLong();
                System.out.println("Tarif Id eingeben");
                eingabe2=sc.nextLong();
                angebot = em.find(Angebot.class, new Angebot_PK(eingabe,eingabe2));
                if (angebot != null) {
                    do {
                        System.out.println("1.Anbieter aendern\n2. Tarif aendern\n3. Exit");
                        eingabe2 = sc.nextLong();
                        if (eingabe2 == 1) {
                            Anbieter anbieter = c.anbieterHilfsMethode_AngebotErstellen(sc, em);
                            angebot.setAnbieter(anbieter);

                        } else if (eingabe2 == 2) {
                            Tarif tarif = c.tarifHilfsMethode_AngebotErstellen(sc,em);
                            angebot.setTarif(tarif);
                        } else if (eingabe2 == 3) {
                            break;
                        }
                    } while (eingabe2 != 3);
                } else System.out.println("Falshe ID");

            } catch (Exception e) {
                System.out.println("Falsche Eingabe");
                exception = true;
            }
        } while (exception == true);
        return angebot;
    }



    /*
     *
     * -----------------------INSERT METHODEN-------------------------------------------------------------------------------------------
     * */

    private Vertrag createVertrag(Scanner scanner) {
        Vertrag vertrag = new Vertrag();
        Double eingabe = 0.0;
        String eingabe2 = null;

        System.out.println("Abschlagsbetrag eingeben:");
        eingabe = scanner.nextDouble();
        if(eingabe != 0.0) {
            vertrag.setAbschlagsbetrag(eingabe);
        }
        System.out.println("Rechnungsadresse eingeben:");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            vertrag.setRechnungsadresse(eingabe2);
        }
        System.out.println("Vertragsbeginn eingeben:                Format: TT/MM/JJ");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            vertrag.setVertragsbeginn(new Date(eingabe2));
        }
        return vertrag;
    }

    private Privatkunde createPrivatkunde(Scanner scanner) {
        Privatkunde privatkunde = new Privatkunde();
        int eingabe = 0;
        String eingabe2 = null;
        System.out.println("Privatkunde einfuegen \n ---------------");
        System.out.println("Anrede: \n1. Herr \n2. Frau");
        eingabe = scanner.nextInt();
        if(eingabe == 1) {
            privatkunde.setAnrede("Herr");
        } else if(eingabe == 2) {
            privatkunde.setAnrede("Frau");
        }

        System.out.println("Vorname eingeben:");
        eingabe2 = scanner.next();

        if(eingabe2 != null && eingabe2.length() > 0) {
            privatkunde.setVorname(eingabe2);
        }

        System.out.println("Nachname eingeben:");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            privatkunde.setNachname(eingabe2);
        }

        System.out.println("Geburtsdatum eingeben:          Format: TT/MM/JJ");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            privatkunde.setGeburtsdatum(new Date(eingabe2));
        }

        System.out.println("Telefonnummer eingeben:");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            privatkunde.setTelefonnummer(eingabe2);
        }
        return privatkunde;
    }

    private Geschaeftskunde createGeschaeftskunde(Scanner scanner) {
        Geschaeftskunde geschaeftskunde = new Geschaeftskunde();
        int eingabe = 0;
        String eingabe2 = null;
        System.out.println("Geschaeftskundeeinfuegen \n ---------------");

        System.out.println("Ansprechpartner eingeben:");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            geschaeftskunde.setAnsprechpartner(eingabe2);
        }

        System.out.println("Firmenname eingeben:");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            geschaeftskunde.setFirmenname(eingabe2);
        }

        System.out.println("Telefonnummer eingeben:");
        eingabe2 = scanner.next();
        if(eingabe2 != null && eingabe2.length() > 0) {
            geschaeftskunde.setTelefonnummer(eingabe2);
        }
        return geschaeftskunde;
    }

    private Adresse createAdresse(Scanner scanner) {
        Adresse adresse = new Adresse();
        String eingabe = null;

        System.out.println("Adresse eingeben \n ---------------- \n");

        System.out.println("Strasse eingeben: \n");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length() > 0)
            adresse.setStrasse(eingabe);

        System.out.println("Hausnummer eingeben: \n");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length() > 0)
            adresse.setHausnummer(eingabe);

        System.out.println("PLZ eingeben: \n");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length() > 0)
            adresse.setPlz(eingabe);

        System.out.println("Stadt eingeben: \n");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length() > 0)
            adresse.setStadt(eingabe);

        System.out.println("Land eingeben: \n");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length() > 0)
            adresse.setLand(eingabe);

        return adresse;
    }

    private Zaehler createZaehler(Scanner scanner){
        Zaehler zaehler = new Zaehler();
        Long eingabe;

        System.out.println("Bitte Zaehlerstand eingeben:");
        eingabe = scanner.nextLong();
        zaehler.setZaehlerstand(eingabe);

        return zaehler;
    }

    private Anbieter createAnbieter(Scanner scanner) {
        Anbieter anbieter = new Anbieter();
        String eingabe = null;
        System.out.println("Bitte Anbietername eingeben:");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length()>0) {
            anbieter.setName(eingabe);
        }
        System.out.println("Bitte E-Mailadresse eingeben:");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length()>0) {
            anbieter.setEmail(eingabe);
        }
        System.out.println("Bitte Ort eingeben:");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length()>0) {
            anbieter.setOrt(eingabe);
        }
        int i = 0;
        do{
            System.out.println("Bitte Telefonnummer eingeben:");
            eingabe = scanner.next();
            if(eingabe != null && eingabe.length()>0) {
                anbieter.getTelefonList().add(eingabe);
            }
            System.out.println("Eine weitere Telefonnummer eingeben: \n1. Ja \n2. Abbrechen");
            i = scanner.nextInt();
        }while (i != 2);

        return anbieter;
    }

    private Tarif createTarif(Scanner scanner) {
        Tarif tarif = new Tarif();
        String eingabe = null;
        double eingabe2 = 0.0;
        System.out.println("Bitte Trarifbezeichnung eingeben:");
        eingabe = scanner.next();
        if(eingabe != null && eingabe.length()>0) {
            tarif.setTarifbezeichnung(eingabe);
        }
        System.out.println("Bitte Grundpreis pro Jahr eingeben:");
        eingabe2 = scanner.nextDouble();
        if(eingabe2 != 0.0) {
            tarif.setGrundpreis(eingabe2);
        }
        System.out.println("Bitte Arbeitpreis pro kWh in Cent monatlich eingeben:");
        eingabe2 = scanner.nextDouble();
        if(eingabe2 != 0.0) {
            tarif.setArbeitKWH(eingabe2);
        }
        System.out.println("Bitte Mindeslaufzeit in Monaten eingeben:");
        eingabe2 = scanner.nextDouble();
        if(eingabe2 != 0.0) {
            tarif.setMindestlaufzeit((int) eingabe2);
        }
        System.out.println("Bitte Kuendigungsfrist in Monaten eingeben:");
        eingabe2 = scanner.nextDouble();
        if(eingabe2 != 0.0) {
            tarif.setKuendigungsfrist((int) eingabe2);
        }
        System.out.println("Bitte Preisgarantie eingeben: \n1. Ja \n2. Nein");
        eingabe2 = scanner.nextDouble();
        if(eingabe2 == 1.0) {
            tarif.setPreisgarantie(true);
        } else if(eingabe2 == 2.0) {
            tarif.setPreisgarantie(false);
        }
        System.out.println("Bitte Neukunden-Bonus eingeben:");
        eingabe2 = scanner.nextDouble();
        if(eingabe2 != 0.0) {
            tarif.setNeukundenBonus(eingabe2);
        }

        return tarif;
    }

    /*
     *
     * -----------------------DELETE METHODEN-------------------------------------------------------------------------------------------
     * */

    private void deleteAdresse(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe;
        try {
            System.out.println("Adresse loeschen\n----------------\n");
            adressenAnzeigen(em);
            System.out.println(" Bitte eine ID waehlen:");
            eingabe = scanner.nextLong();
            Adresse adresse = em.find(Adresse.class, eingabe);
            if(adresse == null) {
                throw new IllegalArgumentException();
            }
            for(Zaehler zaehler: adresse.getZaehlerList()) {
                zaehler.getKunde().getZaehlerList().remove(zaehler);
            }
            em.remove(adresse);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Adresse existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deleteAnbieter(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe;
        try {
            System.out.println("Anbieter loeschen\n----------------\n");
            anbieterAnzeigen(em);
            System.out.println(" Bitte eine anbieterID waehlen:");
            eingabe = scanner.nextLong();
            Anbieter anbieter = em.find(Anbieter.class, eingabe);
            if(anbieter == null) {
                throw new IllegalArgumentException();
            }
            Query query = em.createQuery("select a from Angebot a");
            List<Angebot> angebots = (List<Angebot>) query.getResultList();
            for (Angebot a :  angebots) {
                if(a.getId().getAnbieterID() == anbieter.getAnbieterID()) {
                    System.out.println(a);
                    a.getTarif().getTarif_angebotList().remove(a);
                    for(Vertrag v: a.getVertragset()) {
                        v.setAngebot(null);
                    }
                    a.setVertragset(null);
                    em.remove(a);
                }
            }
            em.remove(anbieter);
            System.out.println("Erfolgreich");
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Anbieter existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deleteAngebot(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe1;
        Long eingabe2;

        try {
            System.out.println("Angebot loeschen\n----------------\n");
            angebotAnzeigen(em);
            System.out.println(" Bitte eine AnbieterID waehlen:");
            eingabe1 = scanner.nextLong();
            System.out.println(" Bitte eine TarifID waehlen:");
            eingabe2 = scanner.nextLong();

            Angebot angebot = em.find(Angebot.class, new Angebot_PK(eingabe1, eingabe2));
            if(angebot == null) {
                throw new IllegalArgumentException();
            }

            angebot.getAnbieter().getAnbieter_angebotList().remove(angebot);
            angebot.getTarif().getTarif_angebotList().remove(angebot);
            for(Vertrag v : angebot.getVertragset()) {
                v.setAngebot(null);
            }
            angebot.setVertragset(null);
            em.remove(angebot);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Angebot existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deletePrivatkunde(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe;
        try {
            System.out.println("Privatkunde loeschen\n----------------\n");
            privatkundeAnzeigen(em);
            System.out.println(" Bitte eine Kundennummer waehlen:");
            eingabe = scanner.nextLong();
            Privatkunde privatkunde = em.find(Privatkunde.class, eingabe);
            if(privatkunde == null) {
                throw new IllegalArgumentException();
            }
            em.remove(privatkunde);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Privatkunde existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deleteGeschaeftskunde(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe;
        try {
            System.out.println("Geschaeftskunde loeschen\n----------------\n");
            geschaeftskundeAnzeigen(em);
            System.out.println(" Bitte eine Kundennummmer waehlen:");
            eingabe = scanner.nextLong();
            Geschaeftskunde geschaeftskunde = em.find(Geschaeftskunde.class, eingabe);
            if(geschaeftskunde == null) {
                throw new IllegalArgumentException();
            }
            em.remove(geschaeftskunde);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Geschaeftskunde existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deleteVertrag(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long vertragsnummer;
        try {
            System.out.println("Vertrag loeschen\n----------------\n");
            vertragAnzeigen(em);
            System.out.println("\nBitte geben Sie die Vertragsnummer ein:");
            vertragsnummer = scanner.nextLong();
            Vertrag vertrag = em.find(Vertrag.class, vertragsnummer);
            if(vertrag == null) {
                throw new IllegalArgumentException();
            }
            vertrag.getAngebot().getVertragset().remove(vertrag);
            vertrag.getZaehler().setVertrag(null);
            em.remove(vertrag);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Vertrag existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deleteZaehler(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe1;
        Long eingabe2;
        try {
            System.out.println("Zaehler loeschen\n----------------\n");
            zaehlernAnzeigen(em);
            System.out.println(" Bitte eine AdresseID waehlen:");
            eingabe1 = scanner.nextLong();
            System.out.println(" Bitte eine Zaehlernummer waehlen:");
            eingabe2 = scanner.nextLong();
            Zaehler_AdressePK pk = new Zaehler_AdressePK(eingabe1);
            pk.setZaehlernummer(eingabe2);
            Zaehler zaehler = em.find(Zaehler.class,pk);
            if(zaehler == null) {
                throw new IllegalArgumentException();
            }
            zaehler.getKunde().getZaehlerList().remove(zaehler);
            zaehler.getAdresse().getZaehlerList().remove(zaehler);
            em.remove(zaehler);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Zaehler existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    private void deleteTarif(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        Long eingabe;
        try {
            System.out.println("Tarif loeschen\n----------------\n");
            tarifAnzeigen(em);
            System.out.println(" Bitte eine TarifID waehlen:");
            eingabe = scanner.nextLong();
            Tarif tarif = em.find(Tarif.class, eingabe);

            if(tarif == null) {
                throw new IllegalArgumentException();
            }

            Query query = em.createQuery("select a from Angebot a");
            List<Angebot> angebots = (List<Angebot>) query.getResultList();
            for (Angebot a :  angebots) {
                if(a.getId().getTarifID() == tarif.getTarifID()) {
                    System.out.println(a);
                    a.getAnbieter().getAnbieter_angebotList().remove(a);
                    for(Vertrag v: a.getVertragset()) {
                        v.setAngebot(null);
                    }
                    a.setVertragset(null);
                    em.remove(a);
                }
            }
            em.remove(tarif);
        }catch (IllegalArgumentException e) {
            System.out.println("Sie haben ungueltige Eingabe getaetigt ODER Tarif existiert nicht");
        }catch (Exception e) {
            System.out.println("Fehler im Lauf, Bitte wiederholen Sie Ihr Eingabe spaeter nochmal..");
        }

    }

    /*
    *
    * -----------------------METHODEN AUF TABELLEN-------------------------------------------------------------------------------------------
    * */

    public void tabelleAdresse(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Adresse \nWas moecheten Sie?\n1. INSERT\n2. UPDATE \n3. DELETE\n4. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    Adresse adresse = createAdresse(scanner);
                    em.persist(adresse);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    updateAdresse(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    deleteAdresse(em);
                    em.getTransaction().commit();
                    break;
                case 4:
                    adressenAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    public void tabelleZaehler(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Zaehler \nWas moecheten Sie?\n1. UPDATE \n2. DELETE\n3. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    updateZaehler(em);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    deleteZaehler(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    zaehlernAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        }

    }

    public void tabelleAnbieter(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Anbieter \nWas moecheten Sie?\n1. INSERT\n2. UPDATE \n3. DELETE\n4. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    Anbieter anbieter = createAnbieter(scanner);
                    em.persist(anbieter);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    updateAnbieter(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    deleteAnbieter(em);
                    em.getTransaction().commit();
                    break;
                case 4:
                    anbieterAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    public void tabelleAngebot(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Angebot \nWas moecheten Sie?\n1. INSERT\n2. UPDATE \n3. DELETE\n4. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    angebotErstellen(em);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    updateAngebot(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    deleteAngebot(em);
                    em.getTransaction().commit();
                    break;
                case 4:
                    angebotAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    public void tabelleGeschaeftskunde(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Geschaeftskunde \nWas moecheten Sie?\n1. INSERT\n2. UPDATE \n3. DELETE\n4. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    Geschaeftskunde geschaeftskunde = createGeschaeftskunde(scanner);
                    em.persist(geschaeftskunde);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    updateGeschaeftskunde(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    deleteGeschaeftskunde(em);
                    em.getTransaction().commit();
                    break;
                case 4:
                    geschaeftskundeAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    public void tabellePrivatkunde(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Privakunde \nWas moecheten Sie?\n1. INSERT\n2. UPDATE \n3. DELETE\n4. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    Privatkunde privatkunde = createPrivatkunde(scanner);
                    em.persist(privatkunde);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    updatePrivatkunde(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    deletePrivatkunde(em);
                    em.getTransaction().commit();
                    break;
                case 4:
                    privatkundeAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    public void tabelleTarif(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Tarif \nWas moecheten Sie?\n1. INSERT\n2. UPDATE \n3. DELETE\n4. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    Tarif tarif = createTarif(scanner);
                    em.persist(tarif);
                    em.getTransaction().commit();
                    break;
                case 2:
                    em.getTransaction().begin();
                    updateTarif(em);
                    em.getTransaction().commit();
                    break;
                case 3:
                    em.getTransaction().begin();
                    deleteTarif(em);
                    em.getTransaction().commit();
                    break;
                case 4:
                    tarifAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    public void tabelleVertrag(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;

        try{
            System.out.println("Tabelle Vertrag \nWas moecheten Sie?\n1. DELETE\n2. Anzeigen");
            check = scanner.nextInt();
            switch (check) {
                case 1:
                    em.getTransaction().begin();
                    deleteVertrag(em);
                    em.getTransaction().commit();
                    break;
                case 2:
                    vertragAnzeigen(em);
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ungueltige Eingabe");
        } catch (Exception e) {
            System.out.println("Fehler im Lauf");
        }

    }

    /*
     *
     * -----------------------TABELLEN ANZEIGEN-------------------------------------------------------------------------------------------
     * */

    private void adressenAnzeigen(EntityManager em) {
        Query query = em.createQuery("SELECT a from Adresse a");
        List<Adresse> adresseList = (List<Adresse>) query.getResultList();

        for(Adresse a : adresseList) {
            System.out.println(a);
        }
    }

    public void angebotAnzeigen(EntityManager em) {
        Query query = em.createQuery("SELECT a from Angebot a");
        List<Angebot> angebotList = (List<Angebot>) query.getResultList();

        for(Angebot a : angebotList) {
            System.out.println(a);
            System.out.println("Anbieter : "+a.getAnbieter().getName() +" Tarif Informationen : "+ a.getTarif()+"\n\n" );
        }
    }

    public void zaehlernAnzeigen(EntityManager em) {
        Query query = em.createQuery("select z from Zaehler z");
        List<Zaehler> zaehlerList = (List<Zaehler>) query.getResultList();

        for(Zaehler z : zaehlerList) {
            System.out.println(z);
        }
    }

    public void anbieterAnzeigen(EntityManager em) {
        Query query = em.createQuery("select a from Anbieter a");
        List<Anbieter> anbieterList = (List<Anbieter>) query.getResultList();

        for(Anbieter z : anbieterList) {
            System.out.println(z);
        }
    }

    public void geschaeftskundeAnzeigen(EntityManager em) {
        Query query = em.createQuery("select g from Geschaeftskunde g");
        List<Geschaeftskunde> geschaeftskundeList = (List<Geschaeftskunde>) query.getResultList();

        for(Geschaeftskunde z : geschaeftskundeList) {
            System.out.println(z);
        }
    }

    public void privatkundeAnzeigen(EntityManager em) {
        Query query = em.createQuery("select p from Privatkunde p");
        List<Privatkunde> privatkundeList = (List<Privatkunde>) query.getResultList();

        for(Privatkunde z : privatkundeList) {
            System.out.println(z);
        }
    }

    public void kundeAnzeigen(EntityManager em) {
        Query query = em.createQuery("select k from Kunde k");
        List<Kunde> kundeList = (List<Kunde>) query.getResultList();

        for(Kunde z : kundeList) {
            System.out.println(z);
        }
    }

    public void vertragAnzeigen(EntityManager em) {
        Query query = em.createQuery("select v from Vertrag v");
        List<Vertrag> vertragList = (List<Vertrag>) query.getResultList();

        for(Vertrag z : vertragList) {
            System.out.println(z);
        }
    }

    public void tarifAnzeigen(EntityManager em) {
        Query query = em.createQuery("select t from Tarif t");
        List<Tarif> tarifList = (List<Tarif>) query.getResultList();

        for(Tarif z : tarifList) {
            System.out.println(z);
        }
    }

    public void anbieter_telefonAnzeigen(EntityManager em) {
        Query query = em.createQuery("SELECT a from Anbieter a");
        List<Anbieter> anbieterList = (List<Anbieter>) query.getResultList();

        for(Anbieter a : anbieterList) {
            for (String s : a.getTelefonList()) {
                System.out.println("AnbieterID: " + a.getAnbieterID() + " Telefon: " + s);
            }
        }

    }

    /*
     * ---------------------------------------------------------------------------------------------
     *
     * */
}
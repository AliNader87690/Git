package controller;

import  jakarta.persistence.*;
import modul.*;
import java.util.Date;

public class Haupklasse {


    @PersistenceUnit
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    @PersistenceContext
    static EntityManager em = emf.createEntityManager();

    static Controller c = new Controller();

    public static void main(String[] args) {
        Anbieter anbieter1 = new Anbieter("DEW21", "DEW21@gmail.com", "Dortmund");
        Anbieter anbieter2 = new Anbieter("Adesso", "adesso@gmail.com", "Dortmund");
        Anbieter anbieter3 = new Anbieter("Brockhaus", "Brockhaus@gmail.com", "Hagen");
        Anbieter anbieter4 = new Anbieter("Enreach", "Enreach@gmail.com", "Hagen");

        Tarif komfort = new Tarif("Komfort", 100.25, 0.25, 24, 3, true, 50.00);
        Tarif basis = new Tarif("Basis", 75.25, 0.08, 12, 3, false, 10.00);
        Tarif spar = new Tarif("Spar", 50, 0.15, 24, 3, true, 40.00);

        Angebot angebot1 = new Angebot(anbieter1, komfort);
        Angebot angebot2 = new Angebot(anbieter2, spar);
        Angebot angebot3 = new Angebot(anbieter3, basis);
        Angebot angebot4 = new Angebot(anbieter4, spar);
        Angebot angebot5 = new Angebot(anbieter1, spar);
        Angebot angebot6 = new Angebot(anbieter1, basis);

        Adresse adresse1 = new Adresse("Lange Str", "Dortmund", "De", "44227", "114");
        Adresse adresse2 = new Adresse("Vogel Str", "Hagen", "De", "44235", "12");
        Adresse adresse3 = new Adresse("Hohe Str", "Hagen", "De", "44255", "10");
        Adresse adresse4 = new Adresse("Im Dorloh Str", "Dortmund", "De", "44338", "25");

        Privatkunde kunde1 = new Privatkunde("01776221795", "Somar", "Youssef", "Herr", new Date("09/01/1998"));
        Privatkunde kunde2 = new Privatkunde("01772154479", "Ali", "Nader", "Herr", new Date("01/01/1994"));

        Geschaeftskunde kunde3 = new Geschaeftskunde("017762214589", "Adesso", "MarkA");
        Geschaeftskunde kunde4 = new Geschaeftskunde("017762223444", "Brockhaus", "MarkB");

        Zaehler zaehler1 = new Zaehler(adresse1, 20000L, kunde1);
        Zaehler zaehler2 = new Zaehler(adresse2, 50000L, kunde2);
        Zaehler zaehler3 = new Zaehler(adresse3, 25000L, kunde3);
        Zaehler zaehler4 = new Zaehler(adresse4, 30000L, kunde4);
        Zaehler zaehler5 = new Zaehler(adresse1, 20000L, kunde1);
        Zaehler zaehler6 = new Zaehler(adresse2, 50000L, kunde2);
        Zaehler zaehler7 = new Zaehler(adresse3, 25000L, kunde3);

        Vertrag vertrag1 = new Vertrag("Lange Str", new Date("01/03/2022"), angebot1, 500, zaehler1);
        Vertrag vertrag2 = new Vertrag("Berg Str", new Date("01/05/2022"), angebot2, 200, zaehler2);
        Vertrag vertrag3 = new Vertrag("Bergischer Ring Str", new Date("01/02/2022"), angebot3, 10, zaehler3);
        Vertrag vertrag4 = new Vertrag("Maximilian Str", new Date("01/03/2021"), angebot4, 250, zaehler4);

        anbieter1.addTelefonnummer("01776221795");
        anbieter1.addTelefonnummer("01776225333");
        anbieter2.addTelefonnummer("01776222536");
        anbieter3.addTelefonnummer("01776236795");
        anbieter4.addTelefonnummer("01776221885");

        kunde1.addZaehler(zaehler1);
        kunde2.addZaehler(zaehler2);
        kunde3.addZaehler(zaehler3);
        kunde4.addZaehler(zaehler4);
        kunde1.addZaehler(zaehler5);
        kunde2.addZaehler(zaehler6);
        kunde3.addZaehler(zaehler7);
        try {

            em.getTransaction().begin();


            em.persist(kunde1);
            em.persist(kunde2);
            em.persist(kunde3);
            em.persist(kunde4);

            em.persist(komfort);
            em.persist(spar);
            em.persist(basis);

            em.persist(anbieter1);
            em.persist(anbieter2);
            em.persist(anbieter3);
            em.persist(anbieter4);

            em.persist(adresse1);
            em.persist(adresse2);
            em.persist(adresse3);
            em.persist(adresse4);


            em.persist(angebot1);
            em.persist(angebot2);
            em.persist(angebot3);
            em.persist(angebot4);
            em.persist(angebot5);
            em.persist(angebot6);

            em.persist(zaehler1);
            em.persist(zaehler2);
            em.persist(zaehler3);
            em.persist(zaehler4);
            em.persist(zaehler5);
            em.persist(zaehler6);
            em.persist(zaehler7);

            em.persist(vertrag1);
            em.persist(vertrag2);
            em.persist(vertrag3);
            em.persist(vertrag4);

            em.getTransaction().commit();
            int aktion = 0;
            while (aktion != 14) {
                aktion = c.hauptMenu();
                switch (aktion) {
                    case 1:
                        c.vertragAbschliessen(em);
                        break;
                    case 2:
                        em.getTransaction().begin();
                        c.vertragKuendigen(em);
                        em.getTransaction().commit();
                        break;
                    case 3:
                        c.vertragBearbeiten(em);
                        break;

                    case 4:
                        c.angebotAnzeigen(em);
                        break;

                    case 5:
                        c.tabelleAngebot(em);
                        break;
                    case 6:
                        c.tabelleAdresse(em);
                        break;
                    case 7:
                        c.tabelleAnbieter(em);
                        break;
                    case 8:
                        c.tabelleGeschaeftskunde(em);
                        break;
                    case 9:
                        c.tabellePrivatkunde(em);
                        break;
                    case 10:
                        c.tabelleTarif(em);
                        break;
                    case 11:
                        c.tabelleVertrag(em);
                        break;
                    case 12:
                        c.tabelleZaehler(em);
                        break;
                    case 13:
                        c.anbieter_telefonAnzeigen(em);
                        break;
                    case 14:
                        break;
                    default:
                        System.out.println("\nUngueltige Eingabe\n");
                }
            }
        } finally {
            emf.close();
            em.close();
        }
    }
}
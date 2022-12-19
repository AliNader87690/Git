package com.example.WatchlistAppServer.controller;


import com.example.WatchlistAppServer.modul.*;
import com.example.WatchlistAppServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InitialController {

    @Autowired
    FilmService filmService;

    @Autowired
    private SerieService serieService;

    @Autowired
    private BenutzerService benutzerService;

    @Autowired
    private MediumInWatchlistService mediumInWatchlistService;

    @Autowired
    private WatchlistServiece watchlistServiece;

    @GetMapping("/")
    public void initialFilme() {
        filmService.addFilm(new Film(2021,"A mythic and emotionally charged hero’s journey, “Dune” tells the story of Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, who must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet’s exclusive supply of the most precious resource in existence—a commodity capable of unlocking humanity’s greatest potential—only those who can conquer their fear will survive.","Dune","8g18jFHCLXk",126,"https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg",8.1));
        filmService.addFilm(new Film(2021,"THE FRENCH DISPATCH brings to life a collection of stories from the final issue of an American magazine published in a fictional 20th-century French city. It stars Benicio del Toro, Adrien Brody, Tilda Swinton, Léa Seydoux, Frances McDormand, Timothée Chalamet, Lyna Khoudri, Jeffrey Wright, Mathieu Amalric, Stephen Park, Bill Murray and Owen Wilson.","the french dispatch","TcPk2p0Zaw4",147,"https://m.media-amazon.com/images/M/MV5BNmQxZTNiODYtNzBhYy00MzVlLWJlN2UtNTc4YWZjMDIwMmEzXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg",7.2));
        filmService.addFilm(new Film(2021,"he Eternals, a group of superhuman beings — each with a unique special power — re-emerge from living their ordinary lives to combat the rise of their evil counterparts, The Deviants. The Celestials, a group of beings even more ancient than the Eternals, created both the Eternals and the Deviants.","Eternals","pWfjJ6bOy7w",130,"https://th.bing.com/th/id/OIP.YP_DcecfMM2iJHJKUAtK6gHaLI?pid=ImgDet&rs=1",6.3));
        filmService.addFilm(new Film(2021,"Black Widow ist ein Science-Fiction-Actionfilm der Regisseurin Cate Shortland, welcher 2021 erschien. Es handelt sich um den 24. Film innerhalb des Marvel Cinematic Universe (MCU). Darin ist Scarlett Johansson als titelgebende Heldin Natasha Romanoff alias Black Widow zu sehen. Der Film kam am 9. Juli 2021 in die US-amerikanischen Kinos und gleichzeitig weltweit gebührenpflichtig ins Programm des Streaminganbieters Disney+. Der deutsche Kinostart war am 8. Juli 2021.","Black Widow","ybji16u608U",133,"https://th.bing.com/th/id/OIP.fEZhnIQrvgivcjGqFDzLDwHaLG?pid=ImgDet&rs=1",6.7));
        filmService.addFilm(new Film(2021,"Venom: Let There Be Carnage ist ein US-amerikanischer Science-Fiction-Actionfilm von Regisseur Andy Serkis, der am 1. Oktober 2021 in die US-amerikanischen und am 21. Oktober 2021 in die deutschen Kinos kam. Es handelt sich um eine Fortsetzung zu Venom (2018) und den zweiten Film aus Sony’s Spider-Man Universe. Die titelgebende Hauptrolle übernahm erneut Tom Hardy.","Venom","FmWuCgJmxo",90,"https://th.bing.com/th/id/R.150b7cdb04cc8d06a4cf8e6930cd5bc6?rik=gccQHOkuVz%2figQ&pid=ImgRaw&r=0",5.9));
        filmService.addFilm(new Film(2021,"Cinderella ist ein US-amerikanischer Musikfilm aus dem Jahr 2021. Er basiert auf dem Märchen Cendrillon des französischen Schriftstellers Charles Perrault und ist dabei eine moderne Version der traditionellen Geschichte. In der titelgebenden Hauptrolle ist Camila Cabello zu sehen, die somit ihr Schauspieldebüt gibt. Weitere Hauptrollen haben Idina Menzel, Minnie Driver, Nicholas Galitzine, Billy Porter und Pierce Brosnan inne. Regie führte Kay Cannon, die auch das Drehbuch schrieb.","Cinderella","T1NeHRuPpoM",125,"https://th.bing.com/th/id/R.a2024a332899325a638b0f6f8634101b?rik=IpV8MwvvY5P80g&pid=ImgRaw&r=0",4.2));
        filmService.addFilm(new Film(2021,"House of Gucci ist ein US-amerikanischer Kriminalfilm von Ridley Scott, der am 24. November 2021 in die US-amerikanischen und am 2. Dezember 2021 in die deutschen Kinos kam. Im Film übernahm Lady Gaga die Rolle der Patrizia Reggiani, die ihren Ex-Ehemann Maurizio Gucci und damaligen Chef des gleichnamigen Modehauses, gespielt von Adam Driver, ermorden ließ.","House of Gucci","pGi3Bgn7U5U",115,"https://th.bing.com/th/id/OIP.IPshnFitCyb_Yfa3duu45wHaJQ?pid=ImgDet&rs=1",6.6));
        filmService.addFilm(new Film(2022,"The Northman ist ein epischer Abenteuerfilm und Thriller von Robert Eggers, der im April 2022 in die Kinos kam. Der größtenteils Anfang des 10. Jahrhunderts spielende und um König Aurvandil und seinen Sohn Amleth aufgebaute Film nimmt Geschichten aus der Zeit der Wikinger und ihre Sagen auf.","The Northman","oMSdFM12hOw",120,"https://th.bing.com/th/id/OIP.zzyWFSLkCXBoMTj0xKt7WwHaK-?pid=ImgDet&rs=1",7.3));
        filmService.addFilm(new Film(2022,"The Adam Project is a time travel story that sees a fighter pilot from the year 2050 team up with his 12-year-old self and his late father on a mission to save the future. Netflix classifies The Adam Project in the sci-fi, family and action/adventure genres.","The Adam Project","IE8HIsIrq4o",100,"https://laverdadnoticias.com/__export/1610829112987/sites/laverdad/img/2021/01/16/the_adam_project_primeras_imaxgenes_de_la_esperada_pelixcula_de_netflix.jpg_2039590105.jpg",6.7));
        filmService.addFilm(new Film(2022,"Tod auf dem Nil (Originaltitel: Death on the Nile) ist ein US-amerikanischer Kriminalfilm, der am 10. Februar 2022 in die deutschen und am darauffolgenden Tag in die US-amerikanischen Kinos kam. Es handelt sich um die dritte Verfilmung des gleichnamigen Kriminalromans und nach Mord im Orient Express aus dem Jahr 2017 um die zweite Agatha-Christie-Verfilmung von Regisseur Kenneth Branagh, der gleichzeitig auch die Hauptrolle des Hercule Poirot übernahm.","Death on the Nile","dZRqB0JLizw",93,"https://th.bing.com/th/id/R.a1937ff78aa45e86c2f062db1b5f24ed?rik=GvS90F3wc6tZTQ&riu=http%3a%2f%2fimages2.eruditetechnologies.com.au%2foriginal%2f978%2f000%2f832%2f9780008328931.jpg&ehk=TZY8%2beiOCGZFI7xe3qfZPuv9HsVmIKMWs4LjgnGYy9w%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1",6.3));
        filmService.addFilm(new Film(2022,"Cyrano de Bergerac ist nicht nur seiner Zeit voraus, sondern auch gleichermaßen mit der Schreibfeder und dem Degen begabt. Aufgrund seiner äußeren Erscheinung ist der kleinwüchsige Cyrano jedoch davon überzeugt, dass die schöne Roxanne seine Liebe niemals erwidern wird. Er bringt es einfach nicht übers Herz, ihr seine Gefühle zu gestehen. Als sie ihm eines Tages anvertraut, dass sie sich in den gutaussehenden Kadetten Christian verliebt hat, hilft er ihm, Roxanne zu umwerben, indem er in seinem Namen emotionale Briefe an sie schreibt.","Cyrano","5e8apSFDXsQ",95,"https://th.bing.com/th/id/R.a95212fee1df735f60f415df4108a4f8?rik=pNd7pfwGsrroHA&pid=ImgRaw&r=0",6.3));
        filmService.addFilm(new Film(2022,"The Bubble ist eine US-amerikanische Filmkomödie von Judd Apatow aus dem Jahr 2022, die zusammen mit Pam Brady geschrieben wurde. Der Film verfügt über ein Ensemble, zu dem Karen Gillan, Iris Apatow, …","The Bubble","ZBD8X5zLG4U",85,"https://fr.web.img5.acsta.net/medias/nmedia/18/64/07/09/18772519.jpg",4.7));
        filmService.addFilm(new Film(2022,"The 355 ist ein US-amerikanischer Agentenfilm von Regisseur Simon Kinberg, der am 6. Januar 2022 in den deutschen und am darauffolgenden Tag in den US-amerikanischen Kinos anlief. In den Hauptrollen sind Jessica Chastain, Lupita Nyong’o, Penélope Cruz, Diane Kruger und Fan Bingbing zu sehen.","The 355","MyktpXSvjZE",90,"https://cps-static.rovicorp.com/2/Open/NBC_Universal/Program/43146602/_derived_jpg_q90_310x470_m0/The_355_NEW_VR_6_1642070814024_9.jpg",5.3));
        filmService.addFilm(new Film(2022,"Scream – Schrei! ist ein US-amerikanischer Horrorfilm von Regisseur Wes Craven aus dem Jahr 1996. Der Kinofilm ist eine Koproduktion von Dimension Films und Woods Entertainment. In der Schweiz erschien er unter dem Titel Scream! – Schrei des Todes.Scream zog als Auftakt der Scream-Filmreihe mit Scream 2 (1997), Scream 3 (2000) und Scream 4 (2011) drei kommerziell erfolgreiche Fortsetzungen nach sich. Von 2015 bis 2019 wurde zudem","Scream","beToTslH17s",90,"https://th.bing.com/th/id/OIP.UNlpAfgrgQSbuZCq9wb3aAHaLH?pid=ImgDet&rs=1",5.3));

        initialSerien();
        initialBenutzer();
        initialWatchlist();
    }

    public void initialSerien() {
        serieService.addSerie(new Serie(2022,"Die in New Jersey aufgewachsene Kamala Khan erfährt, dass sie polymorphe Kräfte hat."
                ,"Ms. Marvel","m9EX0f6V11Y",50,           "https://wwwflickeringmythc3c8f7.zapwp.com/q:i/r:1/wp:1/w:362/u:https://cdn.flickeringmyth.com/wp-content/uploads/2022/05/Ms.-Marvel-character-posters-1-600x889.jpg",6.1,1,6));



        serieService.addSerie(new Serie(2022,"Als ein junger Mann kurz nach dem Tod seines Freundes verschwindet, gerät das Leben in einem wohlhabenden Warschauer Vorort langsam aus den Fugen und bringt Geheimnisse und Lügen ans Licht."
                ,"Sie sehen dich","2AZlM6RjSxk",50,     "https://assets.cdn.moviepilot.de/files/09cf8dae41a4ac4db9a447dd72dde9627fdcf032f2f520b62b36bbe41770/limit/500/1000/624aa5c1ac8c4.jpg"
                ,6.1,1,6));



        serieService.addSerie(new Serie(2022," Eine dramatische Nacherzählung des Fresh Prince of Bel-Air"
                ,"Bel-Air","rQ1uG91Bbls",51,     "https://de.web.img3.acsta.net/pictures/22/01/27/09/59/0452386.jpg"
                ,6.0,2,10));

        serieService.addSerie(new Serie(2022," Marc Spector ist ein ehemaliger CIA-Agent, dessen Leben durch den Mondgott Khonshu gerettet wird."
                ,"Moon Knight","x7Krla_UxRg",51,     "https://terrigen-cdn-dev.marvel.com/content/prod/1x/cia.dm399_second_poster_static_9x16_dated.jpg"
                ,7.4,1,6));


        serieService.addSerie(new Serie(2022,"Eine Gruppe junger Erwachsener besucht eine Party auf einer abgelegenen Insel, doch das verlockende Paradies, das sie dort erwartet, birgt gefährliche Geheimnisse und Fallen."
                ,"Welcome to Eden","__JMu36vrV4",40,     "https://bingeddata.s3.amazonaws.com/uploads/2022/05/kZO0mtTHAgg9QVscLVtwCcQ7Hgx.jpg"
                ,5.4,1,9));

        serieService.addSerie(new Serie(2022,"Jack Reacher wurde wegen Mordes verhaftet und nun braucht die Polizei seine Hilfe. Basierend auf den Büchern von Lee Child."
                ,"Reacher","GSycMV-_Csw",49,     "https://de.web.img3.acsta.net/r_1280_720/pictures/21/12/03/10/38/4833003.jpg"
                ,8.1,1,9));


        serieService.addSerie(new Serie(2022,"In dieser Serie, die auf den Bestseller-Romanen von Michael Connelly basiert, führt ein ikonoklastischer Idealist seine Anwaltskanzlei auf dem Rücksitz seines Lincoln Town Car."
                ,"The Lincoln Lawyer","au06yHMuMGc",60,     "https://assets.cdn.moviepilot.de/files/9d717b4599f010ac5e59992cf36def054f08acc0d0e283abac122d8961c8/limit/500/1000/lincoln_lawyer_xlg.jpg"
                ,7.7,1,11));


        serieService.addSerie(new Serie(2022,"Eine Gruppe genialer Schüler begibt sich auf eine lebensverändernde Reise und will im Kampf gegen eine Armee skrupelloser Drohnen das Überleben der Menschheit sichern."
                ,"The Last Bus","bi3csDVuLqQ",35,     "https://de.web.img3.acsta.net/pictures/22/03/29/14/15/0401344.jpg"
                ,5.0,1,10));

        serieService.addSerie(new Serie(2022,"Vier jugendliche Freunde an der französisch-schweizerischen Grenze, deren Leben durch ein Experiment des LHC, des größten Teilchenbeschleunigers der Welt, auf den Kopf gestellt wird."
                ,"Parallel Worlds","vVcqckxQPrY",45,     "https://assets.cdn.moviepilot.de/files/97c91b30afa777ebbee683ddd9dd0b960762e4c5641c737c910a163ea2ac/limit/500/1000/FNojyX9VQAE0kQ_.jpeg"
                ,7.0,1,6));






//2021

        serieService.addSerie(new Serie(2021,"Der quecksilbrige Schurke Loki nimmt seine Rolle als Gott des Unheils in einer neuen Serie wieder auf, die nach den Ereignissen von Avengers spielt: Endgame spielt."
                ,"Loki","nW948Va",51, "https://static.posters.cz/image/1300/poster/loki-glorious-purpose-i112432.jpg",8.2,1,12));


        serieService.addSerie(new Serie(2021,"Mischt den Stil klassischer Sitcoms mit dem MCU, in dem Wanda Maximoff und Vision - zwei Wesen mit Superkräften, die ihr ideales Vorstadtleben führen - zu ahnen beginnen, dass nicht alles so ist, wie es scheint."
                ,"WandaVision","sj9J2ecsSpo",40, "https://static.posters.cz/image/750/poster/wandavision-reality-rift-i106807.jpg" , 7.9,1,9));



        serieService.addSerie(new Serie(2021,"Serie basierend auf dem Marvel Comics Superhelden Hawkeye, der sich auf die Abenteuer von Young Avenger, Kate Bishop, konzentriert, die die Rolle nach dem ursprünglichen Rächer Clint Barton übernahm."
                ,"Hawkeye","5VYb3B1ETlk",60, "https://i.etsystatic.com/15527708/r/il/eba2d7/3627558326/il_570xN.3627558326_8ger.jpg" , 7.6,1,6));


        serieService.addSerie(new Serie(2021,"Erforscht zentrale Momente aus dem Marvel Cinematic Universe und stellt sie auf den Kopf, um das Publikum in unerforschtes Gebiet zu führen."
                ,"What If..?","x9D0uUKJ5KI",31, "https://terrigen-cdn-dev.marvel.com/content/prod/1x/online_2_0.jpg" , 7.4,1,9));




        serieService.addSerie(new Serie(2021,"Ein Mann und eine Frau, die erschrocken feststellen, dass sie Nachbarn sind und sich einen Psychiater teilen, finden es unmöglich, einander aus dem Weg zu gehen."
                ,"Mad For Each Other","6dC8wO0US90",30, "https://i.pinimg.com/originals/38/68/a7/3868a765bfc9dc729ba2c59116a679e1.jpg" , 7.9,1,13));



        serieService.addSerie(new Serie(2021,"Die scharfe, witzige und rätselhafte DI Annika Strandhed leitet eine neue Spezialeinheit der Marine-Mordkommission, die mit der Untersuchung ungeklärter, brutaler und scheinbar unergründlicher Morde beauftragt ist."
                ,"Annika","fMJjQx4MaoI",40, "https://www.themoviedb.org/t/p/original/8jRoGLyOnTMyLhWajG0ye6KKw0h.jpg" , 6.9,1,6));


        serieService.addSerie(new Serie(2021," Die Serie Die Schlange nach wahren Begebenheiten erzählt die Geschichte des Serienbetrügers Charles Sobhraj und von den außerordentlichen Anstrengungen, ihm das Handwerk zu legen."
                ,"The Serpent"," TgB7rMuxY-s",50, "https://m.media-amazon.com/images/M/MV5BZTdmOTE0YzEtMDgxOS00NzBiLTllMDktMDU4NGZlZTk3YWJjXkEyXkFqcGdeQXVyNjQ2MjQ5NzM@._V1_.jpg" , 7.6,1,8));



        serieService.addSerie(new Serie(2021,"Ein Junge, der halb Mensch und halb Hirsch ist, überlebt in einer post-apokalyptischen Welt mit anderen Hybriden." ,"Sweet Tooth","EeGmYe-duFQ",50, "https://de.web.img3.acsta.net/pictures/21/05/17/17/26/5007043.jpg" , 7.8,1,16));


        serieService.addSerie(new Serie(2021,"Ein Jahr nach dem ersten Ausbruch des subglazialen Vulkans Katla in Island sucht Gríma noch immer nach ihrer vermissten Schwester, die an dem Tag verschwand, als die ständigen Eruptionen begannen." ,"Katla","X9zZl5nov68",40, "https://m.media-amazon.com/images/M/MV5BNGIyYWIzODQtMzU5Ni00YmIxLTk0YzAtOTg3ZWEwNjQ4OTQ4XkEyXkFqcGdeQXVyMTEzMTI1Mjk3._V1_FMjpg_UX1000_.jpg" , 7.0,1,8));


    }

    public void initialBenutzer() {
        Benutzer b = new Benutzer("12345","Somar","Y","somar");
        benutzerService.addBenutzer(b);

    }

    public void initialWatchlist() {
        Benutzer b = benutzerService.getAll().get(0);
        Watchlist watchlist = new Watchlist("Action", b);
        watchlistServiece.addWatchlist(watchlist);
        List<Film> f = filmService.getAll();
        for (Film k : f) {
            MediumInWatchlist m = new MediumInWatchlist("Geplant", "Film", 0, watchlist, k.getId());
            mediumInWatchlistService.addMInWatchlist(m);

        }

        watchlistServiece.addWatchlist(watchlist);

    }
}

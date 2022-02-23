function Bild(url,name)
   { 
       this.url=url;
       this.name=name;
   }

   function Kategorie(url,bildname,name){
       this.bild=new Bild(url,bildname); // Komposition 
       this.name=name;
       
   }

   class Tutorial {
    constructor(burl, bildname, name, sprache, beschreibung, dauer, datum, url, embedcode) {
        this.bild = new Bild(burl, bildname);
        this.name = name;
        this.sprache = sprache;
        this.beschreibung = beschreibung;
        this.dauer = dauer;
        this.datum = new Date(datum);
        this.url = url;
        this.embedcode = embedcode;
        this.kategorien = new Array();
        this.kapitallist = new Array();
        this.fuegekategoreihinzu = function (kategorie) {
            this.kategorien.push(kategorie);
        };
        this.fuegekapitalhinzu = function (kapital) {
            this.kapitallist.push(kapital);
        };
    }
}

   function kapital(name,beschreibung,dauer){
       this.name=name;
       this.beschreibung=beschreibung;
       this.dauer=dauer;
   }
   function getDauerInStundenUndMinuten(dauer){
    var fields=dauer.split(":");
    return `${fields[0]}h ${fields[1]}min`;
   }
   
   let kategorie1=new Kategorie("Img/Web_Engenering.jfif","Web-Engineering","Web-Engineering");
   let kategorie2=new Kategorie("Img/app-desiagn.jpg","App-Design","App-Design");
   let kategorie3=new Kategorie("Img/software-des.jpeg","Software-Design","Software-Design");
   let kategorie4=new Kategorie("Img/fdlkdfkjdlkslkjsfjkfjsfjksfskj.png","c++","c++");
   let Kategorien=[kategorie1,kategorie2,kategorie3,kategorie4];

   
   let t1=new Tutorial("Img/Design.jpg","Design","Design","Deutsch","Erste Schritte in Design Welt","05:30","2016-09-16","","Iw3FjQ-qxt8");
   let t2=new Tutorial("Img/Programmierung.png","Programmierung","Programmierung","Deutsch","Learn how to create ur own Webseite","10:12","2020-11-01","https://www.programmierenlernen24.de/c-programmieren-lernen/","");
   let Tutorials=[t1,t2];

   let kapital1=new kapital("C++ Grundlagen","Die Grundlagen","04:12");
   let kapital2=new kapital("C# oop","die Beziehungen zwschien den Klassen","03:15");
   let kapital3=new kapital("Css","A_Z","15:32");

   let kapital4=new kapital("Modellierung Schritte","Modellierung Schritte","04:12");
   let kapital5=new kapital("UML Diagramme lesen können","UML Diagramme lesen können","04:12");
   let kapital6=new kapital("Werbe Aktionen zur App einfügen","Werbe Aktionen zur App einfügen","04:12");
   
   t1.fuegekapitalhinzu(kapital4);
   t1.fuegekapitalhinzu(kapital5);
   t1.fuegekapitalhinzu(kapital6);
    
   t1.fuegekategoreihinzu(kategorie2);
   t1.fuegekategoreihinzu(kategorie3);

   t2.fuegekapitalhinzu(kapital1);
   t2.fuegekapitalhinzu(kapital2);
   t2.fuegekapitalhinzu(kapital3);

   t2.fuegekategoreihinzu(kategorie1);
   t2.fuegekategoreihinzu(kategorie4);


   t2.kategorien.sort(function(a,b){
       return a.name-b.name;
   });

   function gettutoiralsalsKategorie(kname){
    let erg=[];
    for(let t of Tutorials){
        for(let k of t.kategorien){
            if(k.name===kname){
                erg.push(t);
                break;
            }
        }
    }
    return erg;
   }

   function gettutorialbeiname(tname){
       let erg;
       for(let i of Tutorials){
         if(tname === i.name){
             erg=i;
             break
         }
       }
       return erg;
   }

   module.exports= {
    Tutorial,
    tutorials: Tutorials,
    kategorien: Kategorien,
    getDauerInStundenUndMinuten: getDauerInStundenUndMinuten,
    gettutoiralsalsKategorie: gettutoiralsalsKategorie,
    gettutorialbeiname: gettutorialbeiname
   };
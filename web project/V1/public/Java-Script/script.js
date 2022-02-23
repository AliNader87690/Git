// function getViewportWidth() {
//     return window.innerWidth || 
//     document.documentElement.clientWidth;
//    }
//    console.log(`Die Viewport-Bereite beträgt: ${getViewportWidth()} Pixel`);

   function Bild(url,name)
   { 
       this.url=url;
       this.name=name;
   }

   function Kategorie(url,bildname,name){
       this.bild=new Bild(url,bildname); // Komposition 
       this.name=name;
       
   }

   function Tutorial(url,bildname,name,sprache,beschreibung,dauer,datum,url,embedcode){
       this.bild=new Bild(url,bildname);
       this.name=name;
       this.sprache=sprache;
       this.beschreibung=beschreibung;
       this.dauer=dauer;
       this.datum=new Date(datum);
       this.url=url;
       this.embedcode=embedcode;
       this.kategorien=new Array();
       this.kapitallist=new Array();
       this.fuegekategoreihinzu= function(kategorie){
           this.kategorien.push(kategorie);
       }
       this.fuegekapitalhinzu= function(kapital){
           this.kapitallist.push(kapital);
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
   
   let kategorie1=new Kategorie("https://www.google.com/search?q=web-engineering&rlz=1C1SQJL_deDE870DE870&sxsrf=AOaemvKIvzKOqO8EmjyhgQu3bKZG0_UsUw:1642546803450&source=lnms&tbm=isch&sa=X&sqi=2&ved=2ahUKEwia6PvVs7z1AhVn7rsIHYtdAhYQ_AUoAXoECAIQAw&biw=1536&bih=750&dpr=1.25#imgrc=4-H94FDw4lGtoM","Web-Engineering","Web-Engineering");
   let kategorie2=new Kategorie("https://www.google.com/search?q=app+design&tbm=isch&ved=2ahUKEwiVrYPXs7z1AhXyZ_EDHb9kDe8Q2-cCegQIABAA&oq=app&gs_lcp=CgNpbWcQARgAMgcIIxDvAxAnMgUIABCABDIECAAQQzIECAAQQzIECAAQQzIFCAAQgAQyBAgAEEMyBAgAEEMyBAgAEEMyBQgAEIAEOgYIABAHEB46BAgAEB5QqQRY8wpggRpoAHAAeACAAVSIAZ8CkgEBNJgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=dUbnYdWeKPLPxc8Pv8m1-A4&bih=750&biw=1536&rlz=1C1SQJL_deDE870DE870#imgrc=JlXb-ak7fDIppM","App-Design","App-Design");
   let kategorie3=new Kategorie("https://www.google.com/search?q=software+design&tbm=isch&ved=2ahUKEwi-4evks7z1AhWB1uAKHeVECUwQ2-cCegQIABAA&oq=soft&gs_lcp=CgNpbWcQARgAMgQIABBDMgQIABBDMgQIABBDMgUIABCABDIFCAAQgAQyBAgAEEMyBAgAEEMyBAgAEEMyBQgAEIAEMgUIABCABDoHCCMQ7wMQJ1CrK1iPL2CDPGgAcAB4AIABTYgB1QKSAQE1mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=kkbnYb7QJoGtgwfliaXgBA&bih=750&biw=1536&rlz=1C1SQJL_deDE870DE870#imgrc=p4Wysw_plcqOFM","Software-Design","Software-Design,");
   let kategorie4=new Kategorie("https://www.google.com/search?q=c%2B%2B%2Fc%23&tbm=isch&ved=2ahUKEwjKkr-DtLz1AhXrDmMBHZzNCggQ2-cCegQIABAA&oq=C%2B%2B&gs_lcp=CgNpbWcQARgAMgcIIxDvAxAnMgcIIxDvAxAnMgQIABBDMgUIABCABDIFCAAQgAQyBAgAEEMyBAgAEEMyBAgAEEMyBQgAEIAEMgUIABCABDoKCCMQ7wMQ6gIQJ1DMC1j6TWDpWWgDcAB4AIABSYgB2wOSAQE3mAEAoAEBqgELZ3dzLXdpei1pbWewAQrAAQE&sclient=img&ei=0kbnYcrhN-udjLsPnJurQA&bih=750&biw=1536&rlz=1C1SQJL_deDE870DE870#imgrc=e8nU03QLzzcVJM","c++/c#","c++/c#");
   let Kategorien=[kategorie1,kategorie2,kategorie3,kategorie4];

   
   let t1=new Tutorial("https://www.google.com/search?q=Design&tbm=isch&ved=2ahUKEwiEx8iYtLz1AhXLCWMBHal8BtQQ2-cCegQIABAA&oq=Design&gs_lcp=CgNpbWcQAzIECAAQQzIECAAQQzIFCAAQgAQyBQgAEIAEMgUIABCABDIECAAQQzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQ6BggAEAcQHjoECAAQHjoGCAAQCBAeOgcIIxDvAxAnULcGWKorYO42aABwAHgAgAFNiAHAA5IBATeYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=_0bnYcTLBsuTjLsPqfmZoA0&bih=750&biw=1536&rlz=1C1SQJL_deDE870DE870#imgrc=FR4UmzXsWEhtIM","Design","Design","Deutsch","Erste Schritte in Design Welt","05:30","2016-09-16","","#c12314");
   let t2=new Tutorial("https://www.google.com/search?q=programming&tbm=isch&ved=2ahUKEwih6JPMtLz1AhWgZ_EDHVaRBT4Q2-cCegQIABAA&oq=progr&gs_lcp=CgNpbWcQARgAMgQIABBDMgQIABBDMgQIABBDMgUIABCABDIECAAQQzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgcIIxDvAxAnULMoWJ4vYNw3aABwAHgAgAFEiAH0ApIBATaYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=a0fnYeGGEqDPxc8P1qKW8AM&bih=750&biw=1536&rlz=1C1SQJL_deDE870DE870#imgrc=4QD3QxvapwSrnM","Programmierung","Programmierung","Deutsch","Learn how to create ur own Webseite","10:12","2020-11-01","","c#3131");
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
    for(let i=0;i<Tutorials.length;i++){
        for(let k=0;i<Tutorials[i].kategorien.length;k++){
            if(Tutorials[i].kategorien[k].name==kname){
                erg.push(Tutorials[i]);
                break;
            }
        }
    }
    return erg;
   }
   let ausgabe=gettutoiralsalsKategorie("Web-Engineering");
//    console.log(ausgabe);
//    console.log(getDauerInStundenUndMinuten("20:13"));

  
const http = require("http");
const data = require("./models/persistence");
const url = require("url");
const express = require("express");
let erg = [];
let tutorialname;
const server = http.createServer(function (request, response) {
    if (request.url !== "/") {
        tutorialname = url.parse(request.url, true).query.search;
        erg = [];
        for (let i = 0; i < data.tutorials.length; i++) {
            if (data.tutorials[i].name.includes(tutorialname)) {
                erg.push(`${data.tutorials[i].name}:(${data.tutorials[i].datum})`);
            }
        }
        if (erg.length > 0) {
            send(response, treffergefunden());
        } else {
            send(response, treffernichtgefunden());
        }
    }
});
server.listen(8844, function () {
    console.log("ich lausche auf http://localhost:8844");
})

function send(response, body) {
    response.writeHead(200, { "content-type": "text/html; charset=utf-8" });
    response.end(body);
}

function treffergefunden() {
    return `<!DOCTYPE html>
       <html lang="de">
      
       <head>
          <meta charset="utf-8">
          <title>Liste</title>
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
           <link rel=stylesheet type="text/css" href="http://localhost:8020/css/style.css">
           <link rel=stylesheet type="text/css" href="http://localhost:8020/css/flexbox.css">
           <link rel=stylesheet type="text/css" href="http://localhost:8020/css/tiles.css">
      </head>
      
      <body>
          <header>
              <img src="http://localhost:8020/Img/fa2dd0082f184fc4a556b6d21c2dec75.jpg" alt="Logo (50%)" height="50" width="100">
              <h1>Your Way to get better</h1>
          </header>
      
          <nav>
              <div id="nav">
                  <Strong><a href="http://localhost:8020">Liste der Kategorien</a></Strong>
                  <strong>|<a href="http://localhost:8020/form">neues Tutorial hinzufügen</a></strong>
              </div>
          </nav>
          <div id="haupt">
          <main id="mainlist">
          <p>Tutorials mit: ${tutorialname}</p>
          <ul>
          ${ArrayinListitems()}
          </ul>
          </main>
          <aside>
              <dl>
                  <dt><strong>Neue Tutorials</strong></dt>
                  <dd>
                      <ul>
                          <li>HTML ,Einstieg .Dauer: 2 Std 30 Min .Datum: <time datetime="2021-10-15">15-10-2021</time></li>
                          <li>JAVA ,Grundkurs .Dauer: 2 Std 45 Min .Datum: <time datetime="2021-09-15">15-09-2021</time></li>
                      </ul>
                  </dd>
              </dl>
          </aside>
          </div>
          <footer>
              <hr>
              <small>&copy;2021 MMA-Team</small>
          </footer>
      </body>
      </html>`;
}

function treffernichtgefunden() {
    return `<!DOCTYPE html>
        <html lang="de">
       
        <head>
        <meta charset="utf-8">
        <title>Liste</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <link rel=stylesheet type="text/css" href="http://localhost:8020/css/style.css">
           <link rel=stylesheet type="text/css" href="http://localhost:8020/css/flexbox.css">
           <link rel=stylesheet type="text/css" href="http://localhost:8020/css/tiles.css">
       </head>
    
    <body>
        <header>
            <img src="http://localhost:8020/Img/fa2dd0082f184fc4a556b6d21c2dec75.jpg" alt="Logo (50%)" height="50" width="100">
            <h1>Your Way to get better</h1>
        </header>
    
        <nav>
            <div id="nav">
            <Strong><a href="http://localhost:8020">Liste der Kategorien</a></Strong>
            <strong>|<a href="http://localhost:8020/form">neues Tutorial hinzufügen</a></strong>
            </div>
        </nav>
           <div id="haupt">
           <main id="mainlist">
           <p>Tutorials mit: ${tutorialname}</p>
           <p>Keine Tutorials gefunden!</p>
           </main>
           <aside>
               <dl>
                   <dt><strong>Neue Tutorials</strong></dt>
                   <dd>
                       <ul>
                           <li>HTML ,Einstieg .Dauer: 2 Std 30 Min .Datum: <time datetime="2021-10-15">15-10-2021</time></li>
                           <li>JAVA ,Grundkurs .Dauer: 2 Std 45 Min .Datum: <time datetime="2021-09-15">15-09-2021</time></li>
                       </ul>
                   </dd>
               </dl>
           </aside>
           </div>
           <footer>
               <hr>
               <small>&copy;2021 MMA-Team</small>
           </footer>
       </body>
       </html>`;
}
function ArrayinListitems() {
    let e = " ";
    for (let i = 0; i < erg.length; i++) {
        e += `<li><a href="http://localhost:8020/Tutorial?name=${erg[i].split(":")[0]}">${erg[i]})</a></li>`
    }
    return e;
}


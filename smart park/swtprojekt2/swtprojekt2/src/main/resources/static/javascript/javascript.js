if([[${geschlossen}]]==true){
    document.getElementById("schranke").setAttribute("src","[[@{/Img/schrankerunter.png}]]");
}else{
    document.getElementById("schranke").setAttribute("src","[[@{/Img/schrankeauf.png}]]");
}

if([[${s1.belegt}]]==true){
    document.getElementById("p1").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p1").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s2.belegt}]]==true){
    document.getElementById("p2").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p2").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s3.belegt}]]==true){
    document.getElementById("p3").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p3").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s4.belegt}]]==true){
    document.getElementById("p4").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p4").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s5.belegt}]]==true){
    document.getElementById("p5").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p5").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s6.belegt}]]==true){
    document.getElementById("p6").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p6").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s7.belegt}]]==true){
    document.getElementById("p7").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p7").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s8.belegt}]]==true){
    document.getElementById("p8").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p8").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s9.belegt}]]==true){
    document.getElementById("p9").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p9").setAttribute("src","[[@{/Img/Park.png}]]");
}

if([[${s10.belegt}]]==true){
    document.getElementById("p10").setAttribute("src","[[@{/Img/car.png}]]");
}else{
    document.getElementById("p10").setAttribute("src","[[@{/Img/Park.png}]]");
}


if([[${sw15.co2wert}]] < 1000 && [[${sw15.tempratur}]] < 30){
    document.getElementById("not1").setAttribute("src","[[@{/Img/notazsganggreen.png}]]");
}else{
    document.getElementById("not1").setAttribute("src","[[@{/Img/notausgangred.png}]]");
}

if([[${sw16.co2wert}]] < 1000 && [[${sw16.tempratur}]] < 30){
    document.getElementById("not2").setAttribute("src","[[@{/Img/notazsganggreen.png}]]");
}else{
    document.getElementById("not2").setAttribute("src","[[@{/Img/notausgangred.png}]]");
}

if([[${sw17.co2wert}]] < 1000 && [[${sw17.tempratur}]] < 30){
    document.getElementById("not3").setAttribute("src","[[@{/Img/notazsganggreen.png}]]");
}else{
    document.getElementById("not3").setAttribute("src","[[@{/Img/notausgangred.png}]]");
}

if([[${sw18.co2wert}]] < 1000 && [[${sw18.tempratur}]] < 30){
    document.getElementById("not4").setAttribute("src","[[@{/Img/notazsganggreen.png}]]");
}else{
    document.getElementById("not4").setAttribute("src","[[@{/Img/notausgangred.png}]]");
}

setInterval(function (){
    window.location="http://localhost:8080/Benutzerseite";
},10000);
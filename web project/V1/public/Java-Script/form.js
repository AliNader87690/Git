
 let labelurl=document.createElement("label");
 labelurl.textContent="Wert ";
 labelurl.className="req";
 let input=document.createElement("input");
 input.type="url";
 input.name="content";
 labelurl.append(input);
 let time=document.querySelector("#timelabel");


let labeltextfeld=document.createElement("label");
labeltextfeld.textContent="Wert ";
labeltextfeld.className="req";
let textfeld=document.createElement("textarea");
textfeld.name="content";
textfeld.cols=10;
textfeld.rows=5;
textfeld.maxLength=100;
labeltextfeld.append(textfeld);


document.querySelector("#text").addEventListener('change', function() {
    time.className=" ";
    labeltextfeld.remove();
    document.querySelector("#inhalt").append(labelurl);
    document.querySelector("#time").removeAttribute("required");
});


document.querySelector("#video").addEventListener('change', function() {
    time.className="req";
     labelurl.remove();
     document.querySelector("#time").setAttribute("required",true);
     document.querySelector("#inhalt").append(labeltextfeld);
});



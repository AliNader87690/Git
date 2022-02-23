let menu = document.createElement("p");
menu.textContent = "☰";
menu.id = "menu";

let nav = document.getElementById("nav");

// die Alte Version old-School
// function media1()
// {
//     if(window.innerWidth <= 480)
//     {
//         nav.remove();
//         document.querySelector("nav").prepend(menu);
//         menu.addEventListener("click", function() {
//             document.querySelector("nav").append(nav);
//             nav.style.display="flex";
//             nav.style.flexDirection= "column";
//         })
//         // menu.addEventListener("click ", function() {
//         //     // nav.style.display="flex";
//         //     nav.style.flexDirection= "column";
//         //     // document.querySelector("nav").append(nav);
//         // })
//     }
//     else 
//     {
//         menu.remove();
//         document.querySelector("nav").prepend(nav);
//         nav.style.flexDirection= "row";
//     }
// }
// window.addEventListener("resize", media1);
var clicks = 0;
const mediaquerey = window.matchMedia('(max-width: 480px)');
function handleTabletChange(e) {
    if (e.matches) {
        nav.remove();
        document.querySelector("nav").prepend(menu);
    }
    else {
        clicks = 0;
        menu.remove();
        document.querySelector("nav").prepend(nav);
        nav.style.flexDirection = "row";
    }
}
mediaquerey.addListener(handleTabletChange); // addEventListener('change', handleTabletChange);
handleTabletChange(mediaquerey);

menu.addEventListener("click", function () {
    clicks++;
    if (clicks % 2 != 0) {
        document.querySelector("nav").append(nav);
        nav.style.display = "flex";
        nav.style.flexDirection = "column";
    } else {
        nav.remove();
    }
});
const express = require("express");
const router = express.Router();
const data = require("../models/persistence");
router.get("/", function (req, res, next) {  // Liste der Kategorien
    res.render("List", { data: data });
});
router.use("/Tutorials", function (req, res) { // tutorials
    let erg= data.gettutoiralsalsKategorie(req.query.category);
    res.render("Tutorials", {tlist: erg, get:data.getDauerInStundenUndMinuten});
    
   
});

router.use("/Tutorial", function (req, res) { // tutorail
    let tutname = data.gettutorialbeiname(req.query.name);
    if(tutname !== undefined){
    res.render("Tutorial", { tut: tutname, getdauer: data.getDauerInStundenUndMinuten });
    }else{
        res.status(404).render("Fehler.ejs");
    }
});

router.get("/form", function (req, res) {  // form
    res.render("form");
});

router.post("/", function (req, res) {
    let tutorial;
    if (req.body.type === "text") {
        if(req.query.duration){
        tutorial = new data.Tutorial("", "", req.body.name, req.body.language, req.body.description, req.body.duration, req.body.date, req.body.content, "");
    }else{
        tutorial = new data.Tutorial("", "", req.body.name, req.body.language, req.body.description, "00:00", req.body.date, req.body.content, "");
    }
    } else {
        tutorial = new data.Tutorial("", "", req.body.name, req.body.language, req.body.description, req.body.duration, req.body.date, "", req.body.content);
    }
    let categorien = req.body.categories.split(",");
    for (let kp of categorien) {
        for (let k of data.kategorien) {
            if (kp == k.name) {
                tutorial.fuegekategoreihinzu(k);
            }
        }
    }
    data.tutorials.push(tutorial);

    res.redirect("/");
})
router.use(function (req, res) {
    res.status(404).render("Fehler.ejs");
})



module.exports = router;
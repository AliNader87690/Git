const express=require("express");
const router=require("./routes/router");
const app=express();
app.use(express.urlencoded({extended: false}));
app.set("view engine","ejs");
app.set("views","Views");
app.use(express.static('public'));
app.use(router);
app.listen(8020, function() {console.log("Server is Listening on http://localhost:8020");});

package com.example.WatchlistAppServer;


import com.example.WatchlistAppServer.service.BenutzerService;
import com.example.WatchlistAppServer.service.ProfilService;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WatchlistAppServerApplicationTests {

@Autowired
	ProfilService profilService;

	@Autowired
	BenutzerService benutzerService;

	@Test
	public void addBenutzerTest(){
		//Benutzer benutzer=new Benutzer("0123","Ali","Nader","an44835@gmail.com",false,new Date(1994,01,01),"alnad001");
		//benutzerService.addBenutzer(benutzer);
	}


	@Test
	public void addProfilTest(){
       // Profil profil=new Benutzer("0123","Ali","Nader","an44835@gmail.com",false,new Date(1994,01,01),"alnad001");
		//profilService.addProfil(profil);
	}

}

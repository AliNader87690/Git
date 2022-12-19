package com.example.swtprojekt2.service;
import com.example.swtprojekt2.model.Benutzer;
import com.example.swtprojekt2.model.Student;
import com.example.swtprojekt2.repository.BenutzerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Data
public class BenutzerService {
    @Autowired
    BenutzerRepository brepo;
    public boolean gefunden(String fhkennung,String fhpasswort){
        List<Benutzer> erg=brepo.findAll();
        for (Benutzer benutzer:erg){
            if(benutzer.getFhKennung().equals(fhkennung) && benutzer.getFhPasswort().equals(fhpasswort))
                return true;
        }
        return false;
    }
    public void benutzerInitialisieren(){
        brepo.saveAll(new ArrayList<>(Arrays.asList(
                new Student("Moamen","Alkatib","moalk005@stud.fh-dortmund.de",new Date(1996,9,16),"moalk","moamen"),
                new Student("Moutasem","Sakbani","moutasem.sakbani@gmail.com",new Date(1998,5,15),"mosak002","mout1111"),
                new Student("Somar","Youssef","Somar.yooo@gmail.com",new Date(1998,4,30),"somosomo","somo1111"),
                new Student("Ali","Nader","an44835@gmail.com",new Date(1993,7,21),"alnad001","012345")
        )));
    }
}

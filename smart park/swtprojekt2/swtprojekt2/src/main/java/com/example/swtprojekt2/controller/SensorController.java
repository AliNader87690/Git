package com.example.swtprojekt2.controller;
import com.example.swtprojekt2.model.Aktor;
import com.example.swtprojekt2.model.Naehrungsensor;
import com.example.swtprojekt2.model.Tuersensor;
import com.example.swtprojekt2.model.Waermesensor;
import com.example.swtprojekt2.repository.SensorPerformer;
import com.example.swtprojekt2.service.AktorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SensorController {
    @Autowired
    AktorService aktorService;

    @GetMapping("/initialSensoren")
    public RedirectView initialSensoren(){
        aktorService.aktorenInitialisieren();
        return new RedirectView("/initialBenutzer");
    }
    @GetMapping("/Benutzerseite")
    public ModelAndView karte(@RequestParam boolean angemeldet) {
      if(angemeldet){
          ModelAndView mav = new ModelAndView("karte");
          mav.addObject("geschlossen", aktorService.getAktorRepositiory().getById(14l).checkStatus(() -> {
              Tuersensor ts= (Tuersensor) aktorService.getAktorRepositiory().getById(14l).getSensor();
              return ts.isGeschlossen();
          }));
          Long i;
          for (i = 1l; i <= 10; i++) {
              Long finalI = i;
              mav.addObject("belegt" + Long.toString(i), aktorService.getAktorRepositiory().getById(i).checkStatus(() -> {
                  Naehrungsensor ns=(Naehrungsensor) aktorService.getAktorRepositiory().getById(finalI).getSensor();
                  return ns.isBelegt();
              }));
          }
          for (i = 15l; i <= 18; i++) {
              Long finalI1 = i;
              mav.addObject("unterKontrolle" + Long.toString(i) , aktorService.getAktorRepositiory().getById(i).checkStatus(() -> {
                  Waermesensor ws=(Waermesensor) aktorService.getAktorRepositiory().getById(finalI1).getSensor();
                  System.out.println(ws.getCo2wert()+"  && "+ ws.getTempratur());
                  return !(ws.getCo2wert()<=30 && ws.getTempratur()<=1000);
              }));
          }
          return mav;
      }else
          return new ModelAndView("redirect:home");
    }
    @GetMapping("/tueroefnnen")
    public RedirectView tueroeffnen(){
        aktorService.getAktorRepositiory().getById(14l).doAktion(new SensorPerformer() {
            @Override
            public void changeValue() {
                Aktor tueraktor=aktorService.getAktorRepositiory().getById(14l);
                Tuersensor ts=(Tuersensor) tueraktor.getSensor();
                ts.setGeschlossen(!ts.isGeschlossen());
                aktorService.getAktorRepositiory().save(tueraktor);
            }
        });
      return new RedirectView("/Benutzerseite?angemeldet=true");
    }


    @GetMapping("/tuerschliessen")
    public RedirectView tuerschliessen(){
        aktorService.getAktorRepositiory().getById(14l).doAktion(new SensorPerformer() {
            @Override
            public void changeValue() {
                Aktor tueraktor=aktorService.getAktorRepositiory().getById(14l);
                Tuersensor ts=(Tuersensor) tueraktor.getSensor();
                ts.setGeschlossen(!ts.isGeschlossen());
                aktorService.getAktorRepositiory().save(tueraktor);
            }
        });
        return new RedirectView("/Benutzerseite?angemeldet=true");
    }
}

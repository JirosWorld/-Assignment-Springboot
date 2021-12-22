package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TelevisionsController {

    //attribuut
    private List<String> televisionsets = new ArrayList<>();

    //constructor
    public TelevisionsController(List<String> televisionsets) {
        this.televisionsets = televisionsets;
        televisionsets.add("Sony");
        televisionsets.add("Marktplaats beunhaas");
    }


    //eenvoudige GET-request voor alle televisies
    @GetMapping(value = "/televisionstart")
    public List<String> getAllTelevisions() {
        return televisionsets;
    }
    //ANDERE syntax voor GET-request voor alle televisies
    @GetMapping("/televisions")
    public ResponseEntity<Object> getTelevisions() {
        return ResponseEntity.ok().body(televisionsets);
    }


    //eenvoudige GET-request voor 1 televisie
    @GetMapping(value = "/televisionstart/{id}")
    // je wilt het liefste een 204 status terug, geen 200
    @ResponseStatus(HttpStatus.OK)
    public String getOneTelevision(@PathVariable int id) {
        return televisionsets.get(id);
    }
    //ANDERE syntax voor GET-request voor 1 televisie
    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevisionById(@PathVariable int id){
        return ResponseEntity.ok().body(televisionsets.get(id));
    }

    String wandavision = "foobar";
    //eenvoudige POST-request voor 1 televisie
    @PostMapping(value = "/televisionstart")
    // je wilt het liefste een 204 status terug, geen 200
    // NB: de POST methode heeft een Raw BODY nodig
    @ResponseStatus(HttpStatus.CREATED)
    public String addOneTV(@RequestBody String tv) {
        televisionsets.add(tv);
        televisionsets.add(wandavision);
        return "Added!";
    }
    //ANDERE syntax voor POST-request voor 1 televisie
//    @PostMapping("/televisions")
//    public ResponseEntity<Object> addTelevision(@RequestBody String newTV) {
//        televisionsets.add(newTV);
//        return ResponseEntity.created();
//    }
    // HOEFT NU EVEN NIET, want deze code hoeft niet te werken voor opdracht 1


    //een PUT-request voor 1 televisie
    //oorspronkelijke aray is: List<String> televisionsets = new ArrayList<>();

    @PutMapping("/televisions/{id}")
    public void update(@RequestBody int id,String updatedTV) {
        String replacedTelevision = televisionsets.set(id,updatedTV);
        //mag void zijn!
        //alles wat je returned is eigenlijk alleen maar info aan de gebruiker
        //de return verandert niets in de array/database zelf.
        //anders: als je wél iets terugverwacht, dan mag de functie zo zijn:
        //public ResponseEntity<String> update(@RequestBody...
    }

    //eenvoudige DELETE-request voor 1 televisie
    @DeleteMapping(value = "/televisionstart/{id}")
    // je wilt het liefste een 204 status terug, geen 200
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneTelevision(@PathVariable int id) {
        televisionsets.remove(id);
        //hoeft geen return
    }
    //ANDERE syntax voor DELETE-request voor 1 tv
    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisionsets.remove(id);
        return ResponseEntity.ok("Televisions list after deletion: " + televisionsets);
        // maar DELETE verwacht eigenlijk: return ResponseEntity.noContent();
    }


    //extraatje:
    //een manier om attributen te gebruiken
    //maar deze televisionsets Array heeft nu alleen indexen, geen objecten
//    @GetMapping("/televisions?name={name}")
//    public ResponseEntity<Object> getTelevisionByName(RequestParam String name) {
//        return ResponseEntity.ok("television: " + name);
//    }

}

package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
        return ResponseEntity.ok("televisions list: " + televisionsets);
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
        return ResponseEntity.ok("Television is: " + televisionsets.get(id));
    }


    //eenvoudige POST-request voor 1 televisie
    @PostMapping(value = "/televisionstart")
    // je wilt het liefste een 204 status terug, geen 200
    // NB: de POST methode heeft een Raw BODY nodig
    @ResponseStatus(HttpStatus.CREATED)
    public String addOneTV(@RequestBody String tv) {
        televisionsets.add(tv);
        return "Added!";
    }
    //ANDERE syntax voor POST-request voor 1 televisie
    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String newTV) throws URISyntaxException {
//        televisionsets.add(newTV);
        return ResponseEntity.created(new URI("/televisions/" + newTV)).build();
    }


    //een PUT-request voor 1 televisie
    //oorspronkelijke aray is: List<String> televisionsets = new ArrayList<>();
    //    @PutMapping(value = "replacedTelevision")
    //    ?
    //      return ResponseEntity.noContent();
    @PutMapping("/televisions/{id}")
    public ResponseEntity<String> update(@RequestBody int id,String updatedTV) {
        String replacedTelevision = televisionsets.set(id,updatedTV);
        //mislukt: wordt niet geset..?
        return new ResponseEntity<>(replacedTelevision, HttpStatus.OK);
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
        // maar is niet goed? want DELETE verwacht eigenlijk: return ResponseEntity.noContent();
    }



    //extraatje:
    //een manier om attributen te gebruiken?
    //maar deze heeft alleen indexen, geen objecten
//    @GetMapping("/televisions?name={name}")
//    public ResponseEntity<Object> getTelevisionByName(RequestParam String name) {
//        return ResponseEntity.ok("television: " + name);
//    }

}

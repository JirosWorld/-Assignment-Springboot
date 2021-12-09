package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.WallBracketDto;
import nl.novi.TechItEasy.dtos.WallBracketInputDto;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.models.WallBracket;
import nl.novi.TechItEasy.services.TelevisionWallBracketService;
import nl.novi.TechItEasy.services.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class WallBracketController {
    private final WallBracketService wallBracketService;
    private final TelevisionWallBracketService televisionWallBracketService;

    @Autowired
    public WallBracketController(WallBracketService wallBracketService,
                                 TelevisionWallBracketService televisionWallBracketService) {
        this.wallBracketService = wallBracketService;
        this.televisionWallBracketService = televisionWallBracketService;
    }

    @GetMapping("/wallbrackets")
    public List<WallBracketDto> getAllWallBrackets() {

        var dtos = new ArrayList<WallBracketDto>();

        var wallBrackets = wallBracketService.getAllWallBrackets();

        for (WallBracket wallBracket : wallBrackets){
            dtos.add(WallBracketDto.fromWallBracket(wallBracket));
        }

        return dtos;
    }

    @GetMapping("/wallbrackets/{id}")
    public WallBracketDto getWallBracket(@PathVariable("id") Long id) {

        var wallBracket = wallBracketService.getWallBracket(id);

        return WallBracketDto.fromWallBracket(wallBracket);
    }

    @PostMapping("/wallbrackets")
    public WallBracketDto addWallBracket(@RequestBody WallBracketInputDto dto) {
        var wallBracket = wallBracketService.addWallbracket(dto.toWallBracket());
        return WallBracketDto.fromWallBracket(wallBracket);
    }

    @DeleteMapping("/wallbrackets/{id}")
    public void deleteWallBracket(@PathVariable("id") Long id) {
        wallBracketService.deleteWallBracket(id);
    }

    @PutMapping("/wallbrackets/{id}")
    public WallBracketDto updateWallBracket(@PathVariable("id") Long id, @RequestBody WallBracket wallBracket) {
        wallBracketService.updateWallBracket(id, wallBracket);
        return WallBracketDto.fromWallBracket(wallBracket);
    }

    @GetMapping("/wallbrackets/televisions/{televisionId}")
    public Collection<Television> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId){
        return televisionWallBracketService.getTelevisionWallBracketsByWallBracketId(wallBracketId);
    }
}
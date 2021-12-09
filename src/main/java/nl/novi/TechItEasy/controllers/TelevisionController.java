package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.IdInputDto;
import nl.novi.TechItEasy.dtos.TelevisionDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import nl.novi.TechItEasy.exceptions.BadRequestException;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.models.WallBracket;
import nl.novi.TechItEasy.services.TelevisionService;
import nl.novi.TechItEasy.services.TelevisionWallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class TelevisionController {
    private final TelevisionService televisionService;
    private final TelevisionWallBracketService televisionWallBracketService;

    @Autowired
    public TelevisionController(TelevisionService televisionService,
                                TelevisionWallBracketService televisionWallBracketService) {
        this.televisionService = televisionService;
        this.televisionWallBracketService = televisionWallBracketService;
    }


    @GetMapping("/televisions")
    public List<TelevisionDto> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {

        var dtos = new ArrayList<TelevisionDto>();

        List<Television> televisions;

        if(brand != null ){
            televisions = televisionService.getAllTelevisionsByBrand(brand);
        } else if (brand == null) {
            televisions = televisionService.getAllTelevisions();
        } else {
            throw new BadRequestException();
        }

        for (Television television : televisions){
            dtos.add(TelevisionDto.fromTelevision(television));
        }

        return dtos;
    }

    @GetMapping("/televisions/{id}")
    public TelevisionDto getTelevision(@PathVariable("id") Long id) {

        var television = televisionService.getTelevision(id);

        return TelevisionDto.fromTelevision(television);
    }

    @PostMapping("/televisions")
    public TelevisionDto addTelevision(@RequestBody TelevisionInputDto dto) {
        var television = televisionService.addTelevision(dto.toTelevision());
        return TelevisionDto.fromTelevision(television);
    }

    @DeleteMapping("/televisions/{id}")
    public void deleteTelevision(@PathVariable("id") Long id) {
        televisionService.deleteTelevision(id);
    }

    @PutMapping("/televisions/{id}")
    public TelevisionDto updateTelevision(@PathVariable("id") Long id, @RequestBody Television television) {
        televisionService.updateTelevision(id, television);
        return TelevisionDto.fromTelevision(television);
    }

    @PutMapping("/televisions/{id}/remotecontroller")
    public void assignRemoteControllerToTelevision(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        televisionService.assignRemoteControllerToTelevision(id, input.id);
    }

    @PutMapping("/televisions/{id}/{ciModuleId}")
    public void assignCIModuleToTelevision(@PathVariable("id") Long id, @PathVariable("ciModuleId") Long ciModuleId) {
        televisionService.assignCIModuleToTelevision(id, ciModuleId);
    }

    @GetMapping("/televisions/wallBrackets/{televisionId}")
    public Collection<WallBracket> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId){
        return televisionWallBracketService.getTelevisionWallBracketByTelevisionId(televisionId);
    }
}
package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.CIModuleDto;
import nl.novi.TechItEasy.dtos.CIModuleInputDto;
import nl.novi.TechItEasy.models.CIModule;
import nl.novi.TechItEasy.services.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CIModuleController {
    private final CIModuleService ciModuleService;

    @Autowired
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }


    @GetMapping("/cimodules")
    public List<CIModuleDto> getAllCIModules() {

        var dtos = new ArrayList<CIModuleDto>();

        var ciModules = ciModuleService.getAllCIModules();

        for (CIModule ciModule : ciModules){
            dtos.add(CIModuleDto.fromCIModule(ciModule));
        }

        return dtos;
    }

    @GetMapping("/cimodules/{id}")
    public CIModuleDto getCIModule(@PathVariable("id") Long id) {

        var ciModule = ciModuleService.getCIModule(id);

        return CIModuleDto.fromCIModule(ciModule);
    }

    @PostMapping("/cimodules")
    public CIModuleDto addCIModule(@RequestBody CIModuleInputDto dto) {
        var ciModule = ciModuleService.addCIModule(dto.toCIModule());
        return CIModuleDto.fromCIModule(ciModule);
    }

    @DeleteMapping("/cimodules/{id}")
    public void deleteCIModule(@PathVariable("id") Long id) {
        ciModuleService.deleteCIModule(id);
    }

    @PutMapping("/cimodules/{id}")
    public CIModuleDto updateCIModule(@PathVariable("id") Long id, @RequestBody CIModule ciModule) {
        ciModuleService.updateCIModule(id, ciModule);
        return CIModuleDto.fromCIModule(ciModule);
    }

}
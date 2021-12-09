package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dtos.RemoteControllerDto;
import nl.novi.TechItEasy.dtos.RemoteControllerInputDto;
import nl.novi.TechItEasy.models.RemoteController;
import nl.novi.TechItEasy.services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    @Autowired
    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }


    @GetMapping("/remotecontrollers")
    public List<RemoteControllerDto> getAllRemotecontrollers() {

        var dtos = new ArrayList<RemoteControllerDto>();

        var remotecontrollers = remoteControllerService.getAllRemoteControllers();

        for (RemoteController remoteController : remotecontrollers){
            dtos.add(RemoteControllerDto.fromRemoteController(remoteController));
        }

        return dtos;
    }

    @GetMapping("/remotecontrollers/{id}")
    public RemoteControllerDto getRemotecontroller(@PathVariable("id") Long id) {

        var remotecontroller = remoteControllerService.getRemoteController(id);

        return RemoteControllerDto.fromRemoteController(remotecontroller);
    }

    @PostMapping("/remotecontrollers")
    public RemoteControllerDto addRemoteController(@RequestBody RemoteControllerInputDto dto) {
        var remotecontroller = remoteControllerService.addRemoteController(dto.toRemoteController());
        return RemoteControllerDto.fromRemoteController(remotecontroller);
    }

    @DeleteMapping("/remotecontrollers/{id}")
    public void deleteRemoteController(@PathVariable("id") Long id) {
        remoteControllerService.deleteRemoteController(id);
    }

    @PutMapping("/remotecontrollers/{id}")
    public RemoteControllerDto updateTelevision(@PathVariable("id") Long id, @RequestBody RemoteController remoteController) {
        remoteControllerService.updateRemoteController(id, remoteController);
        return RemoteControllerDto.fromRemoteController(remoteController);
    }

}
package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.RemoteController;
import nl.novi.TechItEasy.repositories.RemoteControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    private RemoteControllerRepository remoteControllerRepository;

    @Autowired
    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteController> getAllRemoteControllers() {
        return remoteControllerRepository.findAll();
    }

    public RemoteController getRemoteController(long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);
        if(remoteController.isPresent()) {
            return remoteController.get();
        } else {
            throw new RecordNotFoundException("No remotecontroller found");
        }
    }

    public RemoteController addRemoteController(RemoteController remoteController) {
        return remoteControllerRepository.save(remoteController);
    }

    public void deleteRemoteController(Long id) {
        remoteControllerRepository.deleteById(id);
    }

    public void updateRemoteController(Long id, RemoteController remoteController) {
        if(!remoteControllerRepository.existsById(id)) {
            throw new RecordNotFoundException("No remotecontroller found");
        }
        RemoteController storedRemoteController = remoteControllerRepository.findById(id).orElse(null);
        storedRemoteController.setId(remoteController.getId());
        storedRemoteController.setCompatibleWith(remoteController.getCompatibleWith());
        storedRemoteController.setBatteryType(remoteController.getBatteryType());
        storedRemoteController.setName(remoteController.getName());
        storedRemoteController.setPrice(remoteController.getPrice());
        storedRemoteController.setBrand(remoteController.getBrand());
        storedRemoteController.setOriginalStock(remoteController.getOriginalStock());
        remoteControllerRepository.save(storedRemoteController);
    }

}

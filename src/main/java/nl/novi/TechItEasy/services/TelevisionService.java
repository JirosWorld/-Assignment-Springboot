package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.repositories.CIModuleRepository;
import nl.novi.TechItEasy.repositories.RemoteControllerRepository;
import nl.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private TelevisionRepository televisionRepository;
    private RemoteControllerRepository remoteControllerRepository;
    private CIModuleRepository ciModuleRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository,
                             RemoteControllerRepository remoteControllerRepository,
                             CIModuleRepository ciModuleRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<Television> getAllTelevisions() {
        return televisionRepository.findAll();
    }

    public Television getTelevision(long id) {
        Optional<Television> television = televisionRepository.findById(id);
        if(television.isPresent()) {
            return television.get();
        } else {
            throw new RecordNotFoundException("No television found");
        }
    }

    public Television addTelevision(Television television) {
        return televisionRepository.save(television);
    }

    public void deleteTelevision(Long id) {
        televisionRepository.deleteById(id);
    }

    public void updateTelevision(Long id, Television television) {
        if(!televisionRepository.existsById(id)) {
            throw new RecordNotFoundException("No television found");
        }
        Television storedTelevision = televisionRepository.findById(id).orElse(null);
        storedTelevision.setAmbiLight(television.getAmbiLight());
        storedTelevision.setAvailableSize(television.getAvailableSize());
        storedTelevision.setBluetooth(television.getBluetooth());
        storedTelevision.setBrand(television.getBrand());
        storedTelevision.setHdr(television.getHdr());
        storedTelevision.setId(storedTelevision.getId());
        storedTelevision.setName(television.getName());
        storedTelevision.setOriginalStock(television.getOriginalStock());
        storedTelevision.setPrice(television.getPrice());
        storedTelevision.setRefreshRate(television.getRefreshRate());
        storedTelevision.setScreenType(television.getScreenType());
        storedTelevision.setScreenQuality(television.getScreenQuality());
        storedTelevision.setSmartTv(television.getSmartTv());
        storedTelevision.setSold(television.getSold());
        storedTelevision.setType(television.getType());
        storedTelevision.setVoiceControl(television.getVoiceControl());
        storedTelevision.setWifi(television.getWifi());
        televisionRepository.save(storedTelevision);
    }

    public List<Television> getAllTelevisionsByBrand(String brand) {
        return televisionRepository.findAllByBrandContainingIgnoreCase(brand);
    }

    public void assignRemoteControllerToTelevision(Long id, Long remotecontrollerId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalRemoteController = remoteControllerRepository.findById(remotecontrollerId);

        if(optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            var television = optionalTelevision.get();
            var remoteController = optionalRemoteController.get();

            television.setRemoteController(remoteController);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void assignCIModuleToTelevision(Long id, Long ciModuleId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalCIModule = ciModuleRepository.findById(ciModuleId);

        if(optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            var television = optionalTelevision.get();
            var ciModule = optionalCIModule.get();

            television.setCiModule(ciModule);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
package nl.novi.TechItEasy.Services;

import nl.novi.TechItEasy.Dtos.TelevisionDto;
import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.Television;
import nl.novi.TechItEasy.Repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionDto> getAllTelevisions() {
        var dtos = new ArrayList<TelevisionDto>();
        var televisions = televisionRepository.findAll();

        for (Television television : televisions) {
            dtos.add(TelevisionDto.fromTelevision(television));
        }

        return dtos;
    }

    public Television getTelevision(Long id) {
        Optional<Television> television = televisionRepository.findById(id);
        if (television.isPresent()) {
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
        if (!televisionRepository.existsById(id)) {
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

}
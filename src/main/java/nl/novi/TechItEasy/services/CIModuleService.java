package nl.novi.TechItEasy.services;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.CIModule;
import nl.novi.TechItEasy.repositories.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {
    private CIModuleRepository ciModuleRepository;

    @Autowired
    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModule> getAllCIModules() {
        return ciModuleRepository.findAll();
    }

    public CIModule getCIModule(long id) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);
        if(ciModule.isPresent()) {
            return ciModule.get();
        } else {
            throw new RecordNotFoundException("No ci-module found");
        }
    }

    public CIModule addCIModule(CIModule ciModule) {
        return ciModuleRepository.save(ciModule);
    }

    public void deleteCIModule(Long id) {
        ciModuleRepository.deleteById(id);
    }

    public void updateCIModule(Long id, CIModule ciModule) {
        if(!ciModuleRepository.existsById(id)) {
            throw new RecordNotFoundException("No ci-module found");
        }
        CIModule storedCIModule = ciModuleRepository.findById(id).orElse(null);
        storedCIModule.setId(ciModule.getId());
        storedCIModule.setType(ciModule.getType());
        storedCIModule.setName(ciModule.getName());
        storedCIModule.setPrice(ciModule.getPrice());
        ciModuleRepository.save(storedCIModule);
    }

}

package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.models.CIModule;

public class CIModuleDto {
    public Long id;
    public String name;
    public String type;
    public Double price;

    public static CIModuleDto fromCIModule(CIModule ciModule) {

        var dto = new CIModuleDto();

        dto.id = ciModule.getId();
        dto.name = ciModule.getName();
        dto.type = ciModule.getType();
        dto.price = ciModule.getPrice();

        return dto;
    }
}

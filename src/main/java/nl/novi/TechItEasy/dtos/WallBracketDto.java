package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.models.WallBracket;

public class WallBracketDto {
    public Long id;
    public String size;
    public Boolean ajustable;
    public String name;
    public Double price;

    public static WallBracketDto fromWallBracket(WallBracket wallBracket) {

        var dto = new WallBracketDto();

        dto.id = wallBracket.getId();
        dto.size = wallBracket.getSize();
        dto.ajustable = wallBracket.getAjustable();
        dto.name = wallBracket.getName();
        dto.price = wallBracket.getPrice();

        return dto;
    }
}

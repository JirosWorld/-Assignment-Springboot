package nl.novi.TechItEasy.dtos;

import nl.novi.TechItEasy.models.WallBracket;

public class WallBracketInputDto {
    public Long id;
    public String size;
    public Boolean ajustable;
    public String name;
    public Double price;

    public WallBracket toWallBracket() {

        var wallBracket = new WallBracket();

        wallBracket.setId(id);
        wallBracket.setSize(size);
        wallBracket.setAjustable(ajustable);
        wallBracket.setName(name);
        wallBracket.setPrice(price);

        return wallBracket;
    }
}

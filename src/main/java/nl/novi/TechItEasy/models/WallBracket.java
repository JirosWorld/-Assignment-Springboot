package nl.novi.TechItEasy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class WallBracket {

    @Id
    @GeneratedValue
    private Long id;
    private String size;
    private Boolean ajustable;
    private String name;
    private Double price;

    @OneToMany(mappedBy = "television")
    @JsonIgnore
    List<TelevisionWallbracket> televisionWallbrackets;

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Boolean getAjustable() {
        return ajustable;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<TelevisionWallbracket> getTelevisionWallbrackets() {
        return televisionWallbrackets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAjustable(Boolean ajustable) {
        this.ajustable = ajustable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTelevisionWallbrackets(List<TelevisionWallbracket> televisionWallbrackets) {
        this.televisionWallbrackets = televisionWallbrackets;
    }
}

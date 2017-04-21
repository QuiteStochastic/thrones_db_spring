package thrones_db_spring.model.pojos.jointables;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import thrones_db_spring.model.pojos.pillars.Character;
import thrones_db_spring.model.pojos.pillars.Location;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "thrones_db_schema", name = "visitor")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@visitorJsonId")
public class Visitor {

    //foreign key
    @ManyToOne
    @JoinColumn(name="locationId")
    private Location location;

    //foreign key
    @ManyToOne
    @JoinColumn(name="characterId")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

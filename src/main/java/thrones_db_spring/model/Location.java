package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by oliverlee
 */
@Entity
@Table(schema = "thrones_db_schema", name = "location")
public class Location {


    @Id
    private Integer locationId;
    private String name;
    private String locationType;
    private String description;
    //foreign key
    //private Integer superiorLocationId;


    //@Transient
    //private Location superiorLoc;

    //secondary, visitor.  backref: locations
    //@Transient
    //private Character characters;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

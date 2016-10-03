package thrones_db_spring.model;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="superiorLocationId")
    private Location superiorLocation;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="visitor",
            joinColumns=@JoinColumn(name="locationId", referencedColumnName="locationId"),
            inverseJoinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"))
    private List<Character> charactersVisited;


    @OneToMany(mappedBy = "location",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="locationId")
    private List<Event> eventsHappened;


    @OneToMany(mappedBy = "seatLocation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="seatLocationId")
    private List<Organization> organizationsHere;



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

    public Location getSuperiorLocation() {
        return superiorLocation;
    }

    public void setSuperiorLocation(Location superiorLocation) {
        this.superiorLocation = superiorLocation;
    }

    public List<Character> getCharactersVisited() {
        return charactersVisited;
    }

    public void setCharactersVisited(List<Character> charactersVisited) {
        this.charactersVisited = charactersVisited;
    }

    public List<Event> getEventsHappened() {
        return eventsHappened;
    }

    public void setEventsHappened(List<Event> eventsHappened) {
        this.eventsHappened = eventsHappened;
    }

    public List<Organization> getOrganizationsHere() {
        return organizationsHere;
    }

    public void setOrganizationsHere(List<Organization> organizationsHere) {
        this.organizationsHere = organizationsHere;
    }
}

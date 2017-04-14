package thrones_db_spring.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by oliverlee
 */
@Entity
@Table(schema = "thrones_db_schema", name = "location")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@locationJsonId")
public class Location {


    @Id
    private Integer locationId;
    private String name;
    private String locationType;
    private String description;

    @JsonView(Location.class)
    @JsonIgnoreProperties({ "eventsHappened", "organizationsHere","subordinateLocations","charactersVisited" })
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="superiorLocationId")
    private Location superiorLocation;


    @JsonView(Location.class)
    @JsonIgnoreProperties({ "memberOf", "visited","participantOf","charactersVisited" })
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="visitor",
            joinColumns=@JoinColumn(name="locationId", referencedColumnName="locationId"),
            inverseJoinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"))
    private Set<Character> charactersVisited;


    @JsonView(Location.class)
    @JsonIgnoreProperties({ "location", "episode","participants","parties" })
    @OneToMany(mappedBy = "location",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="locationId")
    private Set<Event> eventsHappened;


    @JsonView(Location.class)
    @JsonIgnoreProperties({ "seatLocation", "leaderCharacter","members","partyTo" })
    @OneToMany(mappedBy = "seatLocation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="seatLocationId")
    private Set<Organization> organizationsHere;


    @JsonView(Location.class)
    @JsonIgnoreProperties({ "eventsHappened", "organizationsHere","subordinateLocations","charactersVisited" })
    @OneToMany(mappedBy = "superiorLocation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="seatLocationId")
    private Set<Location> subordinateLocations;




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


    public Set<Character> getCharactersVisited() {
        return charactersVisited;
    }

    public void setCharactersVisited(Set<Character> charactersVisited) {
        this.charactersVisited = charactersVisited;
    }

    public Set<Event> getEventsHappened() {
        return eventsHappened;
    }

    public void setEventsHappened(Set<Event> eventsHappened) {
        this.eventsHappened = eventsHappened;
    }

    public Set<Organization> getOrganizationsHere() {
        return organizationsHere;
    }

    public void setOrganizationsHere(Set<Organization> organizationsHere) {
        this.organizationsHere = organizationsHere;
    }

    public Set<Location> getSubordinateLocations() {
        return subordinateLocations;
    }

    public void setSubordinateLocations(Set<Location> subordinateLocations) {
        this.subordinateLocations = subordinateLocations;
    }
}

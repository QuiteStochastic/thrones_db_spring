package thrones_db_spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by oliverlee
 */
@Entity
@Table(schema = "thrones_db_schema", name = "organization")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@organizationJsonId")
public class Organization {

    @Id
    private Integer organizationId;
    private String name;
    private String organizationType;
    private String description;

    @JsonView(Organization.class)
    @JsonIgnoreProperties({ "eventsHappened", "organizationsHere","subordinateLocations","charactersVisited" })
    //foreign key
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="seatLocationId")
    private Location seatLocation;

    @JsonView(Organization.class)
    @JsonIgnoreProperties({ "memberOf", "visited","participantOf","charactersVisited" })
    //foreign key
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="leaderCharacterId")
    private Character leaderCharacter;

    @JsonView(Organization.class)
    @JsonIgnoreProperties({ "memberOf", "visited","participantOf","charactersVisited" })
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="member",
            joinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"),
            inverseJoinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"))
    private Set<Character> members;

    @JsonView(Organization.class)
    @JsonIgnoreProperties({ "location", "episode","participants","parties" })
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="party",
            joinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"),
            inverseJoinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"))
    private Set<Event> partyTo;




    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(Location seatLocation) {
        this.seatLocation = seatLocation;
    }

    public Character getLeaderCharacter() {
        return leaderCharacter;
    }

    public void setLeaderCharacter(Character leaderCharacter) {
        this.leaderCharacter = leaderCharacter;
    }

    public Set<Character> getMembers() {
        return members;
    }

    public void setMembers(Set<Character> members) {
        this.members = members;
    }

    public Set<Event> getPartyTo() {
        return partyTo;
    }

    public void setPartyTo(Set<Event> partyTo) {
        this.partyTo = partyTo;
    }
}

package thrones_db_spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import thrones_db_spring.model.services.SerializationService;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oliverlee
 */

@Entity
@Table(schema = "thrones_db_schema", name = "event")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@eventJsonId")
public class Event {

    @JsonView(SerializationService.Compact.class)
    @Id
	private Integer eventId;
    @JsonView(SerializationService.Compact.class)
    private String name;
    @JsonView(SerializationService.Compact.class)
    private String eventType;
    @JsonView(SerializationService.Compact.class)
    private String description;

    @JsonView(SerializationService.Extended.class)
	@JsonIgnoreProperties({ "eventsHappened", "organizationsHere","subordinateLocations","charactersVisited" })
	//foreign key
	//private Integer locationId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="locationId")
    private Location location;

    @JsonView(SerializationService.Extended.class)
    @JsonIgnoreProperties({ "eventsHappened"})
    //foreign key
	//private Integer episodeId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="episodeId")
    private Episode episode;

    @JsonView(SerializationService.Extended.class)
    @JsonIgnoreProperties({ "memberOf", "visited","participantOf","charactersVisited" })
    //backref= events, secondary =Participant
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participant",
            joinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"),
            inverseJoinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"))
    private Set<Character> participants;

    @JsonView(SerializationService.Extended.class)
    @JsonIgnoreProperties({ "seatLocation", "leaderCharacter","members","partyTo" })
    //backref=events, secondary = Party
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="party",
            joinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"),
            inverseJoinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"))
	private Set<Organization> parties;


	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Set<Character> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Character> participants) {
        this.participants = participants;
    }

    public Set<Organization> getParties() {
        return parties;
    }

    public void setParties(Set<Organization> parties) {
        this.parties = parties;
    }
}

package thrones_db_spring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oliverlee
 */

@Entity
@Table(schema = "thrones_db_schema", name = "event")
public class Event {

	@Id
	private Integer eventId;
	private String name;
	private String eventType;
	private String description;


	//foreign key
	//private Integer locationId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="locationId")
    private Location location;

	//foreign key
	//private Integer episodeId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="episodeId")
    private Episode episode;


	//backref= events, secondary =Participant
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participant")
    private List<Character> participants;

	//backref=events, secondary = Party
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="party")
	private List<Organization> parties;


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

    public List<Character> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Character> participants) {
        this.participants = participants;
    }

    public List<Organization> getParties() {
        return parties;
    }

    public void setParties(List<Organization> parties) {
        this.parties = parties;
    }
}

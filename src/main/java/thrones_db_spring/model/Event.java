package thrones_db_spring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oliverlee
 */

@Entity
@Table(schema = "thrones_db_schema", name = "event")
//@SecondaryTable(schema="thrones_db_schema",name="party","participant")
public class Event {

	@Id
	private Integer eventId;
	private String name;
	private String eventType;
	private String description;


	//foreign key
	private Integer locationId;

	//backref=events
	@OneToMany
	private Location location;

	//foreign key
	private Integer episodeId;

	//backref=events
	@OneToMany
	private Event episode;

	//backref= events, secondary =Participant
	@ManyToMany
	private List<Character> characters;

	//backref=events, secondary = Party
	@ManyToMany
	private List<Organization> organizations;


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

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}
}

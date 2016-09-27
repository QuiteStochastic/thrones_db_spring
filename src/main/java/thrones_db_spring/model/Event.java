package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private Integer locationId;

	//backref=events
	private Location location;

	//foreign key
	private Integer episode_id;

	//backref=events
	private Event episode;

	//backref= events, secondary =Participant
	private List<Character> characters;

	//backref=events, secondary = Party
	private List<Organization> organizations;


}

package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oliverlee
 */

@Entity
@Table(schema = "thronesdb_db", name = "event")
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
	private Character characters;

	//backref=events, secondary = Party
	private Organization organizations;


}

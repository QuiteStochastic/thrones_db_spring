package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "thronesdb_db", name = "character")
public class Character{

	//primary key
	@Id
	private Integer charId;

	private String firstName;

	private String last_name;

	private String alias;

	private String gender;

	private String religion;

	private String status;

	private String description;

	@Transient
	private List<Membership> affiliations = new ArrayList<Membership>();

	@Transient
	private List<Location> visited = new ArrayList<Location>();


	@Transient
	private List<Event> events = new ArrayList<Event>();


	public Integer getCharId() {
		return charId;
	}

	public void setCharId(Integer charId) {
		this.charId = charId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
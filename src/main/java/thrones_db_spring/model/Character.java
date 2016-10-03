package thrones_db_spring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "thrones_db_schema", name = "character")
public class Character{

	//primary key
	@Id
	private Integer characterId;

	private String firstName;

	private String lastName;

	private String alias;

	private String gender;

	private String religion;

	private String status;

	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="member",
            joinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"),
            inverseJoinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"))
	private List<Organization> membershipOf;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="visitor",
            joinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"),
            inverseJoinColumns=@JoinColumn(name="locationId", referencedColumnName="locationId"))
	private List<Location> visited;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participant",
            joinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"),
            inverseJoinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"))
    private List<Event> participantOf;



	public Integer getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

    public List<Organization> getMembershipOf() {
        return membershipOf;
    }

    public void setMembershipOf(List<Organization> membershipOf) {
        this.membershipOf = membershipOf;
    }

    public List<Location> getVisited() {
        return visited;
    }

    public void setVisited(List<Location> visited) {
        this.visited = visited;
    }

    public List<Event> getParticipantOf() {
        return participantOf;
    }

    public void setParticipantOf(List<Event> participantOf) {
        this.participantOf = participantOf;
    }
}
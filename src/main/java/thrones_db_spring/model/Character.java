package thrones_db_spring.model;

import com.fasterxml.jackson.annotation.*;
import thrones_db_spring.model.services.SerializationService;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "thrones_db_schema", name = "character")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@characterJsonId")
public class Character{

	//primary key
    @JsonView(SerializationService.Compact.class)
    @Id
	private Integer characterId;

    @JsonView(SerializationService.Compact.class)
	private String firstName;

    @JsonView(SerializationService.Compact.class)
	private String lastName;

    @JsonView(SerializationService.Compact.class)
	private String alias;

    @JsonView(SerializationService.Compact.class)
	private String gender;

    @JsonView(SerializationService.Compact.class)
	private String religion;

    @JsonView(SerializationService.Compact.class)
	private String status;

    @JsonView(SerializationService.Compact.class)
	private String description;

    @JsonView(SerializationService.Extended.class)
	@JsonIgnoreProperties({ "seatLocation", "leaderCharacter","members","partyTo" })
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="member",
            joinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"),
            inverseJoinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"))
	private Set<Organization> memberOf;

    @JsonView(SerializationService.Extended.class)
    @JsonIgnoreProperties({ "seatLocation", "leaderCharacter","members","partyTo" })
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="visitor",
            joinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"),
            inverseJoinColumns=@JoinColumn(name="locationId", referencedColumnName="locationId"))
	private Set<Location> visited;

    @JsonView(SerializationService.Extended.class)
    @JsonIgnoreProperties({ "seatLocation", "leaderCharacter","members","partyTo" })
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participant",
            joinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"),
            inverseJoinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"))
    private Set<Event> participantOf;



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

    public String getFullName() {

        if(firstName !=null && !firstName.isEmpty() && lastName !=null && !lastName.isEmpty()){
            return firstName+" "+lastName;
        }
        else if(lastName == null || lastName.isEmpty()){
            return firstName;
        }
        else{
            return lastName;
        }

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

	public Set<Organization> getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(Set<Organization> memberOf) {
		this.memberOf = memberOf;
	}

	public Set<Location> getVisited() {
		return visited;
	}

	public void setVisited(Set<Location> visited) {
		this.visited = visited;
	}

	public Set<Event> getParticipantOf() {
		return participantOf;
	}

	public void setParticipantOf(Set<Event> participantOf) {
		this.participantOf = participantOf;
	}
}
package thrones_db_spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import thrones_db_spring.model.repositories.CharacterRepository;
import thrones_db_spring.model.repositories.OrganizationRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliverlee
 */

@Entity
@Table(schema = "thrones_db_schema", name = "event")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@eventJsonId")
public class Event {

    @JsonIgnore
    @Autowired
    private CharacterRepository characterRepository;


    @JsonIgnore
    @Autowired
    private OrganizationRepository organizationRepository;

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
/*	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participant",
            joinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"),
            inverseJoinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"))
    private List<Character> participants;*/

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="participant",
			joinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"),
			inverseJoinColumns=@JoinColumn(name="characterId", referencedColumnName="characterId"))
	private List<Integer> participantIds;



	//backref=events, secondary = Party
/*    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="party",
            joinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"),
            inverseJoinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"))
	private List<Organization> parties;*/


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participant",
            joinColumns=@JoinColumn(name="eventId", referencedColumnName="eventId"),
            inverseJoinColumns=@JoinColumn(name="organizationId", referencedColumnName="organizationId"))
    private List<Integer> partyIds;


	public Integer getEventId() {
		return eventId;
	}

//	public void setEventId(Integer eventId) {
//		this.eventId = eventId;
//	}

	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public String getEventType() {
		return eventType;
	}

//	public void setEventType(String eventType) {
//		this.eventType = eventType;
//	}

	public String getDescription() {
		return description;
	}

//	public void setDescription(String description) {
//		this.description = description;
//	}

    public Location getLocation() {
        return location;
    }

//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public Episode getEpisode() {
        return episode;
    }

//    public void setEpisode(Episode episode) {
//        this.episode = episode;
//    }

/*    public List<Character> getParticipants() {
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
    }*/

    public List<Character> getParticipants() {
        List<Character> participantList=new ArrayList<>(participantIds.size());

        for(Integer i: participantIds){

            participantList.add(characterRepository.getCharacterById(i));
        }

        return participantList;
    }

//    public void setParticipantIds(List<Integer> participantIds) {
//        this.participantIds = participantIds;
//    }


    public List<Organization> getParties() {
        List<Organization> partyList=new ArrayList<>(participantIds.size());

        for(Integer i: participantIds){

            partyList.add(organizationRepository.getOrganizationById(i));
        }

        return partyList;
    }

}

package thrones_db_spring.model.pojos.pillars;

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
@Table(schema = "thrones_db_schema", name = "episode")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@episodeJsonId")
public class Episode {

    @JsonView(SerializationService.Compact.class)
	@Id
	private Integer episodeId;
    @JsonView(SerializationService.Compact.class)
	private String name;
    @JsonView(SerializationService.Compact.class)
    private Integer season;
    @JsonView(SerializationService.Compact.class)
    private Integer episodeNumber;
    @JsonView(SerializationService.Compact.class)
    private String description;

    @JsonView(SerializationService.Extended.class)
	@JsonIgnoreProperties({ "location", "episode","participants","parties" })
    @OneToMany(mappedBy = "episode", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> eventsHappened;



	public Episode(){

	}

	public Episode(Integer epId, String name, Integer season, Integer episodeNumber, String description){

		this.episodeId =epId;
		this.name=name;
		this.season=season;
		this.episodeNumber=episodeNumber;
		this.description=description;
	}




	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public Set<Event> getEventsHappened() {
		return eventsHappened;
	}

	public void setEventsHappened(Set<Event> eventsHappened) {
		this.eventsHappened = eventsHappened;
	}
}

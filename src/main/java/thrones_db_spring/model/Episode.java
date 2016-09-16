package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oliverlee
 */
@Entity
@Table(schema = "thronesdb_db", name = "episode")
public class Episode {


	@Id
	private Integer epId;

	private String name;
	private Integer season;
	private Integer episodeNumber;
	private String description;


	public Episode(Integer epId, String name, Integer season, Integer episodeNumber, String description){

		this.epId=epId;
		this.name=name;
		this.season=season;
		this.episodeNumber=episodeNumber;
		this.description=description;
	}




	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
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
}

package thrones_db_spring.model;

import javax.persistence.*;

/**
 * Created by oliverlee
 */
@Entity
@Table(schema = "thrones_db_schema", name = "organization")
public class Organization {

    @Id
    private Integer organizationId;
    private String name;
    private String organizationType;
    private String description;

    //foreign key
    @ManyToOne
    @JoinColumn(name="seatLocationId")
    private Location seatLocation;

    //foreign key
    @ManyToOne
    @JoinColumn(name="leaderCharacterId")
    private Character leaderCharacter;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(Location seatLocation) {
        this.seatLocation = seatLocation;
    }

    public Character getLeaderCharacter() {
        return leaderCharacter;
    }

    public void setLeaderCharacter(Character leaderCharacter) {
        this.leaderCharacter = leaderCharacter;
    }
}

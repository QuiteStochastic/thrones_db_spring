package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String text;

    //foreign key
    private Integer seatLocationId;

    //foreign key
    private Integer leaderCharacterId;
}

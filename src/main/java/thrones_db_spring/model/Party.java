package thrones_db_spring.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "thrones_db_schema", name = "party")
public class Party {

    //foreign key
    @ManyToOne
    @JoinColumn(name="organizationId")
    private Organization organization;

    //foreign key
    @ManyToOne
    @JoinColumn(name="eventId")
    private Event event;


}

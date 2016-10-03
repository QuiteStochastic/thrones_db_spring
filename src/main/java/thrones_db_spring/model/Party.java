package thrones_db_spring.model;


import javax.persistence.*;

@Entity
@Table(schema = "thrones_db_schema", name = "party")
public class Party {

    //foreign key
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="organizationId")
    private Organization organization;

    //foreign key
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="eventId")
    private Event event;


}

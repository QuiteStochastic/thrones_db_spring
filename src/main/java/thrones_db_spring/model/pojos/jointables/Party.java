package thrones_db_spring.model.pojos.jointables;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import thrones_db_spring.model.pojos.pillars.Event;
import thrones_db_spring.model.pojos.pillars.Organization;

import javax.persistence.*;

@Entity
@Table(schema = "thrones_db_schema", name = "party")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@partyJsonId")
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

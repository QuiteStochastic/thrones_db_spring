package thrones_db_spring.model.pojos.jointables;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import thrones_db_spring.model.pojos.pillars.Character;
import thrones_db_spring.model.pojos.pillars.Event;

import javax.persistence.*;

/**
 * Created by oliverl1
 */
@Entity
@Table(schema = "thrones_db_schema", name = "participant")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "@participantJsonId")
public class Participant {

    //foreign key
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="eventId")
    private Event event;

    //foreign key
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="characterId")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

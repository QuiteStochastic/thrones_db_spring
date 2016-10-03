package thrones_db_spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by oliverl1
 */
@Entity
@Table(schema = "thrones_db_schema", name = "participant")
public class Participant {

    //foreign key
    @ManyToOne
    @JoinColumn(name="eventId")
    private Event event;

    //foreign key
    @ManyToOne
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

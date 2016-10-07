package thrones_db_spring.controllers;

import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thrones_db_spring.model.Character;
import thrones_db_spring.model.*;
import thrones_db_spring.model.repositories.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oliverl1
 */
@RestController
@RequestMapping(path="/api",method = RequestMethod.GET, produces = "application/json")
public class APIController {



    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;


    //private Map<String,String> cache=new HashMap<String, String>();

    @RequestMapping(path="/search",method = RequestMethod.GET)
    public String searchPage(@RequestParam String keyword) {

        System.out.println("hit search api page");



        return "search";
    }

    @RequestMapping(path="/characters",method = RequestMethod.GET)
    public List<Character> charactersAPI(){

        System.out.println("hit characters api page");

        /*if(cache.containsKey("characters")){
            System.out.println("returning json from cache");
            return cache.get("characters");
        }*/
        return characterRepository.getAllCharacters();
    }

    @RequestMapping(path="/characters/{characterId}",method = RequestMethod.GET)
    public Character charactersIndividualAPI(@PathVariable Integer characterId){

        System.out.println("hit individual_characters api page");

        return characterRepository.getCharacterById(characterId);
    }

    @RequestMapping(path="/organizations",method = RequestMethod.GET)
    public List<Organization> organizationsPage(){

        System.out.println("hit organizations api page");

        return organizationRepository.getAllOrganizations();
    }

    @RequestMapping(path="/organizations/{organizationId}",method = RequestMethod.GET)
    public Organization organizationsIndividualPage(@PathVariable Integer organizationId){

        System.out.println("hit individual_organizations api page");
        return organizationRepository.getOrganizationById(organizationId);
    }

    @RequestMapping(path="/events",method = RequestMethod.GET)
    public List<Event> eventsPage(){

        System.out.println("hit events api page");

        return eventRepository.getAllEvents();
    }

    @RequestMapping(path="/events/{eventId}",method = RequestMethod.GET)
    public Event eventsIndividualPage(@PathVariable Integer eventId){

        System.out.println("hit individual_events api page");
        return eventRepository.getEventById(eventId);
    }

    @RequestMapping(path="/locations",method = RequestMethod.GET)
    public List<Location> locationsPage(){

        System.out.println("hit locations api page");

        return locationRepository.getAllLocations();
    }

    @RequestMapping(path="/locations/{locationId}",method = RequestMethod.GET)
    public Location locationsIndividualPage(@PathVariable Integer locationId){

        System.out.println("hit individual_locations api page");
        return locationRepository.getLocationById(locationId);
    }

    @RequestMapping(path="/episodes",method = RequestMethod.GET)
    public List<List<Episode>> episodesPage(){

        System.out.println("hit episodes api page");

        //the allEpisodes list will be ordered by episode number, see the getAllEpisodes method.
        // I ASSUME that in the data, episode n+1 is always either in either the same season or 1 (and exactly 1) season greater than episode n
        // If this assumption is not true, code will fail to work.  given the domain and controlled data, it shouldn't be a problem
        List<Episode> allEpisodes=episodeRepository.getAllEpisodes();

        List<List<Episode>> seasonList=new ArrayList<List<Episode>>();

        List<Episode> season=new ArrayList<Episode>();
        int seasonIndex=1;
        for(Episode e : allEpisodes){
            if(e.getSeason()==seasonIndex){
                season.add(e);
            }
            else{
                seasonList.add(season);
                season=new ArrayList<Episode>();
                season.add(e);
                seasonIndex++;
            }
        }
        seasonList.add(season);

        return seasonList;
    }

    @RequestMapping(path="/episodes/{episodeId}",method = RequestMethod.GET)
    public Episode episodesIndividualPage(@PathVariable Integer episodeId){

        System.out.println("hit individual_episodes api page");

        return episodeRepository.getEpisodeById(episodeId);

    }

}

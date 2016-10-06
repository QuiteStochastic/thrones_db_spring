package thrones_db_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import thrones_db_spring.model.Character;
import thrones_db_spring.model.*;
import thrones_db_spring.model.repositories.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by oliverlee
 */
@RestController
public class PageController {

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


    @Autowired
    private ViewResolver viewResolver;

    private Map<String,String> renderedCache=new HashMap<String, String>();



	@RequestMapping(path="/",method = RequestMethod.GET)
	public String mainPage(final HttpServletRequest req,final HttpServletResponse resp,Model model){

		System.out.println("hit main page");
        if(renderedCache.containsKey("main")){
            System.out.println("returning page from cache");
            return renderedCache.get("main");
        }
        else{

            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("main", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("main",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }
	}

	@RequestMapping(path="/about",method = RequestMethod.GET)
	public String aboutPage(final HttpServletRequest req,final HttpServletResponse resp,Model model){

		System.out.println("hit about page");

        if(renderedCache.containsKey("about")){
            System.out.println("returning page from cache");
            return renderedCache.get("about");
        }
        else{

            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("about", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("about",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/results",method = RequestMethod.GET)
	public String searchPage(Model model) {

		System.out.println("hit search results page");
		//model.addAttribute("test", "hello spring");
		return "search";
	}

	@RequestMapping(path="/characters",method = RequestMethod.GET)
	public String charactersPage(final HttpServletRequest req,final HttpServletResponse resp, Model model){

		System.out.println("hit characters page");

        if(renderedCache.containsKey("characters")){
            System.out.println("returning page from cache");
            return renderedCache.get("characters");
        }
        else{
            List<Character> characterList=characterRepository.getAllCharacters();
            model.addAttribute("characterList", characterList);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("characters", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("characters",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }



		
	}

	@RequestMapping(path="/characters/{characterId}",method = RequestMethod.GET)
	public String charactersIndividualPage(final HttpServletRequest req,final HttpServletResponse resp,Model model,@PathVariable Integer characterId){

		System.out.println("hit individual_characters page");

        if(renderedCache.containsKey("characters"+characterId)){
            System.out.println("returning page from cache");
            return renderedCache.get("characters"+characterId);
        }
        else{
            Character character=characterRepository.getCharacterById(characterId);
            model.addAttribute("character", character);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("individual_characters", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("characters"+characterId,page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "error";
        }


	}

	@RequestMapping(path="/organizations",method = RequestMethod.GET)
	public String organizationsPage(final HttpServletRequest req,final HttpServletResponse resp,Model model){

		System.out.println("hit organizations page");
        if(renderedCache.containsKey("organizations")){
            System.out.println("returning page from cache");
            return renderedCache.get("organizations");
        }
        else {
            List<Organization> organizationList=organizationRepository.getAllOrganizations();
            model.addAttribute("organizationList", organizationList);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("organizations", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("organizations",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/organizations/{organizationId}",method = RequestMethod.GET)
	public String organizationsIndividualPage(final HttpServletRequest req,final HttpServletResponse resp,Model model,@PathVariable Integer organizationId){

		System.out.println("hit individual_organizations page");
        if(renderedCache.containsKey("organizations"+organizationId)){
            System.out.println("returning page from cache");
            return renderedCache.get("organizations"+organizationId);
        }
        else {
            Organization organization=organizationRepository.getOrganizationById(organizationId);
            model.addAttribute("organization", organization);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("individual_organizations", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("organizations"+organizationId,page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/events",method = RequestMethod.GET)
	public String eventsPage(final HttpServletRequest req,final HttpServletResponse resp,Model model){

		System.out.println("hit events page");
        if(renderedCache.containsKey("events")){
            System.out.println("returning page from cache");
            return renderedCache.get("events");
        }
        else {
            List<Event> eventList=eventRepository.getAllEvents();
            model.addAttribute("eventList", eventList);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("events", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("events",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/events/{eventId}",method = RequestMethod.GET)
	public String eventsIndividualPage(final HttpServletRequest req,final HttpServletResponse resp,Model model,@PathVariable Integer eventId){

		System.out.println("hit individual_events page");
        if(renderedCache.containsKey("events"+eventId)){
            System.out.println("returning page from cache");
            return renderedCache.get("events"+eventId);
        }
        else {
            Event event=eventRepository.getEventById(eventId);
            model.addAttribute("event", event);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("individual_events", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("events"+eventId,page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/locations",method = RequestMethod.GET)
	public String locationsPage(final HttpServletRequest req,final HttpServletResponse resp,Model model){

		System.out.println("hit locations page");
        if(renderedCache.containsKey("locations")){
            System.out.println("returning page from cache");
            return renderedCache.get("locations");
        }
        else {
            List<Location> locationList=locationRepository.getAllLocations();
            model.addAttribute("locationList", locationList);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("locations", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("locations",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/locations/{locationId}",method = RequestMethod.GET)
	public String locationsIndividualPage(final HttpServletRequest req,final HttpServletResponse resp,Model model,@PathVariable Integer locationId){

		System.out.println("hit individual_locations page");
        if(renderedCache.containsKey("locations"+locationId)){
            System.out.println("returning page from cache");
            return renderedCache.get("locations"+locationId);
        }
        else {
            Location location=locationRepository.getLocationById(locationId);
            model.addAttribute("location", location);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("individual_locations", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("locations"+locationId,page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

	@RequestMapping(path="/episodes",method = RequestMethod.GET)
	public String episodesPage(final HttpServletRequest req,final HttpServletResponse resp,Model model){

		System.out.println("hit episodes page");
        if(renderedCache.containsKey("episodes")){
            System.out.println("returning page from cache");
            return renderedCache.get("episodes");
        }
        else{

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

            model.addAttribute("seasonList", seasonList);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("episodes", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("episodes",page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";

        }



	}

	@RequestMapping(path="/episodes/{episodeId}",method = RequestMethod.GET)
	public String episodesIndividualPage(final HttpServletRequest req,final HttpServletResponse resp,Model model,@PathVariable Integer episodeId){

		System.out.println("hit individual_episodes page");
        if(renderedCache.containsKey("episodes"+episodeId)){
            System.out.println("returning page from cache");
            return renderedCache.get("episodes"+episodeId);
        }
        else {
            Episode episode=episodeRepository.getEpisodeById(episodeId);
            model.addAttribute("episode", episode);


            View resolvedView;
            MockHttpServletResponse mockResp = new MockHttpServletResponse();
            try {
                resolvedView = viewResolver.resolveViewName("individual_episodes", Locale.US);
                resolvedView.render(model.asMap(), req, mockResp);
                //System.out.println("rendered html : " + mockResp.getContentAsString());
                String page=mockResp.getContentAsString();
                renderedCache.put("episodes"+episodeId,page);
                return page;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "error";
        }

	}

}

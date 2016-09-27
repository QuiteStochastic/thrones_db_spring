package thrones_db_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import thrones_db_spring.model.Character;
import thrones_db_spring.model.Episode;
import thrones_db_spring.model.Organization;
import thrones_db_spring.model.repositories.CharacterRepository;
import thrones_db_spring.model.repositories.EpisodeRepository;
import thrones_db_spring.model.repositories.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliverlee
 */
@Controller
public class PageController {

	@Autowired
	private EpisodeRepository episodeRepository;

	@Autowired
	private CharacterRepository characterRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@RequestMapping(path="/",method = RequestMethod.GET)
	public String mainPage(Model model){

		System.out.println("hit main page");
		//model.addAttribute("test", "hello spring");
		return "main";
	}

	@RequestMapping(path="/about",method = RequestMethod.GET)
	public String aboutPage(Model model){

		System.out.println("hit about page");
		//model.addAttribute("test", "hello spring");
		return "about";
	}

	@RequestMapping(path="/results",method = RequestMethod.GET)
	public String searchPage(Model model) {

		System.out.println("hit search results page");
		//model.addAttribute("test", "hello spring");
		return "search";
	}

	@RequestMapping(path="/characters",method = RequestMethod.GET)
	public String charactersPage(Model model){

		System.out.println("hit characters page");

		List<Character> characterList=characterRepository.getAllCharacters();
		model.addAttribute("characterList", characterList);
		
		return "characters";
	}

	@RequestMapping(path="/characters/{characterId}",method = RequestMethod.GET)
	public String charactersIndividualPage(Model model,@PathVariable Integer characterId){

		System.out.println("hit individual_characters page");

        Character character=characterRepository.getCharacterById(characterId);
        model.addAttribute("character", character);

        return "individual_characters";
	}

	@RequestMapping(path="/organizations",method = RequestMethod.GET)
	public String organizationsPage(Model model){

		System.out.println("hit organizations page");
		List<Organization> organizationList=organizationRepository.getAllOrganizations();
		model.addAttribute("organizationList", organizationList);

		return "organizations";
	}

	@RequestMapping(path="/organizations/{organizationId}",method = RequestMethod.GET)
	public String organizationsIndividualPage(Model model,@PathVariable String organizationId){

		System.out.println("hit individual_organizations page");
		//model.addAttribute("test", "hello spring");
		return "individual_organizations";
	}

	@RequestMapping(path="/events",method = RequestMethod.GET)
	public String eventsPage(Model model){

		System.out.println("hit events page");
		//model.addAttribute("test", "hello spring");
		return "events";
	}

	@RequestMapping(path="/events/{eventId}",method = RequestMethod.GET)
	public String eventsIndividualPage(Model model,@PathVariable String eventId){

		System.out.println("hit individual_events page");
		//model.addAttribute("test", "hello spring");
		return "individual_events";
	}

	@RequestMapping(path="/locations",method = RequestMethod.GET)
	public String locationsPage(Model model){

		System.out.println("hit locations page");
		//model.addAttribute("test", "hello spring");
		return "locations";
	}

	@RequestMapping(path="/locations/{locationId}",method = RequestMethod.GET)
	public String locationsIndividualPage(Model model,@PathVariable String locationId){

		System.out.println("hit individual_locations page");
		//model.addAttribute("test", "hello spring");
		return "individual_locations";
	}

	@RequestMapping(path="/episodes",method = RequestMethod.GET)
	public String episodesPage(Model model){

		System.out.println("hit episodes page");

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
		return "episodes";
	}

	@RequestMapping(path="/episodes/{episodeId}",method = RequestMethod.GET)
	public String episodesIndividualPage(Model model,@PathVariable Integer episodeId){

		System.out.println("hit individual_episodes page");

		Episode episode=episodeRepository.getEpisodeById(episodeId);
		model.addAttribute("episode", episode);

		return "individual_episodes";
	}

}

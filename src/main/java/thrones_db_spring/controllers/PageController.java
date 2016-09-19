package thrones_db_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import thrones_db_spring.model.Episode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliverlee
 */
@Controller
public class PageController {

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
		//model.addAttribute("test", "hello spring");
		return "characters";
	}

	@RequestMapping(path="/characters/{characterId}",method = RequestMethod.GET)
	public String charactersIndividualPage(Model model,@PathVariable String characterId){

		System.out.println("hit individual_characters page");
		//model.addAttribute("test", "hello spring");
		return "individual_characters";
	}

	@RequestMapping(path="/organizations",method = RequestMethod.GET)
	public String organizationsPage(Model model){

		System.out.println("hit organizations page");
		//model.addAttribute("test", "hello spring");
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

		List<Episode> s1List=new ArrayList<Episode>();
		List<Episode> s2List=new ArrayList<Episode>();


		s1List.add(new Episode(1,"test1",1,1,"test s1e1"));
		s1List.add(new Episode(2,"test2",1,2,"test s1e2"));
		s1List.add(new Episode(3,"test3",1,3,"test s1e3"));

		s2List.add(new Episode(11,"test4",2,1,"test s2e1"));
		s2List.add(new Episode(12,"test5",2,2,"test s2e2"));
		s2List.add(new Episode(13,"test6",2,3,"test s2e3"));

		List<List<Episode>> seasonList=new ArrayList<List<Episode>>();

		seasonList.add(s1List);
		seasonList.add(s2List);


		model.addAttribute("seasonList", seasonList);
		return "episodes";
	}

	@RequestMapping(path="/episodes/{episodeId}",method = RequestMethod.GET)
	public String episodesIndividualPage(Model model,@PathVariable String episodeId){

		System.out.println("hit individual_episodes page");
		//model.addAttribute("test", "hello spring");
		return "individual_episodes";
	}

}

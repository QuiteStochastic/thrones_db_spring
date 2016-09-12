package thrones_db_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oliverlee
 */
@Controller
public class MainPageController {

	@RequestMapping(path="/",method = RequestMethod.GET)
	public String mainPage(Model model){

		System.out.println("hit main page");


		model.addAttribute("test", "hello spring");
		return "main";
		/*return "<!DOCTYPE HTML>\n" +
				"<html>" +
				"<head>\n" +
				"    <title>Thrones DB Hello World</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <p>Hello spring boot</p>\n" +
				"</body>\n" +
				"</html>";*/
	}


}

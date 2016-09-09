package thrones_db_spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oliverlee
 */
//@RestController
public class MainPage {

	@RequestMapping(path="/",method = RequestMethod.GET)
	public String mainPage(){

		System.out.println("hit main page");



		return "<!DOCTYPE HTML>\n" +
				"<html>" +
				"<head>\n" +
				"    <title>Thrones DB Hello World</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <p>Hello spring boot</p>\n" +
				"</body>\n" +
				"</html>";
	}


}

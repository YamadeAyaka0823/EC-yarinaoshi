package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.example.domain.WeatherHacks;

@Controller
@RequestMapping("/weatherHacks")
public class WeatherHacksController {
	
    private RestTemplate restTemplate = new RestTemplate();
	
	/** お天気API リクエストURL */
	private static final String URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";
	
	@Autowired
	private HttpSession session;
	
	/** お天気APIサービス呼び出し */
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public String weather(WeatherHacks weatherHacks, Model model) {
		WeatherHacks weatherHacksClass = restTemplate.getForObject(URL, WeatherHacks.class);
		session.setAttribute("weatherHacksClass", weatherHacksClass);
		return "administrator/administratorList";
	}
	

}

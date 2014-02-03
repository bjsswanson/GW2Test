package com.swansonb.game.guildwars2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BuyerController {

	@RequestMapping(value = "/listing", method = RequestMethod.GET,
			produces = "application/json; charset=utf-8")
	public @ResponseBody String listing(@RequestParam("s") String session) throws IOException {
		return "{}";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("jsp/index.jsp");
	}


}
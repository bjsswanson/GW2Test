package com.swansonb.game.guildwars2.controller;

import com.swansonb.game.guildwars2.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BuyerController {

	@Autowired
	BuyerService buyerService;

	@RequestMapping(value = "/listing", method = RequestMethod.GET,
			produces = "application/json; charset=utf-8")
	public @ResponseBody String listing(@RequestParam("session") String session) throws IOException {
		return buyerService.getArmorMasterWork15to20(session).toString();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView index() {
		return new ModelAndView("jsp/index.jsp");
	}
}
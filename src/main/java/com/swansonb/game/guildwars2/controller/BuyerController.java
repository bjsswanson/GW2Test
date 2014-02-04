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

	private static final String TEMP_SESSION = "D669340F-A6E0-4306-A0BE-9DCA4454E48E";

	@Autowired
	BuyerService buyerService;

	@RequestMapping(value = "/listing", method = RequestMethod.GET,
			produces = "application/json; charset=utf-8")
	public @ResponseBody String listing(@RequestParam("session") String session) throws IOException {
		return buyerService.getArmorMasterWork15to20(TEMP_SESSION).toString();
	}

	@RequestMapping(value = "/buy", method = RequestMethod.GET,
			produces = "application/json; charset=utf-8")
	public @ResponseBody String buyArmor(
			@RequestParam String session,
	        @RequestParam int type,
	        @RequestParam int rarity,
	        @RequestParam int min_level,
	        @RequestParam int max_level,
	        @RequestParam int coins) throws IOException {
		return buyerService.buy(session, type, rarity, min_level, max_level, coins);
	}

	@RequestMapping(value = "/buyArmor", method = RequestMethod.GET,
			produces = "application/json; charset=utf-8")
	public @ResponseBody String buyArmor() throws IOException {
		return buyerService.buyArmorMasterWork15to20(TEMP_SESSION);
	}

	@RequestMapping(value = "/buyWeapon", method = RequestMethod.GET,
			produces = "application/json; charset=utf-8")
	public @ResponseBody String buyWeapon() throws IOException {
		return buyerService.buyWeaponMasterWork15to20(TEMP_SESSION);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView index() {
		return new ModelAndView("jsp/index.jsp");
	}
}
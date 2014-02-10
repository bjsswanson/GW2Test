package com.swansonb.game.guildwars2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ComponentScan
@EnableAutoConfiguration
public class GW2Buyer {

	public static void main(String[] args) throws URISyntaxException, IOException {
		SpringApplication.run(com.swansonb.game.guildwars2.GW2Buyer.class, args);

		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().browse(new URI("http://localhost:8080"));
		}
	}
}
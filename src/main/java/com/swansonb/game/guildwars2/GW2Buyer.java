package com.swansonb.game.guildwars2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@ComponentScan
@EnableAutoConfiguration
public class GW2Buyer {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
		SpringApplication.run(com.swansonb.game.guildwars2.GW2Buyer.class, args);
	}
}
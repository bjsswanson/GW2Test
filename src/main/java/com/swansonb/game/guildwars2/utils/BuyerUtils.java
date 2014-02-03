package com.swansonb.game.guildwars2.utils;

public class BuyerUtils {

	public static int goldToCoins(int gold, int silver, int copper) {
		return copper + (silver * 100) + (gold * 100 * 100);
	}

	public static String coinsToGold(int coins) {
		int copper = coins % 100;
		int silver = ((coins - copper) % 10000) / 100;
		int gold = (coins - ((silver * 100) - copper)) / 10000;
		return gold + "g " + silver + "s " + copper + "c";
	}
}


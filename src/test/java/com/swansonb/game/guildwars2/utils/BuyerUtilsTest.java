package com.swansonb.game.guildwars2.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuyerUtilsTest {

	@Test
	public void testGoldToCoins() throws Exception {
		int coins = BuyerUtils.goldToCoins(3, 10, 52);
		assertEquals(31052, coins);
	}

	@Test
	public void testCoinsToGold() throws Exception {
		String gold = BuyerUtils.coinsToGold(31052);
		assertEquals("3g 10s 52c", gold);
	}
}

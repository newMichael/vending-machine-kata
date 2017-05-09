package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReturnCoinsTest {
	
	private VendingMachine vendingMachine;
	private static Coin quarter;
	private static Coin dime;
	private static Coin nickel;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		quarter = new Coin("quarter");
		dime = new Coin("dime");
		nickel = new Coin("nickel");
	}
	
	@Before // setup()
	public void before() throws Exception {
		VendingMachine vendingMachine = new VendingMachine();
		this.vendingMachine = vendingMachine;
		vendingMachine.getMachineBank().add(dime);
		vendingMachine.getMachineBank().add(nickel);
	}

	@Test
	public void machine_returns_no_coins_with_no_balance() {
		vendingMachine.handleReturnCoins();
		assertEquals(0, vendingMachine.getCoinReturn().size());
	}
	
	@Test
	public void machine_returns_all_coins_inserted() {
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(dime);
		vendingMachine.handleCoinInsert(nickel);
		vendingMachine.handleReturnCoins();
		assertEquals(3, vendingMachine.getCoinReturn().size());
		assertEquals(0, vendingMachine.getCurrentTransaction());
	}
	
	@Test
	public void machine_displays_correct_message_after_return() {
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(dime);
		vendingMachine.handleReturnCoins();
		assertEquals("INSERT COIN", vendingMachine.checkDisplay());
		assertEquals(0, vendingMachine.getCurrentTransaction());
	}

}

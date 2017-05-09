package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AcceptCoinsTest {

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
	public void test_for_machine_default_state() {
		assertNotNull(vendingMachine);
		assertTrue(vendingMachine.getCurrentTransaction() == 0);
		assertTrue(vendingMachine.getCoinReturn().size() == 0);
	}

	@Test
	public void machine_returns_invalid_coin() {
		Coin penny = new Coin("penny");
		vendingMachine.handleCoinInsert(penny);
		assertTrue(vendingMachine.getCurrentTransaction() == 0);
		assertTrue(vendingMachine.getCoinReturn().size() == 1);
	}
	
	@Test
	public void machine_accepts_quarters() {
		vendingMachine.handleCoinInsert(quarter);
		assertTrue(vendingMachine.getCoinReturn().size() == 0);
		assertEquals(25, vendingMachine.getCurrentTransaction());
	}
	
	@Test
	public void machine_accepts_dimes() {
		vendingMachine.handleCoinInsert(dime);
		assertTrue(vendingMachine.getCoinReturn().size() == 0);
		assertEquals(10, vendingMachine.getCurrentTransaction());
	}
	
	@Test
	public void machine_accepts_nickels() {
		vendingMachine.handleCoinInsert(nickel);
		assertTrue(vendingMachine.getCoinReturn().size() == 0);
		assertEquals(5, vendingMachine.getCurrentTransaction());
	}
	
	@Test //asserts all valid coins update display, and that it is correct for over $1.00
	public void machine_display_is_correct() {
		assertEquals("INSERT COIN", vendingMachine.checkDisplay());
		vendingMachine.handleCoinInsert(quarter);
		assertEquals("$0.25", vendingMachine.checkDisplay());
		vendingMachine.handleCoinInsert(dime);
		assertEquals("$0.35", vendingMachine.checkDisplay());
		vendingMachine.handleCoinInsert(nickel);
		assertEquals("$0.40", vendingMachine.checkDisplay());
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		assertEquals("$1.15", vendingMachine.checkDisplay());
	}

}

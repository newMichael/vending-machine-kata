package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExactChangeOnlyTest {
	
	private VendingMachine vendingMachine;
	private static Coin quarter;
	private static Coin dime;
	private static Coin nickel;
	private static Product cola;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		quarter = new Coin("quarter");
		dime = new Coin("dime");
		nickel = new Coin("nickel");
		cola = new Product("Cola", 100);
	}
	
	@Before // setup()
	public void before() throws Exception {
		VendingMachine vendingMachine = new VendingMachine();
		this.vendingMachine = vendingMachine;
	}

	@Test
	public void machine_displays_exact_change_message() {
		assertEquals("EXACT CHANGE ONLY", vendingMachine.checkDisplay());
	}

}

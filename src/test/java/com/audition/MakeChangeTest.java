package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MakeChangeTest {
	
	private VendingMachine vendingMachine;
	private static Coin quarter;
	private static Coin dime;
	private static Coin nickel;
	private static Product candy;
	
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		quarter = new Coin("quarter");
		dime = new Coin("dime");
		nickel = new Coin("nickel");
		candy = new Product("Candy", 65);
	}
	
	@Before // setup()
	public void before() throws Exception {
		VendingMachine vendingMachine = new VendingMachine();
		this.vendingMachine = vendingMachine;
		vendingMachine.getMachineBank().add(dime);
		vendingMachine.getMachineBank().add(nickel);
	}

	@Test
	public void machine_makes_change_of_ten_cents() {
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleSelectProduct(candy);
		assertEquals("dime", vendingMachine.getCoinReturn().get(0).getIdentity());
		assertEquals(0, vendingMachine.getCurrentTransaction());
	}

}

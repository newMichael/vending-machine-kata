package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SoldOutTest {
	
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
		cola.setInventory(0);
	}
	
	@Before // setup()
	public void before() throws Exception {
		VendingMachine vendingMachine = new VendingMachine();
		this.vendingMachine = vendingMachine;
		vendingMachine.getMachineBank().add(dime);
		vendingMachine.getMachineBank().add(nickel);
	}

	@Test
	public void machine_displays_sold_out_on_empty_product() {
		vendingMachine.handleSelectProduct(cola);
		assertEquals("SOLD OUT", vendingMachine.checkDisplay());
		assertEquals("INSERT COIN", vendingMachine.checkDisplay());

	}
	
	@Test
	public void machine_does_not_complete_transaction_on_sold_out_product() {
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleSelectProduct(cola);
		assertEquals("SOLD OUT", vendingMachine.checkDisplay());
		assertEquals(100, vendingMachine.getCurrentTransaction());
	}

}

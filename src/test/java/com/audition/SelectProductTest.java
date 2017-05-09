package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SelectProductTest {
	
	private VendingMachine vendingMachine;
	private static Coin quarter;
	private static Coin dime;
	private static Coin nickel;
	private static Product cola;
	private static Product chips;
	private static Product candy;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		quarter = new Coin("quarter");
		dime = new Coin("dime");
		nickel = new Coin("nickel");
		cola = new Product("Cola", 100);
		chips = new Product("Chips", 50);
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
	public void not_enough_money_when_product_selected() {
		vendingMachine.handleSelectProduct(chips);
		assertEquals("PRICE: $0.50", vendingMachine.checkDisplay());
	}
	
	@Test
	public void display_shows_not_enough_money_message_once() {
		vendingMachine.handleSelectProduct(chips);
		assertEquals("PRICE: $0.50", vendingMachine.checkDisplay());
		assertEquals("INSERT COIN", vendingMachine.checkDisplay());
	}
	
	@Test
	public void display_shows_not_enough_money_message_once_with_some_money_inserted() {
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleSelectProduct(chips);
		assertEquals("PRICE: $0.50", vendingMachine.checkDisplay());
		assertEquals("$0.25", vendingMachine.checkDisplay());
	}
	
	@Test
	public void display_shows_thank_you_message() {
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleCoinInsert(quarter);
		vendingMachine.handleSelectProduct(cola);
		assertEquals("THANK YOU", vendingMachine.checkDisplay());
		assertEquals("INSERT COIN", vendingMachine.checkDisplay());
	}

}

package com.audition;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
	
	private int currentTransaction;
	private List<Coin> customersCoins = new ArrayList<>();
	private List<Coin> machineBank = new ArrayList<>();
	private List<Coin> coinReturn = new ArrayList<>();
	private String display = "INSERT COIN";
	

	public VendingMachine() {
	}
	
	//** CUSTOMER ACTIONS **//
	
	public void handleCoinInsert(Coin coin) {
		if(coinIsValid(coin.getIdentity())) {
			customersCoins.add(coin);
			updateCurrentTransaction(coin.getIdentity());
		} else {
			coinReturn.add(coin);
		}
	}
	
	public void handleSelectProduct(Product product) {
		if(product.getInventory() < 1) {
			display = "SOLD OUT";
		} else if(!changeCanBeMade() && currentTransaction != product.getCost()) {
			display = "EXACT CHANGE ONLY";
		} else if(currentTransaction >= product.getCost()) {
			display = "THANK YOU";
			addCustomersCoinsToMachineBank();
			makeChange(product);
			currentTransaction = 0;
		} else {
			display = "PRICE: $" + product.getCost()/100 + "." + product.getCost()%100; 
		}
	}
	

	public String checkDisplay() {
		if(displayShownOnce()) {
			String oneTimeMessage = display;
			display = "";
			return oneTimeMessage;
		}
		if(!changeCanBeMade() && currentTransaction == 0) {
			display = "EXACT CHANGE ONLY";
		} else if(currentTransaction == 0) {
			display = "INSERT COIN";
		} else {
			display = "$" + currentTransaction/100 + "." + currentTransaction%100;
		}
		return display;
	}
	
	public void handleReturnCoins() {
		if (!customersCoins.isEmpty()) {
			for (Coin coin : customersCoins) {
				coinReturn.add(coin);
			}
			customersCoins.clear();
			currentTransaction = 0;
		}
	}
	
	
	//** GETTERS **//

	public List<Coin> getCustomersCoins() {
		return customersCoins;
	}
	
	public List<Coin> getMachineBank() {
		return machineBank;
	}
	
	public String getDisplay() {
		return display;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}
	
	public int getCurrentTransaction() {
		return currentTransaction;
	}
	
	public List<Coin> getCoinReturn() {
		return coinReturn;
	}
	
	//** PRIVATE METHODS **//
	
	private boolean coinIsValid(String coinName) {
		if (coinName.equals("nickel") || coinName.equals("dime") || coinName.equals("quarter")) {
			return true;
		} else {
			return false;
		}
	}

	private void updateCurrentTransaction(String coinName) {
		if (coinName.equals("nickel")) {
			currentTransaction += 5;
		}
		if (coinName.equals("dime")) {
			currentTransaction += 10;
		}
		if (coinName.equals("quarter")) {
			currentTransaction += 25;
		}
	}

	private boolean displayShownOnce() {
		return display.equals("THANK YOU") || display.equals("SOLD OUT") || display.startsWith("PRICE");
	}
	
	private void makeChange(Product product) {
		int changeDue;
		changeDue = currentTransaction - product.getCost();
		while(changeDue > 0) {
			if(changeDue >= 25) {
				coinReturn.add(new Coin("quarter"));
				changeDue -= 25;
				removeCoinFromBank("quarter");
			} else if(changeDue >= 10) {
				coinReturn.add(new Coin("dime"));
				changeDue -= 10;
				removeCoinFromBank("dime");
			} else {
				coinReturn.add(new Coin("nickel"));
				changeDue -= 5;
				removeCoinFromBank("nickel");
			}
		}
	}
	
	private void removeCoinFromBank(String identity) {
		for(Coin coin : machineBank) {
			if (coin.getIdentity().equals(identity)) {
				machineBank.remove(coin);
				break;
			}
		}
	}
	
	private void addCustomersCoinsToMachineBank() {
		if(!customersCoins.isEmpty()) {
			for(Coin coin: customersCoins) {
				machineBank.add(coin);
			}
			customersCoins.clear();
		}
	}
	
	private boolean changeCanBeMade() {
		int dimeCount = 0;
		int nickelCount = 0;
		for(Coin coin: machineBank) {
			if(coin.getIdentity().equals("dime")) {
				dimeCount++;
			}
			if(coin.getIdentity().equals("nickel")) {
				nickelCount++;
			}
		}
		if((dimeCount > 0 && nickelCount > 0) || nickelCount > 1) {
			return true;
		} else {
			return false;
		}
	}
}

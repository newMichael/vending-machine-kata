package com.audition;

public class Product {
	
	private String name;
	private int cost;
	private int inventory;
	
	public Product (String name, int cost) {
		this.name = name;
		this.cost = cost;
		inventory = 2;
	}
	
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

}

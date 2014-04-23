package com.team3.basics;

public class MenuItem {
	public String itemname,itemtype;
	public double itemprice;
	/**
	 * @param itemname
	 * @param itemtype
	 * @param itemscost
	 * @param itemmcost
	 * @param itemlcost
	 */
	public MenuItem(String itemname, String itemtype, double itemprice) {
		super();
		this.itemname = itemname;
		this.itemtype = itemtype;
		this.itemprice = itemprice;
	
	}
	public String getName() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public double getItemscost() {
		return itemprice;
	}
	public void setItemscost(double itemscost) {
		this.itemprice = itemprice;
	}
	
	

}

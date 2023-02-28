package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class CommodityModel {
	/*資料表欄位*/
	private int item_index;
	private String type;
	private String brand;
	private String location;
	private int cost;
	private String purchasetime;
	private int life_month;
	private String description;
	private String retire;
	/*資料表存取方法getter與setter*/
	public int get_item_index() {
		return this.item_index;
	}
	public void set_item_index(int Item_index) {
		this.item_index=Item_index;
	}
	public String get_type() {
		return this.type;
	}
	public void set_type(String Type) {
		this.type=Type;
	}
	public String get_brand() {
		return this.brand;
	}
	public void set_brand(String Brand) {
		this.brand=Brand;
	}
	public String get_location() {
		return this.location;
	}
	public void set_location(String Location) {
		this.location=Location;
	}
	public int get_cost() {
		return this.cost;
	}
	public void set_cost(int Cost) {
		this.cost=Cost;
	}
	public String get_purchasetime() {
		return this.purchasetime;
	}
	public void set_purchasetime(String Purchasetime) {
		this.purchasetime=Purchasetime;
	}
	public int get_life_month() {
		return this.life_month;
	}
	public void set_life_month(int Life_month) {
		this.life_month=Life_month;
	}
	public String get_description() {
		return this.description;
	}
	public void set_description(String Description) {
		this.description=Description;
	}
	public String get_retire() {
		return this.retire;
	}
	public void set_retire(String Retire) {
		this.retire=Retire;
	}
}

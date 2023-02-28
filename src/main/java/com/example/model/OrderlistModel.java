package com.example.model;

import java.text.SimpleDateFormat;
import org.springframework.stereotype.Component;

@Component
public class OrderlistModel {
	/*資料表欄位*/
	private int order_index;
	private String account;
	private String address;
	private String order_name;
	private int item_index;
	private int item_rent_time;
	private String start;
	private String end;
	private int price;
	/*資料表存取方法getter與setter*/
	public int get_order_index() {
		return this.order_index;
	}
	public void set_order_index(int Order_index) {
		this.order_index=Order_index;
	}
	public String get_account() {
		return this.account;
	}
	public void set_account(String Account) {
		this.account=Account;
	}
	public String get_address() {
		return this.address;
	}
	public void set_address(String Address) {
		this.address=Address;
	}
	public String get_order_name() {
		return this.order_name;
	}
	public void set_order_name(String Order_name) {
		this.order_name=Order_name;
	}
	public int get_item_index() {
		return this.item_index;
	}
	public void set_item_index(int Item_index) {
		this.item_index=Item_index;
	}
	public int get_item_rent_time() {
		return this.item_rent_time;
	}
	public void set_item_rent_time(int Item_rent_time) {
		this.item_rent_time=Item_rent_time;
	}
	public String get_start() {
		return this.start;
	}
	public void set_start(String Start) {
		this.start=Start;
	}
	public String get_end() {
		return this.end;
	}
	public void set_end(String End) {
		this.end=End;
	}
	public int get_price() {
		return this.price;
	}
	public void set_price(int Price) {
		this.price=Price;
	}
	public Boolean check_expire() {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date today=new java.util.Date();
			java.util.Date parsedDate=formatter.parse(get_end());
			long today_millisecond=today.getTime();
			long Date_millisecond=parsedDate.getTime();
			if(today_millisecond>=Date_millisecond) {
				return true;
			}
		}
		catch(java.text.ParseException e){
			e.printStackTrace();
		}
		return false;
	}
}

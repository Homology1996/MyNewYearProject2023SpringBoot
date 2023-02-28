package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class MemberModel {
	/*資料表欄位*/
	private int member_index;
	private String name;
	private String phone;
	private String address;
	private String account;
	private String password;
	private String order_names;
	/*資料表存取方法getter與setter*/
	public int get_member_index() {
		return this.member_index;
	}
	public void set_member_index(int Member_index) {
		this.member_index=Member_index;
	}
	public String get_name() {
		return this.name;
	}
	public void set_name(String Name) {
		this.name=Name;
	}
	public String get_phone() {
		return this.phone;
	}
	public void set_phone(String Phone) {
		this.phone=Phone;
	}
	public String get_address() {
		return this.address;
	}
	public void set_address(String Address) {
		this.address=Address;
	}
	public String get_account() {
		return this.account;
	}
	public void set_account(String Account) {
		this.account=Account;
	}
	public String get_password() {
		return this.password;
	}
	public void set_password(String Password) {
		this.password=Password;
	}
	public String get_order_names() {
		return this.order_names;
	}
	public void set_order_names(String Order_names) {
		this.order_names=Order_names;
	}
}

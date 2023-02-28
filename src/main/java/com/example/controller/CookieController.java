package com.example.controller;

import jakarta.servlet.http.Cookie;/*處理cookie*/
import jakarta.servlet.http.HttpServletRequest;/*處理http的request*/
import jakarta.servlet.http.HttpServletResponse;/*處理http的response*/

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*資料庫查詢結果*/

/*呼叫資料庫時使用*/
import com.example.model.*;
import com.example.repository.*;
import com.example.service.*;

@Controller
public class CookieController {
	@Autowired
	private HttpServletRequest request;		/*這裡幫我們注入了一個request，讓我們靠這個request來處理cookie*/
	@Autowired
	private HttpServletResponse response;	/*這裡幫我們注入了一個response，讓我們靠這個response來處理cookie*/
	@Autowired
	CommodityModel commodityModel;
	@Autowired
	CommodityRepository commodityRepository;
	@Autowired
	CommodityService commodityService;
	@Autowired
	CommodityController commodityController;
	@Autowired
	MemberModel memberModel;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MemberService memberService;
	@Autowired
	MemberController memberController;
	@Autowired
	OrderlistModel orderlistModel;
	@Autowired
	OrderlistRepository orderlistRepository;
	@Autowired
	OrderlistService orderlistService;
	@Autowired
	OrderlistController orderlistController;
	
	public Boolean Check_Cookie_With_Name(String CookieName) {
		Cookie[] cookies=request.getCookies();
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals(CookieName)) {
				/*只要有return，就會結束整個程式*/
				return true;
			}
		}
		/*如果全部cookie檢查完都沒有符合的結果，就回傳false*/
		return false;
	}
	public String Get_Cookie_With_Name(String CookieName) {
		Cookie[] cookies=request.getCookies();
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals(CookieName)) {
				return cookies[i].getValue();
			}
		}
		return "";
	}
	public Boolean Check_Login(){
		SqlRowSet memberTable=memberService.selectMember(memberModel);
		Cookie[] cookies=request.getCookies();
		while(memberTable.next()) {
			for(int i=0;i<cookies.length;i++) {
				if((memberTable.getString("account").equals(cookies[i].getName()))&&(memberTable.getString("password").equals(cookies[i].getValue()))){
					return true;
				}
			}
		}
		return false;
	}
	public String Get_Login_Account(){
		SqlRowSet memberTable=memberService.selectMember(memberModel);
		Cookie[] cookies=request.getCookies();
		while(memberTable.next()) {
			for(int i=0;i<cookies.length;i++) {
				if((memberTable.getString("account").equals(cookies[i].getName()))&&(memberTable.getString("password").equals(cookies[i].getValue()))){
					return cookies[i].getName();
				}
			}
		}
		return "";
	}
	public String Get_Login_Password(){
		SqlRowSet memberTable=memberService.selectMember(memberModel);
		Cookie[] cookies=request.getCookies();
		while(memberTable.next()) {
			for(int i=0;i<cookies.length;i++) {
				if((memberTable.getString("account").equals(cookies[i].getName()))&&(memberTable.getString("password").equals(cookies[i].getValue()))){
					return cookies[i].getValue();
				}
			}
		}
		return "";
	}
	public void Add_Cookie(String CookieName, String CookieValue) {
		Cookie cookie=new Cookie(CookieName,CookieValue);
		response.addCookie(cookie);
	}
	public void Add_Cookie_With_Expire(String CookieName, String CookieValue, int day) {
		int second=day*24*60*60;
		Cookie cookie=new Cookie(CookieName,CookieValue);
		cookie.setMaxAge(second);
		response.addCookie(cookie);
	}
	public void Delete_Cookie(String CookieName) {
		Add_Cookie_With_Expire(CookieName,"",-1);
	}
}

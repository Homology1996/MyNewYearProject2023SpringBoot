package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import jakarta.servlet.http.Cookie;/*處理cookie*/
import jakarta.servlet.http.HttpServletRequest;/*處理http的request*/
import jakarta.servlet.http.HttpServletResponse;/*處理http的response*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;/*讀取表單傳送資料*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;/*在controller控制使用的變數，並傳遞至view做使用*/
import org.springframework.jdbc.support.rowset.SqlRowSet;/*資料庫查詢結果*/

/*呼叫資料庫時使用*/
import com.example.model.*;
import com.example.repository.*;
import com.example.service.*;

@Controller
public class ControllerClass {
	/*
	@Autowired功能類似生成物件
	用在HttpServletRequest就能拿來模擬使用者目前發出了request
	用在HttpServletResponse就能拿來模擬Controller發出response
	用在class，則是生成該類別的物件
	*/
	@Autowired
	private HttpServletRequest request;
	@SuppressWarnings("unused")
	@Autowired
	private HttpServletResponse response;
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
	@Autowired
	CookieController cookieController;
	
	public class Depreciation {
		double New;
		double Old;
		Depreciation(double NEW, double OLD){
			this.New=NEW;
			this.Old=OLD;
		}
		long day=1000*60*60*24L;
		long month=day*30L;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");/*格式化物件*/
		public double rate(int Life_month, String Purchasetime, String Now) {
			double New=this.New;
			double Old=this.Old;
			double rate=0.0;
			try {
	            java.util.Date parsedPurchasetime=formatter.parse(Purchasetime);
	            java.util.Date parsedNow=formatter.parse(Now);
	            /*算出特定日期距離1970-01-01有幾微秒*/
	            long Purchasetime_millisecond=parsedPurchasetime.getTime();
	            long Now_millisecond=parsedNow.getTime();
	            long Now_Pur_Diff_milli=Now_millisecond-Purchasetime_millisecond;
	            /*把上面的微秒換成月*/
	            double Now_Pur_Diff_month=(Now_Pur_Diff_milli/month);
	            if(Now_millisecond<Purchasetime_millisecond){
	    			/*對應到現在的時間比購買時間還早，也就是錯誤的時間*/
	    			rate=0.0;
	    		}
	    		else if(Now_millisecond<Purchasetime_millisecond+Life_month*month){
	    			/*對應到現在的時間落在起始與結束時間之內*/
	    			rate=New-((New-Old)*Now_Pur_Diff_month)/Life_month;
	    		}
	    		else{
	    			/*對應到現在的時間比預期壽命還大，那就直接回傳最舊的費率*/
	    			rate=Old;
	    		}
	        } 
			catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }
			return rate;
		}
		public int compute(int Cost, int Life_month, int item_rent_time, String Purchasetime, String Start) {
			double average=Cost/Life_month;
			double sum=0.0;
			try{
			    java.util.Date start = formatter.parse(Start);
			    long startmillisec = start.getTime();
			    for(int i=0;i<item_rent_time;i++){
			    	long nowmillisec=startmillisec+i*month;
			    	java.util.Date milli_to_date = new java.util.Date(nowmillisec);  
			        String Now=formatter.format(milli_to_date);
			    	double rate_this_month=rate(Life_month, Purchasetime, Now);
			    	sum+=average*rate_this_month;
			    }
			}
			catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }
			return (int)Math.round(sum);
		}
	}
	
	/*網頁路徑映射*/
	@GetMapping("/")
	public String to_index() {
		/*thymeleaf會去找回傳字串為名稱的html來執行*/
		return "index";
	}
	@GetMapping("/products")
	public String to_products(Model model) {
		/*呼叫商品資料表並添加至頁面*/
		ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
		model.addAttribute("CommodityTable",CommodityTable);
		return "products";
	}
	@GetMapping("/select")
	public String to_select(Model model) {
		String word=cookieController.Get_Cookie_With_Name("select");
		if(word.equals("bed")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_bed();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("table")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_table();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("refrigerator")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_refrigerator();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("television")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_television();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("air_conditioner")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_air_conditioner();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("laundry_machine")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_laundry_machine();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("low_price")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_low_price();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("medium_price")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_medium_price();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else if(word.equals("high_price")) {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable_high_price();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		else {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
			model.addAttribute("CommodityTable",CommodityTable);
		}
		return "select";
	}
	@GetMapping("/detail")
	public String to_detail(Model model) {
		String itemID=cookieController.Get_Cookie_With_Name("product");
		if(itemID.length()==0) {
			/*避免url暴力破解而出現錯誤，所以設置防呆機制*/
			itemID="1";
		}
		String imgsrc="images/pic"+itemID+".jpg";
		String orderID="order"+itemID;
		String MaxEnd=orderlistController.MaxEnd(itemID);
		String status=orderlistController.Expire_Status(itemID);
		model.addAttribute("itemID",itemID);
		model.addAttribute("imgsrc",imgsrc);
		model.addAttribute("orderID",orderID);
		model.addAttribute("MaxEnd",MaxEnd);
		model.addAttribute("status",status);
		ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
		CommodityModel ThisRow=new CommodityModel();
		for(int index=0;index<CommodityTable.size();index++) {
			if(itemID.equals(Integer.toString(CommodityTable.get(index).get_item_index()))) {
				ThisRow=CommodityTable.get(index);
				model.addAttribute("ThisRow",ThisRow);
				break;
			}
		}
		return "detail";
	}
	@GetMapping("/cart")
	public String to_cart(Model model) {
		/*商品編號清單*/
		ArrayList<String> ItemID_list=new ArrayList<String>();
		Cookie[] cookies=request.getCookies();
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().contains("order")){/*contains=字串裡面是否含有特定子字串*/
				ItemID_list.add(cookies[i].getValue());
			}
		}
		model.addAttribute("ItemID_list",ItemID_list);
		/*設定時間篩選器的起始日期與結束日期*/
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String> DefaultDate_list=new ArrayList<String>();
		ArrayList<String> MonthAfter_list=new ArrayList<String>();
		for(int index=0;index<ItemID_list.size();index++) {
			String MaxEnd=orderlistController.MaxEnd(ItemID_list.get(index));
			String Today=formatter.format(new java.util.Date());
			try {
				java.util.Date parsedMaxEnd=formatter.parse(MaxEnd);
				java.util.Date parsedToday=formatter.parse(Today);
				long MaxEnd_millisecond=parsedMaxEnd.getTime();
	            long Today_millisecond=parsedToday.getTime();
	            if(Today_millisecond>MaxEnd_millisecond){
	            	String DefaultDate=Today;
	            	DefaultDate_list.add(DefaultDate);
	            	long MonthAfter_millisecond=Today_millisecond+1000*60*60*24*30L;
	            	String MonthAfter=formatter.format(MonthAfter_millisecond);
	            	MonthAfter_list.add(MonthAfter);
	            }
	            else{
	            	/*這裡對應到的是續租的部分*/
	            	String DefaultDate=MaxEnd;
	            	DefaultDate_list.add(DefaultDate);
	            	long MonthAfter_millisecond=MaxEnd_millisecond+1000*60*60*24*30L;
	            	String MonthAfter=formatter.format(MonthAfter_millisecond);
	            	MonthAfter_list.add(MonthAfter);
	            }
			}
			catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }
		}
		model.addAttribute("DefaultDate_list",DefaultDate_list);
        model.addAttribute("MonthAfter_list",MonthAfter_list);
        /*設定日期列表*/
		class CombineItem{
			String ItemID;
			String DefaultDate;
			String MonthAfter;
			@SuppressWarnings("unused")
			public String get_ItemID() {
				return this.ItemID;
			}
			public void set_ItemID(String itemID) {
				this.ItemID=itemID;
			}
			@SuppressWarnings("unused")
			public String get_DefaultDate() {
				return this.DefaultDate;
			}
			public void set_DefaultDate(String defaultDate) {
				this.DefaultDate=defaultDate;
			}
			@SuppressWarnings("unused")
			public String get_MonthAfter() {
				return this.MonthAfter;
			}
			public void set_MonthAfter(String monthAfter) {
				this.MonthAfter=monthAfter;
			}
		}
		ArrayList<CombineItem> Combine_list=new ArrayList<CombineItem>();
		for(int index=0;index<ItemID_list.size();index++) {
			CombineItem item=new CombineItem();
			item.set_ItemID(ItemID_list.get(index));
			item.set_DefaultDate(DefaultDate_list.get(index));
			item.set_MonthAfter(MonthAfter_list.get(index));
			Combine_list.add(item);
		}
		model.addAttribute("Combine_list",Combine_list);
		/*添加商品資料表*/
		ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
		model.addAttribute("CommodityTable",CommodityTable);
		/*設定登入狀態*/
		Boolean check_login=cookieController.Check_Login();
		model.addAttribute("check_login",check_login);
		return "cart";
	}
	@PostMapping("/payment")
	public String to_payment(Model model) {
		Enumeration<String> ParameterNames=request.getParameterNames();/*讀取多個送出結果*/
		ArrayList<String> ID_Start_list=new ArrayList<String>();
		ArrayList<String> ID_Month_list=new ArrayList<String>();
		ArrayList<String> ID_End_list=new ArrayList<String>();
		String ID_Start_String="";	/*作為表單傳送字串*/
		String ID_Month_String="";	/*作為表單傳送字串*/
		String ID_End_String="";	/*作為表單傳送字串*/
		while(ParameterNames.hasMoreElements()){
			String ParameterName=(String)ParameterNames.nextElement();
			String ParameterValue=request.getParameter(ParameterName);
			if(ParameterValue.contains("-")) {
				ID_Start_String=ID_Start_String+","+ParameterName.substring(5)+"="+ParameterValue;
				ID_Start_list.add(ParameterName.substring(5)+"="+ParameterValue);
			}
			else {
				ID_Month_String=ID_Month_String+","+ParameterName.substring(5)+"="+ParameterValue;
				ID_Month_list.add(ParameterName.substring(5)+"="+ParameterValue);
			}
		}
		/*去除開頭逗號*/
		if(ID_Start_String.length()*ID_Month_String.length()!=0) {
			ID_Start_String=ID_Start_String.substring(1);
			ID_Month_String=ID_Month_String.substring(1);
			/*表單沒有辦法傳送物件，那就傳送字串來記錄資料，之後再拆成陣列處理*/
			model.addAttribute("ID_Start_String",ID_Start_String);
			model.addAttribute("ID_Month_String",ID_Month_String);
		}
		for(int index=0;index<ID_Start_list.size();index++) {
			String ID_Start=ID_Start_list.get(index);
			String ID=ID_Start.split("=")[0];
			String Start=ID_Start.split("=")[1];
			String ID_Month=ID_Month_list.get(index);
			int Month=Integer.parseInt(ID_Month.split("=")[1]);
			/*計算結束時間*/
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsedStart=formatter.parse(Start);
				long Start_millisecond=parsedStart.getTime();
				long End_millisecond=Start_millisecond+Month*1000*60*60*24*30L;
				String End=formatter.format(End_millisecond);
				ID_End_String=ID_End_String+","+ID+"="+End;
				ID_End_list.add(ID+"="+End); 
			}
			catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }
		}
		/*去除開頭逗號*/
		if(ID_End_String.length()!=0) {
			ID_End_String=ID_End_String.substring(1);
			model.addAttribute("ID_End_String", ID_End_String);
		}
		/*設定日期列表*/
		class CombineInfo{
			String Start;
			String Month;
			String End;
			@SuppressWarnings("unused")
			public String get_Start() {
				return this.Start;
			}
			public void set_Start(String start) {
				this.Start=start;
			}
			@SuppressWarnings("unused")
			public String get_Month() {
				return this.Month;
			}
			public void set_Month(String month) {
				this.Month=month;
			}
			@SuppressWarnings("unused")
			public String get_End() {
				return this.End;
			}
			public void set_End(String end) {
				this.End=end;
			}
		}
		ArrayList<CombineInfo> ID_Info_list=new ArrayList<CombineInfo>();
		for(int index=0;index<ID_Start_list.size();index++) {
			CombineInfo info=new CombineInfo();
			info.set_Start(ID_Start_list.get(index));
			info.set_Month(ID_Month_list.get(index));
			info.set_End(ID_End_list.get(index));
			ID_Info_list.add(info);
		}
		model.addAttribute("ID_Info_list",ID_Info_list);
		/*增加折舊物件*/
		Depreciation Depreciation=new Depreciation(1.5,0.7);
		model.addAttribute("Depreciation",Depreciation);
		/*添加商品資料表*/
		ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
		model.addAttribute("CommodityTable",CommodityTable);
		/*計算總價格*/
		class TotalPrice{
			int Sum=0;
			@SuppressWarnings("unused")
			public Boolean add(int price) {
				this.Sum+=price;
				return true;
			}
			@SuppressWarnings("unused")
			public int outcome() {
				return this.Sum;
			}
		}
		TotalPrice TotalPrice=new TotalPrice();
		model.addAttribute("TotalPrice",TotalPrice);
		return "payment";
	}
	@PostMapping("/payment_process")
	public String to_payment_process(
		@RequestParam(value="ID_Start_String",required=true) String ID_Start_String,
		@RequestParam(value="ID_Month_String",required=true) String ID_Month_String,
		@RequestParam(value="ID_End_String",required=true) String ID_End_String,
		@RequestParam(value="Sum",required=false) int Sum,
		Model model) {
		if((ID_Start_String.length()==0)||(ID_Month_String.length()==0)||(ID_End_String.length()==0)) {
			return "payment_fail";
		}
		else {
			ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
			ArrayList<MemberModel> MemberTable=memberController.Call_MemberTable();
			ArrayList<OrderlistModel> OrderlistTable=orderlistController.Call_OrderlistTable();
			String[] ID_Start_array=ID_Start_String.split(",");
			String[] ID_Month_array=ID_Month_String.split(",");
			String[] ID_End_array=ID_End_String.split(",");
			/*讓jQuery把訂單cookie清空*/
			int CommodityMaxID=CommodityTable.size();
			model.addAttribute("CommodityMaxID",CommodityMaxID);
			/*找出最大訂單編號與訂單名稱*/
			int Orderlist_Max_order_index=OrderlistTable.size();
			String Orderlist_Max_order_name=OrderlistTable.get(Orderlist_Max_order_index-1).get_order_name();
			int Orderlist_Max_order_name_number=Integer.parseInt(Orderlist_Max_order_name.substring(5));
			int Orderlist_New_order_name_number=Orderlist_Max_order_name_number+1;
			String Orderlist_New_order_name="order"+Integer.toString(Orderlist_New_order_name_number);
			/*確認登入帳號與地址*/
			String LoginAccount=cookieController.Get_Login_Account();
			String Address="";
			/*更改會員訂單資訊*/
			MemberModel UpdateMemberModel=new MemberModel();
			for(int i=0;i<MemberTable.size();i++) {
				if(MemberTable.get(i).get_account().equals(LoginAccount)) {
					Address=MemberTable.get(i).get_address();
					UpdateMemberModel=MemberTable.get(i);
					if(UpdateMemberModel.get_order_names().length()==0) {
						UpdateMemberModel.set_order_names(Orderlist_New_order_name);
					}
					else {
						UpdateMemberModel.set_order_names(UpdateMemberModel.get_order_names()+","+Orderlist_New_order_name);
					}
				}
			}
			memberRepository.UpdateOrder_NamesByAccount(UpdateMemberModel);
			/*新增訂單資料表資訊*/
			int order_index_insert=Orderlist_Max_order_index;
			Depreciation Depreciation=new Depreciation(1.5,0.7);
			for(int j=0;j<ID_Month_array.length;j++) {
				order_index_insert++;
				String ID=ID_Month_array[j].split("=")[0];
				String Month=ID_Month_array[j].split("=")[1];
				String start=ID_Start_array[j].split("=")[1];
				String end=ID_End_array[j].split("=")[1];
				int item_index=Integer.parseInt(ID);
				int item_rent_time=Integer.parseInt(Month);
				for(int k=0;k<CommodityTable.size();k++) {
					if(CommodityTable.get(k).get_item_index()==item_index) {
						int cost=CommodityTable.get(k).get_cost();
						int life_month=CommodityTable.get(k).get_life_month();
						String purchasetime=CommodityTable.get(k).get_purchasetime();
						int price=Depreciation.compute(cost,life_month,item_rent_time,purchasetime,start);
						OrderlistModel NewOrder=new OrderlistModel();
						NewOrder.set_order_index(order_index_insert);
						NewOrder.set_account(LoginAccount);
						NewOrder.set_address(Address);
						NewOrder.set_order_name(Orderlist_New_order_name);
						NewOrder.set_item_index(item_index);
						NewOrder.set_item_rent_time(item_rent_time);
						NewOrder.set_start(start);
						NewOrder.set_end(end);
						NewOrder.set_price(price);
						orderlistRepository.Orderlistinsert(NewOrder);
					}
				}
			}
			return "payment_successful";
		}
	}
	@GetMapping("/member")
	public String to_member(Model model) {
		/*添加登入會員的資料到頁面*/
		SqlRowSet memberTable=memberService.selectMember(memberModel);
		Cookie[] cookies=request.getCookies();
		Boolean Login_status=false;/*避免url暴力破解而出現錯誤，所以設置防呆機制*/
		while(memberTable.next()) {
			for(int i=0;i<cookies.length;i++) {
				if((memberTable.getString("account").equals(cookies[i].getName()))&&(memberTable.getString("password").equals(cookies[i].getValue()))){
					model.addAttribute("account",memberTable.getString("account"));
					model.addAttribute("password",memberTable.getString("password"));
					Login_status=true;
				}
			}
		}
		if(!Login_status) {
			/*避免url暴力破解而出現錯誤，所以設置防呆機制*/
			return "redirect:/login";
		}
		/*添加商品資料表*/
		ArrayList<CommodityModel> CommodityTable=commodityController.Call_CommodityTable();
		model.addAttribute("CommodityTable",CommodityTable);
		/*添加會員資料表*/
		ArrayList<MemberModel> MemberTable=memberController.Call_MemberTable();
		model.addAttribute("MemberTable",MemberTable);
		/*添加訂單資料表*/
		ArrayList<OrderlistModel> OrderlistTable=orderlistController.Call_OrderlistTable();
		model.addAttribute("OrderlistTable",OrderlistTable);
		return "member";
	}
	@GetMapping("/login")
	public String to_login(Model model) {
		return cookieController.Check_Login() ? "redirect:/member" : "login";
	}
	@PostMapping("/login_verify")
	public String to_login_verify(
		@RequestParam(value="account",required=false) String account,
		@RequestParam(value="password",required=false) String password,
		@RequestParam(value="remember_me",required=false,defaultValue="No") String remember_me, 
		Model model) {
		/*這裡因為remember_me有可能為null，所以先給它預設值*/
		ArrayList<MemberModel> MemberTable=memberController.Call_MemberTable();
		for(int index=0;index<MemberTable.size();index++) {
			MemberModel ThisRow=MemberTable.get(index);
			if((ThisRow.get_account().equals(account))&&(ThisRow.get_password().equals(password))) {
				if(remember_me.equals("yes")){
					cookieController.Add_Cookie_With_Expire(account,password,7);
				}
				else {
					cookieController.Add_Cookie(account,password);
				}
				/*
				為了讓上方的url正確顯示，所以路徑前面加上redirect:
				如果有參數要傳遞，那就變成forward:
				*/
				model.addAttribute("account",account);
				model.addAttribute("password",password);
				return "redirect:/member";
			}
		}
		/*
		不需要為了login_fail再寫一個網頁的對應路徑
		這樣在登入失敗時，顯示的網址是login_verify
		*/
		return "login_fail";
	}
	@GetMapping("/register")
	public String to_register(Model model) {
		return "register";
	}
	@PostMapping("/register_check")
	public String to_register_check(
		@RequestParam(value="account",required=true) String account,
		@RequestParam(value="password",required=true) String password,
		@RequestParam(value="name",required=true) String name,
		@RequestParam(value="phone",required=true) String phone,
		@RequestParam(value="address",required=true) String address,
		Model model) {
		ArrayList<MemberModel> MemberTable=memberController.Call_MemberTable();
		for(int index=0;index<MemberTable.size();index++) {
			String ThisAccount=MemberTable.get(index).get_account();
			if(account.equals(ThisAccount)) {
				return "no_repetitive_account";
			}
		}
		model.addAttribute("account",account);
		model.addAttribute("password",password);
		model.addAttribute("name",name);
		model.addAttribute("phone",phone);
		model.addAttribute("address",address);
		return "register_check";
	}
	@PostMapping("/register_add_member")
	public String to_register_add_member(
		@RequestParam(value="account",required=true) String account,
		@RequestParam(value="password",required=true) String password,
		@RequestParam(value="name",required=true) String name,
		@RequestParam(value="phone",required=true) String phone,
		@RequestParam(value="address",required=true) String address,
		Model model) {
		/*查出目前最大會員編號*/
		ArrayList<MemberModel> MemberTable=memberController.Call_MemberTable();
		int max_member_index=MemberTable.size();/*這裡有0號會員*/
		/*設置會員資料參數*/
		int new_member_index=max_member_index;
		String new_name=name;
		String new_phone=phone;
		String new_address=address;
		String new_account=account;
		String new_password=password;
		String new_order_names="";
		MemberModel NewMember=new MemberModel();
		NewMember.set_member_index(new_member_index);
		NewMember.set_name(new_name);
		NewMember.set_phone(new_phone);
		NewMember.set_address(new_address);
		NewMember.set_account(new_account);
		NewMember.set_password(new_password);
		NewMember.set_order_names(new_order_names);
		/*資料表新增資料*/
		memberRepository.Memberinsert(NewMember);
		/*增加cookie*/
		cookieController.Add_Cookie(account,password);
		return "redirect:/member";
	}
	@PostMapping("/revise")
	public String to_revise(@RequestParam(value="account",required=true) String account,Model model) {
		model.addAttribute("account",account); 
		return "revise";
	}
	@PostMapping("/revise_member")
	public String to_revise_member(
		@RequestParam(value="revise_account",required=true) String account,
		@RequestParam(value="revise_name",required=false,defaultValue="") String name,
		@RequestParam(value="revise_phone",required=false,defaultValue="") String phone,
		@RequestParam(value="revise_address",required=false,defaultValue="") String address,
		@RequestParam(value="revise_password",required=false,defaultValue="") String password,
		Model model) {
		MemberModel memberModel=new MemberModel();
		memberModel.set_account(account);
		if(name.length()>0) {
			memberModel.set_name(name);
			memberService.updateNameByAccount(memberModel);
		}
		if(phone.length()>0) {
			memberModel.set_phone(phone);
			memberService.updatePhoneByAccount(memberModel);
		}
		if(address.length()>0) {
			memberModel.set_address(address);
			memberService.updateAddressByAccount(memberModel);
		}
		if(password.length()>0) {
			memberModel.set_password(password);
			memberService.updatePasswordByAccount(memberModel);
			cookieController.Add_Cookie(account,password);
		}
		return "revise_member";
	}
	@GetMapping("/aftersale")
	public String to_aftersale(Model model) { 
		String OrderName_ItemIndex=cookieController.Get_Cookie_With_Name("after");
		if(OrderName_ItemIndex.length()<8) {
			/*避免url暴力破解而出現錯誤，所以設置防呆機制*/
			return "redirect:/login";
		}
		String order_name=OrderName_ItemIndex.split("_")[0];
		int item_index=Integer.parseInt(OrderName_ItemIndex.split("_")[1]);
		model.addAttribute("order_name",order_name);
		model.addAttribute("item_index",item_index);
		ArrayList<OrderlistModel> OrderlistTable=orderlistController.Call_OrderlistTable();
		int item_rent_time;
		String start="";
		String end="";
		int price;
		for(int index=0;index<OrderlistTable.size();index++) {
			if((OrderlistTable.get(index).get_order_name().equals(order_name))&&(OrderlistTable.get(index).get_item_index()==item_index)) {
				item_rent_time=OrderlistTable.get(index).get_item_rent_time();
				start=OrderlistTable.get(index).get_start();
				end=OrderlistTable.get(index).get_end();
				price=OrderlistTable.get(index).get_price();
				model.addAttribute("item_rent_time",item_rent_time);
				model.addAttribute("start",start);
				model.addAttribute("end",end);
				model.addAttribute("price",price);
				break;
			}
		}
		/*找出是否有下一筆訂單，並決定是否能退租*/
		String MaxOrderName="";
		for(int index=0;index<OrderlistTable.size();index++) {
			if(OrderlistTable.get(index).get_item_index()==item_index) {
				MaxOrderName=OrderlistTable.get(index).get_order_name();
			}
		}
		Boolean withdraw_status=order_name.equals(MaxOrderName)?true:false;
		model.addAttribute("withdraw_status",withdraw_status);
		/*找出商品的最晚起始時間*/
		String MaxStart="";
		for(int index=0;index<OrderlistTable.size();index++) {
			if(OrderlistTable.get(index).get_item_index()==item_index) {
				MaxStart=OrderlistTable.get(index).get_start();
			}
		}
		/*比對今天與訂單的起始結束日期，並決定是否能續租或是修繕*/
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Boolean continue_status;
			Boolean repair_status;
			java.util.Date parsedStart=formatter.parse(start);
			java.util.Date parsedEnd=formatter.parse(end);
			java.util.Date parsedToday=new java.util.Date();
			java.util.Date parsedMaxStart=formatter.parse(MaxStart);
			long Start_millisecond=parsedStart.getTime();
			long End_millisecond=parsedEnd.getTime();
			long Today_millisecond=parsedToday.getTime();
			long MaxStart_millisecond=parsedMaxStart.getTime();
			if((Today_millisecond>=Start_millisecond)&&(Today_millisecond<End_millisecond)){
				continue_status=true;
				/*進行中的訂單才能續租*/
				repair_status=true;
				/*進行中的訂單才能修繕*/
			}
			else{
				continue_status=false;
				/*未進行的訂單，或是已過期的訂單，就不能續租*/
				repair_status=false;
				/*未進行的訂單，或是已過期的訂單，就不能修繕*/
			}
			if(Today_millisecond<MaxStart_millisecond){
				continue_status=false;
				/*如果還有下一筆訂單，就不能續租*/
			}
			model.addAttribute("continue_status",continue_status);
			model.addAttribute("repair_status",repair_status);
		}
		catch (java.text.ParseException e) {
			e.printStackTrace();
		}	
		return "aftersale";
	}
	@PostMapping("aftersale_service")
	public String to_aftersale_service(
		@RequestParam(value="order_name",required=true) String order_name,
		@RequestParam(value="item_index",required=true) int item_index,
		@RequestParam(value="item_rent_time",required=true) int item_rent_time,
		@RequestParam(value="start",required=true) String start,
		@RequestParam(value="end",required=true) String end,
		@RequestParam(value="price",required=true) int price,
		@RequestParam(value="aftersale",required=true) String aftersale,
		Model model) {
		model.addAttribute("order_name",order_name);
		model.addAttribute("item_index",item_index);
		model.addAttribute("item_rent_time",item_rent_time);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		model.addAttribute("price",price);
		if(aftersale.equals("repair")) {
			return "repair";
		}
		else if(aftersale.equals("continue")) {
			String CookieName="order"+Integer.toString(item_index);
			String CookieValue=Integer.toString(item_index);
			cookieController.Add_Cookie(CookieName,CookieValue);
			return "redirect:/cart";
		}
		else if(aftersale.equals("withdraw")) {
			/*計算退租起始時間*/
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsedStart=formatter.parse(start);
				java.util.Date parsedToday=new java.util.Date();
				String Today=formatter.format(parsedToday);
				long Start_millisecond=parsedStart.getTime();
				long Today_millisecond=parsedToday.getTime();
				String AlterStart=Today_millisecond>=Start_millisecond?Today:start;
				model.addAttribute("AlterStart",AlterStart);
			}
			catch (java.text.ParseException e) {
				e.printStackTrace();
			}	
			return "withdraw";
		}
		else {
			return "redirect:/login";
		}
	}
	@PostMapping("/repair_content")
	public String to_repair(
		@RequestParam(value="order_name",required=true) String order_name,
		@RequestParam(value="item_index",required=true) String item_index,
		@RequestParam(value="content",required=true) String content,
		Model model) {
		return "repair_content";
	}
	@PostMapping("/withdraw_receive")
	public String to_withdraw(
		@RequestParam(value="order_name",required=true) String order_name,
		@RequestParam(value="item_index",required=true) String item_index,
		@RequestParam(value="AlterEnd",required=true) String AlterEnd,
		Model model) {
		ArrayList<OrderlistModel> OrderlistTable=orderlistController.Call_OrderlistTable();
		for(int i=0;i<OrderlistTable.size();i++) {
			if((OrderlistTable.get(i).get_order_name().equals(order_name))&&(OrderlistTable.get(i).get_item_index()==Integer.parseInt(item_index))) {
				OrderlistModel UpdateRow=OrderlistTable.get(i);
				UpdateRow.set_end(AlterEnd);
				orderlistRepository.Orderlistupdate(UpdateRow);
				break;
			}
		}
		return "withdraw_receive";
	}
}

package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.OrderlistModel;/*引入前面建立的model*/
import com.example.service.OrderlistService;/*引入前面建立的service*/

@Controller
public class OrderlistController {
	@Autowired
	OrderlistModel orderlistModel;
	@Autowired
	OrderlistService orderlistService;
	/*查詢*/
	@GetMapping("/ExecuteOrderlistSelect")/*專案要執行下面方法時所對應的路徑*/
	public String select(){
		orderlistModel = new OrderlistModel();
        SqlRowSet result=orderlistService.selectOrderlist(orderlistModel);
        OrderlistModel orderlistTable = new OrderlistModel();
        while (result.next()) {
        	orderlistTable.set_order_index(result.getInt("order_index"));
        	orderlistTable.set_account(result.getString("account"));
        	orderlistTable.set_address(result.getString("address"));
        	orderlistTable.set_order_name(result.getString("order_name"));
        	orderlistTable.set_item_index(result.getInt("item_index"));
        	orderlistTable.set_item_rent_time(result.getInt("item_rent_time"));
        	orderlistTable.set_start(result.getString("start"));
        	orderlistTable.set_end(result.getString("end"));
        	orderlistTable.set_price(result.getInt("price"));
        	/*在Console顯示內容*/
        	System.out.print(orderlistTable.get_order_index());
        	System.out.print(" "+orderlistTable.get_account());
        	System.out.print(" "+orderlistTable.get_address());
        	System.out.print(" "+orderlistTable.get_order_name());
        	System.out.print(" "+orderlistTable.get_item_index());
        	System.out.print(" "+orderlistTable.get_item_rent_time());
        	System.out.print(" "+orderlistTable.get_start());
        	System.out.print(" "+orderlistTable.get_end());
        	System.out.println(" "+orderlistTable.get_price());
        }
        return "index";
    }
	/*呼叫訂單資料表*/
	public ArrayList<OrderlistModel> Call_OrderlistTable() {
		SqlRowSet rowset=orderlistService.selectOrderlist(orderlistModel);
		ArrayList<OrderlistModel> result=new ArrayList<OrderlistModel>();
		while(rowset.next()) {
			OrderlistModel ThisRow=new OrderlistModel();
			ThisRow.set_order_index(rowset.getInt("order_index"));
			ThisRow.set_account(rowset.getString("account"));
			ThisRow.set_address(rowset.getString("address"));
			ThisRow.set_order_name(rowset.getString("order_name"));
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_item_rent_time(rowset.getInt("item_rent_time"));
			ThisRow.set_start(rowset.getString("start"));
			ThisRow.set_end(rowset.getString("end"));
			ThisRow.set_price(rowset.getInt("price"));
			result.add(ThisRow);
		}
		return result;
	}
	/*找出一件商品的最大結束時間*/
	public String MaxEnd(String item_index) {
		String MaxEnd="";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<OrderlistModel> OrderlistTable=Call_OrderlistTable();
		for(int index=0;index<OrderlistTable.size();index++) {
			if(item_index.equals(Integer.toString(OrderlistTable.get(index).get_item_index()))) {
				String ThisEnd=OrderlistTable.get(index).get_end();
				if(MaxEnd.length()<1) {
					MaxEnd=ThisEnd;
				}
				else {
					try {
						/*把字串轉換成日期物件*/
						java.util.Date parsedMaxEnd=formatter.parse(MaxEnd);
			            java.util.Date parsedThisEnd=formatter.parse(ThisEnd);
			            /*把日期物件轉換成數字*/
			            long MaxEnd_millisecond=parsedMaxEnd.getTime();       
			            long ThisEnd_millisecond=parsedThisEnd.getTime();
			            if(ThisEnd_millisecond>MaxEnd_millisecond){
			            	MaxEnd=ThisEnd;
			            }
					}
					catch (java.text.ParseException e) {
			            e.printStackTrace();
			        }
				}
			}
		}
		if(MaxEnd.length()<1) {
			MaxEnd="1996-07-22";
		}
		return MaxEnd;
	}
	/*出租狀態*/
	public String Expire_Status(String item_index) {
		String MaxEnd=MaxEnd(item_index);
		String status="";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedMaxEnd=formatter.parse(MaxEnd);
			java.util.Date parsedToday=new java.util.Date();
			long MaxEnd_millisecond=parsedMaxEnd.getTime();
            long Today_millisecond=parsedToday.getTime();
            if(Today_millisecond>MaxEnd_millisecond){
            	status="ok";/*出租狀態定義*/
            }
            else{
            	status="no";/*出租狀態定義*/
            }
		}
		catch (java.text.ParseException e) {
            e.printStackTrace();
        }
		return status;
	}
	/*新增*/
	public void insert(OrderlistModel orderlistModel){
    	try {
    		orderlistService.insertOrderlist(orderlistModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
	/*刪除*/
	public void delete(int order_index){
		/*不要使用，會影響後續的網站語法結構!!!*/
    	orderlistModel = new OrderlistModel();
    	orderlistModel.set_order_index(order_index);
    	try {
    		orderlistService.deleteOrderlist(orderlistModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
	/*修改*/
	public void update(OrderlistModel orderlistModel){
    	try {
    		orderlistService.updateOrderlist(orderlistModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
}

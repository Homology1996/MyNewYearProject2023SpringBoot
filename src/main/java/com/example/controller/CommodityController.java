package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.CommodityModel;/*引入前面建立的model*/
import com.example.service.CommodityService;/*引入前面建立的service*/

@Controller
public class CommodityController {
	@Autowired
	CommodityModel commodityModel;
	@Autowired
	CommodityService commodityService;
	/*查詢*/
	@GetMapping("/ExecuteCommoditySelect")/*專案要執行下面方法時所對應的路徑*/
	public String select(){
    	commodityModel = new CommodityModel();
        SqlRowSet result=commodityService.selectCommodity(commodityModel);
        CommodityModel commodityTable = new CommodityModel();
        while (result.next()) {
        	commodityTable.set_item_index(result.getInt("item_index"));
        	commodityTable.set_type(result.getString("type"));
        	commodityTable.set_brand(result.getString("brand"));
        	commodityTable.set_location(result.getString("location"));
        	commodityTable.set_cost(result.getInt("cost"));
        	commodityTable.set_purchasetime(result.getString("purchasetime"));
        	commodityTable.set_life_month(result.getInt("life_month"));
        	commodityTable.set_description(result.getString("description"));
        	commodityTable.set_retire(result.getString("retire"));
        	/*在Console顯示內容*/
        	System.out.print(commodityTable.get_item_index());
        	System.out.print(" "+commodityTable.get_type());
        	System.out.print(" "+commodityTable.get_brand());
        	System.out.print(" "+commodityTable.get_location());
        	System.out.print(" "+commodityTable.get_cost());
        	System.out.print(" "+commodityTable.get_purchasetime());
        	System.out.print(" "+commodityTable.get_life_month());
        	System.out.print(" "+commodityTable.get_description());
        	System.out.println(" "+commodityTable.get_retire());
        }
        return "index";
    }
	/*呼叫商品資料表*/
	public ArrayList<CommodityModel> Call_CommodityTable() {
		SqlRowSet rowset=commodityService.selectCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_bed() {
		SqlRowSet rowset=commodityService.selectbedCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_table() {
		SqlRowSet rowset=commodityService.selecttableCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_refrigerator() {
		SqlRowSet rowset=commodityService.selectrefrigeratorCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_television() {
		SqlRowSet rowset=commodityService.selecttelevisionCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_air_conditioner() {
		SqlRowSet rowset=commodityService.selectair_conditionerCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_laundry_machine() {
		SqlRowSet rowset=commodityService.selectlaundry_machineCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_low_price() {
		SqlRowSet rowset=commodityService.selectlow_priceCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_medium_price() {
		SqlRowSet rowset=commodityService.selectmedium_priceCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	public ArrayList<CommodityModel> Call_CommodityTable_high_price() {
		SqlRowSet rowset=commodityService.selecthigh_priceCommodity(commodityModel);
		ArrayList<CommodityModel> result=new ArrayList<CommodityModel>();
		while(rowset.next()) {
			CommodityModel ThisRow=new CommodityModel();
			ThisRow.set_item_index(rowset.getInt("item_index"));
			ThisRow.set_type(rowset.getString("type"));
			ThisRow.set_brand(rowset.getString("brand"));
			ThisRow.set_location(rowset.getString("location"));
			ThisRow.set_cost(rowset.getInt("cost"));
			ThisRow.set_purchasetime(rowset.getString("purchasetime"));
			ThisRow.set_life_month(rowset.getInt("life_month"));
			ThisRow.set_description(rowset.getString("description"));
			ThisRow.set_retire(rowset.getString("retire"));
			result.add(ThisRow);
		}
		return result;
	}
	/*新增*/
	public void insert(CommodityModel commodityModel){
    	try {
    		commodityService.insertCommodity(commodityModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
	/*刪除*/
	public void delete(int item_index){
		/*不要使用，會影響後續的網站語法結構!!!*/
    	commodityModel = new CommodityModel();
    	commodityModel.set_item_index(item_index);
    	try {
    		commodityService.deleteCommodity(commodityModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
	/*修改*/
	public void update(CommodityModel commodityModel){
    	try {
    		commodityService.updateCommodity(commodityModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
}

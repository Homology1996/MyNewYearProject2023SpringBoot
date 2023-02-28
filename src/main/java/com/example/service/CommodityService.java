package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.CommodityModel;/*引入前面建立的model*/
import com.example.repository.CommodityRepository;/*引入前面建立的repository*/

@Service
public class CommodityService {
	@Autowired
	CommodityRepository commodityRepository;
	/*查詢*/
	public SqlRowSet selectCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselect(commodityModel);
	}
	public SqlRowSet selectbedCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselectbed(commodityModel);
	}
	public SqlRowSet selecttableCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselecttable(commodityModel);
	}
	public SqlRowSet selectrefrigeratorCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselectrefrigerator(commodityModel);
	}
	public SqlRowSet selecttelevisionCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselecttelevision(commodityModel);
	}
	public SqlRowSet selectair_conditionerCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselectair_conditioner(commodityModel);
	}
	public SqlRowSet selectlaundry_machineCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselectlaundry_machine(commodityModel);
	}
	public SqlRowSet selectlow_priceCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselectlow_price(commodityModel);
	}
	public SqlRowSet selectmedium_priceCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselectmedium_price(commodityModel);
	}
	public SqlRowSet selecthigh_priceCommodity(CommodityModel commodityModel){
		return commodityRepository.Commodityselecthigh_price(commodityModel);
	}
	/*新增*/
	public void insertCommodity(CommodityModel commodityModel){
		commodityRepository.Commodityinsert(commodityModel);
	}
	/*刪除*/
	public void deleteCommodity(CommodityModel commodityModel){
		/*不要使用，會影響後續的網站語法結構!!!*/
		commodityRepository.Commoditydelete(commodityModel);
	}
	/*修改*/
	public void updateCommodity(CommodityModel commodityModel){
		commodityRepository.Commodityupdate(commodityModel);
	}
	/*執行SQL語法*/
}

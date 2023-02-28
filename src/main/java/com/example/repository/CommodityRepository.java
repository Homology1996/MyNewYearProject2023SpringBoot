package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.CommodityModel;/*引入前面建立的model*/

@Repository
public class CommodityRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*查詢*/
	public SqlRowSet Commodityselect(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity");
		return result;
	}
	public SqlRowSet Commodityselectbed(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE type='bed'");
		return result;
	}
	public SqlRowSet Commodityselecttable(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE type='table'");
		return result;
	}
	public SqlRowSet Commodityselectrefrigerator(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE type='refrigerator'");
		return result;
	}
	public SqlRowSet Commodityselecttelevision(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE type='television'");
		return result;
	}
	public SqlRowSet Commodityselectair_conditioner(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE type='air_conditioner'");
		return result;
	}
	public SqlRowSet Commodityselectlaundry_machine(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE type='laundry_machine'");
		return result;
	}
	public SqlRowSet Commodityselectlow_price(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE cost/life_month>=0 AND cost/life_month<250");
		return result;
	}
	public SqlRowSet Commodityselectmedium_price(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE cost/life_month>=250 AND cost/life_month<500");
		return result;
	}
	public SqlRowSet Commodityselecthigh_price(CommodityModel commodityModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM commodity WHERE cost/life_month>=500");
		return result;
	}
	/*新增*/
	public void Commodityinsert(CommodityModel commodityModel){
		int item_index=commodityModel.get_item_index();
		String type=commodityModel.get_type();
		String brand=commodityModel.get_brand();
		String location=commodityModel.get_location();
		int cost=commodityModel.get_cost();
		String purchasetime=commodityModel.get_purchasetime();
		int life_month=commodityModel.get_life_month();
		String description=commodityModel.get_description();
		String retire=commodityModel.get_retire();
		jdbcTemplate.update("INSERT INTO commodity (item_index, type, brand, location, cost, purchasetime, life_month, description, retire)"
		+" VALUES ("+item_index+",'"+type+"','"+brand+"','"+location+"',"+cost+",'"+purchasetime+"',"+life_month+",'"+description+"','"+retire+"');");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	/*刪除*/
	public void Commoditydelete(CommodityModel commodityModel){
		/*不要使用，會影響後續的網站語法結構!!!*/
		int item_index=commodityModel.get_item_index();
		jdbcTemplate.update("DELETE FROM commodity WHERE item_index="+item_index);
	}
	/*修改*/
	public void Commodityupdate(CommodityModel commodityModel){
		/*根據商品編號來修改資訊*/
		int item_index=commodityModel.get_item_index();
		String type=commodityModel.get_type();
		String brand=commodityModel.get_brand();
		String location=commodityModel.get_location();
		int cost=commodityModel.get_cost();
		String purchasetime=commodityModel.get_purchasetime();
		int life_month=commodityModel.get_life_month();
		String description=commodityModel.get_description();
		String retire=commodityModel.get_retire();
		jdbcTemplate.update("UPDATE commodity SET type='"+type+"',brand='"+brand+"',location='"+location+"',cost="+cost+",purchasetime='"+purchasetime+"',"
		+"life_month="+life_month+",description='"+description+"',retire='"+retire+"' WHERE item_index="+item_index);
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	/*編寫SQL語法，建立各種CRUD的指令*/
}

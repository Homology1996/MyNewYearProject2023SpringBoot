package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.OrderlistModel;/*引入前面建立的model*/

@Repository
public class OrderlistRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*查詢*/
	public SqlRowSet Orderlistselect(OrderlistModel orderlistModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM orderlist");
		return result;
	}
	/*新增*/
	public void Orderlistinsert(OrderlistModel orderlistModel){
		int order_index=orderlistModel.get_order_index();
		String account=orderlistModel.get_account();
		String address=orderlistModel.get_address();
		String order_name=orderlistModel.get_order_name();
		int item_index=orderlistModel.get_item_index();
		int item_rent_time=orderlistModel.get_item_rent_time();
		String start=orderlistModel.get_start();
		String end=orderlistModel.get_end();
		int price=orderlistModel.get_price();
		jdbcTemplate.update("INSERT INTO orderlist (order_index, account, address, order_name, item_index, item_rent_time, start, end, price)"
		+" VALUES ("+order_index+",'"+account+"','"+address+"','"+order_name+"',"+item_index+","+item_rent_time+",'"+start+"','"+end+"',"+price+");");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	/*刪除*/
	public void Orderlistdelete(OrderlistModel orderlistModel){
		/*不要使用，會影響後續的網站語法結構!!!*/
		int order_index=orderlistModel.get_order_index();
		jdbcTemplate.update("DELETE FROM orderlist WHERE order_index="+order_index);
	}
	/*修改*/
	public void Orderlistupdate(OrderlistModel orderlistModel){
		int order_index=orderlistModel.get_order_index();
		String account=orderlistModel.get_account();
		String address=orderlistModel.get_address();
		String order_name=orderlistModel.get_order_name();
		int item_index=orderlistModel.get_item_index();
		int item_rent_time=orderlistModel.get_item_rent_time();
		String start=orderlistModel.get_start();
		String end=orderlistModel.get_end();
		int price=orderlistModel.get_price();
		jdbcTemplate.update("UPDATE orderlist SET account='"+account+"',address='"+address+"',order_name='"+order_name+"',item_index="+item_index
		+",item_rent_time="+item_rent_time+",start='"+start+"',end='"+end+"',price="+price+" WHERE order_index="+order_index);
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	/*編寫SQL語法，建立各種CRUD的指令*/
}

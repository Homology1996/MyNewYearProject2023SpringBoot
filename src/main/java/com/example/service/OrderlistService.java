package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.OrderlistModel;/*引入前面建立的model*/
import com.example.repository.OrderlistRepository;/*引入前面建立的repository*/

@Service
public class OrderlistService {
	@Autowired
	OrderlistRepository orderlistRepository;
	/*查詢*/
	public SqlRowSet selectOrderlist(OrderlistModel orderlistModel){
		return orderlistRepository.Orderlistselect(orderlistModel);
	}
	/*新增*/
	public void insertOrderlist(OrderlistModel orderlistModel){
		orderlistRepository.Orderlistinsert(orderlistModel);
	}
	/*刪除*/
	public void deleteOrderlist(OrderlistModel orderlistModel){
		/*不要使用，會影響後續的網站語法結構!!!*/
		orderlistRepository.Orderlistdelete(orderlistModel);
	}
	/*修改*/
	public void updateOrderlist(OrderlistModel orderlistModel){
		orderlistRepository.Orderlistupdate(orderlistModel);
	}
	/*執行SQL語法*/
}

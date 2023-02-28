package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.MemberModel;/*引入前面建立的model*/

@Repository
public class MemberRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*查詢*/
	public SqlRowSet Memberselect(MemberModel memberModel) {
		SqlRowSet result=jdbcTemplate.queryForRowSet("SELECT * FROM member");
		return result;
	}
	/*新增*/
	public void Memberinsert(MemberModel memberModel){
		int member_index=memberModel.get_member_index();
		String name=memberModel.get_name();
		String phone=memberModel.get_phone();
		String address=memberModel.get_address();
		String account=memberModel.get_account();
		String password=memberModel.get_password();
		String order_names=memberModel.get_order_names();
		jdbcTemplate.update("INSERT INTO member (member_index, name, phone, address, account, password, order_names)"
		+" VALUES ("+member_index+",'"+name+"','"+phone+"','"+address+"','"+account+"','"+password+"','"+order_names+"');");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	/*刪除*/
	public void Memberdelete(MemberModel memberModel){
		/*不要使用，會影響後續的網站語法結構!!!*/
		int member_index=memberModel.get_member_index();
		jdbcTemplate.update("DELETE FROM member WHERE member_index="+member_index);
	}
	/*修改*/
	public void Memberupdate(MemberModel memberModel){
		int member_index=memberModel.get_member_index();
		String name=memberModel.get_name();
		String phone=memberModel.get_phone();
		String address=memberModel.get_address();
		String account=memberModel.get_account();
		String password=memberModel.get_password();
		String order_names=memberModel.get_order_names();
		jdbcTemplate.update("UPDATE member SET name='"+name+"',phone='"+phone+"',address='"+address+"',account='"+account+"',password='"+password+"',"
		+"order_names='"+order_names+"' WHERE member_index="+member_index);
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	public void UpdateNameByAccount(MemberModel memberModel) {
		String account=memberModel.get_account();
		String name=memberModel.get_name();
		jdbcTemplate.update("UPDATE member SET name='"+name+"' WHERE account='"+account+"'");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	public void UpdatePhoneByAccount(MemberModel memberModel) {
		String account=memberModel.get_account();
		String phone=memberModel.get_phone();
		jdbcTemplate.update("UPDATE member SET phone='"+phone+"' WHERE account='"+account+"'");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	public void UpdateAddressByAccount(MemberModel memberModel) {
		String account=memberModel.get_account();
		String address=memberModel.get_address();
		jdbcTemplate.update("UPDATE member SET address='"+address+"' WHERE account='"+account+"'");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	public void UpdatePasswordByAccount(MemberModel memberModel) {
		String account=memberModel.get_account();
		String password=memberModel.get_password();
		jdbcTemplate.update("UPDATE member SET password='"+password+"' WHERE account='"+account+"'");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	public void UpdateOrder_NamesByAccount(MemberModel memberModel) {
		String account=memberModel.get_account();
		String order_names=memberModel.get_order_names();
		jdbcTemplate.update("UPDATE member SET order_names='"+order_names+"' WHERE account='"+account+"'");
		/*如果要更改的內容為字串，記得用單引號包起來*/
	}
	/*編寫SQL語法，建立各種CRUD的指令*/
}

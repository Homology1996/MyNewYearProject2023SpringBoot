package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.MemberModel;/*引入前面建立的model*/
import com.example.service.MemberService;/*引入前面建立的service*/

@Controller
public class MemberController {
	@Autowired
	MemberModel memberModel;
	@Autowired
	MemberService memberService;
	/*查詢*/
	@GetMapping("/ExecuteMemberSelect")/*專案要執行下面方法時所對應的路徑*/
	public String select(){
    	memberModel = new MemberModel();
        SqlRowSet result=memberService.selectMember(memberModel);
        MemberModel memberTable = new MemberModel();
        while (result.next()) {
        	memberTable.set_member_index(result.getInt("member_index"));
        	memberTable.set_name(result.getString("name"));
        	memberTable.set_phone(result.getString("phone"));
        	memberTable.set_address(result.getString("address"));
        	memberTable.set_account(result.getString("account"));
        	memberTable.set_password(result.getString("password"));
        	memberTable.set_order_names(result.getString("order_names"));
        	/*在Console顯示內容*/
        	System.out.print(memberTable.get_member_index());
        	System.out.print(" "+memberTable.get_name());
        	System.out.print(" "+memberTable.get_phone());
        	System.out.print(" "+memberTable.get_address());
        	System.out.print(" "+memberTable.get_account());
        	System.out.print(" "+memberTable.get_password());
        	System.out.println(" "+memberTable.get_order_names());
        }
        return "index";
    }
	/*呼叫會員資料表*/
	public ArrayList<MemberModel> Call_MemberTable() {
		SqlRowSet rowset=memberService.selectMember(memberModel);
		ArrayList<MemberModel> result=new ArrayList<MemberModel>();
		while(rowset.next()) {
			MemberModel ThisRow=new MemberModel();
			ThisRow.set_member_index(rowset.getInt("member_index"));
			ThisRow.set_name(rowset.getString("name"));
			ThisRow.set_phone(rowset.getString("phone"));
			ThisRow.set_address(rowset.getString("address"));
			ThisRow.set_account(rowset.getString("account"));
			ThisRow.set_password(rowset.getString("password"));
			ThisRow.set_order_names(rowset.getString("order_names"));
			result.add(ThisRow);
		}
		return result;
	}
	/*新增*/
	public void insert(MemberModel memberModel){
    	try {
    		memberService.insertMember(memberModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
	/*刪除*/
	public void delete(int member_index){
		/*不要使用，會影響後續的網站語法結構!!!*/
    	memberModel = new MemberModel();
    	memberModel.set_member_index(member_index);
    	try {
    		memberService.deleteMember(memberModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
	/*修改*/
	public void update(MemberModel memberModel){
    	try {
    		memberService.updateMember(memberModel);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		/*如果發生錯誤，就能從這裡看出錯誤為何*/
    	}
    }
}

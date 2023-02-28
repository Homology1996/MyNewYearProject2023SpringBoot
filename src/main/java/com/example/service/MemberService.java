package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.rowset.SqlRowSet;/*查詢結果*/

import com.example.model.MemberModel;/*引入前面建立的model*/
import com.example.repository.MemberRepository;/*引入前面建立的repository*/

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	/*查詢*/
	public SqlRowSet selectMember(MemberModel memberModel){
		return memberRepository.Memberselect(memberModel);
	}
	/*新增*/
	public void insertMember(MemberModel memberModel){
		memberRepository.Memberinsert(memberModel);
	}
	/*刪除*/
	public void deleteMember(MemberModel memberModel){
		/*不要使用，會影響後續的網站語法結構!!!*/
		memberRepository.Memberdelete(memberModel);
	}
	/*修改*/
	public void updateMember(MemberModel memberModel){
		memberRepository.Memberupdate(memberModel);
	}
	public void updateNameByAccount(MemberModel memberModel) {
		memberRepository.UpdateNameByAccount(memberModel);
	}
	public void updatePhoneByAccount(MemberModel memberModel) {
		memberRepository.UpdatePhoneByAccount(memberModel);
	}
	public void updateAddressByAccount(MemberModel memberModel) {
		memberRepository.UpdateAddressByAccount(memberModel);
	}
	public void updatePasswordByAccount(MemberModel memberModel) {
		memberRepository.UpdatePasswordByAccount(memberModel);
	}
	public void updateOrder_NamesByAccount(MemberModel memberModel) {
		memberRepository.UpdateOrder_NamesByAccount(memberModel);
	}
	/*執行SQL語法*/
}

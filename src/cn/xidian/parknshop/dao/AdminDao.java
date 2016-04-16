package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.Odium;
import cn.xidian.parknshop.beans.Admin;
import cn.xidian.parknshop.beans.Complaint;

public interface AdminDao {

	List<Complaint> findComplaintByState(int state);

	List<Odium> findOdiumByState(int state);

	Complaint findComplaintByComplaintNo(long complaintNo);

	Odium findOdiumByNo(long number);
	
	List<Admin> adminLogin(String username, String password);

}

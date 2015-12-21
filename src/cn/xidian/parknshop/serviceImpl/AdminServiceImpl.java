package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Complaint;
import cn.xidian.parknshop.dao.AdminDao;
import cn.xidian.parknshop.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Resource(name="adminDao")
	private AdminDao adminDao;

	@Override
	public List<Complaint> findComplaintByState(int state) {
		// TODO Auto-generated method stub
		return adminDao.findComplaintByState(state);
	}

	@Override
	public List<Comments> findCommentByState(int state) {
		// TODO Auto-generated method stub
		return adminDao.findCommentByState(state);
	}

	@Override
	public Complaint findComplaintByComplaintNo(long complaintNo) {
		// TODO Auto-generated method stub
		return adminDao.findComplaintByComplaintNo(complaintNo);
	}

	@Override
	public Comments findCommentById(int commentsId) {
		// TODO Auto-generated method stub
		return adminDao.findCommentById(commentsId);
	}

}

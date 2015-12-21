package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Complaint;

public interface AdminDao {

	List<Complaint> findComplaintByState(int state);

	List<Comments> findCommentByState(int state);

	Complaint findComplaintByComplaintNo(long complaintNo);

	Comments findCommentById(int commentsId);

}

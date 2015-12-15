package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="tb_Complaint")

public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="complaint_no",unique=true,nullable=false)
	private int complaintNo;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="username",referencedColumnName="username",insertable=true,updatable=true)
	private User complaintUser;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop complaintedShop;
	
	@Column(name = "complaint_reason", length = 200)  
	private String reason;
	
	@Temporal(TemporalType.DATE)
	@Column(name="complaint_time",nullable=false)
	private Date complaintTime;
	
	@Column(name = "complaint_view", length = 100)  
	private int state;
	
	@Column(name = "complaint_result", length = 100)  
	private int handleResult;


  
    public String getReason() {  
        return this.reason;  
    }  
  
    public void setReason(String reason) {  
        this.reason = reason;  
    } 
   
    public int getState() {  
        return this.state;  
    }  
  
    public void setState(int state) {  
        this.state = state;  
    }
    
    public int getHandleResult() {  
        return this.handleResult;  
    }  
  
    public void setHandleResult(int handleResult) {  
        this.handleResult = handleResult;  
    } 
    public Date getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}
	
	
	public Shop getComplaintedShop() {
		return complaintedShop;
	}

	public void setComplaintedShop(Shop complaintedShop) {
		this.complaintedShop = complaintedShop;
	}

	
	public User getComplaintUser() {
		return complaintUser;
	}

	public void setComplaintUser(User complaintUser) {
		this.complaintUser = complaintUser;
	}

	public int getComplaintNo() {
		return complaintNo;
	}

	public void setComplaintNo(int complaintNo) {
		this.complaintNo = complaintNo;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


}

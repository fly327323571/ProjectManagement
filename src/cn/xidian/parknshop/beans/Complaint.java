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
@Entity
@Table(name="Complaint")

public class Complaint {
	
	private int ComplaintId;
	private User complaintUser;
	private Shop complaintedShop;
	private String reason;
	private Date complaintTime;//鎶曡瘔鏃堕棿
	private String state;//澶勭悊鐘舵��    锛堝寘鎷琻ull 宸插鐞�  鏈鐞嗭級
	private String handleResult;//澶勭悊鎰忚(鍗忓晢锛岃鍛婁拱瀹讹紝娉ㄩ攢涔板璐﹀彿锛屽崠瀹惰禂鍋匡紝鍗栧缃氭锛屽叧鍋滃崠瀹跺簵閾�)


    @Column(name = "reason", length = 100)  
    public String getreason() {  
        return this.reason;  
    }  
  
    public void setreason(String reason) {  
        this.reason = reason;  
    } 
    @Column(name = "state", length = 100)  
    public String getstate() {  
        return this.state;  
    }  
  
    public void setstate(String state) {  
        this.state = state;  
    }
    @Column(name = "handleresult", length = 100)  
    public String gethandleResult() {  
        return this.handleResult;  
    }  
  
    public void sethandleResult(String handleResult) {  
        this.handleResult = handleResult;  
    } 
    public Date getcomplaintTime() {
		return complaintTime;
	}

	public void setcomplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="shop_id")
	public Shop getComplaintedShop() {
		return complaintedShop;
	}

	public void setComplaintedShop(Shop complaintedShop) {
		this.complaintedShop = complaintedShop;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="user_id")
	public User getComplaintUser() {
		return complaintUser;
	}

	public void setComplaintUser(User complaintUser) {
		this.complaintUser = complaintUser;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="complaint_id")
	public int getComplaintId() {
		return ComplaintId;
	}

	public void setComplaintId(int complaintId) {
		ComplaintId = complaintId;
	}
}

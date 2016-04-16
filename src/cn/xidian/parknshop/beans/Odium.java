package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_odium")
public class Odium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long odiumid;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="username",referencedColumnName="username",insertable=true,updatable=true)
	private User user;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no",insertable=true,updatable=true)
	private Commodity commodity;
	
	@Column(name="comment",length=200,nullable=false)
	private String comments;
	
	@Column(name="commodity_rank")
	private double rank;
	
	@Column(name="odium_reason",length=255)
	private String reason;
	
	@Column(name="odium_no",nullable=false,unique=true)
	private long number;
	
	@Column(name="comment_isRead",length=2)
	private int isRead=0;
	
	@Column(name="comments_state")
	private int state=0;
	
	@Temporal(TemporalType.DATE) 
	private Date commentsTime;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number=number;
	}
	
	public long getOdiumId() {
		return odiumid;
	}

	public void setOdiumId(long odiumid) {
		this.odiumid = odiumid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCommentsTime() {
		return commentsTime;
	}

	public void setCommentsTime(Date commentsTime) {
		this.commentsTime = commentsTime;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}
	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}

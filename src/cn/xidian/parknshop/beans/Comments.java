package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_comment")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int commentsId;
	
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
	
	@Temporal(TemporalType.DATE) 
	private Date commentsTime;

	public int getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
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
	
}

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
@Table(name = "tb_comment")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long commentsId;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "username", referencedColumnName = "username", insertable = true, updatable = true)
	private User user;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "shop_no", referencedColumnName = "shop_no", insertable = true, updatable = true)
	private Shop shop;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "commodity_no", referencedColumnName = "commodity_no", insertable = true, updatable = true)
	private Commodity commodity;

	@Column(name = "comment", length = 200, nullable = false)
	private String comments;

	@Column(name = "comment_rank")
	private double rank;

	@Column(name = "comment_isRead", length = 2)
	private int isRead = 0;

	@Temporal(TemporalType.DATE)
	private Date commentsTime;

	public long getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(long commentsId) {
		this.commentsId = commentsId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCommentsTime() {
		return commentsTime;
	}

	public void setCommentsTime(Date commentsTime) {
		this.commentsTime = commentsTime;
	}
}

package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_collectshop")
public class CollectShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
	@JoinColumn(name="username",referencedColumnName="username",insertable=true,updatable=true)
	private User user;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@Temporal(TemporalType.DATE)
	private Date addTime;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}

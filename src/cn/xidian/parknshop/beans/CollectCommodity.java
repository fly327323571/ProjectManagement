package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_collectcommodity")
public class CollectCommodity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
	@JoinColumn(name="username",referencedColumnName="username",insertable=true,updatable=true)
	private User user;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no")
	private Commodity commodity;
	
	@Temporal(TemporalType.DATE) 
	private Date addTime;



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
}

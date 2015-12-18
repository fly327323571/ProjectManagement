package cn.xidian.parknshop.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="tb_shopOwner")
public class ShopOwner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="portrait",length=255)
	private String portrait;
	
	@Column(name="realname",length=50)
	private String realName;
	
	@Column(name="idCard",length=50)
	private String idCard;
	
	@OneToOne
	@JoinColumn(name="username",referencedColumnName="username")
	private User user;
	
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
}

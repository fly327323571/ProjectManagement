package cn.xidian.parknshop.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;
	
	@Column(name="name",nullable=false,length=200)
	private String userName;

	@Column(name="telephone",length=20)
	private String tel;
	
	@Column(name="email",nullable=false,length=30)
	private String email;
	
	@Column(name="address",nullable=false,length=100)
	private String address;
	
	@Column(name="person_id",length=100)
	private String cardId;
	
	@Column(name="power")
	private int IsSeller;
	
	@Column(name="check_register_success")
	private int checkRegSuccess;
	
	@Column(name="password",nullable=false,length=100)
	private String password;
	
	@Temporal(TemporalType.DATE) 
	private Date registerTime;
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="shopOwner")
//	private Set<Shop> shops;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getIsSeller() {
		return IsSeller;
	}

	public void setIsSeller(int isSeller) {
		IsSeller = isSeller;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public int getCheckRegSuccess() {
		return checkRegSuccess;
	}

	public void setCheckRegSuccess(int checkRegSuccess) {
		this.checkRegSuccess = checkRegSuccess;
	}
}



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
@Table(name="tb_User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private long Id;
	
	@Column(name="username",nullable=false,unique=true,length=50)
	private String userName;
	
	@Column(name="password",nullable=false,length=50)
	private String password;
	
	@Column(name="nickName",nullable=false,length=200)
	private String nickName;
	
	@Column(name="user_phone",length=20)
	private String tel;
	
	@Column(name="email",nullable=false,length=30)
	private String email;
	
	@Column(name="address",nullable=false,length=100)
	private String address;
	
	@Column(name="person_id",length=100)
	private String cardId;
	
	@Column(name="permission")
	private boolean isSeller;
	
	@Column(name="check_register_success")
	private int checkRegSuccess;
	

	@Temporal(TemporalType.DATE) 
	private Date registerTime;
	

	

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

	public boolean isSeller() {
		return isSeller;
	}

	public void setSeller(boolean isSeller) {
		this.isSeller = isSeller;
	}
}



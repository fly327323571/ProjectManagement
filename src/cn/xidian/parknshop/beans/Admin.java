package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="tb_admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="admin_name",length=20,nullable=false,unique=true)
	private String adminAccountName;
	
	@Column(name="admin_psw",length=200,nullable=false)
	private String adminPassword;
	
	@Column(name="admin_phone",length=20,nullable=false,unique=true)
	private String adminTel;



	public String getAdminAccountName() {
		return adminAccountName;
	}

	public void setAdminAccountName(String adminAccountName) {
		this.adminAccountName = adminAccountName;
	}

	public String getAdminTel() {
		return adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
}

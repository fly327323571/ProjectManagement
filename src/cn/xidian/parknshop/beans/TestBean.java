package cn.xidian.parknshop.beans;

import javax.persistence.*;


@Entity
@Table(name = "Test")
public class TestBean {
	
	private int id;
	private String userName;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "username", length =255)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

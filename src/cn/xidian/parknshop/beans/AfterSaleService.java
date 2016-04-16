package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="tb_service")
public class AfterSaleService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="service_no",unique=true,nullable=false)
	private long serviceNo;
	
	@Column(name="service_type")
	private int serviceType;
	
	@Column(name="service_status")
	private int status=0;
	
	@OneToOne
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="username",referencedColumnName="username",insertable=true,updatable=true)
	private User applyServiceUser;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no",insertable=true,updatable=true)
	private Commodity commodity;

	@Column(name="reasons",length=200)
	private String reasons;
	
	
	
	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public User getUser() {
		return applyServiceUser;
	}

	public void setUser(User applyServiceUser) {
		this.applyServiceUser = applyServiceUser;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(long serviceNo) {
		this.serviceNo = serviceNo;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	
}

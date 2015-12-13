package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="After_Sale_Service")
public class AfterSaleService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_id")
	private int serviceId;
	
	@Column(name="service_type")
	private int serviceType;
	
	@Column(name="status")
	private int status;
	
	@OneToOne
	@JoinColumn(name="shop_id")
	private Shop shop;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="apply_Service_User_id")
	private User applyServiceUser;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

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
	
}

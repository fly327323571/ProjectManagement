package cn.xidian.parknshop.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="tb_express")
public class Express implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;

	@Column(name="express_no",unique=true,nullable=false)
	private long expressNo;
	
	@Column(name="express_type",length=20)
	private String express_type;
	
	@Column(name="express_companyName",length=100,nullable=false)
	private String expressCompanyName;
	
	@Column(name="from_address",length=255,nullable=false)
	private String fromAddr;

	@Column(name="to_address",length=255,nullable=false)
	private String toAddr;
	
	@Column(name="price")
	private double price=0.0D;
	
	@OneToOne
	@JoinColumn(name="order_no",referencedColumnName="order_no",insertable=true,updatable=true)
	private Order order;

	public String getExpress_type() {
		return express_type;
	}

	public void setExpress_type(String express_type) {
		this.express_type = express_type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public long getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(long expressNo) {
		this.expressNo = expressNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}

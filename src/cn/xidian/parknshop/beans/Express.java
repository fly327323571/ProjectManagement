package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="tb_Express")
public class Express {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;

	@Column(name="express_no",unique=true,nullable=false)
	private int expressNo;
	
//	@Column(name="express_type",length=20)
	private String express_type;
	
	@OneToOne
	@JoinColumn(name="order_no",referencedColumnName="order_no",insertable=true,updatable=true)
	private Order order;



	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getExpress_type() {
		return express_type;
	}

	public void setExpress_type(String express_type) {
		this.express_type = express_type;
	}

	public int getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(int expressNo) {
		this.expressNo = expressNo;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
}

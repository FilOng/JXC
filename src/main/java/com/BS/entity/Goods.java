package com.BS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 商品实体
 * @author Administrator
 *
 */
@Entity
@Table(name="t_goods")
public class Goods {
	
	@Id
	@GeneratedValue
	private Integer id; // 编号
	
	@Column(length=50)
	private String code;//商品编码
	
	@Column(length=50)
	private String name; //商品名称
	
	@Column(length=50)
	private String model;//商品型号
	
	@ManyToOne
	@JoinColumn(name="typeId")
	private GoodsType type;//商品类别
	
	@Column(length=10)
	private String unit;//商品单位
	
	private float lastPurchasingPrice;//上次采购价格
	
	private float purchasingPrice;//采购价格  成本价  假如价格有变动 算平均值
	
	private float sellingPrice;//出售价格
	
	private int state;//0初始化状态  1期初库存入仓库 2有进货或者销售单据
	
	@Column(length=200)
	private String producers;//生产产商
	
	@Column(length=1000)
	private String remarks;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getLastPurchasingPrice() {
		return lastPurchasingPrice;
	}

	public void setLastPurchasingPrice(float lastPurchasingPrice) {
		this.lastPurchasingPrice = lastPurchasingPrice;
	}

	public float getPurchasingPrice() {
		return purchasingPrice;
	}

	public void setPurchasingPrice(float purchasingPrice) {
		this.purchasingPrice = purchasingPrice;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", code=" + code + ", name=" + name + ", model=" + model + ", type=" + type
				+ ", unit=" + unit + ", lastPurchasingPrice=" + lastPurchasingPrice + ", purchasingPrice="
				+ purchasingPrice + ", sellingPrice=" + sellingPrice + ", state=" + state + ", producers=" + producers
				+ ", remarks=" + remarks + "]";
	}
	
	
	
}

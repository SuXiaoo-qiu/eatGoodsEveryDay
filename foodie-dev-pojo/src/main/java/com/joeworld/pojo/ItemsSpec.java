package com.joeworld.pojo;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 商品规格;每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计
 */
public class ItemsSpec {


	/** 商品规格id */
	private String id;
	/** 商品外键id */
	private String itemId;
	/** 规格名称 */
	private String name;
	/** 库存 */
	private Integer stock;
	/** 折扣力度 */
	private BigDecimal discounts;
	/** 优惠价 */
	private Integer priceDiscount;
	/** 原价 */
	private Integer priceNormal;
	/** 创建时间 */
	private Date createdTime;
	/** 更新时间 */
	private Date updatedTime;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public String getItemId() {
		return this.itemId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Integer getStock() {
		return this.stock;
	}
	
	public void setDiscounts(BigDecimal discounts) {
		this.discounts = discounts;
	}
	
	public BigDecimal getDiscounts() {
		return this.discounts;
	}
	
	public void setPriceDiscount(Integer priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	
	public Integer getPriceDiscount() {
		return this.priceDiscount;
	}
	
	public void setPriceNormal(Integer priceNormal) {
		this.priceNormal = priceNormal;
	}
	
	public Integer getPriceNormal() {
		return this.priceNormal;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public Date getUpdatedTime() {
		return this.updatedTime;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        ItemsSpec that = (ItemsSpec) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ItemsSpec{" +
				"id=" + id +
						",itemId='" + itemId + "'" + 
						",name='" + name + "'" + 
						",stock='" + stock + "'" + 
						",discounts='" + discounts + "'" + 
						",priceDiscount='" + priceDiscount + "'" + 
						",priceNormal='" + priceNormal + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
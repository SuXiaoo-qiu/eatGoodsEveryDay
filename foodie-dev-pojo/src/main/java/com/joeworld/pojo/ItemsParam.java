package com.joeworld.pojo;

import java.util.Date;

/**
 * 商品参数
 */
public class ItemsParam {


	/** 商品参数id */
	private String id;
	/** 商品外键id */
	private String itemId;
	/** 产地;产地，例：中国江苏 */
	private String producPlace;
	/** 保质期;保质期，例：180天 */
	private String footPeriod;
	/** 品牌名;品牌名，例：三只大灰狼 */
	private String brand;
	/** 生产厂名;生产厂名，例：大灰狼工厂 */
	private String factoryName;
	/** 生产厂址;生产厂址，例：大灰狼生产基地 */
	private String factoryAddress;
	/** 包装方式;包装方式，例：袋装 */
	private String packagingMethod;
	/** 规格重量;规格重量，例：35g */
	private String weight;
	/** 存储方法;存储方法，例：常温5~25° */
	private String storageMethod;
	/** 食用方式;食用方式，例：开袋即食 */
	private String eatMethod;
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
	
	public void setProducPlace(String producPlace) {
		this.producPlace = producPlace;
	}
	
	public String getProducPlace() {
		return this.producPlace;
	}
	
	public void setFootPeriod(String footPeriod) {
		this.footPeriod = footPeriod;
	}
	
	public String getFootPeriod() {
		return this.footPeriod;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	public String getFactoryName() {
		return this.factoryName;
	}
	
	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}
	
	public String getFactoryAddress() {
		return this.factoryAddress;
	}
	
	public void setPackagingMethod(String packagingMethod) {
		this.packagingMethod = packagingMethod;
	}
	
	public String getPackagingMethod() {
		return this.packagingMethod;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getWeight() {
		return this.weight;
	}
	
	public void setStorageMethod(String storageMethod) {
		this.storageMethod = storageMethod;
	}
	
	public String getStorageMethod() {
		return this.storageMethod;
	}
	
	public void setEatMethod(String eatMethod) {
		this.eatMethod = eatMethod;
	}
	
	public String getEatMethod() {
		return this.eatMethod;
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
        ItemsParam that = (ItemsParam) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ItemsParam{" +
				"id=" + id +
						",itemId='" + itemId + "'" + 
						",producPlace='" + producPlace + "'" + 
						",footPeriod='" + footPeriod + "'" + 
						",brand='" + brand + "'" + 
						",factoryName='" + factoryName + "'" + 
						",factoryAddress='" + factoryAddress + "'" + 
						",packagingMethod='" + packagingMethod + "'" + 
						",weight='" + weight + "'" + 
						",storageMethod='" + storageMethod + "'" + 
						",eatMethod='" + eatMethod + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
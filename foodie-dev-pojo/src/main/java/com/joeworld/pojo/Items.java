package com.joeworld.pojo;

import java.util.Date;

/**
 * 商品表;商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 */
public class Items {


	/** 商品主键id */
	private String id;
	/** 商品名称;商品名称 */
	private String itemName;
	/** 分类外键id;分类id */
	private Integer catId;
	/** 一级分类外键id;一级分类id，用于优化查询 */
	private Integer rootCatId;
	/** 累计销售;累计销售 */
	private Integer sellCounts;
	/** 上下架状态;上下架状态,1:上架 2:下架 */
	private Integer onOffStatus;
	/** 商品内容;商品内容 */
	private String content;
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
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	
	public Integer getCatId() {
		return this.catId;
	}
	
	public void setRootCatId(Integer rootCatId) {
		this.rootCatId = rootCatId;
	}
	
	public Integer getRootCatId() {
		return this.rootCatId;
	}
	
	public void setSellCounts(Integer sellCounts) {
		this.sellCounts = sellCounts;
	}
	
	public Integer getSellCounts() {
		return this.sellCounts;
	}
	
	public void setOnOffStatus(Integer onOffStatus) {
		this.onOffStatus = onOffStatus;
	}
	
	public Integer getOnOffStatus() {
		return this.onOffStatus;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
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
        Items that = (Items) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Items{" +
				"id=" + id +
						",itemName='" + itemName + "'" + 
						",catId='" + catId + "'" + 
						",rootCatId='" + rootCatId + "'" + 
						",sellCounts='" + sellCounts + "'" + 
						",onOffStatus='" + onOffStatus + "'" + 
						",content='" + content + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
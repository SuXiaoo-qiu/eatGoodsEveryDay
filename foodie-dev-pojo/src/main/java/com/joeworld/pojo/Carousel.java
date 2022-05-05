package com.joeworld.pojo;

import java.util.Date;

/**
 * 轮播图
 */
public class Carousel {


	/** 主键 */
	private String id;
	/** 图片;图片地址 */
	private String imageUrl;
	/** 背景色;背景颜色 */
	private String backgroundColor;
	/** 商品id;商品id */
	private String itemId;
	/** 商品分类id;商品分类id */
	private String catId;
	/** 轮播图类型;轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类 */
	private Integer type;
	/** 轮播图展示顺序;轮播图展示顺序，从小到大 */
	private Integer sort;
	/** 是否展示;是否展示，1：展示    0：不展示 */
	private Integer isShow;
	/** 创建时间;创建时间 */
	private Date createTime;
	/** 更新时间;更新 */
	private Date updateTime;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public String getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public String getItemId() {
		return this.itemId;
	}
	
	public void setCatId(String catId) {
		this.catId = catId;
	}
	
	public String getCatId() {
		return this.catId;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	public Integer getIsShow() {
		return this.isShow;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Carousel that = (Carousel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Carousel{" +
				"id=" + id +
						",imageUrl='" + imageUrl + "'" + 
						",backgroundColor='" + backgroundColor + "'" + 
						",itemId='" + itemId + "'" + 
						",catId='" + catId + "'" + 
						",type='" + type + "'" + 
						",sort='" + sort + "'" + 
						",isShow='" + isShow + "'" + 
						",createTime='" + createTime + "'" + 
						",updateTime='" + updateTime + "'" + 
		                '}';
    }
	
}
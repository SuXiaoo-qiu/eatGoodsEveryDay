package com.joeworld.pojo;

import java.util.Date;

/**
 * 商品图片
 */
public class ItemsImg {


	/** 图片主键 */
	private String id;
	/** 商品外键id;商品外键id */
	private String itemId;
	/** 图片地址;图片地址 */
	private String url;
	/** 顺序;图片顺序，从小到大 */
	private Integer sort;
	/** 是否主图;是否主图，1：是，0：否 */
	private Integer isMain;
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
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
	
	public Integer getIsMain() {
		return this.isMain;
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
        ItemsImg that = (ItemsImg) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ItemsImg{" +
				"id=" + id +
						",itemId='" + itemId + "'" + 
						",url='" + url + "'" + 
						",sort='" + sort + "'" + 
						",isMain='" + isMain + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
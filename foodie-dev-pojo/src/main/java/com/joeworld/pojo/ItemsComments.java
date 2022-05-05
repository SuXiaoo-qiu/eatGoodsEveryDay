package com.joeworld.pojo;

import java.util.Date;

/**
 * 商品评价表
 */
public class ItemsComments {


	/** id主键 */
	private String id;
	/** 用户id;用户名须脱敏 */
	private String userId;
	/** 商品id */
	private String itemId;
	/** 商品名称 */
	private String itemName;
	/** 商品规格id;可为空 */
	private String itemSpecId;
	/** 规格名称;可为空 */
	private String sepcName;
	/** 评价等级;1：好评 2：中评 3：差评 */
	private Integer commentLevel;
	/** 评价内容 */
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
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public String getItemId() {
		return this.itemId;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemSpecId(String itemSpecId) {
		this.itemSpecId = itemSpecId;
	}
	
	public String getItemSpecId() {
		return this.itemSpecId;
	}
	
	public void setSepcName(String sepcName) {
		this.sepcName = sepcName;
	}
	
	public String getSepcName() {
		return this.sepcName;
	}
	
	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}
	
	public Integer getCommentLevel() {
		return this.commentLevel;
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
        ItemsComments that = (ItemsComments) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ItemsComments{" +
				"id=" + id +
						",userId='" + userId + "'" + 
						",itemId='" + itemId + "'" + 
						",itemName='" + itemName + "'" + 
						",itemSpecId='" + itemSpecId + "'" + 
						",sepcName='" + sepcName + "'" + 
						",commentLevel='" + commentLevel + "'" + 
						",content='" + content + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
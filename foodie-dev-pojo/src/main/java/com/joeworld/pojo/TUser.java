package com.joeworld.pojo;

import java.util.Date;

/**
 * 用户表
 */
public class TUser {


	/** id */
	private Integer id;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date createdTime;
	/** 更新人 */
	private String updatedBy;
	/** 更新时间 */
	private Date updatedTime;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedBy() {
		return this.updatedBy;
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
        TUser that = (TUser) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "TUser{" +
				"id=" + id +
						",createdBy='" + createdBy + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedBy='" + updatedBy + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
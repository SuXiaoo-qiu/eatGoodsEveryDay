package com.joeworld.pojo;

import java.util.Date;

/**
 * 用户地址表
 */
public class UserAddress {


	/** 地址主键id */
	private String id;
	/** 关联用户id */
	private String userId;
	/** 收件人姓名 */
	private String receiver;
	/** 收件人手机号 */
	private String mobile;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 区县 */
	private String district;
	/** 详细地址 */
	private String detail;
	/** 扩展字段 */
	private String extand;
	/** 是否默认地址;1:是  0:否 */
	private Integer isDefault;
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
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getReceiver() {
		return this.receiver;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getProvince() {
		return this.province;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getDistrict() {
		return this.district;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getDetail() {
		return this.detail;
	}
	
	public void setExtand(String extand) {
		this.extand = extand;
	}
	
	public String getExtand() {
		return this.extand;
	}
	
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	public Integer getIsDefault() {
		return this.isDefault;
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
        UserAddress that = (UserAddress) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "UserAddress{" +
				"id=" + id +
						",userId='" + userId + "'" + 
						",receiver='" + receiver + "'" + 
						",mobile='" + mobile + "'" + 
						",province='" + province + "'" + 
						",city='" + city + "'" + 
						",district='" + district + "'" + 
						",detail='" + detail + "'" + 
						",extand='" + extand + "'" + 
						",isDefault='" + isDefault + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
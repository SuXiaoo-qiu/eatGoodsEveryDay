package com.joeworld.pojo;

import java.util.Date;

/**
 * 订单表
 */
public class Orders {


	/** 订单主键;同时也是订单编号 */
	private String id;
	/** 用户id */
	private String userId;
	/** 收货人快照 */
	private String receiverName;
	/** 收货人手机号快照 */
	private String receiverMobile;
	/** 收货地址快照 */
	private String receiverAddress;
	/** 订单总价格 */
	private Integer totalAmount;
	/** 实际支付总价格 */
	private Integer realPayAmount;
	/** 邮费;默认可以为零，代表包邮 */
	private Integer postAmount;
	/** 支付方式;1:微信 2:支付宝 */
	private Integer payMethod;
	/** 买家留言 */
	private String leftMsg;
	/** 扩展字段 */
	private String extand;
	/** 买家是否评价;1：已评价，0：未评价 */
	private Integer isComment;
	/** 逻辑删除状态;1: 删除 0:未删除 */
	private Integer isDelete;
	/** 创建时间（成交时间） */
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
	
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public String getReceiverName() {
		return this.receiverName;
	}
	
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	
	public String getReceiverMobile() {
		return this.receiverMobile;
	}
	
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public String getReceiverAddress() {
		return this.receiverAddress;
	}
	
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Integer getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setRealPayAmount(Integer realPayAmount) {
		this.realPayAmount = realPayAmount;
	}
	
	public Integer getRealPayAmount() {
		return this.realPayAmount;
	}
	
	public void setPostAmount(Integer postAmount) {
		this.postAmount = postAmount;
	}
	
	public Integer getPostAmount() {
		return this.postAmount;
	}
	
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	
	public Integer getPayMethod() {
		return this.payMethod;
	}
	
	public void setLeftMsg(String leftMsg) {
		this.leftMsg = leftMsg;
	}
	
	public String getLeftMsg() {
		return this.leftMsg;
	}
	
	public void setExtand(String extand) {
		this.extand = extand;
	}
	
	public String getExtand() {
		return this.extand;
	}
	
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	
	public Integer getIsComment() {
		return this.isComment;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
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
        Orders that = (Orders) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Orders{" +
				"id=" + id +
						",userId='" + userId + "'" + 
						",receiverName='" + receiverName + "'" + 
						",receiverMobile='" + receiverMobile + "'" + 
						",receiverAddress='" + receiverAddress + "'" + 
						",totalAmount='" + totalAmount + "'" + 
						",realPayAmount='" + realPayAmount + "'" + 
						",postAmount='" + postAmount + "'" + 
						",payMethod='" + payMethod + "'" + 
						",leftMsg='" + leftMsg + "'" + 
						",extand='" + extand + "'" + 
						",isComment='" + isComment + "'" + 
						",isDelete='" + isDelete + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
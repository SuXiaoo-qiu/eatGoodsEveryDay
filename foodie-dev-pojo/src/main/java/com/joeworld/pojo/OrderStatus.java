package com.joeworld.pojo;

import java.util.Date;

/**
 * 订单状态表;订单的每个状态更改都需要进行记录
10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
退货/退货，此分支流程不做，所以不加入
 */
public class OrderStatus {


	/** 订单ID;对应订单表的主键id */
	private String orderId;
	/** 订单状态 */
	private Integer orderStatus;
	/** 订单创建时间;对应[10:待付款]状态 */
	private Date createdTime;
	/** 支付成功时间;对应[20:已付款，待发货]状态 */
	private Date payTime;
	/** 发货时间;对应[30：已发货，待收货]状态 */
	private Date deliverTime;
	/** 交易成功时间;对应[40：交易成功]状态 */
	private Date successTime;
	/** 交易关闭时间;对应[50：交易关闭]状态 */
	private Date closeTime;
	/** 留言时间;用户在交易成功后的留言时间 */
	private Date commentTime;

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Date getPayTime() {
		return this.payTime;
	}
	
	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}
	
	public Date getDeliverTime() {
		return this.deliverTime;
	}
	
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	
	public Date getSuccessTime() {
		return this.successTime;
	}
	
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	
	public Date getCloseTime() {
		return this.closeTime;
	}
	
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	public Date getCommentTime() {
		return this.commentTime;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        OrderStatus that = (OrderStatus) o;
        return orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(orderId);
    }
    
    @Override
    public String toString() {
        return "OrderStatus{" +
				"orderId=" + orderId +
						",orderStatus='" + orderStatus + "'" + 
						",createdTime='" + createdTime + "'" + 
						",payTime='" + payTime + "'" + 
						",deliverTime='" + deliverTime + "'" + 
						",successTime='" + successTime + "'" + 
						",closeTime='" + closeTime + "'" + 
						",commentTime='" + commentTime + "'" + 
		                '}';
    }
	
}
package com.joeworld.pojo.vo;

import com.joeworld.pojo.bo.ShopcartBo;

import java.util.List;

public class OrderVo {

    private String orderId;
    private MerchantOrdersVo merchantOrdersVo;
    private List<ShopcartBo> toBeRemovedShopcatdList ;

    public List<ShopcartBo> getToBeRemovedShopcatdList() {
        return toBeRemovedShopcatdList;
    }

    public void setToBeRemovedShopcatdList(List<ShopcartBo> toBeRemovedShopcatdList) {
        this.toBeRemovedShopcatdList = toBeRemovedShopcatdList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public MerchantOrdersVo getMerchantOrdersVo() {
        return merchantOrdersVo;
    }

    public void setMerchantOrdersVo(MerchantOrdersVo merchantOrdersVo) {
        this.merchantOrdersVo = merchantOrdersVo;
    }
}
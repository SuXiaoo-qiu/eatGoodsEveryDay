package com.joeworld.pojo.vo;

import com.joeworld.enums.OrderStatusEnum;
import com.joeworld.enums.YesOrNo;

/**
 * 订单状态条数
 */
public class OrderStatusCommentVo {
    private Integer waitPayCounts ;
    private Integer waitDeliverCounts ;
    private Integer waitReceiveCounts ;
    private Integer waitCommentCounts ;

    public OrderStatusCommentVo() {
    }

    public OrderStatusCommentVo(int waitPayCounts, int waitDeliverCounts, int waitReceiveCounts, int waitCommentCounts) {
        this.waitPayCounts = waitPayCounts;
        this.waitDeliverCounts = waitDeliverCounts;
        this.waitReceiveCounts = waitReceiveCounts;
        this.waitCommentCounts = waitCommentCounts;
    }

    public Integer getWaitPayCounts() {
        return waitPayCounts;
    }

    public void setWaitPayCounts(Integer waitPayCounts) {
        this.waitPayCounts = waitPayCounts;
    }

    public Integer getWaitDeliverCounts() {
        return waitDeliverCounts;
    }

    public void setWaitDeliverCounts(Integer waitDeliverCounts) {
        this.waitDeliverCounts = waitDeliverCounts;
    }

    public Integer getWaitReceiveCounts() {
        return waitReceiveCounts;
    }

    public void setWaitReceiveCounts(Integer waitReceiveCounts) {
        this.waitReceiveCounts = waitReceiveCounts;
    }

    public Integer getWaitCommentCounts() {
        return waitCommentCounts;
    }

    public void setWaitCommentCounts(Integer waitCommentCounts) {
        this.waitCommentCounts = waitCommentCounts;
    }
}

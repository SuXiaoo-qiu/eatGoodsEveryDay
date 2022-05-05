package com.joeworld.pojo.vo;

/**
 * 用于展示商品评价的vo
 */
public class CommentLeveCountsVo {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
    private Integer publicCounts;

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getGoodCounts() {
        return goodCounts;
    }

    public void setGoodCounts(Integer goodCounts) {
        this.goodCounts = goodCounts;
    }

    public Integer getNormalCounts() {
        return normalCounts;
    }

    public void setNormalCounts(Integer normalCounts) {
        this.normalCounts = normalCounts;
    }

    public Integer getBadCounts() {
        return badCounts;
    }

    public void setBadCounts(Integer badCounts) {
        this.badCounts = badCounts;
    }

    public Integer getPublicCounts() {
        return publicCounts;
    }

    public void setPublicCounts(Integer publicCounts) {
        this.publicCounts = publicCounts;
    }
}

package com.joeworld.pojo.vo;

import java.util.Date;
import java.util.List;

/**
 *  用于展示商品评价的vo
 */
public class ItmeCommentVo {
    private Integer commentLevel;
    private Date createdTime;
    private String sepcName;
    private String content;
    private String nickname;
    private String userFace;

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getSepcName() {
        return sepcName;
    }

    public void setSepcName(String sepcName) {
        this.sepcName = sepcName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }
}

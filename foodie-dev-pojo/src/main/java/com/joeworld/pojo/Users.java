package com.joeworld.pojo;

import java.util.Date;

/**
 * 用户表
 */
public class Users {


	/** 主键id;用户id */
	private String id;
	/** 用户名;用户名 */
	private String username;
	/** 密码;密码 */
	private String password;
	/** 昵称;昵称 */
	private String nickname;
	/** 真实姓名;真实姓名 */
	private String realname;
	/** 头像;头像 */
	private String face;
	/** 手机号;手机号 */
	private String mobile;
	/** 邮箱地址;邮箱地址 */
	private String email;
	/** 性别;性别 1:男  0:女  2:保密 */
	private Integer sex;
	/** 生日;生日 */
	private Date birthday;
	/** 创建时间;创建时间 */
	private Date createdTime;
	/** 更新时间;更新时间 */
	private Date updatedTime;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getRealname() {
		return this.realname;
	}
	
	public void setFace(String face) {
		this.face = face;
	}
	
	public String getFace() {
		return this.face;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getSex() {
		return this.sex;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return this.birthday;
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
        Users that = (Users) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Users{" +
				"id=" + id +
						",username='" + username + "'" + 
						",password='" + password + "'" + 
						",nickname='" + nickname + "'" + 
						",realname='" + realname + "'" + 
						",face='" + face + "'" + 
						",mobile='" + mobile + "'" + 
						",email='" + email + "'" + 
						",sex='" + sex + "'" + 
						",birthday='" + birthday + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedTime='" + updatedTime + "'" + 
		                '}';
    }
	
}
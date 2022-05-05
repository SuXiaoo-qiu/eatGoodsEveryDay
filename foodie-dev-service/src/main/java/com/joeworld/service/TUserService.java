package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.TUser;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface TUserService {

    /**
    *   分页查询所有记录
    * @param map
    * @return
    */
    PageInfo<ClassInfo> findPage(Map<String,Object> map);
   
	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<TUser> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	TUser getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param tUser 新增的记录
     * @return 返回影响行数
     */
	int insert(TUser tUser);
	
	/**
     * 新增，忽略null字段
     *
     * @param tUser 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(TUser tUser);
	
	/**
     * 修改，修改所有字段
     *
     * @param tUser 修改的记录
     * @return 返回影响行数
     */
	int update(TUser tUser);
	
	/**
     * 修改，忽略null字段
     *
     * @param tUser 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(TUser tUser);
	
	/**
     * 删除记录
     *
     * @param tUser 待删除的记录
     * @return 返回影响行数
     */
	int delete(TUser tUser);


}
package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.Items;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface ItemsService {

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
	List<Items> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Items getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param items 新增的记录
     * @return 返回影响行数
     */
	int insert(Items items);
	
	/**
     * 新增，忽略null字段
     *
     * @param items 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Items items);
	
	/**
     * 修改，修改所有字段
     *
     * @param items 修改的记录
     * @return 返回影响行数
     */
	int update(Items items);
	
	/**
     * 修改，忽略null字段
     *
     * @param items 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Items items);
	
	/**
     * 删除记录
     *
     * @param items 待删除的记录
     * @return 返回影响行数
     */
	int delete(Items items);
	
}
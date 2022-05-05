package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsParam;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface ItemsParamService {

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
	List<ItemsParam> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	ItemsParam getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param itemsParam 新增的记录
     * @return 返回影响行数
     */
	int insert(ItemsParam itemsParam);
	
	/**
     * 新增，忽略null字段
     *
     * @param itemsParam 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(ItemsParam itemsParam);
	
	/**
     * 修改，修改所有字段
     *
     * @param itemsParam 修改的记录
     * @return 返回影响行数
     */
	int update(ItemsParam itemsParam);
	
	/**
     * 修改，忽略null字段
     *
     * @param itemsParam 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(ItemsParam itemsParam);
	
	/**
     * 删除记录
     *
     * @param itemsParam 待删除的记录
     * @return 返回影响行数
     */
	int delete(ItemsParam itemsParam);

	public ItemsParam getItemsId(String itemId);
}
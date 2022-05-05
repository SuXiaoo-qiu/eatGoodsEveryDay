package com.joeworld.service;



import com.joeworld.common.PageInfo;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsSpec;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface ItemsSpecService {

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
	List<ItemsSpec> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	ItemsSpec getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param itemsSpec 新增的记录
     * @return 返回影响行数
     */
	int insert(ItemsSpec itemsSpec);
	
	/**
     * 新增，忽略null字段
     *
     * @param itemsSpec 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(ItemsSpec itemsSpec);
	
	/**
     * 修改，修改所有字段
     *
     * @param itemsSpec 修改的记录
     * @return 返回影响行数
     */
	int update(ItemsSpec itemsSpec);
	
	/**
     * 修改，忽略null字段
     *
     * @param itemsSpec 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(ItemsSpec itemsSpec);
	
	/**
     * 删除记录
     *
     * @param itemsSpec 待删除的记录
     * @return 返回影响行数
     */
	int delete(ItemsSpec itemsSpec);

	/**
	 * 根据商品外键id来查询啥商品规格
	 * @param itemId
	 * @return
	 */
	List<ItemsSpec> getItemsId(String itemId);

	/**
	 * 扣除库存
	 * @param specId
	 * @param pendingCounts
	 */
	int decreaseItemSpecStock(String specId,int pendingCounts);
}
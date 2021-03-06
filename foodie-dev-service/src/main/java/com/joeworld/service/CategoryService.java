package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.Category;
import com.joeworld.pojo.vo.CateGoryVo;
import com.joeworld.pojo.vo.NewCateVo;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface CategoryService {

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
	List<Category> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Category getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param category 新增的记录
     * @return 返回影响行数
     */
	int insert(Category category);
	
	/**
     * 新增，忽略null字段
     *
     * @param category 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Category category);
	
	/**
     * 修改，修改所有字段
     *
     * @param category 修改的记录
     * @return 返回影响行数
     */
	int update(Category category);
	
	/**
     * 修改，忽略null字段
     *
     * @param category 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Category category);
	
	/**
     * 删除记录
     *
     * @param category 待删除的记录
     * @return 返回影响行数
     */
	int delete(Category category);


	/**
	 * 查询二级分类
	 * @param fatherId
	 * @return
	 */
	List<CateGoryVo> selectTwoCarousel(Integer fatherId);


	/**
	 * 查询首页下的没一级分类下的六个最新的商品
	 * @param rootCatId
	 * @return
	 */
	List<NewCateVo> getSixNewItemsLazy(Integer rootCatId);

}
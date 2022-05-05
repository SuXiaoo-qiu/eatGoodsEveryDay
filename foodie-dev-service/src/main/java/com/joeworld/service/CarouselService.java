package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.Carousel;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface CarouselService {

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
	List<Carousel> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Carousel getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
	int insert(Carousel carousel);
	
	/**
     * 新增，忽略null字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Carousel carousel);
	
	/**
     * 修改，修改所有字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
	int update(Carousel carousel);
	
	/**
     * 修改，忽略null字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Carousel carousel);
	
	/**
     * 删除记录
     *
     * @param carousel 待删除的记录
     * @return 返回影响行数
     */
	int delete(Carousel carousel);

	void byz(Carousel carousel);

	List<Carousel> selectListAll(Map<String,Object> map);


}
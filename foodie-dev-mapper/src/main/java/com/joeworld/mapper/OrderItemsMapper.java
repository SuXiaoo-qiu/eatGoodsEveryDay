package com.joeworld.mapper;

import com.joeworld.pojo.OrderItems;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderItemsMapper {
	
    /**
	 * 分页查询所有记录
	 *
	 * @return 返回集合，没有返回空List
	 */
	List<ClassInfo> findPage(Map<String,Object> map);


	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<OrderItems> listAll(Map<String,Object> map);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	OrderItems getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param orderItems 新增的记录
     * @return 返回影响行数
     */
	int insert(OrderItems orderItems);
	
	/**
     * 新增，忽略null字段
     *
     * @param orderItems 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(OrderItems orderItems);
	
	/**
     * 修改，修改所有字段
     *
     * @param orderItems 修改的记录
     * @return 返回影响行数
     */
	int update(OrderItems orderItems);
	
	/**
     * 修改，忽略null字段
     *
     * @param orderItems 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(OrderItems orderItems);
	
	/**
     * 删除记录
     *
     * @param orderItems 待删除的记录
     * @return 返回影响行数
     */
	int delete(OrderItems orderItems);
	
}
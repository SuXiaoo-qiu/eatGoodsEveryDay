package com.joeworld.mapper;

import com.joeworld.pojo.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderStatusMapper {
	
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
	List<OrderStatus> listAll(Map<String,Object> map);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	OrderStatus getById(String orderId);
	
	/**
     * 新增，插入所有字段
     *
     * @param orderStatus 新增的记录
     * @return 返回影响行数
     */
	int insert(OrderStatus orderStatus);
	
	/**
     * 新增，忽略null字段
     *
     * @param orderStatus 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(OrderStatus orderStatus);
	
	/**
     * 修改，修改所有字段
     *
     * @param orderStatus 修改的记录
     * @return 返回影响行数
     */
	int update(OrderStatus orderStatus);
	
	/**
     * 修改，忽略null字段
     *
     * @param orderStatus 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(OrderStatus orderStatus);
	
	/**
     * 删除记录
     *
     * @param orderStatus 待删除的记录
     * @return 返回影响行数
     */
	int delete(OrderStatus orderStatus);

}
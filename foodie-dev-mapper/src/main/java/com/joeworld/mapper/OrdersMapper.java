package com.joeworld.mapper;

import com.joeworld.pojo.Orders;
import com.joeworld.pojo.Users;
import com.joeworld.pojo.vo.MyOrdersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrdersMapper {
	
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
	List<Orders> listAll(Map<String,Object> map);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Orders getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
	int insert(Orders orders);
	
	/**
     * 新增，忽略null字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Orders orders);
	
	/**
     * 修改，修改所有字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
	int update(Orders orders);
	
	/**
     * 修改，忽略null字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Orders orders);
	
	/**
     * 删除记录
     *
     * @param orders 待删除的记录
     * @return 返回影响行数
     */
	int delete(Orders orders);


	List<ClassInfo> selectMyOrders(@Param("paramsMap")Map map);

	 Orders selectMyOrderOne(Orders orders);
}
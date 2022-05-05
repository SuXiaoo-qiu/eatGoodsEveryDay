package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.Orders;
import com.joeworld.pojo.Users;
import com.joeworld.pojo.bo.SubmitOrderBo;
import com.joeworld.pojo.vo.MyOrdersVo;
import com.joeworld.pojo.vo.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface OrdersService {

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

	/**
	 * 创建订单
	 * @param submitOrderBo
	 */
	OrderVo orderCreate(SubmitOrderBo submitOrderBo);

	/**
	 * 查询我的订单列表（分页）
	 * @param map
	 * @return
	 */
	PageInfo<ClassInfo> selectMyOrders(Map<String,Object> map);


	/**
	 * 查询我的订单 单条
	 * @param userId
	 * @param orderId
	 * @return
	 */
	Orders selectMyOrderOne(String userId, String orderId);

	/**
	 * 确认收货
	 * @param userId
	 * @param orderId
	 * @return
	 */
	boolean confirmReceive(String userId, String orderId);

	/**
	 * 删除订单
	 * @param userId
	 * @param orderId
	 * @return
	 */
	boolean deleteOrder(String userId, String orderId);
}
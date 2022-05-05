package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.DateUtil;
import com.joeworld.common.PageInfo;
import com.joeworld.enums.OrderStatusEnum;
import com.joeworld.mapper.OrderStatusMapper;
import com.joeworld.pojo.OrderStatus;
import com.joeworld.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusMapper orderStatusMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = orderStatusMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<OrderStatus> listAll(Map<String,Object> map) {
    	return orderStatusMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public OrderStatus getById(String orderId) {
    	return orderStatusMapper.getById(orderId);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param orderStatus 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(OrderStatus orderStatus) {
    	return orderStatusMapper.insert(orderStatus);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param orderStatus 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(OrderStatus orderStatus) {
    	return orderStatusMapper.insertIgnoreNull(orderStatus);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param orderStatus 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(OrderStatus orderStatus) {
    	return orderStatusMapper.update(orderStatus);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param orderStatus 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(OrderStatus orderStatus) {
    	return orderStatusMapper.updateIgnoreNull(orderStatus);
    }
	
    /**
     * 删除记录
     *
     * @param orderStatus 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(OrderStatus orderStatus) {
    	return orderStatusMapper.delete(orderStatus);
    }

    @Override
    public void notifyMerchantOrderId(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());
        orderStatusMapper.updateIgnoreNull(paidStatus);
    }

    @Override
    public OrderStatus getStatusById(String orderId) {
        return orderStatusMapper.getById(orderId);
    }
    /**
     * 定时一天关闭未支付的订单
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void closeOrder() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderStatus",OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> orderStatuses = orderStatusMapper.listAll(map);
        orderStatuses.forEach(item -> {
            int i = DateUtil.daysBetween(item.getCreatedTime(), new Date());
            if (i>=1) {
                //订单有效期超过一天订单关闭
                this.doCloseOrder(item.getOrderId());
           }
        });
    }
    void doCloseOrder(String orderId){
        OrderStatus clase = new OrderStatus();
        clase.setOrderId(orderId);
        clase.setOrderStatus(OrderStatusEnum.CLOSE.type);
        clase.setCloseTime(new Date());
        orderStatusMapper.updateIgnoreNull(clase);

    }


}
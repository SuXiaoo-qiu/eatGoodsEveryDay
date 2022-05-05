package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.OrderItemsMapper;
import com.joeworld.pojo.OrderItems;
import com.joeworld.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = orderItemsMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<OrderItems> listAll(Map<String,Object> map) {
    	return orderItemsMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public OrderItems getById(String id) {
    	return orderItemsMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param orderItems 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(OrderItems orderItems) {
    	return orderItemsMapper.insert(orderItems);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param orderItems 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(OrderItems orderItems) {
    	return orderItemsMapper.insertIgnoreNull(orderItems);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param orderItems 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(OrderItems orderItems) {
    	return orderItemsMapper.update(orderItems);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param orderItems 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(OrderItems orderItems) {
    	return orderItemsMapper.updateIgnoreNull(orderItems);
    }
	
    /**
     * 删除记录
     *
     * @param orderItems 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(OrderItems orderItems) {
    	return orderItemsMapper.delete(orderItems);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> selectListByOrderId(String orderId) {
        HashMap hashMap = new HashMap();
        hashMap.put("orderId",orderId);
        return orderItemsMapper.listAll(hashMap);
    }
}
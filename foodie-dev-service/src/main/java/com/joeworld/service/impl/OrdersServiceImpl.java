package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.enums.OrderStatusEnum;
import com.joeworld.enums.YesOrNo;
import com.joeworld.mapper.ItemsSpecMapper;
import com.joeworld.mapper.OrderItemsMapper;
import com.joeworld.mapper.OrderStatusMapper;
import com.joeworld.mapper.OrdersMapper;
import com.joeworld.pojo.*;
import com.joeworld.pojo.bo.ShopcartBo;
import com.joeworld.pojo.bo.SubmitOrderBo;
import com.joeworld.pojo.vo.MerchantOrdersVo;
import com.joeworld.pojo.vo.MyOrdersVo;
import com.joeworld.pojo.vo.OrderVo;
import com.joeworld.service.*;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private UserAddressService addressService;
    @Autowired
    private Sid sid;
    @Autowired
    private ItemsSpecService itemsSpecService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ItemsImgService itemsImgService;

    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = ordersMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<Orders> listAll(Map<String,Object> map) {
    	return ordersMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public Orders getById(String id) {
    	return ordersMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(Orders orders) {
    	return ordersMapper.insert(orders);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(Orders orders) {
    	return ordersMapper.insertIgnoreNull(orders);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(Orders orders) {
    	return ordersMapper.update(orders);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(Orders orders) {
    	return ordersMapper.updateIgnoreNull(orders);
    }
	
    /**
     * 删除记录
     *
     * @param orders 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(Orders orders) {
    	return ordersMapper.delete(orders);
    }
    private  ShopcartBo getBycontsFromShopcart(List<ShopcartBo> shopcartBoList,String specId){
       for (ShopcartBo shopcartBo : shopcartBoList) {
           if (shopcartBo.getSpecId().equals(specId)){
               return shopcartBo;
           }
       }
       return null;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVo orderCreate(SubmitOrderBo submitOrderBo,List<ShopcartBo> shopcartBoList) {

        UserAddress userAddress = new UserAddress();
        userAddress.setId(submitOrderBo.getAddressId());
        userAddress.setUserId(submitOrderBo.getUserId());
        //地址
        UserAddress addressList = addressService.selectAddressByidAndUserId(userAddress);
        //保存订单
        Orders order = new Orders();
        order.setId(sid.nextShort());
        order.setUserId(submitOrderBo.getUserId());
        order.setReceiverName(addressList.getReceiver());
        order.setReceiverMobile(addressList.getMobile());
        //拼接省市区详细地址
        order.setReceiverAddress(addressList.getProvince()+
                " "+addressList.getCity()+
                " "+addressList.getDistrict()+
                " "+addressList.getDetail());
//        order.setRealPayAmount();
        order.setPostAmount(YesOrNo.no.type);//邮费
        //支付方式
        order.setPayMethod(submitOrderBo.getPayMethod());
        order.setLeftMsg(submitOrderBo.getLeftMsg()); //评价
        order.setIsComment(YesOrNo.no.type);
        order.setIsDelete(YesOrNo.no.type);
        order.setCreatedTime(new Date());
        //根据itemSpecIds保存订单商品信息表
        String itemSpecIdArr[] = submitOrderBo.getItemSpecIds().split(",");
        Integer totalAmount = 0;   //商品原价
        Integer realPayAmount= 0;  //优惠后实际支付价格的累计

        for (String itemSpecid: itemSpecIdArr) {
            ShopcartBo bycontsFromShopcart = this.getBycontsFromShopcart(shopcartBoList, itemSpecid);
            Integer byCounts = bycontsFromShopcart.getBuyCounts();
            //商品规格
            ItemsSpec itemsSpec = itemsSpecService.getById(itemSpecid);
            // TODO: 2022/4/27 整合redis 后商品购买数量从redis中获取 这里暂时写死
            totalAmount += itemsSpec.getPriceNormal() * byCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * byCounts;
            //商品
            Items itemByIdList = itemsService.getById(itemsSpec.getItemId());
            //商品图片
            String url = itemsImgService.getByIdAndIsMain(itemsSpec.getItemId(), YesOrNo.yes.type);
            OrderItems orderItems = new OrderItems();
            orderItems.setId(sid.nextShort());
            orderItems.setOrderId(order.getId());
            orderItems.setItemId(itemsSpec.getItemId());
            orderItems.setItemName(itemByIdList.getItemName());
            orderItems.setItemImg(url);
            orderItems.setBuyCounts(byCounts);
            orderItems.setItemSpecId(itemsSpec.getId());
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());//优惠价格= 成交价格
            orderItemsMapper.insertIgnoreNull(orderItems);
            //减库存
            int i = itemsSpecService.decreaseItemSpecStock(itemsSpec.getId(), byCounts);
            if (i != 1) {
                throw new RuntimeException("订单创建失败，原因：库存不足！");
            }




        }
        order.setTotalAmount(totalAmount);
        order.setRealPayAmount(realPayAmount);
        ordersMapper.insertIgnoreNull(order);
        //保存订单状态表
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(order.getId());
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insertIgnoreNull(orderStatus);

        MerchantOrdersVo merchantOrdersVo = new MerchantOrdersVo();
        merchantOrdersVo.setMerchantOrderId(order.getId());
        merchantOrdersVo.setMerchantUserId(submitOrderBo.getUserId());
        merchantOrdersVo.setAmount(realPayAmount+ order.getPostAmount()); //实际支付总金额（包含商户所支付的订单费邮费总额）
        merchantOrdersVo.setPayMethod(order.getPayMethod());//支付方式

        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(order.getId());
        orderVo.setMerchantOrdersVo(merchantOrdersVo);
        return orderVo;
    }

    /**
     * 我的订单
     * @param map
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<ClassInfo> selectMyOrders(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = ordersMapper.selectMyOrders(map);
        return new PageInfo<>(page);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Orders selectMyOrderOne(String userId, String orderId) {
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setId(orderId);
        orders.setIsDelete(YesOrNo.no.type);
        return ordersMapper.selectMyOrderOne(orders);
    }

    /**
     * 确认收获
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean confirmReceive(String userId, String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        orderStatus.setSuccessTime(new Date());
        int i = orderStatusMapper.updateIgnoreNull(orderStatus);
        return i == 1 ? true : false ;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteOrder(String userId, String orderId) {
        Orders orders = new Orders();
        orders.setIsDelete(YesOrNo.yes.type);
        orders.setId(orderId);
        orders.setUserId(userId);
        orders.setUpdatedTime(new Date());
        int i = ordersMapper.updateIgnoreNull(orders);
        System.out.println("修改成功");
        return i == 1 ? true : false ;

    }


}
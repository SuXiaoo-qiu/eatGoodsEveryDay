package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.enums.YesOrNo;
import com.joeworld.pojo.OrderItems;
import com.joeworld.pojo.Orders;
import com.joeworld.service.OrderItemsService;
import com.joeworld.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orderItemsController")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;
    @Autowired
    private OrdersService ordersService;
    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = orderItemsService.findPage(params);
        if (pageInfo.getList().size() > 0){
            pageInfo.setCode(200);
            pageInfo.setMessage("成功");
        }else {
            pageInfo.setPageSize(0);
            pageInfo.setCode(500);
            pageInfo.setMessage("暂无数据");
        }

        return pageInfo;
        
    }
    
    /**
    *查询所有记录
    * @param params
    * @return 返回集合，没有返回空List
    */
    @ApiOperation(value = "条件查询全部数据")
    @RequestMapping("/list")
    public R listAll(@RequestParam Map<String, Object> params) {
     List<OrderItems> OrderItems =  orderItemsService.listAll(params);
        return  R.ok(OrderItems);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public OrderItems getById(String id) {
        return orderItemsService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param orderItems 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody OrderItems orderItems) {
      int insert = orderItemsService.insertIgnoreNull(orderItems);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param orderItems 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody OrderItems orderItems) {
    int update = orderItemsService.updateIgnoreNull(orderItems);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param orderItems 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody OrderItems orderItems) {
     	int delete = orderItemsService.delete(orderItems);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }

    /**
     * 根据用户id 查询评价列表（lsit）
     * @param orderId
     * @return
     */
    @ApiOperation(value = "根据用户id 查询评价列表（lsit）")
    @RequestMapping("/selectListByOrderId")
    @ResponseBody
    public R selectListByOrderId(@RequestParam String orderId,@RequestParam String userId) {
        /**
         * 确认订单和用户同一个
         */
        Orders orders = ordersService.selectMyOrderOne(userId, orderId);
        if (orders == null) {
            return R.error("订单存在");
        }
        if (null!=orders.getIsComment() && YesOrNo.yes.type == orders.getIsComment()){
            return R.error("该订单已经评价过！请刷新后重试！");
        }
        List<OrderItems> OrderItems =  orderItemsService.selectListByOrderId(orderId);
        return  R.ok(OrderItems);
    }




}
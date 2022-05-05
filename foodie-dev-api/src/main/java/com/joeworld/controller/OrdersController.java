package com.joeworld.controller;

import com.joeworld.common.BaseConller;
import com.joeworld.common.CookieUtils;
import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.pojo.Orders;
import com.joeworld.pojo.bo.SubmitOrderBo;
import com.joeworld.pojo.vo.MerchantOrdersVo;
import com.joeworld.pojo.vo.OrderVo;
import com.joeworld.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ordersController")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "订单",tags = {"订单相关的接口"})
public class OrdersController  extends BaseConller {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = ordersService.findPage(params);
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
     List<Orders> Orders =  ordersService.listAll(params);
        return  R.ok(Orders);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public Orders getById(String id) {
        return ordersService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody Orders orders) {
      int insert = ordersService.insertIgnoreNull(orders);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody Orders orders) {
    int update = ordersService.updateIgnoreNull(orders);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param orders 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Orders orders) {
     	int delete = ordersService.delete(orders);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }

    /**
     * 用户下单
     * @param submitOrderBo
     * @return
     */
    @ApiOperation(value = "用户下单")
    @PostMapping("/orderCreate")
    public R orderCreate(@RequestBody SubmitOrderBo submitOrderBo, HttpServletRequest request, HttpServletResponse response) {
        if (submitOrderBo == null) {
            return R.error("请求参数为空");
        }
        if (StringUtils.isBlank(submitOrderBo.getPayMethod().toString())){
            return R.error("支付方式错误");
        }
        OrderVo orderVo = ordersService.orderCreate(submitOrderBo);
        MerchantOrdersVo merchantOrdersVo = orderVo.getMerchantOrdersVo();
        merchantOrdersVo.setReturnUrl(payReturnURL);
        merchantOrdersVo.setAmount(1);


        // TODO: 2022/4/28  整合redis之后。完善购物车中已经结算的商品进行清除，并且同步到前端的cookie中
//        CookieUtils.setCookie(request, response, FOODIE_SHOPCART,"",true);
        HttpHeaders httpHeaders = new HttpHeaders();
        //传输数据类型
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //账号密码
        httpHeaders.add("imoocUserId","imooc");
        httpHeaders.add("password","imooc");
        HttpEntity<MerchantOrdersVo> objectHttpEntity = new HttpEntity<>(merchantOrdersVo,httpHeaders);
        ResponseEntity<R> rResponseEntity = restTemplate.postForEntity(paymentUrl, objectHttpEntity, R.class);
        R body = rResponseEntity.getBody();
        if (body.getStatus() != 200) {
            return R.error("支付中心订单创建失败，请联系管理员");

        }

        return R.ok(orderVo.getOrderId());
    }


    /**
     * 查询我的订单列表（分页）
     * @param userId
     * @param orderStatus
     * @param pageSize
     * @param page
     * @return
     */
    @ApiOperation(value = "查询我的订单列表（分页）")
    @RequestMapping("/selectMyOrders")
    public PageInfo<ClassInfo> selectMyOrders(@RequestParam String userId,
                                              @RequestParam Integer orderStatus,
                                              @RequestParam Integer pageSize,
                                              @RequestParam Integer page){
        Map map = new HashMap();
        map.put("userId",userId);
        map.put("orderStatus",orderStatus);
        map.put("pageCode",page);
        map.put("pageSize",pageSize);
        PageInfo<ClassInfo> pageInfo = ordersService.selectMyOrders(map);
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
     * 确认收货
     * @param userId
     * @param orderId
     * @return
     */
    @ApiOperation(value = "确认收货")
    @RequestMapping("/confirmReceive")
    public R confirmReceive(@RequestParam String userId,
                                              @RequestParam String orderId){

        Orders orders = ordersService.selectMyOrderOne(userId, orderId);
        if (orders == null) {
            return R.error("订单存在");
        }
        boolean b = ordersService.confirmReceive(userId, orderId);
        if (b) {
            return R.ok();
        }
        return R.error("确认失败 刷新后重试");
    }
    /**
     * 删除订单
     * @param userId
     * @param orderId
     * @return
     */
    @ApiOperation(value = "删除订单")
    @RequestMapping("/deletOrder")
    public R deletOrder(@RequestParam String userId,
                            @RequestParam String orderId){

        Orders orders = ordersService.selectMyOrderOne(userId, orderId);
        if (orders == null) {
            return R.error("订单存在");
        }
        boolean b = ordersService.deleteOrder(userId, orderId);
        if (b) {
            return R.ok();
        }
        return R.error("删除失败");




    }



}
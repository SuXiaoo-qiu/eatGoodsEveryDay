package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.enums.OrderStatusEnum;
import com.joeworld.pojo.OrderStatus;
import com.joeworld.pojo.Orders;
import com.joeworld.service.OrderStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "订单状态",tags = {"订单状态相关的接口(微信支付)"})
public class OrderUpStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    /**
     * 修改支付状态
     * @param merchantOrderId
     * @return
     */
    @ApiOperation(value = "修改支付状态")
    @PostMapping("/notifyMerchantOrderId")
    public R notifyMerchantOrderId(String merchantOrderId) {
        orderStatusService.notifyMerchantOrderId(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return R.ok();
    }


    /**
     * 根据id查询查询订单状态
     * @param orderId
     * @return
     */
    @ApiOperation(value = "根据id查询查询订单状态")
    @RequestMapping("/getStatusById")
    public R getStatusById(String orderId) {
        OrderStatus statusById = orderStatusService.getStatusById(orderId);
        return R.ok(statusById);
    }



}